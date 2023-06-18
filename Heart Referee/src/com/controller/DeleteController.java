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

import org.eclipse.jdt.internal.compiler.parser.RecoveredModuleStatement;

import com.dao.RecoverTableDao;
import com.dao.RecoverTableDaoImpl;
import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.History2D;
import com.entity.Number2D;
import com.entity.Recover2D;
import com.entity.Ledger;
import com.entity.User2D;

import common.CommonConstants;
import common.CommonParameters;

public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<User2D> userList = new ArrayList<User2D>();
	List<Recover2D> recoverPlusList = new ArrayList<Recover2D>();
	History2D twoDH = null;
	Number2D twoD = null;
	int total;
	int userTotal;
	int pageTotal;
	int realID;
	String msg;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public DeleteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		String idS = request.getParameter("id");
		String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;
		int recoverMoney = 0;
		int recoverPMoney = 0;
		int recoverPlusMoney = 0;
		int recoverComMoney = 0;
		int page;
		
		if (idS == "99999" || idS.equals("99999")) {
			String number = request.getParameter("number");
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			String recoverFlag = request.getParameter("recoverFlag");
			String extraMoney = request.getParameter("extraMoney");
			String todayDate = date + " " + time;
			
			if(recoverFlag != null) {
				recoverMoney = 0 - recoverTableDao.getTotalRecoverMoney();
				recoverPlusList = recoverTableDao.getTotalRecoverPlusMoney(Integer.parseInt(number));
				for(int i =0;i<recoverPlusList.size();i++) {
					Recover2D seller = recoverTableDao.getRecoverSellerBySellerName(recoverPlusList.get(i).getSellerName());
					int commission = (seller.getSellerCom() * recoverTableDao.getTotalRecoverMoneyBySeller(recoverPlusList.get(i).getSellerName()))/100;
					recoverComMoney = recoverComMoney + Math.round(commission/50f) * 50;
					recoverPlusMoney = recoverPlusMoney + (recoverPlusList.get(i).getSellerMoney() * seller.getSellerZ());
				}
				recoverPlusMoney = recoverPlusMoney + recoverComMoney;
				SetAllRecoverTable(Integer.parseInt(number),todayDate);
			}
			
			Ledger ledger = new Ledger();
			ledger.setDate(todayDate);
			ledger.setRecoverMoney(recoverMoney);
			ledger.setRecoverPMoney(recoverPMoney);
			ledger.setRecoverComMoney(recoverComMoney);
			ledger.setRecoverPlusMoney(recoverPlusMoney);
			ledger.setExtraMoney(0 - Integer.parseInt(extraMoney));
			
			session.removeAttribute(CommonParameters.SESSION_NAME);
			session.invalidate();
			tableDao.addValuesToAllTable(ledger);
			tableDao.deleteTable();
			response.sendRedirect("index.jsp");

		} 
		else if(idS == "delete" || idS.equals("delete")) {
			tableDao.deleteUser(userName);
			response.sendRedirect("index.jsp");
		}
		else if (idS == "all" || idS.equals("all")) {
			tableDao.deleteAllTable();
			tableDao.deleteTable();
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
			if(realID > CommonConstants.ID_COUNT_LIMIT) {
				idAlertColor = CommonConstants.ID_ALERT_COLOR; 
			}

			for(int i = 0;i < userList.size();i++) {
				String checked = "red";
				if(tableDao.checkNameInTempTable(userList.get(i).getUser()) == true)
				{
					checked = "green";
				}
				userList.get(i).setChecked(checked);
			}

			request.setAttribute(CommonParameters.TOTAL_MONEY, total);
			request.setAttribute(CommonParameters.USER_TOTAL_MONEY, userTotal);
			request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR, CommonConstants.HOVER_COLOR_CODE);
			request.setAttribute(CommonParameters.REAL_ID, realID);
			request.setAttribute(CommonParameters.ID_ALERT_COLOR, idAlertColor);
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
	
	protected void SetAllRecoverTable(int number,String date) {
		List<Recover2D> recoverSellerList = recoverTableDao.getRecoverSellerList();
		for(int i =0;i<recoverSellerList.size();i++) {
			String seller = recoverSellerList.get(i).getSellerName();
			recoverSellerList.get(i).setDate(date);
			recoverSellerList.get(i).setSellerMoney(recoverTableDao.getTotalRecoverMoneyBySeller(seller));
			recoverSellerList.get(i).setRecoverP(recoverTableDao.getTotalRecoverPBySeller(seller,number));
			recoverSellerList.get(i).setRecoverCom((recoverSellerList.get(i).getSellerMoney()*recoverSellerList.get(i).getSellerCom())/100);
			recoverSellerList.get(i).setRecoverPlus(recoverSellerList.get(i).getRecoverCom() + (recoverSellerList.get(i).getRecoverP()*recoverSellerList.get(i).getSellerZ()));
			recoverSellerList.get(i).setTotalRecover(recoverSellerList.get(i).getRecoverPlus()-recoverSellerList.get(i).getSellerMoney());
			tableDao.addValuesToAllRecoverTable(recoverSellerList.get(i));
			
		}
	}

}
