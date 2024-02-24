package metroPackage;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Metro {
    private final List<Station> stations = new ArrayList<>();
    private final Map<String, List<String>> stationsToLine = new LinkedHashMap<>();
    private final Set<Line> lines = new TreeSet<>();

    public Metro(File pathToFiles){
        try {
            FileFinder.fileFinder(pathToFiles);
            CsvParser.parseCsv();
            JsonParser.parseJSON();
            metroParser(HtmlParser.createStationsList());
        }
        catch (Exception e){
            e.getStackTrace();
        }
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
        StationsCollector stationsCollector = new StationsCollector(stations);
        stationsCollector.toJson(new File(outPath));
    }

    public void writeLine(String outPath){
        File outFile = new File(outPath);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            LinesCollector linesCollector = new LinesCollector(stationsToLine,lines);
            writer.writeValue(outFile, linesCollector);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class LinesCollector{       //Класс для записи JSON фала с информацией по линиям метро(Название,номер + Список станций)
        Map<String, List<String>> stations = new LinkedHashMap<>();
        Set<Line> lines = new TreeSet<>() ;

        public LinesCollector(Map<String, List<String>> stationsToLine, Set<Line> lines){
            stations = stationsToLine;
            this.lines = lines;
        }
        public LinesCollector(){

        }
        public Map<String, List<String>> getStations() {
            return stations;
        }

        public void setStations(Map<String, List<String>> stations) {
            this.stations = stations;
        }

        public Set<Line> getLines() {
            return lines;
        }

        public void setLines(Set<Line> lines) {
            this.lines = lines;
        }
    }

    static  class StationsCollector{   // Класс для записи JSON фала с информацией по станциям метро
        private Map<String, List<Station>> stationsMap;
        public StationsCollector(List<Station> stationsList){
            stationsMap = new HashMap<>();
            stationsMap.put("stations", stationsList);
        }
        public Map<String, List<Station>> getStationsMap() {
            return stationsMap;
        }
        public void setStationsMap(Map<String, List<Station>> stationsMap) {
            this.stationsMap = stationsMap;
        }
        public void toJson(File outPath){
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            try {
                writer.writeValue(outPath, stationsMap);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
