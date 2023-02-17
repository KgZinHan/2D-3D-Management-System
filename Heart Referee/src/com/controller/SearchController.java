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
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public SearchController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> dNumberList = new ArrayList<Integer>();
		List<Number2D> countList = new ArrayList<Number2D>();
		String search = request.getParameter("number");
		int number = Integer.parseInt(search);
		ColorCount2D count2D = new ColorCount2D();
		String dNumbers = "0";
		int redCount = 0;
		int blueCount = 0;
		int greenCount = 0;
		int orangeCount = 0;
		int blackCount = 0;

		twoDList = tableDao.search2DAmount(number);
		for (int j = 0; j < twoDList.size(); j++) {
			if (getTotal()-((twoDList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) > CommonConstants.FINAL_LIMIT) {
				twoDList.get(j).setColor("blue");
			}
			if (twoDList.get(j).getMoney() < CommonConstants.VERY_HAPPY_LIMIT) {
				twoDList.get(j).setColor("green");
			}
			if (getTotal()-((twoDList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) < recoverTotal) {
				twoDList.get(j).setColor("orange");
			}
			if ((twoDList.get(j).getMoney() * 80) > getTotal()) {
				twoDList.get(j).setColor("red");
			}
		}
		if (twoDList.size() < 1) {
			request.setAttribute(CommonParameters.MESSAGE, "Dance off bro!! Number not found.");
		}
		
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney(); 
		dNumberList = tableDao.getDangerousNumber();
		if (!(dNumberList.size() <= 0)) {
			dNumbers = dNumberList.get(0).toString();
			for (int i = 1; i < dNumberList.size(); i++) {
				if(dNumberList.get(i) < 10) {
					dNumbers = dNumbers + " - " + "0"+ dNumberList.get(i).toString();
				}
				else {
					dNumbers = dNumbers + " - " + dNumberList.get(i).toString();
				}		
			}
		}
		
		//color count method
		countList = tableDao.sortByMoney();
		for (int j = 0; j < countList.size(); j++) {
			if (countList.get(j).getMoney() < CommonConstants.VERY_HAPPY_LIMIT) {
				greenCount = greenCount + 1;
			}	
			else if (getTotal()-((countList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) > CommonConstants.FINAL_LIMIT) {
				blueCount = blueCount + 1;
			}
			else if ((countList.get(j).getMoney() * 80) > getTotal()) {
				redCount = redCount + 1;
			}
			else if (getTotal()-((countList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) < recoverTotal ) {
				orangeCount = orangeCount + 1;
			}	
			else {
				blackCount = blackCount + 1;
			}
		}
		count2D.setGreenCount(greenCount);
		count2D.setBlueCount(blueCount);
		count2D.setBlackCount(blackCount);
		count2D.setOrangeCount(orangeCount);
		count2D.setRedCount(redCount);
		count2D.setPurpleCount(100 - greenCount - blueCount - blackCount - orangeCount - redCount);
		
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TAB_BAR_WAITING_TABLE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.DANGEROUS_NUMBERS, dNumbers);
		request.setAttribute(CommonParameters.RECOVER_AMOUNT, CommonConstants.RECOVER_LIMIT);
		request.setAttribute(CommonParameters.COLOR_COUNT, count2D);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/waiting");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		String search = request.getParameter("number");
		int number = Integer.parseInt(search);
		List<Integer> dNumberList = new ArrayList<Integer>();
		String dNumbers = "0";
		twoDList = tableDao.search2DAmountByUser(number, userName);
		for (int j = 0; j < twoDList.size(); j++) {
			if (getTotal()-((twoDList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) > CommonConstants.FINAL_LIMIT) {
				twoDList.get(j).setColor("blue");
			}
			if (twoDList.get(j).getMoney() < CommonConstants.VERY_HAPPY_LIMIT) {
				twoDList.get(j).setColor("green");
			}
			if (getTotal()-((twoDList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) < CommonConstants.RECOVER_LIMIT ) {
				twoDList.get(j).setColor("orange");
			}
			if ((twoDList.get(j).getMoney() * 80) > getTotal()) {
				twoDList.get(j).setColor("red");
			}
		}
		twoDCheckList = tableDao.getHistoryTableByUsername(userName);
		total = tableDao.getTotalMoney();
		userTotal = tableDao.getUserTotalMoney(userName);
		userList = tableDao.getUsers();
		realID = tableDao.getIdCount();

		dNumberList = tableDao.getDangerousNumber();
		if (!(dNumberList.size() <= 0)) {
			dNumbers = dNumberList.get(0).toString();
			for (int i = 1; i < dNumberList.size(); i++) {
				if(dNumberList.get(i) < 10) {
					dNumbers = dNumbers + " - " + "0"+ dNumberList.get(i).toString();
				}
				else {
					dNumbers = dNumbers + " - " + dNumberList.get(i).toString();
				}		
			}
		}

		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, userTotal);
		request.setAttribute(CommonParameters.DANGEROUS_NUMBERS, dNumbers);
		request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR, "aqua");
		request.setAttribute(CommonParameters.REAL_ID, realID);
		request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.TOTAL_MONEY_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.USER_LIST, userList);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/home");
		dispatcher.forward(request, response);
	}
	
	protected int getTotal() {
		int totalMoney = 0;
		totalMoney = tableDao.getTotalMoney();
		return totalMoney;

	}

}
