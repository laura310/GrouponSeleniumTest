import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.*;

import java.util.Scanner;
import org.junit.*;

public class SeleniumDemo {
	
	private WebDriver driver;
	
	@Before
	public void getChromedriver() {
		
		/**example:  /Users/laurazhou/Documents/workspaceIntelliJ/SeleniumForGroupon/chromedriver **/
		System.out.println("Please nter the path to chromedriver on your machine:");
		
		Scanner in = new Scanner(System.in);
		String pathToChromedriver = in.nextLine();
		System.out.println("Path to chromedriver is: " + pathToChromedriver);
		in.close();
		
		
		System.setProperty("webdriver.chrome.driver", pathToChromedriver);		
		driver = new ChromeDriver();
        driver.get("http://www.groupon.com");
        
        
        /** Two methods to get rid of pop Sign-Up page **/
        /** Method-1: Click "No Thanks" **/
        WebElement waitPop = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("nothx")));
        waitPop.sendKeys(Keys.ENTER);

//        /** Method-2: Submit Email Address **/
//        WebElement emailAddrElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("email_address")));
//        emailAddrElement.sendKeys("cmpe287team2@gmail.com");
//        driver.findElement(By.xpath("//*[@id=\"submit_button\"]")).sendKeys(Keys.ENTER);
        
        
        
      //sign in
      //driver.findElement(By.xpath("//*[@id='ls-user-signin']")).click();   //not stable
      driver.findElement(By.id("ls-user-signin")).sendKeys(Keys.ENTER); //*[@id="ls-user-signin"]
      String email = "cmpe287team2@gmail.com";
      String psw = "tester123";
      driver.findElement(By.xpath("//*[@id=\"email-input\"]")).sendKeys(email);
      driver.findElement(By.xpath("//*[@id=\"password-input\"]")).sendKeys(psw);
      driver.findElement(By.xpath("//*[@id=\"signin-button\"]")).click();
      pauseFunc(2000);
	}
	
	
	@Test
	public void testSearch() {
		
		pauseFunc(1000);
		
        WebElement element = driver.findElement(By.name("search"));
        element.sendKeys("iphone 6");
        element.submit();
               
        pauseFunc(3000);
        
        WebElement certainProduct = driver.findElement(By.xpath("//*[@id=\"pull-cards\"]/figure[4]"));
        certainProduct.click();

        pauseFunc(4000);     
        
        
        //choose color and size 
        WebElement colorEle = driver.findElement(By.id("trait-0"));
        Select selColor = new Select(colorEle);
        selColor.selectByValue("Silver");
        WebElement sizeEle = driver.findElement(By.xpath("//*[@id=\"trait-1\"]"));
        Select selSize = new Select(sizeEle);
        selSize.selectByValue("iPhone 6 64GB");
        pauseFunc(2000);
      
      
	    //add to cart
	    WebElement buyEle = driver.findElement(By.id("buy-link"));
	    buyEle.click();
	    pauseFunc(3000);
      
	    //check out
	    WebElement checkOutEle = driver.findElement(By.xpath("//*[@id=\"bottom-proceed-to-checkout\"]"));
	    checkOutEle.click();
	    pauseFunc(3000);
    }
	
	
	
//	@After
	public void quitDriver() {
		driver.quit();
	}
	
	
	private void pauseFunc(int millisec) {
		try {
			Thread.sleep(millisec);
		}catch (InterruptedException ex) {
			System.out.println("InterruptedException from Thread.sleep in main");
			ex.printStackTrace();
		}
	}
}
