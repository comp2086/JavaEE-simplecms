/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TankJr
 */
public class UserController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.html";
        
        // Get action
        String action = request.getParameter("action");
        
        if (action.equals("add")) {
            url = "/add.jsp";
        } else if (action.equals("list")) {
            url = "/list.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.html";
        
        String firstName = request.getParameter("firstName");
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
