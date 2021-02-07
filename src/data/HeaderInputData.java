package data;

import java.io.Serializable;

public class HeaderInputData implements Serializable {
    private String start;
    private String placeOfStart;
    private String time;
    private Integer codeRT;
    private Integer codeST;
    private String determinedBy; // ulozi tam bud string start, dodavatel alebo stop
    private String determiningSupplier; // ak urcitPodla je dodavatel, tak tu bude mat ulozeneho dodavatela, inak to bude null

    public HeaderInputData(String start, String placeOfStart, String time, Integer codeRT, Integer codeST, String determinedBy, String determiningSupplier) {
        this.start = start;
        this.placeOfStart = placeOfStart;
        this.time = time;
        this.codeRT = codeRT;
        this.codeST = codeST;
        this.determinedBy = determinedBy;
        this.determiningSupplier = determiningSupplier;
    }



    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPlaceOfStart() {
        return placeOfStart;
    }

    public void setPlaceOfStart(String placeOfStart) {
        this.placeOfStart = placeOfStart;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCodeRT() {
        return codeRT;
    }

    public void setCodeRT(Integer codeRT) {
        this.codeRT = codeRT;
    }

    public Integer getCodeST() {
        return codeST;
    }

    public void setCodeST(Integer codeST) {
        this.codeST = codeST;
    }

    public String getDeterminedBy() {
        return determinedBy;
    }

    public void setDeterminedBy(String determinedBy) {
        this.determinedBy = determinedBy;
    }

    public String getDeterminingSupplier() {
        return determiningSupplier;
    }

    public void setDeterminingSupplier(String determiningSupplier) {
        this.determiningSupplier = determiningSupplier;
    }

}
