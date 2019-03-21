package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AllDMLOperationsOnStudentDetails extends JFrame implements ActionListener{
	private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	private static final String UPDATE_QUERY="UPDATE STUDENT SET SNAME=?,SADD=?,SCOURSE=? WHERE SNO=?";
	private static final String DELETE_QUERY="DELETE FROM STUDENT WHERE SNO=?";
	private static final String SELECT_QUERY="SELECT SNO,SNAME,SADD,SCOURSE FROM STUDENT";
	
	private JLabel lsno,lsname,lsadd,lscourse;
	private JTextField tsno,tsname,tsadd,tscourse;
	private JButton binsert,bupdate,bdelete,bview;
	private Connection con;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	
	public AllDMLOperationsOnStudentDetails() {
			System.out.println("AllDMLOperationsOnStudentDetails.AllDMLOperationsOnStudentDetails()");
			setTitle("ALL STUDENT DETAILS");
			setBackground(Color.YELLOW);
			setLayout(new FlowLayout());
			setSize(400,400);
			
			lsno=new JLabel("sno");
			add(lsno);
			tsno=new JTextField(10);
			add(tsno);
			
			lsname=new JLabel("sname");
			add(lsname);
			tsname=new JTextField(10);
			add(tsname);
			
			lsadd=new JLabel("sadd");
			add(lsadd);
			tsadd=new JTextField(10);
			add(tsadd);
			
			lscourse=new JLabel("scourse");
			add(lscourse);
			tscourse=new JTextField(10);
			add(tscourse);
			
			binsert=new JButton("insert");
			add(binsert);
			binsert.addActionListener(this);
			
			bupdate=new JButton("update");
			add(bupdate);
			bupdate.addActionListener(this);
			
			bdelete=new JButton("delete");
			add(bdelete);
			bdelete.addActionListener(this);

			bview=new JButton("view");
			add(bview);
			bview.addActionListener(this);
			
			setVisible(true);
//			tsno.setEditable(false);
//			tsname.setEditable(false);
//			tsadd.setEditable(false);
//			tscourse.setEditable(false);
			Initialize();
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.out.println("AllDMLOperationsOnStudentDetails.AllDMLOperationsOnStudentDetails()");
					try{
							if(rs!=null)
								rs.close();
						}
						catch(SQLException se){
							se.printStackTrace();
						}
						
						try{
							if(st!=null)
								st.close();
						}
						catch(SQLException se){
							se.printStackTrace();
						}
						
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
				}//windowClosing
				
			}//annonymous Inner Class
			);//addWindowsListner()
			
	}
	
	void Initialize(){
		System.out.println("AllDMLOperationsOnStudentDetails.Initialize()");
		try{
			//register JDBC Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
			System.out.println("AllDMLOperationsOnStudentDetails.main()");
			new AllDMLOperationsOnStudentDetails();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		
		boolean ins_flag=false,upd_flag=false,del_flag=false,view_flag=false;
		try{
			if(e.getSource()==binsert){
					System.out.println("AllDMLOperationsOnStudentDetails.actionPerformed()::insert");
				//create PreparedStatement obj
				if(con!=null)
					ps=con.prepareStatement(INSERT_QUERY);
				
				if(ps!=null){
					
					//set i/p values to query param
					ps.setInt(1,Integer.parseInt(tsno.getText()));
					ps.setString(2,tsname.getText());
					ps.setString(3,tsadd.getText());
					ps.setString(4,tscourse.getText());
					
					//execute the query
					ps.execute();
					ins_flag=true;
					System.out.println(ins_flag);
					if(ins_flag)
						System.out.println("record inserted");
					else
						System.out.println("record not inserted");
				}
				
			}//binsert
			else if(e.getSource()==bupdate){
				System.out.println("AllDMLOperationsOnStudentDetails.actionPerformed()::UPDATION");
				//create PreparedStatement obj
				if(con!=null)
					ps=con.prepareStatement(UPDATE_QUERY);

				if(ps!=null){
					//set i/p values to query param
					ps.setString(1,tsname.getText() );	// sname
					ps.setString(2,tsadd.getText()); 		// sadd
					ps.setString(3,tscourse.getText()); // scourse
					ps.setInt(4,Integer.parseInt(tsno.getText()));	//sno
					
					//execute the query
					ps.execute();
					upd_flag=true;
					if(upd_flag)
						System.out.println("Details Modified");
					else
						System.out.println("Details Not Modified");
				}
				
			}//bupdate
			
			else if(e.getSource()==bdelete){
			System.out.println("AllDMLOperationsOnStudentDetails.actionPerformed()::DELETE");
				//create PreparedStatement obj
				if(con!=null)
					ps=con.prepareStatement(DELETE_QUERY);
				if(ps!=null){
					//set i/p value to query param
					ps.setInt(1,Integer.parseInt(tsno.getText()));
					
					//execute the query
					ps.execute();
					del_flag=true;
					
					if(del_flag)
						System.out.println("Record deleted");
					else
						System.out.println("Record not deleted");	
				}
			
			}//bdelete
			
			else if(e.getSource()==bview){
				System.out.println("AllDMLOperationsOnStudentDetails.actionPerformed()::SELECT");
				//create Statement Object
				if(con!=null)
					st=con.createStatement();
				//send and execute Query
					rs=st.executeQuery(SELECT_QUERY);
				//process the ResultSet
					if(rs!=null){
						while(rs.next()){
							view_flag=true;
							System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"    "+rs.getString(3)+"     "+rs.getString(4));
						}
					}
					//process the result
					if(view_flag)
						System.out.println("Records found and Displayed");
					else
						System.out.println("Records Not Found");
			}//bview
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
	
	}//action performed

}//class
