package com.robusta.excelread.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.robusta.excelread.dao.Excel;

public class RobustaFunctions {

    public String excelOperations(Excel excel)
            throws Exception {

        if (!excel.action.isEmpty() && !excel.filePath.isEmpty() && excel.rowIndex != null && excel.colIndex != null) {

            String response;
            Workbook workbook = null;
            File file = null;
            FileOutputStream fileOut = null;
            FileInputStream fileInputStream;
            OPCPackage oPackage;

            try {
                file = new File(excel.filePath);
                fileInputStream = new FileInputStream(file);
                oPackage = OPCPackage.open(fileInputStream);
                workbook = WorkbookFactory.create(oPackage);

                Sheet sheet = workbook.getSheetAt(0);
                Row row = sheet.getRow(excel.rowIndex);
                Cell cell = null;


                switch (excel.action.toUpperCase()) {
                    case "GETCELLDATA":
                        if(row == null)
                            throw new Exception("invalid rows");

                        cell = row.getCell(excel.colIndex);

                        if(cell == null)
                            throw new Exception("invalid cells");

                        response = cell.getStringCellValue();
                        break;
                    case "SETCELLDATA":

                        if (row == null)
                            row = sheet.createRow(excel.rowIndex);

                        cell = row.getCell(excel.colIndex);

                        if(cell == null)
                            cell = row.createCell(excel.colIndex);

                        cell.setCellValue(excel.getNewValue().toString());
                        fileInputStream.close();
                        fileOut = new FileOutputStream(file);
                        workbook.write(fileOut);
                        workbook.close();
                        fileOut.close();
                        response = "set new value  :" + excel.getNewValue() + " to row :" + excel.getRowIndex() + " cell :" + excel.getColIndex() + "";
                        break;
                    default:
                        throw new Exception("This action is not available");
                }
            } catch (Exception e) {
                if(workbook != null)
                    workbook.close();
                if(fileOut != null)
                    fileOut.close();
                throw e;
            }
            return response;
        }
        throw new Exception("Please enter required parameter");
    }
}