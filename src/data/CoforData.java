package data;

/**
 * trieda na ulozenie udajov pre dodavatela, kedze na obrazovke Pridanie dodavatela, sa zadavaju udaje pre dodavatela
 * a nasledne sa maju ulozit do suborovej databazy, konkretne priecnka cofors.txt.
 * A taktiez ked bude potrebne vybrat udaj z databazy cofor, tak nech sa s nim lahsie pracuje, napr. ked uzivatel zada
 * cislo coforu tabulky zastavok u dodavatela, tak sa maju vygenerovat ulozene udaje pre daneho dodavatela.
 */
public class CoforData {
    private Integer cofor;
    private String supplier;
    private String town;
    private String zipcode;
    private String country;
    private String loading;

    private String pondelok;
    private String utorok;
    private String streda;
    private String stvrtok;
    private String piatok;
    private String sobota;
    private String nedela;

    public CoforData(Integer cofor, String supplier, String town, String zipcode, String country, String loading,
                     String pondelok, String utorok, String streda, String stvrtok, String piatok,
                     String sobota, String nedela) {
        this.cofor = cofor;
        this.supplier = supplier;
        this.town = town;
        this.zipcode = zipcode;
        this.country = country;
        this.loading = loading;
        this.pondelok = pondelok;
        this.utorok = utorok;
        this.streda = streda;
        this.stvrtok = stvrtok;
        this.piatok = piatok;
        this.sobota = sobota;
        this.nedela = nedela;
    }

    public Integer getCofor() {
        return cofor;
    }

    public void setCofor(Integer cofor) {
        this.cofor = cofor;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading = loading;
    }

    public String getPondelok() {
        return pondelok;
    }

    public void setPondelok(String pondelok) {
        this.pondelok = pondelok;
    }

    public String getUtorok() {
        return utorok;
    }

    public void setUtorok(String utorok) {
        this.utorok = utorok;
    }

    public String getStreda() {
        return streda;
    }

    public void setStreda(String streda) {
        this.streda = streda;
    }

    public String getStvrtok() {
        return stvrtok;
    }

    public void setStvrtok(String stvrtok) {
        this.stvrtok = stvrtok;
    }

    public String getPiatok() {
        return piatok;
    }

    public void setPiatok(String piatok) {
        this.piatok = piatok;
    }

    public String getSobota() {
        return sobota;
    }

    public void setSobota(String sobota) {
        this.sobota = sobota;
    }

    public String getNedela() {
        return nedela;
    }

    public void setNedela(String nedela) {
        this.nedela = nedela;
    }
}
