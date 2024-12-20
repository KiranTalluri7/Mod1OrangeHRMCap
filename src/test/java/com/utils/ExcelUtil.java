package com.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public ExcelUtil(String filepath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filepath);
		workbook =  new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		
	}
	public Object[][] getTestData(){
		int rowCount =sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        
        Object[][] data =new Object[rowCount-1][colCount];
        DataFormatter formatter = new DataFormatter();
        
        for(int i=1;i< rowCount;i++) {
        	for(int j=0;j<colCount;j++) {
                data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
        	}
        }
        return data;
	}
	
        public void closeWorkbook() throws IOException {
        	workbook.close();
        }
        
	}
	
	



