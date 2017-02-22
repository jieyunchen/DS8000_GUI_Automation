package ds8k.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createClusterPage {
	
	private WebDriver driver;

	public createClusterPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void createCluster(int counter, int i) {
		
		String TextboxSelect = "dijit_form_ValidationTextBox_" + Integer.toString(2 + i);
		String buttonSelect = "dijit_form_Button_" + Integer.toString(7 + 2 * (i + 1)) + "_label";
		
		//System.out.println("Counter: " + counter);
		//System.out.println("TextboxSelect: " + TextboxSelect);
		//System.out.println("buttonSelect: " + buttonSelect);
		
		driver.findElement(By.id(TextboxSelect)).sendKeys("cheruby_auto_cluster_" + Integer.toString(counter));
		
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(buttonSelect)));	
		
		System.out.println("button: " + driver.findElement(By.id(buttonSelect)).getText());
		driver.findElement(By.id(buttonSelect)).click();	
	}
}
