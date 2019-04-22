package com.atm;

/**
 * @(#)second_atm.java
 *
 *
 * @author 
 * 
 */

import javax.swing.*;

import com.util.Utility;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminRegister extends JFrame implements ActionListener {
	JButton btnRegister = new JButton(new ImageIcon("btnRegister.jpg"));
	JButton btnBack = new JButton(new ImageIcon("btnBack.jpg"));
	Font f1 = new Font("", Font.BOLD, 10);
	JLabel lblUser = new JLabel("User Name ", JLabel.RIGHT);
	JLabel lblFName = new JLabel("First Name ", JLabel.RIGHT);
	JLabel lblVPass = new JLabel("Verify Pin Number ", JLabel.RIGHT);
	JLabel lblLName = new JLabel("Last Name ", JLabel.RIGHT);
	JLabel lblPass = new JLabel("Pin Number ", JLabel.RIGHT);
	JLabel lblBday = new JLabel("B-Day ", JLabel.RIGHT);
	// JLabel lblCash = new JLabel("Cash Tender ", JLabel.RIGHT);
	JLabel lblday = new JLabel("Day");
	JLabel lblMonth = new JLabel("Month");
	JLabel lblyer = new JLabel("Year");
	JTextField txtUser = new JTextField(20);
	JTextField txtFName = new JTextField(20);
	JTextField txtLName = new JTextField(20);
	JPasswordField txtPass = new JPasswordField(20);
	JPasswordField txtVPass = new JPasswordField(20);
	JTextField txtday = new JTextField(20);
	JTextField txtmonth = new JTextField(20);
	JTextField txtyear = new JTextField(20);

	// JTextField txtCash = new JTextField(20);

	// JButton btnCret = new JButton(new ImageIcon("reg.jpg"));

	public void clear() {

		txtUser.setText("");
		txtFName.setText("");
		txtPass.setText("");
		txtLName.setText("");
		txtVPass.setText("");
		txtday.setText("");
		txtmonth.setText("");
		txtyear.setText("");

	}

	public AdminRegister() {
		super("Project");
		JPanel pane = new JPanel();
		pane.setLayout(null);
		lblyer.setFont(f1);
		lblday.setFont(f1);
		lblMonth.setFont(f1);
		lblUser.setBounds(500, 200, 120, 25);
		pane.add(lblUser);
		txtUser.setBounds(650, 200, 150, 25);
		pane.add(txtUser);
		lblFName.setBounds(500, 250, 120, 25);
		pane.add(lblFName);
		txtFName.setBounds(650, 250, 150, 25);
		pane.add(txtFName);
		lblLName.setBounds(500, 300, 120, 25);
		pane.add(lblLName);
		txtLName.setBounds(650, 300, 150, 25);
		pane.add(txtLName);
		lblPass.setBounds(500, 350, 120, 25);
		pane.add(lblPass);
		txtPass.setBounds(650, 350, 150, 25);
		pane.add(txtPass);
		lblVPass.setBounds(500, 400, 120, 25);
		pane.add(lblVPass);
		txtVPass.setBounds(650, 400, 150, 25);
		pane.add(txtVPass);
		
		lblBday.setBounds(500, 450, 120, 25);
		pane.add(lblBday);
		txtday.setBounds(650, 450, 50, 25);
		pane.add(txtday);
		txtmonth.setBounds(700, 450, 50, 25);
		pane.add(txtmonth);
		txtyear.setBounds(750, 450, 50, 25);
		pane.add(txtyear);
		lblday.setBounds(650, 430, 70, 20);
		pane.add(lblday);
		lblMonth.setBounds(700, 430, 70, 20);
		pane.add(lblMonth);
		lblyer.setBounds(750, 430, 70, 20);
		pane.add(lblyer);
		lblUser.setForeground(Color.white);
		lblFName.setForeground(Color.white);
		lblVPass.setForeground(Color.white);
		lblLName.setForeground(Color.white);
		lblPass.setForeground(Color.white);
		lblBday.setForeground(Color.white);
		lblday.setForeground(Color.white);
		lblMonth.setForeground(Color.white);
		lblyer.setForeground(Color.white);

		btnRegister.setBounds(1000, 570, 320, 80);
		btnBack.setBounds(50, 570, 320, 80);
		pane.add(btnRegister);
		pane.add(btnBack);
		btnRegister.addActionListener(this);
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

		Object source = e.getSource();
		if (source == btnRegister) {
			
			
			if(txtUser.getText().trim().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Username.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtUser.requestFocus(true);

			}else if(txtFName.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Firstname.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtFName.requestFocus(true);
			}else if(txtLName.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Lastname.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtLName.requestFocus(true);
			}else if(txtPass.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter PIN.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtPass.requestFocus(true);
			}else if(!txtPass.getText().trim().isEmpty() && txtPass.getText().trim().length()!=4){
				
				JOptionPane.showMessageDialog(null,
						"Please enter 4 digit PIN.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtPass.setText("");
				txtPass.requestFocus(true);
			}else if(txtVPass.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Verify PIN.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtVPass.requestFocus(true);
			}else if(!txtVPass.getText().trim().isEmpty() && txtVPass.getText().trim().length()!=4){
				
				JOptionPane.showMessageDialog(null,
						"Please enter 4 digit PIN.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtVPass.setText("");
				txtVPass.requestFocus(true);
			}else if(!txtVPass.getText().trim().isEmpty() && !txtPass.getText().trim().isEmpty() && !txtVPass.getText().equals(txtPass.getText())){
				
				JOptionPane.showMessageDialog(null,
						"Verify PIN is not matching with the PIN.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtVPass.setText("");
				txtVPass.requestFocus(true);
			}
			else if(txtday.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Day.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtday.requestFocus(true);
			}else if(!txtday.getText().trim().isEmpty() && txtday.getText().trim().length()!=2){
				
				JOptionPane.showMessageDialog(null,
						"Please enter 2 digit day",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtday.setText("");
				txtday.requestFocus(true);
				
			}else if(!txtday.getText().trim().isEmpty() && !Utility.numberOrNot(txtday.getText().trim())){
				
				JOptionPane.showMessageDialog(null,
						"Please enter only number",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtday.setText("");
				txtday.requestFocus(true);
				
			}
			else if(txtmonth.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter month.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtmonth.requestFocus(true);
			}else if(!txtmonth.getText().trim().isEmpty() && txtmonth.getText().trim().length()!=2 ){
				JOptionPane.showMessageDialog(null,
						"Please enter 2 digit Month",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtmonth.setText("");
				txtmonth.requestFocus(true);
			}else if(!txtmonth.getText().trim().isEmpty() && !Utility.numberOrNot(txtmonth.getText().trim())){
				
				JOptionPane.showMessageDialog(null,
						"Please enter only number",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtmonth.setText("");
				txtmonth.requestFocus(true);
				
			}
			else if(txtyear.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Year.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtyear.requestFocus(true);
			}else if(!txtyear.getText().trim().isEmpty() && txtyear.getText().trim().length()!=4 ){
				JOptionPane.showMessageDialog(null,
						"Please enter 4 digit year",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtyear.setText("");
				txtyear.requestFocus(true);
				
			}else if(!txtyear.getText().trim().isEmpty() && !Utility.numberOrNot(txtyear.getText().trim())){
				
				JOptionPane.showMessageDialog(null,
						"Please enter only number",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtyear.setText("");
				txtyear.requestFocus(true);
				
			}else{
				
				Connection con = null;
				int processed=0;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/atmfingerprint",
							"root", "1111");
					//String birday = txtday.getText()+"/"+txtmonth.getText()+"/"+txtyear.getText();
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
				    sdf = new SimpleDateFormat("dd/MM/yyyy");
				    String  strDate = sdf.format(date);
				    System.out.println("formatted date in dd/MM/yyyy : " + strDate);

					Statement ps = con.createStatement();
					String query = "insert into adminreg(username,firstname,lastname,pinnumber,bday,bmonth,byear,registrationdate) values('"
					+ txtUser.getText()
					+ "','"
					+ txtFName.getText()
					+ "','"
					+ txtLName.getText()
					+ "','"
					+ txtPass.getText()
					+ "','"
					+ txtday.getText()
					+ "','"
					+ txtmonth.getText()
					+ "','"
					+ txtyear.getText()
					+ "','"
					+ strDate+"')";	
					System.out.println(query);
					processed = ps.executeUpdate(query);

					
					con.close();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"There is some issue. Contact Administrator.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
				}

				if (processed == 1) {
					JOptionPane.showMessageDialog(null,
							"Successfully Updated.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
					AddOrEditAdmin log = new AddOrEditAdmin();
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					// this.setLocation(x, y);
					log.setLocation(0, 0);
					log.setSize(dim.width, dim.height);

					log.setTitle("Add/Edit-Admin");
					log.setResizable(false);
					log.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"There is some issue. Contact Administrator.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}


				
				
			
			
		
		} else if (source == btnBack) {
			AddOrEditAdmin log = new AddOrEditAdmin();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			// this.setLocation(x, y);
			log.setLocation(0, 0);
			log.setSize(dim.width, dim.height);

			log.setTitle("Add/Edit-Admin");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		}

	}

	public static void main(String[] args) {
		AdminRegister log = new AdminRegister();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0, 0);
		log.setSize(dim.width, dim.height);
		log.setTitle("Admin-Register");
		log.setResizable(false);
		log.setVisible(true);
	}

}
