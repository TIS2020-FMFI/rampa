package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Main extends Application {

    public static Stage uvodnyStage = null;
    public static Stage zadanieVstupovStage = null;
    public static Stage statVzdialenostStage = null;
    public static Stage technickeUdajeStage = null;
    public static Stage udajeDodavatelaStage = null;
    public static Stage vyberCoforStage = null;
    public static Stage ukazkaStage = null;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/intro.fxml"));
        uvodnyStage = primaryStage;
        uvodnyStage.setTitle("GEFCO Grafikon");
        uvodnyStage.getIcons().add(new Image(new FileInputStream("src/resources/images/kamionObr.png")));
        uvodnyStage.setScene(new Scene(root));
        uvodnyStage.show();

        createStages();
    }

    // aby sa rychlejsie scrollovalo kedze defaultne sa scrolluje dost pomaly v scroll pane
    public static void makeFasterScroll(ScrollPane scrollPane, AnchorPane anchorPane, Integer SCROLL_SPEED) {
        // aby sa rychlejsie scrollovalo kedze defaultne sa scrolluje dost pomaly
        scrollPane.setContent(anchorPane);
        anchorPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY() * SCROLL_SPEED;
            double width = scrollPane.getContent().getBoundsInLocal().getWidth();
            double vvalue = scrollPane.getVvalue();
            scrollPane.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the scrolling equally fast regardless of the actual width of the component
        });
    }

    public static void createStages() {
        zadanieVstupovStage = new Stage();
        zadanieVstupovStage.setTitle("GEFCO Grafikon");

        statVzdialenostStage = new Stage();
        statVzdialenostStage.setTitle("Určenie prejdenej vzdialenosti pre daný štát");

        technickeUdajeStage = new Stage();
        technickeUdajeStage.setTitle("Určenie technických údajov");

        udajeDodavatelaStage = new Stage();
        udajeDodavatelaStage.setTitle("Pridanie dodávateľa");

        vyberCoforStage = new Stage();
        vyberCoforStage.setTitle("Vyberanie dodávateľa");

        ukazkaStage = new Stage();
        ukazkaStage.setTitle("Ukážka");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
