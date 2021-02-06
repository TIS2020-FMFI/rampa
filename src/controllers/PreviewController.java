package controllers;

import data.PreviewRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PreviewController implements Initializable {
    final Integer CELL_HEIGHT = 30;
    final int SCROLL_SPEED = 6;

    ObservableList<PreviewRow> data = FXCollections.observableArrayList();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox tableVbox;
    @FXML
    private TableView<PreviewRow> tableView;
    @FXML
    private TableColumn<PreviewRow, String> stopName;
    @FXML
    private TableColumn<PreviewRow, String> place;
    @FXML
    private TableColumn<PreviewRow, String> distance;
    @FXML
    private TableColumn<PreviewRow, String> speed;
    @FXML
    private TableColumn<PreviewRow, String> arrivalTime;
    @FXML
    private TableColumn<PreviewRow, String> departureTime;
    @FXML
    private TableColumn<PreviewRow, String> day;
    @FXML
    private TableColumn<PreviewRow, String> newDay;
    @FXML
    private TableColumn<PreviewRow, String> newArrivalTime;
    @FXML
    private Button recalculate;

    private Main main;

    public PreviewController(Main main) throws IOException {
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/ukazka.fxml"));
        loader.setController(this);

        main.previewStage.setScene(new Scene(loader.load()));

        main.previewStage.widthProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefWidth((Double) newVal - 30));
        main.previewStage.heightProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefHeight((Double) newVal));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        loadData();

        main.makeFasterScroll(scrollPane, anchorPane, SCROLL_SPEED);
    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        stopName.setCellValueFactory(new PropertyValueFactory<>("stopName"));
        place.setCellValueFactory(new PropertyValueFactory<>("place"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        speed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        departureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        day.setCellValueFactory(new PropertyValueFactory<>("day"));
        newDay.setCellValueFactory(new PropertyValueFactory<>("newDay"));
        newArrivalTime.setCellValueFactory(new PropertyValueFactory<>("newArrivalTime"));

        editableCols();
    }

    private void editableCols() {
        stopName.setCellFactory(TextFieldTableCell.forTableColumn());
        stopName.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setStopName(e.getNewValue()));

        place.setCellFactory(TextFieldTableCell.forTableColumn());
        place.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setPlace(e.getNewValue()));

        distance.setCellFactory(TextFieldTableCell.forTableColumn());
        distance.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDistance(e.getNewValue()));

        speed.setCellFactory(TextFieldTableCell.forTableColumn());
        speed.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setSpeed(e.getNewValue()));

        arrivalTime.setCellFactory(TextFieldTableCell.forTableColumn());
        arrivalTime.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setArrivalTime(e.getNewValue()));

        departureTime.setCellFactory(TextFieldTableCell.forTableColumn());
        departureTime.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDepartureTime(e.getNewValue()));

        day.setCellFactory(TextFieldTableCell.forTableColumn());
        day.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDay(e.getNewValue()));

        newDay.setCellFactory(TextFieldTableCell.forTableColumn());
        newDay.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setNewDay(e.getNewValue()));

        newArrivalTime.setCellFactory(TextFieldTableCell.forTableColumn());
        newArrivalTime.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setNewArrivalTime(e.getNewValue()));
    }

    public void loadData() {
        tableView.setFixedCellSize(CELL_HEIGHT);
        data.add(new PreviewRow("", "", "", "", "", "", "", "", ""));
        tableView.setItems(data);
    }

}


