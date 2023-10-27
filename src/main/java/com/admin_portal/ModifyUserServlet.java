package com.admin_portal;

import java.io.IOException;
import database.UserDB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("delete".equals(action)) {
            // Handle user deletion
            int userId = Integer.parseInt(request.getParameter("userid"));
            UserDB.deleteUser(userId);
        } else if ("update".equals(action)) {
            // Handle user update
            int userId = Integer.parseInt(request.getParameter("userid"));
            String userType = request.getParameter("newType");
            UserDB.updateUser(userId, userType);
        }

        // Forward the request to the AdminServlet
        request.getRequestDispatcher("AdminServlet").forward(request, response);
    }
}