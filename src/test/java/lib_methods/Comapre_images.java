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
import org.testng.Assert;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class Comapre_images {

	private String imagepath = "E:\\Siddhartha\\Projects\\Automation-neon\\MMM.zip_expanded\\com.Makemymeal_\\src\\test\\resources\\TestData\\";

	public WebDriver driver;
	public Scroll sc;

	public Comapre_images(WebDriver drv) {
		this.driver = drv;
		sc = new Scroll(driver);

	}

	public boolean compare_image(WebElement ele, String imagename) {

		try {
			Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, ele);
			BufferedImage actualImage = logoImageScreenshot.getImage();

			BufferedImage expectedImage = ImageIO.read(new File(imagepath + imagename));

			Thread.sleep(3000);

			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);

			boolean d = diff.hasDiff();

			Assert.assertFalse(diff.hasDiff(), "Images are not Same");

		} catch (Exception e) {

			e.printStackTrace();

		}
		return true;

	}

	public String compare_hideen_image(WebElement e, int x, int y, String expimg)
			throws IOException, InterruptedException {
		// take screenshot of the page and save it as FILE type

		// Read Expected image

		BufferedImage expectedImage = ImageIO.read(new File(imagepath +expimg+ ".png"));

		sc.scroll_down(1000);

		// Take screenshot

		File scrshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File screenshot = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/beforecropped.png");

		// copy screenshot to file
		FileUtils.copyFile(scrshot, screenshot);

		// Read screenshot image

		BufferedImage actualImage = ImageIO.read(screenshot);

		Point p = e.getLocation();
		int h = e.getSize().getHeight();
		int w = e.getSize().getWidth();

		// crop screenshot image using the height and width dimensions

		BufferedImage finalImg = actualImage.getSubimage(x, y, w, h);

		File outputfile = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/output.png");

		// Write cropped image

		ImageIO.write(finalImg, "png", outputfile);

		// Read cropped image

		BufferedImage act = ImageIO.read(outputfile);

		Thread.sleep(3000);

		// comapre expected and crroped image

		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(act, expectedImage);

		boolean d = diff.hasDiff();

		Assert.assertFalse(diff.hasDiff(), "Images are not Same");

		return expimg;
	}
}
