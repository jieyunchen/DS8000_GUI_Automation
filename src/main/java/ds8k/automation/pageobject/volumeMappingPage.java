package ds8k.automation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class volumeMappingPage {
	
	private WebDriver driver;
	private String hostName;
	private int hostCounter;

	public volumeMappingPage(WebDriver driver, String hostName, int hostCounter) {
		this.driver = driver;
		this.hostName = hostName;
		this.hostCounter = hostCounter;
	}
	
	public void assignToHost(){
		String hostNameSelect = ".//*[@id='evo_form_SmartSelect_" + Integer.toString(hostCounter) + "']/tbody/tr/td[2]/input";
		String hostMenuSelect = "evo_form_SmartSelect_" + Integer.toString(hostCounter) + "_menu";
		String mapBtnSelect = "dijit_form_Button_" + Integer.toString(7 + (hostCounter + 1) * 2) + "_label";
		
		System.out.println("hostName: " + hostName);
		System.out.println("hostNameSelect: " + hostNameSelect);
		System.out.println("hostMenuSelect: " + hostMenuSelect);
		System.out.println("mapBtnSelect: " + mapBtnSelect);
		
		driver.findElement(By.xpath(hostNameSelect)).click();
		WebElement table = driver.findElement(By.id(hostMenuSelect));
		List<WebElement> tableCells = table.findElements(By.tagName("tr"));
		for(WebElement cell : tableCells) {
			if (cell.getText().equals(hostName)){
				cell.click();
				break;
			}
		}
		driver.findElement(By.id(mapBtnSelect)).click();
		
		/*
		WebDriverWait wait = new WebDriverWait(driver,120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_Button_0")));	
		driver.findElement(By.id("dijit_form_Button_0")).click();
		*/
	}
}
