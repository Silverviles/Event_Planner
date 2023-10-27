package com.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.User;
import database.UserDB;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Map<Integer, User> userDetails = UserDB.validate(username, password);

            if (userDetails != null && userDetails.containsKey(1)) {
                // User is validated, set user details as a request attribute
                request.setAttribute("userDetails", userDetails.get(1));
                User u = UserDB.retrieve(username);
                HttpSession session = request.getSession();
                session.setAttribute("userid", u.getUserid());
                session.setAttribute("username", u.getUsername());
                session.setAttribute("password", u.getPassword());

                if(u.isAdmin()) {
                	session.setAttribute("type", "admin");
                }
                else if(u.isEvent_organizer()) {
                	session.setAttribute("type", "event_organizer");
                }
                else if(u.isService_provider()) {
                	session.setAttribute("type", "service_provider");
                }
                else {
                	session.setAttribute("type", "customer");
                }

                RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
                dis.forward(request, response);
            } else if (userDetails != null && userDetails.containsKey(0)) {
                // User is not validated (password mismatch), redirect to signup.jsp with status 0
                response.sendRedirect("Login/user.jsp?status=0");
            } else {
                // User is not validated (user not found), redirect to signup.jsp with status -1
                response.sendRedirect("Login/user.jsp?status=-1");
            }
        } catch (Exception e) {
            // Handle exceptions, such as database errors
            e.printStackTrace();
        }
    }
}