package gamemap;

import org.junit.Test;

import java.io.IOException;

import static gamemap.SubAreaFactory.subAreaHashMap;

public class SubAreaFactoryTest {

    @Test
    public void createSubAreaTest() {
        var item = subAreaHashMap();
        System.out.println(item.size());
    }
}