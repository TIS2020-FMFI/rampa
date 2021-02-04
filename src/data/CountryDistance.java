package data;

import java.io.Serializable;

public class CountryDistance implements Serializable {
    private String state;
    private String distance;

    public CountryDistance(String state, String distance) {
        this.state = state;
        this.distance = distance;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }


}
