package controllers;

import data.UkazkaRow;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UkazkaController implements Initializable {
    final Integer CELL_HEIGHT = 30;
    final int SCROLL_SPEED = 6;

    ObservableList<UkazkaRow> data = FXCollections.observableArrayList();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox tableVbox;
    @FXML
    private TableView<UkazkaRow> tableView;
    @FXML
    private TableColumn<UkazkaRow, String> menoZastavky;
    @FXML
    private TableColumn<UkazkaRow, String> miesto;
    @FXML
    private TableColumn<UkazkaRow, String> vzdialenost;
    @FXML
    private TableColumn<UkazkaRow, String> rychlost;
    @FXML
    private TableColumn<UkazkaRow, String> casPrichodu;
    @FXML
    private TableColumn<UkazkaRow, String> casOdchodu;
    @FXML
    private TableColumn<UkazkaRow, String> den;
    @FXML
    private TableColumn<UkazkaRow, String> novyDen;
    @FXML
    private TableColumn<UkazkaRow, String> novyCasPrichodu;
    @FXML
    private Button prepocitat;

    public UkazkaController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/ukazka.fxml"));
        loader.setController(this);

        Main.ukazkaStage.setScene(new Scene(loader.load()));

        Main.ukazkaStage.widthProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefWidth((Double) newVal - 30));
        Main.ukazkaStage.heightProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefHeight((Double) newVal));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        loadData();

        Main.makeFasterScroll(scrollPane, anchorPane, SCROLL_SPEED);
    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        menoZastavky.setCellValueFactory(new PropertyValueFactory<>("menoZastavky"));
        miesto.setCellValueFactory(new PropertyValueFactory<>("miesto"));
        vzdialenost.setCellValueFactory(new PropertyValueFactory<>("vzdialenost"));
        rychlost.setCellValueFactory(new PropertyValueFactory<>("rychlost"));
        casPrichodu.setCellValueFactory(new PropertyValueFactory<>("casPrichodu"));
        casOdchodu.setCellValueFactory(new PropertyValueFactory<>("casOdchodu"));
        den.setCellValueFactory(new PropertyValueFactory<>("den"));
        novyDen.setCellValueFactory(new PropertyValueFactory<>("novyDen"));
        novyCasPrichodu.setCellValueFactory(new PropertyValueFactory<>("novyCasPrichodu"));

        editableCols();
    }

    private void editableCols() {
        menoZastavky.setCellFactory(TextFieldTableCell.forTableColumn());
        menoZastavky.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setMenoZastavky(e.getNewValue()));

        miesto.setCellFactory(TextFieldTableCell.forTableColumn());
        miesto.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setMiesto(e.getNewValue()));

        vzdialenost.setCellFactory(TextFieldTableCell.forTableColumn());
        vzdialenost.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setVzdialenost(e.getNewValue()));

        rychlost.setCellFactory(TextFieldTableCell.forTableColumn());
        rychlost.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setRychlost(e.getNewValue()));

        casPrichodu.setCellFactory(TextFieldTableCell.forTableColumn());
        casPrichodu.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setCasPrichodu(e.getNewValue()));

        casOdchodu.setCellFactory(TextFieldTableCell.forTableColumn());
        casOdchodu.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setCasOdchodu(e.getNewValue()));

        den.setCellFactory(TextFieldTableCell.forTableColumn());
        den.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDen(e.getNewValue()));

        novyDen.setCellFactory(TextFieldTableCell.forTableColumn());
        novyDen.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setNovyDen(e.getNewValue()));

        novyCasPrichodu.setCellFactory(TextFieldTableCell.forTableColumn());
        novyCasPrichodu.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setNovyCasPrichodu(e.getNewValue()));
    }

    public void loadData() {
        tableView.setFixedCellSize(30.0);
        data.add(new UkazkaRow("", "", "", "", "", "", "", "", ""));
        tableView.setItems(data);
    }

}


