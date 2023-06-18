package com.dao;
import java.util.ArrayList;
import java.util.List;
import com.entity.AllUser2D;
import com.entity.Closed2D;
import com.entity.History2D;
import com.entity.Number2D;
import com.entity.Summary2D;
import com.entity.User2D;

public interface WelcomeDao {
	public ArrayList<Number2D> getHistoryTableByPage(int page,String seller);  
	
	public int getNewPage();
}
