/**
 * Created by xiaolei on 2017/2/23.
 */
public class Route {
    private String toTownName;
    private int wayLength;
    public Route(String toTownName,int wayLength){
        this.toTownName = toTownName;
        this.wayLength = wayLength;
    }
    public String getToTownName() {
        return toTownName;
    }
    public int getWayLength() {
        return wayLength;
    }

}
