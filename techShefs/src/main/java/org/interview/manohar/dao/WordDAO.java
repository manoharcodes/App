package org.interview.manohar.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.interview.manohar.model.Bank;
import org.interview.manohar.model.Transaction;
import org.interview.manohar.model.UserAccounts;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WordDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	HashMap<String, String> usermap = new HashMap();	
	HashMap<String, List<UserAccounts>> loggedUsers = new HashMap();  // for storing logged in users for a bank
	ArrayList<UserAccounts> allUsers = new ArrayList();
	HashMap<Long, Transaction> transactions = new HashMap();

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public boolean getValidatedUser(String name, String pass) {
		boolean valid = false;
		HashMap<String, String> userData = getAllUsers();
		if(userData.containsKey(name)) {
			String passExisting = userData.get(name);
			if(passExisting.equals(pass)) valid = true;
			
		}
		return valid;
	}
	
	public boolean registerUser(String name, String pass) {
		boolean valid = false;
		HashMap<String, String> userData = getAllUsers();
		if(userData.containsKey(name)) return false;
		else{
			userData.put(name, pass);
			System.out.println(">>>>>> remove it " + getAllUsers());
			return true;
		}
	}

	public List<String> getbanklist() {
		ArrayList<String> bankList = new ArrayList();
		bankList.add("Citi");
		bankList.add("SBI");
		bankList.add("AXIS");
		bankList.add("HDFC");
		bankList.add("ICICI");
		bankList.add("PNB");
		bankList.add("YES BANK");
		
		return bankList;
	}

	public List<Bank> getbankdata(String bankname) {
		HashMap<String, List<Bank>> bankData = getAllData();
		return bankData.get(bankname);
	}

	private HashMap<String, List<Bank>> getAllData() {
		HashMap<String, List<Bank>> map = new HashMap();
		
		ArrayList<Bank> list1  = new ArrayList();
		list1.add(new Bank("Manoj","pass343","12345"));
		list1.add(new Bank("Anil","pass341","12356"));
		
		ArrayList<Bank> list2  = new ArrayList();
		list2.add(new Bank("Shyam","pass232","23456"));
		list2.add(new Bank("Naveen","pass454","23455"));
		
		ArrayList<Bank> list3  = new ArrayList();
		list3.add(new Bank("Ravi","pass34dds","34567"));
		list3.add(new Bank("Shivam","pass787jhh","34565"));
		
		ArrayList<Bank> list4  = new ArrayList();
		list4.add(new Bank("Abhishek","pass2gd","45678"));
		list4.add(new Bank("Manohar","pass787","45676"));
		
		ArrayList<Bank> list5  = new ArrayList();
		list5.add(new Bank("Abhinay","pass3fs","56789"));
		list5.add(new Bank("Rahul","passssd","56787"));
		
		ArrayList<Bank> list6  = new ArrayList();
		list6.add(new Bank("Kamal","pass676","67898"));
		list6.add(new Bank("Ramesh","pass76454","67848"));
		
		map.put("CITI", list1);
		map.put("SBI", list2);
		map.put("AXIS", list3);
		map.put("HDFC", list4);
		map.put("ICICI", list5);
		map.put("PNB", list6);
		return map;
	}

	public Boolean logintobank(String bankname, Bank input) {
		HashMap<String, List<Bank>> bankData = getAllData();
		if(bankData.containsKey(bankname)) {
			List<Bank> list = bankData.get(bankname);
			
			for (Bank b : list) {
				if( (b.getUsername().equals(input.getUsername()))
					&& (b.getPassword().equals(input.getPassword())) 
					&& b.getCorpId().equals(input.getCorpId())) {
					
					saveLogin(bankname, input.getUsername());						
					return true;
				}
			}
			
		}
		return false;
	}
	
	public void saveLogin(String bankName, String username) {
		List<UserAccounts> userList = getAllUserAccounts();
		for (UserAccounts userAccounts : userList) {
			if(userAccounts.getUserName().equals(username)) {
				List accountList = loggedUsers.get(bankName);
				accountList.add(userAccounts);
				loggedUsers.put(bankName, accountList);
			}
		}
	}
	
	public HashMap<String, String> getAllUsers() {
		
		usermap.put( "Manoj", "pass343");
		usermap.put( "Anil", "pass341");
		usermap.put( "Shyam", "pass232");
		usermap.put( "Naveen", "pass454");
		usermap.put( "Ravi", "pass34dds");
		usermap.put( "Shivam", "pass787jhh");
		usermap.put( "Abhishek", "pass2gd");
		usermap.put( "Manohar", "pass787");
		usermap.put( "Abhinay", "pass3fs");
		usermap.put( "Rahul", "passssd");
		usermap.put( "Kamal", "pass676");
		usermap.put( "Ramesh", "pass76454");
		return usermap;
	}

	public List<UserAccounts> getaccounts(String bankname) {
		List loggedUserList = loggedUsers.get(bankname);
		return loggedUserList;
	}
	
	public List<UserAccounts> getAllUserAccounts() {
		
		if(allUsers.isEmpty()) {
		
			allUsers.add(new UserAccounts("savings",3243242333L,"Manoj"));
			allUsers.add(new UserAccounts("savings",6765354354L,"Anil"));
			allUsers.add(new UserAccounts("Current",3534543543L,"Shyam"));
			allUsers.add(new UserAccounts("savings",6767676445L,"Naveen"));
			allUsers.add(new UserAccounts("Current",8768353454L,"Ravi"));
			allUsers.add(new UserAccounts("savings",2423432333L,"Shivam"));
			allUsers.add(new UserAccounts("Current",3432333332L,"Abhishek"));
			allUsers.add(new UserAccounts("Current",6767666677L,"Manohar"));
			allUsers.add(new UserAccounts("savings",6767688867L,"Abhinay"));
			allUsers.add(new UserAccounts("Current",8875656667L,"Rahul"));
			allUsers.add(new UserAccounts("savings",8875656557L,"Kamal"));
			allUsers.add(new UserAccounts("Current",3243242111L,"Ramesh"));
		
		}
		
		return allUsers;
	}

	public Transaction gettransactiondata(Long accountnumber) {
		getTransactions();
		return transactions.get(accountnumber);
	}

	public HashMap getTransactions() {
		if(!transactions.isEmpty()) {
			
			transactions.put(3243242333L, new Transaction(new Date(), 50000L, "Credit"));
			transactions.put(6765354354L, new Transaction(new Date(), 10000L, "Debit"));
			transactions.put(3534543543L, new Transaction(new Date(), 60000L, "Credit"));
			transactions.put(6767676445L, new Transaction(new Date(), 30000L, "Debit"));			
			transactions.put(8768353454L, new Transaction(new Date(), 1000L, "Credit"));
			transactions.put(2423432333L, new Transaction(new Date(), 250000L, "Debit"));
			transactions.put(6767666677L, new Transaction(new Date(), 60000L, "Credit"));
			transactions.put(6767688867L, new Transaction(new Date(), 220000L, "Debit"));
			transactions.put(8875656667L, new Transaction(new Date(), 6000L, "Credit"));
			transactions.put(8875656557L, new Transaction(new Date(), 5000L, "Debit"));
			transactions.put(3243242111L, new Transaction(new Date(), 80000L, "Credit"));		
		}
		return transactions;
	}

}
