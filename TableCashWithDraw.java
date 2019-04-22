/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package com.atm;

/*
 * TableDemo.java requires no other files.
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.util.ATMSession;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/** 
 * TableDemo is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */
public class TableCashWithDraw extends JPanel {
    private boolean DEBUG = false;

    public TableCashWithDraw() {
        super(new GridLayout(1,0));

        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"User Name",
                                        "Withdraw Amount",
                                        "Date",
                                       };
       int rowcount=0;
       int colcount=3;
       
        
       
       private Object[][] data = null;
       MyTableModel(){
    		String username = ATMSession.getInstance().getItem("usersearcheduserid").toString();
    		Connection con = null;
    		ResultSet rs1 = null;
    		ResultSet rs2 = null;
    		//int numberRow=0;
    		try {
    			Class.forName("com.mysql.jdbc.Driver");
    			con = DriverManager.getConnection(
    					"jdbc:mysql://localhost:3306/atmfingerprint",
    					"root", "1111");
    			String query1= "select count(*) from withdraw where username='"
    					+ username + "'";
    			Statement ps1 = con.createStatement();
    			rs2 = ps1.executeQuery(query1);
    			rs2.next();
    			rowcount = rs2.getInt(1);
    			System.out.println("Row Count==="+rowcount);
    			data = new Object[rowcount][colcount];
    			Statement ps = con.createStatement();
    			String query= "select * from withdraw where username='"
    					+ username + "'";
    			System.out.println("Query="+query);
    			rs1 = ps.executeQuery(query);
    			
    			int rowcount = 0;
    			/*while (rs1.next()) {
    					
    				System.out.println(rs1.getString("username"));
    				System.out.println(rs1.getString("withdrawamnt"));
    				System.out.println(rs1.getString("withdrawdate"));
    				data[rowcount][0] = rs1.getString("username");
    				data[rowcount][1] = rs1.getString("withdrawamnt");
    				data[rowcount][2] = rs1.getString("withdrawdate");
    				rowcount++;
    			}*/
    			
    			rs1.afterLast();
    			while (rs1.previous()) {
					
    				System.out.println(rs1.getString("username"));
    				System.out.println(rs1.getString("withdrawamnt"));
    				System.out.println(rs1.getString("withdrawdate"));
    				data[rowcount][0] = rs1.getString("username");
    				data[rowcount][1] = rs1.getString("withdrawamnt");
    				data[rowcount][2] = rs1.getString("withdrawdate");
    				rowcount++;
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
              
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TableCashWithDraw newContentPane = new TableCashWithDraw();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}