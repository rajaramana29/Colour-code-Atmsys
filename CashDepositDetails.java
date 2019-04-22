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

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CashDepositDetails extends JFrame implements ActionListener {

	JButton btnBack = new JButton(new ImageIcon("btnBack.jpg"));

	public void clear() {

		/*
		 * txtUser.setText(""); txtFName.setText(""); txtPass.setText("");
		 * txtLName.setText(""); txtVPass.setText("");
		 */
	}

	public CashDepositDetails() {
		super("Project");

		JPanel pane = new JPanel();
		pane.setLayout(null);

		btnBack.setBounds(50, 570, 320, 80);
		// pane.add(btnConfirmEdit);
		pane.add(btnBack);
		// btnConfirmEdit.addActionListener(this);
		btnBack.addActionListener(this);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));

		lbl.setBounds(405, 0, dim.width - 800, dim.height);
		pane.add(lbl);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Cash Deposit Details"));

	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == btnBack) {
			ATMSession.getInstance().removeItem("usersearcheduserid");
			CashDepositUserSearch log = new CashDepositUserSearch();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			// this.setLocation(x, y);
			log.setLocation(0, 0);
			log.setSize(dim.width, dim.height);
			log.setTitle("CashDeposit-User-Search");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		}

	}

	public static void main(String[] args) {
		CashDepositDetails_old log = new CashDepositDetails_old();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		log.setLocation(0, 0);
		log.setSize(dim.width, dim.height);
		log.setTitle("Cash Deposit Details");
		log.setResizable(false);
		TableExample1 newContentPane = new TableExample1();
        newContentPane.setOpaque(true); //content panes must be opaque
        newContentPane.setBounds(50, 370, 320, 80);
        log.setContentPane(newContentPane);
        log.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        log.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	ATMSession.getInstance().removeItem("usersearcheduserid");
    			CashDepositUserSearch log = new CashDepositUserSearch();
    			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    			// this.setLocation(x, y);
    			log.setLocation(0, 0);
    			log.setSize(dim.width, dim.height);
    			log.setTitle("CashDeposit-User-Search");
    			log.setResizable(false);
    			log.setVisible(true);
            	
            	
            }
        });
		log.setVisible(true);
		
        
	}
	

}
