import java.util.*;

/**
 * Created by xiaolei on 2017/2/23.
 */
public class ShortestLengthFinder {
    private Map<String, List<Route>> map = null;
    public static int NO_WAY_FIND = -1;

    public ShortestLengthFinder(Map<String, List<Route>> map) throws CloneNotSupportedException {
        if (map != null)
            this.map = getNewMap(map);
    }

    private Map<String, List<Route>> getNewMap(Map<String, List<Route>> map) throws CloneNotSupportedException {
        Map<String, List<Route>> newMap = new HashMap<String, List<Route>>();
        for (String key : map.keySet()) {
            List<Route> newRoutes = new ArrayList<>();
            List<Route> routes = map.get(key);
            if (routes != null)
                for (Route route : routes) {
                    Route newRoute = (Route) route.clone();
                    newRoutes.add(newRoute);
                }
            newMap.put(key, newRoutes);
        }
        return newMap;
    }

    public int findShortestWay(String fromTown, String toTown) {
        if (map == null)
            return NO_WAY_FIND;
        updateLength();
        List<Route> routeFrom = map.get(fromTown);
        int length = NO_WAY_FIND;
        for (Route route : routeFrom) {
            if (route.getToTownName().equals(toTown)) {
                length = route.getWayLength();
                break;
            }
        }
        return length;
    }

    private void updateLength() {
        Set<String> towns = map.keySet();
        List<Route> routesMid, routesFirst;
        int temp;
        for (String midTown : towns) {
            routesMid = map.get(midTown);
            if (routesMid != null)
                for (String firstTown : towns) {
                    routesFirst = map.get(firstTown);
                    if (routesFirst != null)
                        for (String secendTown : towns) {
                            temp = getTemp(routesMid, routesFirst, midTown, secendTown);
                            if (temp != NO_WAY_FIND)
                                updateShortestLength(routesFirst, temp, secendTown);
                        }
                }
        }
    }

    private void updateShortestLength(List<Route> routesFirst, int temp, String secendTown) {
        boolean isUpdated = false;
        for (Route froute : routesFirst) {
            if (froute.getToTownName().equals(secendTown)) {
                if (temp < froute.getWayLength()) {
                    froute.setWayLength(temp);
                    isUpdated = true;
                }
            }
        }
        if (!isUpdated) {
            routesFirst.add(new Route(secendTown, temp));
        }
    }

    private int getTemp(List<Route> routesMid, List<Route> routesFirst, String midTown, String secendTown) {
        int temp = NO_WAY_FIND;
        for (Route froute : routesFirst) {
            temp = updateTemp(routesMid, midTown, secendTown, froute);
            if (temp != NO_WAY_FIND)
                break;
        }
        return temp;
    }

    private int updateTemp(List<Route> routesMid, String midTown, String secendTown, Route froute) {
        int temp = NO_WAY_FIND;
        if (froute.getToTownName().equals(midTown)) {
            for (Route mroute : routesMid) {
                if (mroute.getToTownName().equals(secendTown)) {
                    temp = froute.getWayLength() + mroute.getWayLength();
                    break;
                }
            }
        }
        return temp;
    }
}
