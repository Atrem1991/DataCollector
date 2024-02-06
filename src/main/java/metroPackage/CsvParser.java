package metroPackage;
import java.io.*;
import java.util.*;

public class CsvParser {
    private static Map<String,String> stationDateInfo = new HashMap<>();
   private static List<String> data = new ArrayList<>();
    private static void readCsvToList() throws IOException {
        List<File> csvFiles = FileFinder.getCsvList();
        for (File csvFile : csvFiles) {
                BufferedReader reader = new BufferedReader(new FileReader(csvFile));
                String line;
                while((line = reader.readLine()) != null){
                    data.add(line);
                }
        }
    }
    public static void parseCsv() throws IOException {
     readCsvToList();
        for(String str : data){
            String[] apartedInfo = str.split(",");
            String stationName = apartedInfo[0].toLowerCase();
            String date = apartedInfo[1];
            stationDateInfo.put(stationName,date);
        }
    }
    public static String getDate(String stationName){
        return stationDateInfo.get(stationName);
    }
}
