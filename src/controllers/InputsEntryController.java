package controllers;

import data.HeaderInputData;
import data.OneStopRow;
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
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import main.Main;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
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
    ScrollPane scrollPane;
    @FXML
    private Label introLabel;
    @FXML
    private TextField startTF;
    @FXML
    private TextField startPlaceTF;
    @FXML
    private TextField timeTF;
    @FXML
    private TextField codeRTTF;
    @FXML
    private TextField codeSTTF;
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
    private ChoiceBox<String> determinedBySupplier;
    @FXML
    private ChoiceBox<String> rowToAddOrDelete;
    @FXML
    private RadioButton rbStart;
    @FXML
    private RadioButton rbSupplier;
    @FXML
    private RadioButton rbStop;

    public InputsEntryController(Main main) throws IOException {
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/zadanieVstupov.fxml"));
        loader.setController(this);

        main.inputsEntryStage.setScene(new Scene(loader.load()));
        main.inputsEntryStage.setTitle("Zadanie vstupov pre grafikon - " + main.tripData.grafikonName);

        setIntroLabel(main.tripData.grafikonName);

        main.inputsEntryStage.widthProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefWidth((Double) newVal - 30));
        main.inputsEntryStage.heightProperty().addListener((obs, oldVal, newVal) -> anchorPane.setPrefHeight((Double) newVal));

        main.inputsEntryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,
                main::closeWindowEvent);
    }

    public void setIntroLabel(String introLabel) {
        this.introLabel.setText(introLabel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCols();
        tableView.setFixedCellSize(30.0);
        main.makeFasterScroll(scrollPane, anchorPane, SCROLL_SPEED);
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

        cofor.setSortable(false);
        name.setSortable(false);
        distance.setSortable(false);
        speed.setSortable(false);
        loadingTime.setSortable(false);
        stopType.setSortable(false);
        loading.setSortable(false);
        ramp.setSortable(false);
        beginOfST.setSortable(false);
        time.setSortable(false);
        freqCofor.setSortable(false);

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

    private void recalculateTableHeights()
    {
        tableView.setPrefHeight(data.size() * CELL_HEIGHT + 67.0);
        tableVbox.setPrefHeight(data.size() * CELL_HEIGHT + 303.0);
    }

    public void addRow(int index) {
        data.add(index+1, new OneStopRow("", "",0,0, 0,
                "","","", new CheckBox(),"",0));
        recalculateTableHeights();
    }

    public void deleteRow(int index) {
        data.remove(index);
        recalculateTableHeights();
    }

    public void addTripLengthBtnClick(MouseEvent event) throws IOException {
        if (stateDistancesController == null) {
            stateDistancesController = new StateDistancesController(main);
        }
        else stateDistancesController.loadData();
        main.stateDistancesStage.show();
        main.inputsEntryStage.hide();
    }

    public void addTechnicalDataBtnClick(MouseEvent event) throws IOException {
        if (technicalDataController == null) {
            technicalDataController = new TechnicalDataController(main);
        }
        else technicalDataController.loadData();
        main.technicalDataStage.show();
        main.inputsEntryStage.hide();
    }

    public void goBackToIntroBtnClick(MouseEvent mouseEvent) {
        main.closeWindowEvent(null);
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
        storeDataToInternalRepresentation();
        if (previewController == null) {
            previewController = new PreviewController(main);
        }
        main.inputsEntryStage.hide();
        main.previewStage.show();
    }

    public void saveBtnClick(MouseEvent mouseEvent) {
        storeDataToInternalRepresentation();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Trip File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MilkRun Trips", "*.milkrun"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showSaveDialog(main.introStage);
        if (selectedFile != null) {
            try {
                main.tripData.save(selectedFile.getCanonicalPath());
            } catch (Exception ex)
            {
                main.showExceptionAlert("Could not save the trip", "Exception when trying to write the trip to file: " + ex.toString());
            }
        }
    }

    public void exportExcelBtnClick(MouseEvent mouseEvent) throws IOException {
        storeDataToInternalRepresentation();
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        Sheet sheet = workbook.createSheet("Meno Harku");
        technicalDataController.writeTechnicalData(workbook, sheet,1, 1);
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
        determinedBySupplier.setItems(listAddedSuppliers(items));
    }

    public void setRowToAddOrDelete(MouseEvent mouseEvent) {
        List<String> items = new ArrayList<>();
        items.add("-");
        rowToAddOrDelete.setItems(listAddedSuppliers(items));
    }

    public ObservableList listAddedSuppliers(List items) {
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

    public String determinedBy() {
        if(rbStart.isSelected()){
            return "Start";
        }
        if(rbStop.isSelected()){
            return "Stop";
        }
        if(rbSupplier.isSelected()) {
            Object value = determinedBySupplier.getValue();
            if (value != null) {
                return value.toString();
            }
        }
        return ""; //bol zakrtnuty dodavatel ale nebol ziaden vybraty
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

    void storeHeaderInputData()
    {
        // TODO: validate
        String detSup;
        String detBy = determinedBy();
        if (detBy.equals("Start") || detBy.equals("Stop")) detSup = null;
        else detSup = detBy;

        main.tripData.headerInputData = new HeaderInputData(
                                            startTF.getText(), startPlaceTF.getText(), timeTF.getText(),
                                            Integer.parseInt(codeRTTF.getText()),
                                            Integer.parseInt(codeSTTF.getText()), detBy, detSup);
    }

    void storeDataToInternalRepresentation()
    {
        List<OneStopRow> rows = new ArrayList<OneStopRow>(tableView.getItems());
        main.tripData.oneStopRowList = rows;
        main.tripData.grafikonName = this.introLabel.getText();
        storeHeaderInputData();
    }

    void loadHeaderInputData()
    {
        startTF.setText(main.tripData.headerInputData.getStart());
        startPlaceTF.setText(main.tripData.headerInputData.getPlaceOfStart());
        timeTF.setText(main.tripData.headerInputData.getTime());
        codeRTTF.setText(Integer.toString(main.tripData.headerInputData.getCodeRT()));
        codeSTTF.setText(Integer.toString(main.tripData.headerInputData.getCodeST()));

        if (main.tripData.headerInputData.getDeterminedBy().equals("Start"))
            rbStart.setSelected(true);
        else if (main.tripData.headerInputData.getDeterminedBy().equals("Stop"))
            rbStop.setSelected(true);
        else {
            rbSupplier.setSelected(true);
            determinedBySupplier.setValue(main.tripData.headerInputData.getDeterminingSupplier());
        }
    }

    private void createMissingControlsInInputData()
    {
        for (OneStopRow r: main.tripData.oneStopRowList)
        {
            if (r.getBeginOfST() == null)
                r.setBeginOfST(new CheckBox());
        }
    }

    void loadDataFromInternalRepresentation()
    {
        loadHeaderInputData();
        createMissingControlsInInputData();
        data = FXCollections.observableArrayList(main.tripData.oneStopRowList);
        tableView.setItems(data);
        if (main.tripData.oneStopRowList.size() == 0)
            addRow(-1);
        recalculateTableHeights();
        introLabel.setText(main.tripData.grafikonName);
    }
}