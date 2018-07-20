package lib_methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

//File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.io.IOUtils;
import javax.activation.*;

public class SendEmail2 {

	public static Session session;
	public MimeMessage message;

	final static String user = "code1@mechsoftgroup.com";
	final String password = "mechsoft@123";
	String to = "Siddhartha.panda@mechsoftgroup.com";
	
	
	
	String cc = "somanath.kumbhar@mechsoftgroup.com,meghraj.belikatti@mechsoftgroup.com";

	String from = "Siddhartha.panda@mechsoftgroup.com";

	public SendEmail2() {

		String host = "smtpout.secureserver.net";

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, true));

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}


	public void send_simple_mail() throws MessagingException {

		message.setSubject("testmail");
		message.setText("This is simple program of sending email using JavaMail API");

		Transport.send(message);

		System.err.println("Issue Report mail sent successfully...");

	}


	public void send_HTML_mail() throws MessagingException, FileNotFoundException, IOException {

		StringWriter writer = new StringWriter();
		IOUtils.copy(
				new FileInputStream(new File(
						"C:\\Users\\Siddhartha\\Desktop\\New folder\\t.html")),
				writer);

		message.setSubject("testmail");
		message.setContent(writer.toString(), "text/html");

		Transport.send(message);

		System.err.println("Issue Report mail sent successfully...");

	}


	public void send_Attachment_mail(String pagename,String filepath) throws MessagingException, FileNotFoundException, IOException {

		message.setSubject(pagename);

		BodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		messageBodyPart.setText("This is message body");

		// Create a multipar message
		Multipart multipart = new MimeMultipart();

		// Set text message part
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		String filename = filepath;
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);

		Transport.send(message);

		System.err.println("Issue Report mail sent successfully...");

	}

}
