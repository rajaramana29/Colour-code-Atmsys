package com.digitalpersona.onetouch.ui.swing.Enrollment;

import com.atm.AdminHome;
import com.atm.model.User;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.*;
import com.util.AESAlgorithm;
import com.util.ATMSession;
import com.util.Utility;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EnrollmentForm extends CaptureForm
{
	private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();
	public static String TEMPLATE_PROPERTY = "template";
	private DPFPTemplate template;
	public EnrollmentForm(Frame owner) {
		super(owner);
	}
	
	@Override protected void init()
	{
		super.init();
		this.setTitle("Fingerprint Enrollment");
		updateStatus();
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);
		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

		// Check quality of the sample and add to enroller if it's good
		if (features != null) try
		{
			makeReport("The fingerprint feature set was created.");
			enroller.addFeatures(features);		// Add feature set to template.
			// Continue save process in other location----Start
			
		//	if(DPFPTemplateStatus.TEMPLATE_STATUS_READY.equals(enroller.getTemplateStatus())){
				
			//}
			// Continue save process in other location----End
		}
		catch (DPFPImageQualityException ex) { }
		finally {
			updateStatus();
			
			// Check if template has been created.
			switch(enroller.getTemplateStatus())
			{
				case TEMPLATE_STATUS_READY:	// report success and stop capturing
		
					stop();
					//((MainForm)getOwner()).setTemplate(enroller.getTemplate());
					System.out.println("---------------------------------");
					try {
						User usr = null;
						String username="error";
						if(ATMSession.getInstance().getItem("registereduser")!=null){
							usr = (User) ATMSession.getInstance().getItem("registereduser");
							username = usr.getUsername();
						}
						System.out.println("username===="+username);
						File file = new File("D:\\atmfinger\\"+username+ ".fpt");
						FileOutputStream stream = new FileOutputStream(file);
						 DPFPTemplate t = enroller.getTemplate();
						stream.write(t.serialize());
						stream.close();
						 
						String r = Utility.createR();
					    String g = Utility.createG();
					    String b = Utility.createB();
				
						Connection con = null;
							int processed=0;
							try {
								Class.forName("com.mysql.jdbc.Driver");
								con = DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/atmfingerprint",
										"root", "1111");
								//String birday = txtday.getText()+"/"+txtmonth.getText()+"/"+txtyear.getText();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
							    sdf = new SimpleDateFormat("dd/MM/yyyy");
							    String  strDate = sdf.format(date);
							    AESAlgorithm aesa = new AESAlgorithm();
							    System.out.println("formatted date in dd/MM/yyyy : " + strDate);
							    			Statement ps = con.createStatement();
								String query = "insert into clientreg(username,firstname,lastname,pinnumber,mobileno,bday,bmonth,byear,fingerprint,r,g,b,email,registrationdate) values('"
								+ usr.getUsername()
								+ "','"
								+ usr.getFirstname()
								+ "','"
								+ usr.getLastname()
								+ "','"
								+ usr.getPinnumber()
								+ "','"
								+ usr.getMobileno()
								+ "','"
								+ usr.getBday()
								+ "','"
								+ usr.getBmonth()
								+ "','"
								+ usr.getByear()
								+ "','"
								+ usr.getUsername()+".fpt"
								+ "','"
								+ aesa.encrypt(r)
								+ "','"
								+ aesa.encrypt(g)
								+ "','"
								+ aesa.encrypt(b)
								+ "','"
								+ usr.getEmail()
								+ "','"
								+ strDate+"')";
								System.out.println(usr.toString());
								System.out.println(query);
								processed = ps.executeUpdate(query);
								con.close();
								
								
							} catch (Exception ex) {
								ex.printStackTrace();
								JOptionPane.showMessageDialog(null,
										"There is some issue. Contact Administrator.",
										"WARNING", JOptionPane.WARNING_MESSAGE);
							}

							if (processed == 1) {
								//TODO Sent mail
								String from = Utility.getValueFromProperties("email.properties", "from");
								String password =Utility.getValueFromProperties("email.properties", "password");
								System.out.println("from:"+from+", password:"+password);
								String to = usr.getEmail();
								String sub = "Registration details";
								String msg = "Registration approved. Your secret key code is R='"
										+r+ "' G='"+g+"' B='"+b+"'";
								boolean status = Utility.sendMail(from, password, to, sub, msg);
								if(status){
								ATMSession.getInstance().removeItem("registereduser");
								JOptionPane.showMessageDialog(null,
										"User Successfully Registered.",
										"WARNING", JOptionPane.WARNING_MESSAGE);
								AdminHome log=new AdminHome();
								Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
								//this.setLocation(x, y);
								log.setLocation(0,0);
								log.setSize(dim.width,dim.height);

								log.setTitle("Admin-Home");
								log.setResizable(false);
								log.setVisible(true);
																
								dispose();
								}else{
									JOptionPane.showMessageDialog(null,
											"There is some issue with sending mail. Contact Administrator.",
											"WARNING", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"There is some issue. Contact Administrator.",
										"WARNING", JOptionPane.WARNING_MESSAGE);
							}

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Fingerprint saving", JOptionPane.ERROR_MESSAGE);
					}




					setPrompt("Click Close, and then click Fingerprint Verification.");
					break;

				case TEMPLATE_STATUS_FAILED:	// report failure and restart capturing
					enroller.clear();
					stop();
					updateStatus();
					((MainForm)getOwner()).setTemplate(null);
					JOptionPane.showMessageDialog(EnrollmentForm.this, "The fingerprint template is not valid. Repeat fingerprint enrollment.", "Fingerprint Enrollment", JOptionPane.ERROR_MESSAGE);
					start();
					break;
			}
		}
	}
	
	private void updateStatus()
	{
		// Show number of samples needed.
		setStatus(String.format("Fingerprint samples needed: %1$s", enroller.getFeaturesNeeded()));
	}
	
}
