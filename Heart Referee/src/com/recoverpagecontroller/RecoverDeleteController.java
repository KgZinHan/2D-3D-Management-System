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
import com.entity.User2D;

import common.CommonParameters;

public class RecoverDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<User2D> userList = new ArrayList<User2D>();
	History2D twoDH = null;
	Number2D twoD = null;
	int total;
	int recoverTotal;
	String msg;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public RecoverDeleteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idS = request.getParameter("id");
		List<Integer> dNumberList = new ArrayList<Integer>();
		String dNumbers = "0";
		int id = Integer.parseInt(idS);
		
		twoD = new Number2D();
		twoD = recoverTableDao.getRecoverById(id);
		twoDH = new History2D();
		twoDH.setMoney(twoD.getMoney());
		twoDH.setNote(twoD.getNumber() + " Deleted");
		twoDH.setR("-");
		twoDH.setTotal(-twoD.getMoney());
		recoverTableDao.add2DtoRecoverHistory(twoDH);

		recoverTableDao.deleteRecoverRow(id);
		msg = twoD.getNumber() + " is Deleted!";

		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
		
		twoDList = recoverTableDao.getRecoverTable();
		
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
		request.setAttribute(CommonParameters.DANGEROUS_NUMBERS, dNumbers);
		request.setAttribute(CommonParameters.TAB_BAR_RECOVER_NOTE_COLOR, "aqua");
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.DELETE_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/recoverPage");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
