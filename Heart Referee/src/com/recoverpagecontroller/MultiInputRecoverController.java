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
import com.entity.History2D;
import com.entity.Number2D;
import com.entity.Recover2D;

import common.CommonConstants;
import common.CommonParameters;

public class MultiInputRecoverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<Recover2D> recoverSellerList = new ArrayList<Recover2D>();
	History2D h2D = null;
	int total;
	int recoverTotal;
	int pageTotal;
	int realID;
	String shortMsg;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public MultiInputRecoverController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sellerName = (String) request.getAttribute(CommonParameters.SELLER_NAME);
		int totalSellerRecover = 0;
		
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney(); 
		totalSellerRecover = recoverTableDao.getTotalRecoverMoneyBySeller(sellerName);
		
		recoverSellerList = recoverTableDao.getRecoverSellerList();
		twoDList = recoverTableDao.getRecoverHistoryTableBySeller(sellerName);

		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TOTAL_SELLER_RECOVER, totalSellerRecover);
		request.setAttribute(CommonParameters.SELLER_NAME, sellerName);
		request.setAttribute(CommonParameters.RECOVER_LIST, recoverSellerList);
		request.setAttribute(CommonParameters.TAB_BAR_RECOVER_NOTE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.NOTE_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.R_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.TOTAL_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.NUMBER_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/recoverPage");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sellerName = request.getParameter("sellerName");
		h2D = new History2D();
		String msg = new String();
		String moneyS = request.getParameter("money");
		int money = Integer.parseInt(moneyS);
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
				recoverTableDao.add2DwithR(number1, rNumber1, money,sellerName);
				rCount = rCount + 1;
				shortMsg = numberS1;
			}
			if (!(numberS2.isEmpty())) {
				int number2 = Integer.parseInt(numberS2);
				Integer rNumber2 = getReverse(number2);
				recoverTableDao.add2DwithR(number2, rNumber2, money,sellerName);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS2;
			}
			if (!(numberS3.isEmpty())) {
				int number3 = Integer.parseInt(numberS3);
				Integer rNumber3 = getReverse(number3);
				recoverTableDao.add2DwithR(number3, rNumber3, money,sellerName);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS3;
			}
			if (!(numberS4.isEmpty())) {
				int number4 = Integer.parseInt(numberS4);
				Integer rNumber4 = getReverse(number4);
				recoverTableDao.add2DwithR(number4, rNumber4, money,sellerName);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS4;
			}
			if (!(numberS5.isEmpty())) {
				int number5 = Integer.parseInt(numberS5);
				Integer rNumber5 = getReverse(number5);
				recoverTableDao.add2DwithR(number5, rNumber5, money,sellerName);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS5;
			}
			if (!(numberS6.isEmpty())) {
				int number6 = Integer.parseInt(numberS6);
				Integer rNumber6 = getReverse(number6);
				recoverTableDao.add2DwithR(number6, rNumber6, money,sellerName);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS6;
			}
			if (!(numberS7.isEmpty())) {
				int number7 = Integer.parseInt(numberS7);
				Integer rNumber7 = getReverse(number7);
				recoverTableDao.add2DwithR(number7, rNumber7, money,sellerName);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS7;
			}
			if (!(numberS8.isEmpty())) {
				int number8 = Integer.parseInt(numberS8);
				Integer rNumber8 = getReverse(number8);
				recoverTableDao.add2DwithR(number8, rNumber8, money,sellerName);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS8;
			}
			if (!(numberS9.isEmpty())) {
				int number9 = Integer.parseInt(numberS9);
				Integer rNumber9 = getReverse(number9);
				recoverTableDao.add2DwithR(number9, rNumber9, money,sellerName);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS9;
			}
			if (!(numberS0.isEmpty())) {
				int number0 = Integer.parseInt(numberS0);
				Integer rNumber0 = getReverse(number0);
				recoverTableDao.add2DwithR(number0, rNumber0, money,sellerName);
				rCount = rCount + 1;
				shortMsg = shortMsg + " / " + numberS0;
			}
			msg = shortMsg + " R " + moneyS + " added.";
			h2D.setR("R");
			h2D.setTotal(2 * rCount * money);
		} else {
			int count = 0;
			if (!(numberS1.isEmpty())) {
				int number1 = Integer.parseInt(numberS1);
				recoverTableDao.add2D(number1, money,sellerName);
				count = count + 1;
				shortMsg = numberS1;
			}
			if (!(numberS2.isEmpty())) {
				int number2 = Integer.parseInt(numberS2);
				recoverTableDao.add2D(number2, money,sellerName);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS2;
			}
			if (!(numberS3.isEmpty())) {
				int number3 = Integer.parseInt(numberS3);
				recoverTableDao.add2D(number3, money,sellerName);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS3;
			}
			if (!(numberS4.isEmpty())) {
				int number4 = Integer.parseInt(numberS4);
				recoverTableDao.add2D(number4, money,sellerName);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS4;
			}
			if (!(numberS5.isEmpty())) {
				int number5 = Integer.parseInt(numberS5);
				recoverTableDao.add2D(number5, money,sellerName);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS5;
			}
			if (!(numberS6.isEmpty())) {
				int number6 = Integer.parseInt(numberS6);
				recoverTableDao.add2D(number6, money,sellerName);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS6;
			}
			if (!(numberS7.isEmpty())) {
				int number7 = Integer.parseInt(numberS7);
				recoverTableDao.add2D(number7, money,sellerName);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS7;
			}
			if (!(numberS8.isEmpty())) {
				int number8 = Integer.parseInt(numberS8);
				recoverTableDao.add2D(number8, money,sellerName);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS8;
			}
			if (!(numberS9.isEmpty())) {
				int number9 = Integer.parseInt(numberS9);
				recoverTableDao.add2D(number9, money,sellerName);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS9;
			}
			if (!(numberS0.isEmpty())) {
				int number0 = Integer.parseInt(numberS0);
				recoverTableDao.add2D(number0, money,sellerName);
				count = count + 1;
				shortMsg = shortMsg + " / " + numberS0;
			}
			msg = shortMsg + " - " + moneyS + " added.";
			h2D.setR("-");
			h2D.setTotal(count * money);
		}
		h2D.setNote(shortMsg);
		h2D.setMoney(money);
		h2D.setName(sellerName);
		recoverTableDao.add2DtoRecoverHistory(h2D);
		request.setAttribute(CommonParameters.SELLER_NAME, sellerName);
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
