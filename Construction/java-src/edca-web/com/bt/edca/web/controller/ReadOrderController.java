package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.util.EdcaLogger;
import com.bt.edca.web.constants.EdcaWebConstants;

/**
 * Description : Redirect the Controller to Readorderpage.
 * This is class is not invoking any service layer, Just for redirection Purpose
 * @author 608479048
 */
@Controller
public class ReadOrderController {

	/**
	 * logger is to display log messages.
	 */
	
	private static final String ORDER_ID =
            "orderId";
	private static final String ORDER_STATUS =
            "orderStatus";
	private static final String READ_ORDER =
            "readOrder";

	private static EdcaLogger logger = EdcaLogger
			.getLogger(ReadOrderController.class);

	/**
	 * this method reDirect the Controller to Read OrderPage
	 */
	@RequestMapping(value = { "/read/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView getOrderDetails(
			@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			@RequestParam(EdcaWebConstants.ORDER_STATUS_CODE) String orderStatus) {

		Map<String, Object> model = new HashMap<String, Object>();

		model.put(ORDER_ID, orderId);
		
		model.put(ORDER_STATUS, orderStatus);

		return new ModelAndView(READ_ORDER, model);

	}	
	
}
