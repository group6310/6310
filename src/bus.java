public class bus {
    private int busID;
    private int route;
    private int busLocationIndex;
    private int busLocation;
    private int busNextLocation;
    private int nextLocationTime;
    private int initialPassengers;
    private int passengerCapacity;
    private int currentPassenger;
    private int initialFuel;
    private int fuelCapacity;
    private int currentFuel;
    private int speed;
    private route busRoute;
    private int currentTime;
    private boolean startOrNot;

    public bus(int ID, int route, int location, int iniPass, int passCap, int iniFuel, int fuelCap, int speed) {
        this.busID = ID;
        this.route = route;
        this.busLocationIndex = location;
        this.initialPassengers = iniPass;
        this.passengerCapacity = passCap;
        this.initialFuel = iniFuel;
        this.fuelCapacity = fuelCap;
        this.speed = speed;
        this.busRoute = this.getRouteByID(this.route);
        this.busLocation = this.busRoute.currStop(this.busLocationIndex);
        this.busNextLocation = this.busRoute.nextStop(this.busLocationIndex);
        this.currentTime = 0;
        this.currentPassenger = 0;
        //this.updateNextLocationTime();
    }

    public void setBusStartTime(int startTime) {
        this.currentTime = startTime;
        this.updateNextLocationTime();
    }

    public void moveBus() {
            this.currentTime = this.nextLocationTime;
            updateLocation();
            updateNextLocationTime();
            updatePassengers();
            updateFuel();
    }

    public int getBusNextLocation() {
        return this.busNextLocation;
    }

    public int getNextLocationTime() {
        return this.nextLocationTime;
    }

    private route getRouteByID(int routeID) {
        return working_system.routeList.get(routeID);
    }

    private void updateLocation() {
        int totalRouteStops = busRoute.totalStops();
        if (this.busLocationIndex < totalRouteStops - 1) {
            this.busLocationIndex++;
        } else {
            this.busLocationIndex = 0;
        }
        this.busLocation = this.busRoute.currStop(this.busLocationIndex);
        this.busNextLocation = this.busRoute.nextStop(this.busLocationIndex);

    }

    private void updateNextLocationTime() {
        stop currStop = working_system.stopList.get(busLocation);
        stop nextStop = working_system.stopList.get(busNextLocation);
        Double distance =  currStop.distanceTo(nextStop);
        int travel_time = 1 + (distance.intValue() * 60 / this.getSpeed() );
        this.nextLocationTime = this.currentTime + travel_time;
    }

    private void updatePassengers() {
        this.currentPassenger  = 0;
    }

    private void updateFuel() {
        this.currentFuel = 0;
    }

    public int getSpeed() {
        return this.speed;
    }

}
