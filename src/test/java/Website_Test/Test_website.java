package Website_Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import config.Configuration;

public class Test_website extends Configuration {

	@Test
	public void verify_links() throws IOException, InterruptedException {

		//vf.verify(driver.getCurrentUrl());
		Thread.sleep(2000);

	}

	@Test(dependsOnMethods="verify_links")
	public void client_link() throws IOException, InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//img[@src='images/splash/client.png']"));
		h.h(ele);
		ele.click();
		//verify_links();
		
	List<WebElement> header= driver.findElements(By.xpath("//h2"));
	List<WebElement> para= driver.findElements(By.xpath("//p"));
	List<WebElement> links= driver.findElements(By.xpath("//a"));
	
	for (WebElement ele1: header){
		
		System.out.println("Header "+ele1.getText());
		
	}
		
for (WebElement ele2: para){
		
		System.out.println("para "+ele2.getText());
		
	}

for (WebElement ele3: para){
	
	//System.out.println("para "+ele2.getText());
	
}
		
		

	}

}
