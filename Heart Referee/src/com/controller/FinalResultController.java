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

import com.dao.CommTableDao;
import com.dao.CommTableDaoImpl;
import com.dao.RecoverTableDao;
import com.dao.RecoverTableDaoImpl;
import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.AllUser2D;
import com.entity.Comm2D;
import com.entity.Recover2D;
import com.entity.User2D;

import common.CommonConstants;
import common.CommonParameters;

@WebServlet("/FinalResultController")
public class FinalResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	TableDao tableDao;
	CommTableDao commTableDao;
	RecoverTableDao recoverTableDao;

	public FinalResultController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);
		commTableDao = new CommTableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);

		List<AllUser2D> temp2DList = new ArrayList<>();
		List<User2D> user2DList = new ArrayList<>();
		List<AllUser2D> totalTemp2DList = new ArrayList<>();
		List<Recover2D> recoverList = new ArrayList<Recover2D>();
		List<Recover2D> totalRecoverList = new ArrayList<Recover2D>();
		
		Integer number = 0;
		int totalRecover = 0;
		int totalRecoverP = 0;
		int totalRecoverCom = 0;
		int totalRecoverPlus = 0;
		int totalFinalRecover = 0;
		/* int commTotal = 0; */
		int allTotal = 0;
		int count = 1;
		String allTotalColor = "green";
		String finalRecoverColor = "green";
		

		String username = request.getParameter("username");
		String numberS = request.getParameter("number");

		if (username == null) {
			HttpSession session = request.getSession();
			username = (String) session.getAttribute(CommonParameters.SESSION_USER);
		}

		user2DList = tableDao.getUsers();
		temp2DList = tableDao.getTempTable();
		totalTemp2DList = tableDao.getTotalTempTable();

		for (int i = 0; i < user2DList.size(); i++) {
			String checked = "red";
			if (tableDao.checkNameInTempTable(user2DList.get(i).getUser()) == true) {
				checked = "green";
			}
			user2DList.get(i).setChecked(checked);
		}
		if (!temp2DList.isEmpty()) {
			number = temp2DList.get(0).getNumber();
			numberS = number.toString();
			if (number < 10) {
				numberS = "0" + number.toString();
			}
		}

		Comm2D commission = commTableDao.getCommissionByCommName(username);

		totalRecover = recoverTableDao.getTotalRecoverMoney();
		recoverList = recoverTableDao.getTotalRecoverList();
		totalRecoverList = recoverTableDao.getAllTotalRecoverList();

		/*
		 * for (Recover2D recover : recoverList) { Recover2D seller =
		 * recoverTableDao.getRecoverSellerBySellerName(recover.getSellerName()); int
		 * recCom = (seller.getSellerCom() * recover.getSellerMoney()) / 100; int recP =
		 * recoverTableDao.getTotalRecoverPBySeller(recover.getSellerName(), number);
		 * totalRecoverCom += Math.round(recCom / 50f) * 50; totalRecoverPlus += recP *
		 * seller.getSellerZ(); }
		 */

		for (Recover2D recover : recoverList) {

			recover.setCount(count);
			
			Recover2D seller = recoverTableDao.getRecoverSellerBySellerName(recover.getSellerName());

			int recoverCom = (seller.getSellerCom() * recover.getSellerMoney()) / 100;
			recoverCom = Math.round(recoverCom / 50f) * 50;
			totalRecoverCom += recoverCom;
			recover.setRecoverCom(recoverCom);

			int recP = recoverTableDao.getTotalRecoverPBySeller(recover.getSellerName(), number);
			totalRecoverP += recP;
			recover.setRecoverP(recP);

			int recoverPlus = (recP * seller.getSellerZ()) + recoverCom;
			totalRecoverPlus += recoverPlus;
			recover.setRecoverPlus(recoverPlus);

			int finalRecover = recoverPlus - recover.getSellerMoney();
			totalFinalRecover += finalRecover;
			recover.setTotalRecover(finalRecover);

			if (finalRecover < 0) {
				recover.setColor("red");
			} else {
				recover.setColor("green");
			}
			
			count++;
		}
		
		for(Recover2D totalRecover2D : totalRecoverList) {
			totalRecover2D.setRecoverCom(totalRecoverCom);
			totalRecover2D.setRecoverP(totalRecoverP);
			totalRecover2D.setRecoverPlus(totalRecoverPlus);
			totalRecover2D.setTotalRecover(totalFinalRecover);
			if(totalFinalRecover < 0) {
				totalRecover2D.setColor("red");
			}else {
				totalRecover2D.setColor("green");
			}
		}
		
		/*
		 * commTotal = tableDao.getTempTotalResult(); if(commTotal < 0) {
		 * recoverTotalColor = "red"; }
		 */
		
		allTotal = tableDao.getTempTotalResult() - totalRecover + totalRecoverPlus;
		if (allTotal < 0) {
			allTotalColor = "red";
		}
		
		if(totalFinalRecover < 0) {
			finalRecoverColor = "red";
		}

		request.setAttribute(CommonParameters.USER_LIST, user2DList);
		request.setAttribute(CommonParameters.TEMP_2D_LIST, temp2DList);
		request.setAttribute(CommonParameters.TOTAL_TEMP_2D_LIST, totalTemp2DList);
		request.setAttribute(CommonParameters.TEMP_RECOVER_2D_LIST, recoverList);
		request.setAttribute(CommonParameters.TOTAL_TEMP_RECOVER_2D_LIST, totalRecoverList);
		request.setAttribute(CommonParameters.FINAL_RESULT_ALL_TOTAL, allTotal);
		request.setAttribute(CommonParameters.FINAL_RESULT_ALL_TOTAL_COLOR, allTotalColor);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_RECOVER, totalFinalRecover);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_RECOVER_COLOR, finalRecoverColor);
		/*
		 * request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_COMMSSION,
		 * commTotal);
		 */
		request.setAttribute(CommonParameters.FINAL_RESULT_NUMBER, numberS);
		request.setAttribute(CommonParameters.FINAL_RESULT_COMMISSION_PERCENT, commission.getCommPercent());
		request.setAttribute(CommonParameters.TAB_BAR_FINAL_RESULT_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.SESSION_USER, username);
		request.setAttribute(CommonParameters.FINAL_RESULT_DIV_DISPLAY, "none");
		dispatcher = request.getRequestDispatcher("/finalResult");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);

		List<AllUser2D> temp2DList = new ArrayList<>();
		List<User2D> user2DList = new ArrayList<>();
		List<AllUser2D> totalTemp2DList = new ArrayList<>();
		List<Recover2D> recoverList = new ArrayList<Recover2D>();
		List<Recover2D> totalRecoverList = new ArrayList<Recover2D>();
		
		int totalRecover = 0;
		int totalRecoverP = 0;
		int totalRecoverCom = 0;
		int totalRecoverPlus = 0;
		int totalFinalRecover = 0;
		int commTotal = 0;
		int allTotal = 0;
		String totalColor = "green";
		String allTotalColor = "green";
		String finalRecoverColor = "green";

		String username = request.getParameter("username");
		String numberS = request.getParameter("number");
		String percentS = request.getParameter("comPercent");
		int number = Integer.parseInt(numberS);
		int percent = Integer.parseInt(percentS);

		int totalMoney = tableDao.getUserTotalMoney(username);
		int p = tableDao.getUserMoneyByNumber(username, number);
		int userPMoney = p * 80;
		int tCommission = (totalMoney * percent) / 100;
		int commission = Math.round(tCommission / 50f) * 50;
		int total = totalMoney - userPMoney - commission;
		if (total < 0) {
			totalColor = "red";
		}

		user2DList = tableDao.getUsers();
		temp2DList = tableDao.getTempTable();
		totalTemp2DList = tableDao.getTotalTempTable();

		for (int i = 0; i < user2DList.size(); i++) {
			String checked = "red";
			if (tableDao.checkNameInTempTable(user2DList.get(i).getUser()) == true) {
				checked = "green";
			}
			user2DList.get(i).setChecked(checked);
		}

		totalRecover = recoverTableDao.getTotalRecoverMoney();
		recoverList = recoverTableDao.getTotalRecoverList();
		totalRecoverList = recoverTableDao.getAllTotalRecoverList();

		for (Recover2D recover : recoverList) {

			Recover2D seller = recoverTableDao.getRecoverSellerBySellerName(recover.getSellerName());

			int recoverCom = (seller.getSellerCom() * recover.getSellerMoney()) / 100;
			recoverCom = Math.round(recoverCom / 50f) * 50;
			totalRecoverCom += recoverCom;
			recover.setRecoverCom(recoverCom);

			int recP = recoverTableDao.getTotalRecoverPBySeller(recover.getSellerName(), number);
			totalRecoverP += recP;
			recover.setRecoverP(recP);

			int recoverPlus = (recP * seller.getSellerZ()) + recoverCom;
			totalRecoverPlus += recoverPlus;
			recover.setRecoverPlus(recoverPlus);

			int finalRecover = recoverPlus - recover.getSellerMoney();
			totalFinalRecover += finalRecover;
			recover.setTotalRecover(finalRecover);

			if (finalRecover < 0) {
				recover.setColor("red");
			} else {
				recover.setColor("green");
			}
		}
		
		for(Recover2D totalRecover2D : totalRecoverList) {
			totalRecover2D.setRecoverCom(totalRecoverCom);
			totalRecover2D.setRecoverP(totalRecoverP);
			totalRecover2D.setRecoverPlus(totalRecoverPlus);
			totalRecover2D.setTotalRecover(totalFinalRecover);
			if(totalFinalRecover < 0) {
				totalRecover2D.setColor("red");
			}else {
				totalRecover2D.setColor("green");
			}
		}
		
		commTotal = tableDao.getTempTotalResult();
		
		allTotal = tableDao.getTempTotalResult() - totalRecover + totalRecoverPlus;
		if (allTotal < 0) {
			allTotalColor = "red";
		}
		
		if(totalFinalRecover < 0) {
			finalRecoverColor = "red";
		}

		request.setAttribute(CommonParameters.USER_LIST, user2DList);
		request.setAttribute(CommonParameters.TEMP_2D_LIST, temp2DList);
		request.setAttribute(CommonParameters.TOTAL_TEMP_2D_LIST, totalTemp2DList);
		request.setAttribute(CommonParameters.TEMP_RECOVER_2D_LIST, recoverList);
		request.setAttribute(CommonParameters.TOTAL_TEMP_RECOVER_2D_LIST, totalRecoverList);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_RECOVER, totalFinalRecover);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_COMMSSION, commTotal);
		request.setAttribute(CommonParameters.FINAL_RESULT_ALL_TOTAL, allTotal);
		request.setAttribute(CommonParameters.FINAL_RESULT_ALL_TOTAL_COLOR, allTotalColor);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_RECOVER, totalFinalRecover);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_RECOVER_COLOR, finalRecoverColor);
		request.setAttribute(CommonParameters.TAB_BAR_FINAL_RESULT_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.SESSION_USER, username);
		request.setAttribute(CommonParameters.FINAL_RESULT_DIV_DISPLAY, "block");
		request.setAttribute(CommonParameters.FINAL_RESULT_NUMBER, numberS);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_MONEY, totalMoney);
		request.setAttribute(CommonParameters.FINAL_RESULT_P, p);
		request.setAttribute(CommonParameters.FINAL_RESULT_P_MONEY, userPMoney);
		request.setAttribute(CommonParameters.FINAL_RESULT_COMMISSION_PERCENT, percent);
		request.setAttribute(CommonParameters.FINAL_RESULT_COMMISSION, commission);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL, total);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_COLOR, totalColor);
		dispatcher = request.getRequestDispatcher("/finalResult");
		dispatcher.forward(request, response);
	}

}