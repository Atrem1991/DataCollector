package metroPackage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    private static Map<String,Float> stationMap = new HashMap<>();

    public static void parseJSON() throws IOException {
        List<File> jsonFiles = FileFinder.getJsonList();
        for (File file : jsonFiles){
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode array = (ArrayNode)mapper.readTree(file);
            for(int i = 0; i < array.size(); i++){
                JsonNode node = array.get(i);
                String stationName = node.get("station_name").asText().toLowerCase();
                float stationDepth;
                String depth = node.get("depth").asText().replace(',','.');
                if(checkDepthFormat(depth)){
                    stationDepth = Float.parseFloat(depth);
                } else {
                    stationDepth = (float)-999;      //в JSON фалах есть некорректные данные по глубине.
                }
                stationMap.put(stationName,stationDepth);
            }
        }
    }
    public static float getDepth(String station) throws IOException {
        return stationMap.get(station);
    }
    private static boolean checkDepthFormat(String depth){
        String reg = "[-]?[0-9]+[.]?[0-1]?";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(depth);
        return matcher.find();
    }
}
