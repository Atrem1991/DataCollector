package metroPackage;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonWriter {
    private ObjectMapper mapper;
    private ObjectWriter writer;

    public JsonWriter() {
        mapper = new ObjectMapper();
        writer = mapper.writer(new DefaultPrettyPrinter());
    }

    public <Metro> void writeStations(String outPath, Metro metro){
        File out = new File(outPath);
        try {
            writer.writeValue(out, metro);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
