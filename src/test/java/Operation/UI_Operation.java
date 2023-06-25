package Operation;

import java.io.IOException;
import java.time.Duration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.tf.excel.Readsheet_task;

public class UI_Operation 
{
	WebDriver driver;
	Wait<WebDriver> wait;
	public static Hashtable<String, String>hp = new Hashtable<String, String>();
	
	
	public UI_Operation(WebDriver driver)
	{
		this.driver=driver;
	wait= new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(500000))
				.pollingEvery(Duration.ofMillis(1))
				.ignoring(NoSuchElementException.class);	
	}
	public void perfrom(String Keyword,String Object_Name,String Object_Type, String Object_value,String value,String P2,String P3) throws Exception
	{
		
		String KEYWORD=Keyword.toUpperCase();
		switch(KEYWORD)
		
		{
		// keyword for Task
		case "OPEN_APPLICATION_AND_LOGIN":
			// perfrom open the application
			String sheet_Name=Keyword;
			String username=Object_Name;
			String password=Object_Type;
			BaseClass.logstart(KEYWORD, Object_Name);
			Readsheet_task.readtask_assheet(sheet_Name,username,password,null,null,null,null);
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;
			
		case "ADD_PRODUCT_AND_VERIFY_COUNT":
			// perfrom open the application
			BaseClass.logstart(KEYWORD, Object_Name);
			String sheet_Name2=Keyword;
			String product_name=Object_Name;
			String countofproduct=Object_Type;
			Readsheet_task.readtask_assheet(sheet_Name2,product_name,countofproduct,null,null,null,null);
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;
		
					
		//Keyword for Action
		case "GOTOURL":
			BaseClass.logstart(KEYWORD, Object_Name);
			driver.get(value);
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;
		case "VERIFYOBJECT":
			BaseClass.logstart(KEYWORD, Object_Name);
			wait.until(ExpectedConditions.elementToBeClickable(this.getobject(Object_Name, Object_Type, Object_value)));
			if(driver.findElement(this.getobject(Object_Name, Object_Type, Object_value)).isDisplayed())
			{
				System.out.println("Object Verify sucessfully"+Object_Name);
			}
			System.out.println("Object not found on page and check the Object"+Object_Name);
			BaseClass.logendformessage("Object not Found for :", Object_Name);
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;
		case "VERIFYCHECKBOXISCHECKED":
			BaseClass.logstart(KEYWORD, Object_Name);
			wait.until(ExpectedConditions.elementToBeClickable(this.getobject(Object_Name, Object_Type, Object_value)));
			if(driver.findElement(this.getobject(Object_Name, Object_Type, Object_value)).isSelected())
			{
				System.out.println("Check box is checked "+Object_Name);
			}
			else
			{
				System.out.println("Checkbox is not checked");
			}
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;


		case "SETTEXT":
			BaseClass.logstart(KEYWORD, Object_Name);
			wait.until(ExpectedConditions.elementToBeClickable(this.getobject(Object_Name, Object_Type, Object_value)));
			driver.findElement(this.getobject(Object_Name, Object_Type, Object_value)).sendKeys(value);
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;
		/*case "CLICK":
			BaseClass.logstart(KEYWORD, Object_Name);
			wait.until(ExpectedConditions.elementToBeClickable(this.getobject(Object_Name, Object_Type, Object_value)));
			driver.findElement(this.getobject(Object_Name, Object_Type, Object_value)).click();
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;*/
		case "DOUBLECLICK":
			BaseClass.logstart(KEYWORD, Object_Name);
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;
		case "SELECT":
			BaseClass.logstart(KEYWORD, Object_Name);
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;
		case "MOUSEOVER":
			Actions action1 = new Actions(driver);
			BaseClass.logstart(KEYWORD, Object_Name);
			wait.until(ExpectedConditions.elementToBeClickable(this.getobject(Object_Name, Object_Type, Object_value)));
			WebElement mouseoverelement = driver.findElement(this.getobject(Object_Name, Object_Type, Object_value));
			//action1.moveToElement(mouseoverelement);
			action1.clickAndHold(mouseoverelement);
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;	
		case "PAGEREFRESH":
			BaseClass.logstart(KEYWORD, Object_Name);
			driver.navigate().refresh();
			BaseClass.logEnd(KEYWORD, Object_Name);			
			break;
		case "CLEANTEXTFIELD":
			BaseClass.logstart(KEYWORD, Object_Name);
			wait.until(ExpectedConditions.elementToBeClickable(this.getobject(Object_Name, Object_Type, Object_value)));
			driver.findElement(this.getobject(Object_Name, Object_Type, Object_value)).clear();
			BaseClass.logEnd(KEYWORD, Object_Name);
			break;
		case "CLICK":
			if(Object_value.contains("$"))
			{
				System.out.println("VALUE = "+value);
				String Replaced_Object_value = Object_value.replace("$VALUE$", value);
				BaseClass.logstart(KEYWORD, Object_Name);
				wait.until(ExpectedConditions.elementToBeClickable(this.getobject(Object_Name, Object_Type, Replaced_Object_value)));
				driver.findElement(this.getobject(Object_Name, Object_Type, Replaced_Object_value)).click();
				BaseClass.logEnd(KEYWORD, Object_Name);
			}
			else
			{
				BaseClass.logstart(KEYWORD, Object_Name);
				wait.until(ExpectedConditions.elementToBeClickable(this.getobject(Object_Name, Object_Type, Object_value)));
				driver.findElement(this.getobject(Object_Name, Object_Type, Object_value)).click();
				BaseClass.logEnd(KEYWORD, Object_Name);
			}
			
			break;
			
		case "GET_VALUE_AND_STORE":
			BaseClass.logstart(KEYWORD, Object_Name);
			wait.until(ExpectedConditions.elementToBeClickable(this.getobject(Object_Name, Object_Type, Object_value)));
			String geted_object_value= driver.findElement(this.getobject(Object_Name, Object_Type, Object_value)).getText();
			String KEY= value;
			hp.put(KEY, geted_object_value);
			BaseClass.logEnd(KEYWORD, geted_object_value);
			break;
		case "MATCH_HASH_TABLE_VALUE_WITH_VAR":
			BaseClass.logstart(KEYWORD, Object_Name);
			String getvalueofvariablefromhastable = hp.get(Object_value);
			if(getvalueofvariablefromhastable.contains(value))
			{
				BaseClass.logendformessage(getvalueofvariablefromhastable+ "is equal with "+value, Object_Name);
				System.out.println("Both values are match");
				
			}
			else
			{
				System.out.println("Vales are not match");
			}

			
			// wait  object keywords
			
		case "WAIT_FOR_LOADER":
			BaseClass.logstart(KEYWORD, Object_Name);
			BaseClass.logEnd(KEYWORD, Object_Name);			
			break;
			
		case "WAIT_2_SECOND":
			BaseClass.logstart(KEYWORD, Object_Name);
			Thread.sleep(2000);
			BaseClass.logEnd(KEYWORD, Object_Name);	
			break;
		case "WAIT_3_SECOND":
			BaseClass.logstart(KEYWORD, Object_Name);
			Thread.sleep(3000);
			BaseClass.logEnd(KEYWORD, Object_Name);	
			break;
		case "WAIT_4_SECOND":
			BaseClass.logstart(KEYWORD, Object_Name);
			Thread.sleep(4000);
			BaseClass.logEnd(KEYWORD, Object_Name);	
			break;
		case "WAIT_5_SECOND":
			BaseClass.logstart(KEYWORD, Object_Name);
			Thread.sleep(5000);
			BaseClass.logEnd(KEYWORD, Object_Name);	
			break;
		case "WAIT_8_SECOND":
			BaseClass.logstart(KEYWORD, Object_Name);
			Thread.sleep(8000);
			BaseClass.logEnd(KEYWORD, Object_Name);	
			break;
		case "WAIT_10_SECOND":
			BaseClass.logstart(KEYWORD, Object_Name);
			Thread.sleep(10000);
			BaseClass.logEnd(KEYWORD, Object_Name);	
			break;
		case "WAIT_15_SECOND":
			BaseClass.logstart(KEYWORD, Object_Name);
			Thread.sleep(15000);
			BaseClass.logEnd(KEYWORD, Object_Name);	
			break;		
		
		}
	}
	
	//navigate to Object
	
	private By getobject(String object_Name, String object_Type, String object_value) throws Exception
	{
		
		// TODO Auto-generated method stub
		if(object_Type.contentEquals("ID"))
		{
			return By.id(object_value);
		}
		else if(object_Type.contentEquals("XPATH"))
		{
			return By.xpath(object_value);
		}
		else if(object_Type.contentEquals("NAME"))
		{
			return By.name(object_value);
		}
		else if(object_Type.contentEquals("CLASSNAME"))
		{
			return By.className(object_value);
		}
		else if(object_Type.contentEquals("CSS"))
		{
			return By.cssSelector(object_value);
		}
		else if(object_Type.contentEquals("LINKTEXT"))
		{
			return By.linkText(object_value);
		}
		{
			throw new Exception("Wrong Object Type");  
		}
				
	} 
}
