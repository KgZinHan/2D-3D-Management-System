package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.driver.DbDriver;
import com.entity.AllUser2D;
import com.entity.History2D;
import com.entity.Number2D;
import com.entity.Summary2D;
import com.entity.User2D;
import java.text.SimpleDateFormat;

public class TableDaoImpl implements TableDao {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	List<Number2D> twoDList = null;
	List<Summary2D> resultList = null;
	Number2D twoD = null;
	Summary2D result2D = null;

	public TableDaoImpl() {
	}

	@Override
	public List<Number2D> getTable() {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM TWO_D_TABLE ORDER BY ID DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(resultSet.getInt("id"));
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setQuantity(1);
				twoD.setrNumber(0);
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return twoDList;
	}

	@Override
	public List<Number2D> getTableByUser(String name) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM TWO_D_TABLE WHERE NAME = ? ORDER BY ID DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(resultSet.getInt("id"));
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setPage(resultSet.getInt("page"));
				twoD.setQuantity(1);
				twoD.setrNumber(0);
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return twoDList;
	}

	@Override
	public int getTotalMoney() {
		int total = 0;
		String query = "SELECT SUM(MONEY)AS MONEY FROM TWO_D_TABLE";
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
	public int getUserTotalMoney(String name) {
		int total = 0;
		String query = "SELECT SUM(MONEY)AS MONEY FROM TWO_D_TABLE WHERE NAME = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
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
	public List<Number2D> search2DAmount(int number) {
		twoDList = new ArrayList<Number2D>();
		int count = 1;
		int recoverMoney = 0;
		String query = "SELECT NUMBER,SUM(MONEY) AS money,COUNT(NUMBER) AS QUANTITY FROM TWO_D_TABLE WHERE NUMBER= ? GROUP BY NUMBER";
		String query1 = "SELECT SUM(MONEY) AS RMONEY FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ?";
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet1 = null;
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = count + 1;
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setQuantity(resultSet.getInt("quantity"));
				twoD.setCount(count);
				preparedStatement1 = connection.prepareStatement(query1);
				preparedStatement1.setInt(1, twoD.getNumber());
				resultSet1 = preparedStatement1.executeQuery();
				if(resultSet1.next()){
					recoverMoney = resultSet1.getInt("rmoney");
				}
				twoD.setRecoverMoney(recoverMoney);
				twoD.setTotal(twoD.getMoney() - recoverMoney);
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}

	@Override
	public List<Number2D> search2DAmountByUser(int number, String name) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT SUM(MONEY) AS money FROM TWO_D_TABLE WHERE NUMBER= ? AND NAME= ? GROUP BY NUMBER";
		String query1 = "SELECT NUMBER,SUM(MONEY) AS money FROM TWO_D_TABLE WHERE NUMBER= ? GROUP BY NUMBER";
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet1 = null;
		connection = DbDriver.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement1 = connection.prepareStatement(query1);
			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, name);
			preparedStatement1.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			resultSet1 = preparedStatement1.executeQuery();
			while (resultSet1.next()) {
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(resultSet1.getInt("number"));
				twoD.setQuantity(resultSet1.getInt("money"));
				if(resultSet.next()) {
					twoD.setMoney(resultSet.getInt("money"));
				}			
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}

	@Override
	public void add2D(int number, int money, String name, int page) {
		String query = "INSERT INTO TWO_D_TABLE(NUMBER,MONEY,NAME,PAGE) VALUES (?,?,?,?)";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			preparedStatement.setInt(2, money);
			preparedStatement.setString(3, name);
			preparedStatement.setInt(4, page);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add2DwithR(int number, int rNumber, int money, String name, int page) {
		String query = "INSERT INTO TWO_D_TABLE(NUMBER,MONEY,NAME,PAGE) VALUES (?,?,?,?),(?,?,?,?)";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			preparedStatement.setInt(2, money);
			preparedStatement.setString(3, name);
			preparedStatement.setInt(4, page);
			preparedStatement.setInt(5, rNumber);
			preparedStatement.setInt(6, money);
			preparedStatement.setString(7, name);
			preparedStatement.setInt(8, page);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTable() {
		String deleteQuery = "DELETE FROM TWO_D_TABLE WHERE ID >= 0";
		String deleteQuery2 = "DELETE FROM HISTORY_TABLE WHERE ID >= 0";
		String deleteQuery3 = "DELETE FROM TWO_D_RECOVER_TABLE WHERE ID >= 0";
		String deleteQuery4 = "DELETE FROM RECOVER_HISTORY_TABLE WHERE ID >= 0";
		String deleteQuery5 = "DELETE FROM TEMP_TABLE";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(deleteQuery2);
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(deleteQuery3);
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(deleteQuery4);
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(deleteQuery5);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add2DwithSpecialA(int[] array, int money, String name, int page) {
		String query = "INSERT INTO TWO_D_TABLE(NUMBER,MONEY,NAME,PAGE) VALUES (?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?)";
		connection = DbDriver.getConnection();
		int x = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= array.length; i++) {
				preparedStatement.setInt(i + x, array[i - 1]);
				preparedStatement.setInt(i + x + 1, money);
				preparedStatement.setString(i + x + 2, name);
				preparedStatement.setInt(i + x + 3, page);
				x = x + 3;
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add2DwithSpecialB(int[] array, int money, String name, int page) {
		String query = "INSERT INTO TWO_D_TABLE(NUMBER,MONEY,NAME,PAGE) VALUES (?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?)";
		connection = DbDriver.getConnection();
		int x = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= array.length; i++) {
				preparedStatement.setInt(i + x, array[i - 1]);
				preparedStatement.setInt(i + x + 1, money);
				preparedStatement.setString(i + x + 2, name);
				preparedStatement.setInt(i + x + 3, page);
				x = x + 3;
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void add2DwithSpecialC(int[] array, int money, String name, int page) {
		String query = "INSERT INTO TWO_D_TABLE(NUMBER,MONEY,NAME,PAGE) VALUES (?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?)";
		connection = DbDriver.getConnection();
		int x = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= array.length; i++) {
				preparedStatement.setInt(i + x, array[i - 1]);
				preparedStatement.setInt(i + x + 1, money);
				preparedStatement.setString(i + x + 2, name);
				preparedStatement.setInt(i + x + 3, page);
				x = x + 3;
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public List<Number2D> sortByNumber() {
		twoDList = new ArrayList<Number2D>();
		int count = 0;
		int recoverMoney = 0;
		String query = "SELECT NUMBER,SUM(MONEY) AS money,COUNT(NUMBER) AS QUANTITY FROM TWO_D_TABLE GROUP BY NUMBER ORDER BY NUMBER";
		String query1 = "SELECT SUM(MONEY) AS RMONEY FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ?";
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet1 = null;
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = count + 1;
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setQuantity(resultSet.getInt("quantity"));
				twoD.setCount(count);
				preparedStatement1 = connection.prepareStatement(query1);
				preparedStatement1.setInt(1, twoD.getNumber());
				resultSet1 = preparedStatement1.executeQuery();
				if(resultSet1.next()){
					recoverMoney = resultSet1.getInt("rmoney");
				}
				twoD.setRecoverMoney(recoverMoney);
				twoD.setTotal(twoD.getMoney() - recoverMoney);
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}

	@Override
	public List<Number2D> sortByMoney() {
		twoDList = new ArrayList<Number2D>();
		int count = 0;
		int recoverMoney = 0;
		String query = "SELECT NUMBER,SUM(MONEY) AS money,COUNT(NUMBER) AS QUANTITY FROM TWO_D_TABLE GROUP BY NUMBER ORDER BY MONEY DESC";
		String query1 = "SELECT SUM(MONEY) AS RMONEY FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ?";
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet1 = null;
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = count + 1;
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setQuantity(resultSet.getInt("quantity"));
				twoD.setCount(count);
				preparedStatement1 = connection.prepareStatement(query1);
				preparedStatement1.setInt(1, twoD.getNumber());
				resultSet1 = preparedStatement1.executeQuery();
				if(resultSet1.next()){
					recoverMoney = resultSet1.getInt("rmoney");
				}
				twoD.setRecoverMoney(recoverMoney);
				twoD.setTotal(twoD.getMoney() - recoverMoney);
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}
	

	@Override
	public List<Number2D> sortByQuantity() {
		twoDList = new ArrayList<Number2D>();
		int count = 0;
		int recoverMoney = 0;
		String query = "SELECT NUMBER,SUM(MONEY) AS money,COUNT(NUMBER) AS QUANTITY FROM TWO_D_TABLE GROUP BY NUMBER ORDER BY QUANTITY DESC";
		String query1 = "SELECT SUM(MONEY) AS RMONEY FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ?";
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet1 = null;
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = count + 1;
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setCount(count);
				preparedStatement1 = connection.prepareStatement(query1);
				preparedStatement1.setInt(1, twoD.getNumber());
				resultSet1 = preparedStatement1.executeQuery();
				if(resultSet1.next()){
					recoverMoney = resultSet1.getInt("rmoney");
				}
				twoD.setRecoverMoney(recoverMoney);
				twoD.setTotal(twoD.getMoney() - recoverMoney);
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}
	
	public int getRecoverMoney(int number) {
		int recoverMoney = 0;
		String query = "SELECT SUM(MONEY) AS RMONEY FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				recoverMoney = resultSet.getInt("rmoney");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recoverMoney;
	}

	@Override
	public void deleteRow(int id) {
		String deleteQuery = "DELETE FROM TWO_D_TABLE WHERE ID = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getMoney(int number) {
		int rNumberMoney = 0;
		String selectQuery = "SELECT SUM(MONEY) AS money FROM TWO_D_TABLE WHERE NUMBER = ? GROUP BY NUMBER";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				rNumberMoney = resultSet.getInt("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rNumberMoney;
	}

	@Override
	public void add2DtoHistory(History2D twoD) {
		String query = "INSERT INTO HISTORY_TABLE(NOTE,MONEY,TOTAL,R,NAME,PAGE,PAGE_TOTAL) VALUES (?,?,?,?,?,?,?)";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, twoD.getNote());
			preparedStatement.setInt(2, twoD.getMoney());
			preparedStatement.setInt(3, twoD.getTotal());
			preparedStatement.setString(4, twoD.getR());
			preparedStatement.setString(5, twoD.getName());
			preparedStatement.setInt(6, twoD.getPageNo());
			preparedStatement.setInt(7, twoD.getPageTotal());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Number2D> getHistoryTable() {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM HISTORY_TABLE ORDER BY ID DESC";
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
	public Number2D getNumber(int id) {
		Number2D new_2D = new Number2D();
		String selectQuery = "SELECT NUMBER,MONEY FROM TWO_D_TABLE WHERE ID = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				new_2D.setNumber(resultSet.getInt("number"));
				new_2D.setMoney(resultSet.getInt("money"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new_2D;
	}

	@Override
	public List<Integer> getDangerousNumber() {
		List<Integer> dNumberList = new ArrayList<Integer>();
		String selectQuery = "SELECT NUMBER,SUM(MONEY) AS money FROM TWO_D_TABLE GROUP BY NUMBER ORDER BY MONEY DESC LIMIT 10";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				dNumberList.add(resultSet.getInt("number"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dNumberList;
	}

	@Override
	public List<Number2D> getHistoryTableByUsername(String name) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM HISTORY_TABLE WHERE NAME = ? ORDER BY ID DESC";

		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNote(resultSet.getString("note"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setR(resultSet.getString("r"));
				twoD.setTotal(resultSet.getInt("total"));
				twoD.setPage(resultSet.getInt("page"));
				twoD.setPageTotal(resultSet.getInt("page_total"));
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return twoDList;
	}

	public int getPageTotal(String name, int page) {
		int pageTotal = 0;
		String query = "SELECT SUM(TOTAL) AS TOTAL FROM HISTORY_TABLE WHERE NAME=? AND PAGE=?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, page);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				pageTotal = resultSet.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageTotal;
	}

	@Override
	public List<Number2D> filterStart(int start) {
		twoDList = new ArrayList<Number2D>();
		int count = 0;
		int recoverMoney = 0;
		String query = "SELECT NUMBER,SUM(MONEY) AS money,COUNT(NUMBER) AS QUANTITY FROM TWO_D_TABLE WHERE NUMBER >= ? and NUMBER < ? GROUP BY NUMBER ORDER BY NUMBER";
		String query1 = "SELECT SUM(MONEY) AS RMONEY FROM TWO_D_RECOVER_TABLE WHERE NUMBER = ?";
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet1 = null;
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start*10);
			preparedStatement.setInt(2, (start*10) + 10);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = count + 1;
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setQuantity(resultSet.getInt("quantity"));
				twoD.setCount(count);
				preparedStatement1 = connection.prepareStatement(query1);
				preparedStatement1.setInt(1, twoD.getNumber());
				resultSet1 = preparedStatement1.executeQuery();
				if(resultSet1.next()){
					recoverMoney = resultSet1.getInt("rmoney");
				}
				twoD.setRecoverMoney(recoverMoney);
				twoD.setTotal(twoD.getMoney() - recoverMoney);
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}
	
	@Override
	public List<Number2D> startList(int start) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT SUM(MONEY) AS money FROM TWO_D_TABLE WHERE NUMBER = ?";
		connection = DbDriver.getConnection();
		int number = 0;
		number = start * 10;
		for(int i = 0;i< 10;i ++) {
			int money = 0;
			twoD = new Number2D();
			twoD.setId(99999);
			twoD.setNumber(number);
			try {	
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, twoD.getNumber());
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					money = resultSet.getInt("money");
				}
				twoD.setMoney(money);
				number = number + 1;
				twoDList.add(twoD);
				}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return twoDList;
	}

	@Override
	public List<User2D> getUsers() {
		List<User2D> userList = new ArrayList<User2D>();
		String query = "SELECT NAME,SUM(MONEY) AS MONEY FROM TWO_D_TABLE GROUP BY NAME ORDER BY MONEY DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User2D user = new User2D();
				user.setUser(resultSet.getString("name"));
				user.setMoney(resultSet.getInt("money"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public List<Summary2D> getResultTableByNumber(int number) {
		resultList = new ArrayList<Summary2D>();
		String query = "SELECT NAME,SUM(MONEY) AS MONEY FROM TWO_D_TABLE GROUP BY NAME ORDER BY MONEY DESC";
		String query1 = "SELECT SUM(MONEY) AS MONEY FROM TWO_D_TABLE WHERE NUMBER = ? AND NAME=?";
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet1 = null;
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			preparedStatement1 = connection.prepareStatement(query1);
			while (resultSet.next()) {
				result2D = new Summary2D();
				result2D.setUserName(resultSet.getString("name"));
				preparedStatement1.setInt(1, number);
				preparedStatement1.setString(2, resultSet.getString("name"));
				resultSet1 = preparedStatement1.executeQuery();
				if(resultSet1.next()){
					result2D.setMoney(resultSet1.getInt("money"));
				}	
				result2D.setUserMoney(resultSet.getInt("money"));
				resultList.add(result2D);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	@Override
	public int getMoneyToRecoverByLimit(int limit) {
		int recovery = 0;
		String query = "SELECT NUMBER,SUM(MONEY) AS MONEY FROM TWO_D_TABLE GROUP BY NUMBER";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int money = resultSet.getInt("money");
				if (money > limit) {
					recovery = recovery + (money - limit);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recovery;
	}

	@Override
	public List<Number2D> sortByUserNumber(String username) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT NUMBER,SUM(MONEY) AS money,COUNT(NUMBER) AS QUANTITY FROM TWO_D_TABLE WHERE NAME=? GROUP BY NUMBER ORDER BY NUMBER";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setQuantity(resultSet.getInt("quantity"));
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}

	@Override
	public List<Number2D> sortByUserMoney(String username) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT NUMBER,SUM(MONEY) AS money,COUNT(NUMBER) AS QUANTITY FROM TWO_D_TABLE WHERE NAME=? GROUP BY NUMBER ORDER BY MONEY DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setQuantity(resultSet.getInt("quantity"));
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}

	@Override
	public List<Number2D> sortByUserQuantity(String username) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT NUMBER,SUM(MONEY) AS money,COUNT(NUMBER) AS QUANTITY FROM TWO_D_TABLE WHERE NAME=? GROUP BY NUMBER ORDER BY QUANTITY DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setQuantity(resultSet.getInt("quantity"));
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoDList;
	}

	@Override
	public int getPageNoByUsername(String name) {
		int page = 0;
		String query = "SELECT PAGE FROM TWO_D_TABLE WHERE NAME = ? ORDER BY ID DESC LIMIT 1";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				page = resultSet.getInt("page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

	@Override
	public int getPageNoById(int id) {
		int page = 0;
		String query = "SELECT PAGE FROM HISTORY_TABLE WHERE ID=?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				page = resultSet.getInt("page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

	@Override
	public int getPageById(int id) {
		int page = 0;
		String query = "SELECT PAGE FROM TWO_D_TABLE WHERE ID=?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				page = resultSet.getInt("page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

	@Override
	public List<Number2D> getNumberDetailsByUser(String name, int number) {
		List<Number2D> twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM TWO_D_TABLE WHERE NAME = ? AND NUMBER = ? ORDER BY ID DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, number);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(resultSet.getInt("id"));
				twoD.setNumber(resultSet.getInt("number"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setPage(resultSet.getInt("page"));
				twoD.setQuantity(1);
				twoD.setrNumber(0);
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return twoDList;
	}

	@Override
	public int getIdCount() {
		int id = 0;
		String query = "SELECT ID FROM TWO_D_TABLE ORDER BY ID DESC LIMIT 1";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public void deleteUser(String name) {
		String deleteQuery = "DELETE FROM TWO_D_TABLE WHERE NAME = ?";
		String deleteQuery1 = "DELETE FROM HISTORY_TABLE WHERE NAME = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setString(1, name);
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(deleteQuery1);
			preparedStatement.setString(1, name);
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getUserMoneyByNumber(String name,int number) {
		int money = 0;
		String selectQuery = "SELECT SUM(MONEY) AS money FROM TWO_D_TABLE WHERE NAME = ? AND NUMBER = ? GROUP BY NUMBER";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, number);
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
	public void addValuesToAllTable() {
		List<AllUser2D> userList = getTempTable();
		String query = "INSERT INTO ALL_TABLE(NAME,NUMBER,TOTAL_MONEY,P,P_MONEY,COM_PERCENT,COM_MONEY,TOTAL,DATE,TWO_D_TIME) VALUES (?,?,?,?,?,?,?,?,?,?)";
		String checkQuery = "SELECT DATE FROM ALL_TABLE WHERE DATE = ?";
		String time;
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(checkQuery);
			preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				time = "morning";
			}
			else {
				time = "evening";
			}
			
			preparedStatement = connection.prepareStatement(query);
			for(int i =0; i< userList.size(); i++) {
				preparedStatement.setString(1, userList.get(i).getUsername());
				preparedStatement.setInt(2,userList.get(i).getNumber());
				preparedStatement.setInt(3,userList.get(i).getTotalMoney());
				preparedStatement.setInt(4,userList.get(i).getP());
				preparedStatement.setInt(5,userList.get(i).getpMoney());
				preparedStatement.setInt(6,userList.get(i).getComPercent());
				preparedStatement.setInt(7,userList.get(i).getComMoney());
				preparedStatement.setInt(8,userList.get(i).getTotal());
				preparedStatement.setDate(9, Date.valueOf(LocalDate.now()));
				preparedStatement.setString(10, time);
				
				preparedStatement.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<AllUser2D> getAllTableByUser(String username) {
		List<AllUser2D> user2DList = new ArrayList<AllUser2D>();
		String query = "SELECT * FROM ALL_TABLE WHERE NAME = ? ";
		int count = 1;
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AllUser2D user2D = new AllUser2D();
				user2D.setUsername(resultSet.getString("name"));
				user2D.setNumber(resultSet.getInt("number"));
				user2D.setTotalMoney(resultSet.getInt("total_money"));
				user2D.setP(resultSet.getInt("p"));
				user2D.setpMoney(resultSet.getInt("p_money"));
				user2D.setComPercent(resultSet.getInt("com_percent"));
				user2D.setComMoney(resultSet.getInt("com_money"));
				user2D.setTotal(resultSet.getInt("total"));
				user2D.setDate(resultSet.getDate("date"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		        String formattedDate = dateFormat.format(user2D.getDate());
		        user2D.setStringDate(formattedDate);
				user2D.setTime(resultSet.getString("two_d_time"));
				if(user2D.getTotal() <= 0) {
					user2D.setColor("red");
				}
				else {
					user2D.setColor("green");
				}
				user2D.setCount(count);
				count++;
				user2DList.add(user2D);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user2DList;
	}
	
	@Override
	public List<AllUser2D> getTotalAllTableByUser(String username) {
		List<AllUser2D> totalUser2DList = new ArrayList<AllUser2D>();
		String query = "SELECT NAME,SUM(TOTAL_MONEY) AS TOTAL_MONEY,SUM(P) AS P,SUM(P_MONEY) AS P_MONEY,SUM(COM_MONEY) AS COM_MONEY , SUM(TOTAL) AS TOTAL FROM ALL_TABLE WHERE NAME = ? GROUP BY NAME";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AllUser2D user2D = new AllUser2D();
				user2D.setUsername(resultSet.getString("name"));
				user2D.setTotalMoney(resultSet.getInt("total_money"));
				user2D.setP(resultSet.getInt("p"));
				user2D.setpMoney(resultSet.getInt("p_money"));
				user2D.setComMoney(resultSet.getInt("com_money"));
				user2D.setTotal(resultSet.getInt("total"));
				if(user2D.getTotal() <= 0) {
					user2D.setColor("red");
				}
				else {
					user2D.setColor("green");
				}
				totalUser2DList.add(user2D);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalUser2DList;
	}

	@Override
	public void deleteAllTable() {
		String deleteQuery = "DELETE FROM ALL_TABLE";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void addUserTempTable(AllUser2D user2D) {
		String query = "INSERT INTO TEMP_TABLE(NAME,NUMBER,TOTAL_MONEY,P,P_MONEY,COM_PERCENT,COM_MONEY,TOTAL) VALUES (?,?,?,?,?,?,?,?)";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user2D.getUsername());
			preparedStatement.setInt(2,user2D.getNumber());
			preparedStatement.setInt(3, user2D.getTotalMoney());
			preparedStatement.setInt(4, user2D.getP());
			preparedStatement.setInt(5, user2D.getpMoney());
			preparedStatement.setInt(6, user2D.getComPercent());
			preparedStatement.setInt(7, user2D.getComMoney());
			preparedStatement.setInt(8, user2D.getTotal());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean checkNameInTempTable(String username) {
		boolean flag = false;
		String query = "SELECT * FROM TEMP_TABLE WHERE NAME = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void updateUserTempTable(AllUser2D user2D) {
		String query = "UPDATE TEMP_TABLE SET NUMBER = ?,TOTAL_MONEY = ?,P = ?,P_MONEY = ?,COM_PERCENT = ?,COM_MONEY = ?, TOTAL = ? WHERE NAME = ?";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user2D.getNumber());
			preparedStatement.setInt(2, user2D.getTotalMoney());
			preparedStatement.setInt(3, user2D.getP());
			preparedStatement.setInt(4, user2D.getpMoney());
			preparedStatement.setInt(5, user2D.getComPercent());
			preparedStatement.setInt(6, user2D.getComMoney());
			preparedStatement.setInt(7, user2D.getTotal());
			preparedStatement.setString(8, user2D.getUsername());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<AllUser2D> getTempTable() {
		List<AllUser2D> allUser2DList = new ArrayList<AllUser2D>();
		AllUser2D user2D;
		String query = "SELECT * FROM TEMP_TABLE";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user2D = new AllUser2D();
				user2D.setUsername(resultSet.getString("name"));
				user2D.setNumber(resultSet.getInt("number"));
				user2D.setTotalMoney(resultSet.getInt("total_money"));
				user2D.setP(resultSet.getInt("p"));	
				user2D.setpMoney(resultSet.getInt("p_money"));
				user2D.setComPercent(resultSet.getInt("com_percent"));
				user2D.setComMoney(resultSet.getInt("com_money"));
				user2D.setTotal(resultSet.getInt("Total"));
				allUser2DList.add(user2D);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allUser2DList;
	}

	

	

}
