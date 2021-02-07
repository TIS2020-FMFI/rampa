package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Serializovatelna trieda, ktora ma ulozene vsetky udaje, ktore je potrebne ulozit do suboru, ked sa uklada cely obsah
 * grafikonu.
 */
public class SavedDataWrapper implements Serializable {

    public String grafikonName;
    public HeaderInputData headerInputData;
    public List<OneStopRow> oneStopRowList;
    public TechnicalData technicalData;
    public List<CountryDistance> countryDistancesList;

    private static final String unspecifiedStr = "unspecified";

    public SavedDataWrapper()
    {
        grafikonName = "unnamed";
        headerInputData = new HeaderInputData("town", "place",
                "08:00", 123, 0, "stop", null);
        oneStopRowList = new ArrayList<>();
        technicalData = new TechnicalData(unspecifiedStr, unspecifiedStr, unspecifiedStr,
                unspecifiedStr, unspecifiedStr, unspecifiedStr,0, 0);
        countryDistancesList = new ArrayList<>();
    }

    public void save(String fileName) throws Exception
    {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        outputStream.writeObject(this);
        outputStream.close();
    }

    public static SavedDataWrapper load(String fileName) throws Exception
    {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
        SavedDataWrapper loaded = (SavedDataWrapper) inputStream.readObject();
        inputStream.close();
        return loaded;
    }
}
