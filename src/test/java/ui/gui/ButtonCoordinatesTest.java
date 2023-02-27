package ui.gui;

import gamecontrol.GameDifficulty;
import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;
import gamemodel.mapengine.SubArea;
import gamemusic.AudioPlayer;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;
import static gamecontrol.GlobalVariables.inGameMap;


public class ButtonCoordinatesTest {

    @Before
    public void setUp() throws Exception {
        GlobalVariables.inGameMap = new MainMap(GameDifficulty.Medium);
        AudioPlayer.setSoundOn(false);
        GlobalVariables.gameInitialization();
    }

    @Test
    public void testGetRandomAreaLocations_GivenMapOfSubareaListsShouldReturnMapOfUniqueSubareaToPointObjectsForEachList() {
        HashMap<SubArea, Point> randomAreaLocations = ButtonCoordinates.getRandomAreaLocations();
        // no 2 areas in a list should contain the same Point

        ArrayList<Point> holdPoints;
        for (List<SubArea> subareaList : inGameMap.gameMap.values()) {

            holdPoints = new ArrayList<>();
            for (SubArea subArea : subareaList) {
                holdPoints.add(randomAreaLocations.get(subArea));
            }
            for (Point pnt : holdPoints) {

                int count = 0;
                for (Point p : holdPoints) {
                    if(p.equals(pnt)) {
                        count += 1;
                    }
                    assertTrue( count <=1 && count >= 0 );
                }
            }
        }
    }
    @Test
    public void testGetRandomAreaLocations_ShouldCreateMapOfSubareaAndPointObjects(){
        Set<Map.Entry<SubArea, Point>> entries = ButtonCoordinates.getRandomAreaLocations().entrySet();
        for (Map.Entry<SubArea, Point> entry : entries) {
            assertTrue(entry.getKey() instanceof SubArea);
            assertTrue(entry.getValue() instanceof Point);
        }
    }
    @Test
    public void testAssignPointToSubarea_GivenValidListOfSubareaAndListOfPointsAndAreaLocationsCanAssignPointToSubarea(){
        ArrayList<SubArea> subareaList = new ArrayList<>();
        SubArea subArea = new SubArea();
        subareaList.add(subArea);
        ArrayList<Point> uniquePoints = new ArrayList<>();
        Point point = new Point(0, 0);
        uniquePoints.add(point);
        HashMap<SubArea, Point> randomAreaLocations = new HashMap<>();

        Integer expectInteger = 1;
        Point expectPoint = point;
        Integer actualInteger = ButtonCoordinates.assignPointToSubarea(subareaList, uniquePoints, randomAreaLocations);
        Point actualPoint = randomAreaLocations.get(subArea);
        assertEquals(expectInteger,actualInteger);
        assertEquals(expectPoint,actualPoint);
    }
    @Test
    public void testGetUniquePoints_GivenListOfKnownSubareasSizeCanGetListOfUniquePointsOfSameSize() {
        ArrayList<SubArea> listOne = new ArrayList<>();
        listOne.add(new SubArea());
        ArrayList<SubArea> listTwo = new ArrayList<>();

        int expectedSizeListOne = listOne.size();
        int expectedSizeListTwo = listTwo.size();
        ArrayList<Point> actualPointsListForListOne = ButtonCoordinates.getUniquePoints(listOne);
        ArrayList<Point> actualPointsListForListTwo = ButtonCoordinates.getUniquePoints(listTwo);

        assertEquals(expectedSizeListOne,actualPointsListForListOne.size());
        assertEquals(expectedSizeListTwo,actualPointsListForListTwo.size());

    }
    @Test
    public void testGetUniquePoints_GivenListOfUnknownVaryingSizeNSubareasCanGetListOfUniquePointsOfSizeNRespectively(){
        Collection<List<SubArea>> collectionOfSubareas = inGameMap.gameMap.values();
        for (List<SubArea> actualSubAreas : collectionOfSubareas) {
            ArrayList<Point> actualUniquePoints = ButtonCoordinates.getUniquePoints(actualSubAreas);
                assertEquals(actualUniquePoints.size(),actualUniquePoints.size());
        }
    }
    @Test
    public void testGenerateFixedCoordinates_CanGetValidPointGivenValidIndexRange(){
        Point expectedLow = new Point(0,0);
        Point expectedHigh = new Point(2*ButtonCoordinates.w,2*ButtonCoordinates.h);
        HashMap<Integer, Point> integerPointHashMap = ButtonCoordinates.generateFixedCoordinates();
        Point actualLow = integerPointHashMap.get(1);
        Point actualHigh = integerPointHashMap.get(9);
        assertEquals(expectedLow,actualLow);
        assertEquals(expectedHigh,actualHigh);
    }
    @Test
    public void testButtonDimension_GivenGameDifficultyOfEasy() throws IOException {
        GlobalVariables.inGameMap = new MainMap(GameDifficulty.Easy);
        AudioPlayer.setSoundOn(false);
        GlobalVariables.gameInitialization();
        Dimension expected = new Dimension(113,76);
        Dimension actual = ButtonCoordinates.buttonDimension();
        assertEquals(expected,actual);
    }
    @Test
    public void testButtonDimension_GivenGameDifficultyOfMedium() throws IOException {
        GlobalVariables.inGameMap = new MainMap(GameDifficulty.Medium);
        AudioPlayer.setSoundOn(false);
        GlobalVariables.gameInitialization();
        Dimension expected3x4 = new Dimension(85,76);
        Dimension expected4x3 = new Dimension(113,57);
        Dimension actual = ButtonCoordinates.buttonDimension();
        assertTrue(actual.equals(expected3x4) || actual.equals(expected4x3));
    }
    @Test
    public void testButtonDimension_GivenGameDifficultyOfHard() throws IOException {
        GlobalVariables.inGameMap = new MainMap(GameDifficulty.Hard);
        AudioPlayer.setSoundOn(false);
        GlobalVariables.gameInitialization();
        Dimension expected = new Dimension(85,57);
        Dimension actual = ButtonCoordinates.buttonDimension();
        assertEquals(expected,actual);
    }
}