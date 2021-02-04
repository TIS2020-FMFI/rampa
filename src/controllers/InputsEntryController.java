package controllers;

import data.AllSuppliers;
import data.OneStopRow;
import data.Supplier;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class InputsEntryController implements Initializable {
    final Integer CELL_HEIGHT = 30;
    final int SCROLL_SPEED = 6;
    StateDistancesController stateDistancesController = null;
    TechnicalDataController technicalDataController = null;
    SupplierDataController supplierDataController = null;
    PreviewController previewController = null;
    ObservableList<OneStopRow> data = FXCollections.observableArrayList();

    private Main main;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label introLabel;
    @FXML
    private TableView<OneStopRow> tableView;
    @FXML
    private TableColumn<OneStopRow, String> cofor;
    @FXML
    private TableColumn<OneStopRow, String> name;
    @FXML
    private TableColumn<OneStopRow, Integer> distance;
    @FXML
    private TableColumn<OneStopRow, Integer> speed;
    @FXML
    private TableColumn<OneStopRow, Integer> loadingTime;
    @FXML
    private TableColumn<OneStopRow, String> stopType;
    @FXML
    private TableColumn<OneStopRow, String> loading;
    @FXML
    private TableColumn<OneStopRow, String> ramp;
    @FXML
    private TableColumn<OneStopRow, CheckBox> beginOfST;
    @FXML
    private TableColumn<OneStopRow, String> time;
    @FXML
    private TableColumn<OneStopRow, Integer> freqCofor;
    @FXML
    private VBox tableVbox; // box v ktorom je tabulka a buttony pod nou
    @FXML
    private ChoiceBox determinedBySupplier;
    @FXML
    private ChoiceBox rowToAddOrDelete;

    public InputsEntryController(Main main, final String grafikonName) throws IOException {
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/zadanieVstupov.fxml"));
        loader.setController(this);

        main.inputsEntryStage.setScene(new Scene(loader.load()));
        main.inputsEntryStage.setTitle("Zadanie vstupov pre grafikon - " + grafikonName);

        setIntroLabel(grafikonName);

        main.inputsEntryStage.widthProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefWidth((Double) newVal - 30));
        main.inputsEntryStage.heightProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefHeight((Double) newVal));
    }

    public void setIntroLabel(String introLabel) {
        this.introLabel.setText(this.introLabel.getText() + introLabel);
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
        cofor.setCellValueFactory(new PropertyValueFactory<>("cofor"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        speed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        loadingTime.setCellValueFactory(new PropertyValueFactory<>("loadingTime"));
        stopType.setCellValueFactory(new PropertyValueFactory<>("stopType"));
        loading.setCellValueFactory(new PropertyValueFactory<>("loading"));
        ramp.setCellValueFactory(new PropertyValueFactory<>("ramp"));
        beginOfST.setCellValueFactory(new PropertyValueFactory<>("beginOfST"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        freqCofor.setCellValueFactory(new PropertyValueFactory<>("freqCofor"));

        editableCols();
    }

    private void editableCols() {
        cofor.setCellFactory(TextFieldTableCell.forTableColumn());
        cofor.setOnEditCommit(e -> {e.getTableView().getItems().get(e.getTablePosition().getRow()).setCofor(e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(dopln(1, e.getNewValue()));
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLoading(dopln(5, e.getNewValue()));
        });

        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue()));

        distance.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        distance.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDistance(e.getNewValue()));

        speed.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        speed.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setSpeed(e.getNewValue()));

        loadingTime.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        loadingTime.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setLoadingTime(e.getNewValue()));

        stopType.setCellFactory(TextFieldTableCell.forTableColumn());
        stopType.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setStopType(e.getNewValue()));

        loading.setCellFactory(TextFieldTableCell.forTableColumn());
        loading.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setLoading(e.getNewValue()));

        ramp.setCellFactory(TextFieldTableCell.forTableColumn());
        ramp.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setRamp(e.getNewValue()));
        
        // treba to zmenit na radiobutton len asi to ten TableView nepodporuje alebo nejako zakazat checkboxu viac checknuti ako jedno
