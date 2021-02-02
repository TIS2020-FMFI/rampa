package controllers;

import data.ZastavkaRow;
import excel.ExcelUtil;
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

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ZadanieVstupovController implements Initializable {
    final Integer CELL_HEIGHT = 30;
    final int SCROLL_SPEED = 6;
    StatVzdialenostController statVzdialenostController = null;
    TechnickeUdajeController technickeUdajeController = null;
    UdajeDodavatelaController udajeDodavatelaController = null;
    UkazkaController ukazkaController = null;
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
        cofor.setOnEditCommit(e -> {e.getTableView().getItems().get(e.getTablePosition().getRow()).setCofor(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMeno(dopln(1, e.getNewValue()));
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLoading(dopln(5, e.getNewValue()));
        });

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
        
        // treba to zmenit na radiobutton len asi to ten TableView nepodporuje alebo nejako zakazat checkboxu viac checknuti ako jedno
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

    public void pridatTechnickeUdajeBtnClick(MouseEvent event) throws IOException {
        if (technickeUdajeController == null) {
            technickeUdajeController = new TechnickeUdajeController();
        }
        Main.technickeUdajeStage.show();
        Main.zadanieVstupovStage.hide();
    }

    public void goBackToIntroBtnClick(MouseEvent mouseEvent) {
        Main.uvodnyStage.show();
        Main.zadanieVstupovStage.close();
    }

    public void pridatUdajeDodavatelaBtnClick(MouseEvent mouseEvent) throws IOException {
        if (udajeDodavatelaController == null) {
            udajeDodavatelaController = new UdajeDodavatelaController();
        }
        Main.udajeDodavatelaStage.show();
        Main.zadanieVstupovStage.hide();
    }

    public void vygenerovatUkazkuBtnClick(MouseEvent mouseEvent) throws IOException {
        if (ukazkaController == null) {
            ukazkaController = new UkazkaController();
        }
        Main.ukazkaStage.show();
    }

    public void exportExcelBtnClick(MouseEvent mouseEvent) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        Sheet sheet = workbook.createSheet("Meno Harku");
        technickeUdajeController.writeTechnickeUdaje(workbook, sheet,1, 1);
    }
//funkcia dopln - x = ktora informacia sa ma doplnit (1 = supplier, 2 = town, atd oddelene ";"
    //          - cofor - input cofor, najde ho v txt a v danom riadku najde x
    private String dopln(int x, String cofor) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/resources/database/cofors.txt"));
            String line;
            String cele = "";
            while((line = br.readLine())!=null)
            {
                if(cofor.equals(line.split(";")[0].split("#")[1]))
                {

                    return line.split("\\$")[0].split(";")[x].split("#")[1];
                }
            }
        }
        catch (Exception hs) { }
        return "";
    }

    static class MyStringConverter extends StringConverter<Integer> {
        @Override
        public String toString(Integer integer) {
            return "" + integer;
        }

        @Override
        public Integer fromString(String s) {
            return Integer.parseInt(s);
        }
    }
}


