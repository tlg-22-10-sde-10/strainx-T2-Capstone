package jsonparsing;

import Contents.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class jsonTest {

    private final String sampleJsonSource = "{ \"title\" : \"parsed from json as node\" }";

    @Test
    public void jsonFileReader() throws IOException {

//        String s = json.jsonFileReader();
//        System.out.println(s);
//        assertEquals("this is my item", s);
    }

//    @Test
//    public void parse() throws JsonProcessingException {
//        JsonNode node = json.parse(sampleJsonSource);
//
//        assertEquals("parsed from json as node", node.get("title").asText());
//    }
//
//    @Test
//    public void fromJson() throws JsonProcessingException {
//
//        JsonNode node = json.parse(sampleJsonSource);
//
//        Item item = json.fromJson(node, Item.class);
//
//
//    }


}