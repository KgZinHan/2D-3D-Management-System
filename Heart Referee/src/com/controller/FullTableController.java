package com.controller;

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


public class FullTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	int total;
	int recoverTotal;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public FullTableController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Number2D> zeroStartList = new ArrayList<Number2D>();
		List<Number2D> oneStartList = new ArrayList<Number2D>();
		List<Number2D> twoStartList = new ArrayList<Number2D>();
		List<Number2D> threeStartList = new ArrayList<Number2D>();
		List<Number2D> fourStartList = new ArrayList<Number2D>();
		List<Number2D> fiveStartList = new ArrayList<Number2D>();
		List<Number2D> sixStartList = new ArrayList<Number2D>();
		List<Number2D> sevenStartList = new ArrayList<Number2D>();
		List<Number2D> eightStartList = new ArrayList<Number2D>();
		List<Number2D> nineStartList = new ArrayList<Number2D>();
		
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney(); 
		zeroStartList = tableDao.startList(0);
		setColor(zeroStartList);
		oneStartList = tableDao.startList(1);
		setColor(oneStartList);
		twoStartList = tableDao.startList(2);
		setColor(twoStartList);
		threeStartList = tableDao.startList(3);
		setColor(threeStartList);
		fourStartList = tableDao.startList(4);
		setColor(fourStartList);
		fiveStartList = tableDao.startList(5);
		setColor(fiveStartList);
		sixStartList = tableDao.startList(6);
		setColor(sixStartList);
		sevenStartList = tableDao.startList(7);
		setColor(sevenStartList);
		eightStartList = tableDao.startList(8);
		setColor(eightStartList);
		nineStartList = tableDao.startList(9);
		setColor(nineStartList);
		
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TAB_BAR_FULL_TABLE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute("zeroList", zeroStartList);
		request.setAttribute("oneList", oneStartList);
		request.setAttribute("twoList", twoStartList);
		request.setAttribute("threeList", threeStartList);
		request.setAttribute("fourList", fourStartList);
		request.setAttribute("fiveList", fiveStartList);
		request.setAttribute("sixList", sixStartList);
		request.setAttribute("sevenList", sevenStartList);
		request.setAttribute("eightList", eightStartList);
		request.setAttribute("nineList", nineStartList);
		dispatcher = request.getRequestDispatcher("/fullTable");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected int getTotal() {
		int totalMoney = 0;
		totalMoney = tableDao.getTotalMoney();
		return totalMoney;

	}
	
	protected void setColor(List<Number2D> twoDList) {
		for (int j = 0; j < twoDList.size(); j++) {
			if (getTotal() - ((twoDList.get(j).getMoney() * 80) +((getTotal() * 15) / 100) + recoverTotal)  > CommonConstants.HAPPY_LIMIT) {
				twoDList.get(j).setColor("green");
			}
			if ((twoDList.get(j).getMoney() * 80)+ ((getTotal() * 15) / 100) + recoverTotal > getTotal()) {
				twoDList.get(j).setColor("red");
			}
		}
	}

}
