package data;

import javafx.scene.control.CheckBox;

import java.time.LocalTime;

public class ZastavkaRow {
    private String cofor;
    private String meno;
    private Integer vzdialenost;
    private Integer rychlost;
    private Integer dlzkaNakladky;
    private String typZastavky;
    private String loading;
    private String rampa;
    private CheckBox zaciatokST;
    private String cas;
    private Integer freqCofor;

    public ZastavkaRow(String cofor, String meno, Integer vzdialenost, Integer rychlost,
                       Integer dlzkaNakladky, String typZastavky, String loading,
                       String rampa, CheckBox zaciatokST, String cas, Integer freqCofor) {
        this.cofor = cofor;
        this.meno = meno;

        this.vzdialenost = vzdialenost;
        this.rychlost = rychlost;
        this.dlzkaNakladky = dlzkaNakladky;

        this.typZastavky = typZastavky;
        this.loading = loading;
        this.rampa = rampa;
        this.zaciatokST = zaciatokST;

        this.cas = cas;
        this.freqCofor = freqCofor;
    }


    public String getCofor() {
        return cofor;
    }

    public void setCofor(String cofor) {
        this.cofor = cofor;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public Integer getVzdialenost() {
        return vzdialenost;
    }

    public void setVzdialenost(Integer vzdialenost) {
        this.vzdialenost = vzdialenost;
    }

    public Integer getRychlost() {
        return rychlost;
    }

    public void setRychlost(Integer rychlost) {
        this.rychlost = rychlost;
    }

    public Integer getDlzkaNakladky() {
        return dlzkaNakladky;
    }

    public void setDlzkaNakladky(Integer dlzkaNakladky) {
        this.dlzkaNakladky = dlzkaNakladky;
    }

    public String getTypZastavky() {
        return typZastavky;
    }

    public void setTypZastavky(String typZastavky) {
        this.typZastavky = typZastavky;
    }

    public String getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading = loading;
    }

    public String getRampa() {
        return rampa;
    }

    public void setRampa(String rampa) {
        this.rampa = rampa;
    }

    public CheckBox getZaciatokST() {
        return zaciatokST;
    }

    public void setZaciatokST(CheckBox zaciatokST) {
        this.zaciatokST = zaciatokST;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public Integer getFreqCofor() {
        return freqCofor;
    }

    public void setFreqCofor(Integer freqCofor) {
        this.freqCofor = freqCofor;
    }
}
