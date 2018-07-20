package lib_methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Mouse_actions {

	public WebDriver driver;
	public Actions act;

	public Mouse_actions(WebDriver driver) {
		this.driver = driver;
		
		act = new Actions(driver);

	}

	public void click_and_hold(WebElement ele) {

		act.clickAndHold(ele);

	}

}
