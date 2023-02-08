package gamemapengine;

import org.junit.Test;

import static gamemapengine.SubAreaFactory.subAreaHashMap;

public class SubAreaFactoryTest {

    @Test
    public void createSubAreaTest() {
        var item = subAreaHashMap();
        System.out.println(item.size());
    }
}