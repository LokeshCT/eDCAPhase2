package com.bt.edca.web.controller;

import com.bt.edca.common.dto.CustomerDetailsDTO;
import com.bt.edca.common.dto.SitePageDTO;
import com.bt.edca.service.CustomerDetailsService;
import com.bt.edca.util.EdcaLogger;

import com.bt.edca.util.helper.CustomerDetailsType;
import com.bt.edca.web.constants.EdcaWebConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controller Class to return the JSP page using orderId.
 * 
 * @author Niranjan das Basa.
 * 
 *
 */
@Controller
public class CustomerDetailsController {
	/**
	 * Format of Disabled.
	 */

	private static final String IS_DISABLED = "isDisabled";

	/**
	 * Format of CustomerDetailsDTO.
	 */
	private static final String CUSTOMER_DETAILS_DTO = "customerDetailsDTO";

	/**
	 * logger is to display log messages.
	 */
	private static EdcaLogger logger = EdcaLogger.getLogger(OrderSearchController.class);

	/**
	 * Service Class for CustomerDetailsController.
	 */
	@Autowired
	private CustomerDetailsService customerDetailsService;

	/**
	 * @return customerDetailsService.
	 */
	public CustomerDetailsService getCustomerDetailsService() {
		return customerDetailsService;
	}

	/**
	 * @param customerDetailsService.
	 */
	public void setCustomerDetailsService(CustomerDetailsService customerDetailsService) {
		this.customerDetailsService = customerDetailsService;
	}

	/**
	 * Based on URL it returns jsp page.
	 * 
	 * @param orderId.
	 * @return customerDetails jsp page.
	 */
	@RequestMapping(value = { "/customerdetails/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView getCustomerDetails(@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			@ModelAttribute("customerDetailsDTOs") CustomerDetailsDTO customerDetailsDTOs) {

		Map<String, Object> model = new HashMap<String, Object>();

		CustomerDetailsDTO customerDetailsDTO = customerDetailsService.getCustomerDetails(orderId,
				CustomerDetailsType.CUSTOMER);

		List<CustomerDetailsDTO> suborderDetails = customerDetailsService.getSubOrderType(orderId);
		int size = suborderDetails.size();
		List<CustomerDetailsDTO> poaReference = customerDetailsService.getPoaReference(orderId);

		int poaReferencelistsize = poaReference.size();
		model.put("poaList", poaReferencelistsize);
		model.put("size", size);

		model.put(IS_DISABLED, "true");
		model.put("customerDetailList", suborderDetails);
		model.put("poaReference", poaReference);
		model.put("customer", customerDetailsDTO);
		return new ModelAndView("customerDetails", model);
	}

	/**
	 * Based on URL it returns jsp page.
	 * 
	 * @param orderId.
	 * @return infrastructureCustomer jsp page.
	 */
	@RequestMapping(value = { "/infrastructurecustomer/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView getInfrastructureCustomer(@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			@ModelAttribute("customerDetailsDTOs") CustomerDetailsDTO customerDetailsDTOs) {
		Map<String, Object> model = new HashMap<String, Object>();

		CustomerDetailsDTO customerDetailsDTO = customerDetailsService.getCustomerDetails(orderId,
				CustomerDetailsType.CUSTOMER);

		List<CustomerDetailsDTO> suborderDetails = customerDetailsService.getSubOrderType(orderId);
		int size = suborderDetails.size();
		List<CustomerDetailsDTO> poaReference = customerDetailsService.getPoaReference(orderId);

		int poaReferencelistsize = poaReference.size();
		model.put("poaList", poaReferencelistsize);
		model.put("size", size);

		model.put(IS_DISABLED, "true");
		model.put("customerDetailList", suborderDetails);
		model.put("poaReference", poaReference);
		model.put("customer", customerDetailsDTO);

		return new ModelAndView("infrastructureCustomer", model);
	}
	
	@RequestMapping(value = { "/saveCustomerDetails" }, method = RequestMethod.GET)
	public @ResponseBody String saveData(HttpSession session, HttpServletRequest request,
			@ModelAttribute("customer") CustomerDetailsDTO customerDetailsDTO) {
		
		
		String response="Success";
		return response;
	}

	
	
}