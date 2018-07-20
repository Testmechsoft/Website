package Website_Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.annotations.Test;

import lib_methods.SendEmail;

public class mailer {

	public SendEmail s;

	mailer() throws FileNotFoundException, MessagingException, IOException {

		s = new SendEmail();

	}

	@Test
	public void run() throws FileNotFoundException, MessagingException, IOException {

		s.send_HTML_mail();
	}
}
