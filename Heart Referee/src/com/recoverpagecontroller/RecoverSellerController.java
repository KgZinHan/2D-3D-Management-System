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
import com.entity.Recover2D;

import common.CommonParameters;

public class RecoverSellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<Recover2D> recoverSellerList = new ArrayList<Recover2D>();
	History2D twoDH = null;
	int total;
	int recoverTotal;
	int realID;
	String shortMsg;
	TableDao tableDao = new TableDaoImpl();
	RecoverTableDao recoverTableDao = new RecoverTableDaoImpl();

	public RecoverSellerController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		if(mode == "update" || mode.equals("update")) {
			String sellerName = request.getParameter("sellerName");
			String sellerCom = request.getParameter("sellerCom");
			String sellerZ = request.getParameter("sellerZ");
			Recover2D seller = new Recover2D();
			seller.setSellerName(sellerName);
			seller.setSellerCom(Integer.parseInt(sellerCom));
			seller.setSellerZ(Integer.parseInt(sellerZ));
			if(recoverTableDao.checkSellerName(sellerName)) {
				recoverTableDao.updateSeller(seller);
			}
			else {
				recoverTableDao.addSeller(seller);
			}
		}
		if(mode == "delete" || mode.equals("delete")) {
			String sellerName = request.getParameter("sellerName");
			recoverTableDao.deleteRecoverSeller(sellerName);
		}
		recoverSellerList = recoverTableDao.getRecoverSellerList();
		request.setAttribute(CommonParameters.RECOVER_SELLER_LIST, recoverSellerList);
		dispatcher = request.getRequestDispatcher("/recoverSellerPage");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		}
}
