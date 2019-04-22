package com.atm;

/**
 * @(#)second_atm.java
 *
 *
 * @author 
 * 
 */

import javax.swing.*;

import com.atm.model.User;
import com.digitalpersona.onetouch.ui.swing.Enrollment.EnrollmentForm;
import com.util.ATMSession;
import com.util.Utility;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserRegisterFingerScan extends JFrame implements ActionListener {

	JButton btnScan = new JButton(new ImageIcon("btnScan.jpg"));
	JButton btnBack = new JButton(new ImageIcon("btnBack.jpg"));

	Font f1 = new Font("", Font.BOLD, 10);
		
	JLabel lblUser = new JLabel("Start your finger scanning by click on scan button.", JLabel.RIGHT);
	
	//	JTextField txtCash = new JTextField(20);

	//JButton btnCret = new JButton(new ImageIcon("reg.jpg"));

	Connection cn;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	PreparedStatement ps1;

	public void clear() {

	}

	public UserRegisterFingerScan() {
		super("Project");

		JPanel pane = new JPanel();
		pane.setLayout(null);

		lblUser.setBounds(400, 200, 420, 25);
		pane.add(lblUser);
			
		lblUser.setForeground(Color.white); 
		btnScan.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		pane.add(btnScan);
		pane.add(btnBack);
		btnScan.addActionListener(this);
		btnBack.addActionListener(this);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));

		lbl.setBounds(405, 0, dim.width - 800, dim.height);
		pane.add(lbl);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Registration Form"));

		

	}

	public void actionPerformed(ActionEvent e) {
		EnrollmentForm form = new EnrollmentForm(this); 
		Object source = e.getSource();
		if (source == btnScan) {
//-----------------------------Finger scan start
			onEnroll(form);
			dispose();
			
//-----------------------------Finger scan end			
	
			}else if(source == btnBack){
				ATMSession.getInstance().removeItem("registereduser");
				AdminHome log=new AdminHome();
				
				Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
				log.setLocation(0,0);
				log.setSize(dim.width,dim.height);
				log.setTitle("Admin-Home");
				log.setResizable(false);
				log.setVisible(true);
				
				dispose();
		}
	}
	
	private void onEnroll( EnrollmentForm form) {
		
		form.setVisible(true);
	}
	public static void main(String[]args){
		UserRegisterFingerScan log=new UserRegisterFingerScan();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);
		log.setTitle("User-FingerScan");
		log.setResizable(false);
		log.setVisible(true);
	}
	
	

}

// else{
// JOptionPane.showMessageDialog(null,"Verify your password.","ATM",JOptionPane.INFORMATION_MESSAGE);
// }

