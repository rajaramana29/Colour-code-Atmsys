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

import java.awt.event.*;
import java.sql.*;

public class ChooseTransactionType extends JFrame implements ActionListener{
	//JLabel lblpass=new JLabel("Pin Number: ");
	JButton btnCashDeposit=new JButton(new ImageIcon("btnCashDeposit.jpg"));
	JButton btnCashWithdraw=new JButton(new ImageIcon("btnCashWithdraw.jpg"));
	JButton btnBalaceEnquiry=new JButton(new ImageIcon("btnBalaceEnquiry.jpg"));
	//JButton btnRegister=new JButton(new ImageIcon("btnRegister.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));
	//JButton btnBlock = new JButton("Lock Account >>");

	Connection cn;
	//ResultSet rs;
	Statement st;
	PreparedStatement ps;
	public ChooseTransactionType(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(btnCashDeposit);
		pane.add(btnCashWithdraw);
		
		pane.add(btnBalaceEnquiry);
		//pane.add(btnRegister);
		pane.add(btnBack);
		//pane.add(btnBlock);
		//-----Setting the location of the components
		//lblpass.setBounds(550,350,80,20);
	//	lblpass.setForeground(Color.white);
		//txtpass.setBounds(650,350,100,20);
		btnCashDeposit.setBounds(1000,570,320,80);
		btnCashWithdraw.setBounds(1000,470,320,80);
		btnBalaceEnquiry.setBounds(50,470,320,80);
		
		//btnRegister.setBounds(1000,470,320,80);
		btnBack.setBounds(50,570,320,80);
		//btnBlock.setBounds(55,90,150,20);
		//-----Adding the an actionlistener to the buttons
		btnCashDeposit.addActionListener(this);
		btnCashWithdraw.addActionListener(this);
		btnBalaceEnquiry.addActionListener(this);
		//btnRegister.addActionListener(this);
		btnBack.addActionListener(this);
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
		
		if(source==btnCashDeposit){
			CashDeposit log=new CashDeposit();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			//this.setLocation(x, y);
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);

			log.setTitle("CashDeposit");
			log.setResizable(false);
			log.setVisible(true);
	

			dispose();
		}else if(source==btnCashWithdraw){
			CashWithDraw log=new CashWithDraw();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			//this.setLocation(x, y);
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);

			log.setTitle("CashWithDraw");
			log.setResizable(false);
			log.setVisible(true);


			dispose();
		}else if(source==btnBalaceEnquiry){
			CheckBalance log=new CheckBalance();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			//this.setLocation(x, y);
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);

			log.setTitle("Check Balance");
			log.setResizable(false);
			log.setVisible(true);

			dispose();
		}else if(source==btnBack){
			ATMSession.getInstance().cleanUp();
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
		ChooseTransactionType log=new ChooseTransactionType();
		Object ob = new Object();
		
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);
		log.setTitle("Choose Transaction type");
		log.setResizable(false);
		log.setVisible(true);
		
		
	
	}
}