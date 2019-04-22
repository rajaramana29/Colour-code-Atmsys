package com.atm;

/**
 * @(#)first_atm.java
 *
 *
 * 
 * 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Admin extends JFrame implements ActionListener {

	JTextField txtuser = new JTextField(25);
	JPasswordField txtpass = new JPasswordField(25);
	JLabel lbluser = new JLabel("Username: ");
	JLabel lblpass = new JLabel("Pin Number: ");
	JButton btnLogin = new JButton(new ImageIcon("btnLogin.jpg"));
	JButton btnBack = new JButton(new ImageIcon("btnBack.jpg"));

	public Admin() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane = new JPanel();
		pane.setLayout(null);
		// ----Adding Components into your Frame
		pane.add(txtuser);
		pane.add(txtpass);
		pane.add(lbluser);
		pane.add(lblpass);
		pane.add(btnLogin);
		pane.add(btnBack);
		// -----Setting the location of the components
		lbluser.setBounds(550, 300, 80, 20);
		lbluser.setForeground(Color.white);
		lblpass.setBounds(550, 350, 80, 20);
		lblpass.setForeground(Color.white);
		txtuser.setBounds(650, 300, 100, 20);
		txtpass.setBounds(650, 350, 100, 20);
		btnLogin.setBounds(1000, 570, 320, 80);
		btnBack.setBounds(50, 570, 320, 80);
		// -----Adding the an action listener to the buttons
		btnLogin.addActionListener(this);
		btnBack.addActionListener(this);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));

		lbl.setBounds(405, 0, dim.width - 800, dim.height);
		pane.add(lbl);

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnLogin) {
			boolean valuated = false;
			String username = txtuser.getText();
			String password = txtpass.getText();
			if ((username.isEmpty() || password.isEmpty())) {
				JOptionPane.showMessageDialog(null,
						"Please fill all the fields.", "WARNING",
						JOptionPane.WARNING_MESSAGE);
				if (username.isEmpty()) {
					txtuser.requestFocus(true);
				} else {
					txtpass.requestFocus(true);
				}
			} else {
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
							if (password.equals(rs1.getString("pinnumber"))) {
								valuated = true;
							}
						}

					}

					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"There is some issue. Contact Administrator.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
					txtuser.setText("");
					txtpass.setText("");
					txtuser.requestFocus(true);
				}

				if (valuated) {
					AdminHome log = new AdminHome();
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					log.setLocation(0, 0);
					log.setSize(dim.width, dim.height);
					log.setTitle("Admin-Home");
					log.setResizable(false);
					log.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid credentials.",
							"WARNING", JOptionPane.WARNING_MESSAGE);
					txtuser.setText("");
					txtpass.setText("");
					txtuser.requestFocus(true);
				}
			}

		} else if (source == btnBack) {
			Home log = new Home();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0, 0);
			log.setSize(dim.width, dim.height);
			log.setTitle("Home");
			log.setResizable(false);
			log.setVisible(true);
			dispose();

		}

	}

	public static void main(String[] args) {
		Admin log = new Admin();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0, 0);
		log.setSize(dim.width, dim.height);
		log.setTitle("Admin-Login");
		log.setResizable(false);
		log.setVisible(true);

	}
}