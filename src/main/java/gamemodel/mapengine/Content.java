package gamemodel.mapengine;

import gamecontrol.contents.Enemy;
import gamecontrol.contents.Event;
import gamecontrol.contents.Item;

import java.util.ArrayList;
import java.util.List;


public class Content {
    public List<Item> items = new ArrayList<>();
    public List<Event> events = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();
}
