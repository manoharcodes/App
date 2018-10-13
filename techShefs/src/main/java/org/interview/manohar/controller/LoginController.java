package org.interview.manohar.controller;

import javax.servlet.http.HttpServletRequest;

import org.interview.manohar.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interview.manohar.exception.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	WordService wordService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity validateUsr(@RequestParam("username")String username, @RequestParam("password")String password) {
		        String msg = "";
		        
		        System.out.println(" request Data:"+username+","+password);
		        	       
		        boolean isValid = wordService.findUser(username,password);
		 
		        if(!isValid) {
		            msg = "No match found please try again " + username + " already exists, try new username";
			        return new ResponseEntity(msg, HttpStatus.OK);
		        }else msg = "succesfully logged in!";
		 
		        return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity validateUsr2(@RequestParam("username")String username, @RequestParam("password")String password) {
		        String msg = "";
		        
		        System.out.println(" request Data:"+username+","+password);
		        	       
		        boolean isValid = wordService.registerUser(username,password);
		 
		        if(!isValid) {
		            msg = "User with username - " + username + " already exists, try new username";
			        return new ResponseEntity(msg, HttpStatus.OK);
		        }else msg = "succesfully registered!";
		 
		        return new ResponseEntity(msg, HttpStatus.OK);
	}


}
