package com.nt.jdbc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIScrollableTestAppUsingSwingBuilder {
	private static final String GET_ALL_STUDS="SELECT SNO,SNAME,SADD FROM STUDENT";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JFrame frame;
	private JTextField tsno;
	private JTextField tsname;
	private JTextField tadd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIScrollableTestAppUsingSwingBuilder window = new GUIScrollableTestAppUsingSwingBuilder();
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
	public GUIScrollableTestAppUsingSwingBuilder() {
		initialize();
		myInit();
	}

	
	//myInit()
	private void myInit() {
		try {
			//register the jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			//create preparedStatement obj
			if(con!=null) {
				ps=con.prepareStatement(GET_ALL_STUDS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			}
			//execute query
			if(ps!=null) {
				rs=ps.executeQuery();
			}
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
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println(
						"GUIScrollableTestAppUsingSwingBuilder.initialize().new WindowAdapter() {...}.windowClosing()");
				try {
					if(rs!=null) 
						rs.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(ps!=null) 
						ps.close();
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
		
		JLabel lsno = new JLabel("sno:");
		lsno.setBounds(107, 42, 56, 16);
		frame.getContentPane().add(lsno);
		
		JLabel lsname = new JLabel("sname");
		lsname.setBounds(107, 88, 56, 16);
		frame.getContentPane().add(lsname);
		
		JLabel lsadd = new JLabel("sadd");
		lsadd.setBounds(107, 136, 56, 16);
		frame.getContentPane().add(lsadd);
		
		tsno = new JTextField();
		tsno.setBounds(221, 39, 116, 22);
		frame.getContentPane().add(tsno);
		tsno.setColumns(10);
		
		tsname = new JTextField();
		tsname.setColumns(10);
		tsname.setBounds(221, 85, 116, 22);
		frame.getContentPane().add(tsname);
		
		tadd = new JTextField();
		tadd.setColumns(10);
		tadd.setBounds(221, 133, 116, 22);
		frame.getContentPane().add(tadd);
		
		JButton btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					tsno.setText(rs.getString(1));
					tsname.setText(rs.getString(2));
					tadd.setText(rs.getString(3));
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnFirst.setBounds(12, 188, 97, 25);
		frame.getContentPane().add(btnFirst);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isFirst()) {
					rs.previous();
					tsno.setText(rs.getString(1));
					tsname.setText(rs.getString(2));
					tadd.setText(rs.getString(3));
					}
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnPrevious.setBounds(230, 188, 97, 25);
		frame.getContentPane().add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isLast()) {
					rs.next();
					tsno.setText(rs.getString(1));
					tsname.setText(rs.getString(2));
					tadd.setText(rs.getString(3));
					}
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNext.setBounds(121, 188, 97, 25);
		frame.getContentPane().add(btnNext);
		
		JButton btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.last();
					tsno.setText(rs.getString(1));
					tsname.setText(rs.getString(2));
					tadd.setText(rs.getString(3));
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
			}
		});
		btnLast.setBounds(339, 188, 97, 25);
		frame.getContentPane().add(btnLast);
	}
}
