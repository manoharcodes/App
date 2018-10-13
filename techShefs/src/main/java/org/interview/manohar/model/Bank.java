package org.interview.manohar.model;

public class Bank {
	
	String username;
	String password;
	String corpId;
	
	public Bank(){}
	
	public Bank(String username, String password, String corpId) {
		super();
		this.username = username;
		this.password = password;
		this.corpId = corpId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	
	

}
