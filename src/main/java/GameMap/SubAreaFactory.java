package GameMap;

import com.fasterxml.jackson.databind.JsonNode;
import jsonparsing.json;

import java.io.IOException;
import java.util.HashMap;

public class SubAreaFactory {

    private static final int numberOfLocations = 12;
    private static final String path = "src/main/resources/locations.json";

    public static HashMap<String, String> createSubArea() throws IOException {
        HashMap<String, String> subAreas = new HashMap<>();
        JsonNode locationsNode = json.jsonFileReader(path);

        for (int i = 0; i < numberOfLocations; i++) {
            subAreas.put(locationsNode.get("locations").get(i).get("name").textValue(), locationsNode.get("locations").get(i).get("description").textValue());
        }
        System.out.println(subAreas.keySet());
        System.out.println(subAreas.values());

        return subAreas;
    }

}
