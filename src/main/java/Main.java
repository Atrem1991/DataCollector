import metroPackage.JsonWriter;
import metroPackage.Metro;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Metro metro = new Metro(new File("C:\\Users\\Artem.Gusev\\Desktop\\stations-data (1)\\data"));
//        metro.toShow();
//        JsonWriter writer = new JsonWriter();
//        writer.writeStations("OUT\\json.json", metro);
        metro.writeStations("OUT\\json.json");
        metro.writeStationsToLine("OUT\\json.json");

    }
}