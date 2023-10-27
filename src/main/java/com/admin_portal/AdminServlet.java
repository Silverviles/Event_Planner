package com.admin_portal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.User;
import database.UserDB;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		HttpSession session;
		if(request.getSession() == null) {
			RequestDispatcher dis = request.getRequestDispatcher("user.jsp");
			dis.forward(request, response);
		}
		else if(request.getSession() != null) {
			session = request.getSession();
			
		}
		*/
		List<User> users = new ArrayList<>();
		List<User> admins = new ArrayList<>();
		List<User> service_providers = new ArrayList<>();
		List<User> event_organizers = new ArrayList<>();
		users = UserDB.retrieve(0);
		admins = UserDB.retrieve(1);
		service_providers = UserDB.retrieve(3);
		event_organizers = UserDB.retrieve(2);

		int userCount = users.size();
		int customers = UserDB.getCustomerCount();
		int adminCount = admins.size();
		int service_providerCount = service_providers.size();
		int event_organizerCount = event_organizers.size();

		request.setAttribute("users", users);
		request.setAttribute("user_count", userCount);
		request.setAttribute("admins", admins);
		request.setAttribute("admin_count", adminCount);
		request.setAttribute("service_providers", service_providers);
		request.setAttribute("service_provider_count", service_providerCount);
		request.setAttribute("event_organizers", event_organizers);
		request.setAttribute("event_organizer_count", event_organizerCount);
		request.setAttribute("customer_count", customers);
        RequestDispatcher dis = request.getRequestDispatcher("AdminPortal/admin_portal.jsp");
        dis.forward(request, response);
	}

}
