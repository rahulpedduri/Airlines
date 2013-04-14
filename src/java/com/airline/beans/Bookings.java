/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.beans;

import com.airline.db.ConnectionParameters;
import com.airline.db.Database;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author Phani Rahul
 */
public class Bookings  implements Serializable{
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
    private Flight flight;
    private Database db;
    private static final String GET_BOOKING_HISTORY = "select * from bookings "
            + "left join flights on bookings.flightId=flights.flightnumber "
            + "where niner_id= ? ";
    private static final String SAVE_TO_DATABASE = "INSERT INTO bookings "
            + "(BOOKINGID, niner_id, FLIGHTID,  NUMBEROFSEATS, ACCOUNTID, TOTALCOST) "
            + "	VALUES (?, ?, ?,  ?, ?, ?)";
    private static final String NEXT_SEQ = "select booking_sequence_no.NEXTVAL from dual";

    public int save() throws SQLException {
        PreparedStatement ps = db.getPreparedStatement(SAVE_TO_DATABASE);

        Statement st = db.getConn().createStatement();
        ResultSet r = st.executeQuery(NEXT_SEQ);
        r.next();
        int nextValue = r.getInt(1);

        ps.setLong(1, nextValue);
        ps.setString(2, username);
        ps.setString(3, flightNumber);
        ps.setInt(4, seats);
        ps.setLong(5, Long.valueOf(AccountId));
        ps.setDouble(6, totalCost);
        int rs = db.runPreparedStatementUpdate(ps);
        return nextValue;
    }
    public void addFlightToBean() 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        flight = new Flight(db, flightNumber);
    }

    public ArrayList<Bookings> getBookingHistory(String username) throws SQLException {
        PreparedStatement ps = db.getPreparedStatement(GET_BOOKING_HISTORY);
        ps.setString(1, username);
        ResultSet rs = db.runPreparedStatementQuery(ps);
        Bookings booking;
        ArrayList<Bookings> bookingHistory;
        bookingHistory = new ArrayList<Bookings>();
        
        while (rs.next()) {
            booking = new Bookings();
            //BOOKINGID, "niner_id", FLIGHTID, "DATE", NUMBEROFSEATS, ACCOUNTID, TOTALCOST) 
            booking.setAccountId(rs.getString("ACCOUNTID"));
            booking.setBookingId(rs.getString("BOOKINGID"));
            booking.setDate(rs.getString("DATE"));
            booking.setFlightNumber(rs.getString("FLIGHTID"));
            booking.setSeats(rs.getString("NUMBEROFSEATS"));
            booking.setTotalCost(rs.getDouble("TOTALCOST"));
            booking.setUsername(rs.getString("niner_id"));
            try {
                flight = new Flight(db,booking.getFlightNumber());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Bookings.class.getName()).log(Level.SEVERE, null, ex);
            }
             booking.setFlight(flight);  

            bookingHistory.add(booking);
        }
        return bookingHistory;
    }

    public Bookings() {}

    public Bookings(ServletContext context)
            throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        db = Database.getConnection(ConnectionParameters.getConnectionParameters(context));
    }
    public void setFlight(Flight flight) {
        this.flight=flight;
    }
public Flight getFlight() {
        return flight;
    }
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSeats() {
        return seats + "";
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