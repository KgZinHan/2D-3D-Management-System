package com.dao;

import java.util.List;

import com.entity.History2D;
import com.entity.Number2D;

public interface RecoverTableDao {
	public List<Number2D> getRecoverHistoryTable();//common
	
	public int getTotalRecoverMoney();//common
	
	public void add2DwithSpecialA(int[] array, int money);// special A
	
	public void add2DwithSpecialB(int[] array, int money);// special B
	
	public void add2DwithSpecialC(int[] array, int money);// special C 
	
	public void add2DtoRecoverHistory(History2D twoD);// add recover history
	
	public void add2D(int number, int money);// multi & custom
	
	public void add2DwithR(int number, int rNumber, int money);// multi & custom
	
	public List<Number2D> getRecoverTable();// details table
	
	public List<Number2D> getRecoverNumberDetails(int number);//search number details
	
	public  List<Number2D> search2DRecoverAmount(int number); //search total of each
	
	public List<Number2D> sortRecoverByNumber();// sorting number

	public List<Number2D> sortRecoverByMoney();// sorting money
	
	public int getRecoverMoney(int number);//for recover table
	
	public Number2D getRecoverById(int id);// for deleting recover number
	
	public void deleteRecoverRow(int id); // for delete

}
