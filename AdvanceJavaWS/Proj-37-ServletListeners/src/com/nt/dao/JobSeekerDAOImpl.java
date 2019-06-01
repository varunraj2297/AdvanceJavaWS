package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.JobSeekerBO;

public class JobSeekerDAOImpl implements JobSeekerDAO {

	private static final String  NAUKRI_INSERT_QUERY="INSERT INTO NAUKRI_INFO VALUES(JSID_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
	
	public Connection getPooledConnection() throws Exception{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		
		ic=new InitialContext();
		ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
		con=ds.getConnection();
		
		return con;
	}
	@Override
	public int insert(JobSeekerBO bo) throws Exception {
		
	    Connection con=null;
	    PreparedStatement ps=null;
	    int result=0;
	    
	    con=getPooledConnection();
	    ps=con.prepareStatement(NAUKRI_INSERT_QUERY);
	    
	    ps.setString(1,bo.getJname());
	    ps.setInt(2,bo.getAge());
	    ps.setString(3,bo.getAddrs());
	    ps.setString(4,bo.getSkill());
	    ps.setInt(5,bo.getExp());
	    ps.setString(6,bo.getLoc());
	    ps.setInt(7,bo.getExpsal());
	    
	    result=ps.executeUpdate();
		return result;
	}

}
