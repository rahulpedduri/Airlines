/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.beans;

/**
 *
 * @author Phani Rahul
 */
public class BankResponse {
    private String username;
    private double bill;
    private boolean transaction_status;
    private String session_id;

    public BankResponse() {
    }
        
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public boolean isTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(boolean transaction_status) {
        this.transaction_status = transaction_status;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
    
    
}
