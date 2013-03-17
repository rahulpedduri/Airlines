/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.beans;

import com.airline.exceptions.LoginException;
import com.airline.exceptions.PropertiesNotFoundException;
import com.airline.db.ConnectionParameters;
import com.airline.db.Database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *This class deals with interfacing the servlets from the data.
 * Behind the scene complexity in handling access to the file is dealt in this class.
 * File used: \\data\\data.properties
 * @author Phani Rahul
 * 
 */
public class User {

    public final static String DATA_FILE = "/data/data.properties";
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
 
    private final String LOGIN_QUERY="Select * from login where username=? and password=?";
    private Database db;

    public User(ServletContext context) 
            throws ClassNotFoundException, SQLException, 
            InstantiationException, IllegalAccessException {
        db = Database.getConnection(ConnectionParameters.getConnectionParameters(context));
    }
    /**
     * checks to see if the username specified already exists.
     * @param username
     * @returntrue if the username does not already exist
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static boolean checkUsernameExists(String username,ConnectionParameters cp){
        return false;
    }

    /**
     * checks to see if the username and password match the details on the server
     * @param username the username of the user
     * @param password the password of the user
     * @return true if login is a success 
     *
     */
    public boolean loginCheck(String username, String password) {
        try {
            PreparedStatement ps = db.getPreparedStatement(LOGIN_QUERY);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = db.runPreparedStatement(ps);
            if(rs.next()){
                populate(rs);
                return true;
            }
            else 
                return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     private void populate(ResultSet rs) throws SQLException {
       this.username= rs.getString("username");
       this.password=rs.getString("password");
       this.firstName=rs.getString("first_name");
       this.lastName=rs.getString("last_name");
       this.phone=rs.getString("phone");
       this.email=rs.getString("email");
    }


    /**
     * saves the user object to the properties file
     * 
     */
   public void save(){
       
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
