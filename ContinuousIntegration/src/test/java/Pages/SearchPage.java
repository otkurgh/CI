package Pages;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


import Base.BaseClass;

public class SearchPage extends BaseClass {

	public static String title;
	
	
	public ResultPage searchResult(){
	
		driver.get("https://www.google.com");
	    title=driver.getTitle();
	    System.out.println(title);
		WebElement searchbox=driver.findElement(By.name("q"));
		searchbox.click();
		searchbox.sendKeys("Otkur Ghojash");
		searchbox.sendKeys(Keys.ENTER);
	    System.out.print("Page turns into result page");
		return new ResultPage();
		
}
	
}
