package ds8k.automation.validation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import ds8k.automation.pageobject.GuiHomePage;
import ds8k.automation.pageobject.createHostPage;
import ds8k.automation.pageobject.hostPage;
import ds8k.automation.pageobject.loginPage;
import ds8k.automation.pageobject.volumeByHostPage;
import ds8k.automation.pageobject.volumeMappingPage;

public class volumeMappingTest_ {
	
	private WebDriver driver;
	private GuiHomePage homePage;
	private volumeByHostPage volumeByHostPage;
	private volumeMappingPage volMapPage;
	
	@Before 
	public void setup() throws Exception {
		//FirefoxProfile profile = new FirefoxProfile();
		//profile.setPreference("network.proxy.type", 1);
		//profile.setPreference("network.proxy.socks", "localhost");
		//profile.setPreference("network.proxy.socks_port", 8888);
		//driver = new FirefoxDriver(profile);
		driver = new FirefoxDriver();
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
	public void volume_mapping_to_host() throws Exception {
		String hostName;
		navigateToHostByVolumePage();
		System.out.println("Starting to batch volume mapping to host:" + GetCurrentTime());
		for (int counter = 0; counter < 100; counter++){
			hostName = "cheruby_auto_host_" + Integer.toString(counter);
			volumeByHostPage.mapVolume();
			volMapPage = new volumeMappingPage(driver, hostName, counter);
			volMapPage.assignToHost();
			try {
				Thread.sleep(10000);
			}catch (Exception e){
				e.getMessage();
			}
		}
		System.out.println("End to batch volume mapping to host:" + GetCurrentTime());

	}
	
	
	/*
	public void multiple_volume_mapping_to_host() throws Exception {
		navigateToHostByVolumePage();
		System.out.println("Starting to batch volume mapping to host:" + GetCurrentTime());
		volumeByHostPage.mapMultiVolumes(10);
	}
	*/
	
	
}
