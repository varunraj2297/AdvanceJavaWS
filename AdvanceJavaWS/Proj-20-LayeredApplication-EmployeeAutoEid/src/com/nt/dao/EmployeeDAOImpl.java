package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.nt.bo.EmployeeBO;

public class EmployeeDAOImpl implements EmployeeDAO {	
	public static final String INSERT_QUERY="INSERT INTO EMP_SAL_DETAILS VALUES(EID_SEQUENCE.NEXTVAL,?,?,?,?,?)";
	@Override
	public Connection getPooledConnection() {
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		try {
			ic=new InitialContext();
			ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
			con=ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	

	@Override
	public int insert(EmployeeBO bo) {
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		try {
			con=getPooledConnection();
			ps=con.prepareStatement(INSERT_QUERY);
			
			ps.setString(1,bo.getEname());
			ps.setString(2,bo.getAddrs());
			ps.setString(3,bo.getDesg());
			ps.setFloat(4,bo.getGross_sal());
			ps.setFloat(5,bo.getNet_sal());
			
			count=ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try{
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
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
