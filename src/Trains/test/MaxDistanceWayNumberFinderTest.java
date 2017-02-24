package Trains.test;

import Trains.Route;
import Trains.waynumberfinder.MaxDistanceWayNumberFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaolei on 2017/2/24.
 */
public class MaxDistanceWayNumberFinderTest {
    private MaxDistanceWayNumberFinder finder;
    private Map<String, List<Route>> routeMap;
    private ArrayList<Route> ARoutes;
    private ArrayList<Route> BRoutes;

    @Before
    public void init() {
        routeMap = new HashMap<String, List<Route>>();
        ARoutes = new ArrayList<Route>();
        BRoutes = new ArrayList<Route>();
    }

    @Test
    public void test_findWayNumber_can_return() {
        finder = new MaxDistanceWayNumberFinder(null);
        int wayNumber = finder.findWayNumber("A", "B", 0);
        assertNotNull(wayNumber);
    }

    @Test
    public void test_findWayNumber_can_find_only_one_route_within_limited() {
        ARoutes.add(new Route("B", 5));
        BRoutes.add(new Route("C", 5));
        routeMap.put("A", ARoutes);
        routeMap.put("B", BRoutes);
        finder = new MaxDistanceWayNumberFinder(routeMap);
        int wayNumber = finder.findWayNumber("A", "C", 10);
        assertEquals(1, wayNumber);
    }

    @Test
    public void test_findWayNumber_can_find_only_one_route_without_limited() {
        ARoutes.add(new Route("B", 5));
        BRoutes.add(new Route("C", 5));
        routeMap.put("A", ARoutes);
        routeMap.put("B", BRoutes);
        finder = new MaxDistanceWayNumberFinder(routeMap);
        int wayNumber = finder.findWayNumber("A", "C", 10);
        assertEquals(1, wayNumber);
    }

    @Test
    public void test_findWayNumber_can_find_one_in_one_out() {
        ARoutes.add(new Route("B", 5));
        ARoutes.add(new Route("C", 5));
        BRoutes.add(new Route("C", 5));
        routeMap.put("A", ARoutes);
        routeMap.put("B", BRoutes);
        finder = new MaxDistanceWayNumberFinder(routeMap);
        int wayNumber = finder.findWayNumber("A", "C", 5);
        assertEquals(1, wayNumber);
    }

    @Test
    public void test_findWayNumber_can_find_two_in_one_out() {
        ARoutes.add(new Route("B", 5));
        ARoutes.add(new Route("C", 5));
        BRoutes.add(new Route("A", 5));
        BRoutes.add(new Route("C", 5));
        routeMap.put("A", ARoutes);
        routeMap.put("B", BRoutes);
        finder = new MaxDistanceWayNumberFinder(routeMap);
        int wayNumber = finder.findWayNumber("A", "C", 10);
        assertEquals(2, wayNumber);
    }
}
