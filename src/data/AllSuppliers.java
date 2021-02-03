package data;

import java.io.*;
import java.util.TreeMap;

public class AllSuppliers {

    public static final String suppliersFile = "src/resources/database/cofors.txt";

    private TreeMap<String, Supplier> allSuppliers;

    /** loads a list of all suppliers from file */
    public AllSuppliers() throws IOException
    {
        allSuppliers = new TreeMap<>();
        load();
    }

    public boolean validCofor(String cofor)
    {
        return allSuppliers.containsKey(cofor);
    }

    public Supplier getSupplier(String cofor)
    {
        return allSuppliers.get(cofor);
    }

    public void removeSupplier(String cofor)
    {
        allSuppliers.remove(cofor);
    }

    public void addSupplier(Supplier newSupplier)
    {
        allSuppliers.put(newSupplier.getCofor(), newSupplier);
    }

    private void load() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(suppliersFile));
        String line;

        while ((line = br.readLine()) != null) {
            Supplier s = new Supplier(line);
            allSuppliers.put(s.getCofor(), s);
        }
    }

    public void save() throws IOException
    {
        FileWriter fw = new FileWriter(data.AllSuppliers.suppliersFile, false);
        PrintWriter pw = new PrintWriter(fw);
        StringBuilder sb = new StringBuilder();

        for (Supplier supplier: allSuppliers.values())
        {
            pw.println("cofor#" + supplier.getCofor() +
                      ";supplier#" + supplier.getName() +
                      ";town#" + supplier.getTown() +
                      ";zipcode#" + supplier.getZipcode() +
                      ";country#" + supplier.getCountry() +
                      ";loading#" + supplier.getLoading() +
                      "$monday#" + supplier.getMonday() +
                      ";tuesday#" + supplier.getTuesday() +
                      ";wednesday#" + supplier.getWednesday() +
                      ";thursday#" + supplier.getThursday() +
                      ";friday#" + supplier.getFriday() +
                      ";saturday#" + supplier.getSaturday() +
                      ";sunday#" + supplier.getSunday());
        }
        pw.close();
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int cnt = allSuppliers.size();
        for (Supplier supplier: allSuppliers.values()) {
            sb.append(supplier.toString());
            if (cnt-- > 1) sb.append("\n");
        }
        return sb.toString();
    }
}
