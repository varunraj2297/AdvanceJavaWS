package com.nt.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class GUIBasedScrollableTest extends JFrame implements ActionListener {
	private static final String GET_ALL_STUDS = "SELECT SNO,SNAME,SADD FROM STUDENT";

	private JLabel lno, lname, ladd;
	private JTextField tno, tname, tadd;
	private JButton bFirst, bNext, bPrevious, bLast;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private boolean flag = false;

	public GUIBasedScrollableTest() {
		System.out.println("ScrollableTest.ScrollableTest()");
		setTitle("Student Application");
		setSize(300, 400);
		setLayout(new FlowLayout());

		lno = new JLabel("sno");
		add(lno);
		tno = new JTextField(10);
		add(tno);

		lname = new JLabel("sname");
		add(lname);
		tname = new JTextField(10);
		add(tname);

		ladd = new JLabel("sadd");
		add(ladd);
		tadd = new JTextField(10);
		add(tadd);

		bFirst = new JButton("First");
		add(bFirst);
		bFirst.addActionListener(this);

		bNext = new JButton("Next");
		add(bNext);
		bNext.addActionListener(this);

		bPrevious = new JButton("Previous");
		add(bPrevious);
		bPrevious.addActionListener(this);

		bLast = new JButton("Last");
		add(bLast);
		bLast.addActionListener(this);

		setVisible(true);
		initialize();
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("ScrollableTest.ScrollableTest():windowClosing()");
					//close jdbc objs
				try {
					if(rs!=null)
						rs.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(st!=null)
						st.close();
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
			}//windowClosing method
		}//Anonymous inner class
		);//addWindowListener method call
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	private void initialize() {
		System.out.println("ScrollableTest.initialize()");
		try {

			// register jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

			// create statement object
			if (con != null)
				st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println(st);
			
			// create ResultSet obj
			if (st != null)
				rs = st.executeQuery(GET_ALL_STUDS);
				System.out.println(rs);
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

	}

	public static void main(String[] args) {
		System.out.println("ScrollableTest.main()");
		new GUIBasedScrollableTest();
	
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			
			
			
			if(ae.getSource()==bFirst) {
				System.out.println("ScrollableTest.actionPerformed()::First");
				if(rs!=null)
					flag=rs.first();
			}
			
			else if(ae.getSource()==bNext) {
				System.out.println("ScrollableTest.actionPerformed()::Next");
				if(rs!=null)
					if(!rs.isLast())
						flag=rs.next();
			}
		
				else	 if(ae.getSource()==bPrevious) {
					System.out.println("ScrollableTest.actionPerformed()::Previous");
					if(rs!=null)
						if(!rs.isFirst()) 
							flag=rs.previous();
				}
			else{
				System.out.println("ScrollableTest.actionPerformed()::Last");
				if(rs!=null)
					flag=rs.last();
			}
	
			if(flag) {
				tno.setText(rs.getString(1));
				tname.setText(rs.getString(2));
				tadd.setText(rs.getString(3));
			}
	
		}
	catch(SQLException se) {
		se.printStackTrace();
	}
	
	}//actionPerformed(-)
}//class
