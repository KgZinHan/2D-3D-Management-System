package com.entity;

import java.sql.Date;

public class Ledger {
	private Date date;
	private String time;
	private int recoverMoney;
	private int recoverPMoney;
	private int recoverComMoney;
	private int recoverPlusMoney;
	private int extraMoney;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getRecoverMoney() {
		return recoverMoney;
	}

	public void setRecoverMoney(int recoverMoney) {
		this.recoverMoney = recoverMoney;
	}

	public int getRecoverPMoney() {
		return recoverPMoney;
	}

	public void setRecoverPMoney(int recoverPMoney) {
		this.recoverPMoney = recoverPMoney;
	}

	public int getRecoverComMoney() {
		return recoverComMoney;
	}

	public void setRecoverComMoney(int recoverComMoney) {
		this.recoverComMoney = recoverComMoney;
	}

	public int getRecoverPlusMoney() {
		return recoverPlusMoney;
	}

	public void setRecoverPlusMoney(int recoverPlusMoney) {
		this.recoverPlusMoney = recoverPlusMoney;
	}

	public int getExtraMoney() {
		return extraMoney;
	}

	public void setExtraMoney(int extraMoney) {
		this.extraMoney = extraMoney;
	}

}
