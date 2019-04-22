package com.atm;
/**
 * @(#)first_atm.java
 *
 *
 * 
 * 
 */


import java.awt.*;

import javax.swing.*;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.ui.swing.Enrollment.VerificationForm;
import com.util.ATMSession;

import java.awt.event.*;
import java.io.FileInputStream;
import java.sql.*;

public class FingerPrintVerification extends JFrame implements ActionListener{
	private DPFPTemplate template;	
	JLabel colorcode=new JLabel("Start your finger print verification by clicking on F-P Verify");
	JButton btnFingerprintverification=new JButton(new ImageIcon("btnFingerprintverification.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));
	
	public FingerPrintVerification(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(colorcode);
		pane.add(btnFingerprintverification);
		pane.add(btnBack);
		//-----Setting the location of the components
		colorcode.setBounds(500,300,400,20);
		colorcode.setForeground(Color.white);
		//txtpass.setBounds(650,350,100,20);
		btnFingerprintverification.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnFingerprintverification.addActionListener(this);
		btnBack.addActionListener(this);
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		lbl.setBounds(405,0,dim.width-800,dim.height);
		pane.add(lbl);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		String filename="error";
		boolean valuated = false;
		if(source==btnFingerprintverification){
			String username = ATMSession.getInstance().getItem("logedinusername").toString();
			Connection con = null;
			ResultSet rs1 = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/atmfingerprint",
						"root", "1111");
				Statement ps = con.createStatement();

				rs1 = ps.executeQuery("select * from clientreg where username='"+ username + "'" );

				while (rs1.next()) {

					System.out.println(rs1.getString("fingerprint"));
					filename = rs1.getString("fingerprint");
							valuated = true;
				}

				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"There is some issue. Contact Administrator.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
			
			}

			if(valuated){
				System.out.println("the file name is=================="+filename);
			try {
				FileInputStream stream = new FileInputStream("d:\\atmfinger\\"+filename);
				byte[] data = new byte[stream.available()];
				stream.read(data);
				stream.close();
				DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
				t.deserialize(data);
				setTemplate(t);
				dispose();
				VerificationForm form = new VerificationForm(new Frame(),t);
				form.setVisible(true);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Fingerprint loading", JOptionPane.ERROR_MESSAGE);
			}
			
			
			}else{
				JOptionPane.showMessageDialog(null,
						"There is some issue. Contact Administrator.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
			
			}
		}else if(source==btnBack){
		ChooseAuthentcationType log=new ChooseAuthentcationType();
			
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("Choose Authentication type");
			log.setResizable(false);
			log.setVisible(true);


			dispose();
		}
		

	}
	public DPFPTemplate getTemplate() {
		return template;
	}
	public void setTemplate(DPFPTemplate template) {
		String TEMPLATE_PROPERTY = "template";
		DPFPTemplate old = DPFPGlobal.getTemplateFactory().createTemplate();;
		this.template = template;
		firePropertyChange(TEMPLATE_PROPERTY, old, template);
	}	
	public static void main(String[]args){
		FingerPrintVerification log=new FingerPrintVerification();
		
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);
		log.setTitle("FingerPrintVerification");
		log.setResizable(false);
		log.setVisible(true);
		
		
	
	}
}