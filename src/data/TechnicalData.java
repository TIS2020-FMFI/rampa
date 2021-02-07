package data;

import java.io.Serializable;

public class TechnicalData implements Serializable {
    private String appDate;
    private String flow;
    private String heightGoods;
    private String heightTruck;
    private String length1;
    private String length2;
    private Integer export;
    private Integer importValue;

    public TechnicalData(String appDate, String flow, String heightGoods,
                         String heightTruck, String length1, String length2,
                         Integer export, Integer importValue) {
        this.appDate = appDate;
        this.flow = flow;
        this.heightGoods = heightGoods;
        this.heightTruck = heightTruck;
        this.length1 = length1;
        this.length2 = length2;
        this.export = export;
        this.importValue = importValue;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getHeightGoods() {
        return heightGoods;
    }

    public void setHeightGoods(String heightGoods) {
        this.heightGoods = heightGoods;
    }

    public String getHeightTruck() {
        return heightTruck;
    }

    public void setHeightTruck(String heightTruck) {
        this.heightTruck = heightTruck;
    }

    public String getLength1() {
        return length1;
    }

    public void setLength1(String length1) {
        this.length1 = length1;
    }

    public String getLength2() {
        return length2;
    }

    public void setLength2(String length2) {
        this.length2 = length2;
    }

    public Integer getExport() {
        return export;
    }

    public void setExport(Integer export) {
        this.export = export;
    }

    public Integer getImportValue() {
        return importValue;
    }

    public void setImportValue(Integer importValue) {
        this.importValue = importValue;
    }
}
