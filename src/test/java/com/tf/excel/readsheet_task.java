package com.tf.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import Operation.BaseClass;
import Operation.UI_Operation;
import config.readconfigfile;

public class readsheet_task
{
	public static RemoteWebDriver driver=null;
	public static String testcase_name_for_fail_scenario;
	
	BaseClass baseclass1 = new BaseClass();
	public static String test_case_Name, Keyword,object_Name,object_tpye,oject_Value,value,extra1,extra2;
	@SuppressWarnings("deprecation")
	public static void readtask_assheet(String Sheet_name,String p1,String p2,String p3,String p4,String p5,String p6) throws IOException
	{
		testcase_name_for_fail_scenario = Sheet_name;
		try {
		System.out.println("Start the test case execution for : "+Sheet_name);
		
		readconfigfile data= new readconfigfile();
		//create object for open file
		
		File file = new File(System.getProperty("user.dir")+"\\"+data.getexcelfilename());
		System.out.println("File open");
		
		//create object for to fileInputStream to read the excel file
		FileInputStream fp= new FileInputStream(file);
		
		//create object foe xlsx woorkbook
		XSSFWorkbook workbook = new XSSFWorkbook(fp); 
		
		//read sheet inside the workbook by its name
		XSSFSheet sheet=workbook.getSheet(Sheet_name);
		if(sheet != null)
		{
			System.out.println("Sheet open");	
			
			//find the number of row in sheet
			int firstrwonnumber=sheet.getFirstRowNum();
			int lastrownumber=sheet.getLastRowNum();
			int rowcount=lastrownumber-firstrwonnumber;
			//below for loop is read Keyword, object name, object type, object value and 3 parameters from sheet
			for(int i=0;i<rowcount;i++)
			{
				System.out.println("Read test case step by step and start step execution");
				//skip first row and start with second one
				Row row=sheet.getRow(i+1);
				row.getCell(0).setCellType(CellType.STRING);
				test_case_Name=row.getCell(0).getStringCellValue();
				row.getCell(1).setCellType(CellType.STRING);
				 Keyword=row.getCell(1).getStringCellValue();
				row.getCell(2).setCellType(CellType.STRING);
				object_Name=row.getCell(2).getStringCellValue();
				row.getCell(3).setCellType(CellType.STRING);
				object_tpye=row.getCell(3).getStringCellValue();
				row.getCell(4).setCellType(CellType.STRING);
				oject_Value=row.getCell(4).getStringCellValue();
				row.getCell(5).setCellType(CellType.STRING);
				value=row.getCell(5).getStringCellValue();
				row.getCell(6).setCellType(CellType.STRING);
				extra1=row.getCell(6).getStringCellValue();		
				row.getCell(7).setCellType(CellType.STRING);
				extra2=row.getCell(7).getStringCellValue();		
				
				//below switch condition is for read parameter from actual step from sheet
				String choice=row.getCell(5).getStringCellValue();
				switch(choice)
				{
				case "p1":
					value=p1;
					break;
				case "p2":
					value=p2;
					break;
				case "p3":
					value=p3;
					break;
				case "p4":
					value=p4;
					break;
				case "p5":
					value=p5;
					break;
				case "p6":
					value=p6;
					break;					
				}
				
				if(Keyword.contains("BR_LAUNCH"))
				{
					BaseClass.lunchbrowser();
				}
				else
				{
				transaction(BaseClass.driver);				
				}
			}
			System.out.println(" test case execution done : "+Sheet_name);
			BaseClass.extent_test.log(LogStatus.INFO, "No Test Step for execution" +Sheet_name);
		}
		}
		catch (Exception e) 
		{
			BaseClass.extent_test.log(LogStatus.ERROR,
					"Exception while perfotming step: " +object_Name + " - "+e.getMessage());
			System.out.println("Excpetion while performing Steps: " +object_Name + " - "+e.getMessage());
			Assert.fail();
		}
			
	}

	public static void transaction(RemoteWebDriver driver) throws IOException
	{
		UI_Operation operation= new UI_Operation(driver);
		try {
			operation.perfrom(Keyword, object_Name, object_tpye, oject_Value, value, extra1, extra2);
		} catch (Exception e) {
			BaseClass.extent_test.log(LogStatus.ERROR,
					"Exception while perfotming step: " +object_Name + " - "+e.getMessage());
			System.out.println("Excpetion while performing Steps: " +object_Name + " - "+e.getMessage());
			Assert.fail();
		}				
	}
}
