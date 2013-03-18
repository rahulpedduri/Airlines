/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.beans;

/**
 *
 * @author Phani Rahul
 */
public class Transactions {
    private String accountId;
    private String holderName;
    private String routingNumber;
    private double balance;
    private String username;
    
    public boolean validateAccountDetails(){
        //TODO : validate account details
        load();
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        save();
    }
    private void load(){
        
    }
    private void save(){
        
    }
    
}
