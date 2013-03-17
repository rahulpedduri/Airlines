/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.controllers;

import com.airline.beans.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *This servlet handles the login part of the web application.
 * @author Phani Rahul
 */
public class Login extends HttpServlet {

   
    private final String ON_SUCCESS="UserLevel/flight_search_query.jsp";
    private final String ON_FAIL="login.jsp";
    private String username;
    private String password;
    private User user;
    private HashMap messages;
    private HttpSession session;
    private Cookie cookie;

     /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods. This especially handles the login request from the user.
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
            /* TODO output your page here. You may use following sample code. */
            Object login = request.getParameter("login_submit");

            if (login != null && !((String) login).trim().equals("")) {
                username = request.getParameter("username");
                password = request.getParameter("password");
                
                messages = new HashMap();

                try {
                    user = new User(getServletContext());
                   // System.out.println("servlet contxt: "+ getServletContext().getRealPath(User.DATA_FILE));
                   // User.loadPropertiesFromFile(getServletContext().getRealPath(User.DATA_FILE));
               
                }
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (checkString(username) && checkString(password)) {
                    boolean log = false;
                   try{
                        log = user.loginCheck(username, password);
                   }catch(Exception ex){
                       Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                   }
                    System.out.println("login: " + log);
                    if (!log) {
                        messages.put("login.fail", "Username/Password is incorrect");
                         request.setAttribute("message", messages);
                        request.getRequestDispatcher(ON_FAIL).forward(request, response);
                        return;
                    }
                    else
                    {
                      session =  request.getSession();
                      session.setAttribute("user", user);
                      request.setAttribute("message", messages);
                     System.out.println("redirecting..");
                     response.sendRedirect(ON_SUCCESS);
                     return;
                    }
                }
                else
                { 
                    messages.put("login.unknown", "Login failed because on invalid values provided");
                     request.setAttribute("message", messages);
                    request.getRequestDispatcher(ON_FAIL).forward(request, response);
                }
                
            }

        } finally {
            out.close();
           
        }
    }

    private boolean checkString(String string) {
        if (string != null && !string.trim().equals("")) {
            return true;
        }
        return false;
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
