package com.atm;

/**
 * @(#)second_atm.java
 *
 *
 * @author 
 * 
 */

import javax.swing.*;

import com.util.ATMSession;
import com.util.Utility;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EditUser extends JFrame implements ActionListener {

	JButton btnConfirmEdit = new JButton(new ImageIcon("btnConfirmEdit.jpg"));
	JButton btnBack = new JButton(new ImageIcon("btnBack.jpg"));

	Font f1 = new Font("", Font.BOLD, 10);
		
	JLabel lblUser = new JLabel("User Name ", JLabel.RIGHT);
	
	JLabel lblFName = new JLabel("First Name ", JLabel.RIGHT);
	JLabel lblLName = new JLabel("Last Name ", JLabel.RIGHT);
	JLabel lblBday = new JLabel("B-Day ", JLabel.RIGHT);
	JLabel lblday = new JLabel("Day");
	JLabel lblMonth = new JLabel("Month");
	JLabel lblyer = new JLabel("Year");

	JTextField txtUser = new JTextField(20);
	JTextField txtFName = new JTextField(20);
	JTextField txtLName = new JTextField(20);
	JTextField txtday = new JTextField(20);
	JTextField txtmonth = new JTextField(20);
	JTextField txtyear = new JTextField(20);
	JLabel lblemail = new JLabel("Email:", JLabel.RIGHT);
	JTextField txtemail=new JTextField(25);


	public void clear() {

		txtUser.setText("");
		txtFName.setText("");
		txtLName.setText("");
	}

	public EditUser() {
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


		lblBday.setBounds(500, 350, 120, 25);
		pane.add(lblBday);
		txtday.setBounds(650, 350, 50, 25);
		pane.add(txtday);
		txtmonth.setBounds(700,350, 50, 25);
		pane.add(txtmonth);
		txtyear.setBounds(750, 350, 50, 25);
		pane.add(txtyear);
		lblday.setBounds(650, 330, 70, 20);
		pane.add(lblday);
		lblMonth.setBounds(700, 330, 70, 20);
		pane.add(lblMonth);
		lblyer.setBounds(750, 330, 70, 20);
		pane.add(lblyer);
		lblemail.setBounds(500, 400, 120, 25);
		pane.add(lblemail);
		txtemail.setBounds(650, 400, 150, 25);
		pane.add(txtemail);

		
		lblUser.setForeground(Color.white); 
		lblFName.setForeground(Color.white); 
		 lblLName.setForeground(Color.white); 
		 lblBday.setForeground(Color.white); 
		 lblday.setForeground(Color.white); 
		 lblMonth.setForeground(Color.white); 
		 lblyer.setForeground(Color.white); 
		 lblemail.setForeground(Color.white); 

		btnConfirmEdit.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		pane.add(btnConfirmEdit);
		pane.add(btnBack);
		btnConfirmEdit.addActionListener(this);
		btnBack.addActionListener(this);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));

		lbl.setBounds(405, 0, dim.width - 800, dim.height);
		pane.add(lbl);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Edit User Form"));
		String username =null;
		if(ATMSession.getInstance().getItem("usersearcheduserid")!=null){
		txtUser.setText(ATMSession.getInstance().getItem("usersearcheduserid").toString());
		username = ATMSession.getInstance().getItem("usersearcheduserid").toString();
		}
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
				System.out.println(rs1.getString("firstname"));
				System.out.println(rs1.getString("lastname"));
				System.out.println(rs1.getString("bday"));
				System.out.println(rs1.getString("bmonth"));
				System.out.println(rs1.getString("byear"));
				txtUser.setText(rs1.getString("username"));
				txtFName.setText(rs1.getString("firstname"));
				txtLName.setText(rs1.getString("lastname"));
				txtday.setText(rs1.getString("bday"));
				txtmonth.setText(rs1.getString("bmonth"));
				txtyear.setText(rs1.getString("byear"));
				txtemail.setText(rs1.getString("email"));
			}

			con.close();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There is some issue. Contact Administrator.",
					"WARNING", JOptionPane.WARNING_MESSAGE);
		}



	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == btnConfirmEdit) {
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
			}else if(txtday.getText().trim().isEmpty()){
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
				
			}else if(txtemail.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						"Please enter Email Address.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtemail.requestFocus(true);
			}else if(!txtemail.getText().trim().isEmpty() && (!txtemail.getText().trim().contains("@") || !txtemail.getText().trim().contains(".") ) ){
				JOptionPane.showMessageDialog(null,
						"Please enter a valid email address",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtemail.setText("");
				txtemail.requestFocus(true);
				
			}
			else{
				
				Connection con = null;
				int processed=0;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/atmfingerprint",
							"root", "1111");
					//String birday = txtday.getText()+"/"+txtmonth.getText()+"/"+txtyear.getText();
								String username = ATMSession.getInstance().getItem("usersearcheduserid").toString();
					//System.out.println(query);
								Statement ps = con.createStatement();
								String query= "update clientreg set username='"
										+ txtUser.getText() + "',firstname='" + txtFName.getText()+ "',lastname='" + txtLName.getText()
										+ "',bday='" + txtday.getText() + "',bmonth='"
										+ txtmonth.getText() + "',byear='" + txtyear.getText() + "',email='"+txtemail.getText().trim()+"' where username='"
										+ username + "'";
								System.out.println("Query==="+query);
					processed = ps.executeUpdate(query);

					
					con.close();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"There is some issue. Contact Administrator.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
				}

				if (processed > 0) {
					JOptionPane.showMessageDialog(null,
							"Successfully Edited.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
					AdminHome log=new AdminHome();
					Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
					//this.setLocation(x, y);
					log.setLocation(0,0);
					log.setSize(dim.width,dim.height);

					log.setTitle("Admin-Home");
					log.setResizable(false);
					log.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"There is some issue. Contact Administrator.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}


		}else if(source == btnBack){
			ATMSession.getInstance().removeItem("searcheduserid");
			EditUserSearch log=new EditUserSearch();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			//this.setLocation(x, y);
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);

			log.setTitle("Edit-User-Search");
			log.setResizable(false);
			log.setVisible(true);


			dispose();
		}
	}
	public static void main(String[]args){
		EditUser log=new EditUser();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);
		log.setTitle("Edit-User");
		log.setResizable(false);
		log.setVisible(true);
	}

}

// else{
// JOptionPane.showMessageDialog(null,"Verify your password.","ATM",JOptionPane.INFORMATION_MESSAGE);
// }

