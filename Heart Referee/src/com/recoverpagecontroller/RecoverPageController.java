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

import common.CommonConstants;
import common.CommonParameters;

public class RecoverPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	List<Number2D> twoDList = new ArrayList<Number2D>();
	List<Recover2D> recoverSellerList = new ArrayList<Recover2D>();
	History2D twoDH = null;
	int total;
	int recoverTotal;
	String shortMsg;
	TableDao tableDao;
	RecoverTableDao recoverTableDao;

	public RecoverPageController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);
		
		String sellerName = request.getParameter("sellerName"); 
		
		if(sellerName.equals("Default")) {
			sellerName = recoverTableDao.getSellerName();
		}
		
		total = tableDao.getTotalMoney();
		recoverTotal = recoverTableDao.getTotalRecoverMoney();
		int totalSellerRecover = recoverTableDao.getTotalRecoverMoneyBySeller(sellerName);
		recoverSellerList = recoverTableDao.getRecoverSellerList();
		twoDList = recoverTableDao.getRecoverHistoryTableBySeller(sellerName);
		
		request.setAttribute(CommonParameters.TOTAL_MONEY, total);
		request.setAttribute(CommonParameters.USER_TOTAL_MONEY, recoverTotal);
		request.setAttribute(CommonParameters.TOTAL_SELLER_RECOVER,totalSellerRecover);
		request.setAttribute(CommonParameters.SELLER_NAME, sellerName);
		request.setAttribute(CommonParameters.RECOVER_LIST, recoverSellerList);
		request.setAttribute(CommonParameters.TAB_BAR_RECOVER_NOTE_COLOR, CommonConstants.HOVER_COLOR_CODE);
		request.setAttribute(CommonParameters.NOTE_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.R_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.TOTAL_COLUMN_DISPLAY, "table-cell");
		request.setAttribute(CommonParameters.NUMBER_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
		request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
		dispatcher = request.getRequestDispatcher("/recoverPage");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		tableDao = new TableDaoImpl(request);
		recoverTableDao = new RecoverTableDaoImpl(request);
		
		Boolean flag = true;
		Boolean pairFlag = false;
		String note = "error";
		int type = 1;
		
		String sellerName = request.getParameter("sellerName"); 
		String moneyS = request.getParameter("money");
		String numberS = request.getParameter("number");
		int money = Integer.parseInt(moneyS);
		
		if(!(numberS.isEmpty())) {
			switch(numberS) {
			case "**":
				int[] arrayP = { 00, 11, 22, 33, 44, 55, 66, 77, 88, 99 };
				recoverTableDao.add2DwithSpecialABySeller(arrayP, money,sellerName);
				shortMsg = "pairs ";
				note = "pairs";
				break;
			case "//":
				int[] arraynk = { 07, 70, 18, 81, 24, 42, 35, 53, 69, 96 };
				recoverTableDao.add2DwithSpecialABySeller(arraynk, money,sellerName);
				shortMsg = "natkhat ";
				note = "natkhat";
				break;
			case "++":
				int[] arrayPP = { 05, 50, 16, 61, 27, 72, 38, 83, 94, 49 };
				recoverTableDao.add2DwithSpecialABySeller(arrayPP, money,sellerName);
				shortMsg = "power ";
				note = "power";
				break;
			case "0+":
				int[] array0bk = { 0, 19, 28, 37, 46, 55, 64, 73, 82, 91 };
				recoverTableDao.add2DwithSpecialABySeller(array0bk, money,sellerName);
				shortMsg = "0 bk ";
				note = shortMsg;
				break;
			case "1+":
				int[] array1bk = { 1, 10, 29, 38, 47, 56, 65, 74, 83, 92 };
				recoverTableDao.add2DwithSpecialABySeller(array1bk, money,sellerName);
				shortMsg = "1 bk ";
				note = shortMsg;
				break;
			case "2+":
				int[] array2bk = { 2, 11, 20, 39, 48, 57, 66, 75, 84, 93 };
				recoverTableDao.add2DwithSpecialABySeller(array2bk, money,sellerName);
				shortMsg = "2 bk ";
				note = shortMsg;
				break;
			case "3+":
				int[] array3bk = { 3, 12, 21, 30, 49, 58, 67, 76, 85, 94 };
				recoverTableDao.add2DwithSpecialABySeller(array3bk, money,sellerName);
				shortMsg = "3 bk ";
				note = shortMsg;
				break;
			case "4+":
				int[] array4bk = { 4, 13, 22, 31, 40, 59, 68, 77, 86, 95 };
				recoverTableDao.add2DwithSpecialABySeller(array4bk, money,sellerName);
				shortMsg = "4 bk ";
				note = shortMsg;
				break;
			case "5+":
				int[] array5bk = { 5, 14, 23, 32, 41, 50, 69, 78, 87, 96 };
				recoverTableDao.add2DwithSpecialABySeller(array5bk, money,sellerName);
				shortMsg = "5 bk ";
				note = shortMsg;
				break;
			case "6+":
				int[] array6bk = { 6, 15, 24, 33, 42, 51, 60, 79, 88, 97 };
				recoverTableDao.add2DwithSpecialABySeller(array6bk, money,sellerName);
				shortMsg = "6 bk ";
				note = shortMsg;
				break;
			case "7+":
				int[] array7bk = { 7, 16, 25, 34, 43, 52, 61, 70, 89, 98 };
				recoverTableDao.add2DwithSpecialABySeller(array7bk, money,sellerName);
				shortMsg = "7 bk ";
				note = shortMsg;
				break;
			case "8+":
				int[] array8bk = { 8, 17, 26, 35, 44, 53, 62, 71, 80, 99 };
				recoverTableDao.add2DwithSpecialABySeller(array8bk, money,sellerName);
				shortMsg = "8 bk ";
				note = shortMsg;
				break;
			case "9+":
				int[] array9bk = { 9, 18, 27, 36, 45, 54, 63, 72, 81, 90 };
				recoverTableDao.add2DwithSpecialABySeller(array9bk, money,sellerName);
				shortMsg = "9 bk ";
				note = shortMsg;
				break;
			case "0*":
				int[] array0s = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
				recoverTableDao.add2DwithSpecialABySeller(array0s, money,sellerName);
				shortMsg = "0 * ";
				note = shortMsg;
				break;
			case "1*":
				int[] array1s = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
				recoverTableDao.add2DwithSpecialABySeller(array1s, money,sellerName);
				shortMsg = "1 * ";
				note = shortMsg;
				break;
			case "2*":
				int[] array2s = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 };
				recoverTableDao.add2DwithSpecialABySeller(array2s, money,sellerName);
				shortMsg = "2 * ";
				note = shortMsg;
				break;
			case "3*":
				int[] array3s = { 30, 31, 32, 33, 34, 35, 36, 37, 38, 39 };
				recoverTableDao.add2DwithSpecialABySeller(array3s, money,sellerName);
				shortMsg = "3 * ";
				note = shortMsg;
				break;
			case "4*":
				int[] array4s = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49 };
				recoverTableDao.add2DwithSpecialABySeller(array4s, money,sellerName);
				shortMsg = "4 * ";
				note = shortMsg;
				break;
			case "5*":
				int[] array5s = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 };
				recoverTableDao.add2DwithSpecialABySeller(array5s, money,sellerName);
				shortMsg = "5 * ";
				note = shortMsg;
				break;
			case "6*":
				int[] array6s = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 };
				recoverTableDao.add2DwithSpecialABySeller(array6s, money,sellerName);
				shortMsg = "6 * ";
				note = shortMsg;
				break;
			case "7*":
				int[] array7s = { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 };
				recoverTableDao.add2DwithSpecialABySeller(array7s, money,sellerName);
				shortMsg = "7 * ";
				note = shortMsg;
				break;
			case "8*":
				int[] array8s = { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 };
				recoverTableDao.add2DwithSpecialABySeller(array8s, money,sellerName);
				shortMsg = "8 * ";
				note = shortMsg;
				break;
			case "9*":
				int[] array9s = { 90, 91, 92, 93, 94, 95, 96, 97, 98, 99 };
				recoverTableDao.add2DwithSpecialABySeller(array9s, money,sellerName);
				shortMsg = "9 * ";
				note = shortMsg;
				break;
			case "*0":
				int[] array0e = { 0, 10, 20, 30, 40, 50, 60, 70, 80, 90 };
				recoverTableDao.add2DwithSpecialABySeller(array0e, money,sellerName);
				shortMsg = "* 0 ";
				note = shortMsg;
				break;
			case "*1":
				int[] array1e = { 1, 11, 21, 31, 41, 51, 61, 71, 81, 91 };
				recoverTableDao.add2DwithSpecialABySeller(array1e, money,sellerName);
				shortMsg = "* 1 ";
				note = shortMsg;
				break;
			case "*2":
				int[] array2e = { 2, 12, 22, 32, 42, 52, 62, 72, 82, 92 };
				recoverTableDao.add2DwithSpecialABySeller(array2e, money,sellerName);
				shortMsg = "* 2 ";
				note = shortMsg;
				break;
			case "*3":
				int[] array3e = { 3, 13, 23, 33, 43, 53, 63, 73, 83, 93 };
				recoverTableDao.add2DwithSpecialABySeller(array3e, money,sellerName);
				shortMsg = "* 3 ";
				note = shortMsg;
				break;
			case "*4":
				int[] array4e = { 4, 14, 24, 34, 44, 54, 64, 74, 84, 94 };
				recoverTableDao.add2DwithSpecialABySeller(array4e, money,sellerName);
				shortMsg = "* 4";
				note = shortMsg;
				break;
			case "*5":
				int[] array5e = { 5, 15, 25, 35, 45, 55, 65, 75, 85, 95 };
				recoverTableDao.add2DwithSpecialABySeller(array5e, money,sellerName);
				shortMsg = "* 5 ";
				note = shortMsg;
				break;
			case "*6":
				int[] array6e = { 6, 16, 26, 36, 46, 56, 66, 76, 86, 96 };
				recoverTableDao.add2DwithSpecialABySeller(array6e, money,sellerName);
				shortMsg = "* 6 ";
				note = shortMsg;
				break;
			case "*7":
				int[] array7e = { 7, 17, 27, 37, 47, 57, 67, 77, 87, 97 };
				recoverTableDao.add2DwithSpecialABySeller(array7e, money,sellerName);
				shortMsg = "* 7 ";
				note = shortMsg;
				break;
			case "*8":
				int[] array8e = { 8, 18, 28, 38, 48, 58, 68, 78, 88, 98 };
				recoverTableDao.add2DwithSpecialABySeller(array8e, money,sellerName);
				shortMsg = "* 8 ";
				note = shortMsg;
				break;
			case "*9":
				int[] array9e = { 9, 19, 29, 39, 49, 59, 69, 79, 89, 99 };
				recoverTableDao.add2DwithSpecialABySeller(array9e, money,sellerName);
				shortMsg = "* 9 ";
				note = shortMsg;
				break;
			case "/*":
				int[] arraybro = { 01, 10, 12, 21, 23, 32, 34, 43, 45, 54,56, 65, 67, 76, 78, 87, 89, 98, 90, 9 };
				recoverTableDao.add2DwithSpecialCBySeller(arraybro, money,sellerName);
				shortMsg = "nyiko ";
				note = shortMsg;
				type = 3;
				break;
			case "*0*":
				int[] array0p = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90};
				recoverTableDao.add2DwithSpecialBBySeller(array0p, money,sellerName);
				shortMsg = "0 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*1*":
				int[] array1p = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 1, 21, 31, 41, 51, 61, 71, 81, 91 };
				recoverTableDao.add2DwithSpecialBBySeller(array1p, money,sellerName);
				shortMsg = "1 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*2*":
				int[] array2p = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 2, 12, 32, 42, 52, 62, 72, 82, 92 };
				recoverTableDao.add2DwithSpecialBBySeller(array2p, money,sellerName);
				shortMsg = "2 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*3*":
				int[] array3p = {30, 31, 32, 33, 34, 35, 36, 37, 38, 39 , 3, 13, 23, 43, 53, 63, 73, 83, 93 };
				recoverTableDao.add2DwithSpecialBBySeller(array3p, money,sellerName);
				shortMsg = "3 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*4*":
				int[] array4p = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 4, 14, 24, 34, 54, 64, 74, 84, 94 };
				recoverTableDao.add2DwithSpecialBBySeller(array4p, money,sellerName);
				shortMsg = "4 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*5*":
				int[] array5p = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 5, 15, 25, 35, 45, 65, 75, 85, 95 };
				recoverTableDao.add2DwithSpecialBBySeller(array5p, money,sellerName);
				shortMsg = "5 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*6*":
				int[] array6p = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 6, 16, 26, 36, 46, 56, 76, 86, 96 };
				recoverTableDao.add2DwithSpecialBBySeller(array6p, money,sellerName);
				shortMsg = "6 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*7*":
				int[] array7p = { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 7, 17, 27, 37, 47, 57, 67, 87, 97 };
				recoverTableDao.add2DwithSpecialBBySeller(array7p, money,sellerName);
				shortMsg = "7 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*8*":
				int[] array8p = { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 8, 18, 28, 38, 48, 58, 68, 78, 98 };
				recoverTableDao.add2DwithSpecialBBySeller(array8p, money,sellerName);
				shortMsg = "8 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*9*":
				int[] array9p = { 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 9, 19, 29, 39, 49, 59, 69, 79, 89 };
				recoverTableDao.add2DwithSpecialBBySeller(array9p, money,sellerName);
				shortMsg = "9 apar ";
				note = shortMsg;
				type = 2;
				break;
			case "*0**":
				int[] array0pp = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 20, 30, 40, 50, 60, 70, 80, 90};
				recoverTableDao.add2DwithSpecialCBySeller(array0pp, money,sellerName);
				shortMsg = "0 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*1**":
				int[] array1pp = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 1, 11, 21, 31, 41, 51, 61, 71, 81, 91 };
				recoverTableDao.add2DwithSpecialCBySeller(array1pp, money,sellerName);
				shortMsg = "1 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*2**":
				int[] array2pp = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 2, 12, 22, 32, 42, 52, 62, 72, 82, 92 };
				recoverTableDao.add2DwithSpecialCBySeller(array2pp, money,sellerName);
				shortMsg = "2 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*3**":
				int[] array3pp = {30, 31, 32, 33, 34, 35, 36, 37, 38, 39 , 3, 13, 23, 33, 43, 53, 63, 73, 83, 93 };
				recoverTableDao.add2DwithSpecialCBySeller(array3pp, money,sellerName);
				shortMsg = "3 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*4**":
				int[] array4pp = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 4, 14, 24, 34, 44, 54, 64, 74, 84, 94 };
				recoverTableDao.add2DwithSpecialCBySeller(array4pp, money,sellerName);
				shortMsg = "4 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*5**":
				int[] array5pp = { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 5, 15, 25, 35, 45, 55, 65, 75, 85, 95 };
				recoverTableDao.add2DwithSpecialCBySeller(array5pp, money,sellerName);
				shortMsg = "5 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*6**":
				int[] array6pp = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 6, 16, 26, 36, 46, 56, 66, 76, 86, 96 };
				recoverTableDao.add2DwithSpecialCBySeller(array6pp, money,sellerName);
				shortMsg = "6 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*7**":
				int[] array7pp = { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 7, 17, 27, 37, 47, 57, 67, 77, 87, 97 };
				recoverTableDao.add2DwithSpecialCBySeller(array7pp, money,sellerName);
				shortMsg = "7 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*8**":
				int[] array8pp = { 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 8, 18, 28, 38, 48, 58, 68, 78, 88, 98 };
				recoverTableDao.add2DwithSpecialCBySeller(array8pp, money,sellerName);
				shortMsg = "8 apar ";
				note = shortMsg;
				type = 3;
				break;
			case "*9**":
				int[] array9pp = { 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 9, 19, 29, 39, 49, 59, 69, 79, 89, 99 };
				recoverTableDao.add2DwithSpecialCBySeller(array9pp, money,sellerName);
				shortMsg = "9 apar ";
				note = shortMsg;
				type = 3;
				break;
			default:
				if (numberS.contains("/")) { // Round numbers
					numberS = numberS.replace("/", "");
					type = 5;
					pairFlag = numberS.contains("*");
					numberS = numberS.replace("*", "");

					if (checkAllDigits(numberS)) {
						List<String> strNumberList = generateTwoDigitsCombinations(numberS);
						for (String strNumber : strNumberList) {
							int number = Integer.parseInt(strNumber);
							int rNumber = getReverse(number);
							recoverTableDao.add2DwithR(number, rNumber, money, sellerName);
						}
						shortMsg = numberS + " akhway";

						if (pairFlag) {
							List<String> strPairNumberList = generateAllPairs(numberS);
							for (String strPairNumber : strPairNumberList) {
								int number = Integer.parseInt(strPairNumber);
								recoverTableDao.add2D(number, money,sellerName);								
							}
							shortMsg += " puu";
						}

						note = shortMsg;
					} else {
						shortMsg = "wrong input! Please enter again!!";
						flag = false;
					}
				}else {
					shortMsg = "wrong input!Please Enter again!!";
					flag = false;
				}
						
		}
			if(flag == true) {
				String msg = new String();
				msg = shortMsg + moneyS + " added.";
				request.setAttribute(CommonParameters.MESSAGE, msg);

				// for history table
				twoDH = new History2D();
				twoDH.setMoney(money);
				twoDH.setNote(note);
				if(type == 2) {
					twoDH.setTotal(19 * money);
				}
				else if(type == 3) {
					twoDH.setTotal(20 * money);
				}
				else if(type == 5) {
					List<String> strNumberList = generateTwoDigitsCombinations(numberS);
					List<String> strPairList = new ArrayList<String>();
					if (pairFlag) {
						strPairList = generateAllPairs(numberS);
					}
					twoDH.setTotal(((2 * strNumberList.size()) + strPairList.size()) * money);
				}
				else {
					twoDH.setTotal(10 * money);
				}
				
				twoDH.setR("-");
				twoDH.setName(sellerName);
				recoverTableDao.add2DtoRecoverHistory(twoDH);
			}
			else {
				request.setAttribute(CommonParameters.MESSAGE, shortMsg);
			}
			
			total = tableDao.getTotalMoney();
			recoverTotal = recoverTableDao.getTotalRecoverMoney();
			int totalSellerRecover = recoverTableDao.getTotalRecoverMoneyBySeller(sellerName);
			recoverSellerList = recoverTableDao.getRecoverSellerList();
			twoDList = recoverTableDao.getRecoverHistoryTableBySeller(sellerName);
			
			request.setAttribute(CommonParameters.TOTAL_MONEY, total);
			request.setAttribute(CommonParameters.USER_TOTAL_MONEY, recoverTotal);
			request.setAttribute(CommonParameters.TOTAL_SELLER_RECOVER,totalSellerRecover);
			request.setAttribute(CommonParameters.SELLER_NAME, sellerName);
			request.setAttribute(CommonParameters.RECOVER_LIST, recoverSellerList);
			request.setAttribute(CommonParameters.TAB_BAR_RECOVER_NOTE_COLOR, CommonConstants.HOVER_COLOR_CODE);
			request.setAttribute(CommonParameters.NOTE_COLUMN_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.R_COLUMN_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.TOTAL_COLUMN_DISPLAY, "table-cell");
			request.setAttribute(CommonParameters.NUMBER_COLUMN_DISPLAY, "none");
			request.setAttribute(CommonParameters.QUANTITY_COLUMN_DISPLAY, "none");
			request.setAttribute(CommonParameters.TWO_D_LIST, twoDList);
			dispatcher = request.getRequestDispatcher("/recoverPage");
			dispatcher.forward(request, response);
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
	
	protected boolean checkAllDigits(String str) {
		return str.matches("\\d+");
	}

	protected boolean checkOnlyOneDigit(int number) {
		return (number >= 0 && number <= 9);
	}	

	protected List<String> generateTwoDigitsCombinations(String number) {
		List<String> combinations = new ArrayList<>();

		for (int i = 0; i < number.length() - 1; i++) {
			for (int j = i + 1; j < number.length(); j++) {
				String combination = "" + number.charAt(i) + number.charAt(j);
				combinations.add(combination);
			}
		}

		return combinations;
	}

	protected List<String> generateAllPairs(String number) {
		List<String> combinations = new ArrayList<>();

		for (int i = 0; i < number.length(); i++) {
			String combination = "" + number.charAt(i) + number.charAt(i);
			combinations.add(combination);
		}

		return combinations;
	}
}
