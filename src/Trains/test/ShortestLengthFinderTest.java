package Trains.test;

import Trains.Route;
import Trains.ShortestLengthFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xiaolei on 2017/2/23.
 */
public class ShortestLengthFinderTest {
    private ShortestLengthFinder finder;
    private HashMap<String, List<Route>> routeMap;
    private ArrayList<Route> ARoutes;
    private ArrayList<Route> BRoutes;

    @Before
    public void init() {
        routeMap = new HashMap<String, List<Route>>();
        ARoutes = new ArrayList<Route>();
        BRoutes = new ArrayList<Route>();
    }

    @Test
    public void test_findShortestWay_can_return() throws Exception {
        finder = new ShortestLengthFinder(null);
        int length = finder.findShortestWay("A", "C");
        assertNotNull(length);
    }

    @Test
    public void test_findShortestWay_can_return_when_no_way() throws Exception {
        finder = new ShortestLengthFinder(null);
        int length = finder.findShortestWay("A", "C");
        assertEquals(ShortestLengthFinder.NO_WAY_FIND, length);
    }

    @Test
    public void test_findShortestWay_can_return_only_two_node() throws Exception {
        ARoutes.add(new Route("B", 2));
        routeMap.put("A", ARoutes);
        finder = new ShortestLengthFinder(routeMap);
        int length = finder.findShortestWay("A", "B");
        assertEquals(2, length);
    }

    @Test
    public void test_findShortestWay_can_return_when_need_add_new_route() throws Exception {
        ARoutes.add(new Route("B", 2));
        ARoutes.add(new Route("C", 3));
        BRoutes.add(new Route("C", 3));
        BRoutes.add(new Route("D", 3));
        routeMap.put("A", ARoutes);
        routeMap.put("B", BRoutes);
        routeMap.put("C", null);
        routeMap.put("D", null);
        finder = new ShortestLengthFinder(routeMap);
        int length = finder.findShortestWay("A", "D");
        assertEquals(5, length);
    }
}