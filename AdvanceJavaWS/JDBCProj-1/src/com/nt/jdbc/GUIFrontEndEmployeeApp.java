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

public class GUIFrontEndEmployeeApp extends JFrame implements ActionListener,WindowListener {
private static final String INSERT_EMPLOYEE_QUERY="INSERT INTO EMP1(EMPNO,ENAME,JOB) VALUES(EMP_SEQ.NEXTVAL,?,?)";
	private JLabel lename,ljob,lres;
	private JTextField tename,tjob;
	private JButton register;
	private Connection con;
	private PreparedStatement ps;
	
	GUIFrontEndEmployeeApp(){
		setTitle("Employee Register");
		setBackground(Color.CYAN);
		setSize(400, 400);
		setLayout(new FlowLayout());

		//add compts
		lename=new JLabel("Employee name::");
		add(lename);
		tename=new JTextField(10);
		add(tename);
		
		ljob=new JLabel("Employee job::");
		add(ljob);
		tjob=new JTextField(10);
		add(tjob);
		
		lres=new JLabel("Result");
		add(lres);
		
		register=new JButton("Register");
		register.addActionListener(this);
		add(register);
		
		setVisible(true);
		//initialize jdbc objs
		makeConnection();
		addWindowListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		System.out.println("GUIFrontEndEmployeeApp-main()");
		new GUIFrontEndEmployeeApp();

	}
	
	void makeConnection(){
		System.out.println("GUIFrontEndEmployeeApp-makeConnection()");
		try{
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			//creating PreparedStament object
			if(con!=null)
				ps=con.prepareStatement(INSERT_EMPLOYEE_QUERY);
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
		System.out.println("GUIFrontEndEmployeeApp-windowClosing");
		try{
			if(ps!=null)
				ps.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		try{
			if(con!=null)
				con.close();
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
		String name=null,job=null;
		int result=0;
		// TODO Auto-generated method stub
		System.out.println("GUIFrontEndEmployeeApp-actionPerformed");
		//setting values to query param
		try{
			if(ps!=null){
				name=tename.getText();
				job=tjob.getText();
			
				ps.setString(1,name);
				ps.setString(2,job);
			
				//executing the query
				result=ps.executeUpdate();
			}
			if(result==0){
				lres.setText("Employee Not Registered");
				lres.setForeground(Color.RED);
				tename.setText("");
				tjob.setText("");
			}
			else
			{
				lres.setText("Employee Registered");
				lres.setForeground(Color.GREEN);
				tename.setText("");
				tjob.setText("");
			}
		}
		catch(SQLException se){
				lres.setForeground(Color.RED);
				lres.setText("Employee Not Registered "+se.getMessage());
				tename.setText("");
				tjob.setText("");
		}
		catch(Exception ex){
			lres.setForeground(Color.RED);
			lres.setText("Employee Not Registered "+ex.getMessage());
			tename.setText("");
			tjob.setText("");
	}
	
	}

}
