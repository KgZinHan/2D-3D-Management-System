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

public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<User2D> userList = new ArrayList<User2D>();
	History2D twoDH = null;
	Number2D twoD = null;
	int total;
	int userTotal;
	int pageTotal;
	int realID;
	String msg;
	TableDao tableDao = new TableDaoImpl();

	public DeleteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		String idS = request.getParameter("id");
		List<Integer> dNumberList = new ArrayList<Integer>();
		

		String dNumbers = "0";
		int page;
		if (idS == "99999" || idS.equals("99999")) {
			session.removeAttribute(CommonParameters.SESSION_NAME);
			session.invalidate();
			tableDao.deleteTable();
			response.sendRedirect("index.jsp");

		} 
		else if(idS == "delete" || idS.equals("delete")) {
			tableDao.deleteUser(userName);
			response.sendRedirect("index.jsp");
		}
		else {
			int id = Integer.parseInt(idS);
			twoD = new Number2D();
			twoD = tableDao.getNumber(id);
			twoDH = new History2D();
			twoDH.setMoney(twoD.getMoney());
			twoDH.setNote(twoD.getNumber() + " Deleted");
			twoDH.setR("-");
			twoDH.setTotal(-twoD.getMoney());
			page = tableDao.getPageById(id);
			twoDH.setPageNo(page);
			twoDH.setName(userName);
			pageTotal = tableDao.getPageTotal(userName, page);
			pageTotal = pageTotal - (twoD.getMoney());
			twoDH.setPageTotal(pageTotal);

			tableDao.add2DtoHistory(twoDH);

			tableDao.deleteRow(id);
			msg = twoD.getNumber() + " is Deleted!";

			twoDList = tableDao.getTableByUser(userName);
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
			request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR, CommonConstants.HOVER_COLOR_CODE);
			request.setAttribute(CommonParameters.REAL_ID, realID);
			request.setAttribute(CommonParameters.MESSAGE, msg);
			request.setAttribute(CommonParameters.PAGE_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.DELETE_COLUMN_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
			request.setAttribute(CommonParameters.USER_LIST, userList);
			request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
			dispatcher = request.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
