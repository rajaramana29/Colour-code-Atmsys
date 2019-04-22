package com.atm;

/**
 * @(#)second_atm.java
 *
 *
 * @author 
 * 
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CashWithDrawDetails extends JFrame implements ActionListener {

	//JButton btnConfirmEdit = new JButton(new ImageIcon("btnConfirmEdit.jpg"));
	JButton btnBack = new JButton(new ImageIcon("btnBack.jpg"));
/*
	Font f1 = new Font("", Font.BOLD, 10);
		
	JLabel lblUser = new JLabel("User Name ", JLabel.RIGHT);
	
	JLabel lblFName = new JLabel("First Name ", JLabel.RIGHT);
	JLabel lblVPass = new JLabel("Verify Pin Number ", JLabel.RIGHT);
	JLabel lblLName = new JLabel("Last Name ", JLabel.RIGHT);
	JLabel lblPass = new JLabel("Pin Number ", JLabel.RIGHT);
	JLabel lblBday = new JLabel("B-Day ", JLabel.RIGHT);
	//JLabel lblCash = new JLabel("Cash Tender ", JLabel.RIGHT);
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
*///	JTextField txtCash = new JTextField(20);

	//JButton btnCret = new JButton(new ImageIcon("reg.jpg"));

	Connection cn;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	PreparedStatement ps1;

	public void clear() {

/*		txtUser.setText("");
		txtFName.setText("");
		txtPass.setText("");
		txtLName.setText("");
		txtVPass.setText("");*/
	}

	public CashWithDrawDetails() {
		super("Project");

		JPanel pane = new JPanel();
		pane.setLayout(null);

/*		lblyer.setFont(f1);
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
		txtVPass.setBounds(650, 350, 150, 25);
		pane.add(txtVPass);

		lblVPass.setBounds(500, 400, 120, 25);
		pane.add(lblVPass);
		txtPass.setBounds(650, 400, 150, 25);
		pane.add(txtPass);

		lblBday.setBounds(500, 450, 120, 25);
		pane.add(lblBday);
		txtday.setBounds(650, 450, 50, 25);
		pane.add(txtday);
		txtmonth.setBounds(700,450, 50, 25);
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
		//JLabel lblCash = new JLabel("Cash Tender ", JLabel.RIGHT);
		 lblday.setForeground(Color.white); 
		 lblMonth.setForeground(Color.white); 
		 lblyer.setForeground(Color.white); 

		lblCash.setBounds(500, 275, 120, 25);
		pane.add(lblCash);
		txtCash.setBounds(650, 275, 100, 25);
		pane.add(txtCash);

		btnCret.setBounds(129, 310, 120, 35);
		pane.add(btnCret);
		btnCret.addActionListener(this);
		btnConfirmEdit.setBounds(1000,570,320,80);
*/		btnBack.setBounds(50,570,320,80);
		//pane.add(btnConfirmEdit);
		pane.add(btnBack);
		//btnConfirmEdit.addActionListener(this);
		btnBack.addActionListener(this);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));

		lbl.setBounds(405, 0, dim.width - 800, dim.height);
		pane.add(lbl);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Cash withdraw Details"));

		try {
			cn = DriverManager.getConnection("jdbc:ucanaccess://project.mdb");
			Statement s = cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if(source == btnBack){
			CashWithDrawUserSearch log=new CashWithDrawUserSearch();
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			//this.setLocation(x, y);
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);

			log.setTitle("CashWithDraw-User-Search");
			log.setResizable(false);
			log.setVisible(true);


			dispose();
		}
