package com.nt.jdbc;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MiniProjectUsingMetaData {

	private static final String GET_USERS="SELECT USERNAME FROM ALL_USERS";
	private static final String GET_ALL_TABLES="SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER=?";
	private static final String GET_ALL_COLS="SELECT COLUMN_NAME FROM COLS WHERE TABLE_NAME=?";
	private static String GET_ALL_VALUES;
	private JFrame frame;
	private Connection con;
	private PreparedStatement pst1,pst2,pst3,pst4;
	private ResultSet rs1,rs2,rs3,rs4;
	String tableName;
	String colName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiniProjectUsingMetaData window = new MiniProjectUsingMetaData();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiniProjectUsingMetaData() {
		myInit();
		initialize();
		
	}

	
	private void myInit() {
		try {
			//register JDBC Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			//create Statement
			if(con!=null) {
				pst1=con.prepareStatement(GET_USERS);
				pst2=con.prepareStatement(GET_ALL_TABLES);
				pst3=con.prepareStatement(GET_ALL_COLS);
			}
			//send and execute the query
			if(pst1!=null) 
				rs1=pst1.executeQuery();
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if(rs4!=null)
						rs4.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(rs3!=null)
						rs3.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(rs2!=null)
						rs2.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(rs1!=null)
						rs1.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(pst4!=null)
						pst4.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(pst3!=null)
						pst3.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(pst2!=null)
						pst2.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(pst1!=null)
						pst1.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(con!=null)
						con.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox cUsers = new JComboBox();
		cUsers.setBounds(102, 34, 106, 50);
		frame.getContentPane().add(cUsers);
		//process the resultset rs1
		if(pst1!=null) {
			while(rs1.next()) {
				cUsers.addItem(rs1.getString(1));
			}
		}
		
		
		
		JComboBox cTables = new JComboBox();
		cTables.setBounds(283, 39, 137, 41);
		frame.getContentPane().add(cTables);
		
		
		JComboBox cCols = new JComboBox();
		cCols.setBounds(102, 144, 97, 36);
		frame.getContentPane().add(cCols);
		
		JComboBox cValues = new JComboBox();
		cValues.setBounds(295, 144, 125, 35);
		frame.getContentPane().add(cValues);
		
		JLabel lUsers = new JLabel("Db Users");
		lUsers.setFont(new Font("Tahoma", Font.BOLD, 20));
		lUsers.setBounds(0, 39, 97, 47);
		frame.getContentPane().add(lUsers);
		
		JLabel lTables = new JLabel("Tables");
		lTables.setFont(new Font("Tahoma", Font.BOLD, 20));
		lTables.setBounds(211, 39, 72, 47);
		frame.getContentPane().add(lTables);
		
		JLabel lCols = new JLabel("Columns");
		lCols.setFont(new Font("Tahoma", Font.BOLD, 20));
		lCols.setBounds(0, 143, 92, 33);
		frame.getContentPane().add(lCols);
		
		JLabel lValues = new JLabel("Values");
		lValues.setFont(new Font("Tahoma", Font.BOLD, 20));
		lValues.setBounds(211, 144, 72, 32);
		frame.getContentPane().add(lValues);
		
		JButton bTables = new JButton("getTables");
		bTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String userName=(String) cUsers.getSelectedItem();
					//setting i/p values to get_all_tables query param
					if(pst2!=null) {
						pst2.setString(1,userName);
					//execute the query
						rs2=pst2.executeQuery();
					}
						//process the resultset rs2
						if(rs2!=null) {
							while(rs2.next()) {
								cTables.addItem(rs2.getString(1));
							}
						}
					
				}catch(SQLException se) {
					se.printStackTrace();
				}
				
				
			}
		});
		bTables.setBounds(25, 105, 97, 25);
		frame.getContentPane().add(bTables);
		
		JButton bCols = new JButton("getColumns");
		bCols.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					tableName=(String) cTables.getSelectedItem();
					//setting i/p values to get_all_cols query param
					if(pst3!=null) {
						pst3.setString(1,tableName);
					//execute the query
						rs3=pst3.executeQuery();
					}
						//process the resultset rs2
						if(rs3!=null) {
							while(rs3.next()) {
								cCols.addItem(rs3.getString(1));
							}
						}
					
				}catch(SQLException se) {
					se.printStackTrace();
				}

			}
		});
		bCols.setBounds(237, 105, 111, 25);
		frame.getContentPane().add(bCols);
		
		JButton bValues = new JButton("getValues");
		bValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					colName=(String) cCols.getSelectedItem();
					
					GET_ALL_VALUES="SELECT "+colName+" FROM "+tableName;
					//create prepared statement obj
					pst4=con.prepareStatement(GET_ALL_VALUES);
					//execute the query
						rs4=pst4.executeQuery();
					
						//process the resultset rs2
						if(rs4!=null) {
							while(rs4.next()) {
								cValues.addItem(rs4.getString(1));
							}
						}
					
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
		});
		bValues.setBounds(25, 189, 97, 25);
		frame.getContentPane().add(bValues);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
