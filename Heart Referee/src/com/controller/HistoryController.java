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

public class HistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<Integer> rNumberList = new ArrayList<Integer>();
	List<User2D> userList = new ArrayList<User2D>();
	int realID;
	String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;
	int total;
	int userTotal;
	TableDao tableDao = new TableDaoImpl();

	public HistoryController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		
		
		twoDList = tableDao.getHistoryTableByUsername(userName);
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
		request.setAttribute(CommonParameters.REAL_ID, realID);
		request.setAttribute(CommonParameters.ID_ALERT_COLOR, idAlertColor);
		request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.NOTE_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.R_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.TOTAL_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.PAGE_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.PAGE_TOTAL_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.NUMBER_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.USER_LIST, userList);
		request.setAttribute(CommonParameters.RECOVER_2D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
