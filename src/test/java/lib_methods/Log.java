package lib_methods;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

public class Log {
	
	public Logger logger;
	
	public Log(){
		
		logger = logger.getLogger(getClass());
		PropertyConfigurator.configure("E:\\Siddhartha\\Projects\\Automation-neon\\MMM.zip_expanded\\com.Makemymeal_\\log\\log4j.properties");

	}
	
	public void tracelog(String message){
	logger.info(message);

	}

}
