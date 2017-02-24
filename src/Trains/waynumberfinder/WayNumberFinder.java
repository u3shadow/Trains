package Trains.waynumberfinder;

import Trains.Route;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xiaolei on 2017/2/24.
 */
public abstract class WayNumberFinder {
    protected Map<String, List<Route>> map;
    protected Set<String> resultSet;
    protected int limited;
    protected String from, to;
    public static int NO_WAY_FIND = -1;

    public WayNumberFinder(Map<String, List<Route>> map) {
        this.map = map;
    }

    public final int findWayNumber(String fromTown, String toTown, int limited) {
        from = fromTown;
        to = toTown;
        this.limited = limited;
        int wayNumber;
        wayNumber = getNumber();
        return wayNumber;
    }

    private int getNumber() {
        int result;
        if (map == null) {
            result = NO_WAY_FIND;
        } else {
            result = getWaySetSize();
            if (result == 0)
                result = NO_WAY_FIND;
        }
        return result;
    }

    private int getWaySetSize() {
        resultSet = new HashSet<>();
        Route initRout = new Route(from, 0);
        updateRouteSet(initRout, "", 0);
        return resultSet.size();
    }

    private void updateRouteSet(Route nowRoute, String nowWay, int nowLength) {
        nowWay += (nowRoute.getToTownName() + ",");
        nowLength += nowRoute.getWayLength();

        if (isOnLimited(nowWay, nowLength, nowRoute)) {
            if (isRightWay(nowWay, nowLength, nowRoute)) {
                resultSet.add(nowWay);
            }
            goNext(nowRoute, nowWay, nowLength);
        }
        return;
    }

    private void goNext(Route nowRoute, String nowWay, int nowLength) {
        List<Route> nextRoutes = map.get(nowRoute.getToTownName());
        if (nextRoutes != null)
            for (Route route : nextRoutes) {
                updateRouteSet(route, nowWay, nowLength);
            }
    }

    protected abstract boolean isRightWay(String nowWay, int nowLength, Route nowRoute);

    protected abstract boolean isOnLimited(String nowWay, int nowLength, Route nowRoute);
}
