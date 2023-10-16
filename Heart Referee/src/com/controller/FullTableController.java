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
	TableDao tableDao;
	RecoverTableDao recoverTableDao;

	public FullTableController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);
		
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
		setColor(zeroStartList,request);
		oneStartList = tableDao.startList(1);
		setColor(oneStartList,request);
		twoStartList = tableDao.startList(2);
		setColor(twoStartList,request);
		threeStartList = tableDao.startList(3);
		setColor(threeStartList,request);
		fourStartList = tableDao.startList(4);
		setColor(fourStartList,request);
		fiveStartList = tableDao.startList(5);
		setColor(fiveStartList,request);
		sixStartList = tableDao.startList(6);
		setColor(sixStartList,request);
		sevenStartList = tableDao.startList(7);
		setColor(sevenStartList,request);
		eightStartList = tableDao.startList(8);
		setColor(eightStartList,request);
		nineStartList = tableDao.startList(9);
		setColor(nineStartList,request);

		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TAB_BAR_FULL_TABLE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.BUTTON_NAME, CommonConstants.WITH_RECOVER);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);
		
		if(request.getParameter("mode").equals("Without Recover")) {
			doGet(request,response);
		}
		else {
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
			zeroStartList = getStartListWithRecover(tableDao.startList(0), request);
			setColor(zeroStartList,request);
			oneStartList = getStartListWithRecover(tableDao.startList(1), request);
			setColor(oneStartList,request);
			twoStartList = getStartListWithRecover(tableDao.startList(2), request);
			setColor(twoStartList,request);
			threeStartList = getStartListWithRecover(tableDao.startList(3), request);
			setColor(threeStartList,request);
			fourStartList = getStartListWithRecover(tableDao.startList(4), request);
			setColor(fourStartList,request);
			fiveStartList = getStartListWithRecover(tableDao.startList(5), request);
			setColor(fiveStartList,request);
			sixStartList = getStartListWithRecover(tableDao.startList(6), request);
			setColor(sixStartList,request);
			sevenStartList = getStartListWithRecover(tableDao.startList(7), request);
			setColor(sevenStartList,request);
			eightStartList = getStartListWithRecover(tableDao.startList(8), request);
			setColor(eightStartList,request);
			nineStartList = getStartListWithRecover(tableDao.startList(9), request);
			setColor(nineStartList,request);

			request.setAttribute(CommonParameters.TOTAL_MONEY, total);
			request.setAttribute(CommonParameters.TOTAL_RECOVER_MONEY, recoverTotal);
			request.setAttribute(CommonParameters.TAB_BAR_FULL_TABLE_COLOR, CommonConstants.HOVER_COLOR_CODE);
			request.setAttribute(CommonParameters.BUTTON_NAME, CommonConstants.WITHOUT_RECOVER);
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
	}

	protected int getTotal(HttpServletRequest request) {
		tableDao = new TableDaoImpl(request);
		int totalMoney = 0;
		totalMoney = tableDao.getTotalMoney();
		return totalMoney;

	}

	protected void setColor(List<Number2D> twoDList,HttpServletRequest request) {
		for (int j = 0; j < twoDList.size(); j++) {
			int calculatedMoney = getTotal(request)
					- ((twoDList.get(j).getMoney() * 80) + ((getTotal(request) * 15) / 100) + recoverTotal);
			if (calculatedMoney >= CommonConstants.HAPPY_LIMIT) {
				twoDList.get(j).setColor("green");
			}

			if ((twoDList.get(j).getMoney() * 80) + ((getTotal(request) * 15) / 100) + recoverTotal > getTotal(request)) {
				twoDList.get(j).setColor("red");
			}
		}
	}
	
	protected List<Number2D> getStartListWithRecover(List<Number2D> startList,HttpServletRequest request){
		recoverTableDao = new RecoverTableDaoImpl(request);
		for(Number2D number2D : startList) {
			int newMoney = number2D.getMoney() - recoverTableDao.getRecoverMoney(number2D.getNumber());
			number2D.setMoney(newMoney);
		}
		return startList;
	}

}
