package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Utility {
	
	public static boolean numberOrNot(String input)
	{
	    System.out.println("This is from numberOrNot"+input);
		try
	    {
			 System.out.println("Try");
			Long.parseLong(input);
	    }
	    catch(NumberFormatException ex)
	    {
	    	System.out.println("Exception");
	    	return false;
	    }
	    return true;
	}
	
	public static String createRandomNo(){
		Random random= new Random();
		String rndno = String.format("%04d", random.nextInt(10000));
		return rndno;
	}
	
	public static String createR(){
		Random random= new Random();
		String rndno = String.format("%03d", random.nextInt(1000));
		return rndno;
	}
	public static String createG(){
		Random random= new Random();
		String rndno = String.format("%03d", random.nextInt(1000));
		return rndno;
	}
	public static String createB(){
		Random random= new Random();
		String rndno = String.format("%03d", random.nextInt(1000));
		if("test".equalsIgnoreCase("test")){
			return rndno;
		}
		 
		return rndno;
	}
	
	public static boolean isDouble(String input)
	{
	    System.out.println("This is from isDouble"+input);
		try
	    {
			 System.out.println("Try");
			Double.parseDouble(input);
	    }
	    catch(NumberFormatException ex)
	    {
	    	System.out.println("Exception");
	    	return false;
	    }
	    return true;
	}

	public static boolean sendMail(String from, String password, String to,
			String sub, String msg) {
		// Get properties object
		boolean status = false;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, password);
					}
				});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
			System.out.println("message sent successfully");
			status = true;
		} catch (Exception e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	
	public static String getValueFromProperties(String filename, String key) {
		String value = null;
		try {
			
			InputStream fileInput = new FileInputStream("email.properties");
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			value = properties.getProperty(key).toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	public static void main(String args[]){
		System.out.println(getValueFromProperties("email.properties","from"));
	}
	
}
