package cms.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cms.business.User;
import cms.data.*;

public class UserController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.jsp";
        
        // Get action
        String action = request.getParameter("action");
        
        if (action.equals("add")) {
            url = "/user/add.jsp";
        } else if (action.equals("list")) {
            request.setAttribute("users", UserDB.list());    
            url = "/user/list.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String firstName = request.getParameter("firstName");
        
        User newUser = new User(firstName);
        UserDB.insert(newUser);
        
        // Show updated list of all users
        response.sendRedirect("users?action=list");
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
