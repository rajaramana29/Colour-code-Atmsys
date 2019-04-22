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

public class CashWithDraw extends JFrame implements ActionListener{
	
	JTextField txtwithdraw=new JTextField(25);
	JLabel lbldepositamountlbl=new JLabel("WithDraw Amount: ");
	JButton btnWithDraw=new JButton(new ImageIcon("btnWithDraw.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));

	public CashWithDraw(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtwithdraw);
		pane.add(lbldepositamountlbl);
		pane.add(btnWithDraw);
		pane.add(btnBack);
		//-----Setting the location of the components
		lbldepositamountlbl.setBounds(550,300,150,20);
		lbldepositamountlbl.setForeground(Color.white);
		txtwithdraw.setBounds(680,300,170,20);
		btnWithDraw.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnWithDraw.addActionListener(this);
		btnBack.addActionListener(this);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
			lbl.setBounds(405,0,dim.width-800,dim.height);
		pane.add(lbl);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnWithDraw){
			if(txtwithdraw.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Withdraw Amount.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtwithdraw.requestFocus(true);
			}else if(!txtwithdraw.getText().trim().isEmpty() && !Utility.isDouble(txtwithdraw.getText().trim())){
				
				JOptionPane.showMessageDialog(null,
						"Please enter valid amount",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtwithdraw.setText("");
				txtwithdraw.requestFocus(true);
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
								if(Double.parseDouble(txtwithdraw.getText().trim()) <= Double.parseDouble(rs1.getString("balance"))){
									valuated = true;
									Double newBalance = Double.parseDouble(rs1.getString("balance")) - Double.parseDouble(txtwithdraw.getText().trim());
									Statement ps1 = con.createStatement();
									int updated = ps1.executeUpdate("update clientreg set balance='"
											+ newBalance + "' where username='"
											+ username + "'");
									if(updated==1){
										
										Statement ps2 = con.createStatement();
										String query = "insert into withdraw(username,withdrawamnt,withdrawdate) values('"
												+ username
												+ "','"
												+ txtwithdraw.getText().trim()
												+ "','"
												+ strDate
												+"')";	
												System.out.println(query);
												int processed = ps2.executeUpdate(query);
												if(processed==1){
													ATMSession.getInstance().setItem("logedincurrentbalance", newBalance);
													ATMSession.getInstance().setItem("logedinwithdrawamnt", txtwithdraw.getText().trim());
													ATMSession.getInstance().setItem("logedinwithdrawdate", strDate);
												}else{
													JOptionPane.showMessageDialog(null,
															"There is some issue. Contact Administrator.",
															"WARNING", JOptionPane.WARNING_MESSAGE);
													txtwithdraw.setText("");
													txtwithdraw.setText("");
													txtwithdraw.requestFocus(true);
												}
												
									}else{
										JOptionPane.showMessageDialog(null,
												"There is some issue. Contact Administrator.",
												"WARNING", JOptionPane.WARNING_MESSAGE);
										txtwithdraw.setText("");
										txtwithdraw.setText("");
										txtwithdraw.requestFocus(true);
									}
										
								}
							}


					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"There is some issue. Contact Administrator.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
					txtwithdraw.setText("");
					txtwithdraw.setText("");
					txtwithdraw.requestFocus(true);
				}

				if (valuated) {
					
					WithDrawTransactionConfirmation log=new WithDrawTransactionConfirmation();
					Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
					log.setLocation(0,0);
					log.setSize(dim.width,dim.height);
					log.setTitle("CashwithDrawTransactionConfirmation");
					log.setResizable(false);
					log.setVisible(true);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "You dont have enough balance to withdraw.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
					txtwithdraw.setText("");
					txtwithdraw.setText("");
					txtwithdraw.requestFocus(true);
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
	}
	
	public static void main(String[]args){
		CashWithDraw log=new CashWithDraw();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(x, y);
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);

		log.setTitle("CashWithDraw");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}