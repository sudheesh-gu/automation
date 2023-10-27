package maven_page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class Naukri {
WebDriver driver;
  By register=By.xpath("//*[@id=\"register_Layer\"]");
  By logo=By.xpath("//*[@id=\"root\"]/div[4]/div[2]/a/img");
  By jobs=By.xpath("//*[@id=\"root\"]/div[4]/div[2]/nav/ul/li[1]/a/div");
  By fresherjob=By.xpath("//*[@id=\"root\"]/div[4]/div[2]/nav/ul/li[1]/div/ul[2]/li[2]/a/div");
  By fresher=By.xpath("//*[@id=\"trending-naukri-wdgt\"]/div/div[2]/a[2]");
  By login=By.xpath("//*[@id=\"login_Layer\"]");
  By email=By.xpath("//*[@id=\"root\"]/div[4]/div[2]/div/div/div[2]/div/form/div[2]/input");
  By pass=By.xpath("//*[@id=\"root\"]/div[4]/div[2]/div/div/div[2]/div/form/div[3]/input");
  By loginbutton=By.xpath("//*[@id=\"root\"]/div[4]/div[2]/div/div/div[2]/div/form/div[6]/button");
  By companies=By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/nav/ul/li[2]/a/div");
  By topcompanies=By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/nav/ul/li[2]/div/ul[2]/li[2]/a/div");
  By completeprofile=By.xpath("/html/body/main/div/div/div/div/div/div[3]/div/div[3]/div[2]/a");
   By upload=By.xpath("//*[@id=\"root\"]/div/div/span/div/div/div/div/div/div[2]/div[1]/div/div/ul/li[2]/a");
  
  public Naukri(WebDriver driver) {
	
	  this.driver=driver;
  }
  
  public void titleverification()  {
	  String title=driver.getTitle();
	  String t="naukri";
	  if(title.equals(t)) {
		  System.out.println("title same");
	  }
	  else {
		  System.out.println("title diff");
	  }
  }
  public void contetverification()  {
	  String cont=driver.getPageSource();
	  if (cont.contains("naukri")) {
		  System.out.println("contains");
	  }
	  else  {
		  System.out.println("not contains");
	  }
  }
  
  public void logodisplay() {
	  WebElement li=driver.findElement(logo);
	  boolean b=li.isDisplayed();
	  if(b)  {
		  System.out.println("logo displayed");
	  }
	  else  {
		  System.out.println("logo not displayed");
	  }
  }
  
  public void screenshot() throws IOException  {
  File f=(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE));
  FileHandler.copy(f,new File("D:\\screenshot\\a.png"));
}
  
  public void mousehover()  {
	  Actions act=new Actions(driver);
	  WebElement ai=driver.findElement(jobs);
	  act.moveToElement(ai);
	  act.perform();
	  driver.findElement(fresherjob).click();
	  driver.navigate().back();
	  
  }
  

  
  public void handlewindow()  {
	  String parentwindow=driver.getWindowHandle();
	  driver.findElement(fresher).click();
	  
	  Set <String> childwindow=driver.getWindowHandles();
	  for(String handle:childwindow)
		    {
		  if(!handle.equalsIgnoreCase(parentwindow)) 
		  {
			  driver.switchTo().window(handle);
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			  Actions a=new Actions(driver);
			  WebElement aii=driver.findElement(companies);
			  a.moveToElement(aii);
			  a.perform();
			  driver.findElement(topcompanies).click();
			  driver.close();
		
			  }
		  driver.switchTo().window(parentwindow);
			
		  }
		 
		  
	  }
	    
  
  public void scroll()
  {
	  JavascriptExecutor js=(JavascriptExecutor) driver;
	  js.executeScript("window.scrollBy(0,500)","");
  }
  
  public void login() throws IOException
  {
	  driver.findElement(login).click();
        File f=new File("C:\\\\Users\\\\Sudeesh\\\\Documents\\\\facebook.xlsx");
 		FileInputStream fi=new FileInputStream(f);
 		XSSFWorkbook wb= new XSSFWorkbook(fi);
 		XSSFSheet sh=wb.getSheet("Sheet1");
 		System.out.println(sh.getLastRowNum());
 		
 		for(int i=1;i<=sh.getLastRowNum();i++)
 		{
 			String username=sh.getRow(i).getCell(0).getStringCellValue();
   			System.out.println( username);
   			String paswd=sh.getRow(i).getCell(1).getStringCellValue();
   			System.out.println(paswd);
   			
   		
   		    driver.findElement(email).sendKeys(username);
   			driver.findElement(pass).sendKeys(paswd);   			
   			
   			driver.findElement(loginbutton).click();
   			
 		}
 			
 			
  }
  public void upload() throws AWTException  
  {
  driver.findElement(completeprofile).click();	  
  driver.findElement(upload).click();
  fileupload("C:\\Users\\Sudeesh\\Downloads\\sudhee resume.pdf");
 	  
  }
  private void fileupload(String m) throws AWTException
  {
	  StringSelection strSelection=new StringSelection(m);
	  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelection, null);
	  Robot robot=new Robot();
	  robot.delay(3000);
	  robot.keyPress(KeyEvent.VK_CONTROL);
	  robot.keyPress(KeyEvent.VK_V);
	  robot.keyRelease(KeyEvent.VK_CONTROL);
	  robot.keyRelease(KeyEvent.VK_V);
	  robot.keyPress(KeyEvent.VK_ENTER);
	  robot.keyRelease(KeyEvent.VK_ENTER);
	  
  }
  
}
