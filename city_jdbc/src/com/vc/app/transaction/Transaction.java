package com.vc.app.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Transaction {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    
	Class.forName("com.mysql.jdbc.Driver");
    Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo-jdbc-transaction", "root", "root");
    Statement stmt = dbConnection.createStatement();
    
    dbConnection.setAutoCommit(false);
    
    try {
      stmt.executeUpdate("UPDATE account_banking SET amount = 40000000.0 WHERE name = 'A'");
      stmt.executeUpdate("UPDATE account_banking SET amount = 140000000.0 WHERE name = 'B'");
      dbConnection.commit();
    } catch (Exception e) {
      e.printStackTrace();
      dbConnection.rollback();
    }
    dbConnection.close();
  }
}
