public class stop {
    private int stopID;
    private String stopName;
    private int numRiders;
    private double stopLatitude;
    private double stopLongtitude;

    public stop(int ID, String stopName, int numRiders, double latitude, double longtitude) {
        this.stopID = ID;
        this.stopName = stopName;
        this.numRiders = numRiders;
        this.stopLatitude = latitude;
        this.stopLongtitude = longtitude;
    }

    public int updateRiders() {
        // !!! need to be modified!!!
        return numRiders;
    }

    public double distanceTo(stop thatStop) {
        double x1 = this.stopLatitude;
        double y1 = this.stopLongtitude;
        double x2 = thatStop.getStopLatitude();
        double y2 = thatStop.getStopLongtitude();
        double distance = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
        double trueDistance = 70.0 * distance;
        return trueDistance;
    }

    public double getStopLatitude() {
        return this.stopLatitude;
    }

    public double getStopLongtitude() {
        return this.stopLongtitude;
    }

}
