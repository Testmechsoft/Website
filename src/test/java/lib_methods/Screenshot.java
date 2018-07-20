package lib_methods;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;

public class Screenshot {

	public WebDriver driver;
	public Scroll sc;
	public heighlight h;

	public Screenshot(WebDriver drv) {
		this.driver = drv;
		sc = new Scroll(driver);
		h = new heighlight(driver);

	}

	public String Capturescreenshot(WebDriver driver, String Screenshotname) throws IOException {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File sc = ts.getScreenshotAs(OutputType.FILE);

			String dest = System.getProperty("user.dir") + "/src/test/resources/Errors/" + Screenshotname + ".jpeg";

			FileUtils.copyFile(sc, new File(dest));
			return dest;

		} catch (Exception e) {
			System.out.println("exception while taking screenshot" + e.getMessage());
			return e.getMessage();
		}

	}

	public void Element_screenshot(WebElement ele, String screenshotname) {

		try {
			Thread.sleep(3000);
			ru.yandex.qatools.ashot.Screenshot screenshot = new AShot().takeScreenshot(driver, ele);

			ImageIO.write(screenshot.getImage(), "PNG", new File(
					System.getProperty("user.dir") + "/src/test/resources/TestData/" + screenshotname + ".png"));

			Thread.sleep(2000);

			driver.get(System.getProperty("user.dir") + "/src/test/resources/TestData/" + screenshotname + ".png");

			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Element_screenshot_coridates(WebElement e, String imagename) throws IOException, InterruptedException {

		sc.scroll_down(1000);

		// take screenshot of the page and save it as FILE type
		File scrshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// copy it in D:\\
		FileUtils.copyFile(scrshot,
				new File(System.getProperty("user.dir") + "/src/test/resources/TestData/beforecropped.png"));

		h.h(e);

		Point p = e.getLocation();

		int h = e.getSize().getHeight();
		int w = e.getSize().getWidth();
		int x = p.getX();
		int y = p.getY();

		BufferedImage img = ImageIO.read(scrshot);

		// crop image using the height and width dimensions

		BufferedImage finalImg = img.getSubimage(x, y, w, h);

		ImageIO.write(finalImg, "png", scrshot);

		File f1 = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/" + imagename + ".png");
		FileUtils.copyFile(scrshot, f1);

		driver.get(
				"E:\\Siddhartha\\Projects\\Automation-neon\\MMM.zip_expanded\\com.Makemymeal_\\src\\test\\resources\\TestData\\"
						+ imagename + ".png");
		Thread.sleep(3000);

	}

}
