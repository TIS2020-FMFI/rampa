package data;

import java.io.Serializable;

public class ZadanieVstupovHeaderData implements Serializable {
    private String start;
    private String miestoStartu;
    private String cas;
    private Integer kodRT;
    private Integer kodST;
    private String urcitPodla; // ulozi tam bud string start, dodavatel alebo stop
    private String urcitPodlaDodavatel; // ak urcitPodla je dodavatel, tak tu bude mat ulozeneho dodavatela, inak to bude null

    public ZadanieVstupovHeaderData(String start, String miestoStartu, String cas, Integer kodRT, Integer kodST, String urcitPodla, String urcitPodlaDodavatel) {
        this.start = start;
        this.miestoStartu = miestoStartu;
        this.cas = cas;
        this.kodRT = kodRT;
        this.kodST = kodST;
        this.urcitPodla = urcitPodla;
        this.urcitPodlaDodavatel = urcitPodlaDodavatel;
    }



    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getMiestoStartu() {
        return miestoStartu;
    }

    public void setMiestoStartu(String miestoStartu) {
        this.miestoStartu = miestoStartu;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public Integer getKodRT() {
        return kodRT;
    }

    public void setKodRT(Integer kodRT) {
        this.kodRT = kodRT;
    }

    public Integer getKodST() {
        return kodST;
    }

    public void setKodST(Integer kodST) {
        this.kodST = kodST;
    }

    public String getUrcitPodla() {
        return urcitPodla;
    }

    public void setUrcitPodla(String urcitPodla) {
        this.urcitPodla = urcitPodla;
    }

    public String getUrcitPodlaDodavatel() {
        return urcitPodlaDodavatel;
    }

    public void setUrcitPodlaDodavatel(String urcitPodlaDodavatel) {
        this.urcitPodlaDodavatel = urcitPodlaDodavatel;
    }

}
