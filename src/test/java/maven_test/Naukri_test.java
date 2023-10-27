package maven_test;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import maven_page.Naukri;


public class Naukri_test {
WebDriver driver;

@BeforeTest
public void setup()  {
	driver=new ChromeDriver();
}

@BeforeMethod

public void url()  {
	driver.get("https://www.naukri.com/");
}
@Test
public void test() throws  IOException, AWTException  {
	Naukri ob=new Naukri(driver);
	driver.manage().window().maximize();
	ob.titleverification();
	ob.contetverification();
	ob.logodisplay();
	ob.screenshot();
	ob.mousehover();
	ob.handlewindow();
	//driver.navigate().back();
	ob.scroll();
	ob.login();
	ob.upload();
	
}
}
