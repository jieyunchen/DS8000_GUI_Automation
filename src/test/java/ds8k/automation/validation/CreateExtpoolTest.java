package ds8k.automation.validation;

import static org.junit.Assert.assertEquals;


import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ds8k.automation.pageobject.GuiHomePage;
import ds8k.automation.pageobject.arrayByPoolPage;
import ds8k.automation.pageobject.createPoolPairPage;
import ds8k.automation.pageobject.loginPage;


public class CreateExtpoolTest {
	private WebDriver driver;
	private GuiHomePage homePage;
	private arrayByPoolPage arrayPage;
	private createPoolPairPage createExtPoolPage;
	private String[] extpoolPairs;
	private String extpoolpairCreateLog;
	private int extpoolId_0;
	private int extpoolId_1;
	private int unassignedMANum_0 = 0;
	private int unassignedMANum_1 = 0;
	
	

	private void NavigateToArrayByPoolPage() {
		//Navigate to ArrayByPool Page
		loginPage my_login = new loginPage(driver);
		homePage = my_login.navigateToHomePage();
		arrayPage = homePage.navigateToArrayByPoolPage();
		
		//Before creating extent pool pair, get unassigned MA Number 
		unassignedMANum_0 = arrayPage.getUnassignedMANum();
		System.out.println("Unassigned MA number before create extent pool pair: " + unassignedMANum_0);
	}
	
	private void CheckExtpoolPairName(){
		String[] extpoolIdStr_0 = extpoolPairs[0].split("_");
		String[] extpoolIdStr_1 = extpoolPairs[1].split("_");
		extpoolId_0 = Integer.parseInt(extpoolIdStr_0[extpoolIdStr_0.length - 1]);
		extpoolId_1 = Integer.parseInt(extpoolIdStr_1[extpoolIdStr_1.length - 1]);
		System.out.println("Extent pool 0: " + extpoolId_0 + "; Extent pool 1: " + extpoolId_1);
		assertEquals(0, extpoolId_0 % 2);
		assertEquals(1, extpoolId_1 % 2);
	}
	
	private void CreateExtPoolAndVerifyResult(){
	    //Create extent pool pair and check if the task completed successfully
		extpoolpairCreateLog = createExtPoolPage.CreateExtPoolPair();
		assertEquals(true, extpoolpairCreateLog.contains("Task completed."));
		assertEquals(true, extpoolpairCreateLog.contains("100%"));
		
		//After creating extent pool pair, get unassigned MA number again and check if "unassignedMANum_1 + 2 == unassignedMANum_0"
		unassignedMANum_1 = arrayPage.getUnassignedMANum();
		System.out.println("Unassigned MA number after create extent pool pair: " + unassignedMANum_1);
		assertEquals(unassignedMANum_0 - 2, unassignedMANum_1);
		
		//Check if the new created extent pools are listed on the "array by pool" page
		assertEquals(true, arrayPage.IsExtpoolListOnPage(extpoolPairs[0]));
		assertEquals(true, arrayPage.IsExtpoolListOnPage(extpoolPairs[1]));
		
		//Refresh the page 
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gem_pods_CapacityStatusPod_0")));
				
		//Check if there is only one MA under new created extent pool 
		arrayPage.clickAllExpendBtn();
		
		assertEquals(1, arrayPage.getMANumberOfExtpool(extpoolPairs[0]));
		assertEquals(1, arrayPage.getMANumberOfExtpool(extpoolPairs[1]));
	}
	
	
	@Before 
	public void setup() throws Exception {
		/*
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 1);
		profile.setPreference("network.proxy.socks", "localhost");
		profile.setPreference("network.proxy.socks_port", 8888);
		driver = new FirefoxDriver(profile); */
		driver = new FirefoxDriver();
		//driver.manage().window().setSize(new Dimension(1200, 1024));
		driver.manage().window().setSize(new Dimension(1920, 1080));
               // driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	@After 
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	
	@Test
	public void create_ckd_pool_pair_verify() throws Exception {
		
		//Navigate to Array By Pool Page
		NavigateToArrayByPoolPage();
				
		//Navigate to create extent pool pair page 
		createExtPoolPage = arrayPage.navigateToCreatePoolPairPage();
		
		//Fill the form to create the CKD extent pool pair and get the extent pool pair
		extpoolPairs  = createExtPoolPage.fillCreateCKDPoolForm();
		System.out.println("Extent pool 0: " + extpoolPairs[0] + "; Extent pool 1: " + extpoolPairs[1]);
		
		//Check assigned extent pool Name is correct
		CheckExtpoolPairName();
		
	    //Create extent pool pair and validate if all the results are expected
		CreateExtPoolAndVerifyResult();
		
     }
     
	
     public void create_fb_pool_pair_verify() throws Exception {
 		//Navigate to Array By Pool Page
 		NavigateToArrayByPoolPage();
 				
 		//Navigate to create extent pool pair page 
 		createExtPoolPage = arrayPage.navigateToCreatePoolPairPage();
 		
 		//Fill the form to create the FB extent pool pair and get the extent pool pair
 		extpoolPairs  = createExtPoolPage.fillCreateFBPoolForm();
 		System.out.println("Extent pool 0: " + extpoolPairs[0] + "; Extent pool 1: " + extpoolPairs[1]);
 		
 		//Check assigned extent pool Name is correct
 		CheckExtpoolPairName();
 		
 	    //Create extent pool pair and validate if all the results are expected
 		CreateExtPoolAndVerifyResult();
		
     }
	
}
