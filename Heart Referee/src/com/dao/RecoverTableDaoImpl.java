package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.driver.DbDriver;
import com.entity.History2D;
import com.entity.Number2D;
import com.entity.Recover2D;
import com.entity.Summary2D;

import common.CommonParameters;

public class RecoverTableDaoImpl implements RecoverTableDao {

	private HttpServletRequest request; // Member variable to store the HttpServletRequest
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	List<Number2D> twoDList = null;
	List<Summary2D> resultList = null;
	Number2D twoD = null;
	Summary2D result2D = null;
	
	public RecoverTableDaoImpl(HttpServletRequest request) {	
	 this.request = request;
	}
	
	@Override
	public int getTotalRecoverMoney() {
		int total = 0;
		String query = "SELECT SUM(MONEY)AS MONEY FROM TWO_D_RECOVER_TABLE WHERE PARTITION = ?";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, partition);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				total = resultSet.getInt("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return total;
	}
	
	@Override
	public void add2D(int number, int money, String seller) { //Check
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY,NAME,PARTITION) VALUES (?,?,?,?)";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			preparedStatement.setInt(2, money);
			preparedStatement.setString(3, seller);
			preparedStatement.setString(4, partition);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add2DwithR(int number, int rNumber, int money,String seller) { //Check
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY,NAME,PARTITION) VALUES (?,?,?,?),(?,?,?,?)";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			preparedStatement.setInt(2, money);
			preparedStatement.setString(3,seller);
			preparedStatement.setString(4, partition);
			preparedStatement.setInt(5, rNumber);
			preparedStatement.setInt(6, money);
			preparedStatement.setString(7,seller);
			preparedStatement.setString(8, partition);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add2DtoRecoverHistory(History2D twoD) { //Check
		String query = "INSERT INTO RECOVER_HISTORY_TABLE(NOTE,MONEY,TOTAL,R,NAME,PARTITION) VALUES (?,?,?,?,?,?)";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, twoD.getNote());
			preparedStatement.setInt(2, twoD.getMoney());
			preparedStatement.setInt(3, twoD.getTotal());
			preparedStatement.setString(4, twoD.getR());
			preparedStatement.setString(5,twoD.getName());
			preparedStatement.setString(6,partition);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void add2DwithSpecialABySeller(int[] array, int money,String seller) { //Check
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY,NAME,PARTITION) VALUES "
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?)";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		int x = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= array.length; i++) {
				preparedStatement.setInt(i + x, array[i - 1]);
				preparedStatement.setInt(i + x + 1, money);
				preparedStatement.setString(i + x + 2, seller);
				preparedStatement.setString(i + x + 3, partition);
				x = x + 3;
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add2DwithSpecialBBySeller(int[] array, int money,String seller) { //Check
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY,NAME,PARTITION) VALUES "
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?)";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		int x = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= array.length; i++) {
				preparedStatement.setInt(i + x, array[i - 1]);
				preparedStatement.setInt(i + x + 1, money);
				preparedStatement.setString(i + x + 2, seller);
				preparedStatement.setString(i + x + 3, partition);
				x = x + 3;
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void add2DwithSpecialCBySeller(int[] array, int money,String seller) { //Check
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY,NAME,PARTITION) VALUES "
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
				+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?)";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		int x = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= array.length; i++) {
				preparedStatement.setInt(i + x, array[i - 1]);
				preparedStatement.setInt(i + x + 1, money);
				preparedStatement.setString(i + x + 2, seller);
				preparedStatement.setString(i + x + 3, partition);
				x = x + 3;
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public List<Number2D> getRecoverHistoryTableBySeller(String seller) { 
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM RECOVER_HISTORY_TABLE WHERE NAME = ? AND PARTITION = ? ORDER BY ID DESC";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, seller);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNote(resultSet.getString("note"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setR(resultSet.getString("r"));
				twoD.setTotal(resultSet.getInt("total"));
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return twoDList;
	}
	
	@Override
	public List<Number2D> getRecoverTableBySeller(String seller) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM TWO_D_RECOVER_TABLE WHERE NAME = ? AND PARTITION = ? ORDER BY ID DESC";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, seller);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(resultSet.getInt("id"));
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return twoDList;
	}
	
	@Override
	public List<Number2D> getRecoverNumberDetails(int number,String seller) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ? AND NAME = ? AND PARTITION = ? ORDER BY ID DESC";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, seller);
			preparedStatement.setString(3, partition);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(resultSet.getInt("id"));
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return twoDList;
	}
	
	@Override
	public  List<Number2D> search2DRecoverAmount(int number,String seller) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT SUM(MONEY) AS money FROM TWO_D_RECOVER_TABLE WHERE NUMBER= ? AND NAME = ? AND PARTITION = ? GROUP BY NUMBER";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, seller);
			preparedStatement.setString(3, partition);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(number);
				twoD.setMoney(resultSet.getInt("money"));	
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}
	
	@Override
	public List<Number2D> sortRecoverByNumber(String seller) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT NUMBER,SUM(MONEY) AS money FROM TWO_D_RECOVER_TABLE WHERE NAME = ? AND PARTITION = ? GROUP BY NUMBER ORDER BY NUMBER";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, seller);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}

	@Override
	public List<Number2D> sortRecoverByMoney(String seller) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT NUMBER,SUM(MONEY) AS money FROM TWO_D_RECOVER_TABLE WHERE NAME = ? AND PARTITION = ? GROUP BY NUMBER ORDER BY MONEY DESC";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, seller);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}
	
	@Override
	public int getRecoverMoney(int number) {
		int money = 0;
		String selectQuery = "SELECT SUM(MONEY) AS money FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ? AND PARTITION = ? GROUP BY NUMBER";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				money = resultSet.getInt("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return money;
	}
	
	@Override
	public Number2D getRecoverById(int id) {
		String query = "SELECT NUMBER,MONEY FROM TWO_D_RECOVER_TABLE WHERE ID = ? AND PARTITION = ?";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoD;
	}

	@Override
	public void deleteRecoverRow(int id) {
		String deleteQuery = "DELETE FROM TWO_D_RECOVER_TABLE WHERE ID = ? AND PARTITION = ?";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, partition);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Recover Seller Table

	@Override
	public List<Recover2D> getRecoverSellerList() {
		List<Recover2D> recoverSellerList = new ArrayList<Recover2D>();
		String query = "SELECT * FROM RECOVER_SELLER_TABLE";
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Recover2D seller = new Recover2D();
				seller.setSellerName(resultSet.getString("seller_name"));
				seller.setSellerCom(resultSet.getInt("seller_com"));
				seller.setSellerZ(resultSet.getInt("seller_z"));
				recoverSellerList.add(seller);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recoverSellerList;
	}
	
	@Override
	public Recover2D getRecoverSellerBySellerName(String sellerName) {
		Recover2D seller = new Recover2D();
		String query = "SELECT * FROM RECOVER_SELLER_TABLE WHERE SELLER_NAME = ?";
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, sellerName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				seller.setSellerCom(resultSet.getInt("seller_com"));
				seller.setSellerZ(resultSet.getInt("seller_z"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return seller;
	}
	
	@Override
	public String getSellerName() {
		String query = "SELECT SELLER_NAME FROM RECOVER_SELLER_TABLE ORDER BY SELLER_Z DESC LIMIT 1";
		String sellerName = "Default"; 
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				sellerName = resultSet.getString("seller_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sellerName;
	}
	
	@Override
	public boolean checkSellerName(String sellerName) {
		boolean flag = false;
		String query = "SELECT * FROM RECOVER_SELLER_TABLE WHERE SELLER_NAME = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, sellerName);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void addSeller(Recover2D seller) {
		String query = "INSERT INTO RECOVER_SELLER_TABLE(SELLER_NAME,SELLER_COM,SELLER_Z) VALUES (?,?,?)";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, seller.getSellerName());
			preparedStatement.setInt(2, seller.getSellerCom());
			preparedStatement.setInt(3, seller.getSellerZ());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSeller(Recover2D seller) {
		String query = "UPDATE RECOVER_SELLER_TABLE SET SELLER_COM = ?,SELLER_Z = ? WHERE SELLER_NAME = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, seller.getSellerCom());
			preparedStatement.setInt(2, seller.getSellerZ());
			preparedStatement.setString(3, seller.getSellerName());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteRecoverSeller(String sellerName) {
		String deleteQuery = "DELETE FROM RECOVER_SELLER_TABLE WHERE SELLER_NAME = ?";
		String deleteQuery1 = "DELETE FROM TWO_D_RECOVER_TABLE WHERE NAME = ? AND PARTITION = ?";
		String deleteQuery2 = "DELETE FROM RECOVER_HISTORY_TABLE WHERE NAME = ? AND PARTITION = ?";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setString(1, sellerName);
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(deleteQuery1);
			preparedStatement.setString(1, sellerName);
			preparedStatement.setString(2, partition);
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(deleteQuery2);
			preparedStatement.setString(1, sellerName);
			preparedStatement.setString(2, partition);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////////////////
	@Override
	public int getTotalRecoverMoneyBySeller(String sellerName) {
		int total = 0;
		String query = "SELECT SUM(MONEY)AS MONEY FROM TWO_D_RECOVER_TABLE WHERE NAME = ? AND PARTITION = ?";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, sellerName);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				total = resultSet.getInt("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return total;
	}

	@Override
	public int getTotalRecoverPBySeller(String sellerName,int number) {
		int p = 0;
		String query = "SELECT SUM(MONEY)AS MONEY FROM TWO_D_RECOVER_TABLE WHERE NAME = ? AND NUMBER = ? AND PARTITION = ?";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, sellerName);
			preparedStatement.setInt(2, number);
			preparedStatement.setString(3, partition);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				p = resultSet.getInt("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}

	

	@Override
	public int getTotalRecoverP(int number) {
		int pTotal = 0;
		String query = "SELECT SUM(MONEY)AS MONEY FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ? AND PARTITION = ?";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				pTotal = resultSet.getInt("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pTotal;
	}

	@Override
	public List<Recover2D> getTotalRecoverPlusMoney(int number) {
		List<Recover2D> recoverList = new ArrayList<Recover2D>();
		String selectQuery = "SELECT NAME,SUM(MONEY) AS MONEY FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ? AND PARTITION = ? GROUP BY NAME";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Recover2D recover = new Recover2D();
				recover.setSellerName(resultSet.getString("name"));
				recover.setSellerMoney(resultSet.getInt("money"));
				recoverList.add(recover);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return recoverList;
	}

	@Override
	public List<Recover2D> getSellerList() {
		List<Recover2D> recoverList = new ArrayList<Recover2D>();
		String selectQuery = "SELECT NAME,SUM(MONEY) AS MONEY FROM TWO_D_RECOVER_TABLE WHERE PARTITION = ? GROUP BY NAME";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, partition);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Recover2D recover = new Recover2D();
				recover.setSellerName(resultSet.getString("name"));
				recover.setSellerMoney(resultSet.getInt("money"));
				recoverList.add(recover);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return recoverList;
	}

	@Override
	public List<Recover2D> getTotalRecoverList() {
		List<Recover2D> recoverList = new ArrayList<Recover2D>();
		String selectQuery = "SELECT NAME,SUM(MONEY) AS MONEY FROM TWO_D_RECOVER_TABLE WHERE PARTITION = ? GROUP BY NAME";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, partition);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Recover2D recover = new Recover2D();
				recover.setSellerName(resultSet.getString("name"));
				recover.setSellerMoney(resultSet.getInt("money"));
				recoverList.add(recover);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return recoverList;
	}
	
	@Override
	public List<Recover2D> getAllTotalRecoverList() {
		List<Recover2D> recoverList = new ArrayList<Recover2D>();
		String selectQuery = "SELECT SUM(MONEY) AS MONEY FROM TWO_D_RECOVER_TABLE WHERE PARTITION = ?";
		
		HttpSession session = request.getSession();	
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, partition);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Recover2D recover = new Recover2D();
				recover.setSellerMoney(resultSet.getInt("money"));
				recoverList.add(recover);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return recoverList;
	}

	
	
	

}
