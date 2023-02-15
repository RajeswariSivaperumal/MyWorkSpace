package hitachi.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import hitachi.utils.CommonLibrary;

public class Hooks {
	WebDriver driver;	
	
	@BeforeMethod
	public void launchURL() throws IOException
	{
		System.setProperty("webdriver.chrome.driver","./ChromeDriver/chromedriver.exe");
    	driver=new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30));
    	driver.get(CommonLibrary.getProperty("url"));  	
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();	
	}

}
