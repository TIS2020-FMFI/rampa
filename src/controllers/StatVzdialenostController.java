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

public class StatVzdialenostController implements Initializable {
    final Integer CELL_HEIGHT = 30;
    final int SCROLL_SPEED = 6;
    ObservableList<StatVzdialenostRow> data = FXCollections.observableArrayList();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox tableVbox;
    @FXML
    private TableView<StatVzdialenostRow> tableView;
    @FXML
    private TableColumn<StatVzdialenostRow, String> stat;
    @FXML
    private TableColumn<StatVzdialenostRow, String> vzdialenost;


    public StatVzdialenostController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/prejdenaVzdialenost.fxml"));
        loader.setController(this);

        Main.statVzdialenostStage.setScene(new Scene(loader.load()));

        Main.statVzdialenostStage.widthProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefWidth((Double) newVal - 30));
        Main.statVzdialenostStage.heightProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefHeight((Double) newVal));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        loadData();

        //Main.makeFasterScroll(scrollPane, anchorPane, SCROLL_SPEED);
    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        stat.setCellValueFactory(new PropertyValueFactory<>("stat"));
        vzdialenost.setCellValueFactory(new PropertyValueFactory<>("vzdialenost"));

        editableCols();
    }

    private void editableCols() {
        stat.setCellFactory(TextFieldTableCell.forTableColumn());
        stat.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setStat(e.getNewValue()));

        vzdialenost.setCellFactory(TextFieldTableCell.forTableColumn());
        vzdialenost.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setVzdialenost(e.getNewValue()));

    }

    public void loadData() {
        tableView.setFixedCellSize(30.0);
        data.add(new StatVzdialenostRow("ah", ""));
        tableView.setItems(data);
    }

    public void pridatStatBtnClick(MouseEvent mouseEvent) {
        tableView.setPrefHeight(tableView.getPrefHeight() + CELL_HEIGHT);
        tableVbox.setPrefHeight(tableVbox.getPrefHeight() + CELL_HEIGHT);
        data.add(new StatVzdialenostRow("", ""));
    }

    public void odobratStatBtnClick(MouseEvent mouseEvent) {
        if (tableView.getPrefHeight() > CELL_HEIGHT*3 + 6) {
            tableView.setPrefHeight(tableView.getPrefHeight() - CELL_HEIGHT);
            tableVbox.setPrefHeight(tableVbox.getPrefHeight() - CELL_HEIGHT);
            data.remove(data.size() - 1);
        }
    }

    public void potvrditBtnClick(MouseEvent mouseEvent) {
        Main.zadanieVstupovStage.show();
        Main.statVzdialenostStage.hide();
    }
}
