/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.controllers;

import com.airline.beans.BankResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author Phani Rahul
 */
public class PendingTransaction extends HttpServlet {

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
            Object status = request.getParameter("status");
            if (status != null && !((String) status).trim().equals("")) {

                /*sets the session status as pending until the transaction with the bank is cmplete*/
                if (((String) status).equals("set_pending")) {

                    session.setAttribute("status", false);

                } /*checks to see if the status has changed*/ else if (((String) status).equals("waiting")) {
                    /*return json on the status of application*/
                    //.
                    //.
                    //.
                    /* TODO                    
                     {status : true/false, html : <...based on the status>}           
                     */
                    JSONObject resp = new JSONObject();

                    BankResponse b;
                    String stat = (String) session.getAttribute("status");
                    if (stat.equals("true")) {
                        resp.put("status", "true");

                        b = (BankResponse) session.getAttribute("bank_response");
                        if (b.isTransaction_status()) {
                            resp.put("html", "<h1>Sucessform</h1>");
                        } else {
                            resp.put("html", "<h1>Transaction Failed!</h1>");
                        }
                        //out.print("{ status: \"true\" , html: \"<h1>test</h1>\" }");
                        //out.print(resp);
                    } else {
                        resp.put("status", "false");
                    }
                    out.print(resp);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
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