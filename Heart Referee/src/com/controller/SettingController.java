package com.controller;

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

import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.History2D;
import com.entity.Number2D;

import common.CommonParameters;

@WebServlet("/SettingController")
public class SettingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<String> userList = new ArrayList<String>();
	History2D twoDH = null;
	int total;
	int userTotal;
	String shortMsg;
	TableDao tableDao = new TableDaoImpl();

	public SettingController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		session.setAttribute(CommonParameters.SESSION_USER, userName);
		session.setAttribute(CommonParameters.SESSION_NAME, "history");
		session.setMaxInactiveInterval(-1);
		response.sendRedirect("History");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
