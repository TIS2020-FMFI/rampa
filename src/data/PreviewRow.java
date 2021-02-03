package data;

public class PreviewRow {

    private String stopName;
    private String place;
    private String distance;
    private String speed;
    private String arrivalTime;
    private String departureTime;
    private String day;
    private String newDay;
    private String newArrivalTime;


    public PreviewRow(String stopName, String place, String distance, String speed, String arrivalTime,
                      String departureTime, String day, String newDay, String newArrivalTime) {
        this.stopName = stopName;
        this.place = place;
        this.distance = distance;
        this.speed = speed;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.day = day;
        this.newDay = newDay;
        this.newArrivalTime = newArrivalTime;
    }


    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNewDay() {
        return newDay;
    }

    public void setNewDay(String newDay) {
        this.newDay = newDay;
    }

    public String getNewArrivalTime() {
        return newArrivalTime;
    }

    public void setNewArrivalTime(String newArrivalTime) {
        this.newArrivalTime = newArrivalTime;
    }

}
