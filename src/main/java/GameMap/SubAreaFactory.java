package GameMap;

import com.fasterxml.jackson.databind.JsonNode;
import jsonparsing.json;

import java.io.IOException;
import java.util.HashMap;

public class SubAreaFactory {

    private static final int numberOfLocations = 12;
    private static final String path = "src/main/resources/locations.json";

    public static int locationsInJson = 0;

    public static HashMap<String, String> subAreaHashMap() throws IOException {
        HashMap<String, String> subAreas = new HashMap<>();
        JsonNode locationsNode = json.jsonFileReader(path);

        var locations = locationsNode.get("locations");
        locationsInJson = locations.size();

        for (int i = 0; i < locationsInJson; i++) {
            String locationName = locations.get(i).get("name").textValue();
            String locationDescription = locations.get(i).get("description").textValue();

            subAreas.put(locationName, locationDescription);
        }

        return subAreas;
    }
}
