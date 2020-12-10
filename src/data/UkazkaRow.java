package data;

import javafx.scene.control.CheckBox;

public class UkazkaRow {

    private String menoZastavky;
    private String miesto;
    private String vzdialenost;
    private String rychlost;
    private String casPrichodu;
    private String casOdchodu;
    private String den;
    private String novyDen;
    private String novyCasPrichodu;

    public String getMenoZastavky() {
        return menoZastavky;
    }

    public void setMenoZastavky(String menoZastavky) {
        this.menoZastavky = menoZastavky;
    }

    public String getMiesto() {
        return miesto;
    }

    public void setMiesto(String miesto) {
        this.miesto = miesto;
    }

    public String getVzdialenost() {
        return vzdialenost;
    }

    public void setVzdialenost(String vzdialenost) {
        this.vzdialenost = vzdialenost;
    }

    public String getRychlost() {
        return rychlost;
    }

    public void setRychlost(String rychlost) {
        this.rychlost = rychlost;
    }

    public String getCasPrichodu() {
        return casPrichodu;
    }

    public void setCasPrichodu(String casPrichodu) {
        this.casPrichodu = casPrichodu;
    }

    public String getCasOdchodu() {
        return casOdchodu;
    }

    public void setCasOdchodu(String casOdchodu) {
        this.casOdchodu = casOdchodu;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public String getNovyDen() {
        return novyDen;
    }

    public void setNovyDen(String novyDen) {
        this.novyDen = novyDen;
    }

    public String getNovyCasPrichodu() {
        return novyCasPrichodu;
    }

    public void setNovyCasPrichodu(String novyCasPrichodu) {
        this.novyCasPrichodu = novyCasPrichodu;
    }

    public UkazkaRow(String menoZastavky, String miesto, String vzdialenost, String rychlost, String casPrichodu,
                     String casOdchodu, String den, String novyDen, String novyCasPrichodu) {
        this.menoZastavky = menoZastavky;
        this.miesto = miesto;
        this.vzdialenost = vzdialenost;
        this.rychlost = rychlost;
        this.casPrichodu = casPrichodu;
        this.casOdchodu = casOdchodu;
        this.den = den;
        this.novyDen = novyDen;
        this.novyCasPrichodu = novyCasPrichodu;
    }
}
