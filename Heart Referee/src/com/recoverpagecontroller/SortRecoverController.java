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
import com.entity.Number2D;


import common.CommonConstants;
import common.CommonParameters;

public class SortRecoverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	int total;
	int recoverTotal;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public SortRecoverController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Number2D> top2D = new ArrayList<Number2D>();
		List<Integer> dNumberList = new ArrayList<Integer>();
		String dNumbers = "0";
		String entity = request.getParameter("m");
		String numberHColor = "";
		String moneyHColor = "";
		if (entity == "number" || entity.equals("number")) {
			top2D = recoverTableDao.sortRecoverByNumber();
			numberHColor = CommonConstants.SORT_COLOR_CODE;
		}
		if (entity == "money" || entity.equals("money")) {
			top2D = recoverTableDao.sortRecoverByMoney();
			moneyHColor = CommonConstants.SORT_COLOR_CODE;
		}
		
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();

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
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.DANGEROUS_NUMBERS, dNumbers);
		request.setAttribute(CommonParameters.TAB_BAR_RECOVER_NOTE_COLOR, "aqua");
		request.setAttribute(CommonParameters.NUMBER_SORT_COLOR, numberHColor);
		request.setAttribute(CommonParameters.MONEY_SORT_COLOR, moneyHColor);
		request.setAttribute(CommonParameters.TWO_D_LIST, top2D);
		dispatcher = request.getRequestDispatcher("/recoverPage");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
