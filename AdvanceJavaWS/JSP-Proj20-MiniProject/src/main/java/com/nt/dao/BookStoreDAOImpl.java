package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.BookDetailsBO;

public class BookStoreDAOImpl implements BookStoreDAO {

	private static final String GET_BOOKS_BY_CATEGORY = "SELECT BOOKID,BOOKNAME,AUTHOR,STATUS,PRICE,CATEGORY FROM BOOK_CATALOG WHERE CATEGORY=?";

	private Connection getConnection() throws Exception {

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;

		ic = new InitialContext();
		ds = (DataSource) ic.lookup("java:comp/env/DsJndi");
		con = ds.getConnection();
		return con;

	}

	@Override
	public List<BookDetailsBO> getBooksByCategory(String category) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookDetailsBO> listBO=null;
		BookDetailsBO bo=null;
		try {
			con = getConnection();

			ps = con.prepareStatement(GET_BOOKS_BY_CATEGORY);
			ps.setString(1, category);
			rs = ps.executeQuery();
            listBO=new ArrayList<BookDetailsBO>();
			while (rs.next()) {
                  bo=new BookDetailsBO();
                  bo.setBookId(rs.getInt(1));
                  bo.setBookName(rs.getString(2));
                  bo.setAuthor(rs.getString(3));
                  bo.setStatus(rs.getString(4));
                  bo.setPrice(rs.getFloat(5));
                  bo.setCategory(rs.getString(6));
                  
                  listBO.add(bo);
			}
		} catch (Exception e) {
			throw (e);
		}
		finally {
			try{
				if(rs!=null) 
			           rs.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
				throw(se);
			}
			try{
				if(ps!=null) 
			           ps.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
				throw(se);
			}
			try{
				if(con!=null) 
			           con.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
				throw(se);
			}
		}
		return listBO;
	}

}
