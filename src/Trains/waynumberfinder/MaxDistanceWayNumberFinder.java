package Trains.waynumberfinder;

import Trains.Route;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaolei on 2017/2/24.
 */
public class MaxDistanceWayNumberFinder extends WayNumberFinder {


    public MaxDistanceWayNumberFinder(Map<String, List<Route>> map) {
        super(map);
    }

    @Override
    protected boolean isRightWay(String nowWay, int nowLength, Route nowRoute) {
        return nowRoute.getToTownName().equals(to) && nowLength > 0;
    }

    @Override
    protected boolean isOnLimited(String nowWay, int nowLength, Route nowRoute) {
        return nowLength < limited;
    }
}
