package Trains;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaolei on 2017/2/23.
 */
public class ExactWayDistanceFinder {
    public static int NO_WAY_FIND = -1;
    private Map<String, List<Route>> map;

    public ExactWayDistanceFinder(Map<String, List<Route>> routeMaps) {
        map = routeMaps;
    }

    public int getLength(String... routeToFind) {
        if (map == null)
            return NO_WAY_FIND;
        List<Route> routes;
        int distance = 0;

        for (int i = 0; i < routeToFind.length - 1; i++) {
            routes = getRoutes(routeToFind[i]);
            int wayLength = getDistance(routes, i, routeToFind);
            if (wayLength != NO_WAY_FIND) {
                distance += wayLength;
            } else {
                distance = NO_WAY_FIND;
                break;
            }
        }
        return distance;
    }

    private int getDistance(List<Route> routes, int i, String[] routeToFind) {
        int distance = NO_WAY_FIND;
        if (routes != null)
            for (Route route : routes) {
                if (route.getToTownName().equals(routeToFind[i + 1])) {
                    distance = route.getWayLength();
                    break;
                }
            }
        return distance;
    }

    private List<Route> getRoutes(String town) {
        return map.get(town);
    }
}
