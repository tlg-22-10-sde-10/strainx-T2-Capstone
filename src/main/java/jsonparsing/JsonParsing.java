package jsonparsing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class JsonParsing {

    private static final ObjectMapper objectMapper = getDefaultMapper();

    private static ObjectMapper getDefaultMapper() {
        ObjectMapper defaultMapper = new ObjectMapper();

        defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return defaultMapper;
    }

    public static JsonNode jsonFileReader(String path) throws IOException {
        return objectMapper.readTree(new File(path));
    }

    //reflection
    public static InputStream openResource(String name) {
        return JsonParsing.class.getClassLoader().getResourceAsStream(name);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
