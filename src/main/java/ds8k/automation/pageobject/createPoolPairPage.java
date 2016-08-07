package ds8k.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createPoolPairPage {
	
	private WebDriver driver;
	
	public createPoolPairPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String[] fillCreateFBPoolForm() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='evo_widget_PresetChooser2_0']/table/tbody/tr/td[1]/fieldset/div/a[1]")));	
		driver.findElement(By.xpath(".//*[@id='evo_widget_PresetChooser2_0']/table/tbody/tr/td[1]/fieldset/div/a[1]")).click();		
		
		driver.findElement(By.id("dijit_form_ValidationTextBox_2")).sendKeys("cheruby_auto");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_NumberSpinner_0")));	
		
		driver.findElement(By.id("dijit_form_NumberSpinner_0")).clear();
		driver.findElement(By.id("dijit_form_NumberSpinner_0")).sendKeys("2");
				
		try {
			Thread.sleep(2000);
		}catch (Exception e){
			e.getMessage();
		}
		
		String extpool1 = driver.findElement(By.xpath("//*[@id='gem_pools_createpool_CreatePoolSummary_0']/div[1]/div/table/tbody[1]/tr[1]/td[2]/div")).getText();
		System.out.println(extpool1);
		
		String extpool2 = driver.findElement(By.xpath(".//*[@id='gem_pools_createpool_CreatePoolSummary_0']/div[2]/div/table/tbody[1]/tr[1]/td[2]/div")).getText();
		System.out.println(extpool2);
		
		return new String[] {extpool1, extpool2};
		
	}


	public String[] fillCreateCKDPoolForm() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='evo_widget_PresetChooser2_0']/table/tbody/tr/td[1]/fieldset/div/a[2]")));	
		driver.findElement(By.xpath(".//*[@id='evo_widget_PresetChooser2_0']/table/tbody/tr/td[1]/fieldset/div/a[2]")).click();
		
		driver.findElement(By.id("dijit_form_ValidationTextBox_3")).sendKeys("cheruby_auto");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dijit_form_NumberSpinner_1")));	
		
		driver.findElement(By.id("dijit_form_NumberSpinner_1")).clear();
		driver.findElement(By.id("dijit_form_NumberSpinner_1")).sendKeys("2");
		
		try {
			Thread.sleep(2000);
		}catch (Exception e){
			e.getMessage();
		}
		
		
		String extpool1 = driver.findElement(By.xpath("//*[@id='gem_pools_createpool_CreatePoolSummary_1']/div[1]/div/table/tbody[1]/tr[1]/td[2]/div")).getText();
		System.out.println(extpool1);
		
		String extpool2 = driver.findElement(By.xpath(".//*[@id='gem_pools_createpool_CreatePoolSummary_1']/div[2]/div/table/tbody[1]/tr[1]/td[2]/div")).getText();
		System.out.println(extpool2);
		
	
		return new String[] {extpool1, extpool2};
		
	}
	
	public String CreateExtPoolPair() {
		
		//click "create" button 
		System.out.println("Button1 text: " + driver.findElement(By.id("dijit_form_Button_8")).getText());
		driver.findElement(By.id("dijit_form_Button_8")).click();
		
		int counter = 0;
		String btnClassAttr;
	
		
		while(true){
			//wait for 15 seconds
			try {
				Thread.sleep(15000);
			}catch (Exception e){
				e.getMessage();
			}
			if (counter == 0){
				driver.findElement(By.xpath(".//*[@id='dijit_TitlePane_0_titleBarNode']/div/span[3]")).click();
				System.out.println("clicked view more details...");
			}
			btnClassAttr = driver.findElement(By.xpath(".//*[@id='evo_dialog_Dialog_0']/div[3]/span[1]")).getAttribute("class");
			System.out.println("counter: " + counter + "; attribute: " + btnClassAttr);
			if (btnClassAttr.contains("dijitButtonDisabled") == false){
				break;
			}
			counter++;
		}

		System.out.println("out of loop");
		String runningResult = driver.findElement(By.xpath(".//*[@id='evo_dialog_Dialog_0']/div[2]/div[1]")).getText();
		System.out.println(runningResult);
		
		//click close button 
		driver.findElement(By.id("dijit_form_Button_0")).click();
		return runningResult;
		
	}
}
