package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.driver.DbDriver;
import com.entity.History2D;
import com.entity.Number2D;
import com.entity.Summary2D;

public class RecoverTableDaoImpl implements RecoverTableDao {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	List<Number2D> twoDList = null;
	List<Summary2D> resultList = null;
	Number2D twoD = null;
	Summary2D result2D = null;

	public RecoverTableDaoImpl() {
	}

	@Override
	public List<Number2D> getRecoverHistoryTable() {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM RECOVER_HISTORY_TABLE ORDER BY ID DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
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
	public int getTotalRecoverMoney() {
		int total = 0;
		String query = "SELECT SUM(MONEY)AS MONEY FROM TWO_D_RECOVER_TABLE";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
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
	public void add2DwithSpecialA(int[] array, int money) {
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY) VALUES (?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?)";
		connection = DbDriver.getConnection();
		int x = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= array.length; i++) {
				preparedStatement.setInt(i + x, array[i - 1]);
				preparedStatement.setInt(i + x + 1, money);
				x = x + 1;
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add2DwithSpecialB(int[] array, int money) {
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY) VALUES (?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?)";
		connection = DbDriver.getConnection();
		int x = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= array.length; i++) {
				preparedStatement.setInt(i + x, array[i - 1]);
				preparedStatement.setInt(i + x + 1, money);
				x = x + 1;
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void add2DwithSpecialC(int[] array, int money) {
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY) VALUES (?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?),(?,?)";
		connection = DbDriver.getConnection();
		int x = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= array.length; i++) {
				preparedStatement.setInt(i + x, array[i - 1]);
				preparedStatement.setInt(i + x + 1, money);
				x = x + 1;
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void add2DtoRecoverHistory(History2D twoD) {
		String query = "INSERT INTO RECOVER_HISTORY_TABLE(NOTE,MONEY,TOTAL,R) VALUES (?,?,?,?)";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, twoD.getNote());
			preparedStatement.setInt(2, twoD.getMoney());
			preparedStatement.setInt(3, twoD.getTotal());
			preparedStatement.setString(4, twoD.getR());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void add2D(int number, int money) {
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY) VALUES (?,?)";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			preparedStatement.setInt(2, money);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add2DwithR(int number, int rNumber, int money) {
		String query = "INSERT INTO TWO_D_RECOVER_TABLE(NUMBER,MONEY) VALUES (?,?),(?,?)";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			preparedStatement.setInt(2, money);
			preparedStatement.setInt(3, rNumber);
			preparedStatement.setInt(4, money);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Number2D> getRecoverTable() {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM TWO_D_RECOVER_TABLE ORDER BY ID DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
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
	public List<Number2D> getRecoverNumberDetails(int number) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ? ORDER BY ID DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
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
	public  List<Number2D> search2DRecoverAmount(int number) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT SUM(MONEY) AS money FROM TWO_D_RECOVER_TABLE WHERE NUMBER= ? GROUP BY NUMBER";
		connection = DbDriver.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
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
	public List<Number2D> sortRecoverByNumber() {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT NUMBER,SUM(MONEY) AS money FROM TWO_D_RECOVER_TABLE GROUP BY NUMBER ORDER BY NUMBER";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
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
	public List<Number2D> sortRecoverByMoney() {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT NUMBER,SUM(MONEY) AS money FROM TWO_D_RECOVER_TABLE GROUP BY NUMBER ORDER BY MONEY DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
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
		String selectQuery = "SELECT SUM(MONEY) AS money FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ? GROUP BY NUMBER";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, number);
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
		String query = "SELECT NUMBER,MONEY FROM TWO_D_RECOVER_TABLE WHERE ID = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
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
		String deleteQuery = "DELETE FROM TWO_D_RECOVER_TABLE WHERE ID = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
