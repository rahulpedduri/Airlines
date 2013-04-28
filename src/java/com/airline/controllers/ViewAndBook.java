/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.controllers;

import com.airline.beans.Bookings;
import com.airline.beans.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phani Rahul
 */
public class ViewAndBook extends HttpServlet {

    private static final String TRANSACTION="UserLevel/transaction.jsp";
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
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            if(user == null){
                response.sendRedirect("login.jsp");
            }
             Object submit = request.getParameter("view_and_book_submit");

            if (submit != null && !((String) submit).trim().equals("")) {
                String flightNumber = request.getParameter("id");
                String seats = request.getParameter("seats");
                String cost = request.getParameter("cost");
                double totalCost=0;
               if (seats != null && !((String) seats).trim().equals("") 
                       && cost != null && !((String) cost).trim().equals("")) {
                 totalCost= Double.valueOf(cost)*Integer.valueOf(seats);
               }
                
                Bookings booking = new Bookings(getServletContext());
                booking.setFlightNumber(flightNumber);
                booking.addFlightToBean();
                booking.setSeats(seats);
                booking.setTotalCost(totalCost);
                booking.setUsername(((User)session.getAttribute("user")).getUsername());
                ArrayList bookingList = (ArrayList)session.getAttribute("bookings");
                boolean alreadyThere=true;
                if(bookingList == null){
                    alreadyThere=false;
                    bookingList = new ArrayList();
                }
                bookingList.add(booking);
                session.setAttribute("bookings", bookingList);
                response.sendRedirect(TRANSACTION);
                double totalBill;
                totalBill = totalCost;
                Object obj = session.getAttribute("bill");
                if(obj != null){
                    Double num =(Double)obj;
                    double previous= Double.valueOf(num);
                    totalBill += previous;
                }
               session.setAttribute("bill", totalBill);

            }
        }catch(Exception e){
            System.out.println("EXCEPTION: "+e);
        }finally {            
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
