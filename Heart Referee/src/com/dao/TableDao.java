package com.dao;

import java.util.List;

import com.entity.AllUser2D;
import com.entity.Closed2D;
import com.entity.History2D;
import com.entity.Ledger;
import com.entity.Number2D;
import com.entity.Recover2D;
import com.entity.Summary2D;
import com.entity.User2D;

public interface TableDao {

	// common methods
	
	public int getTotalMoney();

	public int getUserTotalMoney(String name);

	public List<Integer> getDangerousNumber();

	public List<User2D> getUsers();
	
	public int getIdCount();
	
	public int getMoneyToRecoverByLimit(int limit);
	
	
	// normal table methods

	public List<Number2D> getTable();
	
	public List<Number2D> getTableByUser(String name);
	
	public List<Number2D> getNumberDetailsByUser(String name,int number);
	

	// search methods

	public List<Number2D> search2DAmount(int number);
	
	
	// sort methods

	public List<Number2D> sortByNumber();

	public List<Number2D> sortByMoney();

	public List<Number2D> sortByQuantity();
	
	public List<Number2D> sortByUserNumber(String username);

	public List<Number2D> sortByUserMoney(String username);

	public List<Number2D> sortByUserQuantity(String username);
	
	
	// input methods

	public void add2D(int number, int money, String name, int page);

	public void add2DwithR(int number, int rNumber, int money, String name, int page);

	public void add2DwithSpecialA(int[] array, int money, String name, int page);
	
	public void add2DwithSpecialB(int[] array, int money, String name, int page);
	
	public void add2DwithSpecialC(int[] array, int money, String name, int page);

	public void add2DtoHistory(History2D twoD);
	
	
	// history table methods

	public Number2D getNumber(int id);

	public List<Number2D> getHistoryTable();
	
	public List<Number2D> getHistoryTableByUsername(String name);
	
	// delete methods

	public void deleteRow(int id);

	public void deleteTable();
	
	public void deleteUser(String name);
	
	// recover table methods

	public int getMoney(int number);
	
	
	// waiting table methods

	public List<Number2D> filterStart(int start);

	public List<Summary2D> getResultTableByNumber(int number);
	
	public List<Number2D> startList(int start);


	// delete table methods
	
	public int getPageNoById(int id); 

	public List<Number2D> search2DAmountByUser(int number, String name);

	
	// page methods
	
	public int getPageTotal(String name, int page); 

	public int getPageNoByUsername(String name);

	public int getPageById(int id);
	
	
	// final result methods
	
	public int getUserMoneyByNumber(String name,int number);
	
	public List<AllUser2D> getTempTable();
	
	public List<AllUser2D> getTotalTempTable();
	
	public int getTempTotalResult();
	
	// histroy result table methods
	
	public void addValuesToAllTable(Ledger ledger); 
	
	public void addValuesToAllRecoverTable(Recover2D recover2D);
	
	public List<AllUser2D> getAllTableByUser(String username); 
	
	public List<AllUser2D> getTotalAllTableByUser(String username); 
	
	public List<AllUser2D> getUserAllTable();
	
	public List<AllUser2D> getTotalAllTable();
	
	public List<AllUser2D> getTotalTotalAllTable();
	
	public List<AllUser2D> getAllRecoverTable();
	
	public List<AllUser2D> getTotalAllRecoverTable();
	
	public boolean checkNameInTempTable(String username);
	
	public void addUserTempTable(AllUser2D user2D);
	
	public void updateUserTempTable(AllUser2D user2D); 
	
	public void deleteAllTable(); 
	 
	
	// closed number page
	
	public boolean findNumberInClosedNumberTable(int closedNumber); 
	
	public void addNumberInClosedNumberTable(int closedNumber); 
	
	public List<Closed2D> getClosedNumberTable(); 
	
	public void deleteClosedNumberInClosedNumberTable(int closedNumber);
	
}
