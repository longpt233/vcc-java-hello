package com.vc.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {
	
	public static Connection getConn() throws SQLException {
		return getConn(Config.DB_URL, Config.USER, Config.PASS);
	}

	public static Connection getConn(String db_url, String user, String pass) throws SQLException {
		try {
//			Class.forName(Config.DRIVER);
			return DriverManager.getConnection(db_url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}