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
import com.util.Utility;

import java.awt.event.*;
import java.sql.*;

public class OTPVerification extends JFrame implements ActionListener {

	JTextField txtotp = new JTextField(25);
	JLabel otpno = new JLabel("OTP no: ");
	JButton btnOtpverification = new JButton(new ImageIcon(
			"btnOtpverification.jpg"));
	JButton btnBack = new JButton(new ImageIcon("btnBack.jpg"));

	public OTPVerification() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane = new JPanel();
		pane.setLayout(null);
		// ----Adding Components into your Frame
		pane.add(txtotp);
		pane.add(otpno);
		pane.add(btnOtpverification);
		pane.add(btnBack);
		// -----Setting the location of the components
		otpno.setBounds(550, 300, 80, 20);
		otpno.setForeground(Color.white);
		txtotp.setBounds(650, 300, 100, 20);
		btnOtpverification.setBounds(1000, 570, 320, 80);
		btnBack.setBounds(50, 570, 320, 80);
		// -----Adding the an actionlistener to the buttons
		btnOtpverification.addActionListener(this);
		btnBack.addActionListener(this);
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		lbl.setBounds(405, 0, dim.width - 800, dim.height);
		pane.add(lbl);

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == btnOtpverification) {
			if (txtotp.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter OTP.",
						"WARNING", JOptionPane.WARNING_MESSAGE);
				txtotp.requestFocus(true);
			} else {
				if (txtotp.getText().trim()
						.equals(ATMSession.getInstance().getItem("logedinotp"))) {
					ChooseAuthentcationType log = new ChooseAuthentcationType();

					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					log.setLocation(0, 0);
					log.setSize(dim.width, dim.height);
					log.setTitle("Choose Authentication type");
					log.setResizable(false);
					log.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Please enter Valid OTP.", "WARNING",
							JOptionPane.WARNING_MESSAGE);
					txtotp.requestFocus(true);
				}
			}
		} else if (source == btnBack) {
			ATMSession.getInstance().removeItem("logedinmobileno");
			ATMSession.getInstance().removeItem("logedinotp");
			MobileNumberVerification log = new MobileNumberVerification();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0, 0);
			log.setSize(dim.width, dim.height);
			log.setTitle("MobileVerification");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		}

	}

	public static void main(String[] args) {
		OTPVerification log = new OTPVerification();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0, 0);
		log.setSize(dim.width, dim.height);
		log.setTitle("OTPVerification");
		log.setResizable(false);
		log.setVisible(true);

	}
}