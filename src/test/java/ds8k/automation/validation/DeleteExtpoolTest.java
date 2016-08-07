package ds8k.automation.validation;

import static org.junit.Assert.*;

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

public class DeleteExtpoolTest {
	
	private WebDriver driver;
	private GuiHomePage homePage;
	private arrayByPoolPage arrayPage;
	private int unassignedMANum_0 = 0;
	private int unassignedMANum_1 = 0;
	private String firtAutoCreateExtpool;
	private String runningLog;
	private String[] MAList;
	
	private void NavigateToArrayByPoolPage() {
		//Navigate to ArrayByPool Page
		loginPage my_login = new loginPage(driver);
		homePage = my_login.navigateToHomePage();
		arrayPage = homePage.navigateToArrayByPoolPage();
		
		//Before creating extent pool pair, get unassigned MA Number 
		unassignedMANum_0 = arrayPage.getUnassignedMANum();
		System.out.println("Unassigned MA number before delete extent pool: " + unassignedMANum_0);
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
		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	
	@After 
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	
	@Test
	public void delete_one_extpool() throws Exception {
		//Navigate to Array By Pool Page
		NavigateToArrayByPoolPage();
		
		//Get the extent pool Name 
		firtAutoCreateExtpool = arrayPage.getFirstAutoExtpool();
		assertNotNull(firtAutoCreateExtpool);
		
		//Get MA list from one extent pool 
		arrayPage.clickAllExpendBtn();
		assertEquals(true, arrayPage.getMANumberOfExtpool(firtAutoCreateExtpool) > 0);
		
		MAList = arrayPage.getMAListFromOneExtPool(firtAutoCreateExtpool);
		System.out.println("MA list under extpool " + firtAutoCreateExtpool);
		for (int i = 0; i < MAList.length; i++) {
			System.out.println(MAList[i]);
		}
		
		//Remove one extent pool and check task completed log is expected. 
		runningLog = arrayPage.removeOneExtPool(firtAutoCreateExtpool);
		assertEquals(true, runningLog.contains("Task completed."));
		assertEquals(true, runningLog.contains("100%"));
		
		//Validate if the extent pool can't be shown in grid 
		assertEquals(false, arrayPage.IsExtpoolListOnPage(firtAutoCreateExtpool));
		
		unassignedMANum_1 = arrayPage.getUnassignedMANum();
		assertEquals(unassignedMANum_0 + MAList.length, unassignedMANum_1);
		
		//Refresh the page 
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gem_pods_CapacityStatusPod_0")));
		
		//Validate if MAs previous under the removed extent pool are now in unassigned arrays section 
		arrayPage.clickTheFirstExpendBtn();
		for (int i = 0; i < MAList.length; i++) {
			assertEquals(true, arrayPage.CheckIfMAInExtpool(MAList[i], "Unassigned Arrays"));
		}
	}

}
