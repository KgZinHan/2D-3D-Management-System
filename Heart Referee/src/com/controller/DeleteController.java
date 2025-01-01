package com.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	List<Recover2D> recoverList = new ArrayList<Recover2D>();
	History2D twoDH = null;
	Number2D twoD = null;
	int total;
	int userTotal;
	int pageTotal;
	int realID;
	String msg;
	TableDao tableDao;
	RecoverTableDao recoverTableDao;

	public DeleteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);

		String idAlertColor = CommonConstants.ID_DEFAULT_COLOR;
		int recoverMoney = 0;
		int recoverPMoney = 0;
		int recoverPlusMoney = 0;
		int recoverComMoney = 0;
		int page;

		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);

		String idS = request.getParameter("id");

		if (idS == "99999" || idS.equals("99999")) {
			String number = request.getParameter("number");
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			String recoverFlag = request.getParameter("recoverFlag");
			String extraMoney = request.getParameter("extraMoney");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate todayDate = LocalDate.parse(date, formatter);
	        Date sqlDate = Date.valueOf(todayDate); 
			if (recoverFlag != null) { // add Recover Results to Recover Ledger

				// Calculate recoverPlusMoney
				recoverMoney = 0 - recoverTableDao.getTotalRecoverMoney();
				recoverList = recoverTableDao.getTotalRecoverList();

				for (Recover2D recover : recoverList) {
					Recover2D seller = recoverTableDao.getRecoverSellerBySellerName(recover.getSellerName());
					int recCom = (seller.getSellerCom() * recover.getSellerMoney()) / 100;
					int recP = recoverTableDao.getTotalRecoverPBySeller(recover.getSellerName(),
							Integer.parseInt(number));
					recoverPMoney += recP;
					recoverComMoney += Math.round(recCom / 50f) * 50;
					recoverPlusMoney += recP * seller.getSellerZ();
				}

				recoverPlusMoney += recoverComMoney;

				// Add to Recover Ledger
				List<Recover2D> recoverSellerList = recoverTableDao.getRecoverSellerList();

				for (Recover2D recoverSeller : recoverSellerList) {

					String seller = recoverSeller.getSellerName();
					recoverSeller.setDate(sqlDate);
					recoverSeller.setTime(time);
					recoverSeller.setSellerMoney(recoverTableDao.getTotalRecoverMoneyBySeller(seller));
					if (recoverSeller.getSellerMoney() > 0) {
						recoverSeller.setRecoverP(
								recoverTableDao.getTotalRecoverPBySeller(seller, Integer.parseInt(number)));
						int recoverCom = (recoverSeller.getSellerMoney() * recoverSeller.getSellerCom()) / 100;
						recoverCom = Math.round(recoverCom / 50f) * 50;
						recoverSeller.setRecoverCom(recoverCom);
						recoverSeller.setRecoverPlus(recoverSeller.getRecoverCom()
								+ (recoverSeller.getRecoverP() * recoverSeller.getSellerZ()));
						recoverSeller.setTotalRecover(recoverSeller.getRecoverPlus() - recoverSeller.getSellerMoney());
						tableDao.addValuesToAllRecoverTable(recoverSeller);
					}
				}
			}

			Ledger ledger = new Ledger();
			ledger.setDate(sqlDate);
			ledger.setTime(time);
			ledger.setRecoverMoney(recoverMoney);
			ledger.setRecoverPMoney(recoverPMoney);
			ledger.setRecoverComMoney(recoverComMoney);
			ledger.setRecoverPlusMoney(recoverPlusMoney);
			ledger.setExtraMoney(Integer.parseInt(extraMoney));
			tableDao.addValuesToAllTable(ledger);
			tableDao.deleteTable();
			
			session.removeAttribute(CommonParameters.SESSION_NAME);
			session.invalidate();
			
			response.sendRedirect("index.jsp");

		} else if (idS == "delete" || idS.equals("delete")) {
			tableDao.deleteUser(userName);
			response.sendRedirect("index.jsp");
		} else if (idS == "all" || idS.equals("all")) {
			tableDao.deleteAllTable();
			tableDao.deleteTable();
			response.sendRedirect("index.jsp");
		} else {
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
			
			if (realID > CommonConstants.ID_COUNT_LIMIT) {
				idAlertColor = CommonConstants.ID_ALERT_COLOR;
			}

			for (int i = 0; i < userList.size(); i++) {
				String checked = "red";
				if (tableDao.checkNameInTempTable(userList.get(i).getUser()) == true) {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
