package org.interview.manohar.service;

import java.util.*;


import org.interview.manohar.dao.WordDAO;
import org.interview.manohar.model.Bank;
import org.interview.manohar.model.Transaction;
import org.interview.manohar.model.UserAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wordService")
public class WordService {

	@Autowired
	WordDAO wordDao;
	
	
	/*@Transactional
	public List<Words> getAllSynonyms(String name) {
		return wordDao.getAllSynonyms(name);
	}*/
	
	
	
	public List<String> getbanklist() {
		return wordDao.getbanklist();
	}

	
	
	public boolean findUser(String uname, String pass) {
		boolean isValidUser = false;
		isValidUser = wordDao.getValidatedUser(uname,pass);
	    return isValidUser;
	}

	
	public List<Bank> getbankdata(String bankname) {
		return wordDao.getbankdata(bankname);
	}


	public Boolean logintobank(String bankname, Bank bank) {
		return wordDao.logintobank(bankname, bank);
	}


	public boolean registerUser(String username, String pass) {
		return wordDao.registerUser(username,pass);
	}



	public List<UserAccounts> getaccounts(String bankname) {
		return wordDao.getaccounts(bankname);
	}



	public Transaction gettransactiondata(Long accountnumber) {
		return wordDao.gettransactiondata(accountnumber);
	}

}
