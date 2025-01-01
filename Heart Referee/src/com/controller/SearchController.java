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

import com.dao.RecoverTableDao;
import com.dao.RecoverTableDaoImpl;
import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.ColorCount2D;
import com.entity.Number2D;
import com.entity.User2D;

import common.CommonConstants;
import common.CommonParameters;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<Number2D> twoDCheckList = new ArrayList<Number2D>();
	List<User2D> userList = new ArrayList<User2D>();
	int total;
	int recoverTotal;
	int userTotal;
	int realID;
	String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;
	TableDao tableDao;
	RecoverTableDao recoverTableDao;

	public SearchController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);

		List<Number2D> countList = new ArrayList<Number2D>();
		ColorCount2D count2D = new ColorCount2D();
		int redCount = 0;
		int greenCount = 0;
		int orangeCount = 0;
		int blackCount = 0;

		String search = request.getParameter("number");
		int number = Integer.parseInt(search);

		twoDList = tableDao.search2DAmount(number);
		total = tableDao.getTotalMoney();

		for (int j = 0; j < twoDList.size(); j++) {

			int netMoney = (twoDList.get(j).getMoney() * 80) + ((total * 15) / 100) + recoverTotal;

			int calculatedMoney = total - netMoney;
			if (calculatedMoney > CommonConstants.HAPPY_LIMIT) {
				twoDList.get(j).setColor("green");
			}

			/*
			 * else if (netMoney > total) { twoDList.get(j).setColor("red"); }
			 */

			else if (calculatedMoney <= CommonConstants.FINAL_LIMIT) {
				twoDList.get(j).setColor("red");
			}

		}

		recoverTotal = recoverTableDao.getTotalRecoverMoney();

		// color count method
		countList = tableDao.sortByMoney();
		for (int j = 0; j < countList.size(); j++) {

			int netMoney = (countList.get(j).getMoney() * 80) + ((total * CommonConstants.AVERAGE_COMM_PERCENT) / 100) + recoverTotal;

			int calculatedMoney = total - netMoney;
			if (calculatedMoney >= CommonConstants.HAPPY_LIMIT) {
				greenCount = greenCount + 1;
			}

			else if (netMoney > total) {
				redCount = redCount + 1;
			} else {
				blackCount = blackCount + 1;
			}

			/*
			 * else if (calculatedMoney <= CommonConstants.FINAL_LIMIT) { redCount =
			 * redCount + 1; } else { blackCount = blackCount + 1; }
			 */

		}

		count2D.setBlackCount(blackCount);
		count2D.setOrangeCount(orangeCount);
		count2D.setRedCount(redCount);
		count2D.setGreenCount(greenCount);
		count2D.setPurpleCount(100 - blackCount - redCount - greenCount);

		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TAB_BAR_WAITING_TABLE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.RECOVER_AMOUNT, CommonConstants.RECOVER_LIMIT);
		request.setAttribute(CommonParameters.COLOR_COUNT, count2D);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/waiting");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);

		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);

		String search = request.getParameter("number");
		int number = Integer.parseInt(search);

		twoDList = tableDao.search2DAmountByUser(number, userName);
		twoDCheckList = tableDao.getHistoryTableByUsername(userName);
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
		request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.TOTAL_MONEY_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.USER_LIST, userList);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}
}
