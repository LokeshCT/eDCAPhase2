package com.bt.edca.web.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bt.edca.util.EdcaLogger;


@Controller
public class PageRedirectionController {

	/**
	 * logger is to display log messages.
	 */
	
	private static EdcaLogger logger = EdcaLogger
			.getLogger(ReadOrderController.class);

	/**
	 * this method reDirect the Controller to Select Profile
	 */
	@RequestMapping(value = { "/selectProfile" }, method = RequestMethod.GET)
	public String getSelectProfileDetails(HttpSession session) {		
		 String userId= (String) session.getAttribute("userId");		
        return "redirect:userDetails/"+userId;
	}	

	/**
	 * this method reDirect the Controller to Order List Page
	 */
	@RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
	public String getOrderListDetails() {		
        return "redirect:searchOrder";
	}	
}
