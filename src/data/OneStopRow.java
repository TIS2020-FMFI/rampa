package data;

import javafx.scene.control.CheckBox;

import java.io.Serializable;

public class OneStopRow implements Serializable {
    private String cofor;
    private String name;
    private Integer distance;
    private Integer speed;
    private Integer loadingTime;
    private String stopType;
    private String loading;
    private String ramp;
    private transient CheckBox beginOfST;
    private boolean beginOfSTChecked;
    private String time;
    private Integer freqCofor;

    public OneStopRow(String cofor, String name, Integer distance, Integer speed,
                      Integer loadingTime, String stopType, String loading,
                      String ramp, CheckBox beginOfST, String time, Integer freqCofor) {
        this.cofor = cofor;
        this.name = name;

        this.distance = distance;
        this.speed = speed;
        this.loadingTime = loadingTime;

        this.stopType = stopType;
        this.loading = loading;
        this.ramp = ramp;
        this.beginOfST = beginOfST;
        beginOfSTChecked = beginOfST.isSelected();

        this.time = time;
        this.freqCofor = freqCofor;
    }


    public String getCofor() {
        return cofor;
    }

    public void setCofor(String cofor) {
        this.cofor = cofor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(Integer loadingTime) {
        this.loadingTime = loadingTime;
    }

    public String getStopType() {
        return stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

    public String getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading = loading;
    }

    public String getRamp() {
        return ramp;
    }

    public void setRamp(String ramp) {
        this.ramp = ramp;
    }

    public CheckBox getBeginOfST() {
        return beginOfST;
    }

    public void setBeginOfST(CheckBox beginOfST) {
        this.beginOfST = beginOfST;
        this.beginOfSTChecked = beginOfST.isSelected();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getFreqCofor() {
        return freqCofor;
    }

    public void setFreqCofor(Integer freqCofor) {
        this.freqCofor = freqCofor;
    }
}
