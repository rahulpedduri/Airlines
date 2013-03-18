/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.controllers;

import com.airline.beans.User;
import com.airline.db.ConnectionParameters;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles the registration part of the web application.
 *
 * @author Phani Rahul
 */
public class Registration extends HttpServlet {

    private final String ON_SUCCESS = "login.jsp";
    private final String ON_FAIL = "registration.jsp";
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private User user;
    private HashMap messages;

    /**
     * Processes requests for both HTTP. This especially handles the
     * registration request from the user.
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


            Object registration = request.getParameter("registration_submit");
            if (registration != null && !((String) registration).trim().equals("")) {

                username = request.getParameter("username");
                password = request.getParameter("password");
                firstName = request.getParameter("firstname");
                lastName = request.getParameter("lastname");
                email = request.getParameter("email");
                phone = request.getParameter("phone");

                messages = new HashMap();



                boolean flag = true;
                if (checkString(username) && flag) {
                    //todo: check if username already exists
                    if (!User.checkUsernameExists(username, ConnectionParameters.getConnectionParameters(getServletContext()))) {
                        user = new User(getServletContext());
                        user.setUsername(username);
                    } else {
                        flag = false;
                        messages.put("registration.fail", "Username Already Taken");
                    }

                    if (checkString(password) && flag) {
                        user.setPassword(password);
                    }
                    if (checkString(firstName) && flag) {
                        user.setFirstName(firstName);
                    }
                    if (checkString(lastName) && flag) {
                        user.setLastName(lastName);
                    }
                    if (checkString(phone) && flag) {
                        user.setPhone(phone);
                    }
                    if (checkString(email) && flag) {
                        user.setEmail(email);
                    }
                }
                if (flag) {
                    flag = user.save();
                }
                if (!flag) {
                    request.setAttribute("message", messages);
                    request.getRequestDispatcher(ON_FAIL).forward(request, response);

                } else {

                    System.out.println("done..");
                    response.sendRedirect(ON_SUCCESS);
                    return;
                }
            }

            System.out.println(messages);
            out.close();


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private boolean checkString(String string) {
        if (string != null && !string.trim().equals("")) {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

        super.destroy();
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
