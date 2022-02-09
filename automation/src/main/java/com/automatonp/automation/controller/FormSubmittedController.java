package com.automatonp.automation.controller;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.automatonp.automation.entity.FormEntity;
import com.automatonp.automation.repository.FormRepository;
import com.automatonp.automation.services.HttpClient;

@Controller
public class FormSubmittedController {

	private WebDriver driver;
	@Autowired
	HttpClient service;
	@Autowired
	FormRepository formRepository;
	
	@RequestMapping("/bot")
	@ResponseBody
	public String SubmitForm() throws Exception {
		
			service.getProxy();
		
			System.out.println("Message");
			//	invokeBrowser();
				invokeBrowserFireFox();
			
			
		    String testResult = "LATEST POSTS";
		    return testResult;
		
	}
	
    @PostMapping(path = "/add")
    public @ResponseBody String addForm(@RequestBody FormEntity formentity) throws Exception {

    	service.createPort(formentity);
    //	 deleteProxy(24001);
    // formRepository.save(formentity);
       
        return "User Created";
    }
    
    
public void invokeBrowser() {
	
		try {
		//	Robot robot = new Robot();
			
			String PROXY = "72.80.242.101:8080";  
		    org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();  
		    proxy.setHttpProxy(PROXY)  
		      .setFtpProxy(PROXY)  
		      .setSslProxy(PROXY);  
		    DesiredCapabilities cap = new DesiredCapabilities();  
		    cap.setCapability(CapabilityType.PROXY, proxy);   
		    System.setProperty("webdriver.chrome.driver", "E:\\\\drivers\\\\chromedriver.exe");   
		    WebDriver driver = new ChromeDriver(cap) ;  
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			
		
			driver.get("https://medicare-me.com/");
	
			executor.executeScript("scroll(0,1200)");
			
			driver.findElement(By.name("fname"));
			WebElement fname=driver.findElement(By.name("fname"));
			
			driver.findElement(By.name("lname"));
			WebElement lname=driver.findElement(By.name("lname"));
			
			driver.findElement(By.name("phone"));
			WebElement phoneNumber=driver.findElement(By.name("phone"));
			
			driver.findElement(By.name("city"));
			WebElement city=driver.findElement(By.name("city"));
			
			driver.findElement(By.name("state"));
			WebElement state=driver.findElement(By.name("state"));
			
			driver.findElement(By.name("zip"));
			WebElement zipCode=driver.findElement(By.name("zip"));
		
			WebElement ageMore=driver.findElement(By.id("leadid_tcpa_disclosure"));
			WebElement login=driver.findElement(By.id("submit"));
			ageMore.click();		
		
	
			fname.sendKeys("john");
			lname.sendKeys("wick");
			phoneNumber.sendKeys("7775465485");
			city.sendKeys("new yark");
			state.sendKeys("AK"); 
			zipCode.sendKeys("99501-4567");
		//	login.click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


public void invokeBrowserFireFox() {
	
		try {
		//	Robot robot = new Robot();
			
			System.setProperty("webdriver.gecko.driver", "E:\\drivers\\geckodriver.exe");
			
			Proxy proxy = new Proxy();
			proxy.setHttpProxy("72.80.242.101:8080");
			proxy.setSslProxy("72.80.242.101:8080");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(CapabilityType.PROXY, proxy);
			driver= new FirefoxDriver(cap);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			
			//driver.get("https://www.browserstack.com/");
			driver.manage().window().maximize();
			driver.get("https://medicare-me.com/");
	
			executor.executeScript("scroll(0,1200)");
			
			driver.findElement(By.name("fname"));
			WebElement fname=driver.findElement(By.name("fname"));
			
			driver.findElement(By.name("lname"));
			WebElement lname=driver.findElement(By.name("lname"));
			
			driver.findElement(By.name("phone"));
			WebElement phoneNumber=driver.findElement(By.name("phone"));
			
			driver.findElement(By.name("city"));
			WebElement city=driver.findElement(By.name("city"));
			
			driver.findElement(By.name("state"));
			WebElement state=driver.findElement(By.name("state"));
			
			driver.findElement(By.name("zip"));
			WebElement zipCode=driver.findElement(By.name("zip"));
		
			WebElement ageMore=driver.findElement(By.id("leadid_tcpa_disclosure"));
			WebElement login = driver.findElement(By.id("submit"));
			ageMore.click();		
		
	
			fname.sendKeys("john");
			lname.sendKeys("wick");
			phoneNumber.sendKeys("7775465485");
			city.sendKeys("new yark");
			state.sendKeys("AK"); 
			zipCode.sendKeys("99501-4567");
		//	login.click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void deleteProxy( int port) throws Exception  {
	
	service.deletePort(port);
}
	public void setProxy() {
	
		ChromeOptions option = new ChromeOptions();
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("localhost:5555");
		option.setCapability(CapabilityType.PROXY, proxy);
		
	}
	


}