//        zaciatokST.setCellFactory(new CheckBoxTableCell());
//        zaciatokST.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setZaciatokST(e.getNewValue()));

        time.setCellFactory(TextFieldTableCell.forTableColumn());
        time.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setTime(e.getNewValue()));

        freqCofor.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        freqCofor.setOnEditCommit( e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setFreqCofor(e.getNewValue()));
    }

    public void loadData() {
        tableView.setFixedCellSize(30.0);
        data.add(new OneStopRow("", "",0,0, 0,
                "","","", new CheckBox(),"",0));
        tableView.setItems(data);
    }

    public void addRowInTable(MouseEvent mouseEvent) {
        Object value = rowToAddOrDelete.getValue();
        if(value != null){
                if(value.toString().equals("-")){
                    addRow(data.size() - 1);
                }
                else{
                    int index = getIndexOfCoforInTable(value);
                    if(index >= 0){
                        addRow(index);
                    }
                }
            }
        else {
            addRow(data.size() - 1);
            }
        }

    public void deleteRowInTable(MouseEvent mouseEvent) {
        if (tableView.getPrefHeight() > CELL_HEIGHT*3 + 6) {
            Object value = rowToAddOrDelete.getValue();
            if(value != null){
                if(value.toString().equals("-")){
                    deleteRow(data.size() - 1);
                }
                else{
                    int index = getIndexOfCoforInTable(value);
                    if(index >= 0){
                        deleteRow(index);
                    }
                }
            }
            else {
                deleteRow(data.size() - 1);
            }
        }
    }

    public int getIndexOfCoforInTable(Object value) {
        int index = 0;
        TableColumn<OneStopRow, String> column = cofor ;
        for (OneStopRow item : tableView.getItems()) {
            if (value == column.getCellObservableValue(item).getValue()){
                return index;
            }
            index ++;
        }
        return -1;
    }

    public void addRow(int index) {
        data.add(index+1, new OneStopRow("", "",0,0, 0,
                "","","", new CheckBox(),"",0));
        tableView.setPrefHeight(tableView.getPrefHeight() + CELL_HEIGHT);
        tableVbox.setPrefHeight(tableVbox.getPrefHeight() + CELL_HEIGHT);
    }

    public void deleteRow(int index) {
        data.remove(index);
        tableView.setPrefHeight(tableView.getPrefHeight() - CELL_HEIGHT);
        tableVbox.setPrefHeight(tableVbox.getPrefHeight() - CELL_HEIGHT);
    }

    public void addTripLengthBtnClick(MouseEvent event) throws IOException {
        if (stateDistancesController == null) {
            stateDistancesController = new StateDistancesController(main);
        }
        main.stateDistancesStage.show();
        main.inputsEntryStage.hide();
    }

    public void addTechnicalDataBtnClick(MouseEvent event) throws IOException {
        if (technicalDataController == null) {
            technicalDataController = new TechnicalDataController(main);
        }
        main.technicalDataStage.show();
        main.inputsEntryStage.hide();
    }

    public void goBackToIntroBtnClick(MouseEvent mouseEvent) {
        main.introStage.show();
        main.inputsEntryStage.close();
    }

    public void addSupplierDataBtnClick(MouseEvent mouseEvent) throws IOException {
        if (supplierDataController == null) {
            supplierDataController = new SupplierDataController(main);
        }
        supplierDataController.resetInputs();
        main.supplierDataStage.show();
        main.inputsEntryStage.hide();
    }

    public void showPreviewBtnClick(MouseEvent mouseEvent) throws IOException {
        if (previewController == null) {
            previewController = new PreviewController(main);
        }
        main.previewStage.show();
    }

    public void exportExcelBtnClick(MouseEvent mouseEvent) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        Sheet sheet = workbook.createSheet("Meno Harku");
        technicalDataController.writeTechnickeUdaje(workbook, sheet,1, 1);
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

    public void setDeterminedBySupplier(MouseEvent mouseEvent) {
        List<String> items = new ArrayList<>();
        determinedBySupplier.setItems(listAddedSuppliersIn(items));
    }

    public void setRowToAddOrDelete(MouseEvent mouseEvent) {
        List<String> items = new ArrayList<>();
        items.add("-");
        rowToAddOrDelete.setItems(listAddedSuppliersIn(items));
    }

    public ObservableList listAddedSuppliersIn(List items) {
        TableColumn<OneStopRow, String> column = cofor ;
        for (OneStopRow item : tableView.getItems()) {
            items.add(column.getCellObservableValue(item).getValue());
        }
        return FXCollections.observableArrayList(items);
    }

    public String openingHoursIntersection(String cofor) {
        data.Supplier supplier = main.suppliers.getSupplier(cofor);
        List<String> days = new ArrayList<>(Arrays.asList(supplier.getMonday(), supplier.getTuesday(),
                supplier.getWednesday(), supplier.getThursday(), supplier.getFriday(), supplier.getSaturday(),
                supplier.getSunday()));

        LocalTime open = LocalTime.parse("00:00");
        LocalTime close = LocalTime.parse("23:59");
        LocalTime o;
        LocalTime c;

        for (String day : days){
            o = LocalTime.parse(day.split("-")[0]);
            c = LocalTime.parse(day.split("-")[1]);
            if (o.isAfter(open)){
                open = o;
            }
            if (c.isBefore(close)){
                close = c;
            }
        }
    return open +"-"+close;
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


