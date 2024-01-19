package analystt_Assignment;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2 {

	WebDriver driver;
	
	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");

	}
	
	@Test(priority= 1)
	public void loginProcess()
	{
		WebElement username= driver.findElement(By.id("Email"));
		WebElement password= driver.findElement(By.id("Password"));
		
		username.clear();
		username.sendKeys("admin@yourstore.com");
		
		password.clear();
		password.sendKeys("admin");
	    
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}
	@Test(priority=2)
	public void verifyPagetitle()
	{
		String actualtitle= "Dashboard / nopCommerce administration";
		String expectedtitle=driver.getTitle();
		System.out.println(expectedtitle);
		if(actualtitle.equals(expectedtitle))
		   {
			   Assert.assertTrue(true);
		   }
		   else
		   {
			   Assert.assertTrue(false );
		   }
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}
	
	@Test(priority=3) 
	public void searchCustomer() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//nav[@class='mt-2']/child::ul/child::li[4]/child::a")).click();
		
		driver.findElement(By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")).click();
		
		driver.findElement(By.id("SearchEmail")).sendKeys("abcd@gmail.com");
		
		driver.findElement(By.id("SearchFirstName")).sendKeys("leena");
		
		driver.findElement(By.id("SearchLastName")).sendKeys("nair");
		
		WebElement month=driver.findElement(By.id("SearchMonthOfBirth"));
		WebElement day=driver.findElement(By.id("SearchDayOfBirth"));
		
		Select sc=new Select (month);
		sc.selectByVisibleText("2");
		
		Select sc1=new Select (day);
		sc1.selectByVisibleText("1");
		
		driver.findElement(By.id("search-customers")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(4000);
				
	}
	
	@Test(priority=4)
	public void logout() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(2000);
	}
	
	
	@AfterTest
	public void quitBrowser()
	{
		driver.quit();
	}
	
	


}
