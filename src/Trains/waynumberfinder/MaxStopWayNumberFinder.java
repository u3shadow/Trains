package Trains.waynumberfinder;

import Trains.Route;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaolei on 2017/2/24.
 */
public class MaxStopWayNumberFinder extends WayNumberFinder {


    public MaxStopWayNumberFinder(Map<String, List<Route>> map) {
        super(map);
    }

    @Override
    protected boolean isRightWay(String nowWay, int nowLength, Route nowRoute) {
        return nowRoute.getToTownName().equals(to);
    }

    @Override
    protected boolean isOnLimited(String nowWay, int nowLength, Route nowRoute) {
        return nowWay.split(",").length <= limited + 1;
    }
}
