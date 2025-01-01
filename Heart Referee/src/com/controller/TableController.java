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

import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.Closed2D;
import com.entity.History2D;
import com.entity.Number2D;
import com.entity.User2D;

import common.CommonConstants;
import common.CommonParameters;

public class TableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<User2D> userList = new ArrayList<User2D>();
	History2D twoDH = null;
	int total;
	int userTotal;
	int pageTotal;
	int realID;
	String shortMsg;
	TableDao tableDao;

	public TableController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);

		String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;

		HttpSession session = request.getSession();
		String table = (String) session.getAttribute(CommonParameters.SESSION_NAME);
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);

		if (table == null || table.isEmpty() || table.equals("details")) {
			if (userName == null || userName.isEmpty() || userName.equals(null)) {
				twoDList = tableDao.getTable();
			} else {
				twoDList = tableDao.getTableByUser(userName);
			}

			total = tableDao.getTotalMoney();
			userTotal = tableDao.getUserTotalMoney(userName);
			userList = tableDao.getUsers();

			for (int i = 0; i < userList.size(); i++) {
				String checked = "red";
				if (tableDao.checkNameInTempTable(userList.get(i).getUser()) == true) {
					checked = "green";
				}
				userList.get(i).setChecked(checked);
			}

			realID = tableDao.getIdCount();
			if (realID > CommonConstants.ID_COUNT_LIMIT) {
				idAlertColor = CommonConstants.ID_ALERT_COLOR;
			}

			request.setAttribute(CommonParameters.TOTAL_MONEY, total);
			request.setAttribute(CommonParameters.USER_TOTAL_MONEY, userTotal);
			request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR, CommonConstants.HOVER_COLOR_CODE);
			request.setAttribute(CommonParameters.REAL_ID, realID);
			request.setAttribute(CommonParameters.ID_ALERT_COLOR, idAlertColor);
			request.setAttribute(CommonParameters.AVG_COMM_PERCENT, CommonConstants.AVERAGE_COMM_PERCENT);
			request.setAttribute(CommonParameters.DEFAULT_MACHINE_NAME, CommonConstants.DEFAULT_MACHINE_NAME);
			request.setAttribute(CommonParameters.RED_COLOR_LIMIT, CommonConstants.FINAL_LIMIT);
			request.setAttribute(CommonParameters.GREEN_COLOR_LIMIT, CommonConstants.HAPPY_LIMIT);
			request.setAttribute(CommonParameters.PAGE_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.DELETE_COLUMN_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
			request.setAttribute(CommonParameters.USER_LIST, userList);
			request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
			dispatcher = request.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
		} else if (table.equals("history")) {
			dispatcher = request.getRequestDispatcher("History");
			dispatcher.forward(request, response);
		} else if (table.equals("normal")) {
			dispatcher = request.getRequestDispatcher("SortByUser?m=money");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		tableDao = new TableDaoImpl(request);
		
		int pageNo = CommonConstants.DEFAULT_PAGE_NO;
		int type = 1;
		String note = "error";
		String alertMsg = "";
		Boolean flag = true;
		Boolean pairFlag = false;
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute(CommonParameters.SESSION_USER);
		
		String moneyS = request.getParameter("money");
		String numberS = request.getParameter("number");
		int money = Integer.parseInt(moneyS);
		
		if (!(numberS.isEmpty())) {
			switch (numberS) {
			case "**":
				int[] arrayP = { 00, 11, 22, 33, 44, 55, 66, 77, 88, 99 };
				tableDao.add2DwithSpecialA(arrayP, money, name, pageNo);
				for (int i = 0; i < arrayP.length; i++) {
					if (checkClosedNumberOrNot(arrayP[i],request)) {
						Integer number = arrayP[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "apuu ";
				note = "apuu ";
				break;
			case "//":
				int[] arraynk = { 07, 70, 18, 81, 24, 42, 35, 53, 69, 96 };
				tableDao.add2DwithSpecialA(arraynk, money, name, pageNo);
				for (int i = 0; i < arraynk.length; i++) {
					if (checkClosedNumberOrNot(arraynk[i],request)) {
						Integer number = arraynk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "natkhat ";
				note = "natkhat";
				break;
			case "++":
				int[] arrayPP = { 05, 50, 16, 61, 27, 72, 38, 83, 94, 49 };
				tableDao.add2DwithSpecialA(arrayPP, money, name, pageNo);
				for (int i = 0; i < arrayPP.length; i++) {
					if (checkClosedNumberOrNot(arrayPP[i],request)) {
						Integer number = arrayPP[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "power ";
				note = "power";
				break;
			case "0+":
				int[] array0bk = { 0, 19, 28, 37, 46, 55, 64, 73, 82, 91 };
				tableDao.add2DwithSpecialA(array0bk, money, name, pageNo);
				for (int i = 0; i < array0bk.length; i++) {
					if (checkClosedNumberOrNot(array0bk[i],request)) {
						Integer number = array0bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "0 bk ";
				note = shortMsg;
				break;
			case "1+":
				int[] array1bk = { 1, 10, 29, 38, 47, 56, 65, 74, 83, 92 };
				tableDao.add2DwithSpecialA(array1bk, money, name, pageNo);
				for (int i = 0; i < array1bk.length; i++) {
					if (checkClosedNumberOrNot(array1bk[i],request)) {
						Integer number = array1bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "1 bk ";
				note = shortMsg;
				break;
			case "2+":
				int[] array2bk = { 2, 11, 20, 39, 48, 57, 66, 75, 84, 93 };
				tableDao.add2DwithSpecialA(array2bk, money, name, pageNo);
				for (int i = 0; i < array2bk.length; i++) {
					if (checkClosedNumberOrNot(array2bk[i],request)) {
						Integer number = array2bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "2 bk ";
				note = shortMsg;
				break;
			case "3+":
				int[] array3bk = { 3, 12, 21, 30, 49, 58, 67, 76, 85, 94 };
				tableDao.add2DwithSpecialA(array3bk, money, name, pageNo);
				for (int i = 0; i < array3bk.length; i++) {
					if (checkClosedNumberOrNot(array3bk[i],request)) {
						Integer number = array3bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "3 bk ";
				note = shortMsg;
				break;
			case "4+":
				int[] array4bk = { 4, 13, 22, 31, 40, 59, 68, 77, 86, 95 };
				tableDao.add2DwithSpecialA(array4bk, money, name, pageNo);
				for (int i = 0; i < array4bk.length; i++) {
					if (checkClosedNumberOrNot(array4bk[i],request)) {
						Integer number = array4bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "4 bk ";
				note = shortMsg;
				break;
			case "5+":
				int[] array5bk = { 5, 14, 23, 32, 41, 50, 69, 78, 87, 96 };
				tableDao.add2DwithSpecialA(array5bk, money, name, pageNo);
				for (int i = 0; i < array5bk.length; i++) {
					if (checkClosedNumberOrNot(array5bk[i],request)) {
						Integer number = array5bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "5 bk ";
				note = shortMsg;
				break;
			case "6+":
				int[] array6bk = { 6, 15, 24, 33, 42, 51, 60, 79, 88, 97 };
				tableDao.add2DwithSpecialA(array6bk, money, name, pageNo);
				for (int i = 0; i < array6bk.length; i++) {
					if (checkClosedNumberOrNot(array6bk[i],request)) {
						Integer number = array6bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "6 bk ";
				note = shortMsg;
				break;
			case "7+":
				int[] array7bk = { 7, 16, 25, 34, 43, 52, 61, 70, 89, 98 };
				tableDao.add2DwithSpecialA(array7bk, money, name, pageNo);
				for (int i = 0; i < array7bk.length; i++) {
					if (checkClosedNumberOrNot(array7bk[i],request)) {
						Integer number = array7bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "7 bk ";
				note = shortMsg;
				break;
			case "8+":
				int[] array8bk = { 8, 17, 26, 35, 44, 53, 62, 71, 80, 99 };
				tableDao.add2DwithSpecialA(array8bk, money, name, pageNo);
				for (int i = 0; i < array8bk.length; i++) {
					if (checkClosedNumberOrNot(array8bk[i],request)) {
						Integer number = array8bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "8 bk ";
				note = shortMsg;
				break;
			case "9+":
				int[] array9bk = { 9, 18, 27, 36, 45, 54, 63, 72, 81, 90 };
				tableDao.add2DwithSpecialA(array9bk, money, name, pageNo);
				for (int i = 0; i < array9bk.length; i++) {
					if (checkClosedNumberOrNot(array9bk[i],request)) {
						Integer number = array9bk[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "9 bk ";
				note = shortMsg;
				break;
			case "0*":
				int[] array0s = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
				tableDao.add2DwithSpecialA(array0s, money, name, pageNo);
				for (int i = 0; i < array0s.length; i++) {
					if (checkClosedNumberOrNot(array0s[i],request)) {
						Integer number = array0s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "0 * ";
				note = shortMsg;
				break;
			case "1*":
				int[] array1s = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
				tableDao.add2DwithSpecialA(array1s, money, name, pageNo);
				for (int i = 0; i < array1s.length; i++) {
					if (checkClosedNumberOrNot(array1s[i],request)) {
						Integer number = array1s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "1 * ";
				note = shortMsg;
				break;
			case "2*":
				int[] array2s = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 };
				tableDao.add2DwithSpecialA(array2s, money, name, pageNo);
				for (int i = 0; i < array2s.length; i++) {
					if (checkClosedNumberOrNot(array2s[i],request)) {
						Integer number = array2s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "2 * ";
				note = shortMsg;
				break;
			case "3*":
				int[] array3s = { 30, 31, 32, 33, 34, 35, 36, 37, 38, 39 };
				tableDao.add2DwithSpecialA(array3s, money, name, pageNo);
				for (int i = 0; i < array3s.length; i++) {
					if (checkClosedNumberOrNot(array3s[i],request)) {
						Integer number = array3s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "3 * ";
				note = shortMsg;
				break;
			case "4*":
				int[] array4s = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49 };
				tableDao.add2DwithSpecialA(array4s, money, name, pageNo);
				for (int i = 0; i < array4s.length; i++) {
					if (checkClosedNumberOrNot(array4s[i],request)) {
						Integer number = array4s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "4 * ";
				note = shortMsg;
				break;
			case "5*":
				int[] array5s = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 };
				tableDao.add2DwithSpecialA(array5s, money, name, pageNo);
				for (int i = 0; i < array5s.length; i++) {
					if (checkClosedNumberOrNot(array5s[i],request)) {
						Integer number = array5s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "5 * ";
				note = shortMsg;
				break;
			case "6*":
				int[] array6s = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 };
				tableDao.add2DwithSpecialA(array6s, money, name, pageNo);
				for (int i = 0; i < array6s.length; i++) {
					if (checkClosedNumberOrNot(array6s[i],request)) {
						Integer number = array6s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "6 * ";
				note = shortMsg;
				break;
			case "7*":
				int[] array7s = { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 };
				tableDao.add2DwithSpecialA(array7s, money, name, pageNo);
				for (int i = 0; i < array7s.length; i++) {
					if (checkClosedNumberOrNot(array7s[i],request)) {
						Integer number = array7s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "7 * ";
				note = shortMsg;
				break;
			case "8*":
				int[] array8s = { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 };
				tableDao.add2DwithSpecialA(array8s, money, name, pageNo);
				for (int i = 0; i < array8s.length; i++) {
					if (checkClosedNumberOrNot(array8s[i],request)) {
						Integer number = array8s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "8 * ";
				note = shortMsg;
				break;
			case "9*":
				int[] array9s = { 90, 91, 92, 93, 94, 95, 96, 97, 98, 99 };
				tableDao.add2DwithSpecialA(array9s, money, name, pageNo);
				for (int i = 0; i < array9s.length; i++) {
					if (checkClosedNumberOrNot(array9s[i],request)) {
						Integer number = array9s[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "9 * ";
				note = shortMsg;
				break;
			case "*0":
				int[] array0e = { 0, 10, 20, 30, 40, 50, 60, 70, 80, 90 };
				tableDao.add2DwithSpecialA(array0e, money, name, pageNo);
				for (int i = 0; i < array0e.length; i++) {
					if (checkClosedNumberOrNot(array0e[i],request)) {
						Integer number = array0e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 0 ";
				note = shortMsg;
				break;
			case "*1":
				int[] array1e = { 1, 11, 21, 31, 41, 51, 61, 71, 81, 91 };
				tableDao.add2DwithSpecialA(array1e, money, name, pageNo);
				for (int i = 0; i < array1e.length; i++) {
					if (checkClosedNumberOrNot(array1e[i],request)) {
						Integer number = array1e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 1 ";
				note = shortMsg;
				break;
			case "*2":
				int[] array2e = { 2, 12, 22, 32, 42, 52, 62, 72, 82, 92 };
				tableDao.add2DwithSpecialA(array2e, money, name, pageNo);
				for (int i = 0; i < array2e.length; i++) {
					if (checkClosedNumberOrNot(array2e[i],request)) {
						Integer number = array2e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 2 ";
				note = shortMsg;
				break;
			case "*3":
				int[] array3e = { 3, 13, 23, 33, 43, 53, 63, 73, 83, 93 };
				tableDao.add2DwithSpecialA(array3e, money, name, pageNo);
				for (int i = 0; i < array3e.length; i++) {
					if (checkClosedNumberOrNot(array3e[i],request)) {
						Integer number = array3e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 3 ";
				note = shortMsg;
				break;
			case "*4":
				int[] array4e = { 4, 14, 24, 34, 44, 54, 64, 74, 84, 94 };
				tableDao.add2DwithSpecialA(array4e, money, name, pageNo);
				for (int i = 0; i < array4e.length; i++) {
					if (checkClosedNumberOrNot(array4e[i],request)) {
						Integer number = array4e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 4";
				note = shortMsg;
				break;
			case "*5":
				int[] array5e = { 5, 15, 25, 35, 45, 55, 65, 75, 85, 95 };
				tableDao.add2DwithSpecialA(array5e, money, name, pageNo);
				for (int i = 0; i < array5e.length; i++) {
					if (checkClosedNumberOrNot(array5e[i],request)) {
						Integer number = array5e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 5 ";
				note = shortMsg;
				break;
			case "*6":
				int[] array6e = { 6, 16, 26, 36, 46, 56, 66, 76, 86, 96 };
				tableDao.add2DwithSpecialA(array6e, money, name, pageNo);
				for (int i = 0; i < array6e.length; i++) {
					if (checkClosedNumberOrNot(array6e[i],request)) {
						Integer number = array6e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 6 ";
				note = shortMsg;
				break;
			case "*7":
				int[] array7e = { 7, 17, 27, 37, 47, 57, 67, 77, 87, 97 };
				tableDao.add2DwithSpecialA(array7e, money, name, pageNo);
				for (int i = 0; i < array7e.length; i++) {
					if (checkClosedNumberOrNot(array7e[i],request)) {
						Integer number = array7e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 7 ";
				note = shortMsg;
				break;
			case "*8":
				int[] array8e = { 8, 18, 28, 38, 48, 58, 68, 78, 88, 98 };
				tableDao.add2DwithSpecialA(array8e, money, name, pageNo);
				for (int i = 0; i < array8e.length; i++) {
					if (checkClosedNumberOrNot(array8e[i],request)) {
						Integer number = array8e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 8 ";
				note = shortMsg;
				break;
			case "*9":
				int[] array9e = { 9, 19, 29, 39, 49, 59, 69, 79, 89, 99 };
				tableDao.add2DwithSpecialA(array9e, money, name, pageNo);
				for (int i = 0; i < array9e.length; i++) {
					if (checkClosedNumberOrNot(array9e[i],request)) {
						Integer number = array9e[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "* 9 ";
				note = shortMsg;
				break;
			case "/*":
				int[] arraybro = { 01, 10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98, 90, 9 };
				tableDao.add2DwithSpecialC(arraybro, money, name, pageNo);
				for (int i = 0; i < arraybro.length; i++) {
					if (checkClosedNumberOrNot(arraybro[i],request)) {
						Integer number = arraybro[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "nyiko ";
				note = shortMsg;
				type = 3;
				break;
			case "*0*":
				int[] array0p = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90 };
				tableDao.add2DwithSpecialB(array0p, money, name, pageNo);
				for (int i = 0; i < array0p.length; i++) {
					if (checkClosedNumberOrNot(array0p[i],request)) {
						Integer number = array0p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "0 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*1*":
				int[] array1p = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 1, 21, 31, 41, 51, 61, 71, 81, 91 };
				tableDao.add2DwithSpecialB(array1p, money, name, pageNo);
				for (int i = 0; i < array1p.length; i++) {
					if (checkClosedNumberOrNot(array1p[i],request)) {
						Integer number = array1p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "1 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*2*":
				int[] array2p = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 2, 12, 32, 42, 52, 62, 72, 82, 92 };
				tableDao.add2DwithSpecialB(array2p, money, name, pageNo);
				for (int i = 0; i < array2p.length; i++) {
					if (checkClosedNumberOrNot(array2p[i],request)) {
						Integer number = array2p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "2 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*3*":
				int[] array3p = { 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 3, 13, 23, 43, 53, 63, 73, 83, 93 };
				tableDao.add2DwithSpecialB(array3p, money, name, pageNo);
				for (int i = 0; i < array3p.length; i++) {
					if (checkClosedNumberOrNot(array3p[i],request)) {
						Integer number = array3p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "3 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*4*":
				int[] array4p = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 4, 14, 24, 34, 54, 64, 74, 84, 94 };
				tableDao.add2DwithSpecialB(array4p, money, name, pageNo);
				for (int i = 0; i < array4p.length; i++) {
					if (checkClosedNumberOrNot(array4p[i],request)) {
						Integer number = array4p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "4 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*5*":
				int[] array5p = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 5, 15, 25, 35, 45, 65, 75, 85, 95 };
				tableDao.add2DwithSpecialB(array5p, money, name, pageNo);
				for (int i = 0; i < array5p.length; i++) {
					if (checkClosedNumberOrNot(array5p[i],request)) {
						Integer number = array5p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "5 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*6*":
				int[] array6p = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 6, 16, 26, 36, 46, 56, 76, 86, 96 };
				tableDao.add2DwithSpecialB(array6p, money, name, pageNo);
				for (int i = 0; i < array6p.length; i++) {
					if (checkClosedNumberOrNot(array6p[i],request)) {
						Integer number = array6p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "6 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*7*":
				int[] array7p = { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 7, 17, 27, 37, 47, 57, 67, 87, 97 };
				tableDao.add2DwithSpecialB(array7p, money, name, pageNo);
				for (int i = 0; i < array7p.length; i++) {
					if (checkClosedNumberOrNot(array7p[i],request)) {
						Integer number = array7p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "7 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*8*":
				int[] array8p = { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 8, 18, 28, 38, 48, 58, 68, 78, 98 };
				tableDao.add2DwithSpecialB(array8p, money, name, pageNo);
				for (int i = 0; i < array8p.length; i++) {
					if (checkClosedNumberOrNot(array8p[i],request)) {
						Integer number = array8p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "8 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*9*":
				int[] array9p = { 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 9, 19, 29, 39, 49, 59, 69, 79, 89 };
				tableDao.add2DwithSpecialB(array9p, money, name, pageNo);
				for (int i = 0; i < array9p.length; i++) {
					if (checkClosedNumberOrNot(array9p[i],request)) {
						Integer number = array9p[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "9 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*0**":
				int[] array0pp = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 20, 30, 40, 50, 60, 70, 80, 90 };
				tableDao.add2DwithSpecialC(array0pp, money, name, pageNo);
				for (int i = 0; i < array0pp.length; i++) {
					if (checkClosedNumberOrNot(array0pp[i],request)) {
						Integer number = array0pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "0 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*1**":
				int[] array1pp = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 1, 11, 21, 31, 41, 51, 61, 71, 81, 91 };
				tableDao.add2DwithSpecialC(array1pp, money, name, pageNo);
				for (int i = 0; i < array1pp.length; i++) {
					if (checkClosedNumberOrNot(array1pp[i],request)) {
						Integer number = array1pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "1 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*2**":
				int[] array2pp = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 2, 12, 22, 32, 42, 52, 62, 72, 82, 92 };
				tableDao.add2DwithSpecialC(array2pp, money, name, pageNo);
				for (int i = 0; i < array2pp.length; i++) {
					if (checkClosedNumberOrNot(array2pp[i],request)) {
						Integer number = array2pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "2 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*3**":
				int[] array3pp = { 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 3, 13, 23, 33, 43, 53, 63, 73, 83, 93 };
				tableDao.add2DwithSpecialC(array3pp, money, name, pageNo);
				for (int i = 0; i < array3pp.length; i++) {
					if (checkClosedNumberOrNot(array3pp[i],request)) {
						Integer number = array3pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "3 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*4**":
				int[] array4pp = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 4, 14, 24, 34, 44, 54, 64, 74, 84, 94 };
				tableDao.add2DwithSpecialC(array4pp, money, name, pageNo);
				for (int i = 0; i < array4pp.length; i++) {
					if (checkClosedNumberOrNot(array4pp[i],request)) {
						Integer number = array4pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "4 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*5**":
				int[] array5pp = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 5, 15, 25, 35, 45, 55, 65, 75, 85, 95 };
				tableDao.add2DwithSpecialC(array5pp, money, name, pageNo);
				for (int i = 0; i < array5pp.length; i++) {
					if (checkClosedNumberOrNot(array5pp[i],request)) {
						Integer number = array5pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "5 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*6**":
				int[] array6pp = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 6, 16, 26, 36, 46, 56, 66, 76, 86, 96 };
				tableDao.add2DwithSpecialC(array6pp, money, name, pageNo);
				for (int i = 0; i < array6pp.length; i++) {
					if (checkClosedNumberOrNot(array6pp[i],request)) {
						Integer number = array6pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "6 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*7**":
				int[] array7pp = { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 7, 17, 27, 37, 47, 57, 67, 77, 87, 97 };
				tableDao.add2DwithSpecialC(array7pp, money, name, pageNo);
				for (int i = 0; i < array7pp.length; i++) {
					if (checkClosedNumberOrNot(array7pp[i],request)) {
						Integer number = array7pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "7 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*8**":
				int[] array8pp = { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 8, 18, 28, 38, 48, 58, 68, 78, 88, 98 };
				tableDao.add2DwithSpecialC(array8pp, money, name, pageNo);
				for (int i = 0; i < array8pp.length; i++) {
					if (checkClosedNumberOrNot(array8pp[i],request)) {
						Integer number = array8pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "8 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*9**":
				int[] array9pp = { 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 9, 19, 29, 39, 49, 59, 69, 79, 89, 99 };
				tableDao.add2DwithSpecialC(array9pp, money, name, pageNo);
				for (int i = 0; i < array9pp.length; i++) {
					if (checkClosedNumberOrNot(array9pp[i],request)) {
						Integer number = array9pp[i];
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + number.toString();
						} else {
							alertMsg = alertMsg + " " + number.toString();
						}
					}
				}
				shortMsg = "9 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "+/+":
				List<String> strNumberPlusPlusList = generateTwoDigitsCombinations("02468");
				for (int i = 0; i < strNumberPlusPlusList.size(); i++) {
					int number = Integer.parseInt(strNumberPlusPlusList.get(i));
					int rNumber = getReverse(number);
					tableDao.add2DwithR(number, rNumber, money, name, pageNo);

					if (checkClosedNumberOrNot(number,request)) {
						Integer iNumber = number;
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + iNumber.toString();
						} else {
							alertMsg = alertMsg + " " + iNumber.toString();
						}
					}
					if (checkClosedNumberOrNot(rNumber,request)) {
						Integer iNumber = rNumber;
						if (rNumber < 10) {
							alertMsg = alertMsg + " " + "0" + iNumber.toString();
						} else {
							alertMsg = alertMsg + " " + iNumber.toString();
						}
					}
				}
				strNumberPlusPlusList = generateAllPairs("02468");
				for (int j = 0; j < strNumberPlusPlusList.size(); j++) {
					int number = Integer.parseInt(strNumberPlusPlusList.get(j));
					tableDao.add2D(number, money, name, pageNo);

					if (checkClosedNumberOrNot(number,request)) {
						Integer iNumber = number;
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + iNumber.toString();
						} else {
							alertMsg = alertMsg + " " + iNumber.toString();
						}
					}
				}
				type = 6;
				shortMsg = "sone sone";
				note = shortMsg;
				break;
			case "-/-":
				List<String> strNumberMinusMinusList = generateTwoDigitsCombinations("13579");
				for (int i = 0; i < strNumberMinusMinusList.size(); i++) {
					int number = Integer.parseInt(strNumberMinusMinusList.get(i));
					int rNumber = getReverse(number);
					tableDao.add2DwithR(number, rNumber, money, name, pageNo);

					if (checkClosedNumberOrNot(number,request)) {
						Integer iNumber = number;
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + iNumber.toString();
						} else {
							alertMsg = alertMsg + " " + iNumber.toString();
						}
					}
					if (checkClosedNumberOrNot(rNumber,request)) {
						Integer iNumber = rNumber;
						if (rNumber < 10) {
							alertMsg = alertMsg + " " + "0" + iNumber.toString();
						} else {
							alertMsg = alertMsg + " " + iNumber.toString();
						}
					}
				}
				strNumberMinusMinusList = generateAllPairs("13579");
				for (int j = 0; j < strNumberMinusMinusList.size(); j++) {
					int number = Integer.parseInt(strNumberMinusMinusList.get(j));
					tableDao.add2D(number, money, name, pageNo);

					if (checkClosedNumberOrNot(number,request)) {
						Integer iNumber = number;
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + iNumber.toString();
						} else {
							alertMsg = alertMsg + " " + iNumber.toString();
						}
					}
				}
				type = 6;
				shortMsg = "ma ma";
				note = shortMsg;
				break;
			case "+/-":
				int[] arrayPlusMinusList = { 1, 3, 5, 7, 9, 21, 23, 25, 27, 29, 41, 43, 45, 47, 49, 61, 63, 65, 67, 69,
						81, 83, 85, 87, 89 };
				for (int i = 0; i < arrayPlusMinusList.length; i++) {
					int number = arrayPlusMinusList[i];
					tableDao.add2D(number, money, name, pageNo);

					if (checkClosedNumberOrNot(number,request)) {
						Integer iNumber = number;
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + iNumber.toString();
						} else {
							alertMsg = alertMsg + " " + iNumber.toString();
						}
					}
				}
				type = 6;
				shortMsg = "sone ma";
				note = shortMsg;
				break;
			case "-/+":
				int[] arrayMinusPlusList = { 10, 12, 14, 16, 18, 30, 32, 34, 36, 38, 50, 52, 54, 56, 58, 70, 72, 74, 76,
						78, 90, 92, 94, 96, 98 };
				for (int i = 0; i < arrayMinusPlusList.length; i++) {
					int number = arrayMinusPlusList[i];
					tableDao.add2D(number, money, name, pageNo);

					if (checkClosedNumberOrNot(number,request)) {
						Integer iNumber = number;
						if (number < 10) {
							alertMsg = alertMsg + " " + "0" + iNumber.toString();
						} else {
							alertMsg = alertMsg + " " + iNumber.toString();
						}
					}
				}
				type = 6;
				shortMsg = "ma sone";
				note = shortMsg;
				break;
			default:
				if (numberS.contains("/")) { // Round numbers
					numberS = numberS.replace("/", "");
					type = 5;
					pairFlag = numberS.contains("*");
					numberS = numberS.replace("*", "");

					if (checkAllDigits(numberS)) {
						List<String> strNumberList = generateTwoDigitsCombinations(numberS);
						for (String strNumber : strNumberList) {
							int number = Integer.parseInt(strNumber);
							int rNumber = getReverse(number);
							tableDao.add2DwithR(number, rNumber, money, name, pageNo);

							if (checkClosedNumberOrNot(number,request)) {
								Integer iNUmber = number;
								if (number < 10) {
									alertMsg = alertMsg + " " + "0" + iNUmber.toString();
								} else {
									alertMsg = alertMsg + " " + iNUmber.toString();
								}
							}
							if (checkClosedNumberOrNot(rNumber,request)) {
								Integer iNUmber = rNumber;
								if (rNumber < 10) {
									alertMsg = alertMsg + " " + "0" + iNUmber.toString();
								} else {
									alertMsg = alertMsg + " " + iNUmber.toString();
								}
							}
						}
						shortMsg = numberS + " akhway";

						if (pairFlag) {
							List<String> strPairNumberList = generateAllPairs(numberS);
							for (String strPairNumber : strPairNumberList) {
								int number = Integer.parseInt(strPairNumber);
								tableDao.add2D(number, money, name, pageNo);

								if (checkClosedNumberOrNot(number,request)) {
									Integer iNUmber = number;
									if (number < 10) {
										alertMsg = alertMsg + " " + "0" + iNUmber.toString();
									} else {
										alertMsg = alertMsg + " " + iNUmber.toString();
									}
								}
							}
							shortMsg += " puu";
						}

						note = shortMsg;
					} else {
						shortMsg = "wrong input! Please enter again!!";
						flag = false;
					}
				}

				else if (numberS.startsWith("++")) {// even start number
					boolean r = numberS.endsWith("++");
					if (!r) {
						type = 7;
					}
					numberS = numberS.replace("++", "");

					if (checkAllDigits(numberS) && checkOnlyOneDigit(Integer.parseInt(numberS))) {
						int digit = Integer.parseInt(numberS);
						List<Integer> evenList = generateTwoDigitsWithEvenStartCombinations(digit, r);
						for (int i = 0; i < evenList.size(); i++) {
							tableDao.add2D(evenList.get(i), money, name, pageNo);

							if (checkClosedNumberOrNot(evenList.get(i),request)) {
								Integer iNUmber = evenList.get(i);
								if (evenList.get(i) < 10) {
									alertMsg = alertMsg + " " + "0" + iNUmber.toString();
								} else {
									alertMsg = alertMsg + " " + iNUmber.toString();
								}
							}
						}
						shortMsg = numberS + (r ? " sone kat" : " sone start");
						note = shortMsg;
					} else {
						shortMsg = "wrong input!Please Enter again!!";
						flag = false;
					}

				} else if (numberS.startsWith("--")) {
					boolean r = numberS.endsWith("--");
					if (!r) {
						type = 7;
					}
					numberS = numberS.replace("--", "");

					if (checkAllDigits(numberS) && checkOnlyOneDigit(Integer.parseInt(numberS))) {
						int digit = Integer.parseInt(numberS);

						List<Integer> oddList = generateTwoDigitsWithOddStartCombinations(digit, r);

						for (int i = 0; i < oddList.size(); i++) {
							tableDao.add2D(oddList.get(i), money, name, pageNo);

							if (checkClosedNumberOrNot(oddList.get(i),request)) {
								Integer iNUmber = oddList.get(i);
								if (oddList.get(i) < 10) {
									alertMsg = alertMsg + " " + "0" + iNUmber.toString();
								} else {
									alertMsg = alertMsg + " " + iNUmber.toString();
								}
							}
						}

						shortMsg = numberS + (r ? " ma kat" : " ma start");
						note = shortMsg;
					} else {
						shortMsg = "wrong input!Please Enter again!!";
						flag = false;
					}

				} else if (numberS.endsWith("++")) {
					type = 7;
					numberS = numberS.replace("++", "");

					if (checkAllDigits(numberS) && checkOnlyOneDigit(Integer.parseInt(numberS))) {
						int digit = Integer.parseInt(numberS);

						List<Integer> evenList = generateTwoDigitsWithEvenEndCombinations(digit);

						for (int i = 0; i < evenList.size(); i++) {
							tableDao.add2D(evenList.get(i), money, name, pageNo);

							if (checkClosedNumberOrNot(evenList.get(i),request)) {
								Integer iNUmber = evenList.get(i);
								if (evenList.get(i) < 10) {
									alertMsg = alertMsg + " " + "0" + iNUmber.toString();
								} else {
									alertMsg = alertMsg + " " + iNUmber.toString();
								}
							}
						}

						shortMsg = numberS + " sone end";
						note = shortMsg;
					} else {
						shortMsg = "wrong input!Please Enter again!!";
						flag = false;
					}
				} else if (numberS.endsWith("--")) {
					type = 7;
					numberS = numberS.replace("--", "");

					if (checkAllDigits(numberS) && checkOnlyOneDigit(Integer.parseInt(numberS))) {
						int digit = Integer.parseInt(numberS);

						List<Integer> oddList = generateTwoDigitsWithOddEndCombinations(digit);

						for (int i = 0; i < oddList.size(); i++) {
							tableDao.add2D(oddList.get(i), money, name, pageNo);

							if (checkClosedNumberOrNot(oddList.get(i),request)) {
								Integer iNUmber = oddList.get(i);
								if (oddList.get(i) < 10) {
									alertMsg = alertMsg + " " + "0" + iNUmber.toString();
								} else {
									alertMsg = alertMsg + " " + iNUmber.toString();
								}
							}
						}

						shortMsg = numberS + " ma end";
						note = shortMsg;
					} else {
						shortMsg = "wrong input!Please Enter again!!";
						flag = false;
					}
				} else {
					shortMsg = "wrong input!Please Enter again!!";
					flag = false;
				}
			}

			if (flag) {
				String msg = shortMsg + " - " + moneyS + " ks added.";
				if (!alertMsg.equals("")) {
					alertMsg = "The number (" + alertMsg + ") are closed numbers.";
				}

				request.setAttribute(CommonParameters.MESSAGE, msg);
				request.setAttribute(CommonParameters.ALERT_MESSAGE, alertMsg);

				// for history table
				twoDH = new History2D();
				twoDH.setNote(note);
				twoDH.setR("-");
				twoDH.setMoney(money);
				twoDH.setName(name);
				twoDH.setPageNo(pageNo);

				switch (type) {
				case 2:
					twoDH.setTotal(19 * money);
					break;
				case 3:
					twoDH.setTotal(20 * money);
					break;
				case 5:
					List<String> strNumberList = generateTwoDigitsCombinations(numberS);
					List<String> strPairList = new ArrayList<String>();
					if (pairFlag) {
						strPairList = generateAllPairs(numberS);
					}
					twoDH.setTotal(((2 * strNumberList.size()) + strPairList.size()) * money);
					break;
				case 6:
					twoDH.setTotal(25 * money);
					break;
				case 7:
					twoDH.setTotal(5 * money);
					break;
				default:
					twoDH.setTotal(10 * money);
					break;
				}

				pageTotal = tableDao.getPageTotal(name, pageNo);
				pageTotal += twoDH.getTotal();
				twoDH.setPageTotal(pageTotal);
				tableDao.add2DtoHistory(twoDH);
				doGet(request, response);
			} else {
				request.setAttribute(CommonParameters.MESSAGE, shortMsg);
				doGet(request, response);
			}

		}
	}

	/* All Functions */

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

	protected boolean checkClosedNumberOrNot(int number,HttpServletRequest request) {
		tableDao = new TableDaoImpl(request);
		boolean flag = false;
		
		List<Closed2D> closed2DList = tableDao.getClosedNumberTable();
		for (Closed2D closed2D : closed2DList) {
			if (number == closed2D.getNumber()) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}

	protected boolean checkAllDigits(String str) {
		return str.matches("\\d+");
	}

	protected boolean checkOnlyOneDigit(int number) {
		return (number >= 0 && number <= 9);
	}

	protected List<Integer> generateTwoDigitsWithEvenStartCombinations(int digit, boolean r) {
		List<Integer> combinations = new ArrayList<>();
		int[] evenInteger = { 0, 20, 40, 60, 80 };

		for (int evenNum : evenInteger) {
			combinations.add(evenNum + digit);
		}

		if (r) {
			int size = combinations.size();
			for (int j = 0; j < size; j++) {
				combinations.add(getReverse(combinations.get(j)));
			}
		}

		return combinations;

	}

	protected List<Integer> generateTwoDigitsWithOddStartCombinations(int digit, boolean r) {
		List<Integer> combinations = new ArrayList<>();
		int[] oddInteger = { 10, 30, 50, 70, 90 };

		for (int oddNum : oddInteger) {
			combinations.add(oddNum + digit);
		}

		if (r) {
			int size = combinations.size();
			for (int j = 0; j < size; j++) {
				combinations.add(getReverse(combinations.get(j)));
			}
		}

		return combinations;
	}

	protected List<Integer> generateTwoDigitsWithEvenEndCombinations(int digit) {
		List<Integer> combinations = new ArrayList<>();
		int[] evenInteger = { 0, 2, 4, 6, 8 };

		for (int evenNum : evenInteger) {
			combinations.add(evenNum + (digit * 10));
		}

		return combinations;
	}

	protected List<Integer> generateTwoDigitsWithOddEndCombinations(int digit) {
		List<Integer> combinations = new ArrayList<>();
		int[] oddInteger = { 1, 3, 5, 7, 9 };

		for (int oddNum : oddInteger) {
			combinations.add(oddNum + (digit * 10));
		}

		return combinations;
	}

	protected List<String> generateTwoDigitsCombinations(String number) {
		List<String> combinations = new ArrayList<>();

		for (int i = 0; i < number.length() - 1; i++) {
			for (int j = i + 1; j < number.length(); j++) {
				String combination = "" + number.charAt(i) + number.charAt(j);
				combinations.add(combination);
			}
		}

		return combinations;
	}

	protected List<String> generateAllPairs(String number) {
		List<String> combinations = new ArrayList<>();

		for (int i = 0; i < number.length(); i++) {
			String combination = "" + number.charAt(i) + number.charAt(i);
			combinations.add(combination);
		}

		return combinations;
	}
}
