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
import com.entity.Summary2D;

import common.CommonParameters;

@WebServlet("/SummaryTableController")
public class SummaryTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	Summary2D sum2D = null;
	int total;
	int recoverTotal;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();
	List<Summary2D> resultList = new ArrayList<Summary2D>();

	public SummaryTableController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numberS = request.getParameter("number");
		int number = Integer.parseInt(numberS);
		int money = 0;
		
		money = tableDao.getMoney(number);
		resultList = tableDao.getResultTableByNumber(number);
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
		
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TWO_D_NUMBER, numberS);
		request.setAttribute(CommonParameters.TWO_D_MONEY, money);
		request.setAttribute(CommonParameters.TWO_D_LIST, resultList);
		dispatcher = request.getRequestDispatcher("/summary");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
