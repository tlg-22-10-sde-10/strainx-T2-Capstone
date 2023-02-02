package GameMap;

public class Main_Map {

    private static final int DEFAULT_X = 3;
    private static final int DEFAULT_Y = 3;

    private int position = 0;

    private int dimensionX = DEFAULT_X;
    private int dimensionY = DEFAULT_Y;

    public SubArea[] getGame_Map() {
        return Game_Map;
    }

    public void setGame_Map(SubArea[] game_Map) {
        Game_Map = game_Map;
    }

    private SubArea[] Game_Map;

    public Main_Map(int x, int y) {
        this.dimensionY = y;
        this.dimensionX = x;
    }

    public void initial_map() {
        Game_Map = new SubArea[this.dimensionX*this.dimensionY];
        for(int i=0; i< this.dimensionX*this.dimensionY; i++) {
            SubArea subArea = new SubArea();
            Game_Map[i] = subArea;
        }
    }

    public void go_North() {
        if (this.position < this.dimensionX) {
            System.out.println("Can't across the border of this place");
        } else {
            position -= this.dimensionX;
        }
    }

    public void go_South() {
        if (this.position > this.dimensionX * (this.dimensionY -1) -1) {
            System.out.println("Can't across the border of this place");
        } else {
            position +=this.dimensionX;
        }
    }

    public void go_West() {
        if ((this.position+1)%dimensionX == 0) {
            System.out.println("Can't across the border of this place");
        } else {
            position +=1;
        }
    }

    public void go_East() {
        if ((this.position)%dimensionX == 0) {
            System.out.println("Can't across the border of this place");
        } else {
            position -=1;
        }
    }
}
