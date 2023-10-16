package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.driver.DbDriver;
import com.entity.Comm2D;


import common.CommonParameters;

public class CommTableDaoImpl implements CommTableDao {

	private HttpServletRequest request; // Member variable to store the HttpServletRequest

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public CommTableDaoImpl(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public List<Comm2D> getCommissionList() {
		List<Comm2D> commList = new ArrayList<Comm2D>();
		String query = "SELECT * FROM COMM_TABLE WHERE PARTITION = ?";
		
		HttpSession session = request.getSession();
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, partition);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Comm2D comm = new Comm2D();
				comm.setCommName(resultSet.getString("comm_name"));
				comm.setCommPercent(resultSet.getInt("comm_percent"));
				comm.setCommZ(resultSet.getInt("comm_z"));
				commList.add(comm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commList;
	}

	@Override
	public Comm2D getCommissionByCommName(String commName) {
		Comm2D comm = new Comm2D();
		String query = "SELECT * FROM COMM_TABLE WHERE COMM_NAME = ? AND PARTITION = ?";
		
		HttpSession session = request.getSession();
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, commName);
			preparedStatement.setString(2, partition);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				comm.setCommPercent(resultSet.getInt("comm_percent"));
				comm.setCommZ(resultSet.getInt("comm_z"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return comm;
	}

	@Override
	public String getCommName() {
		String query = "SELECT COMM_NAME FROM COMM_TABLE WHERE PARTITION = ? ORDER BY COMM_Z DESC LIMIT 1";
		String commName = "Default";
		
		HttpSession session = request.getSession();
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, partition);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				commName = resultSet.getString("comm_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return commName;
	}

	@Override
	public boolean checkCommName(String commName) {
		boolean flag = false;
		String query = "SELECT * FROM COMM_TABLE WHERE COMM_NAME = ? AND PARTITION = ?";
		
		HttpSession session = request.getSession();
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, commName);
			preparedStatement.setString(2, partition);
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
	public void addCommission(Comm2D comm) {
		String query = "INSERT INTO COMM_TABLE(COMM_NAME,COMM_PERCENT,COMM_Z,PARTITION) VALUES (?,?,?,?)";
		
		HttpSession session = request.getSession();
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, comm.getCommName());
			preparedStatement.setInt(2, comm.getCommPercent());
			preparedStatement.setInt(3, comm.getCommZ());
			preparedStatement.setString(4, partition);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateCommission(Comm2D comm) {
		String query = "UPDATE COMM_TABLE SET COMM_PERCENT = ?,COMM_Z = ? WHERE COMM_NAME = ? AND PARTITION = ?";
		
		HttpSession session = request.getSession();
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);
		
		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, comm.getCommPercent());
			preparedStatement.setInt(2, comm.getCommZ());
			preparedStatement.setString(3, comm.getCommName());
			preparedStatement.setString(4, partition);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCommission(String commName) {
		String deleteQuery = "DELETE FROM COMM_TABLE WHERE COMM_NAME = ? AND PARTITION = ?";
		String deleteQuery1 = "DELETE FROM TWO_D_TABLE WHERE NAME = ? AND PARTITION = ?";
		String deleteQuery2 = "DELETE FROM HISTORY_TABLE WHERE NAME = ? AND PARTITION = ?";

		HttpSession session = request.getSession();
		String partition = (String) session.getAttribute(CommonParameters.SESSION_PARTITION);

		connection = DbDriver.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setString(1, commName);
			preparedStatement.setString(2, partition);	
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(deleteQuery1);
			preparedStatement.setString(1, commName);
			preparedStatement.setString(2, partition);
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(deleteQuery2);
			preparedStatement.setString(1, commName);
			preparedStatement.setString(2, partition);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
