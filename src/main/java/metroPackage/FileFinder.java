package metroPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    private static List<File> jsonList = new ArrayList<>();
    private static List<File> csvList = new ArrayList<>();



    public static void fileFinder(File rootPath){
        if(rootPath.isDirectory()){
            File[] paths = rootPath.listFiles();
            for(File path : paths){
                if(path.isFile()){
                    if(path.getAbsolutePath().endsWith(".json"))
                    {
                        jsonList.add(path);
                    }
                    else if(path.getAbsolutePath().endsWith(".csv"))
                    {
                        csvList.add(path);
                    }
                }
                else if(path.isDirectory()){
                    fileFinder(path);
                }
            }
        }
    }

    public static List<File> getJsonList() {
        return jsonList;
    }

    public static List<File> getCsvList() {
        return csvList;
    }

}
