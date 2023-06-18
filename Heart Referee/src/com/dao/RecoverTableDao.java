package com.dao;

import java.util.List;

import com.entity.History2D;
import com.entity.Number2D;
import com.entity.Recover2D;

public interface RecoverTableDao {
	public List<Number2D> getRecoverHistoryTableBySeller(String seller);//common
	
	public int getTotalRecoverMoney();//common
	
	public void add2DwithSpecialABySeller(int[] array, int money,String seller);// special A
	
	public void add2DwithSpecialBBySeller(int[] array, int money,String seller);// special B
	
	public void add2DwithSpecialCBySeller(int[] array, int money,String seller);// special C 
	
	public void add2DtoRecoverHistory(History2D twoD);// add recover history
	
	public void add2D(int number, int money,String seller);// multi & custom
	
	public void add2DwithR(int number, int rNumber, int money,String seller);// multi & custom
	
	public List<Number2D> getRecoverTableBySeller(String seller);// details table
	
	public List<Number2D> getRecoverNumberDetails(int number,String seller);//search number details
	
	public  List<Number2D> search2DRecoverAmount(int number,String seller); //search total of each
	
	public List<Number2D> sortRecoverByNumber(String seller);// sorting number

	public List<Number2D> sortRecoverByMoney(String seller);// sorting money
	
	public int getRecoverMoney(int number);//for recover table
	
	public Number2D getRecoverById(int id);// for deleting recover number
	
	public void deleteRecoverRow(int id); // for delete
	
	public List<Recover2D> getRecoverList();
	
	public String getSellerName();
	
	public List<Recover2D> getRecoverSellerList();
	
	public Recover2D getRecoverSellerBySellerName(String sellerName);
	
	public boolean checkSellerName(String sellerName);
	
	public void addSeller(Recover2D seller);
	
	public void updateSeller(Recover2D seller);
	
	public void deleteRecoverSeller(String sellerName);
	
	public List<Recover2D> getTotalRecoverPlusMoney(int number);
	
	public int getTotalRecoverP(int number);
	
	public int getTotalRecoverMoneyBySeller(String sellerName);
	
	public int getTotalRecoverPBySeller(String sellerName,int number);

}
