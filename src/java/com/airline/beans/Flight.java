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
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author Phani Rahul
 */
public class Flight {

    private String flightNumber;
    private String operator;
    private String departureTime;
    private String arrivalTime;
    private double cost;
    private String source;
    private String destination;
    private int seatsTotal;
    private int seatsTaken;
    private String detail;
    private String cls;
    private Database db;
    private static final String FETCH_FLIGHTS_BY_COND =  "select * from flights ";
    private static final String GET_FLIGHT =  "select * from flights where flightnumber=?";
     private static final String UPDATE_FLIGHTS =  "Update flights set seats_taken = ? where flightnumber=?";
      private static final String CHECK_AVAILABILITY =  "select seats_total,seats_taken from flights where flightnumber=?";
            /*
            "select * from flights where"
            + " source = ? and destination = ? and to_char(departure_time,'dd/mm/yyyy') = ?"
            + "and (seats_total-seats_taken) > ? and class = ?";
            * */

    //TODO:
//        o Reads flight details from the database
//        o Fetches all flights with given parameters
//        o Fetches details of individual flights  String source = request.getParameter("source");
    private static int seats_taken_for_update=0;
    public static void flightUpdateBookings(int seatsBooked, String flightNumber, ServletContext ctx) 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
       Database db = Database.getConnection(ConnectionParameters.getConnectionParameters(ctx));
       if(seatsAvailable(seatsBooked, flightNumber, ctx)){
       PreparedStatement ps = db.getPreparedStatement(UPDATE_FLIGHTS);
       ps.setInt(1, seats_taken_for_update+seatsBooked);
        ps.setLong(2, Long.valueOf(flightNumber));
        int rs= db.runPreparedStatementUpdate(ps);
       }
        
    }
    public static boolean seatsAvailable(int seats,String flightNumber, ServletContext ctx) 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        if( seats<= getseatsAvailable(flightNumber, ctx)){
            return true;
        }else{
            return false;
        }
    }
    public static int getseatsAvailable(String flightNumber, ServletContext ctx) 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        Database db = Database.getConnection(ConnectionParameters.getConnectionParameters(ctx));
        PreparedStatement ps = db.getPreparedStatement(CHECK_AVAILABILITY);
        ps.setLong(1, Long.valueOf(flightNumber));
        ResultSet rs = db.runPreparedStatementQuery(ps);
        int available=0;
        if(rs.next()){
            int total = rs.getInt("seats_total");
            int taken = rs.getInt("seats_taken");
            seats_taken_for_update=taken;
            available = total-taken;
        }
        return available;
    }
    
    public ArrayList<Flight> getFlightsByConditions(
            String source,
            String dest,
            String date,
            String seats,
            String cls) throws SQLException {
        String query = FETCH_FLIGHTS_BY_COND;
        
        boolean andRequired=false;
        if(source != null && !source.equalsIgnoreCase("")){
            if(andRequired) query += " and ";
            else query += " where ";
            query += " source = ? ";
            andRequired=true;
        }
        if(dest != null && !dest.equalsIgnoreCase("")){
            if(andRequired) query += " and ";
            else query += " where ";
            query += " destination = ? ";
             andRequired=true;
        }
        if(date != null && !date.equalsIgnoreCase("")){
            if(andRequired) query += " and ";
            else query += " where ";
            query += " to_char(departure_time,'dd/mm/yyyy') = ? ";
             andRequired=true;
        }
        if(seats != null && !seats.equalsIgnoreCase("")){
            if(andRequired) query += " and ";
            else query += " where ";
            query += " (seats_total-seats_taken) > ? ";
             andRequired=true;
        }
        if(cls != null && !cls.equalsIgnoreCase("")){
            if(andRequired) query += " and ";
            else query += " where ";
            query += " class = ? ";
             andRequired=true;
        }
        
        int columnIndex=1;
        PreparedStatement ps = db.getPreparedStatement(query);
        if(source != null && !source.equalsIgnoreCase("")){
            ps.setString(columnIndex++, source);
        }if(dest != null && !dest.equalsIgnoreCase("")){
            ps.setString(columnIndex++, dest);
        }if(date != null && !date.equalsIgnoreCase("")){
            ps.setString(columnIndex++, date);
        }if(seats != null && !seats.equalsIgnoreCase("")){
            ps.setInt(columnIndex++, Integer.valueOf(seats));
        }if(cls != null && !cls.equalsIgnoreCase("")){
            ps.setString(columnIndex++, cls);
        }
        
        ResultSet rs = db.runPreparedStatementQuery(ps);
        Flight flight;
        ArrayList<Flight> flights = new ArrayList<Flight>();
        while(rs.next()){
            flight = new Flight();
            flight.setFlightNumber(rs.getString("flightnumber"));
            flight.setCost(rs.getDouble("cost"));
            flight.setSeatsTotal(rs.getInt("seats_total"));
            flight.setSeatsTaken(rs.getInt("seats_taken"));
            flight.setOperator(rs.getString("operator"));
            flight.setDepartureTime(rs.getString("departure_time"));
            flight.setArrivalTime(rs.getString("arrival_time"));
            flight.setCls(rs.getString("class"));
            flight.setSource(rs.getString("source"));
            flight.setDestination(rs.getString("destination"));
            
            flights.add(flight);  
        }
       
        return flights;
    }

    public Flight(){}
    public Flight(ServletContext context)
            throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        db = Database.getConnection(ConnectionParameters.getConnectionParameters(context));
    }
     public Flight(ServletContext context,String flightId)
            throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        db = Database.getConnection(ConnectionParameters.getConnectionParameters(context));
         PreparedStatement ps = db.getPreparedStatement(GET_FLIGHT);
         ps.setInt(1, Integer.valueOf(flightId));
          ResultSet rs = db.runPreparedStatementQuery(ps);
          if(rs.next()){
            this.setFlightNumber(rs.getString("flightnumber"));
            this.setCost(rs.getDouble("cost"));
            this.setSeatsTotal(rs.getInt("seats_total"));
            this.setSeatsTaken(rs.getInt("seats_taken"));
            this.setOperator(rs.getString("operator"));
            this.setDepartureTime(rs.getString("departure_time"));
            this.setArrivalTime(rs.getString("arrival_time"));
            this.setCls(rs.getString("class"));
            this.setSource(rs.getString("source"));
            this.setDestination(rs.getString("destination"));
        
        }
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getSeatsTotal() {
        return seatsTotal;
    }

    public void setSeatsTotal(int seatsTotal) {
        this.seatsTotal = seatsTotal;
    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
