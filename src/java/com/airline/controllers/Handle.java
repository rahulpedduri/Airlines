/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.controllers;

import com.airline.beans.Flight;
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
public class Handle extends HttpServlet {

    private static final String QUERY_REULTS = "UserLevel/flight_search_results.jsp";
    private static final String DETAILED_VIEW = "UserLevel/view_and_book.jsp";

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
            Object flight_search_query = request.getParameter("flight_search_submit");
            Object detailed = request.getParameter("detailed");
            Flight flight;

            if (flight_search_query != null && !((String) flight_search_query).trim().equals("")) {
                String source = request.getParameter("source");
                String dest = request.getParameter("dest");
                String date = request.getParameter("date");
                String seats = request.getParameter("seats");
                String cls = request.getParameter("class");

                flight = new Flight(getServletContext());

                ArrayList list = flight.getFlightsByConditions(source, dest, date, seats, cls);
                request.setAttribute("flights", list);
                request.getRequestDispatcher(QUERY_REULTS).forward(request, response);

            }
            if (detailed != null && !((String) detailed).trim().equals("")
                    && ((String) detailed).trim().equalsIgnoreCase("true")) {
                String id = request.getParameter("flight");
                flight = new Flight(getServletContext(), id);
                request.setAttribute("flight", flight);
                request.getRequestDispatcher(DETAILED_VIEW).forward(request, response);

            }


        } catch (Exception e) {
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
