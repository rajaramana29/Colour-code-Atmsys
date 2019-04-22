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

public class AddOrEditAdmin extends JFrame implements ActionListener {

	JButton btnAddAdmin = new JButton(new ImageIcon("btnAddAdmin.jpg"));
	JButton btnEditAdmin = new JButton(new ImageIcon("btnEditAdmin.jpg"));
	JButton btnBack = new JButton(new ImageIcon("btnBack.jpg"));
	// JButton btnBlock = new JButton("Lock Account >>");

	Connection cn;
	// ResultSet rs;
	Statement st;
	PreparedStatement ps;

	public AddOrEditAdmin() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane = new JPanel();
		pane.setLayout(null);
		// ----Adding Components into your Frame
		pane.add(btnAddAdmin);
		pane.add(btnEditAdmin);
		pane.add(btnBack);
		// pane.add(btnRegister);
		// pane.add(btnBlock);
		// -----Setting the location of the components
		btnEditAdmin.setBounds(1000, 470, 320, 80);
		btnAddAdmin.setBounds(1000, 570, 320, 80);
		btnBack.setBounds(50, 570, 320, 80);
		// btnRegister.setBounds(125,70,83,20);
		// btnBlock.setBounds(55,90,150,20);
		// -----Adding the an actionlistener to the buttons
		btnEditAdmin.addActionListener(this);
		btnAddAdmin.addActionListener(this);
		btnBack.addActionListener(this);
		// btnRegister.addActionListener(this);
		// btnBlock.addActionListener(this);

		// btnBlock.setForeground(Color.yellow);
		// btnBlock.setBackground(Color.black);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));

		lbl.setBounds(405, 0, dim.width - 800, dim.height);
		pane.add(lbl);

		// connection

		// try{
		Connection conn;
		try {
			cn = DriverManager.getConnection("jdbc:ucanaccess://project.mdb");
			Statement s = cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// cn = DriverManager.getConnection("jdbc:odbc:project");
		// }catch(ClassNotFoundException e) {
		// System.err.println("Failed to load driver");
		// e.printStackTrace();

		// }catch(SQLException e){
		// System.err.println("Unable to connect");
		// e.printStackTrace();

		// }
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == btnAddAdmin) {
			AdminRegister log = new AdminRegister();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("Admin-Register");
			log.setResizable(false);
			log.setVisible(true);			dispose();

		} else if (source == btnBack) {
			AdminHome log = new AdminHome();

			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0, 0);
			log.setSize(dim.width, dim.height);
			log.setTitle("Admin-Home");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		} else if (source == btnEditAdmin) {
			EditAdminSearch log = new EditAdminSearch();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0, 0);
			log.setSize(dim.width, dim.height);
			log.setTitle("Edit-Admin-Search");
			log.setResizable(false);
			log.setVisible(true);
			dispose();

		}

		/*
		 * else if(source == btnBlock){
		 * 
		 * int n=JOptionPane.showConfirmDialog(null,
		 * "If you lock this account,you cannot access this Anymore.Are you sure you want to Lock this account?"
		 * ,"Warning",JOptionPane.YES_NO_OPTION); if(n==JOptionPane.YES_OPTION){
		 * 
		 * try{
		 * 
		 * 
		 * st=cn.createStatement(); String strUser=""; String strPass="";
		 * 
		 * ResultSet rs=st.executeQuery("SELECT * FROM
		 * 
		 * WHERE UserName='"+txtuser.getText()+"'"); while(rs.next()){
		 * strUser=rs.getString(1); strPass=rs.getString(5); }
		 * 
		 * if(strUser.equals(txtuser.getText())){
		 * if(strPass.equals(txtpass.getText())){
		 * 
		 * ps = cn.prepareStatement("DELETE FROM tbl_Info WHERE UserName ='"+
		 * txtuser.getText() + "'"); ps.executeUpdate();
		 * JOptionPane.showMessageDialog
		 * (null,"Your Account has been successfully Lock."
		 * ,"ATM",JOptionPane.INFORMATION_MESSAGE); txtuser.requestFocus(true);
		 * 
		 * txtuser.setText(""); txtpass.setText(""); }else{
		 * JOptionPane.showMessageDialog
		 * (null,"Username found but the Pin Number is incorrect!"
		 * ,"Security Pass",JOptionPane.WARNING_MESSAGE);
		 * txtpass.requestFocus(true); } }else{
		 * JOptionPane.showMessageDialog(null
		 * ,"Username is incorrect!","Security Pass"
		 * ,JOptionPane.WARNING_MESSAGE); txtuser.requestFocus(true); } }
		 * catch(SQLException s){
		 * System.out.print("SQL statement is not executed!"); } catch(Exception
		 * j){ j.printStackTrace(); }
		 * 
		 * } }
		 */
	}

	public static void main(String[] args) {
		AddOrEditAdmin log = new AddOrEditAdmin();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// this.setLocation(x, y);
		log.setLocation(0, 0);
		log.setSize(dim.width, dim.height);

		log.setTitle("Add/Edit-Admin");
		log.setResizable(false);
		log.setVisible(true);

	}
}