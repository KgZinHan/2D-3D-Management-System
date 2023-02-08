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
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public WaitingTableController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Number2D> top2D = new ArrayList<Number2D>();
		List<Number2D> countList = new ArrayList<Number2D>();
		List<Integer> dNumberList = new ArrayList<Integer>();
		ColorCount2D count2D = new ColorCount2D();
		String entity = request.getParameter("m");
		String numberHColor = "";
		String moneyHColor = "";
		String quantityHColor = "";
		String dNumbers = "0";
		int redCount = 0;
		int blueCount = 0;
		int greenCount = 0;
		int orangeCount = 0;
		int blackCount = 0;
		
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
			if (getTotal()-((top2D.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) > CommonConstants.FINAL_LIMIT) {
				top2D.get(j).setColor("blue");
			}
			if (top2D.get(j).getMoney() < CommonConstants.VERY_HAPPY_LIMIT) {
				top2D.get(j).setColor("green");
			}
			if (getTotal()-((top2D.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) < recoverTotal ) {
				top2D.get(j).setColor("rgb(255,165,30)");
			}
			if ((top2D.get(j).getMoney() * 80) > getTotal()) {
				top2D.get(j).setColor("red");
			}
			
		}
		
		
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
		request.setAttribute(CommonParameters.TAB_BAR_WAITING_TABLE_COLOR, "aqua");
		request.setAttribute(CommonParameters.DANGEROUS_NUMBERS, dNumbers);
		request.setAttribute(CommonParameters.RECOVER_AMOUNT, CommonConstants.RECOVER_LIMIT);
		request.setAttribute(CommonParameters.COLOR_COUNT, count2D);
		request.setAttribute(CommonParameters.NUMBER_SORT_COLOR, numberHColor);
		request.setAttribute(CommonParameters.MONEY_SORT_COLOR, moneyHColor);
		request.setAttribute(CommonParameters.QUANTITY_SORT_COLOR, quantityHColor);
		request.setAttribute(CommonParameters.TWO_D_LIST, top2D);
		dispatcher = request.getRequestDispatcher("/waiting");
		dispatcher.forward(request, response);
	}

	protected int getTotal() {
		int totalMoney = 0;
		totalMoney = tableDao.getTotalMoney();
		return totalMoney;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> dNumberList = new ArrayList<Integer>();
		ColorCount2D count2D = new ColorCount2D();
		int redCount = 0;
		int blueCount = 0;
		int greenCount = 0;
		int orangeCount = 0;
		int blackCount = 0;
		String dNumbers = "0";
		String startS = request.getParameter("start");
		int start = Integer.parseInt(startS);
		
		twoDList = tableDao.filterStart(start);
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
		for (int j = 0; j < twoDList.size(); j++) {
			if (getTotal()-((twoDList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) > CommonConstants.FINAL_LIMIT) {
				twoDList.get(j).setColor("blue");
			}
			if (twoDList.get(j).getMoney() < CommonConstants.VERY_HAPPY_LIMIT) {
				twoDList.get(j).setColor("green");
			}
			if (getTotal()-((twoDList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) < recoverTotal ) {
				twoDList.get(j).setColor("rgb(255,165,30)");
			}
			if ((twoDList.get(j).getMoney() * 80) > getTotal()) {
				twoDList.get(j).setColor("red");
			}
		}
		
		 
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
		for (int j = 0; j < twoDList.size(); j++) {
			if (twoDList.get(j).getMoney() < CommonConstants.VERY_HAPPY_LIMIT) {
				greenCount = greenCount + 1;
			}
			else if (getTotal()-((twoDList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) > CommonConstants.FINAL_LIMIT) {
				blueCount = blueCount + 1;
			}
			else if ((twoDList.get(j).getMoney() * 80) > getTotal()) {
				redCount = redCount + 1;
			}
			else if (getTotal()-((twoDList.get(j).getMoney() * 80) + ((getTotal() * 15) / 100)) < recoverTotal ) {
				orangeCount = orangeCount + 1;
			}
			else {
				blackCount = blackCount + 1;
			}
		}
		count2D.setGreenCount(greenCount * 10);
		count2D.setBlueCount(blueCount * 10);
		count2D.setBlackCount(blackCount * 10);
		count2D.setOrangeCount(orangeCount * 10);
		count2D.setRedCount(redCount * 10);
		count2D.setPurpleCount((10 - greenCount - blueCount - blackCount - orangeCount - redCount) * 10);
		
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TAB_BAR_WAITING_TABLE_COLOR , "aqua");
		request.setAttribute(CommonParameters.DANGEROUS_NUMBERS, dNumbers);
		request.setAttribute(CommonParameters.RECOVER_AMOUNT, CommonConstants.RECOVER_LIMIT);
		request.setAttribute(CommonParameters.COLOR_COUNT, count2D);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/waiting");
		dispatcher.forward(request, response);
	}

}
