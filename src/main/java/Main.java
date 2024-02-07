import metroPackage.HtmlParser;
import metroPackage.Metro;
import metroPackage.Station;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class Main {
    public static void main(String[] args) throws IOException {
        Metro metro = new Metro(new File("C:\\Users\\dell\\Desktop\\data"));
        metro.toShow();
    }
}