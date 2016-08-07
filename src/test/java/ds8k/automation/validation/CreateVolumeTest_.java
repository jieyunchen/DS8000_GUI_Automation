package ds8k.automation.validation;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import ds8k.automation.pageobject.GuiHomePage;
import ds8k.automation.pageobject.loginPage;
import ds8k.automation.pageobject.volumePage;
import ds8k.automation.pageobject.createVolumePage;

public class CreateVolumeTest_ {
	
	private WebDriver driver;
	private GuiHomePage homePage;
	private volumePage volumePage;
	private createVolumePage createvolPage1;
	private createVolumePage createvolPage2;
	
	@Before 
	public void setup() throws Exception {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 1);
		profile.setPreference("network.proxy.socks", "localhost");
		profile.setPreference("network.proxy.socks_port", 8888);
		driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	/*
	@After 
	public void tearDown() throws Exception {
		driver.quit();
	}
	*/
	
	
	
	@Test
	public void create_volume_verify() throws Exception {
		loginPage my_login = new loginPage(driver);
		homePage = my_login.navigateToHomePage();
		volumePage = homePage.navigateToVolumePage();
		
		//create FB volume
		createvolPage1 = volumePage.navigateTocreateVolumePage();
		createvolPage1.createFBVolume();
		
		/*
		//refresh the page 
		driver.navigate().refresh();
		
		//sleep 2 seconds 
		try {
			Thread.sleep(2000);
		}catch (Exception e){
			e.getMessage();
		}
		
		//create CKD volume 
		createvolPage2 = volumePage.navigateTocreateVolumePage();
		createvolPage2.createCKDVolume();
        */
     
     }

}
