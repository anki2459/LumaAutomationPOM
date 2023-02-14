package com.qa.luma.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/LumaAppTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	
	public static Object[][] getTestData(String sheetName)
	{
		Object [][] data = null;
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		//in 2d array filling starts from 0,0 hence we used 0 in i and j , 
		//but the sheet has data from 1 st row and not 0th hence we used (i+1) from row no
		
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			for(int j =0;j<sheet.getRow(0).getLastCellNum();j++)
			{
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
			
		}
		return data;
		
	}

}
