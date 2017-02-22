package ds8k.automation.validation;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.util.Date.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import ds8k.automation.pageobject.GuiHomePage;
import ds8k.automation.pageobject.loginPage;
import ds8k.automation.pageobject.hostPage;
import ds8k.automation.pageobject.createHostPage;
import ds8k.automation.pageobject.createClusterPage;

public class CreateHostTest_ {
	
	private WebDriver driver;
	private GuiHomePage homePage;
	private hostPage hostPage;

	
	@Before 
	public void setup() throws Exception {

		
		driver = new FirefoxDriver();
		//driver.manage().window().setSize(new Dimension(1200, 1024));
		driver.manage().window().setSize(new Dimension(1920, 1080));
        // driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	/*
	@After 
	public void tearDown() throws Exception {
		driver.quit();
	}
	*/
	
	
	private void navigateToHostPage() throws Exception {
		loginPage my_login = new loginPage(driver);
		homePage = my_login.navigateToHomePage();
		hostPage = homePage.navigateToHostPage();
	}
	
	
	private String GetCurrentTime() throws Exception {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime()); 
	}
	
	@Test

	
	/*
    //create single host or cluster 
	public void create_host_verify() throws Exception {
		loginPage my_login = new loginPage(driver);
		homePage = my_login.navigateToHomePage();
		hostPage = homePage.navigateToHostPage();
	//	createHostPage createHostPage = hostPage.createHost();
	//	createHostPage.createHost();
		createClusterPage createClusterPage = hostPage.createCluster();
		createClusterPage.createCluster();
     }
     */
     
	 
	
	//batch create clusters
	public void test_create_batch_cluster() throws Exception {
		int i = 0;
		navigateToHostPage();
		System.out.println("Starting to batch cluster:" + GetCurrentTime());
		for (int counter = 0; counter < 100; counter++){
			createClusterPage cluster = hostPage.createCluster();
			cluster.createCluster(counter, i);
			try {
				Thread.sleep(5000);
			}catch (Exception e){
				e.getMessage();
			}
			i++;
		}
		System.out.println("End to batch cluster:" + GetCurrentTime());
	}
	
	
	
	/*
	//batch create hosts
	public void test_create_batch_host() throws Exception {
		navigateToHostPage();
		System.out.println("Starting to batch host:" + GetCurrentTime());
		for (int counter = 0; counter < 100; counter++){
			createHostPage host = hostPage.createHost();
			host.createHost(counter);
			try {
				Thread.sleep(5000);
			}catch (Exception e){
				e.getMessage();
			}
		}
		System.out.println("End to batch host:" + GetCurrentTime());
	}
	*/
	
	
}
