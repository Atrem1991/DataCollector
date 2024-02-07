package metroPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Metro {
    public final List<Station> stationsObjectsList = new ArrayList<>();

    public Metro(File pathToFiles) throws IOException {
        FileFinder.fileFinder(pathToFiles);
        CsvParser.parseCsv();
        JsonParser.parseJSON();
        createStationObjectList(HtmlParser.createStationsList());
    }

    private void createStationObjectList(List<String> stationsList){
        for(String stationInfo : stationsList){
            try {
                String[] stationData = getSeparatedProperty(stationInfo);
                String[] lineData = stationData[1].split("->");

                String stationName = stationData[0].strip();
                Line line = new Line(lineData[0],lineData[1]);
                boolean isConnection = checkConnectionValue(stationData[2]);
                float depth = JsonParser.getDepth(stationName.toLowerCase());
                String date = CsvParser.getDate(stationName.toLowerCase());

                stationsObjectsList.add(
                        new Station(stationName, line,date,depth, isConnection)
                );
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
    public void toShow(){
        for(Station st : stationsObjectsList){
            System.out.println(st);
        }
    }
}
