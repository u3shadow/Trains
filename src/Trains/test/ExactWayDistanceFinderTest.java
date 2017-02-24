package Trains.test;

import Trains.ExactWayDistanceFinder;
import Trains.Route;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xiaolei on 2017/2/23.
 */
public class ExactWayDistanceFinderTest {
    private ExactWayDistanceFinder finder;
    private HashMap<String, List<Route>> routeMap;
    private ArrayList<Route> ARoutes;
    private ArrayList<Route> BRoutes;

    @Before
    public void init() {
        finder = new ExactWayDistanceFinder();
        routeMap = new HashMap<String, List<Route>>();
        ARoutes = new ArrayList<Route>();
        BRoutes = new ArrayList<Route>();
    }

    @Test
    public void test_getLength_can_return() {
        int length = finder.getLength(new HashMap<String, List<Route>>(), "A", "B", "C");
        assertNotNull(length);
    }

    @Test
    public void test_getLength_when_no_this_way() {
        int length = finder.getLength(new HashMap<String, List<Route>>(), "A", "B", "C");
        assertEquals(ExactWayDistanceFinder.NO_WAY_FIND, length);
    }

    @Test
    public void test_getLength_can_find_two_node() {
        ARoutes.add(new Route("B", 2));
        routeMap.put("A", ARoutes);
        int length = finder.getLength(routeMap, "A", "B");
        assertEquals(2, length);
    }

    @Test
    public void test_getLength_can_find_two_more_node() {

        ARoutes.add(new Route("B", 2));
        BRoutes.add(new Route("C", 5));
        routeMap.put("A", ARoutes);
        routeMap.put("B", BRoutes);
        int length = finder.getLength(routeMap, "A", "B", "C");
        assertEquals(7, length);
    }

    @Test
    public void test_getLength_pratly_find_two_more_node() {
        ARoutes.add(new Route("B", 2));
        BRoutes.add(new Route("C", 5));
        routeMap.put("A", ARoutes);
        routeMap.put("B", BRoutes);
        int length = finder.getLength(routeMap, "A", "B", "C", "D");
        assertEquals(ExactWayDistanceFinder.NO_WAY_FIND, length);
    }

    @Test
    public void test_getLength_prat_find_two_more_node_in() {
        ARoutes.add(new Route("B", 2));
        BRoutes.add(new Route("C", 5));
        routeMap.put("A", ARoutes);
        routeMap.put("B", BRoutes);
        int length = finder.getLength(routeMap, "A", "B","D","C");
        assertEquals(ExactWayDistanceFinder.NO_WAY_FIND, length);
    }
}