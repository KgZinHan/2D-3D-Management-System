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
import com.entity.Recover2D;
import common.CommonParameters;

@WebServlet("/SummaryTableController")
public class SummaryTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Summary2D> resultList = new ArrayList<Summary2D>();
	List<Recover2D> recoverPList = new ArrayList<Recover2D>();
	int total;
	int recoverTotal;
	TableDao tableDao;
	RecoverTableDao recoverTableDao;

	public SummaryTableController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);
		
		int money = 0;
		
		String numberS = request.getParameter("number");
		int number = Integer.parseInt(numberS);
		
		money = tableDao.getMoney(number);
		resultList = tableDao.getResultTableByNumber(number);
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
		recoverPList = recoverTableDao.getTotalRecoverPlusMoney(number);
		
		for(Recover2D recover : recoverPList) {
			recover.setTotalRecover(recoverTableDao.getTotalRecoverMoneyBySeller(recover.getSellerName()));
		}
		
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TWO_D_NUMBER, numberS);
		request.setAttribute(CommonParameters.TWO_D_MONEY, money);
		request.setAttribute(CommonParameters.TWO_D_LIST, resultList);
		request.setAttribute(CommonParameters.RECOVER_LIST, recoverPList);
		dispatcher = request.getRequestDispatcher("/summary");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
