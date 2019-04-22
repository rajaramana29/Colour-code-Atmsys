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

import java.awt.event.*;
import java.sql.*;

public class AdminHome extends JFrame implements ActionListener{
	
	//JTextField txtuser=new JTextField(25);
	//JPasswordField txtpass=new JPasswordField(25);
	//JLabel lbluser=new JLabel("Username: ");
	//JLabel lblpass=new JLabel("Pin Number: ");
	JButton manageAdminUser=new JButton(new ImageIcon("btnManageAdminUser.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));
	JButton registerUser=new JButton(new ImageIcon("btnRegisterUser.jpg"));
	JButton manageUser=new JButton(new ImageIcon("btnManageUser.jpg"));
	JButton viewDailyuserCashDeposit=new JButton(new ImageIcon("btnViewDailyuserCashDeposit.jpg"));
	JButton viewDailyuserCashWithDraw=new JButton(new ImageIcon("btnViewDailyuserCashWithDraw.jpg"));

	
	//JButton btnBlock = new JButton("Lock Account >>");

	Connection cn;
	//ResultSet rs;
	Statement st;
	PreparedStatement ps;
	public AdminHome(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		/*pane.add(txtuser);
		pane.add(txtpass);
		pane.add(lbluser);
		pane.add(lblpass);	
		pane.add(btnLogin);
*/		pane.add(btnBack);
		pane.add(manageAdminUser);
		pane.add(registerUser);
		pane.add(manageUser);
		pane.add(viewDailyuserCashDeposit);
		pane.add(viewDailyuserCashWithDraw);
		//pane.add(btnRegister);
		//pane.add(btnBlock);
		//-----Setting the location of the components
/*		lbluser.setBounds(550,300,80,20);
		lbluser.setForeground(Color.white);
		lblpass.setBounds(550,350,80,20);
		lblpass.setForeground(Color.white);
		txtuser.setBounds(650,300,100,20);
		txtpass.setBounds(650,350,100,20);
		btnLogin.setBounds(1000,570,320,80);*/
		btnBack.setBounds(50,570,320,80);
		manageAdminUser.setBounds(50,470,320,80);
		
		registerUser.setBounds(1000,570,320,80);
		manageUser.setBounds(1000,470,320,80);
		viewDailyuserCashDeposit.setBounds(1000,370,320,80);
		viewDailyuserCashWithDraw.setBounds(1000,270,320,80);
		
		//btnRegister.setBounds(125,70,83,20);
		//btnBlock.setBounds(55,90,150,20);
		//-----Adding the an actionlistener to the buttons
		//btnLogin.addActionListener(this);
		btnBack.addActionListener(this);
		manageAdminUser.addActionListener(this);
		
		registerUser.addActionListener(this);
		manageUser.addActionListener(this);
		viewDailyuserCashDeposit.addActionListener(this);
		viewDailyuserCashWithDraw.addActionListener(this);
		//btnRegister.addActionListener(this);
		//btnBlock.addActionListener(this);
		
		
		//btnBlock.setForeground(Color.yellow);	
		//btnBlock.setBackground(Color.black);
			

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
			lbl.setBounds(405,0,dim.width-800,dim.height);
		pane.add(lbl);

		
		//connection
				
		//try{
			Connection conn;
			try {
				cn = DriverManager.getConnection("jdbc:ucanaccess://project.mdb");
				Statement s = cn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//cn = DriverManager.getConnection("jdbc:odbc:project");
		//}catch(ClassNotFoundException e)  {
 		//	System.err.println("Failed to load driver");
 		//	e.printStackTrace();
 	
 		//}catch(SQLException e){
 			//System.err.println("Unable to connect");
 			//e.printStackTrace();
 			
 	//	}
	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
	 if(source==btnBack){
			Admin log=new Admin();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("Admin-Login");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		
		} else if(source==manageAdminUser){
			AddOrEditAdmin log=new AddOrEditAdmin();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("manageAdminUser");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		}
		else if(source==registerUser){
			UserRegister log=new UserRegister();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("User-Register");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		
		} 
		else if(source==manageUser){
			EditUserSearch log=new EditUserSearch();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			//this.setLocation(x, y);
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);

			log.setTitle("Edit-User-Search");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		
		} 
		else if(source==viewDailyuserCashDeposit){
			CashDepositUserSearch log=new CashDepositUserSearch();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			//this.setLocation(x, y);
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);

			log.setTitle("CashDeposit-User-Search");
			log.setResizable(false);
			log.setVisible(true);

			dispose();
		
		} 
		else if(source==viewDailyuserCashWithDraw){
			CashWithDrawUserSearch log=new CashWithDrawUserSearch();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			//this.setLocation(x, y);
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);

			log.setTitle("CashWithDraw-User-Search");
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
				
				 WHERE UserName='"+txtuser.getText()+"'");
				while(rs.next()){
					strUser=rs.getString(1);
					strPass=rs.getString(5);
				}
				
				if(strUser.equals(txtuser.getText())){
					if(strPass.equals(txtpass.getText())){
						
						ps = cn.prepareStatement("DELETE FROM tbl_Info WHERE UserName ='"+ txtuser.getText() + "'");
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null,"Your Account has been successfully Lock.","ATM",JOptionPane.INFORMATION_MESSAGE);
						txtuser.requestFocus(true);
					
					txtuser.setText("");
					txtpass.setText("");
					}else{
						JOptionPane.showMessageDialog(null,"Username found but the Pin Number is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
						txtpass.requestFocus(true);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Username is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
					txtuser.requestFocus(true);
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
		AdminHome log=new AdminHome();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(x, y);
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);

		log.setTitle("Admin-Home");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}