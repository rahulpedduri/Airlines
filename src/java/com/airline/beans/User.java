/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.beans;

import com.airline.db.ConnectionParameters;
import com.airline.db.Database;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;

/**
 * This class deals with interfacing the servlet from the data. Behind the
 * scene complexity in handling access to the file is dealt in this class. File
 * used: \\data\\data.properties
 *
 * @author Phani Rahul
 *
 */
public class User implements Serializable{

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private static final String LOGIN_QUERY = "Select * from users where niner_id=? and password=?";
    private static final String CHECK_USERNAME_QUERY = "Select * from users where niner_id=?";
    private static final String INSERT_USER_QUERY = "insert into users(niner_id,password,first_name,last_name,phone,email)"
            + "values(?,?,?,?,?,?)";
    private Database db;

    public User(ServletContext context)
            throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        db = Database.getConnection(ConnectionParameters.getConnectionParameters(context));
    }

    /**
     * checks to see if the username specified already exists.
     *
     * @param username
     * @returntrue if the username does not already exist
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean checkUsernameExists(String username, ConnectionParameters cp)
            throws ClassNotFoundException, SQLException, InstantiationException,
            InstantiationException, IllegalAccessException {
        Database db = Database.getConnection(cp);
        PreparedStatement ps = db.getPreparedStatement(CHECK_USERNAME_QUERY);
        ps.setString(1, username);
        ResultSet rs = db.runPreparedStatementQuery(ps);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks to see if the username and password match the details on the
     * server
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if login is a success
     *
     */
    public boolean loginCheck(String username, String password) throws SQLException {

        PreparedStatement ps = db.getPreparedStatement(LOGIN_QUERY);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = db.runPreparedStatementQuery(ps);
        if (rs.next()) {
            populate(rs);
            return true;
        } else {
            return false;
        }
    }

    private void populate(ResultSet rs) throws SQLException {
        this.username = rs.getString("niner_id");
        this.password = rs.getString("password");
        this.firstName = rs.getString("first_name");
        this.lastName = rs.getString("last_name");
        this.phone = rs.getString("phone");
        this.email = rs.getString("email");
    }

    /**
     * saves the user object to the properties file
     *
     */
    public boolean save() throws SQLException {
        PreparedStatement ps = db.getPreparedStatement(INSERT_USER_QUERY);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, firstName);
        ps.setString(4, lastName);
        ps.setString(5, phone);
        ps.setString(6, email);
        int rs = db.runPreparedStatementUpdate(ps);
        if (rs > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
