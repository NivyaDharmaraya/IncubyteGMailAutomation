package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

//import io.cucumber.core.cli.Main;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class GmailStepDefinitions {
	
	// Hooks Opening and Closing browser
	public static WebDriver driver;

	    @Before
	    public void BeforeExecution() 
	    {
		    System.setProperty("webdriver.chrome.driver","./BrowserUtils/chromedriver104.exe");
    	    driver = new ChromeDriver();
		    System.out.println("Executing before hook");
	    }
	
	    @After
		public static void AfterExecution() throws InterruptedException
		{
			System.out.println("Executing After Hook, Execution Completed");
			Thread.sleep(3000);
			driver.close();
		}
	    
	  // TestCase1 : This is postivie login testcase :  Check if user is able to login and send email from gmail 
	    @Given("^The user has launched gmail application in the browser$")
	    public void the_user_has_launched_gmail_application_in_the_browser() throws Throwable 
	    {
	    	String baseURL = "https://accounts.google.com/signin/v2/identifier?service=mail&passive=1209600&osid=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&emr=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";		   
	    	driver.get(baseURL);
		    driver.manage().window().maximize(); 
	    }

	    
	 /*   @When("^The user enters valid (.+) and (.+)$")
	    public void the_user_enters_valid_and(String emailid, String password) throws Throwable 
	    {
	    	WebElement UserID = driver.findElement(By.name("identifier")); 
	    	UserID.sendKeys(emailid); 
	    	
	    	WebElement Next1 = driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[2]"));
	    	Next1.click();
	    	
	    	Thread.sleep(3000);
	    	WebElement Pswd = driver.findElement(By.name("password")); 
	    	Pswd.sendKeys(password); 	
	        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);      
	       		    
	    } */

	    @When("^The user enters valid emailid and password$")
	    public void the_user_enters_valid_emailid_and_password() throws Throwable {
	    	{
		    	WebElement UserID = driver.findElement(By.name("identifier")); 
		    	UserID.sendKeys("gincu926@gmail.com"); 
		    	
		    	WebElement Next1 = driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[2]"));
		    	Next1.click();
		    	
		    	Thread.sleep(3000);
		    	WebElement Pswd = driver.findElement(By.name("password")); 
		    	Pswd.sendKeys("Password@123"); 	
		        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);         		    
		    }
	    }
	    
	    
	    @Then("^Clicks on signin button to login to gmail$")
	    public void clicks_on_signin_button_to_login_to_gmail() throws Throwable 
	    {

	    	WebElement Next2 = driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[2]"));
	    	Next2.click();
	    }

	    
	    @And("^Check if the user has logged in successfully$")
	    public void check_if_the_user_has_logged_in_successfully() throws Throwable 
	    {   
	    	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); 
	        String ExpectedURL = driver.getCurrentUrl();	        
	           if(ExpectedURL.equalsIgnoreCase("https://mail.google.com/mail/u/0/#inbox")) 
	             {
	        	   System.out.println("User has logged in successfuly");
	             }
	    }

	    
	    @And("^Compose email with to, subject,  body and send email$")
	    public void compose_email_with_to_subject_body_and_send_email() throws Throwable 
	    {
	    	WebElement Compose = driver.findElement(By.xpath("//div[contains(text(),'Compose')]"));
	    	Compose.click();
	    	
	    	WebElement To = driver.findElement(By.xpath("//input[@class='agP aFw']"));
	    	To.sendKeys("gincu926@gmail.com");
	    	
	    	WebElement Subject = driver.findElement(By.name("subjectbox"));
	    	Subject.sendKeys("Incubyte Deliverables:1");
	    	
		    Thread.sleep(2000);
		    WebElement Body = driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf tS-tW']"));
		    Body.sendKeys("Automation QA test for Incubyte");
		    
		    Thread.sleep(2000);
		    WebElement Send = driver.findElement(By.xpath("(//div[contains(text(),'Send')])[2]"));
		    Send.click();
	    }

	    
	    @And("^Check in Sentitem and Inbox$")
	    public void check_in_sentitem_and_inbox() throws Throwable 
	    {
		    Thread.sleep(2000);	    
		    WebElement Sentitem = driver.findElement(By.xpath("//a[contains(text(),'Sent')]"));  
		    Sentitem.click(); 
		    
		    Thread.sleep(4000);	    
		    WebElement Inbox = driver.findElement(By.xpath("//a[contains(text(),'Inbox')]"));
		    Inbox.click();
	    }

	    
	    @And("^Logout of Gmail$")
	    public void logout_of_gmail() throws Throwable 
	    {
	    	WebElement Account = driver.findElement(By.xpath("//div[@class='gb_yf gb_Wa gb_lg gb_f']"));
	    	Account.click();
	   	    //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    	
	   	    //driver.findElement(By.xpath("//a[starts-with(@aria-label,'Google Account:')]")).click();  
	   	    driver.switchTo().frame("account");
	   	    WebElement Signout = driver.findElement(By.xpath("//div[contains(text(),'Sign out')]"));  
	   	    Signout.click();
	    }

 // TestCase2 : This is negative login testcase : Check if user is unable to login with invalid credentials
	  
	    @When("^The user enters invalid emailid and password$")
	    public void the_user_enters_invalid_emailid_and_password() throws Throwable 
	    {
	    	the_user_has_launched_gmail_application_in_the_browser();
	    	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	    	
	    	WebElement UserID = driver.findElement(By.name("identifier")); 
	    	UserID.sendKeys("gincu926@gmail.com"); 
	    	
	    	WebElement Next1 = driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[2]"));
	    	Next1.click();
	    	
	    	Thread.sleep(3000);
	    	WebElement Pswd = driver.findElement(By.name("password")); 
	    	Pswd.sendKeys("Password@111"); 	
	        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    }


	    @And("^Check if user is unable able to login$")
	    public void check_if_user_is_unable_able_to_login() throws Throwable 
	    {
	    	clicks_on_signin_button_to_login_to_gmail();
	       
	    }

	    @And("^Check if the user is thrown appropriate error message$")
	    public void check_if_the_user_is_thrown_appropriate_error_message() throws Throwable 
	    {
	    	WebElement error = driver.findElement(By.xpath("(//div[@jsname='B34EJ'])[1]"));
		    String actual  = error.getText();
		    System.out.println(actual);
		    Assert.assertEquals(actual, "Wrong password. Try again or click ‘Forgot password’ to reset it."); 
	    	//System.out.println(driver.findElement(By.xpath("(//div[@jsname='B34EJ'])[1]")).getText());
	     
	    }


}