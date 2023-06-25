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
import com.entity.AllUser2D;
import com.entity.Number2D;
import com.entity.User2D;

import common.CommonConstants;
import common.CommonParameters;

public class RecoverResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<Integer> rNumberList = new ArrayList<Integer>();
	List<User2D> userList = new ArrayList<User2D>();
	int realID;
	String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;
	int total;
	int userTotal;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public RecoverResultController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AllUser2D> user2DList = new ArrayList<AllUser2D>();
		List<AllUser2D> totalUser2DList = new ArrayList<AllUser2D>();
		
		user2DList = tableDao.getAllRecoverTable();
		totalUser2DList = tableDao.getTotalAllRecoverTable();
		request.setAttribute(CommonParameters.TOTAL_RECOVER_DISPLAY, "none");
		
		/* List<Recover2D> userList = recoverTableDao.getRecoverSellerList(); */
		List<AllUser2D> userList = tableDao.getUserAllTable();
		
		request.setAttribute(CommonParameters.TAB_BAR_LEDGER_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.USER_LIST, userList);
		request.setAttribute(CommonParameters.USER_2D_LIST, user2DList);
		request.setAttribute(CommonParameters.TOTAL_USER_2D_LIST, totalUser2DList);
		dispatcher = request.getRequestDispatcher("/recoverResultPage");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}

}
