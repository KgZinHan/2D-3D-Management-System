package com.commcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CommTableDao;
import com.dao.CommTableDaoImpl;
import com.dao.RecoverTableDao;
import com.dao.TableDao;
import com.dao.TableDaoImpl;
import com.entity.Comm2D;
import com.entity.History2D;

import common.CommonParameters;

public class CommissionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Comm2D> commSellerList = new ArrayList<Comm2D>();
	History2D twoDH = null;
	int total;
	int recoverTotal;
	int realID;
	String shortMsg;
	TableDao tableDao;
	RecoverTableDao recoverTableDao;
	CommTableDao commTableDao;

	public CommissionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		tableDao = new TableDaoImpl(request);
		commTableDao = new CommTableDaoImpl(request);
		
		String mode = request.getParameter("mode");
		
		if(mode == "update" || mode.equals("update")) {
			String commName = request.getParameter("commName");
			String commPercent = request.getParameter("commPercent");
			String commZ = request.getParameter("commZ");
			Comm2D comm = new Comm2D();
			comm.setCommName(commName);
			comm.setCommPercent(Integer.parseInt(commPercent));
			comm.setCommZ(Integer.parseInt(commZ));
			
			if(commTableDao.checkCommName(commName)) {
				commTableDao.updateCommission(comm);
			}
			else {
				commTableDao.addCommission(comm);
			}
		}
		
		if(mode == "delete" || mode.equals("delete")) {
			String commName = request.getParameter("commName");
			commTableDao.deleteCommission(commName);
		}
		
		commSellerList = commTableDao.getCommissionList();
		
		request.setAttribute(CommonParameters.COMM_LIST, commSellerList);
		dispatcher = request.getRequestDispatcher("/commissionPage");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		}
}
