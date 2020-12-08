package controllers;

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
import javafx.util.StringConverter;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ZadanieVstupovController implements Initializable {
    final Integer CELL_HEIGHT = 30;
    final int SCROLL_SPEED = 6;
    StatVzdialenostController statVzdialenostController = null;
    ObservableList<ZastavkaRow> data = FXCollections.observableArrayList();

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label uvodnyLabel;
    @FXML
    private TableView<ZastavkaRow> tableView;
    @FXML
    private TableColumn<ZastavkaRow, String> cofor;
    @FXML
    private TableColumn<ZastavkaRow, String> meno;
    @FXML
    private TableColumn<ZastavkaRow, Integer> vzdialenost;
    @FXML
    private TableColumn<ZastavkaRow, Integer> rychlost;
    @FXML
    private TableColumn<ZastavkaRow, Integer> dlzkaNakladky;
    @FXML
    private TableColumn<ZastavkaRow, String> typZastavky;
    @FXML
    private TableColumn<ZastavkaRow, String> loading;
    @FXML
    private TableColumn<ZastavkaRow, String> rampa;
    @FXML
    private TableColumn<ZastavkaRow, CheckBox> zaciatokST;
    @FXML
    private TableColumn<ZastavkaRow, String> cas;
    @FXML
    private TableColumn<ZastavkaRow, Integer> freqCofor;
    @FXML
    private VBox tableVbox; // box v ktorom je tabulka a buttony pod nou

    public ZadanieVstupovController(final String grafikonName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/zadanieVstupov.fxml"));
        loader.setController(this);

        Main.zadanieVstupovStage.setScene(new Scene(loader.load()));
        Main.zadanieVstupovStage.setTitle("Zadanie vstupov pre grafikon - " + grafikonName);

        setUvodnyLabelText(grafikonName);

        Main.zadanieVstupovStage.widthProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefWidth((Double) newVal - 30));
        Main.zadanieVstupovStage.heightProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefHeight((Double) newVal));
        // tableView.getItems().add("Ferko");
    }

    public void setUvodnyLabelText(String uvodnyLabel) {
        this.uvodnyLabel.setText(this.uvodnyLabel.getText() + uvodnyLabel);
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
        cofor.setCellValueFactory(new PropertyValueFactory<>("cofor"));
        meno.setCellValueFactory(new PropertyValueFactory<>("meno"));
        vzdialenost.setCellValueFactory(new PropertyValueFactory<>("vzdialenost"));
        rychlost.setCellValueFactory(new PropertyValueFactory<>("rychlost"));
        dlzkaNakladky.setCellValueFactory(new PropertyValueFactory<>("dlzkaNakladky"));
        typZastavky.setCellValueFactory(new PropertyValueFactory<>("typZastavky"));
        loading.setCellValueFactory(new PropertyValueFactory<>("loading"));
        rampa.setCellValueFactory(new PropertyValueFactory<>("rampa"));
        zaciatokST.setCellValueFactory(new PropertyValueFactory<>("zaciatokST"));
        cas.setCellValueFactory(new PropertyValueFactory<>("cas"));
        freqCofor.setCellValueFactory(new PropertyValueFactory<>("freqCofor"));

        editableCols();
    }

    private void editableCols() {
        cofor.setCellFactory(TextFieldTableCell.forTableColumn());
        cofor.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setCofor(e.getNewValue()));

        meno.setCellFactory(TextFieldTableCell.forTableColumn());
        meno.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setMeno(e.getNewValue()));

        vzdialenost.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        vzdialenost.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setVzdialenost(e.getNewValue()));

        rychlost.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        rychlost.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setRychlost(e.getNewValue()));

        dlzkaNakladky.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        dlzkaNakladky.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDlzkaNakladky(e.getNewValue()));

        typZastavky.setCellFactory(TextFieldTableCell.forTableColumn());
        typZastavky.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setTypZastavky(e.getNewValue()));

        loading.setCellFactory(TextFieldTableCell.forTableColumn());
        loading.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setLoading(e.getNewValue()));

        rampa.setCellFactory(TextFieldTableCell.forTableColumn());
        rampa.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setRampa(e.getNewValue()));

//        zaciatokST.setCellFactory(new CheckBoxTableCell());
//        zaciatokST.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setZaciatokST(e.getNewValue()));

        cas.setCellFactory(TextFieldTableCell.forTableColumn());
        cas.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setCas(e.getNewValue()));

        freqCofor.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        freqCofor.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setFreqCofor(e.getNewValue()));
    }

    public void loadData() {
        tableView.setFixedCellSize(30.0);
        data.add(new ZastavkaRow("", "",0,0, 0,
                "","","", new CheckBox(),"",0));
        tableView.setItems(data);
    }

    public void addRowInTable(MouseEvent mouseEvent) {
        tableView.setPrefHeight(tableView.getPrefHeight() + CELL_HEIGHT);
        tableVbox.setPrefHeight(tableVbox.getPrefHeight() + CELL_HEIGHT);
        data.add(new ZastavkaRow("", "",0,0, 0,
                "","","", new CheckBox(),"",0));
    }

    public void deleteRowInTable(MouseEvent mouseEvent) {
        if (tableView.getPrefHeight() > CELL_HEIGHT*3 + 6) {
            tableView.setPrefHeight(tableView.getPrefHeight() - CELL_HEIGHT);
            tableVbox.setPrefHeight(tableVbox.getPrefHeight() - CELL_HEIGHT);
            data.remove(data.size() - 1);
        }
    }

    public void pridatDlzkuTrasyBtnClick(MouseEvent event) throws IOException {
        if (statVzdialenostController == null) {
            statVzdialenostController = new StatVzdialenostController();
        }
        Main.statVzdialenostStage.show();
        Main.zadanieVstupovStage.hide();
    }

    public void goBackToIntroBtnClick(MouseEvent mouseEvent) {
        Main.uvodnyStage.show();
        Main.zadanieVstupovStage.close();
    }
}

class MyStringConverter extends StringConverter<Integer> {
    @Override
    public String toString(Integer integer) {
        return "" + integer;
    }

    @Override
    public Integer fromString(String s) {
        return Integer.parseInt(s);
    }
}
