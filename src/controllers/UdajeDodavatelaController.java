package controllers;

import data.StatVzdialenostRow;
import data.ZastavkaRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.Main;

import java.io.*;
import java.net.URL;
import java.util.*;

public class UdajeDodavatelaController implements Initializable{

    VyberCoforController vyberCoforController = null;

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
    public TextField pondelokInput;
    @FXML
    public TextField utorokInput;
    @FXML
    public TextField stredaInput;
    @FXML
    public TextField stvrtokInput;
    @FXML
    public TextField piatokInput;
    @FXML
    public TextField sobotaInput;
    @FXML
    public TextField nedelaInput;
    @FXML
    public Label errorLabel;

    private List<TextField> coforDetails;
    private List<TextField> openingHours;

    public UdajeDodavatelaController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/udajeDodavatela.fxml"));
        loader.setController(this);

        Main.udajeDodavatelaStage.setScene(new Scene(loader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coforDetails = new ArrayList<>(
                Arrays.asList(coforInput, supplierInput, townInput, zipcodeInput, countryInput, loadingInput));

        openingHours = new ArrayList<>(
                Arrays.asList(pondelokInput, utorokInput, stredaInput, stvrtokInput, piatokInput, sobotaInput, nedelaInput));
    }

    public void potvrditBtnClick(MouseEvent mouseEvent) throws IOException {
        if(!allInputsFilled()){
            errorLabel.setText("Nevyplnili ste všetky údaje!");
            errorLabel.setVisible(true);
        }
        else if(!hoursCorrectFormat()){
            errorLabel.setText("Časový údaj musí byť vo fromáte 00:00-23:59!");
            errorLabel.setVisible(true);
        }
        if(allInputsFilled() && hoursCorrectFormat()){
            saveUdajeDodavatela();
            errorLabel.setVisible(false);
            Main.zadanieVstupovStage.show();
            Main.udajeDodavatelaStage.hide();
        }
    }

    public void goBackToZadanieVstupovBtnClick(MouseEvent mouseEvent) {
        errorLabel.setVisible(false);
        Main.zadanieVstupovStage.show();
        Main.udajeDodavatelaStage.hide();
    }

    public void vyberCoforBtnClick(MouseEvent mouseEvent) throws IOException {
        errorLabel.setVisible(false);
        vyberCoforController = new VyberCoforController(this);

        Main.vyberCoforStage.show();
    }

    public void saveUdajeDodavatela() throws IOException {
        previouslySaved(coforDetails.get(0).getText());

        List<String> details = new ArrayList<>(
                Arrays.asList("cofor#", "supplier#", "town#", "zipcode#", "country#", "loading#"));
        List<String> days = new ArrayList<>(
                Arrays.asList("pondelok#", "utorok#", "streda#", "stvrtok#", "piatok#", "sobota#", "nedela#"));
        StringBuilder data = new StringBuilder();

        for (int i = 0; i < coforDetails.size(); i++){
            data.append(details.get(i));
            data.append(coforDetails.get(i).getText());
            data.append(";");
        }
        data.setLength(data.length() - 1);
        data.append("$");
        for (int i = 0; i < openingHours.size(); i++){
            data.append(days.get(i));
            data.append(openingHours.get(i).getText());
            data.append(";");
        }
        data.setLength(data.length() - 1);

        FileWriter fw = new FileWriter("src/resources/database/cofors.txt", true);
        PrintWriter pw = new PrintWriter(fw);

        pw.print(data);
        pw.println();
        pw.close();
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


    //ak bol cofor predtym ulozeny, vymaze ho, aby nebola duplicita
    public void previouslySaved(String cofor) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/resources/database/cofors.txt"));
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = br.readLine())!=null)
        {
            if(!cofor.equals(line.split(";")[0].split("#")[1]))
                sb.append(line).append("\n");
        }

        br.close();
        FileWriter fw = new FileWriter(new File("src/resources/database/cofors.txt"));
        fw.write(sb.toString());
        fw.close();
    }

}

