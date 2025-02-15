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

import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.AllUser2D;
import common.CommonConstants;
import common.CommonParameters;

@WebServlet("/HistoryResult")
public class HistoryResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	TableDao tableDao;

	public HistoryResultController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);

		List<AllUser2D> user2DList = new ArrayList<AllUser2D>();
		List<AllUser2D> totalUser2DList = new ArrayList<AllUser2D>();
		
		String username = request.getParameter("username");
		
		if (username == null) {
			HttpSession session = request.getSession();
			username = (String) session.getAttribute(CommonParameters.SESSION_USER);
			user2DList = tableDao.getAllTableByUser(username);
			totalUser2DList = tableDao.getTotalAllTableByUser(username);
			request.setAttribute(CommonParameters.TOTAL_RECOVER_DISPLAY, "none");
		} else {
			if (username == "12345" || username.equals("12345")) {
				username = "All Commissions";
				user2DList = tableDao.getTotalAllTable();
				totalUser2DList = tableDao.getTotalTotalAllTable();
				request.setAttribute(CommonParameters.COM_PERCENT_DISPLAY, "none");
			} else {
				user2DList = tableDao.getAllTableByUser(username);
				totalUser2DList = tableDao.getTotalAllTableByUser(username);
				request.setAttribute(CommonParameters.TOTAL_RECOVER_DISPLAY, "none");
			}
		}

		List<AllUser2D> userList = tableDao.getUserAllTable();

		request.setAttribute(CommonParameters.TAB_BAR_LEDGER_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.SESSION_USER, username);
		request.setAttribute(CommonParameters.USER_LIST, userList);
		request.setAttribute(CommonParameters.USER_2D_LIST, user2DList);
		request.setAttribute(CommonParameters.TOTAL_USER_2D_LIST, totalUser2DList);
		dispatcher = request.getRequestDispatcher("/historyResult");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);
		
		String message = "";
		
		String totalMoneyS = request.getParameter("totalMoney");
		String numberS = request.getParameter("number");
		String pS = request.getParameter("p");
		String pMoneyS = request.getParameter("pMoney");
		String comPercentS = request.getParameter("comPercent");
		String comZS = request.getParameter("comZ");
		String comMoneyS = request.getParameter("comMoney");
		String totalS = request.getParameter("total");
		String username = request.getParameter("username");

		int totalMoney = Integer.parseInt(totalMoneyS);
		int number = Integer.parseInt(numberS);
		int p = Integer.parseInt(pS);
		int pMoney = Integer.parseInt(pMoneyS);
		int comPercent = Integer.parseInt(comPercentS);
		int comZ = Integer.parseInt(comZS);
		int comMoney = Integer.parseInt(comMoneyS);
		int total = Integer.parseInt(totalS);

		AllUser2D user2D = new AllUser2D();
		user2D.setUsername(username);
		user2D.setNumber(number);
		user2D.setTotalMoney(totalMoney);
		user2D.setP(p);
		user2D.setpMoney(pMoney);
		user2D.setComPercent(comPercent);
		user2D.setComMoney(comMoney);
		user2D.setTotal(total);

		if (tableDao.checkNameInTempTable(username) == true) {
			tableDao.updateUserTempTable(user2D);
			message = "Successfully Updated!";
		} else {
			tableDao.addUserTempTable(user2D);
			message = "Successfully Saved!";
		}

		request.setAttribute(CommonParameters.MESSAGE, message);
		request.setAttribute(CommonParameters.FINAL_RESULT_NUMBER, number);
		request.setAttribute(CommonParameters.FINAL_RESULT_COMMISSION_PERCENT, comPercent);
		request.setAttribute(CommonParameters.FINAL_RESULT_COMMISSION_Z, comZ);
		dispatcher = request.getRequestDispatcher("/Final");
		dispatcher.forward(request, response);
	}

}
