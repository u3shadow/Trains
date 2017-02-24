package Trains;

import jdk.internal.util.xml.impl.Input;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xiaolei on 2017/2/24.
 */
public class TrainsTest {
    private Trains trains;

    @Test
    public void test_getInputMap_can_return() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("AB2, BC3".getBytes());
        trains = new Trains(inputStream);
        Map<String, List<Route>> map = trains.getInputMap();
        assertNotNull(map);
    }

    @Test
    public void test_getInputMap_can_return_two_node_one_way() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("AB2".getBytes());
        trains = new Trains(inputStream);
        Map<String, List<Route>> map = trains.getInputMap();
        List<Route> routes = map.get("A");
        assertEquals(1, routes.size());
    }

    @Test
    public void test_getInputMap_more_node_and_route() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7".getBytes());
        trains = new Trains(inputStream);
        Map<String, List<Route>> map = trains.getInputMap();
        List<Route> routes = map.get("A");
        assertEquals(3, routes.size());
    }

}