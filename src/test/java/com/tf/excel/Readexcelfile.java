package com.tf.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import config.Readconfigfile;

public class Readexcelfile
{
	@DataProvider(name="readtestsuite")
	public static Object[][] readexcelfile_TestSuite() throws IOException
	{	
		Object[][] obj1=null;
		Readconfigfile data= new Readconfigfile();
		//create object for open file
		
		File file = new File(System.getProperty("user.dir")+"\\"+data.getexcelfilename());
		
		//create object for to fileInputStream to read the excel file
		FileInputStream fp= new FileInputStream(file);
		
		//create object foe xlsx woorkbook
		XSSFWorkbook workbook = new XSSFWorkbook(fp); 
		
		//read sheet inside the workbook by its name
		XSSFSheet sheet=workbook.getSheet(data.getexecutionsheetname());
			
		//find the number of row in sheet
		int firstrwonnumber=sheet.getFirstRowNum();
		int lastrownumber=sheet.getLastRowNum();
		int rowcount=lastrownumber-firstrwonnumber;
		
		//define object for number of row and number of column		
		obj1=new Object[rowcount][3];
		
		//iterate row 
		for(int i=0;i<rowcount;i++)
		{
			//skip first row and start with second one
			Row row=sheet.getRow(i+1);
			
			//iterate the column
			for(int j=0;j<row.getLastCellNum();j++)
			{
				//get the cell value
				obj1[i][j]=row.getCell(j);
				//System.out.println(obj1[i][j]);
			}
		}
		System.out.println("Execution sheet read sucessfully");
		
		//return complete execution sheet
		return obj1;
			
	}
	
}
