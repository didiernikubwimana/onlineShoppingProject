package edu.mum.wap.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnector {

	private DataSource dataSource;
	private Connection connection;

	public DBConnector() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			this.dataSource = (DataSource) envContext.lookup("jdbc/wapprojectdb");
		} catch (NamingException e) {
			System.err.println(e);
		}
	}

	public Connection getConnection() {
		try {
			connection = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e);
		}
		return connection;
	}

	public void CloseConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
