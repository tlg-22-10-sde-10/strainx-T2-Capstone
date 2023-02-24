package ui.gui;

import gamemodel.mapengine.SubArea;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import static gamecontrol.GlobalVariables.inGameMap;

public class ButtonCoordinates {

    public static HashMap<SubArea, ArrayList<Integer>> buttonLocations = getRandomAreaLocations();

    public static HashMap<SubArea, ArrayList<Integer>> getRandomAreaLocations() {
        HashMap<SubArea, ArrayList<Integer>> randomAreaLocations = new HashMap<>();
        ArrayList<Integer> buttonCoordinates;
        HashMap<Integer, Point> coords = generateFixedCoordinates();
        HashSet<Point> selected = new HashSet<>();
        ArrayList<Point> points;
        Random random = new Random();

        for (java.util.List<SubArea> mapIdx : inGameMap.gameMap.values()) {
            // Get Unique N Points base on Current List of Size N
            while(selected.size() < mapIdx.size() ){
                int r = random.nextInt(coords.size());
                Point point = coords.get(r);
                if(point != null && (r != 2)) selected.add(point);
            }
            points = new ArrayList<>(selected);

            // Give each Subarea a Unique Point
            for (SubArea subArea : mapIdx) {
                buttonCoordinates = new ArrayList<>();
                Point point = points.remove(0);

                buttonCoordinates.add(point.x);
                buttonCoordinates.add(point.y);
                randomAreaLocations.put(subArea, buttonCoordinates);
            }
            // Clear Current Selected Points for Next List
            selected.clear();
        }

        return randomAreaLocations;
    }
    private static HashMap<Integer, Point> generateFixedCoordinates(){
        HashMap<Integer, Point> coordinates = new HashMap<>();
        int width = 1024;
        int height = (int) (768 * .9);
        int cellWidth = (width / inGameMap.getDimensionY());
        int cellHeight = (height / inGameMap.getDimensionX());
        int w = (cellWidth / 3);
        int h = (cellHeight / 3);
        System.out.println(w);
        System.out.println(h);
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

}

//        int randomX = random.nextInt(cellWidth/3);
//        int randomY = random.nextInt(cellHeight/3);
//        while(!uniqueLocation){
//            ranges.put(randomX,randomY);
//            for (Map.Entry<Double,Double> set : ranges.entrySet()) {
//                boolean x =(randomX >= set.getKey()) && (randomX <= set.getKey()+itemWidth);
//                boolean y =(randomY >= set.getValue()) && (randomY <= set.getValue()+itemHeight);
//                if(!x && !y){
//                    randomX = random.nextInt(cellWidth/3)+itemWidth;
//                    randomY = random.nextInt(cellHeight/3)+itemHeight;
//                    ranges.put(randomX,randomY);
//                    uniqueLocation = !uniqueLocation;
//                }
//            }
//        }
////        Rectangle area;
//
////        int width = 1024;
////        int height = (int) (768 * .9);
////        int cellWidth = (width / inGameMap.getDimensionY());
////        int cellHeight = (height / inGameMap.getDimensionX());
////        int btnW = (cellWidth / 3);
////        int btnH = (cellHeight / 3);
////        int randomX = 0;
////        int randomY = 0;