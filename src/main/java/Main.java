import metroPackage.Metro;
import metroPackage.Station;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Metro metro = new Metro(new File("C:\\Users\\dell\\Desktop\\data"));
        metro.toShow();
    }
}