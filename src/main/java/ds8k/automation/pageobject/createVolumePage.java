package ds8k.automation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createVolumePage {
	
	WebDriver driver;

	public createVolumePage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void createFBVolume() {
		//click "FB"
		driver.findElement(By.xpath(".//*[@id='evo_widget_PresetChooser2_0']/table/tbody/tr/td[1]/fieldset/div/a[1]/div[1]")).click();
		
		//click select box of node0 to show all select options 
		driver.findElement(By.xpath(".//*[@id='evo_form_SmartSelect_3']/tbody/tr/td[2]/input")).click();
		
		//select the extent pool that the name of which is start with "cheruby_auto"
		WebElement table = driver.findElement(By.xpath(".//*[@id='evo_form_SmartSelect_3_menu']"));
		List<WebElement> tableCells = table.findElements(By.tagName("tr"));
		System.out.println("Table size: " + tableCells.size());
		for(WebElement cell : tableCells) {
			System.out.println( "Value = " + cell.getText());
			if (cell.getText().startsWith("cheruby_auto") == true){
				cell.click();
				break;
			}
		}
		
		//click select box of node1 to show all select options 
		driver.findElement(By.xpath(".//*[@id='evo_form_SmartSelect_4']/tbody/tr/td[2]/input")).click();
		
		//select the extent pool that the name of which is start with "cheruby_auto"
		table = driver.findElement(By.xpath(".//*[@id='evo_form_SmartSelect_4_menu']"));
		tableCells = table.findElements(By.tagName("tr"));
		System.out.println("Table size: " + tableCells.size());
		for(WebElement cell : tableCells) {
			System.out.println( "Value = " + cell.getText());
			if (cell.getText().startsWith("cheruby_auto") == true){
				cell.click();
				break;
			}
		}
			
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_ValidationTextBox_1")));	
		driver.findElement(By.id("dijit_form_ValidationTextBox_1")).sendKeys("cheruby_fb_auto");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_NumberTextBox_0")));
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("200"); // volume number 
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_NumberTextBox_1")));
		driver.findElement(By.id("dijit_form_NumberTextBox_1")).sendKeys("1"); // volume capacity 
		
		
		
		
		//clickCreateButton();
	}
	
	
	public void createCKDVolume() {
		//click CKD 
		driver.findElement(By.xpath(".//*[@id='evo_widget_PresetChooser2_0']/table/tbody/tr/td[1]/fieldset/div/a[2]/div[1]")).click();
		
		//click select box of node0 to show all select options 
		driver.findElement(By.xpath(".//*[@id='evo_form_SmartSelect_5']/tbody/tr/td[2]/input")).click();
				
		//select the extent pool that the name of which is start with "cheruby_auto"
		WebElement table = driver.findElement(By.xpath(".//*[@id='evo_form_SmartSelect_5_menu']"));
		List<WebElement> tableCells = table.findElements(By.tagName("tr"));
		System.out.println("Table size: " + tableCells.size());
		for(WebElement cell : tableCells) {
			System.out.println( "Value = " + cell.getText());
			if (cell.getText().startsWith("cheruby_auto") == true){
				cell.click();
				break;
			}
		}
				
		//click select box of node1 to show all select options 
		driver.findElement(By.xpath(".//*[@id='evo_form_SmartSelect_6']/tbody/tr/td[2]/input")).click();
				
		//select the extent pool that the name of which is start with "cheruby_auto"
		table = driver.findElement(By.xpath(".//*[@id='evo_form_SmartSelect_6_menu']"));
		tableCells = table.findElements(By.tagName("tr"));
		System.out.println("Table size: " + tableCells.size());
		for(WebElement cell : tableCells) {
			System.out.println( "Value = " + cell.getText());
			if (cell.getText().startsWith("cheruby_auto") == true){
				cell.click();
				break;
			}
		}
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("evo_form_HexNumberTextBox_0")));	
		driver.findElement(By.id("evo_form_HexNumberTextBox_0")).clear();
		driver.findElement(By.id("evo_form_HexNumberTextBox_0")).sendKeys("15"); //lss range
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("evo_form_HexNumberTextBox_1")));
		driver.findElement(By.id("evo_form_HexNumberTextBox_1")).clear();
		driver.findElement(By.id("evo_form_HexNumberTextBox_1")).sendKeys("16"); //lss range 
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_ValidationTextBox_7")));
		driver.findElement(By.id("dijit_form_ValidationTextBox_7")).sendKeys("cheruby_ckd_auto");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_NumberTextBox_12")));
		driver.findElement(By.id("dijit_form_NumberTextBox_12")).sendKeys("10"); // volume number 
		
		clickCreateButton();
	}
	
	private void clickCreateButton() {
		System.out.println("Starting to sleep 2s");
		try {
			Thread.sleep(2000);
		}catch (Exception e){
			e.getMessage();
		}
		System.out.println("End of the sleep");
				
		System.out.println("Button1 text: " + driver.findElement(By.id("dijit_form_Button_7_label")).getText());
		driver.findElement(By.id("dijit_form_Button_7_label")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_Button_0_label")));	
		System.out.println("Button2 text: " + driver.findElement(By.id("dijit_form_Button_0_label")).getText());
		driver.findElement(By.id("dijit_form_Button_0_label")).click();
	}

}
