package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class IntroController implements Initializable {
    ZadanieVstupovController zadanieVstupovController;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField inputField;
    @FXML
    private Button vytvorGrafikonBtn;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // aby bolo focusnute rovno na pisanie mena suboru
        vytvorGrafikonBtn.setFocusTraversable(false);
        inputField.setFocusTraversable(true);
    }
}
