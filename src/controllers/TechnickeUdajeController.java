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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TechnickeUdajeController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;


    public TechnickeUdajeController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/technickeUdaje.fxml"));
        loader.setController(this);

        Main.technickeUdajeStage.setScene(new Scene(loader.load()));
    }

    public void potvrditBtnClick(MouseEvent mouseEvent) {
        Main.zadanieVstupovStage.show();
        Main.technickeUdajeStage.hide();
    }
}

