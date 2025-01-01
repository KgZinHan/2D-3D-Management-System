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

import com.dao.RecoverTableDao;
import com.dao.RecoverTableDaoImpl;
import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.ColorCount2D;
import com.entity.Number2D;

import common.CommonConstants;
import common.CommonParameters;

@WebServlet("/WaitingTableController")
public class WaitingTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	int total;
	int recoverTotal;
	TableDao tableDao;
	RecoverTableDao recoverTableDao;

	public WaitingTableController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);

		List<Number2D> top2D = new ArrayList<Number2D>();
		List<Number2D> countList = new ArrayList<Number2D>();
		String numberHColor = "";
		String moneyHColor = "";
		String quantityHColor = "";
		int redCount = 0;
		int greenCount = 0;
		int blackCount = 0;
		ColorCount2D count2D = new ColorCount2D();

		String entity = request.getParameter("m");

		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();

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
		for (int j = 0; j < top2D.size(); j++) {

			int netMoney = (top2D.get(j).getMoney() * 80) + ((total * CommonConstants.AVERAGE_COMM_PERCENT) / 100)
					+ recoverTotal;

			int calculatedMoney = total - netMoney;
			if (calculatedMoney >= CommonConstants.HAPPY_LIMIT) {
				top2D.get(j).setColor("green");
			}

			else if (netMoney > total) {
				top2D.get(j).setColor("red");
			}

			/*
			 * else if (calculatedMoney <= CommonConstants.FINAL_LIMIT) {
			 * top2D.get(j).setColor("red"); }
			 */

		}

		// color count method
		countList = tableDao.sortByMoney();

		for (int j = 0; j < countList.size(); j++) {

			int netMoney = (countList.get(j).getMoney() * 80) + ((total * CommonConstants.AVERAGE_COMM_PERCENT) / 100)
					+ recoverTotal;

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

		count2D.setGreenCount(greenCount);
		count2D.setBlackCount(blackCount);
		count2D.setRedCount(redCount);
		count2D.setPurpleCount(100 - blackCount - redCount - greenCount);

		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TAB_BAR_WAITING_TABLE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.RECOVER_AMOUNT, CommonConstants.RECOVER_LIMIT);
		request.setAttribute(CommonParameters.COLOR_COUNT, count2D);
		request.setAttribute(CommonParameters.NUMBER_SORT_COLOR, numberHColor);
		request.setAttribute(CommonParameters.MONEY_SORT_COLOR, moneyHColor);
		request.setAttribute(CommonParameters.QUANTITY_SORT_COLOR, quantityHColor);
		request.setAttribute(CommonParameters.TWO_D_LIST, top2D);
		dispatcher = request.getRequestDispatcher("/waiting");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);

		ColorCount2D count2D = new ColorCount2D();
		int redCount = 0;
		int greenCount = 0;
		int blackCount = 0;
		String startS = request.getParameter("start");
		int start = Integer.parseInt(startS);

		twoDList = tableDao.filterStart(start);
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();

		for (int j = 0; j < twoDList.size(); j++) {

			int netMoney = (twoDList.get(j).getMoney() * 80) + ((total * CommonConstants.AVERAGE_COMM_PERCENT) / 100)
					+ recoverTotal;

			int calculatedMoney = total - netMoney;

			if (calculatedMoney >= CommonConstants.HAPPY_LIMIT) {
				twoDList.get(j).setColor("green");
			}

			else if (netMoney > total) {
				twoDList.get(j).setColor("red");
			}

			/*
			 * else if (calculatedMoney <= CommonConstants.FINAL_LIMIT) {
			 * twoDList.get(j).setColor("red"); }
			 */

		}

		// color count method
		for (int j = 0; j < twoDList.size(); j++) {

			int netMoney = (twoDList.get(j).getMoney() * 80) + ((total * CommonConstants.AVERAGE_COMM_PERCENT) / 100)
					+ recoverTotal;
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

		count2D.setGreenCount(greenCount * 10);
		count2D.setBlackCount(blackCount * 10);
		count2D.setRedCount(redCount * 10);
		count2D.setPurpleCount((10 - blackCount - redCount - greenCount) * 10);

		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TAB_BAR_WAITING_TABLE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.RECOVER_AMOUNT, CommonConstants.RECOVER_LIMIT);
		request.setAttribute(CommonParameters.COLOR_COUNT, count2D);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/waiting");
		dispatcher.forward(request, response);
	}

}
