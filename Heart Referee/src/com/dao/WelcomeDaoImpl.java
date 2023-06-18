package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.driver.DbDriver;
import com.entity.History2D;
import com.entity.Number2D;

import common.CommonConstants;

public class WelcomeDaoImpl implements WelcomeDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	ArrayList<Number2D> twoDList = null;
	Number2D twoD = null;
	@Override
	public ArrayList<Number2D> getHistoryTableByPage(int page,String seller) {
		twoDList = new ArrayList<Number2D>();
		String query = "SELECT * FROM HISTORY_TABLE WHERE PAGE= ? AND NAME =? ORDER BY ID DESC";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, page);
			preparedStatement.setString(2, seller);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				twoD = new Number2D();
				twoD.setId(99999);
				twoD.setNote(resultSet.getString("note"));
				twoD.setMoney(resultSet.getInt("money"));
				twoD.setR(resultSet.getString("r"));
				twoD.setTotal(resultSet.getInt("total"));
				twoD.setPage(page);
				twoDList.add(twoD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return twoDList;
	}

	@Override
	public int getNewPage() {
		int lastPage = 1;
		String query = "SELECT PAGE FROM HISTORY_TABLE ORDER BY ID DESC LIMIT 1";
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				lastPage = resultSet.getInt("Page") + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastPage;
	}

}
