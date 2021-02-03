package controllers;

import excel.ExcelUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.Main;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TechnicalDataController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox tableVbox;
    @FXML
    private Label appDateLb;
    @FXML
    private TextField appDateInput;
    @FXML
    private Label flowLb;
    @FXML
    private TextField flowInput;
    @FXML
    private Label heightGoodsLb;
    @FXML
    private TextField heightGoodsInput;
    @FXML
    private Label heightTruckLb;
    @FXML
    private TextField heightTruckInput;
    @FXML
    private Label length1Lb;
    @FXML
    private TextField length1Input;
    @FXML
    private Label length2Lb;
    @FXML
    private TextField length2Input;
    @FXML
    private Label exportLb;
    @FXML
    private TextField exportInput;
    @FXML
    private Label importLb;
    @FXML
    private TextField importInput;

    public List<Label> getLabels() {
        return labels;
    }

    public List<TextField> getInputs() {
        return inputs;
    }

    private List<Label> labels;
    private List<TextField> inputs;

    Main main;

    public TechnicalDataController(Main main) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/technickeUdaje.fxml"));
        loader.setController(this);

        main.technicalDataStage.setScene(new Scene(loader.load()));
    }

    public void potvrditBtnClick(MouseEvent mouseEvent) {
        main.inputsEntryStage.show();
        main.technicalDataStage.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labels = new ArrayList<>(
                Arrays.asList(appDateLb, flowLb, exportLb, heightGoodsLb, importLb, heightTruckLb, length1Lb, length2Lb));

        inputs = new ArrayList<>(
                Arrays.asList(appDateInput, flowInput, exportInput, heightGoodsInput, importInput, heightTruckInput,
                              length1Input, length2Input));
    }

    // vyexportuj tabulku technickych udajov
    public void writeTechnickeUdaje(Workbook workbook, Sheet sheet, int fromRow, int fromColumn) throws IOException {
        final int COLUMN_HEADER_SPAN = 4;  // cez kolko stlpcov sa ma rozprestierat uvodny riadok "TECHNICAL CHARACTERISTICS OF THE FLOW"
        CellRangeAddress region = new CellRangeAddress(fromRow, fromRow, fromColumn, fromColumn + COLUMN_HEADER_SPAN);
        sheet.addMergedRegion(region);

        /* Vytvor header row, ten s napisom "TECHNICAL CHARACTERISTICS OF THE FLOW" */
        Row headerRow = sheet.createRow(fromRow);
        //--------- vytvor style pre headerRow ----------------
        Font boldFont = ExcelUtil.createBoldFont(workbook);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(boldFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerRow.setRowStyle(headerCellStyle);
        // ----------------------------------------------------

        Cell cell = CellUtil.createCell(headerRow, fromColumn, "TECHNICAL CHARACTERISTICS OF THE FLOW");
        cell.setCellStyle(ExcelUtil.getPreferredCellStyle(cell));
        ExcelUtil.createBordersAroundRegion(region, sheet, BorderStyle.THICK);

        // ---------- zapis labels a inputs, ziskane z obrazovky "technicke udaje" do excelu ---------------------------
        final int SPACE_HEADER_ROW = 1; // medzera medzi header row a zvysnymi riadkami
        int rowNum = fromRow + SPACE_HEADER_ROW + 1;
        Row appDateAndFlowRow = sheet.createRow(fromRow + SPACE_HEADER_ROW + 1);
        createAppDate(appDateAndFlowRow, fromColumn, workbook);
        createFlow(appDateAndFlowRow, fromColumn + 3, workbook);

        rowNum += SPACE_HEADER_ROW + 1;
        Row exportGoodsRow = sheet.createRow(rowNum);
        createRestRows(exportGoodsRow, fromColumn, workbook, 2);
        createRestRows(exportGoodsRow, fromColumn + 3, workbook, 3);

        rowNum += 1;
        Row importHeightRow = sheet.createRow(rowNum);
        createRestRows(importHeightRow, fromColumn, workbook, 4);
        createRestRows(importHeightRow, fromColumn + 3, workbook, 5);

        rowNum += 1;
        Row length1Row = sheet.createRow(rowNum);
        createRestRows(length1Row, fromColumn + 3, workbook, 6);

        rowNum += 1;
        Row length2Row = sheet.createRow(rowNum);
        createRestRows(length2Row, fromColumn + 3, workbook, 7);

        ExcelUtil.resizeColumns(sheet, fromColumn, fromColumn + 19);
        ExcelUtil.createExcel("grafikon.xlsx", workbook);
    }

    public void createAppDate(Row row, int fromColumn, Workbook workbook) {
        Cell cellLabel = CellUtil.createCell(row, fromColumn, labels.get(0).getText());
        Cell cellInput = CellUtil.createCell(row, fromColumn + 1, inputs.get(0).getText());
        appDateAndFlowStyle(workbook, cellLabel, cellInput);
    }

    private void createFlow(Row row, int fromColumn, Workbook workbook) {
        Cell cellLabel = CellUtil.createCell(row, fromColumn, labels.get(1).getText());
        Cell cellInput = CellUtil.createCell(row, fromColumn+ 1, inputs.get(1).getText());
        appDateAndFlowStyle(workbook, cellLabel, cellInput);
    }

    private void appDateAndFlowStyle(Workbook workbook, Cell cellLabel, Cell cellInput) {
        CellStyle cellStyleLb = workbook.createCellStyle();
        ExcelUtil.aplikujModrePozadieABoldText(cellStyleLb, workbook);
        cellLabel.setCellStyle(cellStyleLb);

        CellStyle cellStyleInput = workbook.createCellStyle();
        ExcelUtil.aplikujModrePozadieABoldText(cellStyleInput, workbook);
        ExcelUtil.createBordersAroundCell(cellStyleInput, BorderStyle.THIN);
        cellInput.setCellStyle(cellStyleInput);

        CellUtil.setAlignment(cellInput, HorizontalAlignment.CENTER);
    }

    public void createRestRows(Row row, int fromColumn, Workbook workbook, int i) {
        Cell cellLabel = CellUtil.createCell(row, fromColumn, labels.get(i).getText());
        Cell cellInput = CellUtil.createCell(row, fromColumn + 1, inputs.get(i).getText());
        ExcelUtil.createBordersAroundCell(cellLabel, BorderStyle.THIN, workbook);
        ExcelUtil.createBordersAroundCell(cellInput, BorderStyle.THIN, workbook);
        CellUtil.setAlignment(cellLabel, HorizontalAlignment.CENTER);
        CellUtil.setAlignment(cellInput, HorizontalAlignment.CENTER);
    }
}

