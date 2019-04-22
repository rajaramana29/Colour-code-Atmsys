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
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckBalance extends JFrame implements Printable, ActionListener{
	
	JButton btnPrint=new JButton(new ImageIcon("btnPrint.jpg"));
	JButton btnBack=new JButton(new ImageIcon("btnBack.jpg"));
	JLabel lblBank =new JLabel("--------------ABC Bank-----------------");
	JLabel lblUser =new JLabel("");
	JLabel lblCurrentBalance =new JLabel("");
	JLabel lblDate = new JLabel("");
	JLabel lblEnd =new JLabel("----------------------------------------------");
	String currentBalance = "error";
	
	
	public CheckBalance(){
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(btnPrint);
		pane.add(btnBack);
		//-----Setting the location of the components
		btnPrint.setBounds(1000,570,320,80);
		btnBack.setBounds(50,570,320,80);
		//-----Adding the an actionlistener to the buttons
		btnPrint.addActionListener(this);
		btnBack.addActionListener(this);

		String username = "error";
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
	    if(null!=ATMSession.getInstance().getItem("logedinwithdrawdate")){
	    	strDate = ATMSession.getInstance().getItem("logedinwithdrawdate").toString();
		}
	    System.out.println("formatted date in dd/MM/yyyy : " + strDate);
	    lblUser.setText("Your user name is:"+username);
	    //lblCurrentBalance.setText("Your Current Balance is Rs:"+currentBalance);
	    lblDate.setText("Your Tranaction date is:"+strDate);
	    pane.add(lblBank);
		pane.add(lblUser);
		pane.add(lblCurrentBalance);
		pane.add(lblDate);
		pane.add(lblEnd);
		lblBank.setForeground(Color.white);
		lblUser.setForeground(Color.white);
		lblCurrentBalance.setForeground(Color.white);
		lblDate.setForeground(Color.white);
		lblEnd.setForeground(Color.white);
		
		lblBank.setBounds(550,200,480,20);
		lblUser.setBounds(550,250,480,20);
		lblCurrentBalance.setBounds(550,300,480,20);
		lblDate.setBounds(550,350,480,20);
		lblEnd.setBounds(550,400,480,20);

		//String balance = "?";
		boolean valuated = false;
		//String username = ATMSession.getInstance().getItem("logedinusername").toString();
		Connection con = null;
		ResultSet rs1 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/atmfingerprint",
					"root", "1111");
			Statement ps = con.createStatement();

			rs1 = ps.executeQuery("select * from clientreg where username='"
					+ username + "'" );

			while (rs1.next()) {

				System.out.println(rs1.getString("balance"));
				//balance = rs1.getString("balance");
						valuated = true;
						lblCurrentBalance.setText("Your current balance is Rs:"+rs1.getString("balance"));
						
						currentBalance = rs1.getString("balance");
					}


			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There is some issue. Contact Administrator.",
					"WARNING", JOptionPane.WARNING_MESSAGE);
			
		}

		if (valuated) {

		} else {
			JOptionPane.showMessageDialog(null,
					"There is some issue. Contact Administrator.",
					"WARNING", JOptionPane.WARNING_MESSAGE);
		}

		
		
		
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
		CheckBalance log=new CheckBalance();
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(x, y);
		log.setLocation(0,0);
		log.setSize(dim.width,dim.height);

		log.setTitle("Check Balance");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		String username = ATMSession.getInstance().getItem("logedinusername").toString();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
	    sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    String  strDate = sdf.format(date);
	    System.out.println("formatted date in dd/MM/yyyy : " + strDate);
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
	        
	        g.drawString("Your user name is:"+username, 100, 100);
	        
	        g.drawString("Your Current Balance = Rs."+currentBalance, 100, 150);
	        
	        g.drawString("Your withdrawal date is: "+strDate, 100, 200);
	        
	        g.drawString("---------------End------------------", 100,250);
	        

	        /* tell the caller that this page is part of the printed document */
	        return PAGE_EXISTS;
		//return 0;
	}
}