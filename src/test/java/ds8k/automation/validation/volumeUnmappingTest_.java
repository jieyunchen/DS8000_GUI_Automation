package ds8k.automation.validation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import ds8k.automation.pageobject.GuiHomePage;
import ds8k.automation.pageobject.loginPage;
import ds8k.automation.pageobject.volumeByHostPage;

public class volumeUnmappingTest_ {
	
	private WebDriver driver;
	private GuiHomePage homePage;
	private volumeByHostPage volumeByHostPage;
	
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
	
	private void navigateToHostByVolumePage() throws Exception {
		loginPage my_login = new loginPage(driver);
		homePage = my_login.navigateToHomePage();
		volumeByHostPage = homePage.navigateToVolumeByHostPage();
	}
	
	private String GetCurrentTime() throws Exception {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime()); 
	}
	
	@Test
	public void unmapping_all_volume_from_one_cluster() throws Exception {
		navigateToHostByVolumePage();
		
		System.out.println("Starting to batch volume unmapping to host:" + GetCurrentTime());
		volumeByHostPage.unmapAllVolumeFromOneCluster("cheruby_auto_cluster_2");
		System.out.println("End of batch volume unmapping to host:" + GetCurrentTime());
	}
}
