package ds8k.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createHostPage {
	
	private WebDriver driver;

	public createHostPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void createHost(int counter) {
		
		String hostNameSelect = "dijit_form_ValidationTextBox_" + Integer.toString(2 + counter);
		String checkboxSelect = "dijit_form_CheckBox_" + Integer.toString(counter);
		String comboBoxSelect = "dijit_form_ComboBox_" + Integer.toString(1 + counter * 2);
		String buttonSelect = "dijit_form_Button_" + Integer.toString(7 + 2 * (counter + 1)) + "_label";
		String HBAPort;
		
		System.out.println("Counter: " + counter);
		System.out.println("hostNameSelect: " + hostNameSelect);
		System.out.println("checkboxSelect: " + checkboxSelect);
		System.out.println("comboBoxSelect: " + comboBoxSelect);
		System.out.println("buttonSelect: " + buttonSelect);
		
		driver.findElement(By.id(hostNameSelect)).sendKeys("cheruby_auto_host_" + Integer.toString(counter));
		
		System.out.println("checkbox: " + driver.findElement(By.id(checkboxSelect)).isSelected());
		
		if (driver.findElement(By.id(checkboxSelect)).isSelected() == false) {
			driver.findElement(By.id(checkboxSelect)).click();
		}
		
		if (Integer.toHexString(counter).length() == 1) {
			HBAPort = "ff:00:00:00:00:00:00:0" + Integer.toHexString(counter);
		}
		else {
			HBAPort = "ff:00:00:00:00:00:00:" + Integer.toHexString(counter);
		}
		
		driver.findElement(By.id(comboBoxSelect)).sendKeys(HBAPort);
		
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(buttonSelect)));	
		
		System.out.println("button: " + driver.findElement(By.id(buttonSelect)).getText());
		driver.findElement(By.id(buttonSelect)).click();	
		
	}
}
