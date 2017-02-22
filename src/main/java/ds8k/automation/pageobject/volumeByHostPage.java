package ds8k.automation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class volumeByHostPage {
	
	private WebDriver driver;

	public volumeByHostPage(WebDriver driver) {
		this.driver = driver;
	}
	

	private void clickExpendBtn() {
		WebElement exptendTextSelect = driver.findElement(By.xpath(".//*[@id='dojox_grid__LazyExpando_0']/div/div"));
		String expendText = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",exptendTextSelect);
	
	    System.out.println("expendText: " + expendText);
	    if (expendText.equals("+")){
	    	driver.findElement(By.id("dojox_grid__LazyExpando_0")).click();
	    }
	}
	
		
	public void unmapAllVolumeFromOneCluster(String clusterName){
		int counter = 1;
		String cellXPath; 
		String basePath = ".//*[@id='dojox_grid__TreeGridView_1']/div/div/div/div";
		Actions action = new Actions(driver);
		
		List<WebElement> tableCells = driver.findElements(By.xpath(basePath));
		
		for(WebElement cell : tableCells) {
			cellXPath = basePath + "[" + Integer.toString(counter) + "]/table/tbody/tr/td[1]/div/div[2]/div";
			WebElement cluster = driver.findElement(By.xpath(cellXPath));
			if (cluster.getText().equals(clusterName)){
				action.moveToElement(cluster);
				action.contextClick(cluster).build().perform();
				WebElement unmapAllVolBtn = driver.findElement(By.id("evo_widget_MenuItemTooltip_22"));
				System.out.println("unmapAllVolBtn Text: " + unmapAllVolBtn.getText());
				if (unmapAllVolBtn.getAttribute("class").contains("dijitMenuItemDisabled") == true){
					System.out.println("unmap All volumes button is disabled");
				}
				else {
					unmapAllVolBtn.click();
				}
				break;
			}
			counter++;
		}
		System.out.println("counter: " + counter);
	}
		
		
	
	public void mapVolume() {
		int counter = 1;
		String cellXPath; 
		String basePath = ".//*[@id='dojox_grid__TreeGridView_1']/div/div/div/div";
		
		clickExpendBtn();
		List<WebElement> tableCells = driver.findElements(By.xpath(basePath));

		for(WebElement cell : tableCells) {
			System.out.println( "Value = " + cell.getText());
			if (cell.getText().startsWith("cheruby_fb_auto") == true){
				cellXPath = basePath + "[" + Integer.toString(counter) + "]/table/tbody/tr/td[1]/div/div[2]/div";
				System.out.println("cell XPath: " + cellXPath);
				Actions action = new Actions(driver);
				WebElement host = driver.findElement(By.xpath(cellXPath));
				action.moveToElement(host);
				action.contextClick(host).build().perform();
				driver.findElement(By.id("evo_widget_MenuItemTooltip_34_text")).click();
				break;
			}
			counter++;

		}
	}

	public void mapMultiVolumes(int volNumber) {
		String basePath = ".//*[@id='dojox_grid__TreeGridView_1']/div/div/div/div";
		int counter = 1;
		String firstCellPath;
		String lastCellPath;
		
		clickExpendBtn();
		
		try {
			Thread.sleep(2000);
		}catch (Exception e){
			e.getMessage();
		}
		
		
		List<WebElement> tableCells = driver.findElements(By.xpath(basePath));
		
		for (WebElement cell : tableCells) {
			if (cell.getText().startsWith("cheruby_fb_auto") == true){
				break;
			}
			counter++;
		}
		firstCellPath = basePath + "[" + Integer.toString(counter) + "]/table/tbody/tr/td[1]/div/div[2]/div";
		lastCellPath  = basePath + "[" + Integer.toString(counter + volNumber - 1) + "]/table/tbody/tr/td[1]/div/div[2]/div";
		WebElement firstCell = driver.findElement(By.xpath(firstCellPath));
		WebElement lastCell = driver.findElement(By.xpath(lastCellPath));
		System.out.println("firstCellPath: " + firstCellPath);
		System.out.println("lastCellPath: " + lastCellPath);
		Actions action = new Actions(driver);
		action.click(firstCell).keyDown(Keys.SHIFT).click(lastCell).keyUp(Keys.SHIFT).perform(); 
		action.moveToElement(lastCell);
		action.contextClick(lastCell).build().perform();
	 
	}

}
