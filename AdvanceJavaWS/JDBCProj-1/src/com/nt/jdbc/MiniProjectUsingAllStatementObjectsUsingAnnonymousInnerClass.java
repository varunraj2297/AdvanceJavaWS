package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*SQL> select * from all_student;

SNO 	SNAME         M1         M2         M3
---------- ---------- ---------- ---------- ----------
101		 raja               90         80         70
102 	 rani               20         30         40
103 	 ramesh        50         60         30


create or replace procedure p_student_result(m1 in number,m2 in number,m3 in number,result out varchar)
as
begin
if(m1<35 or m2<35 or m3<35) then
	result:='FAIL';
else
	result:='PASS';
end if;
end;
/

*/
public class MiniProjectUsingAllStatementObjectsUsingAnnonymousInnerClass extends JFrame implements ActionListener{
		private static final String ALL_STUDENT_SNOS="SELECT SNO FROM ALL_STUDENT";
		private static final String STUDENT_MARKS="SELECT SNAME,M1,M2,M3 FROM ALL_STUDENT WHERE SNO=?";
		private static final String CALL_QUERY="{ CALL P_STUDENT_RESULT(?,?,?,?) }";
		
		private JLabel lno,lname,lm1,lm2,lm3;
		private JComboBox combox;
		private JButton bdetails,bresult;
		private JTextField tname,tm1,tm2,tm3,tresult;
		private Connection con;
		private Statement st;
		private ResultSet rs,rs1;
		private PreparedStatement ps;
		private CallableStatement cst;
		
		
		public MiniProjectUsingAllStatementObjectsUsingAnnonymousInnerClass(){
			
			setTitle("Mini Project");
			setBackground(Color.GRAY);
			setLayout(new FlowLayout());
			setSize(400, 400);
			
			lno=new JLabel("Student number::");
			add(lno);
			combox =new JComboBox();
			add(combox);
			
			bdetails=new JButton("Details");
			add(bdetails);
			bdetails.addActionListener(this);
			
			lname=new JLabel("Stundent name::");
			add(lname);
			tname=new JTextField(10);
			add(tname);
			
			lm1=new JLabel("Marks 1::");
			add(lm1);
			tm1=new JTextField(10);
			add(tm1);
			
			lm2=new JLabel("Marks 2::");
			add(lm2);
			tm2=new JTextField(10);
			add(tm2);
			
			lm3=new JLabel("Marks 3::");
			add(lm3);
			tm3=new JTextField(10);
			add(tm3);
			
			
			bresult=new JButton("Result");
			add(bresult);
			bresult.addActionListener(this);
			tresult =new JTextField("Result::");
			add(tresult);
			
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			tname.setEditable(false);
			tm1.setEditable(false);
			tm2.setEditable(false);
			tm3.setEditable(false);
			tresult.setEditable(false);
			initialize();
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					 try{
							if(rs!=null)
								rs.close();
						}
						catch(SQLException se){
							se.printStackTrace();
						}
						
						
						try{
							if(rs1!=null)
								rs1.close();
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
							if(cst!=null)
								cst.close();
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
			
					}//windowClosing method
				
				}//annonymous inner class
			); //con
		}
		public static void main(String[] args) {
			System.out.println("MiniProjectUsingAllStatementObjects.main()");
			new MiniProjectUsingAllStatementObjectsUsingAnnonymousInnerClass();
		}
	
		public void initialize(){
			
			try{
				//register JDBC Driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
				//create Statement Object
				st=con.createStatement();
				//execute the query
				rs=st.executeQuery(ALL_STUDENT_SNOS);
				
				//process the ReultSet
				while(rs.next()){
					combox.addItem(rs.getInt(1));
				}
					
				
				//create prepared Statement Object
				ps=con.prepareCall(STUDENT_MARKS);
				
				//create callable statement object
				cst=con.prepareCall(CALL_QUERY);
			
				//register out param with Java Types
				cst.registerOutParameter(4,Types.VARCHAR);
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource()==bdetails){
				int no=(int)combox.getSelectedItem();
				
				try{
					//set i/p values to STUDENT_MARKS Query param
					ps.setInt(1,no);
					//execute the query
					rs1=ps.executeQuery();
					//process the result set
					if(rs1.next()){
						tname.setText(rs1.getString(1));
						tm1.setText(rs1.getString(2));
						tm2.setText(rs1.getString(3));
						tm3.setText(rs1.getString(4));
					}
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			}
			
			else{
				
				try{
					//set i/p values to IN param
					cst.setInt(1,Integer.parseInt(tm1.getText()));
					cst.setInt(2,Integer.parseInt(tm2.getText()));
					cst.setInt(3,Integer.parseInt(tm3.getText()));
					
					//execute the query
					cst.execute();
					
					//gather result from out param
					tresult.setText(cst.getString(4));
				}
				catch(SQLException se){
					se.printStackTrace();
				}
				
			}//else
			
		}//action performed
		
}


