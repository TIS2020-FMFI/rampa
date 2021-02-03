package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class IntroController implements Initializable {
    InputsEntryController inputsEntryController;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField inputField;
    @FXML
    private Button createTripBtn;

    private Main main;

    public void createTripBtnClick(MouseEvent mouseEvent) throws IOException {
        if (inputField.getText().isBlank()) errorLabel.setVisible(true);
        else {
            errorLabel.setVisible(false);

            inputsEntryController = new InputsEntryController(main, inputField.getText());
            main.inputsEntryStage.show();
            main.introStage.hide();

        }
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    public void loadTripBtnClick(MouseEvent mouseEvent) {
        errorLabel.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // aby bolo focusnute rovno na pisanie mena suboru
        createTripBtn.setFocusTraversable(false);
        inputField.setFocusTraversable(true);
    }
}
