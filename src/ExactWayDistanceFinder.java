import java.util.List;
import java.util.Map;

/**
 * Created by xiaolei on 2017/2/23.
 */
public class ExactWayDistanceFinder {
    public static int NO_WAY_FIND = -1;
    private Map<String, List<Route>> map;

    public int getLength(Map<String, List<Route>> routeMaps, String... routeToFind) {
        map = routeMaps;
        if (routeMaps == null)
            return NO_WAY_FIND;
        List<Route> routes;
        int distance = 0;

        for (int i = 0; i < routeToFind.length - 1; i++) {
            routes = getRoutes(routeToFind[i]);
            int wayLengh = getDistance(routes, i, routeToFind);
            if (wayLengh != NO_WAY_FIND) {
                distance += wayLengh;
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
