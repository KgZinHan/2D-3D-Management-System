package com.dao;

import java.util.List;

import com.entity.Comm2D;


public interface CommTableDao {
	
		public List<Comm2D> getCommissionList();
		
		public Comm2D getCommissionByCommName(String commName);
		
		public String getCommName();
		
		public boolean checkCommName(String commName);
		
		public void addCommission(Comm2D comm);
		
		public void updateCommission(Comm2D comm);
		
		public void deleteCommission(String commName);
}
