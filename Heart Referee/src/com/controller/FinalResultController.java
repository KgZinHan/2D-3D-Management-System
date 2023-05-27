package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TableDao;
import com.dao.TableDaoImpl;

import common.CommonConstants;
import common.CommonParameters;

@WebServlet("/FinalResultController")
public class FinalResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	TableDao tableDao = new TableDaoImpl();

	public FinalResultController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		
		request.setAttribute(CommonParameters.TAB_BAR_FINAL_RESULT_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.SESSION_USER, userName); 
		request.setAttribute(CommonParameters.FINAL_RESULT_DIV_DISPLAY, "none"); 
		dispatcher = request.getRequestDispatcher("/finalResult"); 
		dispatcher.forward(request,response);
		 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		String numberS = request.getParameter("number");
		String percentS = request.getParameter("comPercent");
		int number = Integer.parseInt(numberS);
		int percent = Integer.parseInt(percentS);
		String totalColor = "green";
		
		int totalMoney = tableDao.getUserTotalMoney(userName);
		int p = tableDao.getUserMoneyByNumber(userName,number);
		int userPMoney = p * 80 ;
		int commission = (totalMoney * percent)/100;
		int total = totalMoney - userPMoney - commission;
		if(total < 0) {
			totalColor = "red";
		}
		
		request.setAttribute(CommonParameters.TAB_BAR_FINAL_RESULT_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.SESSION_USER, userName); 
		request.setAttribute(CommonParameters.FINAL_RESULT_DIV_DISPLAY, "block"); 
		request.setAttribute(CommonParameters.FINAL_RESULT_NUMBER, number);
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_MONEY, totalMoney); 
		request.setAttribute(CommonParameters.FINAL_RESULT_P, p); 
		request.setAttribute(CommonParameters.FINAL_RESULT_P_MONEY, userPMoney); 
		request.setAttribute(CommonParameters.FINAL_RESULT_COMMISSION_PERCENT, percent);
		request.setAttribute(CommonParameters.FINAL_RESULT_COMMISSION, commission); 
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL, total); 
		request.setAttribute(CommonParameters.FINAL_RESULT_TOTAL_COLOR, totalColor); 
		dispatcher = request.getRequestDispatcher("/finalResult"); 
		dispatcher.forward(request,response);
	}

}