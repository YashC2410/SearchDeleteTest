package com.readwrite.excel.SampleTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import au.com.bytecode.opencsv.CSVWriter;

public class ReadWriteUtil {

	public void testingReadFile() throws IOException, CsvException {
		FileInputStream file = new FileInputStream(new File("Testing/yash1.xlsx"));
		Workbook workBook = new XSSFWorkbook(file);
		Sheet dataType=workBook.getSheetAt(0);
		Iterator<Row> rowIterator = dataType.iterator();
		 while (rowIterator.hasNext()) {
             Row row = rowIterator.next();
             Iterator<Cell> cellIterator
                 = row.cellIterator();
             while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();
                 switch (cell.getCellType()) {
                 case Cell.CELL_TYPE_NUMERIC:
                	 System.out.print(cell.getNumericCellValue()+'\t');
                	 break;
                 case Cell.CELL_TYPE_STRING:
                	 System.out.print(cell.getStringCellValue()+'\t');
                	 break;
                 }
                 
             }
             System.out.println();
		 }
		 file.close();
		 
		
		
	}
	public void testWriteFile() throws IOException, EncryptedDocumentException, InvalidFormatException {
		 FileInputStream inputStream = new FileInputStream(new File("Testing/yash1.xlsx"));
         Workbook workbook = WorkbookFactory.create(inputStream);
         Sheet sheet = workbook.getSheetAt(0);
         Object[][] data = {{"103","Testing","01/02/2022"}};
         int rowCount = sheet.getLastRowNum();
         
         for (Object[] aBook : data) {
             Row row = sheet.createRow(++rowCount);

             int columnCount = 0;
              
             Cell cell = row.createCell(columnCount);
             cell.setCellValue(rowCount);
              
             for (Object field : aBook) {
                 cell = row.createCell(++columnCount);
                 if (field instanceof String) {
                     cell.setCellValue((String) field);
                 } else if (field instanceof Integer) {
                     cell.setCellValue((Integer) field);
                 }
             }

         }
         inputStream.close();
         FileOutputStream outputStream = new FileOutputStream("Testing/yash1.xlsx");
         workbook.write(outputStream);
         workbook.close();
         outputStream.close();
         
		}
	public void deteletTest() throws IOException {
		FileInputStream file = new FileInputStream(new File("Testing/yash1.xlsx"));
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet dataType=workBook.getSheetAt(0);
		//Row test=null;
		Iterator<Row> rowIterator = dataType.iterator();
		 while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator
                = row.cellIterator();
            if(cellIterator.next()!=null) {
            String a =cellIterator.next().getStringCellValue();
            String b = cellIterator.next().getStringCellValue();
            System.out.println(b+"_"+a);
            }
		 }
		 file.close();
		 XSSFRow test1=dataType.getRow(3);
		 dataType.removeRow(test1);
		 workBook.close();
	}
	}

