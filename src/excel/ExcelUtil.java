package excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {
    public void close() {

    }

    public static CellStyle getPreferredCellStyle(Cell cell) {
        // ak sa aplikuje style na riadok v exceli, tak na bunky v tomto riadku, ktore vyplni program, tak na nich sa neaplikuje
        // tento styl, tak aby sa aplikoval styl riadku aj na bunky v tomto riadku
        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle.getIndex() == 0) cellStyle = cell.getRow().getRowStyle();
        if (cellStyle == null) cellStyle = cell.getSheet().getColumnStyle(cell.getColumnIndex());
        if (cellStyle == null) cellStyle = cell.getCellStyle();
        return cellStyle;
    }

    // Resize all columns to fit the content size
    public static void resizeColumns(Sheet sheet, int fromColumn, int toColumn) {
        for(int i = fromColumn; i < toColumn + 1; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public static void createBordersAroundCell(CellStyle cellStyle, BorderStyle borderStyle) {
        cellStyle.setBorderBottom(borderStyle);
        cellStyle.setBorderTop(borderStyle);
        cellStyle.setBorderLeft(borderStyle);
        cellStyle.setBorderRight(borderStyle);
    }

    public static void createBordersAroundCell(Cell cell, BorderStyle borderStyle, Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        ExcelUtil.createBordersAroundCell(cellStyle, BorderStyle.THIN);
        cell.setCellStyle(cellStyle);
    }

    public static void createBordersAroundRegion(CellRangeAddress region, Sheet sheet, BorderStyle borderStyle) {
        RegionUtil.setBorderTop(borderStyle, region, sheet);
        RegionUtil.setBorderBottom(borderStyle, region, sheet);
        RegionUtil.setBorderLeft(borderStyle, region, sheet);
        RegionUtil.setBorderRight(borderStyle, region, sheet);
    }

    public static Font createBoldFont(Workbook workbook) {
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        return boldFont;
    }

    public static void setBackgroundColor(CellStyle cellStyle, IndexedColors indexedColors) {
        cellStyle.setFillForegroundColor(indexedColors.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public static void createExcel(String fileName, Workbook workbook) throws IOException {
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(fileName);
        workbook.write(fileOut);
        fileOut.close();
        // Closing the workbook
        workbook.close();
    }

    public static void aplikujModrePozadieABoldText(CellStyle cellStyle, Workbook workbook) {
        ExcelUtil.setBackgroundColor(cellStyle, IndexedColors.PALE_BLUE);
        cellStyle.setFont(ExcelUtil.createBoldFont(workbook));
    }
}
