package org.interview.manohar.model;

import java.util.Date;

public class Transaction {
	
	Date Timestamp;
	Long Amount;
	String type;
	
	public Transaction(Date timestamp, Long amount, String type) {
		super();
		Timestamp = timestamp;
		Amount = amount;
		this.type = type;
	}
	
	

}
