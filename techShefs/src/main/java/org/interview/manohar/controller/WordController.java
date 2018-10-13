package org.interview.manohar.controller;

import java.util.HashMap;
import java.util.List;

import org.interview.manohar.model.Bank;
import org.interview.manohar.model.Transaction;
import org.interview.manohar.model.UserAccounts;
import org.interview.manohar.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.interview.manohar.exception.ErrorResponse;
import com.interview.manohar.exception.SynonymException;

@RestController
public class WordController {
	
	@Autowired
	WordService wordService;
	
	@RequestMapping(value = "/api/getbanklist", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<String> getSynonyms() {
		List<String> listOfBanks = wordService.getbanklist();
		return listOfBanks;
	}
	
	
	@RequestMapping(value = "api/getbankdata/{bankname}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Bank> getbankdata(@PathVariable String bankname) {
		List<Bank> bankData = wordService.getbankdata(bankname);
		return bankData;
	}
	

	@RequestMapping(value = "api/logintobank/{bankname}", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity logintobank(@PathVariable String bankname, @RequestBody Bank bank) {
		Boolean valid = wordService.logintobank(bankname, bank);
		System.out.println("Testing... 1" + bank.getCorpId());
		if(!valid) {
			ErrorResponse error = new ErrorResponse();
	         error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
	         error.setMessage("You are not authorized to log into "+ bankname +" - bank");
	         return new ResponseEntity(error, HttpStatus.OK);
		}
		
		return new ResponseEntity("Successfully logged IN", HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/getaccounts/{bankname}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity getaccounts(@PathVariable String bankname) {
		List<UserAccounts> accountList = wordService.getaccounts(bankname);

		if(accountList==null || accountList.isEmpty()) {
			ErrorResponse error = new ErrorResponse();
	         error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
	         error.setMessage("You have not logged previously in "+ bankname +" - bank");
	         return new ResponseEntity(error, HttpStatus.OK);
		}
		
		return new ResponseEntity(accountList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/gettransactiondata/{accountnumber}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity gettransactiondata(@PathVariable Long accountnumber) {
		Transaction trns = wordService.gettransactiondata(accountnumber);
		
		return new ResponseEntity(trns, HttpStatus.OK);
	}	

}
