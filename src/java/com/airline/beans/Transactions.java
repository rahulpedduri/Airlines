/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.beans;

import com.airline.db.ConnectionParameters;
import com.airline.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;

/**
 *
 * @author Phani Rahul
 */
public class Transactions {
   
    private double balance;
    private String username; 
    private Database db;
    private static final String VERIFY_QUERY="select * from accounts"
            + " where accountId=? and holderName=? and routingNumber=? and username=?";
    private static final String GET_BALANCE="select balance from accounts where username=?";
    private static final String SET_BALANCE="update accounts set balance=?";
    
    public Transactions(String username, ServletContext context)
            throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        db = Database.getConnection(ConnectionParameters.getConnectionParameters(context));
        this.username=username;
    }
    
    public boolean validateAccountDetails(String accountId,String holderName,String routingNumber) throws SQLException{
        PreparedStatement ps = db.getPreparedStatement(VERIFY_QUERY);
        ps.setString(1, accountId);
        ps.setString(2, holderName);
        ps.setString(3, routingNumber);
        ps.setString(4, this.username);
        ResultSet rs = db.runPreparedStatementQuery(ps);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() throws SQLException {
         PreparedStatement ps = db.getPreparedStatement(GET_BALANCE);
        ps.setString(1, this.username);
         ResultSet rs = db.runPreparedStatementQuery(ps);
        if (rs.next()) {
            balance = rs.getDouble("balance");
        }
        else {
            balance =0;
        }
        return balance;
    }

    public void setBalance(double balance) throws SQLException {
        this.balance = balance;
         PreparedStatement ps = db.getPreparedStatement(SET_BALANCE);
        ps.setString(1, this.username);
        db.runPreparedStatementUpdate(ps);

    }
    
}
