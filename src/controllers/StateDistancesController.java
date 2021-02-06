package controllers;

import data.CountryDistance;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StateDistancesController implements Initializable {
    final Integer CELL_HEIGHT = 30;
    final int SCROLL_SPEED = 6;
    ObservableList<CountryDistance> data = FXCollections.observableArrayList();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox tableVbox;
    @FXML
    private TableView<CountryDistance> tableView;
    @FXML
    private TableColumn<CountryDistance, String> state;
    @FXML
    private TableColumn<CountryDistance, String> distance;

    private Main main;

    public StateDistancesController(Main main) throws IOException {
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/prejdenaVzdialenost.fxml"));
        loader.setController(this);

        main.stateDistancesStage.setScene(new Scene(loader.load()));
        main.stateDistancesStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            anchorPane.setPrefWidth((Double) newVal - 30);
            scrollPane.setPrefWidth((Double) newVal);
        });

        main.stateDistancesStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            anchorPane.setPrefHeight((Double) newVal - 50);
            scrollPane.setPrefWidth((Double) newVal);
        });

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
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));

        editableCols();
    }

    private void editableCols() {
        state.setCellFactory(TextFieldTableCell.forTableColumn());
        state.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setState(e.getNewValue()));

        distance.setCellFactory(TextFieldTableCell.forTableColumn());
        distance.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDistance(e.getNewValue()));

    }

    public void loadData() {
        tableView.setFixedCellSize(CELL_HEIGHT);
        data.clear();
        for (CountryDistance cd : main.tripData.countryDistancesList)
            data.add(new CountryDistance(cd.getState(), cd.getDistance()));
        if (data.size() == 0)
            data.add(new CountryDistance("", ""));
        tableView.setItems(data);
    }

    private void saveData()
    {
        List<CountryDistance> output = main.tripData.countryDistancesList;
        output.clear();
        for (CountryDistance cd : data)
            output.add(new CountryDistance(cd.getState(), cd.getDistance()));
    }

    public void addStateBtnClick(MouseEvent mouseEvent) {
        tableView.setPrefHeight(tableView.getPrefHeight() + CELL_HEIGHT);
        tableVbox.setPrefHeight(tableVbox.getPrefHeight() + CELL_HEIGHT);
        data.add(new CountryDistance("", ""));
    }

    public void removeStateBtnClick(MouseEvent mouseEvent) {
        if (tableView.getPrefHeight() > CELL_HEIGHT*2 + 6) {
            tableView.setPrefHeight(tableView.getPrefHeight() - CELL_HEIGHT);
            tableVbox.setPrefHeight(tableVbox.getPrefHeight() - CELL_HEIGHT);
            data.remove(data.size() - 1);
        }
    }

    public void confirmBtnClick(MouseEvent mouseEvent) {
        saveData();
        main.inputsEntryStage.show();
        main.stateDistancesStage.hide();
    }
}
