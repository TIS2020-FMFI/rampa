package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {

    public static Stage zadanieVstupovStage = null;
    public static Stage uvodnyStage = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/intro.fxml"));
        uvodnyStage = primaryStage;
        uvodnyStage.setTitle("GEFCO Grafikon");
        uvodnyStage.getIcons().add(new Image(new FileInputStream("src/resources/images/kamionObr.png")));
        uvodnyStage.setScene(new Scene(root));
        uvodnyStage.show();

        vytvorZadanieVstupovStage();
    }

    public static void vytvorZadanieVstupovStage() {
        zadanieVstupovStage = new Stage();
        zadanieVstupovStage.setTitle("GEFCO Grafikon");

    }


    public static void main(String[] args) {
        launch(args);
    }
}
