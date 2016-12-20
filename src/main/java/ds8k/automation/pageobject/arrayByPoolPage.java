package ds8k.automation.pageobject;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class arrayByPoolPage {
	
	private WebDriver driver;
	

	public arrayByPoolPage(WebDriver driver) {
		this.driver = driver;
	}
	

	public String[] getAllExtpoolFromPage(){
		String cellXPath; 
		int counter = 1;
		String basePath = ".//*[@id='dojox_grid__TreeGridView_1']/div/div/div/div";
		List<WebElement> tableCells = driver.findElements(By.xpath(basePath));
		String[] extPoolList = new String[tableCells.size()];
		//System.out.println("page row number: " + tableCells.size());
		
		for(WebElement cell : tableCells) {
			cellXPath = basePath + "[" + Integer.toString(counter) + "]/table/tbody/tr/td";
			WebElement rowElement = driver.findElement(By.xpath(cellXPath));
			extPoolList[counter-1] = rowElement.getText();
			//System.out.println("Extent Pool: " + rowElement.getText());
			counter++;
		}
		return extPoolList;
	}
	
	public boolean IsExtpoolListOnPage(String extpoolName) {
		String[] rowList = getAllExtpoolFromPage();
		for (String rowElement : rowList){
			if(rowElement.contains(extpoolName) == true){
				return true;
			}
		}
		return false;
	}
	
	public int getMANumberOfExtpool(String extpoolName) {
		int i = 0;
		int j = 0;
		int counter = 0;
		
		String[] rowList = getAllExtpoolFromPage();
		
		for (i = 0; i < rowList.length; i++) {
			if(rowList[i].contains(extpoolName) == true){
				if (i + 1 == rowList.length) {
					break;
				}
				for(j = i+1; j < rowList.length; j++) {
					if (rowList[j].contains("MA") == true) {
						counter += 1; 
					}
					if (j + 1 == rowList.length) {
						break;
					}
					if (rowList[j+1].contains("MA") == false){
						break;
					}
				}
			}
		}
		return counter; 
	}
		
	public String[] getMAListFromOneExtPool(String extpoolName){
		int i = 0;
		int j = 0;
		int counter = 0;
		String[] MAList = new String[getMANumberOfExtpool(extpoolName)];
		String[] tmpList;
        String[] rowList;
	
		rowList = getAllExtpoolFromPage();
		
		for (i = 0; i < rowList.length; i++) {
			if(rowList[i].contains(extpoolName) == true){
				if (i + 1 == rowList.length) {
					break;
				}
				for(j = i+1; j < rowList.length; j++) {
					if (rowList[j].contains("MA") == true) {
						tmpList = rowList[j].split(" ");
						MAList[counter] = tmpList[0];
						counter += 1; 
					}
					if (j + 1 == rowList.length) {
						break;
					}
					if (rowList[j+1].contains("MA") == false){
						break;
					}
				}
			}
		}
		return MAList; 
	}
	
	public boolean CheckIfMAInExtpool(String assignedMA, String extpoolName) {
		int i = 0;
		int j = 0;
		
		String[] rowList = getAllExtpoolFromPage();
		for (i = 0; i < rowList.length; i++) {
			if(rowList[i].contains(extpoolName) == true){
				if (i + 1 == rowList.length) {
					break;
				}
				for(j = i+1; j < rowList.length; j++) {
					if (rowList[j].contains(assignedMA) == true) {
						return true; 
					}
					if (j + 1 == rowList.length) {
						break;
					}
					if (rowList[j+1].contains("MA") == false){
						break;
					}
				}
			}
		}
		return false;
	}
	
	
	public int getUnassignedMANum() {
		String unassignedMAInfo = driver.findElement(By.xpath(".//*[@id='dojox_grid__TreeGridView_1']/div/div/div/div[1]/table/tbody/tr/td/div/div[2]/div/table/tbody/tr/td[1]/div")).getText();
		System.out.println("unassignedMAInfo: " + unassignedMAInfo);
		int firstPos = unassignedMAInfo.indexOf('(');
		int lastPos = unassignedMAInfo.indexOf(')');
		int unassginedMANum = Integer.parseInt(unassignedMAInfo.substring(firstPos+1, lastPos));
		return unassginedMANum;
	}
	

	public void clickTheFirstExpendBtn() {
		String cellXPath = ".//*[@id='dojox_grid__LazyExpando_0']/div";
		driver.findElement(By.xpath(cellXPath)).click();
		
	}
	
	public void clickAllExpendBtn() {
		String basePath = ".//*[@id='dojox_grid__TreeGridView_1']/div/div/div/div";
		List<WebElement> tableCells = driver.findElements(By.xpath(basePath));
		String cellXPath;

		System.out.println("row number on the page: " + tableCells.size());
		
		for(int i = 0; i < tableCells.size(); i++) {
			if (i == 0) {
				continue;
			}
			cellXPath = ".//*[@id='dojox_grid__LazyExpando_" + Integer.toString(i) + "']/div";
			WebElement expendTextSelect = driver.findElement(By.xpath(cellXPath));
			expendTextSelect.click();
		}
	}
	
	
	public String getFirstAutoExtpool() {
		int i = 0;
		int flag = 0;
		String[] extpoolList = getAllExtpoolFromPage();
		for (i = 0; i < extpoolList.length; i++) {
			if (extpoolList[i].contains("cheruby_auto") == true) {
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			String[] rowStrArray = extpoolList[i].split("\n");
			return rowStrArray[0];
		}
		else {
			return null;
		}
		
	}
	
	public int getExtpoolRowIndex(String extpoolName){
		int i = 0;
		String[] extpoolList = getAllExtpoolFromPage();
		for (i = 0; i < extpoolList.length; i++) {
			if (extpoolList[i].contains(extpoolName) == true) {
				break;
			}
		}
		return i + 1;	
	}
	
	public createPoolPairPage navigateToCreatePoolPairPage() {
		driver.findElement(By.id("dijit_form_Button_4_label")).click();
		return new createPoolPairPage(driver);
	}
	
	
	public String FillAssignMAForm(String extpool) {
		String firstMAXpath;
		String firstMAXpath_1;
		String firstMA; 
		String MAStatus; 
		int i = 2;
		
		String expandBtnXpath = ".//*[@id='dojox_grid__LazyExpando_0']/div";
		driver.findElement(By.xpath(expandBtnXpath)).click();
		
		while(true){
			firstMAXpath = ".//*[@id='dojox_grid__TreeGridView_1']/div/div/div/div[" + i + "]/table/tbody/tr/td";
			firstMAXpath_1 = ".//*[@id='dojox_grid__TreeGridView_1']/div/div/div/div[" + i + "]";
			
			firstMA = driver.findElement(By.xpath(firstMAXpath)).getText();
			MAStatus = driver.findElement(By.xpath(firstMAXpath_1)).getText();
			
			System.out.println("i: " + i + " ; MA: " + firstMA);
			
			if (!firstMA.startsWith("MA")) {
				System.out.println("No MA in DS8K can be assigned");
				return null;
			}
			if (firstMA.startsWith("MA") && MAStatus.contains("Unassigned")){
				break;
			}
			i++;
		}
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(firstMAXpath)));
		action.contextClick(driver.findElement(By.xpath(firstMAXpath))).build().perform();
		//driver.findElement(By.id("evo_widget_MenuItemTooltip_6_text'")).click();
		WebElement assignBtn = driver.findElement(By.id("evo_widget_MenuItemTooltip_8_text"));
		assignBtn.click();
		
		driver.findElement(By.id("evo_form_SmartSelect_2")).click();
		WebElement table = driver.findElement(By.id("evo_form_SmartSelect_2_menu"));
		List<WebElement> tableCells = table.findElements(By.tagName("tr"));
		for(WebElement cell : tableCells) {
			if (cell.getText().startsWith(extpool) == true){
				cell.click();
				break;
			}
		}
		
		driver.findElement(By.id("evo_form_SmartSelect_0")).click();
		table = driver.findElement(By.id("evo_form_SmartSelect_0_menu"));
		tableCells = table.findElements(By.tagName("tr"));
		for(WebElement cell : tableCells) {
			if (cell.getText().startsWith("RAID 5") == true){
				cell.click();
				break;
			}
		}		
		
		String[] MAStrArray = firstMA.split(" ");
		return MAStrArray[0]; 
	}
	
	
	private WebElement GetWarningWindowWebElement() {
		try {
			return driver.findElement(By.id("evo_dialog_Dialog_2_title"));
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	
	public String assignMAToExtPool() {
		//click "assign" button 	
		System.out.println("Button1 text: " + driver.findElement(By.id("dijit_form_Button_8")).getText());
		driver.findElement(By.id("dijit_form_Button_8")).click();
		
		WebElement warningWindowElement = GetWarningWindowWebElement();
		if (warningWindowElement != null) {
			System.out.println(warningWindowElement.getText());
			if (warningWindowElement.getText().equals("Warning")) {
				driver.findElement(By.id("dijit_form_Button_10")).click();
			}
		}
		
		int counter = 0;
		String btnClassAttr;
					
		while(true){
			if (counter == 0){
				driver.findElement(By.xpath(".//*[@id='dijit_TitlePane_0_titleBarNode']/div/span[3]")).click();
				System.out.println("clicked view more details...");
			}

			btnClassAttr = driver.findElement(By.xpath(".//*[@id='evo_dialog_Dialog_0']/div[3]/span[1]")).getAttribute("class");
			System.out.println("counter: " + counter + "; attribute: " + btnClassAttr);
			if (btnClassAttr.contains("dijitButtonDisabled") == false){
				break;
			}
            
			//wait for 5 seconds
            try {
            	Thread.sleep(5000);
             }catch (Exception e){
            	 e.getMessage();
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
	
	public String removeOneExtPool(String extpool){
		int index = getExtpoolRowIndex(extpool);
		String extpoolXpath = ".//*[@id='dojox_grid__TreeGridView_1']/div/div/div/div[" + Integer.toString(index) + "]/table/tbody/tr/td";
		driver.findElement(By.xpath(extpoolXpath)).click();
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(extpoolXpath)));
		action.contextClick(driver.findElement(By.xpath(extpoolXpath))).build().perform();
		
		WebElement deleteBtn = driver.findElement(By.id("evo_widget_MenuItemTooltip_20_text"));
		deleteBtn.click();
		
		WebElement confirmBtn = driver.findElement(By.id("dijit_form_Button_8"));
		confirmBtn.click();
		
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
