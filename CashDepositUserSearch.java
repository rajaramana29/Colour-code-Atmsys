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

public class CashDepositUserSearch extends JFrame implements ActionListener{
	
	JTextField txtuser=new JTextField(25);
	JLabel lbluser=new JLabel("Username: ");
	JButton btnFindUser=new JButton(new ImageIcon("btnFindUser.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));

	public CashDepositUserSearch(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtuser);
		pane.add(lbluser);
		pane.add(btnFindUser);
		pane.add(btnBack);
		//-----Setting the location of the components
		lbluser.setBounds(550,300,80,20);
		lbluser.setForeground(Color.white);
		txtuser.setBounds(650,300,100,20);
		btnFindUser.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnFindUser.addActionListener(this);
		btnBack.addActionListener(this);
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
			lbl.setBounds(405,0,dim.width-800,dim.height);
		pane.add(lbl);

		
	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		boolean valuated = false;
		String username = txtuser.getText();		
		if(source==btnFindUser){
			Connection con = null;
			ResultSet rs1 = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/atmfingerprint",
						"root", "1111");
				Statement ps = con.createStatement();

				rs1 = ps.executeQuery("select * from clientreg where username='"
						+ username + "'");

				while (rs1.next()) {

					System.out.println(rs1.getString("username"));
					System.out.println(rs1.getString("pinnumber"));
					if (username.equals(rs1.getString("username"))) {
							valuated = true;
					}

				}

				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"No Such User.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtuser.setText("");
				txtuser.requestFocus(true);
			}

			if(valuated){
			ATMSession.getInstance().setItem("usersearcheduserid", username);	
			/*CashDepositDetails log=new CashDepositDetails();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("Cash Deposit Details");
			log.setResizable(false);
			log.setVisible(true);*/
			CashDepositDetails log = new CashDepositDetails();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0, 0);
			log.setSize(dim.width, dim.height);
			log.setTitle("Cash Deposit Details");
			log.setResizable(false);
			TableCashDeposit newContentPane = new TableCashDeposit();
	        newContentPane.setOpaque(true); //content panes must be opaque
	        newContentPane.setBounds(50, 370, 320, 80);
	        log.setContentPane(newContentPane);
	        log.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        log.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	            	ATMSession.getInstance().removeItem("usersearcheduserid");
	    			CashDepositUserSearch log1 = new CashDepositUserSearch();
	    			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    			// this.setLocation(x, y);
	    			log1.setLocation(0, 0);
	    			log1.setSize(dim.width, dim.height);
	    			log1.setTitle("CashDeposit-User-Search");
	    			log1.setResizable(false);
	    			log1.setVisible(true);
	            	log.dispose();
	            	
	            }
	        });
			log.setVisible(true);
			//CashDepositDetails.createAndShowGUI();
			dispose();
			}else{
				JOptionPane.showMessageDialog(null,
						"No Such User.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtuser.setText("");
				txtuser.requestFocus(true);

			}


		}else if(source==btnBack){
			AdminHome log=new AdminHome();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			//this.setLocation(x, y);
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);

			log.setTitle("Admin-Home");
			log.setResizable(false);
			log.setVisible(true);

			dispose();
		
		}
	}
	public static void main(String[]args){
		CashDepositUserSearch log=new CashDepositUserSearch();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(x, y);
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);

		log.setTitle("CashDeposit-User-Search");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}