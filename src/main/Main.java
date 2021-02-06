package main;

import data.AllSuppliers;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import java.io.FileInputStream;
import java.util.Optional;

public class Main extends Application {

    public Stage introStage = null;
    public Stage inputsEntryStage = null;
    public Stage stateDistancesStage = null;
    public Stage technicalDataStage = null;
    public Stage supplierDataStage = null;
    public Stage selectCoforStage = null;
    public Stage previewStage = null;

    public data.AllSuppliers suppliers;
    public data.SavedDataWrapper tripData;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/intro.fxml"));
        Parent root = loader.load();
        controllers.IntroController c = loader.getController();
        c.setMain(this);
        introStage = primaryStage;
        introStage.setTitle("GEFCO Grafikon");
        introStage.getIcons().add(new Image(new FileInputStream("src/resources/images/kamionObr.png")));
        introStage.setScene(new Scene(root));
        introStage.show();

        createStages();
    }

    // aby sa rychlejsie scrollovalo kedze defaultne sa scrolluje dost pomaly v scroll pane
    public void makeFasterScroll(ScrollPane scrollPane, AnchorPane anchorPane, Integer SCROLL_SPEED) {
        // aby sa rychlejsie scrollovalo kedze defaultne sa scrolluje dost pomaly
        scrollPane.setContent(anchorPane);
        anchorPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY() * SCROLL_SPEED;
            double width = scrollPane.getContent().getBoundsInLocal().getWidth();
            double vvalue = scrollPane.getVvalue();
            scrollPane.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the scrolling equally fast regardless of the actual width of the component
        });
    }

    public boolean reallyClose()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.CLOSE);
        alert.setTitle("Close this trip?");
        alert.setContentText(String.format("Are you sure you want to leave this page, and return " +
                        "back to the introductory main page? All unsaved changes will be lost."));
        alert.initOwner(inputsEntryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();

        if(res.isPresent()) {
            if(res.get().equals(ButtonType.CLOSE))
                return true;
        }
        return false;
    }

    public void closeWindowEvent(WindowEvent event)
    {
        if (reallyClose()) introStage.show();
        else if (event != null) event.consume();
    }

    public void createStages() {
        inputsEntryStage = new Stage();
        inputsEntryStage.setTitle("GEFCO Grafikon");

        EventHandler<WindowEvent> reopenEntryStage = new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                inputsEntryStage.show();
            }
        };

        stateDistancesStage = new Stage();
        stateDistancesStage.setTitle("Určenie prejdenej vzdialenosti pre daný štát");
        stateDistancesStage.setOnCloseRequest(reopenEntryStage);

        technicalDataStage = new Stage();
        technicalDataStage.setTitle("Určenie technických údajov");
        technicalDataStage.setOnCloseRequest(reopenEntryStage);

        supplierDataStage = new Stage();
        supplierDataStage.setTitle("Pridanie dodávateľa");
        supplierDataStage.setOnCloseRequest(reopenEntryStage);

        selectCoforStage = new Stage();
        selectCoforStage.setTitle("Vyberanie dodávateľa");

        previewStage = new Stage();
        previewStage.setTitle("Ukážka");
        previewStage.setOnCloseRequest(reopenEntryStage);
    }

    public Main()
    {
        try {
            suppliers = new AllSuppliers();
        } catch (IOException ex) {
            showExceptionAlert("Suppliers file not found",
                    "While trying to load the list of suppliers from file " +
                            data.AllSuppliers.suppliersFile + " we have got an exception: " + ex.toString() +
                            " if the file is missing, it will be created when you add a new supplier.");
        }
    }

    public void showExceptionAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
