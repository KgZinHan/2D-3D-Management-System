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
import com.entity.History2D;
import com.entity.Number2D;
import com.entity.User2D;

import common.CommonConstants;
import common.CommonParameters;

public class MultiInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<User2D> userList = new ArrayList<User2D>();
	History2D h2D = null;
	int total;
	int userTotal;
	int pageTotal;
	int realID;
	String shortMsg;
	TableDao tableDao = new TableDaoImpl();

	public MultiInputController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String defaultTable = (String) session.getAttribute(CommonParameters.SESSION_NAME);
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		List<Integer> dNumberList = new ArrayList<Integer>();
		String dNumbers = "0";
		if (defaultTable == null || defaultTable.isEmpty() || defaultTable.equals("details")) {
			if (userName == null || userName.isEmpty() || userName.equals(null)) {
				twoDList = tableDao.getTable();
			} else {
				twoDList = tableDao.getTableByUser(userName);
			}
			total = tableDao.getTotalMoney();
			userTotal = tableDao.getUserTotalMoney(userName);
			userList = tableDao.getUsers();
			realID = tableDao.getIdCount();

			dNumberList = tableDao.getDangerousNumber();
			if (!(dNumberList.size() <= 0)) {
				dNumbers = dNumberList.get(0).toString();
				for (int i = 1; i < dNumberList.size(); i++) {
					if(dNumberList.get(i) < 10) {
						dNumbers = dNumbers + " - " + "0"+ dNumberList.get(i).toString();
					}
					else {
						dNumbers = dNumbers + " - " + dNumberList.get(i).toString();
					}		
				}
			}

			request.setAttribute(CommonParameters.TOTAL_MONEY, total);
			request.setAttribute(CommonParameters.USER_TOTAL_MONEY, userTotal);
			request.setAttribute(CommonParameters.DANGEROUS_NUMBERS, dNumbers);
			request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR,CommonConstants.HOVER_COLOR_CODE);
			request.setAttribute(CommonParameters.REAL_ID, realID);
			request.setAttribute(CommonParameters.DELETE_COLUMN_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.USER_LIST, userList);
			request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
			dispatcher = request.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
		} else if (defaultTable.equals("history")) {
			dispatcher = request.getRequestDispatcher("History");
			dispatcher.forward(request, response);
		} else if (defaultTable.equals("normal")) {
			response.sendRedirect("SortByUser?m=money");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		h2D = new History2D();
		String msg = new String();
		String moneyS = request.getParameter("money");
		int pageNo = 1;
		int money = Integer.parseInt(moneyS);
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute(CommonParameters.SESSION_USER);
		String reverse = request.getParameter("reverse");
		String numberS1 = request.getParameter("number1");
		String numberS2 = request.getParameter("number2");
		String numberS3 = request.getParameter("number3");
		String numberS4 = request.getParameter("number4");
		String numberS5 = request.getParameter("number5");
		String numberS6 = request.getParameter("number6");
		String numberS7 = request.getParameter("number7");
		String numberS8 = request.getParameter("number8");
		String numberS9 = request.getParameter("number9");
		String numberS0 = request.getParameter("number0");

		if (reverse.equals("yes") || reverse == "yes") {
			int rCount = 0;
			if (!(numberS1.isEmpty())) {
				int number1 = Integer.parseInt(numberS1);
				Integer rNumber1 = getReverse(number1);
				tableDao.add2DwithR(number1, rNumber1, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = numberS1;
			}
			if (!(numberS2.isEmpty())) {
				int number2 = Integer.parseInt(numberS2);
				Integer rNumber2 = getReverse(number2);
				tableDao.add2DwithR(number2, rNumber2, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS2;
			}
			if (!(numberS3.isEmpty())) {
				int number3 = Integer.parseInt(numberS3);
				Integer rNumber3 = getReverse(number3);
				tableDao.add2DwithR(number3, rNumber3, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS3;
			}
			if (!(numberS4.isEmpty())) {
				int number4 = Integer.parseInt(numberS4);
				Integer rNumber4 = getReverse(number4);
				tableDao.add2DwithR(number4, rNumber4, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS4;
			}
			if (!(numberS5.isEmpty())) {
				int number5 = Integer.parseInt(numberS5);
				Integer rNumber5 = getReverse(number5);
				tableDao.add2DwithR(number5, rNumber5, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS5;
			}
			if (!(numberS6.isEmpty())) {
				int number6 = Integer.parseInt(numberS6);
				Integer rNumber6 = getReverse(number6);
				tableDao.add2DwithR(number6, rNumber6, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS6;
			}
			if (!(numberS7.isEmpty())) {
				int number7 = Integer.parseInt(numberS7);
				Integer rNumber7 = getReverse(number7);
				tableDao.add2DwithR(number7, rNumber7, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS7;
			}
			if (!(numberS8.isEmpty())) {
				int number8 = Integer.parseInt(numberS8);
				Integer rNumber8 = getReverse(number8);
				tableDao.add2DwithR(number8, rNumber8, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS8;
			}
			if (!(numberS9.isEmpty())) {
				int number9 = Integer.parseInt(numberS9);
				Integer rNumber9 = getReverse(number9);
				tableDao.add2DwithR(number9, rNumber9, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS9;
			}
			if (!(numberS0.isEmpty())) {
				int number0 = Integer.parseInt(numberS0);
				Integer rNumber0 = getReverse(number0);
				tableDao.add2DwithR(number0, rNumber0, money, name, pageNo);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS0;
			}
			msg = shortMsg + " R " + moneyS + " ks added.";
			h2D.setR("R");
			h2D.setTotal(2 * rCount * money);
			pageTotal = tableDao.getPageTotal(name, pageNo);
			pageTotal = pageTotal + (2 * rCount * money);
			h2D.setPageTotal(pageTotal);
		} else {
			int count = 0;
			if (!(numberS1.isEmpty())) {
				int number1 = Integer.parseInt(numberS1);
				tableDao.add2D(number1, money, name, pageNo);
				count = count + 1;
				shortMsg = numberS1;
			}
			if (!(numberS2.isEmpty())) {
				int number2 = Integer.parseInt(numberS2);
				tableDao.add2D(number2, money, name, pageNo);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS2;
			}
			if (!(numberS3.isEmpty())) {
				int number3 = Integer.parseInt(numberS3);
				tableDao.add2D(number3, money, name, pageNo);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS3;
			}
			if (!(numberS4.isEmpty())) {
				int number4 = Integer.parseInt(numberS4);
				tableDao.add2D(number4, money, name, pageNo);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS4;
			}
			if (!(numberS5.isEmpty())) {
				int number5 = Integer.parseInt(numberS5);
				tableDao.add2D(number5, money, name, pageNo);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS5;
			}
			if (!(numberS6.isEmpty())) {
				int number6 = Integer.parseInt(numberS6);
				tableDao.add2D(number6, money, name, pageNo);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS6;
			}
			if (!(numberS7.isEmpty())) {
				int number7 = Integer.parseInt(numberS7);
				tableDao.add2D(number7, money, name, pageNo);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS7;
			}
			if (!(numberS8.isEmpty())) {
				int number8 = Integer.parseInt(numberS8);
				tableDao.add2D(number8, money, name, pageNo);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS8;
			}
			if (!(numberS9.isEmpty())) {
				int number9 = Integer.parseInt(numberS9);
				tableDao.add2D(number9, money, name, pageNo);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS9;
			}
			if (!(numberS0.isEmpty())) {
				int number0 = Integer.parseInt(numberS0);
				tableDao.add2D(number0, money, name, pageNo);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS0;
			}
			msg = shortMsg + " - " + moneyS + " ks added.";
			h2D.setR("-");
			h2D.setTotal(count * money);
			pageTotal = tableDao.getPageTotal(name, pageNo);
			pageTotal = pageTotal + (count * money);
			h2D.setPageTotal(pageTotal);
		}
		h2D.setNote(shortMsg);
		h2D.setMoney(money);
		h2D.setName(name);
		h2D.setPageNo(pageNo);
		tableDao.add2DtoHistory(h2D);
		request.setAttribute(CommonParameters.MESSAGE, msg);
		doGet(request, response);
	}

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

}
