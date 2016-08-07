package ds8k.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class volumePage {
	
	private WebDriver driver;

	public volumePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public createVolumePage navigateTocreateVolumePage(){
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_Button_2_label")));	
		
		driver.findElement(By.id("dijit_form_Button_2_label")).click();
		return new createVolumePage(driver);
	}

}
