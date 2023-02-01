package GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static Client.GlobalVariables.*;

public class Main_Map {

    private static final int DEFAULT_X = 3;
    private static final int DEFAULT_Y = 3;

    //public static ArrayList<SubArea>[] Game_Map;

    public HashMap<Integer, ArrayList<SubArea>> Game_Maps = new HashMap<>();

    private int position = 1;

    private final int dimensionX;
    private final int dimensionY;

//    public void setGame_Map(ArrayList<SubArea>[] game_Map) {
//        Game_Map = game_Map;
//    }

    public int getPosition() {
        return position;
    }

//    public void setPosition(int position) {
//        this.position = position;
//    }

    public Main_Map() {
        this.dimensionY = DEFAULT_Y;
        this.dimensionX = DEFAULT_X;
    }

    public Main_Map(int x, int y) {
        if(x< DEFAULT_X) {
            x = DEFAULT_X;
        }
        if(y<DEFAULT_Y) {
            y = DEFAULT_Y;
        }

        this.dimensionY = y;
        this.dimensionX = x;
    }

    public void initial_map() {
        //Game_Map = new ArrayList[this.dimensionX * this.dimensionY];

        Random rg = new Random();

        for(int i=1; i<= this.dimensionX*this.dimensionY; i++) {
            int subAreaNumbers = rg.nextInt(3) +1;

            ArrayList<SubArea> mapBlock = new ArrayList<>();

            for (int j = 0; j< subAreaNumbers; j++) {
                SubArea subArea = new SubArea();
                subArea.initialization(subAreaNameList, enemies);

                mapBlock.add(subArea);
            }
            Game_Maps.put(i, mapBlock);
        }
    }

    public void go_North() {
        if (this.position < this.dimensionX) {
            System.out.println("Can't across the border of this place");
        } else {
            System.out.println("You are going north");
            position -= this.dimensionX;
        }
    }

    public void go_South() {
        if (this.position > this.dimensionX * (this.dimensionY -1) -1) {
            System.out.println("Can't across the border of this place");
        } else {
            System.out.println("You are going south");
            position +=this.dimensionX;
        }
    }

    public void go_East() {
        if ((this.position+1)%dimensionX == 0) {
            System.out.println("Can't across the border of this place");
        } else {
            System.out.println("You are going west");
            position +=1;
        }
    }

    public void go_West() {
        if ((this.position)%dimensionX == 0) {
            System.out.println("Can't across the border of this place");
        } else {
            System.out.println("You are going east");
            position -=1;
        }
    }
}
