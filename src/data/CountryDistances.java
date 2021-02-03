package data;

import java.io.Serializable;

public class CountryDistances implements Serializable {
    private String stat;
    private String vzdialenost;

    public CountryDistances(String stat, String vzdialenost) {
        this.stat = stat;
        this.vzdialenost = vzdialenost;
    }


    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getVzdialenost() {
        return vzdialenost;
    }

    public void setVzdialenost(String vzdialenost) {
        this.vzdialenost = vzdialenost;
    }


}