/*		if (source == btnCret) {
			String suser = txtUser.getText();
			String sname = txtFName.getText();
			String spass = txtPass.getText();
			String slname = txtLName.getText();
			String svpass = txtVPass.getText();

			
			 * int cash = Integer.parseInt(txtCash.getText()); // int nem =
			 * Integer.parseInt(txtFName.getText());
			 * if(txtPass.getText().equals(txtVPass.getText())){ if(cash >= 100
			 * && cash <= 50000){ // if(txtUser.length() == 0) // if(nem>=0 ||
			 * nem<=0 ){ // if(txtLName.equals("")){
			 * 
			 * // int d=Integer.parseInt(txtday.getText()); // try{ if(d!=0 &&
			 * d>0){
			 

			if ((suser.length() == 0 || sname.length() == 0
					|| spass.length() == 0 || slname.length() == 0 || svpass
						.length() == 0)) {
				JOptionPane.showMessageDialog(null, "Some Fields are empty",
						"WARNING", JOptionPane.WARNING_MESSAGE);
			} else {
				if (spass.equals(svpass)) {
					boolean xx = false;
					try {

						st = cn.createStatement();
						rs = st.executeQuery("SELECT * FROM tbl_list WHERE Password = '"
								+ spass + "'");

						while (rs.next()) {
							String spass2 = rs.getString(4);
							xx = true;
							JOptionPane.showMessageDialog(null,
									"Pincode already used", "Information",
									JOptionPane.INFORMATION_MESSAGE);
						}
						st.close();

						if (xx == false) {
							try {

								st = cn.createStatement();
								
								 * String pass="";
								 * 
								 * ResultSet rs=st.executeQuery(
								 * "SELECT * FROM tbl_Info WHERE VPassword = '"
								 * + txtPass.getText()+"'"); while(rs.next()){
								 * 
								 * pass=rs.getString(8); }
								 * 
								 * 
								 * 
								 * if(pass.equals(txtPass.getText())){
								 * JOptionPane.showMessageDialog(null,
								 * "Pin Number Already exist."
								 * ,"ATM",JOptionPane.INFORMATION_MESSAGE);
								 * 
								 * }else{
								 

								ps = cn.prepareStatement("INSERT INTO tbl_list "
										+ " (UserName,FirstName,LastName,Password,VPassword,bday,bmonth,byear,ct) "
										+ " VALUES(?,?,?,?,?,?,?,?,?)");
								ps.setString(1, txtUser.getText());
								ps.setString(2, txtFName.getText());
								ps.setString(3, txtLName.getText());
								ps.setString(4, txtPass.getText());
								ps.setString(5, txtVPass.getText());
								ps.setString(6, txtday.getText());
								ps.setString(7, txtmonth.getText());
								ps.setString(8, txtyear.getText());
								ps.setString(9, txtCash.getText());

								ps.executeUpdate();

								JOptionPane
										.showMessageDialog(
												null,
												"Your New Account has been successfully Created.",
												"ATM",
												JOptionPane.INFORMATION_MESSAGE);
								txtUser.requestFocus(true);
								st.close();
								clear();

								
								 * first_atm log=new first_atm();
								 * log.setLocation(400,250);
								 * log.setSize(250,150); log.setTitle("Log-In");
								 * log.setResizable(false);
								 * log.setVisible(true); dispose();
								 
							}
							// }

							catch (SQLException sqlEx) {
								JOptionPane.showMessageDialog(null,
										"General error", "ATM",
										JOptionPane.INFORMATION_MESSAGE);
							}

						}
					}

					catch (SQLException s) {
						System.out.println("SQL Error" + s.toString() + " "
								+ s.getErrorCode() + " " + s.getSQLState());
					} catch (Exception x) {
						System.out.println("Error" + x.toString() + " "
								+ x.getMessage());

					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Verify your password.", "ATM",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}

			// }else{
			// JOptionPane.showMessageDialog(null,"Invalid Date.","ATM",JOptionPane.INFORMATION_MESSAGE);
			// }
			// }catch(NumberFormatException b){
			// JOptionPane.showMessageDialog(null,"Invalid Date.","ATM",JOptionPane.INFORMATION_MESSAGE);

			// }
			// / }else{
			// JOptionPane.showMessageDialog(null,"Invalid Name.","ATM",JOptionPane.INFORMATION_MESSAGE);
			// }
			// }else if(txtFName.equals("")){
			// JOptionPane.showMessageDialog(null,"Invalid Name.","ATM",JOptionPane.INFORMATION_MESSAGE);
			// }
			// else{
			// JOptionPane.showMessageDialog(null,"Maximum cash 50,000 & Minimum 100.","ATM",JOptionPane.INFORMATION_MESSAGE);

		}*/
	}
	public static void main(String[]args){
		CashWithDrawDetails log=new CashWithDrawDetails();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);
		log.setTitle("Cash withdraw Details");
		log.setResizable(false);
		log.setVisible(true);
	}

}

// else{
// JOptionPane.showMessageDialog(null,"Verify your password.","ATM",JOptionPane.INFORMATION_MESSAGE);
// }

