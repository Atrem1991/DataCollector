package metroPackage;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Metro {
    private final List<Station> stations = new ArrayList<>();
    private final Map<String, List<String>> stationsToLine = new HashMap<>();
    private final Set<Line> lines = new HashSet<>();

    public Metro(File pathToFiles) throws IOException {
        FileFinder.fileFinder(pathToFiles);
        CsvParser.parseCsv();
        JsonParser.parseJSON();
        metroParser(HtmlParser.createStationsList());
    }

    private void metroParser(List<String> stationsList){
        for(String stationInfo : stationsList){
            try {
                String[] stationData = getSeparatedProperty(stationInfo);
                String[] lineData = stationData[1].split("->");

                String stationName = stationData[0].strip();
                String lineNumber = lineData[1];
                String lineName = lineData[0];
                boolean isConnection = checkConnectionValue(stationData[2]);
                float depth = JsonParser.getDepth(stationName.toLowerCase());
                String date = CsvParser.getDate(stationName.toLowerCase());

                stations.add(
                        new Station(stationName, lineName,date,depth, isConnection)
                );
                setStationsToLine(lineNumber,stationName);
                setLines(lineName, lineNumber);

            }
            catch (Exception e){
                e.getStackTrace();
            }
        }
    }

    private String[] getSeparatedProperty(String properties){
        return properties.split(";");
    }
    private boolean checkConnectionValue(String value){
        boolean isConnection;
        isConnection = value.equalsIgnoreCase("true");
        return isConnection;
    }

    private void setStationsToLine(String lineNumber, String stationName){

        if(stationsToLine.containsKey(lineNumber)){
            List<String> stations = stationsToLine.get(lineNumber);
            stations.add(stationName);
            stationsToLine.replace(lineNumber,stations);
        }
        else {
            List<String> stationsName = new ArrayList<>();
            stationsName.add(stationName);
            stationsToLine.put(lineNumber, stationsName);
        }
    }
    private void setLines(String lineName, String lineNumber){
        Line line = new Line(lineName,lineNumber);
        lines.add(line);
    }

    public void writeStations(String outPath){
        File outFile = new File(outPath);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(outFile, stations);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void writeStationsToLine(String outPath){
        File outFile = new File(outPath);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            TreeMap<String, List<String>> tree = new TreeMap<>(stationsToLine);
            tree.comparator();
            writer.writeValue(outFile, tree);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
