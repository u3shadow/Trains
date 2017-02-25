package Trains;

import Trains.waynumberfinder.ExactStopWayNumberFinder;
import Trains.waynumberfinder.MaxDistanceWayNumberFinder;
import Trains.waynumberfinder.MaxStopWayNumberFinder;
import Trains.waynumberfinder.WayNumberFinder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by xiaolei on 2017/2/24.
 */
public class Trains {
    private InputStream inputStream = null;

    public static void main(String[] args) {
        InputStream inputStream = new ByteArrayInputStream("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7".getBytes());
        Trains trains = new Trains(inputStream);
        Map<String, List<Route>> map = trains.getInputMap();
        ExactWayDistanceFinder exactWayDistanceFinder = new ExactWayDistanceFinder(map);
        WayNumberFinder maxStopFinder = new MaxStopWayNumberFinder(map);
        WayNumberFinder maxLengthFinder = new MaxDistanceWayNumberFinder(map);
        WayNumberFinder exactStopFinder = new ExactStopWayNumberFinder(map);
        List<Integer> result = new ArrayList<>();
        result.add(exactWayDistanceFinder.getLength("A", "B", "C"));
        result.add(exactWayDistanceFinder.getLength("A", "D"));
        result.add(exactWayDistanceFinder.getLength("A", "D", "C"));
        result.add(exactWayDistanceFinder.getLength("A", "E", "B", "C", "D"));
        result.add(exactWayDistanceFinder.getLength("A", "E", "D"));
        result.add(maxStopFinder.findWayNumber("C", "C", 3));
        result.add(exactStopFinder.findWayNumber("A", "C", 4));
        try {
            ShortestLengthFinder shortestFinder = new ShortestLengthFinder(map);
            result.add(shortestFinder.findShortestWay("A", "C"));
            result.add(shortestFinder.findShortestWay("B", "B"));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        result.add(maxLengthFinder.findWayNumber("C", "C", 30));
        String resultString = "";
        for (int i = 0; i < result.size(); i++) {
            resultString = "OutPut#" + (i + 1) + ": ";
            if (result.get(i) <= 0) {
                resultString += "NO SUCH ROUTE";
            } else {
                resultString += result.get(i);
            }
            System.out.println(resultString);
        }
    }

    public Trains() {
    }

    public Trains(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Map<String, List<Route>> getInputMap() {
        Map<String, List<Route>> map = new HashMap<>();
        String input = getInputString();
        String[] routes = input.split(", ");
        for (String route : routes) {
            Route routeEntity = getRouteEntity(route);
            updateRouteMap(map, route, routeEntity);
        }
        return map;
    }

    private void updateRouteMap(Map<String, List<Route>> map, String route, Route routeEntity) {
        String starTown = route.substring(0, 1);
        if (map.containsKey(starTown)) {
            map.get(starTown).add(routeEntity);
        } else {
            List<Route> routeList = new ArrayList<>();
            routeList.add(routeEntity);
            map.put(starTown, routeList);
        }
    }

    private String getInputString() {
        Scanner scanner;
        if (inputStream != null) {
            scanner = new Scanner(inputStream);
        } else {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }

    private Route getRouteEntity(String route) {
        String toTown = route.substring(1, 2);
        int length = Integer.valueOf(route.substring(2));
        Route routeEntity = new Route(toTown, length);
        return routeEntity;
    }
}
