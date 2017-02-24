package Trains.waynumberfinder;

import Trains.Route;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaolei on 2017/2/24.
 */
public class ExactStopWayNumberFinder extends WayNumberFinder {
    public ExactStopWayNumberFinder(Map<String, List<Route>> map) {
        super(map);
    }

    @Override
    protected boolean isOnLimited(String nowWay, int nowLength, Route nowRoute) {
        return nowWay.split(",").length <= limited + 1;
    }

    @Override
    protected boolean isRightWay(String nowWay, int nowLength, Route nowRoute) {
        return nowWay.split(",").length == limited + 1 && nowRoute.getToTownName().equals(to);
    }
}
