package data;

import java.io.Serializable;

public class TechnickeUdaje implements Serializable {
    private String appDateLb;
    private String appDateInput;
    private String flowLb;
    private String flowInput;
    private String heightGoodsLb;
    private String heightGoodsInput;
    private String heightTruckLb;
    private String heightTruckInput;
    private String length1Lb;
    private String length1Input;
    private String length2Lb;
    private String length2Input;
    private String exportLb;
    private Integer exportInput;
    private String importLb;
    private Integer importInput;

    public TechnickeUdaje(String appDateLb, String appDateInput, String flowLb, String flowInput, String heightGoodsLb,
                          String heightGoodsInput, String heightTruckLb, String heightTruckInput, String length1Lb,
                          String length1Input, String length2Lb, String length2Input, String exportLb, Integer exportInput,
                          String importLb, Integer importInput) {
        this.appDateLb = appDateLb;
        this.appDateInput = appDateInput;
        this.flowLb = flowLb;
        this.flowInput = flowInput;
        this.heightGoodsLb = heightGoodsLb;
        this.heightGoodsInput = heightGoodsInput;
        this.heightTruckLb = heightTruckLb;
        this.heightTruckInput = heightTruckInput;
        this.length1Lb = length1Lb;
        this.length1Input = length1Input;
        this.length2Lb = length2Lb;
        this.length2Input = length2Input;
        this.exportLb = exportLb;
        this.exportInput = exportInput;
        this.importLb = importLb;
        this.importInput = importInput;
    }

    public String getAppDateLb() {
        return appDateLb;
    }

    public void setAppDateLb(String appDateLb) {
        this.appDateLb = appDateLb;
    }

    public String getAppDateInput() {
        return appDateInput;
    }

    public void setAppDateInput(String appDateInput) {
        this.appDateInput = appDateInput;
    }

    public String getFlowLb() {
        return flowLb;
    }

    public void setFlowLb(String flowLb) {
        this.flowLb = flowLb;
    }

    public String getFlowInput() {
        return flowInput;
    }

    public void setFlowInput(String flowInput) {
        this.flowInput = flowInput;
    }

    public String getHeightGoodsLb() {
        return heightGoodsLb;
    }

    public void setHeightGoodsLb(String heightGoodsLb) {
        this.heightGoodsLb = heightGoodsLb;
    }

    public String getHeightGoodsInput() {
        return heightGoodsInput;
    }

    public void setHeightGoodsInput(String heightGoodsInput) {
        this.heightGoodsInput = heightGoodsInput;
    }

    public String getHeightTruckLb() {
        return heightTruckLb;
    }

    public void setHeightTruckLb(String heightTruckLb) {
        this.heightTruckLb = heightTruckLb;
    }

    public String getHeightTruckInput() {
        return heightTruckInput;
    }

    public void setHeightTruckInput(String heightTruckInput) {
        this.heightTruckInput = heightTruckInput;
    }

    public String getLength1Lb() {
        return length1Lb;
    }

    public void setLength1Lb(String length1Lb) {
        this.length1Lb = length1Lb;
    }

    public String getLength1Input() {
        return length1Input;
    }

    public void setLength1Input(String length1Input) {
        this.length1Input = length1Input;
    }

    public String getLength2Lb() {
        return length2Lb;
    }

    public void setLength2Lb(String length2Lb) {
        this.length2Lb = length2Lb;
    }

    public String getLength2Input() {
        return length2Input;
    }

    public void setLength2Input(String length2Input) {
        this.length2Input = length2Input;
    }

    public String getExportLb() {
        return exportLb;
    }

    public void setExportLb(String exportLb) {
        this.exportLb = exportLb;
    }

    public Integer getExportInput() {
        return exportInput;
    }

    public void setExportInput(Integer exportInput) {
        this.exportInput = exportInput;
    }

    public String getImportLb() {
        return importLb;
    }

    public void setImportLb(String importLb) {
        this.importLb = importLb;
    }

    public Integer getImportInput() {
        return importInput;
    }

    public void setImportInput(Integer importInput) {
        this.importInput = importInput;
    }
}
