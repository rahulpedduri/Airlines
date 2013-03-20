/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.beans;

import com.airline.db.ConnectionParameters;
import com.airline.db.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author Phani Rahul
 */
public class Flights {

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
    private static final String FETCH_FLIGHTS_BY_COND = "select * from flights where"
            + " source = ? and destiation = ? and to_char(departure_time,'dd/mm/yyyy') = ?"
            + "and seats = ? and class = ?";

    //TODO:
//        o Reads flight details from the database
//        o Fetches all flights with given parameters
//        o Fetches details of individual flights  String source = request.getParameter("source");
    public ArrayList<Flights> getFlightsByConditions(
            String source,
            String dest,
            String date,
            String seats,
            String cls) {

         

        return null;
    }
     public Flights(ServletContext context)
            throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        db = Database.getConnection(ConnectionParameters.getConnectionParameters(context));
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
