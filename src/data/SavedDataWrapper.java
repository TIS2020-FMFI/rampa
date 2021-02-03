package data;

import java.io.Serializable;
import java.util.List;

/**
 * Serializovatelna trieda, ktora ma ulozene vsetky udaje, ktore je potrebne ulozit do suboru, ked sa uklada cely obsah
 * grafikonu.
 */
public class SavedDataWrapper implements Serializable {
    private HeaderInputData headerInputData;
    private List<OneStopRow> oneStopRowList;
    private TechnicalData technicalData;
    private List<CountryDistances> countryDistancesList;
}
