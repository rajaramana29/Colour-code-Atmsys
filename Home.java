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

public class Home extends JFrame implements ActionListener{
	
	
	JButton btnUser= 
		new JButton(new ImageIcon("btnUser.jpg"));
	JButton btnAdmin=new JButton(new ImageIcon("btnAdmin.jpg"));
	//JButton btnBlock = new JButton("Lock Account >>");

	Connection cn;
	//ResultSet rs;
	Statement st;
	PreparedStatement ps;
	public Home(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(btnUser);
		pane.add(btnAdmin);
		//-----Setting the location of the components
		btnUser.setBounds(50,570,320,80);
		btnAdmin.setBounds(1000,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnUser.addActionListener(this);
		btnAdmin.addActionListener(this);
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		lbl.setBounds(405,0,dim.width-800,dim.height);
		pane.add(lbl);

	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnUser){
					
			
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			
			User panel = new User();
			panel.setSize(dim.width,dim.height);
			panel.setVisible(true);
			panel.setTitle("User-Login");
			panel.setResizable(false);
			panel.setLocation(0,0);
			dispose();
				
		}else if(source==btnAdmin){
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();			
				Admin panel = new Admin();
				panel.setSize(dim.width,dim.height);
				panel.setVisible(true);
				panel.setTitle("Admin-Login");
				panel.setResizable(false);
				panel.setLocation(0,0);
				dispose();
		
		}
		
	}
	
	public static void main(String[]args){
		Home log=new Home();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);
		log.setTitle("Home");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}