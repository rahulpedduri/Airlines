/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.controllers;

import com.airline.beans.Bookings;
import com.airline.beans.Flight;
import com.airline.beans.Transactions;
import com.airline.beans.User;
import com.airline.db.ConnectionParameters;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phani Rahul
 */
public class Transaction extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO:
             -verify seat availability again
             -update flights table
             -save Bookings from the session
             -send to transaction confirmation page
             * 
             o Receives the bank details from Transaction jsp page.
             o Verifies the bank account and available balance using the transaction model.
             o On success, the booking details are added to the booking history via the 
             bookings model.
             o Redirects user to the Transaction Confirmation jsp page with flight details and 
             transaction status (Success/Failure). */
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
            }

            Object submit = request.getParameter("transaction_submit");
            Object detailed = request.getParameter("detailed");


            if (submit != null && !((String) submit).trim().equals("")) {
                String account_holder_name = request.getParameter("account_holder_name");
                String account_number = request.getParameter("account_number");
                String routing_number = request.getParameter("routing_number");

                Transactions t = new Transactions(user.getUsername(), getServletContext());
                if (t.validateAccountDetails(account_number, account_holder_name, routing_number)) {
                    List list = (ArrayList) session.getAttribute("bookings");
                    int index=0;
                    Iterator itr = list.iterator();
                    while (itr.hasNext()) {
                        Bookings b = (Bookings) itr.next();
                         if(Flight.seatsAvailable(Integer.valueOf(b.getSeats()), 
                                 b.getFlightNumber(),  getServletContext())){
                             if(t.getBalance() >= b.getTotalCost() ){
                                 b.setAccountId(account_number);
                                 
                                 t.setBalance(t.getBalance() - b.getTotalCost());
                             Flight.flightUpdateBookings(Integer.valueOf(b.getSeats()),
                                      b.getFlightNumber(), (getServletContext()));
                             b.save();
                             list.remove(index);
                             index++;
                             }
                            
                         }
                        
                    }

                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
