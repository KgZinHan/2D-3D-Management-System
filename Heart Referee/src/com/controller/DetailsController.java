package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.History2D;
import com.entity.Number2D;
import com.entity.User2D;

import common.CommonConstants;
import common.CommonParameters;

public class DetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<User2D> userList = new ArrayList<User2D>();
	History2D twoDH = null;
	int total;
	int userTotal;
	String shortMsg;
	TableDao tableDao = new TableDaoImpl();

	public DetailsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		int realID;
		String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;
		
		twoDList = tableDao.getTableByUser(userName);
		total = tableDao.getTotalMoney();
		userTotal = tableDao.getUserTotalMoney(userName);
		userList = tableDao.getUsers();
		realID = tableDao.getIdCount();
		
		if(realID > CommonConstants.ID_COUNT_LIMIT) {
			idAlertColor = CommonConstants.ID_ALERT_COLOR; 
		}

		for(int i = 0;i < userList.size();i++) {
			String checked = "red";
			if(tableDao.checkNameInTempTable(userList.get(i).getUser()) == true)
			{
				checked = "green";
			}
			userList.get(i).setChecked(checked);
		}

		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, userTotal);
		request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.REAL_ID, realID);
		request.setAttribute(CommonParameters.ID_ALERT_COLOR, idAlertColor);
		request.setAttribute(CommonParameters.PAGE_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.DELETE_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.USER_LIST, userList);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int realID;
		String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		String search = request.getParameter("number");
		int number = Integer.parseInt(search);
		
		twoDList = tableDao.getNumberDetailsByUser(userName,number);
		
		total = tableDao.getTotalMoney();
		userTotal = tableDao.getUserTotalMoney(userName);
		userList = tableDao.getUsers();
		realID = tableDao.getIdCount();
		
		if(realID > CommonConstants.ID_COUNT_LIMIT) {
			idAlertColor = CommonConstants.ID_ALERT_COLOR; 
		}

		for(int i = 0;i < userList.size();i++) {
			String checked = "red";
			if(tableDao.checkNameInTempTable(userList.get(i).getUser()) == true)
			{
				checked = "green";
			}
			userList.get(i).setChecked(checked);
		}

		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, userTotal);
		request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.REAL_ID, realID);
		request.setAttribute(CommonParameters.ID_ALERT_COLOR, idAlertColor);
		request.setAttribute(CommonParameters.PAGE_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.DELETE_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.USER_LIST, userList);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}

	protected int getReverse(int number) {
		int reverse = 0;
		while (number != 0) {
			int remainder = number % 10;
			reverse = reverse * 10 + remainder;
			number = number / 10;
		}
		return reverse;
	}

}
