package com.recoverpagecontroller;

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
import com.entity.Number2D;
import com.entity.Recover2D;

import common.CommonConstants;
import common.CommonParameters;

@WebServlet("/SearchRecoverController")
public class SearchRecoverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<Recover2D> recoverSellerList = new ArrayList<Recover2D>();
	int total;
	int recoverTotal;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public SearchRecoverController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sellerName = request.getParameter("sellerName");
		String search = request.getParameter("number");
		int number = Integer.parseInt(search);
		
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
		int totalSellerRecover = recoverTableDao.getTotalRecoverMoneyBySeller(sellerName);
		
		recoverSellerList = recoverTableDao.getRecoverSellerList();
		twoDList = recoverTableDao.search2DRecoverAmount(number,sellerName);
		
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TOTAL_SELLER_RECOVER,totalSellerRecover);
		request.setAttribute(CommonParameters.SELLER_NAME, sellerName);
		request.setAttribute(CommonParameters.RECOVER_LIST, recoverSellerList);
		request.setAttribute(CommonParameters.TAB_BAR_RECOVER_NOTE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/recoverPage");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
