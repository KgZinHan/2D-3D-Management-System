package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CommonConstants;
import common.CommonParameters;

@WebServlet("/SettingController")
public class SettingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;

	public SettingController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String partition = request.getParameter("partition");
		//String mode = request.getParameter("mode");
		if(!partition.equals("done1998")) {
			session.setAttribute(CommonParameters.SESSION_PARTITION, partition);
		}
		session.setAttribute(CommonParameters.SESSION_USER, userName);
		session.setAttribute(CommonParameters.SESSION_NAME, "history");
		session.setMaxInactiveInterval(-1);
		response.sendRedirect("History");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String table = request.getParameter("defaultTable");
		session.setAttribute(CommonParameters.SESSION_NAME, table);
		if (table == null || table.isEmpty() || table.equals("details")) {
			response.sendRedirect("Details");
		} else if (table.equals("history")) {
			response.sendRedirect("History");
		} else if (table.equals("normal")) {
			response.sendRedirect("SortByUser?m=money");
		}
	}

}
