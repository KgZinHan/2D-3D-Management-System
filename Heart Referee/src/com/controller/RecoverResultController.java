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

import com.dao.RecoverTableDao;
import com.dao.RecoverTableDaoImpl;
import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.AllUser2D;
import com.entity.Recover2D;

import common.CommonConstants;
import common.CommonParameters;

public class RecoverResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	TableDao tableDao;
	RecoverTableDao recoverTableDao;
	
	public RecoverResultController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);

		List<AllUser2D> recover2DList = new ArrayList<AllUser2D>();
		List<AllUser2D> totalRecover2DList = new ArrayList<AllUser2D>();
		List<Recover2D> sellerList = new ArrayList<Recover2D>();
		
		String sellerName = request.getParameter("sellerName");
		
		if (sellerName == null) {
			sellerName = recoverTableDao.getSellerName();
			recover2DList = tableDao.getAllRecoverTableBySeller(sellerName);
			totalRecover2DList = tableDao.getTotalAllRecoverTableBySeller(sellerName);
			//request.setAttribute(CommonParameters.TOTAL_RECOVER_DISPLAY, "none");
		} else {
			if (sellerName == "12345" || sellerName.equals("12345")) {
				sellerName = "Total";
				recover2DList = tableDao.getAllRecoverTable();
				totalRecover2DList = tableDao.getTotalAllRecoverTable();
				//totalRecover2DList = tableDao.getTotalTotalAllRecoverTable();
				//request.setAttribute(CommonParameters.COM_PERCENT_DISPLAY, "none");
			} else {
				recover2DList = tableDao.getAllRecoverTableBySeller(sellerName);
				totalRecover2DList = tableDao.getTotalAllRecoverTableBySeller(sellerName);
				//request.setAttribute(CommonParameters.TOTAL_RECOVER_DISPLAY, "none");
			}
		}
		
		sellerList = recoverTableDao.getRecoverSellerList();
		
		request.setAttribute(CommonParameters.TOTAL_RECOVER_DISPLAY, "none");
		request.setAttribute(CommonParameters.TAB_BAR_LEDGER_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.SELLER_NAME, sellerName);
		request.setAttribute(CommonParameters.RECOVER_SELLER_LIST, sellerList);
		request.setAttribute(CommonParameters.USER_2D_LIST, recover2DList);
		request.setAttribute(CommonParameters.TOTAL_USER_2D_LIST, totalRecover2DList);
		dispatcher = request.getRequestDispatcher("/recoverResultPage");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
