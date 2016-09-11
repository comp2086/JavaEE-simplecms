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
        
        String url = null;
        int userId = -1;
        
        String action = request.getParameter("action");
        
        switch (action)
        {   
            case "list":
                request.setAttribute("users", UserDB.list());    
                url = "/user/list.jsp";
                break;
            case "add":
                url = "/user/add.jsp";
                break;
            case "edit":
                userId = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("user", UserDB.getUser(userId));
                url = "/user/edit.jsp";
                break;
            case "delete":
                userId = Integer.parseInt(request.getParameter("id"));
                UserDB.delete(userId);
                url = "users?action=list";
                break;
            default:
                url = "/index.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        User user = new User();
        String firstName = null;
        
        switch (action)
        {
            case "add":
                firstName = request.getParameter("firstName");
                user.setFirstName(firstName);
                UserDB.insert(user);
                break;
            case "edit":
                int userId = Integer.parseInt(request.getParameter("id"));
                firstName = request.getParameter("firstName");
                user.setId(userId);
                user.setFirstName(firstName);
                UserDB.update(user);
                break;
        }
        
        // Show updated list of all users
        response.sendRedirect("users?action=list");
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
