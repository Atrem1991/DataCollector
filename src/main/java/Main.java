import metroPackage.Metro;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args){
        Metro metro = new Metro(
                new File("C:\\Users\\dell\\Desktop\\data") //адрес папки data
        );
        metro.writeStations("OUT\\json1.json");
        metro.writeLine("OUT\\json2.json");
    }
}