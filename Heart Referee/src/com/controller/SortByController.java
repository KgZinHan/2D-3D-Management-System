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
import com.entity.Number2D;
import com.entity.User2D;

import common.CommonConstants;
import common.CommonParameters;

public class SortByController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	int total;
	int userTotal;
	int realID;
	TableDao tableDao;

	public SortByController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);

		List<Number2D> top2D = new ArrayList<Number2D>();
		List<User2D> userList = new ArrayList<User2D>();
		String numberHColor = "";
		String moneyHColor = "";
		String quantityHColor = "";
		String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;

		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);

		String entity = request.getParameter("m");

		if (entity == "number" || entity.equals("number")) {
			top2D = tableDao.sortByNumber();
			numberHColor = CommonConstants.SORT_COLOR_CODE;
		}

		if (entity == "money" || entity.equals("money")) {
			top2D = tableDao.sortByMoney();
			moneyHColor = CommonConstants.SORT_COLOR_CODE;
		}

		if (entity == "quantity" || entity.equals("quantity")) {
			top2D = tableDao.sortByQuantity();
			quantityHColor = CommonConstants.SORT_COLOR_CODE;
		}

		total = tableDao.getTotalMoney();
		userTotal = tableDao.getUserTotalMoney(userName);
		userList = tableDao.getUsers();
		realID = tableDao.getIdCount();

		if (realID > CommonConstants.ID_COUNT_LIMIT) {
			idAlertColor = CommonConstants.ID_ALERT_COLOR;
		}

		for (int i = 0; i < userList.size(); i++) {
			String checked = "red";
			if (tableDao.checkNameInTempTable(userList.get(i).getUser()) == true) {
				checked = "green";
			}
			userList.get(i).setChecked(checked);
		}

		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, userTotal);
		request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.REAL_ID, realID);
		request.setAttribute(CommonParameters.ID_ALERT_COLOR, idAlertColor);
		request.setAttribute(CommonParameters.NUMBER_SORT_COLOR, numberHColor);
		request.setAttribute(CommonParameters.MONEY_SORT_COLOR, moneyHColor);
		request.setAttribute(CommonParameters.QUANTITY_SORT_COLOR, quantityHColor);
		request.setAttribute(CommonParameters.USER_LIST, userList);
		request.setAttribute(CommonParameters.TWO_D_LIST, top2D);
		dispatcher = request.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
