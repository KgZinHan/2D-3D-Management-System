package com.controller;

import java.io.IOException;
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




public class addRecover extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	History2D h2D1 = null;
	History2D h2D2 = null;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numberS = request.getParameter("number");
		String moneyS = request.getParameter("money");
		String rMoneyS = request.getParameter("rMoney");
		String limit = request.getParameter("limit");
		request.setAttribute("number", numberS);
		request.setAttribute("money", moneyS);
		request.setAttribute("rMoney", rMoneyS);
		request.setAttribute("limit", limit);
		dispatcher = request.getRequestDispatcher("/addRecoverPage");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		h2D1 = new History2D();
		h2D2 = new History2D();
		String numberS1 = request.getParameter("number1");
		String moneyS = request.getParameter("money");
		String rMoneyS = request.getParameter("rMoney");
		String limit = request.getParameter("limit");
		int number1 = Integer.parseInt(numberS1);
		int money = Integer.parseInt(moneyS);
		int rMoney = Integer.parseInt(rMoneyS);
		
		Integer rNumber1 = getReverse(number1);
		/*
		 * recoverTableDao.add2D(number1, money); recoverTableDao.add2D(rNumber1,
		 * rMoney);
		 */
		h2D1.setNote(numberS1);
		h2D1.setR("-");
		h2D1.setTotal(money);
		h2D1.setMoney(money);
		recoverTableDao.add2DtoRecoverHistory(h2D1);
		h2D2.setNote(rNumber1.toString());
		h2D2.setR("-");
		h2D2.setTotal(rMoney);
		h2D2.setMoney(rMoney);
		recoverTableDao.add2DtoRecoverHistory(h2D2);
		request.setAttribute("limit", limit);
		dispatcher = request.getRequestDispatcher("/Recover");
		dispatcher.forward(request, response);

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
