package ds8k.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class hostPage {
	private WebDriver driver;

	public hostPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public createHostPage createHost() {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_Button_3_label")));	
		
		driver.findElement(By.id("dijit_form_Button_3_label")).click();
		return new createHostPage(driver);
	}

	public createClusterPage createCluster() {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_Button_2_label")));	
		
		driver.findElement(By.id("dijit_form_Button_2_label")).click();
		return new createClusterPage(driver);
		
	}

}
