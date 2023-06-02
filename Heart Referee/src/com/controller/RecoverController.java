package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RecoverTableDao;
import com.dao.RecoverTableDaoImpl;
import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.Number2D;

import common.CommonConstants;
import common.CommonParameters;

public class RecoverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	int total;
	int totalRecover;
	int recoverTotal;
	int userTotal;
	Boolean flag = true;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public RecoverController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recoverLimitS = request.getParameter("limit");
		int recoverLimit = Integer.parseInt(recoverLimitS);
		int rNumberMoney;
		List<Number2D> twoDList = new ArrayList<Number2D>();
		List<Integer> rNumberList = new ArrayList<Integer>();
		List<Number2D> recoverList = new ArrayList<Number2D>();
		
		twoDList = tableDao.sortByMoney();
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
		totalRecover = tableDao.getMoneyToRecoverByLimit(recoverLimit);
		
		for (int i = 0; i < twoDList.size(); i++) {
			Number2D thisNumber = new Number2D();
			flag = true;
			thisNumber = twoDList.get(i);
			int number = thisNumber.getNumber();
			int newMoney = thisNumber.getMoney()  - recoverTableDao.getRecoverMoney(number) - recoverLimit;
			
			if (thisNumber.getMoney() > recoverLimit && newMoney > 0) {
				for (int j = 0; j < rNumberList.size(); j++) {
					if (number == rNumberList.get(j)) {
						flag = false;
					}
				}
				
				rNumberMoney = getRNumberMoney(number);
				int rNumber = getReverse(number);
				rNumberList.add(rNumber);
				
				if (flag == true) {
					
					if (rNumberMoney == 99) {
						rNumberMoney = 0;						
					} 
					else if(rNumberMoney >= recoverLimit) 
					{
						rNumberMoney = rNumberMoney - recoverLimit;
					}
					else if (rNumberMoney < recoverLimit)
					{
						rNumberMoney = 0;
					}

					Number2D r2D = new Number2D();
					r2D.setNumber(number);
					r2D.setrNumber(rNumberMoney);
					
					
					if(newMoney < 0 ) {
						newMoney = 0;
					}
					r2D.setMoney(newMoney);
					r2D.setQuantity(thisNumber.getQuantity());
					recoverList.add(r2D);
				}
			}
		}
		request.setAttribute("limit", recoverLimit);
		request.setAttribute(CommonParameters.RECOVER_2D_LIST, recoverList);
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, totalRecover);
		request.setAttribute(CommonParameters.TAB_BAR_RECOVER_CHECK_COLOR, CommonConstants.HOVER_COLOR_CODE);
		dispatcher = request.getRequestDispatcher("/recoverTable");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected int getRNumberMoney(int number) {
		int rNumber;
		int rNumberMoney;
		rNumber = getReverse(number);
		if (number == rNumber) {// for pairs(a puu)
			rNumberMoney = 99;
		} else {
			rNumberMoney = tableDao.getMoney(rNumber) - recoverTableDao.getRecoverMoney(rNumber);
		}
		return rNumberMoney;
	}

	protected int getReverse(int number) {
		int reverse = 0;
		if (number < 10) {
			reverse = number * 10;
		} else {
			while (number != 0) {
				int remainder = number % 10;
				reverse = reverse * 10 + remainder;
				number = number / 10;
			}
		}

		return reverse;
	}

}
