import metroPackage.Metro;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args){
        Metro metro = new Metro(new File("C:\\Users\\Artem.Gusev\\Desktop\\stations-data (1)\\data"));
        metro.writeStations("OUT\\json.json");
        metro.writeLine("OUT\\json2.json");
    }
}