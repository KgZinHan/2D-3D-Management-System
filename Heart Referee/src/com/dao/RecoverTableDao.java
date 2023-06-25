package com.dao;

import java.util.List;

import com.entity.History2D;
import com.entity.Number2D;
import com.entity.Recover2D;

public interface RecoverTableDao {
	
	//Common Methods
	
	public int getTotalRecoverMoney();
	
	public void add2D(int number, int money,String seller);// multi & custom insert
	
	public void add2DwithR(int number, int rNumber, int money,String seller);// multi & custom insert
	
	public void add2DtoRecoverHistory(History2D twoD);// recover history insert
	
	public void add2DwithSpecialABySeller(int[] array, int money,String seller);// special A insert
	
	public void add2DwithSpecialBBySeller(int[] array, int money,String seller);// special B insert
	
	public void add2DwithSpecialCBySeller(int[] array, int money,String seller);// special C insert
	
	public List<Number2D> getRecoverHistoryTableBySeller(String seller);//common
	
	public List<Number2D> getRecoverTableBySeller(String seller);// details table
	
	public List<Number2D> getRecoverNumberDetails(int number,String seller);//search number details
	
	public List<Number2D> search2DRecoverAmount(int number,String seller); //search total of each
	
	public List<Number2D> sortRecoverByNumber(String seller);// sorting number

	public List<Number2D> sortRecoverByMoney(String seller);// sorting money
	
	public int getRecoverMoney(int number);//for recover table
	
	public Number2D getRecoverById(int id);// for deleting recover number
	
	public void deleteRecoverRow(int id); // for delete
	
	//Recover Seller Table
	
	public List<Recover2D> getRecoverSellerList();
	
	public Recover2D getRecoverSellerBySellerName(String sellerName);
	
	public String getSellerName();
	
	public boolean checkSellerName(String sellerName);
	
	public void addSeller(Recover2D seller);
	
	public void updateSeller(Recover2D seller);
	
	public void deleteRecoverSeller(String sellerName);
	
	//Recover Ledger
	
	public List<Recover2D> getTotalRecoverPlusMoney(int number);
	
	public int getTotalRecoverP(int number);
	
	public int getTotalRecoverMoneyBySeller(String sellerName);
	
	public int getTotalRecoverPBySeller(String sellerName,int number);
	
	public List<Recover2D> getSellerList();

}
