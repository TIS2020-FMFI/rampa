package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import main.Main;

import java.io.*;
import java.net.URL;
import java.util.*;

public class VyberCoforController implements Initializable{

    @FXML
    private TextArea savedCoforsOutput;
    @FXML
    private TextField coforInput;
    @FXML
    private Label errorLabel;

    private StringBuilder sb = new StringBuilder("");
    private String path;
    UdajeDodavatelaController udajeDodavatelaController;

    private List<String> cofors = new ArrayList<>();
    private List<String> coforsAllData = new ArrayList<>();

    public VyberCoforController(UdajeDodavatelaController udajeDodavatelaController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/vyberCofor.fxml"));
        loader.setController(this);
        this.udajeDodavatelaController = udajeDodavatelaController;

        Main.vyberCoforStage.setScene(new Scene(loader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        URL output = getClass().getResource("../resources/database/cofors.txt");
        path = output.getPath();

        try {
            readSavedCofors();
        } catch (IOException e) {
            e.printStackTrace();
        }
        savedCoforsOutput.appendText(String.valueOf(sb));
    }

    public void potvrditBtnClick(MouseEvent mouseEvent) {
        if (coforInput.getText().isEmpty()){
            errorLabel.setText("Nezadali ste COFOR!");
            errorLabel.setTextFill(Paint.valueOf("RED"));
        }
        else{
            if (cofors.contains(coforInput.getText())){
                filloutUdajeDodavatela(coforInput.getText());
                Main.vyberCoforStage.hide();
            }
            else {
                errorLabel.setText("Zadaný COFOR sa nenchádza v zozname!");
                errorLabel.setTextFill(Paint.valueOf("RED"));
            }
        }
    }

    public void spatBtnClick(MouseEvent mouseEvent) {
        Main.vyberCoforStage.hide();
    }

    public void readSavedCofors() throws  IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        while((line = br.readLine())!=null)
        {
            coforsAllData.add(line);
            line = line.split("\\$")[0];
            cofors.add(line.split(";")[0].split("#")[1]);
            for (String data : line.split(";")) {
                sb.append(data.split("#")[0]).append(": ");
                sb.append(data.split("#")[1]).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }

        br.close();
    }

    public void filloutUdajeDodavatela(String cofor){
        String data =  coforsAllData.get(cofors.indexOf(cofor));

        String coforDetails = data.split("\\$")[0];
        this.udajeDodavatelaController.coforInput.setText(coforDetails.split(";")[0].split("#")[1]);
        this.udajeDodavatelaController.supplierInput.setText(coforDetails.split(";")[1].split("#")[1]);
        this.udajeDodavatelaController.townInput.setText(coforDetails.split(";")[2].split("#")[1]);
        this.udajeDodavatelaController.zipcodeInput.setText(coforDetails.split(";")[3].split("#")[1]);
        this.udajeDodavatelaController.countryInput.setText(coforDetails.split(";")[4].split("#")[1]);
        this.udajeDodavatelaController.loadingInput.setText(coforDetails.split(";")[5].split("#")[1]);

        String openingHours = data.split("\\$")[1];
        this.udajeDodavatelaController.pondelokInput.setText(openingHours.split(";")[0].split("#")[1]);
        this.udajeDodavatelaController.utorokInput.setText(openingHours.split(";")[1].split("#")[1]);
        this.udajeDodavatelaController.stredaInput.setText(openingHours.split(";")[2].split("#")[1]);
        this.udajeDodavatelaController.stvrtokInput.setText(openingHours.split(";")[3].split("#")[1]);
        this.udajeDodavatelaController.piatokInput.setText(openingHours.split(";")[4].split("#")[1]);
        this.udajeDodavatelaController.sobotaInput.setText(openingHours.split(";")[5].split("#")[1]);
        this.udajeDodavatelaController.nedelaInput.setText(openingHours.split(";")[6].split("#")[1]);
    }
}

