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

import com.util.AESAlgorithm;
import com.util.ATMSession;
import com.util.Utility;

import java.awt.event.*;
import java.sql.*;

public class ColorCodeVerification extends JFrame implements ActionListener{
	
//	JTextField txtmobile=new JTextField(25);
	JLabel colorcode=new JLabel("Color code: ");
	JButton btnColorCodeverification=new JButton(new ImageIcon("btnColorCodeverification.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));
	JLabel lblR = new JLabel("R");
	JLabel lblG = new JLabel("G");
	JLabel lblB = new JLabel("B");
	JTextField txtR = new JTextField(20);
	JTextField txtG = new JTextField(20);
	JTextField txtB = new JTextField(20);

	Font f1 = new Font("", Font.BOLD, 10);
	public ColorCodeVerification(){
		lblR.setFont(f1);
		lblG.setFont(f1);
		lblB.setFont(f1);

		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		//pane.add(txtmobile);
		
		pane.add(colorcode);
		pane.add(btnColorCodeverification);
		pane.add(btnBack);
		//-----Setting the location of the components
		colorcode.setBounds(550,300,80,20);
		colorcode.setForeground(Color.white);
		lblR.setBounds(650, 270, 70, 20);
		pane.add(lblR);
		lblG.setBounds(700, 270, 70, 20);
		pane.add(lblG);
		lblB.setBounds(750, 270, 70, 20);
		pane.add(lblB);
		lblR.setForeground(Color.white);
		lblG.setForeground(Color.white);
		lblB.setForeground(Color.white);
		txtR.setBounds(650, 300, 50, 25);
		pane.add(txtR);
		txtG.setBounds(700, 300, 50, 25);
		pane.add(txtG);
		txtB.setBounds(750, 300, 50, 25);
		pane.add(txtB);
		btnColorCodeverification.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnColorCodeverification.addActionListener(this);
		btnBack.addActionListener(this);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
			lbl.setBounds(405,0,dim.width-800,dim.height);
		pane.add(lbl);

	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnColorCodeverification){
			if (txtR.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter R code.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtR.requestFocus(true);
			}else if (txtG.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter G code.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtG.requestFocus(true);
			}else if (txtB.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter B code.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtB.requestFocus(true);
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
				AESAlgorithm aesa = new AESAlgorithm();
				rs1 = ps.executeQuery("select * from clientreg where username='"
						+ username + "'" );

				while (rs1.next()) {

					System.out.println(rs1.getString("r"));
					System.out.println(rs1.getString("g"));
					System.out.println(rs1.getString("b"));
					System.out.println(aesa.decrypt(rs1.getString("r")));
					System.out.println(aesa.decrypt(rs1.getString("g")));
					System.out.println(aesa.decrypt(rs1.getString("b")));
					if(txtR.getText().trim().equals(aesa.decrypt(rs1.getString("r"))) && 
							txtG.getText().trim().equals(aesa.decrypt(rs1.getString("g"))) && 
							txtB.getText().trim().equals(aesa.decrypt(rs1.getString("b"))))
							valuated = true;
						}


				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"There is some issue. Contact Administrator.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtR.setText("");
				txtG.setText("");
				txtB.setText("");
				txtR.requestFocus(true);
			}

			if (valuated) {
				
				ChooseTransactionType log=new ChooseTransactionType();
				Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
				log.setLocation(0,0);
				log.setSize(dim.width,dim.height);
				log.setTitle("Choose Transaction type");
				log.setResizable(false);
				log.setVisible(true);

				dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Please enter correct R G B Codes.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtR.setText("");
				txtG.setText("");
				txtB.setText("");
				txtR.requestFocus(true);
			}

			}	
			
		}else if(source==btnBack){
			ChooseAuthentcationType log=new ChooseAuthentcationType();
			
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("Choose Authentication type");
			log.setResizable(false);
			log.setVisible(true);


			dispose();
		}
	}
	
	public static void main(String[]args){
		ColorCodeVerification log=new ColorCodeVerification();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);
		log.setTitle("ColorCodeVerification");
		log.setResizable(false);
		log.setVisible(true);
	
	}
}