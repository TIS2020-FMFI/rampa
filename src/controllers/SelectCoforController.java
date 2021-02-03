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

public class SelectCoforController implements Initializable{

    @FXML
    private TextArea savedCoforsOutput;
    @FXML
    private TextField coforInput;
    @FXML
    private Label errorLabel;

    private Main main;

    private StringBuilder sb = new StringBuilder();
    SupplierDataController supplierDataController;

    public SelectCoforController(Main main, SupplierDataController supplierDataController) throws IOException {
        this.main = main;
        this.supplierDataController = supplierDataController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/vyberCofor.fxml"));
        loader.setController(this);

        main.selectCoforStage.setScene(new Scene(loader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        savedCoforsOutput.appendText(main.suppliers.toString());
    }

    public void confirmBtnClick(MouseEvent mouseEvent) {
        if (coforInput.getText().isEmpty()){
            errorLabel.setText("Nezadali ste COFOR!");
            errorLabel.setTextFill(Paint.valueOf("RED"));
        }
        else{
            String cofor = coforInput.getText();
            if (main.suppliers.validCofor(cofor)){
                supplierDataController.showSupplier(cofor);
                main.selectCoforStage.hide();
            }
            else {
                errorLabel.setText("Zadaný COFOR sa nenchádza v zozname!");
                errorLabel.setTextFill(Paint.valueOf("RED"));
            }
        }
    }

    public void backBtnClick(MouseEvent mouseEvent) {
        main.selectCoforStage.hide();
    }
}

