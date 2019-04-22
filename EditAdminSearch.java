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

public class EditAdminSearch extends JFrame implements ActionListener{
	
	JTextField txtuser=new JTextField(25);
	//JPasswordField txtpass=new JPasswordField(25);
	JLabel lbluser=new JLabel("Username: ");
//	JLabel lblpass=new JLabel("Pin Number: ");
	JButton btnFindAdmin=new JButton(new ImageIcon("btnFindAdmin.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));
	//JButton btnBlock = new JButton("Lock Account >>");

	Connection cn;
	//ResultSet rs;
	Statement st;
	PreparedStatement ps;
	public EditAdminSearch(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtuser);
		//pane.add(txtpass);
		pane.add(lbluser);
		//pane.add(lblpass);	
		pane.add(btnFindAdmin);
		pane.add(btnBack);
		//-----Setting the location of the components
		lbluser.setBounds(550,300,80,20);
		lbluser.setForeground(Color.white);
		txtuser.setBounds(650,300,100,20);
		btnFindAdmin.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnFindAdmin.addActionListener(this);
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
		if(source==btnFindAdmin){
			
			Connection con = null;
			ResultSet rs1 = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/atmfingerprint",
						"root", "1111");
				Statement ps = con.createStatement();

				rs1 = ps.executeQuery("select * from adminreg where username='"
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
			ATMSession.getInstance().setItem("searcheduserid", username);	
			EditAdmin log=new EditAdmin();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("Edit-Admin");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
			}else{
				JOptionPane.showMessageDialog(null,
						"No Such User.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtuser.setText("");
				txtuser.requestFocus(true);

			}

		}else if(source==btnBack){
			
			AddOrEditAdmin log = new AddOrEditAdmin();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0, 0);
			log.setSize(dim.width, dim.height);

			log.setTitle("Add/Edit-Admin");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		
		}
	}
	
	public static void main(String[]args){
		EditAdminSearch log=new EditAdminSearch();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);

		log.setTitle("Edit-Admin-Search");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}