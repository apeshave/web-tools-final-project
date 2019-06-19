/**
 * @Author: Aditya Peshave
 * Date: Apr 25, 2013
 */
package com.healthcare.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.DAO.PersonDAO;
import com.healthcare.DAO.UserAccountDAO;
import com.healthcare.DAO.VerificationDAO;
import com.healthcare.pojo.Verification;

/**
 * @author ADi
 *
 */
@Controller
public class EmailVerifyController {

	
	@Autowired
	private UserAccountDAO userAccountDAO;
	
	@Autowired
	private VerificationDAO verificationDAO;
	
	@Autowired
	private PersonDAO personDAO;
	
	
	@RequestMapping(value ="/*.verify", method = RequestMethod.GET)
	public ModelAndView verify(@RequestParam("one")String email, @RequestParam("two")String code) {
	
		ArrayList<Verification> verifications = verificationDAO.getAllVerificationObjects();
		System.out.println("the verification accounts are:"+ verifications.toString());
		System.out.println("\n\n one is"+email);
		System.out.println("\n\n two is"+code);
		
		
		for(Verification verification: verifications)
		{
			if(verification.getPerson().getEmail().equals(email) &&
					verification.getCode().equals(code))
			{
				verification.getUserAccount().setVerified(true);
				userAccountDAO.update(verification.getUserAccount());
				return new ModelAndView("thanks");
			}
			
		}
		return new ModelAndView("failed");
		
	}
	
	
}
