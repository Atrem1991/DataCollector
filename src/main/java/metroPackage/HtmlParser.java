package metroPackage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 public class HtmlParser {
    private static Document htmlDocument;
    private static List<String> lineNameList = new ArrayList<>();
    public static List<String> stationsList = new ArrayList<>();
    public static Elements stationsGroupsList;
    private static Elements addStationsGroupsList(){
        stationsGroupsList = htmlDocument.select("#metrodata > div > div > div");
        return stationsGroupsList;
    }
    private static boolean isConnection(Element station) {
        String connection = station.select("span").attr("title");
        if(connection.isEmpty()){
            return false;
        }
        return true;
    }
    private static void setLineList(){
        Elements lines = htmlDocument.select("#metrodata > div > div > span");
        for(Element e : lines){
            String lineNumber = e.attr("data-line");
            String lineName = e.text();
            lineNameList.add(lineName + "->" + lineNumber);
        }
    }
    private static String getLineFromList(int index){
        return lineNameList.get(index).replaceAll("линия", "");
    }
    static List<String> createStationsList(){
        getHTML();
        addStationsGroupsList();
        setLineList();
        int index = 0;
        for (Element stationElementsGroup : stationsGroupsList){
            String line = getLineFromList(index);
            Elements stations = stationElementsGroup.select("p");
            for(Element station :stations){
                String name = station.select("span.name").text().strip();
                boolean isConnection = isConnection(station);
                stationsList.add(name + ";" + line + ";" + isConnection);
            }
        index++;
        }
        return stationsList;
    }
    private static Document getHTML(){
        try {
            String url = "https://skillbox-java.github.io/";
            htmlDocument = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return htmlDocument;
    }
}
