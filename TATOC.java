package tatoc_testing;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TATOC {
	public static void main(String args[]) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.cssSelector("a")).click();
		driver.findElement(By.className("greenbox")).click();
		driver.switchTo().frame("main");
		WebElement box1 = driver.findElement(By.id("answer"));
		String Box1 = box1.getAttribute("class");
		String Box2 = "";
		while(!Box1.equals(Box2)){
		  
		  
		    driver.switchTo().frame("main");
		    driver.findElement(By.cssSelector("a")).click();
		    driver.switchTo().frame("child");
		    Box2=driver.findElement(By.id("answer")).getAttribute("class");
		}
		driver.switchTo().frame("main");
		driver.findElement(By.linkText("Proceed")).click();

		 
		Actions act=new Actions(driver);
		WebElement drop = driver.findElement(By.cssSelector("#dropbox"));
		WebElement drag = driver.findElement(By.cssSelector("#dragbox"));
		act.dragAndDrop(drag, drop).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
		
		//POPUP window
		// It will return the parent window name as a String
		String parentWindow = driver.getWindowHandle();

		// This will return the number of windows opened by Webdriver and will return Set of St//rings
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);
		Iterator<String> I1= handles.iterator();
		 
		while(I1.hasNext())
		{
		 
		   String child_window=I1.next();
		 
		// Here we will compare if parent window is not equal to child window then we            will close
		 
		if(!parentWindow.equals(child_window))
		{
		driver.switchTo().window(child_window);
		    	driver.findElement(By.id("name")).sendKeys("Rishabh");
			    driver.findElement(By.id("submit")).click();
			    break;
			}
	    }
		driver.switchTo().window(parentWindow);
		driver.findElement(By.linkText("Proceed")).click();
	    driver.switchTo().window(parentWindow);
	    
	    //token
	    driver.findElement(By.linkText("Generate Token")).click();
		String Cookie_val = driver.findElement(By.id("token")).getText();
		Cookie cookie = new Cookie("Token", Cookie_val.substring(7));
		driver.manage().addCookie(cookie);
		driver.findElement(By.linkText("Proceed")).click();
	    

	}
}


