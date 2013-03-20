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
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author Phani Rahul
 */
public class Bookings {
    //TODO:
    // Reads booking history from the database
    // Adds new bookings to the database 

    private String flightNumber;
    private int seats;
    private double totalCost;
    private String bookingId;
    private String username;
    private String AccountId;
    private String Date;
    private Database db;
    private static final String GET_BOOKING_HISTORY = "select * from bookings where niner_id= ? ";
    private static final String SAVE_TO_DATABASE = "INSERT INTO bookings "
            + "(BOOKINGID, niner_id, FLIGHTID, DATE, NUMBEROFSEATS, ACCOUNTID, TOTALCOST) "
            + "	VALUES (?, ?, ?, sysdate, ?, ?, ?)";
    private static final String NEXT_SEQ ="select booking_sequence_no.NEXTVAL from dual";

    public boolean save() throws SQLException {
        PreparedStatement ps = db.getPreparedStatement(SAVE_TO_DATABASE);

        Statement st = db.getConn().createStatement();
        ResultSet r = st.executeQuery(NEXT_SEQ);
        r.next() ;
    int nextValue = r.getInt(1) ;

        ps.setLong(1, nextValue);
        ps.setString(2, username);
        ps.setString(3, flightNumber);
        ps.setInt(4, seats);
        ps.setString(5, AccountId);
        ps.setDouble(6, totalCost);
        int rs = db.runPreparedStatementUpdate(ps);
        if (rs > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Bookings> getBookingHistory(String username) {
        return null;
    }

    public Bookings() {
    }

    public Bookings(ServletContext context)
            throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        db = Database.getConnection(ConnectionParameters.getConnectionParameters(context));
    }
//     public Bookings(ServletContext context,String flightId)
//            throws ClassNotFoundException, SQLException,
//            InstantiationException, IllegalAccessException {
//        db = Database.getConnection(ConnectionParameters.getConnectionParameters(context));
//         PreparedStatement ps = db.getPreparedStatement(GET_FLIGHT);
//         ps.setInt(1, Integer.valueOf(flightId));
//          ResultSet rs = db.runPreparedStatementQuery(ps);
//          if(rs.next()){
//            this.setFlightNumber(rs.getString("flightnumber"));
//            this.setCost(rs.getDouble("cost"));
//            this.setSeatsTotal(rs.getInt("seats_total"));
//            this.setSeatsTaken(rs.getInt("seats_taken"));
//            this.setOperator(rs.getString("operator"));
//            this.setDepartureTime(rs.getString("departure_time"));
//            this.setArrivalTime(rs.getString("arrival_time"));
//            this.setCls(rs.getString("class"));
//            this.setSource(rs.getString("source"));
//            this.setDestination(rs.getString("destination"));
//        
//        }
//    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSeats() {
        return seats+"";
    }

    public void setSeats(String seats) {
        this.seats = Integer.valueOf(seats);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String AccountId) {
        this.AccountId = AccountId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
}
