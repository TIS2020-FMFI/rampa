package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.io.*;
import java.net.URL;
import java.util.*;

public class SupplierDataController implements Initializable{

    SelectCoforController selectCoforController = null;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    public TextField coforInput;
    @FXML
    public TextField supplierInput;
    @FXML
    public TextField townInput;
    @FXML
    public TextField zipcodeInput;
    @FXML
    public TextField countryInput;
    @FXML
    public TextField loadingInput;
    @FXML
    public TextField mondayInput;
    @FXML
    public TextField tuesdayInput;
    @FXML
    public TextField wednesdayInput;
    @FXML
    public TextField thursdayInput;
    @FXML
    public TextField fridayInput;
    @FXML
    public TextField saturdayInput;
    @FXML
    public TextField sundayInput;
    @FXML
    public Label errorLabel;

    private Main main;

    private String editingCofor;

    private List<TextField> coforDetails;
    private List<TextField> openingHours;

    public SupplierDataController(Main main) throws IOException {
        this.main = main;
        editingCofor = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/udajeDodavatela.fxml"));
        loader.setController(this);

        main.supplierDataStage.setScene(new Scene(loader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coforDetails = new ArrayList<>(
                Arrays.asList(coforInput, supplierInput, townInput, zipcodeInput, countryInput, loadingInput));

        openingHours = new ArrayList<>(
                Arrays.asList(mondayInput, tuesdayInput, wednesdayInput, thursdayInput, fridayInput, saturdayInput, sundayInput));
    }

    public void resetInputs()
    {
        for (TextField coforDetail : coforDetails)
            coforDetail.setText("");
        for (TextField openingHour : openingHours)
            openingHour.setText("");
    }

    public void showSupplier(String cofor) {
        editingCofor = cofor;
        data.Supplier supplier = main.suppliers.getSupplier(cofor);
        coforInput.setText(cofor);
        supplierInput.setText(supplier.getName());
        townInput.setText(supplier.getTown());
        zipcodeInput.setText(supplier.getZipcode());
        countryInput.setText(supplier.getCountry());
        loadingInput.setText(supplier.getLoading());

        mondayInput.setText(supplier.getMonday());
        tuesdayInput.setText(supplier.getTuesday());
        wednesdayInput.setText(supplier.getWednesday());
        thursdayInput.setText(supplier.getThursday());
        fridayInput.setText(supplier.getFriday());
        saturdayInput.setText(supplier.getSaturday());
        sundayInput.setText(supplier.getSunday());
    }

    private int replaceCoforYesNoDialog(String oldCofor, String newCofor)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("The cofor has been modified");
        alert.setContentText("Would you like to replace the previous record with cofor '" + oldCofor +
                             "' with the new one '" + newCofor + "' or keep the previous record (" + oldCofor +
                             ") and insert a new supplier (" + newCofor + ") instead?");
        ButtonType okButton = new ButtonType("Replace old", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Insert new", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("Cancel, continue to edit", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
        Optional<ButtonType> but = alert.showAndWait();
        if (but.isPresent() && but.get() == okButton) return 1;
        else if (but.isPresent() && but.get() == noButton) return 0;
        else return -1;
    }

    public void saveBtnClick(MouseEvent mouseEvent) throws IOException {

        if(!allInputsFilled()){
            errorLabel.setText("Nevyplnili ste všetky údaje!");
            errorLabel.setVisible(true);
        }
        else if(!hoursCorrectFormat()){
            errorLabel.setText("Časový údaj musí byť vo fromáte 00:00-23:59!");
            errorLabel.setVisible(true);
        }
        else {
            String newCofor = coforInput.getText();
            String newName = supplierInput.getText();
            String newTown = townInput.getText();
            String newZipcode = zipcodeInput.getText();
            String newCountry = countryInput.getText();
            String newLoading = loadingInput.getText();

            String newMonday = mondayInput.getText();
            String newTuesday = tuesdayInput.getText();
            String newWednesday = wednesdayInput.getText();
            String newThursday = thursdayInput.getText();
            String newFriday = fridayInput.getText();
            String newSaturday = saturdayInput.getText();
            String newSunday = sundayInput.getText();

            if (editingCofor != null)
            {
                if (!editingCofor.equals(newCofor))
                {
                    if (main.suppliers.validCofor(newCofor))
                    {
                        main.showExceptionAlert("Supplier with this cofor already exists!",
                                "You have chosen to edit the supplier with cofor '" + editingCofor +
                                "', but then you have changed it to cofor '" + newCofor + "', but a supplier " +
                                " with this cofor already exits. If you want to edit that supplier, you need to " +
                                " select it first.");
                        return;
                    }
                    int decision = replaceCoforYesNoDialog(editingCofor, newCofor);
                    if (decision == -1) return;
                    else if (decision == 1)
                    {
                        main.suppliers.removeSupplier(editingCofor);
                    }
                }
                else main.suppliers.removeSupplier(editingCofor);
            }

            main.suppliers.addSupplier(new data.Supplier(newCofor, newName, newTown, newZipcode, newCountry, newLoading,
                    newMonday, newTuesday, newWednesday, newThursday, newFriday,
                                                 newSaturday, newSunday));
            try {
                main.suppliers.save();
            } catch (IOException ex)
            {
                main.showExceptionAlert("Problem saving suppliers file",
                        "While trying to save the list of suppliers to file " +
                                data.AllSuppliers.suppliersFile + " we have got an exception: " + ex.toString() +
                                " the data have not been saved, sorry.");
            }
            errorLabel.setVisible(false);
            main.inputsEntryStage.show();
            main.supplierDataStage.hide();
        }
    }

    public void cancelBtnClick(MouseEvent mouseEvent) {
        errorLabel.setVisible(false);
        main.inputsEntryStage.show();
        main.supplierDataStage.hide();
    }

    public void selectCoforBtnClick(MouseEvent mouseEvent) throws IOException {
        errorLabel.setVisible(false);
        selectCoforController = new SelectCoforController(main, this);

        main.selectCoforStage.show();
    }

    public boolean allInputsFilled() {
        for (TextField coforDetail : coforDetails) {
            if (coforDetail.getText().isEmpty()) {
                return false;
            }
        }

        for (TextField openingHour : openingHours) {
            if (openingHour.getText().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public boolean hoursCorrectFormat() {
        String format = "([01][0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]";
        for (TextField openingHour : openingHours){
            if(!openingHour.getText().matches(format)){
                return false;
            }
        }
        return true;
    }
}

