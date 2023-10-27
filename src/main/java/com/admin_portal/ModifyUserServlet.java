package com.admin_portal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDB;

@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int userId = Integer.parseInt(request.getParameter("userid"));

        if ("delete".equals(action)) {
            // Handle user deletion
            System.out.println(userId);
            UserDB.deleteUser(userId);
        } else if ("update".equals(action)) {
            // Handle user update
            
            String userType = request.getParameter("newType");
            System.out.println(request.getParameter("newType"));
            System.out.println(userId);
            UserDB.updateUser(userId, userType);
        }

        // Forward the request to the AdminServlet
        request.getRequestDispatcher("AdminServlet").forward(request, response);
    }
}