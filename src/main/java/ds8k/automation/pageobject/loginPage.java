package ds8k.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class loginPage {
	private WebDriver driver;
	private String submitBtnXpath = ".//*[@id='form']/div[1]/div[3]/table/tbody/tr[3]/td[2]/nobr/a";
	private String userName = "admin";
	private String password = "passw0rd";
	private String loginURL = "https://guifvth1.tuc.stglabs.ibm.com:8452/DS8000/login";
	//private String loginURL = "https://guifvth2.tuc.stglabs.ibm.com:8452/DS8000/login";
	
	public loginPage(WebDriver driver) {
		this.driver = driver;
		driver.get(loginURL);
		System.out.println(driver.getTitle());
		
		if (!driver.getTitle().equals("guifvt - Log in")) {
			throw new WrongPageException("Incorrect Page of guifvt");
		}
		
	}
	
	public GuiHomePage navigateToHomePage() {
		//input user name and password
		driver.findElement(By.id("user")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		try {
			Thread.sleep(2000);
		}catch (Exception e){
			e.getMessage();
		}
		driver.findElement(By.xpath(submitBtnXpath)).click();
		
		//Wait until GUI home page presents
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gem_pods_CapacityStatusPod_0")));
		
		
		if (!driver.getTitle().equals("guifvt - System")) {
			throw new WrongPageException("Incorrect GUI Home Page");
		}
		
		return new GuiHomePage(driver);
	}
}


