package ds8k.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GuiHomePage {
	
	private WebDriver driver;

	public GuiHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public arrayByPoolPage navigateToArrayByPoolPage() {
		Actions action = new Actions(driver);
		WebElement extpoolIcon = driver.findElement(By.xpath(".//*[@id='evo_widget_TBFisheyeItem_1']/img"));
		action.moveToElement(extpoolIcon).perform();
		driver.findElement(By.xpath(".//*[@id='evo_widget_TBFisheyeItem_1']/div/div/a[1]")).click();
		return new arrayByPoolPage(driver);
	}
	
	public volumePage navigateToVolumePage() {
		Actions action = new Actions(driver);
		WebElement volumeIcon = driver.findElement(By.xpath(".//*[@id='evo_widget_TBFisheyeItem_2']/img"));
		action.moveToElement(volumeIcon).perform();
		driver.findElement(By.xpath(".//*[@id='evo_widget_TBFisheyeItem_2']/div/div/a[1]")).click();
		return new volumePage(driver);
	}
	
	public hostPage navigateToHostPage(){
		Actions action = new Actions(driver);
		WebElement hostPageIcon = driver.findElement(By.xpath(".//*[@id='evo_widget_TBFisheyeItem_3']/img"));
		action.moveToElement(hostPageIcon).perform();
		driver.findElement(By.xpath(".//*[@id='evo_widget_TBFisheyeItem_3']/div/div/a[1]")).click();
		return new hostPage(driver);
	}
	
	public volumeByHostPage navigateToVolumeByHostPage(){
		Actions action = new Actions(driver);
		WebElement hostPageIcon = driver.findElement(By.xpath(".//*[@id='evo_widget_TBFisheyeItem_3']/img"));
		action.moveToElement(hostPageIcon).perform();
		driver.findElement(By.xpath(".//*[@id='evo_widget_TBFisheyeItem_3']/div/div/a[2]")).click();
		return new volumeByHostPage(driver);
	}
}
