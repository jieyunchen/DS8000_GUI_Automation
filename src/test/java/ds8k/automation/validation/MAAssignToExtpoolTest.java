package ds8k.automation.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import ds8k.automation.pageobject.loginPage;


public class MAAssignToExtpoolTest {
	
	private WebDriver driver;
	private GuiHomePage homePage;
	private arrayByPoolPage arrayPage;
	private int unassignedMANum_0 = 0;
	private int unassignedMANum_1 = 0;
	private String firtAutoCreateExtpool;
	private String assignedMA;
	private String assignMARunningLog;
	
	
	private void NavigateToArrayByPoolPage() {
		//Navigate to ArrayByPool Page
		loginPage my_login = new loginPage(driver);
		homePage = my_login.navigateToHomePage();
		arrayPage = homePage.navigateToArrayByPoolPage();
		
		//Before creating extent pool pair, get unassigned MA Number 
		unassignedMANum_0 = arrayPage.getUnassignedMANum();
		System.out.println("Unassigned MA number before assign MA to extent pool: " + unassignedMANum_0);
		assertEquals(true, unassignedMANum_0 > 0);
	}
	
	@Before 
	public void setup() throws Exception {
		/*
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 1);
		profile.setPreference("network.proxy.socks", "localhost");
		profile.setPreference("network.proxy.socks_port", 8888);
		driver = new FirefoxDriver(profile);  */
		driver = new FirefoxDriver(); 

		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	@After 
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	
	@Test
	public void assign_one_MA_to_extpool() throws Exception {
		//Navigate to Array By Pool Page
		NavigateToArrayByPoolPage();
		firtAutoCreateExtpool = arrayPage.getFirstAutoExtpool();
		assertNotNull(firtAutoCreateExtpool);
		assignedMA = arrayPage.FillAssignMAForm(firtAutoCreateExtpool);
		assertNotNull(assignedMA);
		
		assignMARunningLog = arrayPage.assignMAToExtPool();
		assertEquals(true, assignMARunningLog.contains("Task completed."));
		assertEquals(true, assignMARunningLog.contains("100%"));
		
		unassignedMANum_1 = arrayPage.getUnassignedMANum();
		assertEquals(unassignedMANum_0 - 1, unassignedMANum_1);
		
		//Refresh the page 
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gem_pods_CapacityStatusPod_0")));
		
		arrayPage.clickAllExpendBtn();
		assertEquals(true, arrayPage.CheckIfMAInExtpool(assignedMA, firtAutoCreateExtpool));
	}
}






