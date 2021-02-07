package controllers;

import data.SavedDataWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import main.Main;

import java.io.File;
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
            String grafikonName = inputField.getText();
            main.tripData = new SavedDataWrapper();
            main.tripData.grafikonName = grafikonName;
            try {
                switchToInputsEntry();
            } catch (Exception ex)
            {
                main.showExceptionAlert("Could not load the application", "Exception when trying to load the application: " + ex.toString());
            }
        }
    }

    public void switchToInputsEntry() throws IOException {
        if (inputsEntryController == null)
            inputsEntryController = new InputsEntryController(main);
        inputsEntryController.loadDataFromInternalRepresentation();
        main.inputsEntryStage.show();
        main.introStage.hide();
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    public void loadTripBtnClick(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Trip File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MilkRun Trips", "*.milkrun"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(main.introStage);
        if (selectedFile != null) {
            try {
                main.tripData = SavedDataWrapper.load(selectedFile.getCanonicalPath());
                switchToInputsEntry();
            } catch (Exception ex)
            {
                main.showExceptionAlert("Could not load the trip", "Exception when trying to read the trip from file: " + ex.toString());
            }
        }
        errorLabel.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // aby bolo focusnute rovno na pisanie mena suboru
        createTripBtn.setFocusTraversable(false);
        inputField.setFocusTraversable(true);
    }
}
