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
import com.entity.User2D;

import common.CommonParameters;

@WebServlet("/SearchRecoverController")
public class SearchRecoverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<Number2D> twoDCheckList = new ArrayList<Number2D>();
	List<User2D> userList = new ArrayList<User2D>();
	int total;
	int recoverTotal;
	int realID;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public SearchRecoverController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("number");
		int number = Integer.parseInt(search);
		List<Integer> dNumberList = new ArrayList<Integer>();
		String dNumbers = "0";
		
		twoDList = recoverTableDao.search2DRecoverAmount(number);
		
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
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
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.DANGEROUS_NUMBERS, dNumbers);
		request.setAttribute(CommonParameters.TAB_BAR_RECOVER_NOTE_COLOR, "aqua");
		request.setAttribute(CommonParameters.REAL_ID, realID);
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/recoverPage");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected int getTotal() {
		int totalMoney = 0;
		totalMoney = tableDao.getTotalMoney();
		return totalMoney;

	}

}
