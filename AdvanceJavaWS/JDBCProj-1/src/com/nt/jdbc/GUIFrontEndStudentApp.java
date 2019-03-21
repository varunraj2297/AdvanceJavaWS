package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIFrontEndStudentApp extends JFrame implements  ActionListener,WindowListener {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	
	private JLabel lno,lname,ladd,lres;
	private JTextField tno,tname,tadd;
	private JButton register;
	private Connection con;
	private PreparedStatement ps;
	
	
	GUIFrontEndStudentApp(){
		System.out.println("GUIFrontEndStudentApp zero param constructor");
		setTitle("Student Registrartion");
		setBackground(Color.CYAN);
		setLayout(new FlowLayout());
		setSize(400,400);
		//adding compts
		lno=new JLabel("Student Number");
		add(lno);
		
		tno=new JTextField(10);
		add(tno);
		
		lname=new JLabel("Student Name");
		add(lname);
		
		tname=new JTextField(10);
		add(tname);
		
		ladd=new JLabel("Student Address");
		add(ladd);
		
		tadd=new JTextField(10);
		add(tadd);

		register=new JButton("Register");
		register.addActionListener(this);
		add(register);
		
		lres=new JLabel("Result is::");
		add(lres);
		addWindowListener(this);
		
		
		setVisible(true);
		//initialize jdbc objs
		makeConnection();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		System.out.println("main()");
		new GUIFrontEndStudentApp();
	}

	public void makeConnection(){
		System.out.println("GUIFrontEndStudentApp-makeConnection()");
		try{
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			
			//create prepareStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
		}
		catch(SQLException se){
			 se.printStackTrace();
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}
	@Override
	public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void windowClosing(WindowEvent e) {
	// TODO Auto-generated method stub
		System.out.println("GUIFrontEndStudentApp-closing window");

		try{
			if(ps!=null)
				ps.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		try{
			if(con!=null){
				con.close();
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		

}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	
	}

	@Override
	public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int no=0;
		String name=null;
		String add=null;
		int result=0;
		System.out.println("GUIFrontEndStudentApp-actionPerformed");
		try{
			
			
			//setting i/p values to query param
			
				if(ps!=null){
					no=Integer.parseInt(tno.getText());
					name=tname.getText();
					add=tadd.getText();
				
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, add);
				
			}
			
				//execute the query
				if(ps!=null)
					result=ps.executeUpdate();
				
				//process the resultset
				if(result==1){
					lres.setForeground(Color.GREEN);
					lres.setText("Student Registered");
					tno.setText("");
					tname.setText("");
					tadd.setText("");
				}
				else
				{
					lres.setForeground(Color.RED);
					lres.setText("Student Not Registered");
					tno.setText("");
					tname.setText("");
					tadd.setText("");
				}
			}
			catch(SQLException se){
					lres.setForeground(Color.RED);
					lres.setText("Student Not Registered"+se.getMessage());
					tno.setText("");
					tname.setText("");
					tadd.setText("");
			}
			catch(Exception ex){
				lres.setForeground(Color.RED);
				lres.setText("Student Not Registered"+ex.getMessage());
				tno.setText("");
				tname.setText("");
				tadd.setText("");
			}
		}
}
