package gamemap;

import static client.GlobalVariables.subAreas;

import java.util.HashMap;
import java.util.Map;
import ui.maps.UIEnterSubarea;

public class SubAreaFactory {

    public static Map<String, String> subAreaHashMap() {
        Map<String, String> localSubAreasInstance = new HashMap<>();

        subAreas.forEach(a -> {
            String des = convertDescription(a.getDescription());
            localSubAreasInstance.put(a.getName(), des);
        });

        return localSubAreasInstance;
    }

    private static String convertDescription(String des) {
        StringBuilder convertedDescription = new StringBuilder();

        while(des.length() >= UIEnterSubarea.x_axis_subArea) {
            int index = UIEnterSubarea.x_axis_subArea;
            while (des.charAt(index-1) != ' ') {
                index--;
            }
            convertedDescription.append(des, 0, index);
            convertedDescription.append("\n");

            des = des.substring(index);
        }

        convertedDescription.append(des);
        return convertedDescription.toString();
    }
}
