/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.airline.controllers;


import com.airline.beans.BankSOAP;
import com.airline.beans.Bookings;
import com.airline.beans.Flight;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.http.*;




/**
 *
 * @author Zhiwei Li
 */
public class BankService extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        // build a new soap request by using the BankSOAP
        BankSOAP bankSOAP = new BankSOAP();

        // deal with null parameters
        // get the student ID that the user input into the form and give it to the soap request
        String studentID="",name="";
        double rNumber=0,aNumber=0,bal=0;
        
        if (request.getParameter("studentID") == null){
            studentID = "";
        } else {
            studentID = filter(request.getParameter("studentID"));
        }
        session.setAttribute("studentID", studentID);

        // get the customer's userID that the Web Service will need
        String userID;
        if (request.getParameter("account_holder_name") == null){
            userID = "";
        } else {
            userID = filter(request.getParameter("account_holder_name"));
        }
        session.setAttribute("accountHolderName", userID);
        
        double routingNumber;
        if (request.getParameter("routing_number") == null){
            routingNumber = 0;
        } else {
            routingNumber = Double.valueOf((String)request.getParameter("routing_number"));
        }
        session.setAttribute("routingNumber", routingNumber);
        
        double accountNumber;
        if (request.getParameter("account_number") == null){
            accountNumber = 0;
        } else {
            accountNumber = Double.valueOf((String)request.getParameter("account_number"));
        }
        session.setAttribute("accountNumber", accountNumber);
        
        double bill=0;
        if (session.getAttribute("bill") == null){
            bill = 0;
        } else {
            try {
            bill = (Double)session.getAttribute("bill");
            }
            catch (Exception e) {
                System.out.println(e);
            }
            }
        
        //session.setAttribute("bill", bill);

        // get the desired new balance
        double balance;
        if (request.getParameter("balance") == null){
            balance = 0;
        } else {
            balance = getDoubleParameter(request, "balance", 0);
        }
        
        String soapReturnXML;
        
        String methodName = "getName";
        // call the post method to send our soap and get some xml in return
        soapReturnXML = bankSOAP.getResponseFromWebService(studentID, methodName, userID);
        // place the returned value in the request
        request.setAttribute("getName", bankSOAP.getValue(soapReturnXML, methodName));

        methodName = "getRoutingNumber";
        // call the post method to send our soap and get some xml in return
        soapReturnXML = bankSOAP.getResponseFromWebService(studentID, methodName, userID);
        // place the returned value in the request
        request.setAttribute("getRoutingNumber", bankSOAP.getValue(soapReturnXML, methodName));
        
        methodName = "getAccountNumber";
        // call the post method to send our soap and get some xml in return
        soapReturnXML = bankSOAP.getResponseFromWebService(studentID, methodName, userID);
        // place the returned value in the request
        request.setAttribute("getAccountNumber", bankSOAP.getValue(soapReturnXML, methodName));

        methodName = "getBalance";
        // call the post method to send our soap and get some xml in return
        soapReturnXML = bankSOAP.getResponseFromWebService(studentID, methodName, userID);
        // place the returned value in the request
        request.setAttribute("getBalance", bankSOAP.getValue(soapReturnXML, methodName));
        boolean transactionStatus=true;
        try {
        name = (String)request.getAttribute("getName");
        rNumber = Double.valueOf((String)request.getAttribute("getRoutingNumber"));
        aNumber = Double.valueOf((String)request.getAttribute("getAccountNumber"));
        bal = Double.valueOf((String)request.getAttribute("getBalance"));
        } catch(Exception e) {
            System.out.println(e);
        }

        // set the desired new balance
        if (userID.equals(name) && accountNumber == aNumber && routingNumber == rNumber){
            if(bal >= bill) {
            // call the post method to send our soap
            bankSOAP.setBalanceInWebService(studentID, "setBalance", userID, bill);
            } else {
                transactionStatus=false;
                System.out.println("insufficient balance");
            }
        } else {
                            transactionStatus=false;

            System.out.println("incorrect details");
        }
        
        //RequestDispatcher dispatcher = request.getRequestDispatcher("BankCustomerTester.jsp");
        //dispatcher.forward(request, response);
        methodName = "getBalance";
        // call the post method to send our soap and get some xml in return
        soapReturnXML = bankSOAP.getResponseFromWebService(studentID, methodName, userID);
        // place the returned value in the request
        request.setAttribute("getBalance1", bankSOAP.getValue(soapReturnXML, methodName));
        
        double bal1 = Double.valueOf((String)request.getAttribute("getBalance1"));
        double a = bal-bal1;
         session.setAttribute("status", "true");
        if (a == bill && transactionStatus) {
            String resp = "<script type='text/javascript'>"
                                    + "window.close();"
                                    + "</script>";
            out.print(resp);
            
             session.setAttribute("transaction_status", "true");

            /*Now to save the booking history*/          
            List list = (ArrayList) session.getAttribute("bookings");
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                try {
                    Bookings b = (Bookings) itr.next();               
                        Flight.flightUpdateBookings(Integer.valueOf(b.getSeats()),
                                b.getFlightNumber(), (getServletContext()));
                        int bookingId = b.save();
                        b.setBookingId(bookingId+"");
                } catch (SQLException ex) {
                    Logger.getLogger(BankService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BankService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(BankService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(BankService.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        }else{
             session.setAttribute("transaction_status", "false");
        }
    }

    private String filter(String input) {
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for(int i=0; i<input.length(); i++) {
            c = input.charAt(i);
            if (c == '<') {
                filtered.append("&lt;");
            } else if (c == '>') {
                filtered.append("&gt;");
            } else if (c == '"') {
                filtered.append("&quot;");
            } else if (c == '&') {
                filtered.append("&amp;");
            } else {
                filtered.append(c);
            }
        }
        return(filtered.toString());
    }

    private int getIntParameter(HttpServletRequest request,
            String paramName,
            int defaultValue) {
        /** Read a parameter with the specified name, convert it
         *  to an int, and return it. Return the designated default
         *  value if the parameter doesn't exist or if it is an
         *  illegal integer format.
         */
        String paramString = request.getParameter(paramName);
        int paramValue;
        try {
            paramValue = Integer.parseInt(paramString);
        } catch(NumberFormatException nfe) { // null or bad format
            paramValue = defaultValue;
        }
        return(paramValue);
    }
    private double getDoubleParameter(HttpServletRequest request,
            String paramName,
            int defaultValue) {
        /** Read a parameter with the specified name, convert it
         *  to an double, and return it. Return the designated default
         *  value if the parameter doesn't exist or if it is an
         *  illegal integer format.
         */
        String paramString = request.getParameter(paramName);
        double paramValue;
        try {
            paramValue = Double.parseDouble(paramString);
        } catch(NumberFormatException nfe) { // null or bad format
            paramValue = defaultValue;
        }
        return(paramValue);
    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
