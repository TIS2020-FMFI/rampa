package data;

/**
 * trieda na ulozenie udajov pre dodavatela, kedze na obrazovke Pridanie dodavatela, sa zadavaju udaje pre dodavatela
 * a nasledne sa maju ulozit do suborovej databazy, konkretne priecnka cofors.txt.
 * A taktiez ked bude potrebne vybrat udaj z databazy cofor, tak nech sa s nim lahsie pracuje, napr. ked uzivatel zada
 * cislo coforu tabulky zastavok u dodavatela, tak sa maju vygenerovat ulozene udaje pre daneho dodavatela.
 */
public class Supplier {
    private String cofor; //6 znakov 2 medzery 2 znaky (cisla a pismena)
    private String name;
    private String town;
    private String zipcode;
    private String country;
    private String loading;

    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    public Supplier(String cofor, String name, String town, String zipcode, String country, String loading,
                    String monday, String tuesday, String wednesday, String thursday, String friday,
                    String saturday, String sunday) {
        this.cofor = cofor;
        this.name = name;
        this.town = town;
        this.zipcode = zipcode;
        this.country = country;
        this.loading = loading;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public Supplier(String fullLine)
    {
        String lines[] = fullLine.split("\\$");
        String details = lines[0];
        String hours = lines[1];
        String [] fields = details.split(";");
        cofor = fields[0].split("#")[1];
        name = fields[1].split("#")[1];
        town = fields[2].split("#")[1];
        zipcode = fields[3].split("#")[1];
        country = fields[4].split("#")[1];
        loading = fields[5].split("#")[1];
        fields = hours.split(";");
        monday = fields[0].split("#")[1];
        tuesday = fields[1].split("#")[1];
        wednesday = fields[2].split("#")[1];
        thursday = fields[3].split("#")[1];
        friday = fields[4].split("#")[1];
        saturday = fields[5].split("#")[1];
        sunday = fields[6].split("#")[1];
    }

    public String toString()
    {
        return "cofor: " + cofor +
               ", supplier: " + name +
               ", town: " + town +
               ", zipcode: " + zipcode +
               ", country: " + country +
               ", loading: " + loading;
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

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }
}
