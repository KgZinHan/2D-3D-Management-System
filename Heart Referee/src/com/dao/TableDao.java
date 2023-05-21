package com.dao;

import java.util.List;

import com.entity.History2D;
import com.entity.Number2D;
import com.entity.Summary2D;
import com.entity.User2D;

public interface TableDao {

	public int getTotalMoney();// common

	public int getUserTotalMoney(String name);// common

	public List<Integer> getDangerousNumber();// common

	public List<User2D> getUsers();// common
	
	public int getIdCount();// common

	public List<Number2D> getTable();// for normal table

	public List<Number2D> search2DAmount(int number);// for searching 2D

	public List<Number2D> sortByNumber();// sorting

	public List<Number2D> sortByMoney();// sorting

	public List<Number2D> sortByQuantity();// sorting

	public void add2D(int number, int money, String name, int page);// single
																	// input

	public void add2DwithR(int number, int rNumber, int money, String name, int page);// single

	public void add2DwithSpecialA(int[] array, int money, String name, int page);// special A
	
	public void add2DwithSpecialB(int[] array, int money, String name, int page);// special B
	
	public void add2DwithSpecialC(int[] array, int money, String name, int page);// special C

	public void add2DtoHistory(History2D twoD);// add single input to history

	public Number2D getNumber(int id);// for history table

	public List<Number2D> getHistoryTable();// get history table

	public void deleteRow(int id);// delete single input

	public void deleteTable();// delete all tables
	
	public void deleteUser(String name); // delete the user

	public int getMoney(int number);// for recover table

	public List<Number2D> filterStart(int start);// for waiting table

	public List<Summary2D> getResultTableByNumber(int number);

	public int getMoneyToRecoverByLimit(int limit);// common

	public List<Number2D> getTableByUser(String name);// for normal table

	public List<Number2D> getHistoryTableByUsername(String name);// get history

	public int getPageTotal(String name, int page);

	public int getPageNoById(int id); // for delete table

	public List<Number2D> search2DAmountByUser(int number, String name);

	public List<Number2D> sortByUserNumber(String username);// sorting

	public List<Number2D> sortByUserMoney(String username);// sorting

	public List<Number2D> sortByUserQuantity(String username);// sorting

	public int getPageNoByUsername(String name);// get history

	public int getPageById(int id);// get delete
	
	public List<Number2D> getNumberDetailsByUser(String name,int number);// for normal table
	
	public List<Number2D> startList(int start);
	
	public int getUserMoneyByNumber(String name,int number);
	
	
}
