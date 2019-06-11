package com.nt.dao;

import java.sql.Connection;

import com.nt.bo.EmployeeBO;

public interface EmployeeDAO {
		public Connection getPooledConnection();
		public int insert(EmployeeBO bo);
}
