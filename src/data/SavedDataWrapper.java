package data;

import java.io.Serializable;
import java.util.List;

/**
 * Serializovatelna trieda, ktora ma ulozene vsetky udaje, ktore je potrebne ulozit do suboru, ked sa uklada cely obsah
 * grafikonu.
 */
public class SavedDataWrapper implements Serializable {
    private ZadanieVstupovHeaderData zadanieVstupovHeaderData;
    private List<ZastavkaRow> zastavkaRowList;
    private TechnickeUdaje technickeUdaje;
    private List<StatVzdialenostRow> statVzdialenostRowList;
}
