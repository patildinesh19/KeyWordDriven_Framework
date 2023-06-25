package Operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.LogStatus;

public class Screenshots
{
	public static void capturescreenshot(RemoteWebDriver driver,String sheetname) throws IOException
	{
		try {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String src = source.toString();
		String encodeBase64 = null;
		FileInputStream FileInputStreamReader = null;
		FileInputStreamReader = new FileInputStream(source);
		byte[] bytes = new byte[(int) source.length()];
		FileInputStreamReader.read(bytes);
		encodeBase64 = new String(Base64.encodeBase64(bytes));
		FileInputStreamReader.close();
		
		String AttachScreenShot = "data:image/png;base64, "+encodeBase64;
		
		BaseClass.extent_test.log(LogStatus.INFO, "Screenshot below: "+BaseClass.extent_test.addBase64ScreenShot(AttachScreenShot));
		
		System.out.println("Screenshot taken");
	}catch (IOException e) {
		e.printStackTrace();
	}
			
	}
}
