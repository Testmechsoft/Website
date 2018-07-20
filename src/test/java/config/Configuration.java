package config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalTime;
import javax.mail.MessagingException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import lib_methods.Browser_factory;
import lib_methods.ExcelSheetDriver;
import lib_methods.Explicit_timeout;
import lib_methods.Implicit_timeout;
import lib_methods.Log;
import lib_methods.Mouse_actions;
import lib_methods.Navigate;
import lib_methods.Screenshot;
import lib_methods.Scroll;
import lib_methods.Select_dropdown;
import lib_methods.SendEmail;
import lib_methods.Sikuli_c;
import lib_methods.Verify_Link;
import lib_methods.Wait_for_pageload;
import lib_methods.heighlight;
import lib_methods.pageLoad;

public class Configuration {
	public static WebDriver driver;
	public Log log;
	public Browser_factory b;
    public heighlight h;
	public ExtentReports r;
	public ExtentTest logger;
	public Screenshot s;
	public Verify_Link vf;
	public String filepath;
	public Implicit_timeout wait;
	public Explicit_timeout e;
	public Scroll sc;
	public Select_dropdown sd;
	public WebElement link;
	public LocalTime time;
	public Wait_for_pageload wp;
	public Sikuli_c sik;
	public Dataset d;
	public String s1 = "";
	public ExcelSheetDriver excel;
	public ExcelSheetDriver ex;
	public pageLoad p;
	public Navigate Navigate;
	public Mouse_actions m;
	public SendEmail smt;
	public String msg;

	public Configuration() {
		b = new Browser_factory();
		try {
			driver = b.initiate_browser("chrome");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		log = new Log();
		s = new Screenshot(driver);
		wait = new Implicit_timeout(driver);
		e = new Explicit_timeout(driver);
		sc = new Scroll(driver);
		sd = new Select_dropdown(driver);
		wp = new Wait_for_pageload(driver);
		sik = new Sikuli_c(driver);
		d = new Dataset();
		p = new pageLoad(driver);
		Navigate = new Navigate(driver);
		vf = new Verify_Link(driver);
		m = new Mouse_actions(driver);
		h= new heighlight(driver);
		smt = new SendEmail();
	}

	@BeforeSuite
	public void Openbrowser() throws InterruptedException {

		driver.get("http://192.169.154.191/mechsoftwebsitesbeta/");
		driver.manage().window().maximize();

		try {
			r = new ExtentReports(
					"E:\\Siddhartha\\Projects\\Automation-neon\\MMM.zip_expanded\\com.Makemymeal_\\report\\report.html");
			logger = r.startTest("Test initiated");
			log.tracelog("Test started");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@AfterTest
	public void teardown() throws InterruptedException, FileNotFoundException, MessagingException, IOException {

		log.tracelog("Test finished");
		r.endTest(logger);
		r.flush();

		driver.get("E:\\Siddhartha\\Projects\\Automation-neon\\MMM.zip_expanded\\com.Makemymeal_\\report\\report.html");

		

		try {

			Thread.sleep(5000);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@AfterMethod
	public void tracerror(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			try {

				msg = "Issue in" + " " + result.getName() + "" + "!!!!";

				filepath = s.Capturescreenshot(driver, result.getName());

				smt.send_Attachment_mail(msg, filepath);

				logger.log(LogStatus.FAIL, result.getName() + "-------Fail", logger.addScreenCapture(filepath));

			} catch (Exception e) {

				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}

		else {

			logger.log(LogStatus.PASS, result.getName() + "--------pass");

		}

	}

}
