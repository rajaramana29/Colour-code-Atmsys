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
import com.util.Utility;

import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashDeposit extends JFrame implements ActionListener{
	
	JTextField txtdeposit=new JTextField(25);
	JLabel lbldepositamountlbl=new JLabel("Deposit Amount: ");
	JButton btnDeposit=new JButton(new ImageIcon("btnDeposit.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));

	public CashDeposit(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtdeposit);
		pane.add(lbldepositamountlbl);
		pane.add(btnDeposit);
		pane.add(btnBack);
		//-----Setting the location of the components
		lbldepositamountlbl.setBounds(550,300,150,20);
		lbldepositamountlbl.setForeground(Color.white);
		txtdeposit.setBounds(650,300,170,20);
		btnDeposit.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnDeposit.addActionListener(this);
		btnBack.addActionListener(this);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
			lbl.setBounds(405,0,dim.width-800,dim.height);
		pane.add(lbl);


	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnDeposit){
			
			if(txtdeposit.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Withdraw Amount.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtdeposit.requestFocus(true);
			}else if(!txtdeposit.getText().trim().isEmpty() && !Utility.isDouble(txtdeposit.getText().trim())){
				
				JOptionPane.showMessageDialog(null,
						"Please enter valid amount",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtdeposit.setText("");
				txtdeposit.requestFocus(true);
			}else{
				boolean valuated = false;
				String username = ATMSession.getInstance().getItem("logedinusername").toString();
				//String username = "test";
				Connection con = null;
				ResultSet rs1 = null;
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
			    sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			    String  strDate = sdf.format(date);
			    System.out.println("formatted date in dd/MM/yyyy : " + strDate);
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/atmfingerprint",
							"root", "1111");
					Statement ps = con.createStatement();

					rs1 = ps.executeQuery("select * from clientreg where username='"
							+ username + "'" );

					while (rs1.next()) {

								System.out.println(rs1.getString("balance"));
								
									Double newBalance = Double.parseDouble(rs1.getString("balance")) + Double.parseDouble(txtdeposit.getText().trim());
									Statement ps1 = con.createStatement();
									int updated = ps1.executeUpdate("update clientreg set balance='"
											+ newBalance + "' where username='"
											+ username + "'");
									if(updated==1){
										
										Statement ps2 = con.createStatement();
										String query = "insert into deposit(username,depositamnt,depositdate) values('"
												+ username
												+ "','"
												+ txtdeposit.getText().trim()
												+ "','"
												+ strDate
												+"')";	
												System.out.println(query);
												int processed = ps2.executeUpdate(query);
												if(processed==1){
													ATMSession.getInstance().setItem("logedincurrentbalance", newBalance);
													ATMSession.getInstance().setItem("logedindepositamnt", txtdeposit.getText().trim());
													ATMSession.getInstance().setItem("logedindepositdate", strDate);
													valuated = true;
												}else{
													JOptionPane.showMessageDialog(null,
															"There is some issue. Contact Administrator.",
															"WARNING", JOptionPane.WARNING_MESSAGE);
													txtdeposit.setText("");
													txtdeposit.setText("");
													txtdeposit.requestFocus(true);
												}
												
									}else{
										JOptionPane.showMessageDialog(null,
												"There is some issue. Contact Administrator.",
												"WARNING", JOptionPane.WARNING_MESSAGE);
										txtdeposit.setText("");
										txtdeposit.setText("");
										txtdeposit.requestFocus(true);
									}
										
								}


					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"There is some issue. Contact Administrator.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
					txtdeposit.setText("");
					txtdeposit.setText("");
					txtdeposit.requestFocus(true);
				}

				if (valuated) {
					
					DepositTransactionConfirmation log=new DepositTransactionConfirmation();
					Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
					//this.setLocation(x, y);
					log.setLocation(0,0);
					log.setSize(dim.width,dim.height);

					log.setTitle("CashDepositTransactionConfirmation");
					log.setResizable(false);
					log.setVisible(true);

					dispose();

				} else {
					JOptionPane.showMessageDialog(null,
							"There is some issue. Contact Administrator.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
					txtdeposit.setText("");
					txtdeposit.setText("");
					txtdeposit.requestFocus(true);
				}

				
				
				
			
			}

			

		}else if(source==btnBack){
			ChooseTransactionType log=new ChooseTransactionType();
			
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("Choose Transaction type");
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
				
				 WHERE UserName='"+txtdeposit.getText()+"'");
				while(rs.next()){
					strUser=rs.getString(1);
					strPass=rs.getString(5);
				}
				
				if(strUser.equals(txtdeposit.getText())){
					if(strPass.equals(txtpass.getText())){
						
						ps = cn.prepareStatement("DELETE FROM tbl_Info WHERE UserName ='"+ txtdeposit.getText() + "'");
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null,"Your Account has been successfully Lock.","ATM",JOptionPane.INFORMATION_MESSAGE);
						txtdeposit.requestFocus(true);
					
					txtdeposit.setText("");
					txtpass.setText("");
					}else{
						JOptionPane.showMessageDialog(null,"Username found but the Pin Number is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
						txtpass.requestFocus(true);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Username is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
					txtdeposit.requestFocus(true);
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
		CashDeposit log=new CashDeposit();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(x, y);
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);

		log.setTitle("CashDeposit");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}