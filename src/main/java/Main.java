import metroPackage.Metro;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Metro metro = new Metro(new File("C:\\Users\\dell\\Desktop\\data"));
//        metro.toShow();
//        JsonWriter writer = new JsonWriter();
//        writer.writeStations("OUT\\json.json", metro);
        metro.writeStations("OUT\\json.json");
        metro.writeLine("OUT\\json2.json");
    }
}