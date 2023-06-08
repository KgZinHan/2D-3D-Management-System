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

public class SortByUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	int total;
	int userTotal;
	int realID;
	TableDao tableDao = new TableDaoImpl();

	public SortByUserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		List<Number2D> top2D = new ArrayList<Number2D>();
		List<User2D> userList = new ArrayList<User2D>();
		String entity = request.getParameter("m");
		String numberHColor = "";
		String moneyHColor = "";
		String quantityHColor = "";
		String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;
		
		if (entity == "number" || entity.equals("number")) {
			top2D = tableDao.sortByUserNumber(userName);
			numberHColor = CommonConstants.SORT_COLOR_CODE;
		}
		
		if (entity == "money" || entity.equals("money")) {
			top2D = tableDao.sortByUserMoney(userName);
			moneyHColor = CommonConstants.SORT_COLOR_CODE;
		}
		
		if (entity == "quantity" || entity.equals("quantity")) {
			top2D = tableDao.sortByUserQuantity(userName);
			quantityHColor = CommonConstants.SORT_COLOR_CODE;
		}
		
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
		request.setAttribute(CommonParameters.NUMBER_SORT_COLOR, numberHColor);
		request.setAttribute(CommonParameters.MONEY_SORT_COLOR, moneyHColor);
		request.setAttribute(CommonParameters.QUANTITY_SORT_COLOR, quantityHColor);
		request.setAttribute(CommonParameters.USER_LIST, userList);
		request.setAttribute(CommonParameters.TWO_D_LIST, top2D);
		dispatcher = request.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
