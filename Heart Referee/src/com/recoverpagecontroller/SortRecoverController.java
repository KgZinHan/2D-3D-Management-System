package com.recoverpagecontroller;

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
import com.entity.Recover2D;

import common.CommonConstants;
import common.CommonParameters;

public class SortRecoverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Recover2D> recoverSellerList = new ArrayList<Recover2D>();
	int total;
	int recoverTotal;
	TableDao tableDao;
	RecoverTableDao recoverTableDao;

	public SortRecoverController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);
		
		List<Number2D> top2D = new ArrayList<Number2D>();
		String numberHColor = "";
		String moneyHColor = "";
		
		String sellerName = request.getParameter("sellerName");
		String entity = request.getParameter("m");
		
		if (entity == "number" || entity.equals("number")) {
			top2D = recoverTableDao.sortRecoverByNumber(sellerName);
			numberHColor = CommonConstants.SORT_COLOR_CODE;
		}
		
		if (entity == "money" || entity.equals("money")) {
			top2D = recoverTableDao.sortRecoverByMoney(sellerName);
			moneyHColor = CommonConstants.SORT_COLOR_CODE;
		}
		
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
		int totalSellerRecover = recoverTableDao.getTotalRecoverMoneyBySeller(sellerName);
		recoverSellerList = recoverTableDao.getRecoverSellerList();
		
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TOTAL_SELLER_RECOVER,totalSellerRecover);
		request.setAttribute(CommonParameters.SELLER_NAME, sellerName);
		request.setAttribute(CommonParameters.RECOVER_LIST, recoverSellerList);
		request.setAttribute(CommonParameters.TAB_BAR_RECOVER_NOTE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.NUMBER_SORT_COLOR, numberHColor);
		request.setAttribute(CommonParameters.MONEY_SORT_COLOR, moneyHColor);
		request.setAttribute(CommonParameters.TWO_D_LIST, top2D);
		dispatcher = request.getRequestDispatcher("/recoverPage");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
