/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cms.business.User;
import cms.data.ConnectionPool;
import java.sql.*;
import java.util.*;

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
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            Statement stmt = null;
            ResultSet rs = null;
            
            try {
                String sql = "SELECT * FROM Users";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                
                ArrayList<User> users = new ArrayList<>();
                while (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setFirstName(rs.getString("firstName"));
                    users.add(u);
                }
                request.setAttribute("users", users);
                
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
            
            pool.freeConnection(conn);
                
            url = "/list.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String firstName = request.getParameter("firstName");
        User user = new User(firstName);
           
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement ps = null;
        
        try {
            String preparedSQL = "INSERT INTO Users(firstName) VALUES ( ? )";
            ps = conn.prepareStatement(preparedSQL);
            ps.setString(1, user.getFirstName());
            ps.executeUpdate();
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        // Show updated list of all users
        response.sendRedirect("users?action=list");
    }
}
