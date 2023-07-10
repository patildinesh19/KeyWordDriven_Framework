package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Readconfigfile
{
	private InputStream stream=null;
	private Properties pr=null;
	
	public Readconfigfile()
	{
		try {
				pr=new Properties();			
				stream=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
				//stream=new FileInputStream(new File(System.getProperty(("user.dir")+"/config.properties")));
				try {
					pr.load(stream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getexcelfilename()
	{
		return pr.getProperty("excelfilename");
		
	}
	public String  getexecutionsheetname()
	{
		return pr.getProperty("execute_suite_Sheet_Name");
	}
	
	public String getbrowsername()
	{
		return pr.getProperty("browser");
	}
	public String getSuiteName()
	{
		return pr.getProperty("Suite_Name");
	}
	
}
