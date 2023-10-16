package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CommTableDao;
import com.dao.CommTableDaoImpl;
import com.dao.RecoverTableDao;
import com.dao.RecoverTableDaoImpl;
import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.AllUser2D;
import com.entity.Comm2D;
import com.entity.Recover2D;
import com.entity.Summary2D;

import common.CommonConstants;
import common.CommonParameters;

public class AutoReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Summary2D> resultList = new ArrayList<Summary2D>();
	List<Recover2D> recoverPList = new ArrayList<Recover2D>();
	int total;
	int recoverTotal;
	TableDao tableDao;
	RecoverTableDao recoverTableDao;
	CommTableDao commTableDao;

	public AutoReportController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);
		commTableDao = new CommTableDaoImpl(request);
		
		String numberS = request.getParameter("number");
		int number = Integer.parseInt(numberS);
		
		String message = "";
		
		List<Comm2D> commList = new ArrayList<Comm2D>();
		commList = commTableDao.getCommissionList();
		
		for (Comm2D comm2d : commList) {
			
			int totalMoney = tableDao.getUserTotalMoney(comm2d.getCommName());
			if(totalMoney > 0) {
				int p = tableDao.getUserMoneyByNumber(comm2d.getCommName(), number);
				int userPMoney = p * comm2d.getCommZ();
				int tCommission = (totalMoney * comm2d.getCommPercent()) / 100;
				int commission = Math.round(tCommission / 50f) * 50;
				int total = totalMoney - userPMoney - commission;
				
				AllUser2D user2D = new AllUser2D();
				user2D.setUsername(comm2d.getCommName());
				user2D.setNumber(number);
				user2D.setTotalMoney(totalMoney);
				user2D.setP(p);
				user2D.setpMoney(userPMoney);
				user2D.setComPercent(comm2d.getCommPercent());
				user2D.setComMoney(commission);
				user2D.setTotal(total);
				
				if (tableDao.checkNameInTempTable(comm2d.getCommName()) == true) {
					tableDao.updateUserTempTable(user2D);
					message = "Successfully Updated!";
				} else {
					tableDao.addUserTempTable(user2D);
					message = "Successfully Saved!";
				}
			}
		}

		int money = tableDao.getMoney(number);
		resultList = tableDao.getResultTableByNumber(number);
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
		recoverPList = recoverTableDao.getTotalRecoverPlusMoney(number);
		
		for(Recover2D recover : recoverPList) {
			recover.setTotalRecover(recoverTableDao.getTotalRecoverMoneyBySeller(recover.getSellerName()));
		}
		
		request.setAttribute(CommonParameters.MESSAGE, message);
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TWO_D_NUMBER, numberS);
		request.setAttribute(CommonParameters.TWO_D_MONEY, money);
		request.setAttribute(CommonParameters.TWO_D_LIST, resultList);
		request.setAttribute(CommonParameters.RECOVER_LIST, recoverPList);
		dispatcher = request.getRequestDispatcher("/summary");
		dispatcher.forward(request, response);
	}

}
