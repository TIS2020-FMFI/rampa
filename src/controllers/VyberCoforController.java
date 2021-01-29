package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.io.*;
import java.net.URL;
import java.util.*;

public class VyberCoforController implements Initializable{

    @FXML
    private TextArea savedCoforsOutput;
    @FXML
    private TextField coforInput;

    private StringBuilder sb = new StringBuilder("");
    private String path;
    UdajeDodavatelaController udajeDodavatelaController;

    public VyberCoforController(UdajeDodavatelaController udajeDodavatelaController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/vyberCofor.fxml"));
        loader.setController(this);
        this.udajeDodavatelaController = udajeDodavatelaController;

        Main.vyberCoforStage.setScene(new Scene(loader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        URL outputt = getClass().getResource("../resources/database/cofors.txt");
        path = outputt.getPath();

        try {
            readSavedCofors();
        } catch (IOException e) {
            e.printStackTrace();
        }
        savedCoforsOutput.appendText(String.valueOf(sb));
    }

    public void potvrditBtnClick(MouseEvent mouseEvent) throws IOException {
        // over ci coforInput.getText() je cofor, ktory existuje v cofors.txt subore, ak ano,

        // tak nastav triedne premenne udajeDodavatelaController na hodnoty zo suboru, kde sa nachadza ten subor
        this.udajeDodavatelaController.coforInput.setText(coforInput.getText());
        // this.udajeDodavatelaController.supplierInput.setText(.......);
        // this.udajeDodavatelaController.townInput.setText(.......);
        // a tak nastavit vsetky premenne...
        Main.vyberCoforStage.hide();
    }

    public void spatBtnClick(MouseEvent mouseEvent) throws IOException {
        Main.vyberCoforStage.hide();
    }

    public void readSavedCofors() throws  IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        while((line = br.readLine())!=null)
        {
            line = line.split("\\$")[0];
            for (String data : line.split(";")) {
                sb.append(data.split("#")[0]).append(": ");
                sb.append(data.split("#")[1]).append(", ");
                }
            sb.setLength(sb.length() - 2);
            sb.append("\n");
            }

        br.close();
    }
}


