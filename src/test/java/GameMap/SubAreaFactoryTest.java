package GameMap;

import org.junit.Test;

import java.io.IOException;

import static GameMap.SubAreaFactory.subAreaHashMap;

public class SubAreaFactoryTest {

    @Test
    public void createSubAreaTest() throws IOException {
        var item = subAreaHashMap();
        System.out.println(item.size());
    }
}