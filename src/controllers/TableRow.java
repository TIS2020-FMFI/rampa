package controllers;

import data.ZastavkaRow;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalTime;
import java.util.Date;

public class TableRow {

    private SimpleStringProperty cofor;
    private SimpleStringProperty meno;
    private SimpleIntegerProperty vzdialenost;
    private SimpleIntegerProperty rychlost;
    private SimpleIntegerProperty dlzkaNakladky;
    private SimpleStringProperty typZastavky;
    private SimpleStringProperty loading;
    private SimpleStringProperty rampa;
    private SimpleStringProperty zaciatokST;
    private SimpleObjectProperty<LocalTime> cas;
    private SimpleIntegerProperty freqCofor;

    public TableRow(ZastavkaRow zastavkaRow) {
        this.cofor = new SimpleStringProperty(zastavkaRow.getCofor());
        this.meno = new SimpleStringProperty(zastavkaRow.getMeno());
        //this.cas = new SimpleObjectProperty<LocalTime>(zastavkaRow.getCas());

        //this.vzdialenost = new SimpleIntegerProperty(zastavkaRow.getVzdialenost());
        //this.rychlost = new SimpleIntegerProperty(zastavkaRow.getRychlost());
        //this.dlzkaNakladky = new SimpleIntegerProperty(zastavkaRow.getDlzkaNakladky());

        this.typZastavky = new SimpleStringProperty(zastavkaRow.getTypZastavky());
        this.loading = new SimpleStringProperty(zastavkaRow.getLoading());
        this.rampa = new SimpleStringProperty(zastavkaRow.getRampa());
        //this.zaciatokST = new SimpleStringProperty(zastavkaRow.getZaciatokST());

        //this.freqCofor = new SimpleIntegerProperty(zastavkaRow.getFreqCofor());
    }

    public TableRow(final String cofor) {
//        this.firstName = new SimpleStringProperty(firstName);
//        this.surname = new SimpleStringProperty(surname);
//        this.dateOfBirth = new SimpleObjectProperty<Date>(dateOfBirth);
//        this.occupation = new SimpleStringProperty(occupation);
//        this.salary = new SimpleDoubleProperty(salary);
    }

    public String getCofor() {
        return cofor.get();
    }

    public SimpleStringProperty coforProperty() {
        return cofor;
    }

    public void setCofor(String cofor) {
        this.cofor.set(cofor);
    }

    public String getMeno() {
        return meno.get();
    }

    public SimpleStringProperty menoProperty() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno.set(meno);
    }

    public int getVzdialenost() {
        return vzdialenost.get();
    }

    public SimpleIntegerProperty vzdialenostProperty() {
        return vzdialenost;
    }

    public void setVzdialenost(int vzdialenost) {
        this.vzdialenost.set(vzdialenost);
    }

    public int getRychlost() {
        return rychlost.get();
    }

    public SimpleIntegerProperty rychlostProperty() {
        return rychlost;
    }

    public void setRychlost(int rychlost) {
        this.rychlost.set(rychlost);
    }

    public int getDlzkaNakladky() {
        return dlzkaNakladky.get();
    }

    public SimpleIntegerProperty dlzkaNakladkyProperty() {
        return dlzkaNakladky;
    }

    public void setDlzkaNakladky(int dlzkaNakladky) {
        this.dlzkaNakladky.set(dlzkaNakladky);
    }

    public String getTypZastavky() {
        return typZastavky.get();
    }

    public SimpleStringProperty typZastavkyProperty() {
        return typZastavky;
    }

    public void setTypZastavky(String typZastavky) {
        this.typZastavky.set(typZastavky);
    }

    public String getLoading() {
        return loading.get();
    }

    public SimpleStringProperty loadingProperty() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading.set(loading);
    }

    public String getRampa() {
        return rampa.get();
    }

    public SimpleStringProperty rampaProperty() {
        return rampa;
    }

    public void setRampa(String rampa) {
        this.rampa.set(rampa);
    }

    public String getZaciatokST() {
        return zaciatokST.get();
    }

    public SimpleStringProperty zaciatokSTProperty() {
        return zaciatokST;
    }

    public void setZaciatokST(String zaciatokST) {
        this.zaciatokST.set(zaciatokST);
    }

    public LocalTime getCas() {
        return cas.get();
    }

    public SimpleObjectProperty<LocalTime> casProperty() {
        return cas;
    }

    public void setCas(LocalTime cas) {
        this.cas.set(cas);
    }

    public int getFreqCofor() {
        return freqCofor.get();
    }

    public SimpleIntegerProperty freqCoforProperty() {
        return freqCofor;
    }

    public void setFreqCofor(int freqCofor) {
        this.freqCofor.set(freqCofor);
    }
}
