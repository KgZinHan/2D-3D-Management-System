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

import common.CommonParameters;

public class TableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<User2D> userList = new ArrayList<User2D>();
	History2D twoDH = null;
	int total;
	int userTotal;
	int pageTotal;
	int realID;
	String shortMsg;
	TableDao tableDao = new TableDaoImpl();

	public TableController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String table = (String) session.getAttribute(CommonParameters.SESSION_NAME);
		String userName = (String) session.getAttribute(CommonParameters.SESSION_USER);
		List<Integer> dNumberList = new ArrayList<Integer>();
		String dNumbers = "0";
		if (table == null || table.isEmpty() || table.equals("details")) {
			if (userName == null || userName.isEmpty() || userName.equals(null)) {
				twoDList = tableDao.getTable();
			} else {
				twoDList = tableDao.getTableByUser(userName);
			}

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
			request.setAttribute(CommonParameters.TAB_BAR_HOME_COLOR, "aqua");
			request.setAttribute(CommonParameters.REAL_ID, realID);
			request.setAttribute(CommonParameters.PAGE_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.DELETE_COLUMN_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
			request.setAttribute(CommonParameters.USER_LIST, userList);
			request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
			dispatcher = request.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
		} else if (table.equals("history")) {
			dispatcher = request.getRequestDispatcher("History");
			dispatcher.forward(request, response);
		} else if (table.equals("normal")) {
			dispatcher = request.getRequestDispatcher("SortByUser?m=money");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute(CommonParameters.SESSION_USER);
		String moneyS = request.getParameter("money");
		String numberS = request.getParameter("number");
		Boolean flag = true;
		int pageNo = 1;
		int money = Integer.parseInt(moneyS);
		String note = "error";
		int type = 1;
		if(!(numberS.isEmpty())) {
			switch(numberS) {
			case "**":
				int[] arrayP = { 00, 11, 22, 33, 44, 55, 66, 77, 88, 99 };
				tableDao.add2DwithSpecialA(arrayP, money, name, pageNo);
				shortMsg = "apuu ";
				note = "pairs";
				break;
			case "//":
				int[] arraynk = { 07, 70, 18, 81, 24, 42, 35, 53, 69, 96 };
				tableDao.add2DwithSpecialA(arraynk, money, name, pageNo);
				shortMsg = "natkhat ";
				note = "natkhat";
				break;
			case "++":
				int[] arrayPP = { 05, 50, 16, 61, 27, 72, 38, 83, 94, 49 };
				tableDao.add2DwithSpecialA(arrayPP, money, name, pageNo);
				shortMsg = "power ";
				note = "power";
				break;
			case "0+":
				int[] array0bk = { 0, 19, 28, 37, 46, 55, 64, 73, 82, 91 };
				tableDao.add2DwithSpecialA(array0bk, money, name, pageNo);
				shortMsg = "0 bk ";
				note = shortMsg;
				break;
			case "1+":
				int[] array1bk = { 1, 10, 29, 38, 47, 56, 65, 74, 83, 92 };
				tableDao.add2DwithSpecialA(array1bk, money, name, pageNo);
				shortMsg = "1 bk ";
				note = shortMsg;
				break;
			case "2+":
				int[] array2bk = { 2, 11, 20, 39, 48, 57, 66, 75, 84, 93 };
				tableDao.add2DwithSpecialA(array2bk, money, name, pageNo);
				shortMsg = "2 bk ";
				note = shortMsg;
				break;
			case "3+":
				int[] array3bk = { 3, 12, 21, 30, 49, 58, 67, 76, 85, 94 };
				tableDao.add2DwithSpecialA(array3bk, money, name, pageNo);
				shortMsg = "3 bk ";
				note = shortMsg;
				break;
			case "4+":
				int[] array4bk = { 4, 13, 22, 31, 40, 59, 68, 77, 86, 95 };
				tableDao.add2DwithSpecialA(array4bk, money, name, pageNo);
				shortMsg = "4 bk ";
				note = shortMsg;
				break;
			case "5+":
				int[] array5bk = { 5, 14, 23, 32, 41, 50, 69, 78, 87, 96 };
				tableDao.add2DwithSpecialA(array5bk, money, name, pageNo);
				shortMsg = "5 bk ";
				note = shortMsg;
				break;
			case "6+":
				int[] array6bk = { 6, 15, 24, 33, 42, 51, 60, 79, 88, 97 };
				tableDao.add2DwithSpecialA(array6bk, money, name, pageNo);
				shortMsg = "6 bk ";
				note = shortMsg;
				break;
			case "7+":
				int[] array7bk = { 7, 16, 25, 34, 43, 52, 61, 70, 89, 98 };
				tableDao.add2DwithSpecialA(array7bk, money, name, pageNo);
				shortMsg = "7 bk ";
				note = shortMsg;
				break;
			case "8+":
				int[] array8bk = { 8, 17, 26, 35, 44, 53, 62, 71, 80, 99 };
				tableDao.add2DwithSpecialA(array8bk, money, name, pageNo);
				shortMsg = "8 bk ";
				note = shortMsg;
				break;
			case "9+":
				int[] array9bk = { 9, 18, 27, 36, 45, 54, 63, 72, 81, 90 };
				tableDao.add2DwithSpecialA(array9bk, money, name, pageNo);
				shortMsg = "9 bk ";
				note = shortMsg;
				break;
			case "0*":
				int[] array0s = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
				tableDao.add2DwithSpecialA(array0s, money, name, pageNo);
				shortMsg = "0 * ";
				note = shortMsg;
				break;
			case "1*":
				int[] array1s = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
				tableDao.add2DwithSpecialA(array1s, money, name, pageNo);
				shortMsg = "1 * ";
				note = shortMsg;
				break;
			case "2*":
				int[] array2s = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 };
				tableDao.add2DwithSpecialA(array2s, money, name, pageNo);
				shortMsg = "2 * ";
				note = shortMsg;
				break;
			case "3*":
				int[] array3s = { 30, 31, 32, 33, 34, 35, 36, 37, 38, 39 };
				tableDao.add2DwithSpecialA(array3s, money, name, pageNo);
				shortMsg = "3 * ";
				note = shortMsg;
				break;
			case "4*":
				int[] array4s = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49 };
				tableDao.add2DwithSpecialA(array4s, money, name, pageNo);
				shortMsg = "4 * ";
				note = shortMsg;
				break;
			case "5*":
				int[] array5s = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 };
				tableDao.add2DwithSpecialA(array5s, money, name, pageNo);
				shortMsg = "5 * ";
				note = shortMsg;
				break;
			case "6*":
				int[] array6s = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 };
				tableDao.add2DwithSpecialA(array6s, money, name, pageNo);
				shortMsg = "6 * ";
				note = shortMsg;
				break;
			case "7*":
				int[] array7s = { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 };
				tableDao.add2DwithSpecialA(array7s, money, name, pageNo);
				shortMsg = "7 * ";
				note = shortMsg;
				break;
			case "8*":
				int[] array8s = { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 };
				tableDao.add2DwithSpecialA(array8s, money, name, pageNo);
				shortMsg = "8 * ";
				note = shortMsg;
				break;
			case "9*":
				int[] array9s = { 90, 91, 92, 93, 94, 95, 96, 97, 98, 99 };
				tableDao.add2DwithSpecialA(array9s, money, name, pageNo);
				shortMsg = "9 * ";
				note = shortMsg;
				break;
			case "*0":
				int[] array0e = { 0, 10, 20, 30, 40, 50, 60, 70, 80, 90 };
				tableDao.add2DwithSpecialA(array0e, money, name, pageNo);
				shortMsg = "* 0 ";
				note = shortMsg;
				break;
			case "*1":
				int[] array1e = { 1, 11, 21, 31, 41, 51, 61, 71, 81, 91 };
				tableDao.add2DwithSpecialA(array1e, money, name, pageNo);
				shortMsg = "* 1 ";
				note = shortMsg;
				break;
			case "*2":
				int[] array2e = { 2, 12, 22, 32, 42, 52, 62, 72, 82, 92 };
				tableDao.add2DwithSpecialA(array2e, money, name, pageNo);
				shortMsg = "* 2 ";
				note = shortMsg;
				break;
			case "*3":
				int[] array3e = { 3, 13, 23, 33, 43, 53, 63, 73, 83, 93 };
				tableDao.add2DwithSpecialA(array3e, money, name, pageNo);
				shortMsg = "* 3 ";
				note = shortMsg;
				break;
			case "*4":
				int[] array4e = { 4, 14, 24, 34, 44, 54, 64, 74, 84, 94 };
				tableDao.add2DwithSpecialA(array4e, money, name, pageNo);
				shortMsg = "* 4";
				note = shortMsg;
				break;
			case "*5":
				int[] array5e = { 5, 15, 25, 35, 45, 55, 65, 75, 85, 95 };
				tableDao.add2DwithSpecialA(array5e, money, name, pageNo);
				shortMsg = "* 5 ";
				note = shortMsg;
				break;
			case "*6":
				int[] array6e = { 6, 16, 26, 36, 46, 56, 66, 76, 86, 96 };
				tableDao.add2DwithSpecialA(array6e, money, name, pageNo);
				shortMsg = "* 6 ";
				note = shortMsg;
				break;
			case "*7":
				int[] array7e = { 7, 17, 27, 37, 47, 57, 67, 77, 87, 97 };
				tableDao.add2DwithSpecialA(array7e, money, name, pageNo);
				shortMsg = "* 7 ";
				note = shortMsg;
				break;
			case "*8":
				int[] array8e = { 8, 18, 28, 38, 48, 58, 68, 78, 88, 98 };
				tableDao.add2DwithSpecialA(array8e, money, name, pageNo);
				shortMsg = "* 8 ";
				note = shortMsg;
				break;
			case "*9":
				int[] array9e = { 9, 19, 29, 39, 49, 59, 69, 79, 89, 99 };
				tableDao.add2DwithSpecialA(array9e, money, name, pageNo);
				shortMsg = "* 9 ";
				note = shortMsg;
				break;
			case "/*":
				int[] arraybro = { 01, 10, 12, 21, 23, 32, 34, 43, 45, 54,56, 65, 67, 76, 78, 87, 89, 98, 90, 9 };
				tableDao.add2DwithSpecialC(arraybro, money, name, pageNo);
				shortMsg = "nyiko ";
				note = shortMsg;
				type = 3;
				break;
			case "*0*":
				int[] array0p = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90};
				tableDao.add2DwithSpecialB(array0p, money, name, pageNo);
				shortMsg = "0 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*1*":
				int[] array1p = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 1, 21, 31, 41, 51, 61, 71, 81, 91 };
				tableDao.add2DwithSpecialB(array1p, money, name, pageNo);
				shortMsg = "1 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*2*":
				int[] array2p = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 2, 12, 32, 42, 52, 62, 72, 82, 92 };
				tableDao.add2DwithSpecialB(array2p, money, name, pageNo);
				shortMsg = "2 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*3*":
				int[] array3p = {30, 31, 32, 33, 34, 35, 36, 37, 38, 39 , 3, 13, 23, 43, 53, 63, 73, 83, 93 };
				tableDao.add2DwithSpecialB(array3p, money, name, pageNo);
				shortMsg = "3 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*4*":
				int[] array4p = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 4, 14, 24, 34, 54, 64, 74, 84, 94 };
				tableDao.add2DwithSpecialB(array4p, money, name, pageNo);
				shortMsg = "4 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*5*":
				int[] array5p = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 5, 15, 25, 35, 45, 65, 75, 85, 95 };
				tableDao.add2DwithSpecialB(array5p, money, name, pageNo);
				shortMsg = "5 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*6*":
				int[] array6p = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 6, 16, 26, 36, 46, 56, 76, 86, 96 };
				tableDao.add2DwithSpecialB(array6p, money, name, pageNo);
				shortMsg = "6 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*7*":
				int[] array7p = { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 7, 17, 27, 37, 47, 57, 67, 87, 97 };
				tableDao.add2DwithSpecialB(array7p, money, name, pageNo);
				shortMsg = "7 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*8*":
				int[] array8p = { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 8, 18, 28, 38, 48, 58, 68, 78, 98 };
				tableDao.add2DwithSpecialB(array8p, money, name, pageNo);
				shortMsg = "8 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*9*":
				int[] array9p = { 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 9, 19, 29, 39, 49, 59, 69, 79, 89 };
				tableDao.add2DwithSpecialB(array9p, money, name, pageNo);
				shortMsg = "9 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*0**":
				int[] array0pp = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 20, 30, 40, 50, 60, 70, 80, 90};
				tableDao.add2DwithSpecialC(array0pp, money, name, pageNo);
				shortMsg = "0 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*1**":
				int[] array1pp = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 1, 11, 21, 31, 41, 51, 61, 71, 81, 91 };
				tableDao.add2DwithSpecialC(array1pp, money, name, pageNo);
				shortMsg = "1 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*2**":
				int[] array2pp = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 2, 12, 22, 32, 42, 52, 62, 72, 82, 92 };
				tableDao.add2DwithSpecialC(array2pp, money, name, pageNo);
				shortMsg = "2 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*3**":
				int[] array3pp = {30, 31, 32, 33, 34, 35, 36, 37, 38, 39 , 3, 13, 23, 33, 43, 53, 63, 73, 83, 93 };
				tableDao.add2DwithSpecialC(array3pp, money, name, pageNo);
				shortMsg = "3 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*4**":
				int[] array4pp = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 4, 14, 24, 34, 44, 54, 64, 74, 84, 94 };
				tableDao.add2DwithSpecialC(array4pp, money, name, pageNo);
				shortMsg = "4 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*5**":
				int[] array5pp = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 5, 15, 25, 35, 45, 55, 65, 75, 85, 95 };
				tableDao.add2DwithSpecialC(array5pp, money, name, pageNo);
				shortMsg = "5 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*6**":
				int[] array6pp = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 6, 16, 26, 36, 46, 56, 66, 76, 86, 96 };
				tableDao.add2DwithSpecialC(array6pp, money, name, pageNo);
				shortMsg = "6 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*7**":
				int[] array7pp = { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 7, 17, 27, 37, 47, 57, 67, 77, 87, 97 };
				tableDao.add2DwithSpecialC(array7pp, money, name, pageNo);
				shortMsg = "7 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*8**":
				int[] array8pp = { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 8, 18, 28, 38, 48, 58, 68, 78, 88, 98 };
				tableDao.add2DwithSpecialC(array8pp, money, name, pageNo);
				shortMsg = "8 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*9**":
				int[] array9pp = { 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 9, 19, 29, 39, 49, 59, 69, 79, 89, 99 };
				tableDao.add2DwithSpecialC(array9pp, money, name, pageNo);
				shortMsg = "9 apar ";
				note = shortMsg;
				type = 3;
				break;
			default:
				shortMsg = new String();
				shortMsg = "wrong input!Please Enter again!!";
				flag = false;
						
		}
			if(flag == true) {
				String msg = new String();
				msg = shortMsg + " - " + moneyS + " ks added.";
				request.setAttribute(CommonParameters.MESSAGE, msg);

				// for history table
				twoDH = new History2D();
				twoDH.setMoney(money);
				twoDH.setName(name);
				twoDH.setPageNo(pageNo);
				twoDH.setNote(note);
				if(type == 2) {
					twoDH.setTotal(19 * money);
				}
				else if(type == 3) {
					twoDH.setTotal(20 * money);
				}
				else {
					twoDH.setTotal(10 * money);
				}
				
				twoDH.setR("-");
				pageTotal = tableDao.getPageTotal(name, pageNo);
				pageTotal = pageTotal + twoDH.getTotal();
				twoDH.setPageTotal(pageTotal);
				tableDao.add2DtoHistory(twoDH);
				doGet(request, response);
			}
			else {
				request.setAttribute(CommonParameters.MESSAGE, shortMsg);
				doGet(request, response);
			}
			
		}

	}

	protected int getReverse(int number) {
		int reverse = 0;
		if (number < 10) {
			reverse = number * 10;
		} else {
			while (number != 0) {
				int remainder = number % 10;
				reverse = reverse * 10 + remainder;
				number = number / 10;
			}
		}

		return reverse;
	}
}
