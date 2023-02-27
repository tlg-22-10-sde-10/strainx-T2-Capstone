package ui.gui;

import gamemodel.mapengine.SubArea;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.List;

import static gamecontrol.GlobalVariables.inGameMap;

public class ButtonCoordinates {

    public static int w = (int)(buttonDimension().getWidth());
    public static int h = (int)(buttonDimension().getHeight());
    public static HashMap<SubArea, Point> buttonLocations = getRandomAreaLocations();

    public static HashMap<SubArea, Point> getRandomAreaLocations() {
        HashMap<SubArea, Point> randomAreaLocations = new HashMap<>();
        ArrayList<Point> points;

        for (List<SubArea> mapIdx : inGameMap.gameMap.values()) {
            points = getUniquePoints(mapIdx); //match size
            assignPointToSubarea(mapIdx,points,randomAreaLocations); // assign Point
            if(!points.isEmpty()) points.clear(); // refresh list
        }
        return randomAreaLocations;
    }
    public static Integer assignPointToSubarea(List<SubArea> mapIdx, ArrayList<Point> points,HashMap<SubArea, Point> randomAreaLocations){
        // Give each Subarea a Unique Point
        for (SubArea subArea : mapIdx) {
            Point point = points.remove(0);
            randomAreaLocations.put(subArea, point);
        }
        return 1;
    }
    public static ArrayList<Point> getUniquePoints(List<SubArea> mapIdx){
        // Get Unique N Points based on Current List of Size N
        HashSet<Point> selected = new HashSet<>();
        HashMap<Integer, Point> coords = generateFixedCoordinates();

        while(selected.size() < mapIdx.size() ){
            Random random = new Random();
            int r = random.nextInt(coords.size());
            Point point = coords.get(r);
            if(point != null) selected.add(point);
        }

        return new ArrayList<>(selected);
    }
    public static HashMap<Integer, Point> generateFixedCoordinates(){
        HashMap<Integer, Point> coordinates = new HashMap<>();
        int w = ButtonCoordinates.w;
        int h = ButtonCoordinates.h;

        coordinates.put(1,new Point(0,0));
        coordinates.put(2,new Point(w,0));
        coordinates.put(3,new Point(2*w,0));
        coordinates.put(4,new Point(0,h));
        coordinates.put(5,new Point(w,h));
        coordinates.put(6,new Point(2*w,h));
        coordinates.put(7,new Point(0,2*h));
        coordinates.put(8,new Point(w,2*h));
        coordinates.put(9,new Point(2*w,2*h));
        return coordinates;
    }
    public static Dimension buttonDimension(){
        int width = 1024;
        int height = (int) (768 * .9);
        int cellWidth = (width / inGameMap.getDimensionY());
        int cellHeight = (height / inGameMap.getDimensionX());
        w = (cellWidth / 3);
        h = (cellHeight / 3);
       return new Dimension(w,h);
    }
}