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
import java.sql.*;
import java.util.*;

/**
 *
 * @author TankJr
 */
public class UserController extends HttpServlet {

    private void connect() {
        try {
           Class.forName("com.mysql.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.html";
        
        // Get action
        String action = request.getParameter("action");
        
        if (action.equals("add")) {
            url = "/add.jsp";
        } else if (action.equals("list")) {
            String dbURL = "jdbc:mysql://localhost:3306/simplecms";
            String userName = "root";
            String password = "sesame";
            
            connect();
         
            try (Connection conn = DriverManager.getConnection(dbURL, userName, password)) {
                String sql = "SELECT * FROM Users";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                
                ArrayList<User> users = new ArrayList<>();
                while (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setFirstName(rs.getString("firstName"));
                    users.add(u);
                }
                
                request.setAttribute("users", users);
            } catch (SQLException e) {
                for (Throwable t : e)
                    t.printStackTrace();
            }
            
            url = "/list.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String firstName = request.getParameter("firstName");
        User user = new User(firstName);
           
        String dbURL = "jdbc:mysql://localhost:3306/simplecms";
        String userName = "root";
        String password = "sesame";
        
        connect();
        
        try (Connection conn = DriverManager.getConnection(dbURL, userName, password)) {
            String preparedSQL = "INSERT INTO Users(firstName) VALUES ( ? )";
            PreparedStatement ps = conn.prepareStatement(preparedSQL);
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
