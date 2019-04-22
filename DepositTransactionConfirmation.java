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

import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DepositTransactionConfirmation extends JFrame implements Printable,ActionListener{
	
	JButton btnPrint=new JButton(new ImageIcon("btnPrint.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));
	JLabel lblBank =new JLabel("--------------ABC Bank-----------------");
	JLabel lblUser =new JLabel("");
	JLabel lblDepositAmount =new JLabel("");
	JLabel lblCurrentBalance =new JLabel("");
	JLabel lblDate = new JLabel("");
	JLabel lblEnd =new JLabel("----------------------------------------------");
	
	public DepositTransactionConfirmation(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(btnPrint);
		pane.add(btnBack);
		btnPrint.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnPrint.addActionListener(this);
		btnBack.addActionListener(this);

		String withDrawAmount = "error";
		String currentBalance = "error";
		String username = "error";
		if(null!=ATMSession.getInstance().getItem("logedindepositamnt")){
			withDrawAmount = ATMSession.getInstance().getItem("logedindepositamnt").toString();
		}
		if(null!=ATMSession.getInstance().getItem("logedincurrentbalance")){
			currentBalance = ATMSession.getInstance().getItem("logedincurrentbalance").toString();
		}
		if(null!=ATMSession.getInstance().getItem("logedinusername")){
			username = ATMSession.getInstance().getItem("logedinusername").toString();
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
	    sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    String  strDate = sdf.format(date);
	    if(null!=ATMSession.getInstance().getItem("logedindepositdate")){
	    	strDate = ATMSession.getInstance().getItem("logedindepositdate").toString();
		}
	    System.out.println("formatted date in dd/MM/yyyy : " + strDate);
	    lblUser.setText("Your user name is:"+username);
	    lblDepositAmount.setText("Your have done a Deposit of Rs:"+withDrawAmount);
	    lblCurrentBalance.setText("Your Current Balance is Rs:"+currentBalance);
	    lblDate.setText("Your Deposit date is:"+strDate);
	    pane.add(lblBank);
		pane.add(lblUser);
		pane.add(lblDepositAmount);
		pane.add(lblCurrentBalance);
		pane.add(lblDate);
		pane.add(lblEnd);
		lblBank.setForeground(Color.white);
		lblUser.setForeground(Color.white);
		lblDepositAmount.setForeground(Color.white);
		lblCurrentBalance.setForeground(Color.white);
		lblDate.setForeground(Color.white);
		lblEnd.setForeground(Color.white);
		
		lblBank.setBounds(550,200,480,20);
		lblUser.setBounds(550,250,480,20);
		lblDepositAmount.setBounds(550,300,480,20);
		lblCurrentBalance.setBounds(550,350,480,20);
		lblDate.setBounds(550,400,480,20);
		lblEnd.setBounds(550,450,480,20);

		
		
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
			lbl.setBounds(405,0,dim.width-800,dim.height);
		pane.add(lbl);

	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnPrint){
			PrinterJob job = PrinterJob.getPrinterJob();
	         job.setPrintable(this);
	         boolean ok = job.printDialog();
	         if (ok) {
	             try {
	                  job.print();
	             } catch (PrinterException ex) {
	              /* The job did not successfully complete */
	             }
	         }
	    
			

		}else if(source==btnBack){
			ATMSession.getInstance().removeItem("logedindepositdate");
			ATMSession.getInstance().removeItem("logedincurrentbalance");
			ATMSession.getInstance().removeItem("logedindepositamnt");

			ChooseTransactionType log=new ChooseTransactionType();
			
			Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
			log.setLocation(0,0);
			log.setSize(dim.width,dim.height);
			log.setTitle("Choose Transaction type");
			log.setResizable(false);
			log.setVisible(true);


			dispose();		
		}
		
	}
	
	public static void main(String[]args){
		DepositTransactionConfirmation log=new DepositTransactionConfirmation();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(x, y);
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);

		log.setTitle("CashDepositTransactionConfirmation");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		String withDrawAmount = "error";
		String currentBalance = "error";
		String username = "error";
		
		if(null!=ATMSession.getInstance().getItem("logedindepositamnt")){
			withDrawAmount = ATMSession.getInstance().getItem("logedindepositamnt").toString();
		}
		if(null!=ATMSession.getInstance().getItem("logedincurrentbalance")){
			currentBalance = ATMSession.getInstance().getItem("logedincurrentbalance").toString();
		}
		if(null!=ATMSession.getInstance().getItem("logedinusername")){
			username = ATMSession.getInstance().getItem("logedinusername").toString();
		}
		
			Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
	    sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    String  strDate = sdf.format(date);
	    System.out.println("formatted date in dd/MM/yyyy : " + strDate);
	    
	    if(null!=ATMSession.getInstance().getItem("logedindepositdate")){
	    	strDate = ATMSession.getInstance().getItem("logedindepositdate").toString();
		}
		 if (page > 0) { /* We have only one page, and 'page' is zero-based */
	            return NO_SUCH_PAGE;
	        }

	        /* User (0,0) is typically outside the imageable area, so we must
	         * translate by the X and Y values in the PageFormat to avoid clipping
	         */
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.translate(pf.getImageableX(), pf.getImageableY());

	        /* Now we perform our rendering */
	        g.drawString("-----------Welcome to ABC Bank------------", 100, 50);
	        
	        g.drawString("Your user name is:\t\t"+username, 100, 100);
	        
	        g.drawString("Your have done a Deposit of Rs:\t"+withDrawAmount, 100, 150);
	        
	        g.drawString("Your Current Balance is Rs:\t"+currentBalance, 100, 200);
	        
	        g.drawString("Your Deposit date is:\t"+strDate, 100, 250);
	        
	        g.drawString("---------------End------------------", 100,300);
	        /* tell the caller that this page is part of the printed document */
	        return PAGE_EXISTS;
		//return 0;
	}
}