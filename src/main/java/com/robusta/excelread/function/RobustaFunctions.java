package com.robusta.excelread.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.robusta.excelread.dao.Excel;

public class RobustaFunctions {

	public String excelOperations(Excel excel)
			 throws Exception{

		if (!excel.action.isEmpty() && !excel.filePath.isEmpty() && excel.rowIndex != null && excel.colIndex != null) {
			String response;
			Workbook workbook = null;
			File file;
			FileOutputStream fileOut;
			FileInputStream fileInputStream;
			OPCPackage oPackage;
			try {
				 file = new ClassPathResource(excel.filePath).getFile();
				 fileInputStream = new FileInputStream(file);
				 oPackage = OPCPackage.open(fileInputStream);
				workbook = WorkbookFactory.create(oPackage);

				Sheet sheet = workbook.getSheetAt(0);
				Row row = sheet.getRow(excel.rowIndex);
				Cell cell = row.getCell(excel.colIndex);

				switch (excel.action.toUpperCase()) {
				case "GETCELLDATA":

					response =  cell.getStringCellValue();
					break;

				case "SETCELLDATA":
					
					cell.setCellValue(excel.getNewValue().toString());	
					fileOut  = new FileOutputStream(file);
					workbook.write(fileOut);
					workbook.close();
					fileOut.close();
					response =  "set new value  :"+ excel.getNewValue()+" to row :" + excel.getRowIndex() + " cell :" + excel.getColIndex() + "";
					break;
				default:
					return "this action is not available";
				}

			} catch (Exception e) {
				throw new Exception(e);
			}finally {
				if(workbook != null)
					workbook.close();
			}

			
			return response;
		}

		return "please enter required parameter";
	}
}
