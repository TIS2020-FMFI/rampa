package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.io.IOException;



public class IntroController {
    ZadanieVstupovController zadanieVstupovController;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField inputField;


    public void vytvoritGrafikonBtnClick(MouseEvent mouseEvent) throws IOException {
        if (inputField.getText().isBlank()) errorLabel.setVisible(true);
        else {
            errorLabel.setVisible(false);

            zadanieVstupovController = new ZadanieVstupovController(inputField.getText());
            Main.zadanieVstupovStage.show();
            Main.uvodnyStage.hide();

        }
    }

    public void nacitatGrafikonBtnClick(MouseEvent mouseEvent) {
        errorLabel.setVisible(false);
    }
}
