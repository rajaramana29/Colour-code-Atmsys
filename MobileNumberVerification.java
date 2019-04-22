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

import com.util.ATMSession;
import com.util.SendSMS;
import com.util.SendSMS_OLD;
import com.util.Utility;

import java.awt.event.*;
import java.sql.*;

public class MobileNumberVerification extends JFrame implements ActionListener{
	
	JTextField txtmobile=new JTextField(25);
	JLabel mobileno=new JLabel("Mobile no: ");
	JButton btnMobileverification=new JButton(new ImageIcon("btnMobileverification.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));

	Connection cn;
	Statement st;
	PreparedStatement ps;
	public MobileNumberVerification(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtmobile);
		pane.add(mobileno);
		pane.add(btnMobileverification);
		pane.add(btnBack);
		//-----Setting the location of the components
		mobileno.setBounds(550,300,80,20);
		mobileno.setForeground(Color.white);
		txtmobile.setBounds(650,300,100,20);
		btnMobileverification.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnMobileverification.addActionListener(this);
		btnBack.addActionListener(this);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
			lbl.setBounds(405,0,dim.width-800,dim.height);
		pane.add(lbl);

	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnMobileverification){
			
			if(txtmobile.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Mobile Number.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtmobile.requestFocus(true);
			}else if(!txtmobile.getText().trim().isEmpty() && txtmobile.getText().trim().length()!=10){
				
				JOptionPane.showMessageDialog(null,
						"Please enter 10 digit mobile no.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtmobile.setText("");
				txtmobile.requestFocus(true);
			}else if(!txtmobile.getText().trim().isEmpty() && !Utility.numberOrNot(txtmobile.getText().trim())){
				
				JOptionPane.showMessageDialog(null,
						"Please enter only Number",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtmobile.setText("");
				txtmobile.requestFocus(true);
			}else{
			boolean valuated = false;
			String username = ATMSession.getInstance().getItem("logedinusername").toString();
			Connection con = null;
			ResultSet rs1 = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/atmfingerprint",
						"root", "1111");
				Statement ps = con.createStatement();

				rs1 = ps.executeQuery("select * from clientreg where username='"
						+ username + "' and mobileno='"+txtmobile.getText().trim()+"'" );

				while (rs1.next()) {

					System.out.println(rs1.getString("mobileno"));
							valuated = true;
						}


				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"There is some issue. Contact Administrator.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtmobile.setText("");
				txtmobile.setText("");
				txtmobile.requestFocus(true);
			}

			if (valuated) {
				String otp = Utility.createRandomNo();
				boolean status = SendSMS.sendOTP(txtmobile.getText().trim(),otp);
				if(status){
				ATMSession.getInstance().setItem("logedinmobileno", txtmobile.getText().trim());
				System.out.println("otp="+otp);
				ATMSession.getInstance().setItem("logedinotp", otp);
				OTPVerification log=new OTPVerification();
				Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
				log.setLocation(0,0);
				log.setSize(dim.width,dim.height);
				log.setTitle("OTPVerification");
				log.setResizable(false);
				log.setVisible(true);
				dispose();
				}else{
					JOptionPane.showMessageDialog(null,
							"There is some issue. While sending OTP in to your Mobile no. Contact Administrator.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
					txtmobile.setText("");
					txtmobile.setText("");
					txtmobile.requestFocus(true);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Please enter registered mobile no.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtmobile.setText("");
				txtmobile.setText("");
				txtmobile.requestFocus(true);
			}

			}
		}else if(source==btnBack){
			ATMSession.getInstance().removeItem("logedinusername");
			User log=new User();
			
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("User-Login");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		}
		
	/*	else if(source == btnBlock){
			
			int n=JOptionPane.showConfirmDialog(null,"If you lock this account,you cannot access this Anymore.Are you sure you want to Lock this account?","Warning",JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION){
			
		try{
				
				
				st=cn.createStatement();
				String strUser="";
				String strPass="";
				
				ResultSet rs=st.executeQuery("SELECT * FROM 
				
				 WHERE UserName='"+txtmobile.getText()+"'");
				while(rs.next()){
					strUser=rs.getString(1);
					strPass=rs.getString(5);
				}
				
				if(strUser.equals(txtmobile.getText())){
					if(strPass.equals(txtpass.getText())){
						
						ps = cn.prepareStatement("DELETE FROM tbl_Info WHERE UserName ='"+ txtmobile.getText() + "'");
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null,"Your Account has been successfully Lock.","ATM",JOptionPane.INFORMATION_MESSAGE);
						txtmobile.requestFocus(true);
					
					txtmobile.setText("");
					txtpass.setText("");
					}else{
						JOptionPane.showMessageDialog(null,"Username found but the Pin Number is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
						txtpass.requestFocus(true);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Username is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
					txtmobile.requestFocus(true);
				}
					}
					catch(SQLException s){
					System.out.print("SQL statement is not executed!");
				}
						catch(Exception j){
						j.printStackTrace();
				}
			
			}
			}*/
	}
	
	public static void main(String[]args){
		MobileNumberVerification log=new MobileNumberVerification();
		
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);
		log.setTitle("MobileVerification");
		log.setResizable(false);
		log.setVisible(true);
		
		
	
	}
}