package com.bt.edca.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.common.dto.OrderDetailsDTO;
import com.bt.edca.common.dto.UserDetailsDTO;
import com.bt.edca.service.OrderSearchService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.helper.OrderSearchType;
import com.bt.edca.web.constants.EdcaWebConstants;

/**
 * This Controller Class returns the OrderDetailslist to the Model and View
 * based on the request mapping URL.
 * 
 * @author Mathivathana
 */

@Controller
public class OrderSearchController {

	/**
	 * logger is to display log messages.
	 */

	private static EdcaLogger logger = EdcaLogger.getLogger(OrderSearchController.class);

	/**
	 * Service Class for OrderSearchController.
	 */

	@Autowired
	private OrderSearchService orderSearchService;

	/**
	 * Gets the OrderSearchService.
	 * 
	 * @return orderSearchService.
	 */
	public OrderSearchService getOrderSearchService() {
		return orderSearchService;
	}

	/**
	 * Sets the orderSearchService.
	 * 
	 * @return orderSearchService.
	 */
	public void setOrderSearchService(OrderSearchService orderSearchService) {
		this.orderSearchService = orderSearchService;
	}

	/**
	 * Based on URL it returns jsp page.
	 * 
	 * @param pageNum.
	 * @return OrderDetails jsp page.
	 */

	@RequestMapping(value = { "/searchOrder/{status}/{pageNum}" }, method = RequestMethod.GET)

	public ModelAndView getOrderDetails(@PathVariable(EdcaWebConstants.PAGE_NUMBER) String pageNum,
			@PathVariable(EdcaWebConstants.ORDER_STATUS) String status, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("UserId");
		String teamId = (String) session.getAttribute("teamId");
		String profileId = (String) session.getAttribute("profileId");
		String productCatagoryName = (String) session.getAttribute("productCatagoryName");		

		List<String> orderStatusList = new ArrayList<String>();

		orderStatusList = OrderSearchType.getDisplayValuesList();

		String codeValue = OrderSearchType.getSearchTypeByDisplayName(status);

		List<OrderDetailsDTO> orderDTOList = new ArrayList<OrderDetailsDTO>();

		orderDTOList = orderSearchService.getOrderDetails(pageNum, codeValue, userId, teamId, profileId,
				productCatagoryName);

		String isDisabled;

		if (status.equalsIgnoreCase(EdcaWebConstants.INVENTORY)) {

			isDisabled = "true";

		} else {

			isDisabled = "false";
		}
		int listSize = orderDTOList.size();
		String totalRows= "1";
		if(listSize > 0)
		{
			totalRows = orderDTOList.get(0).getCnt();
		
		}

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("orderStatusCode", codeValue);

		model.put("OrderStatusTypes", orderStatusList);

		model.put("IsDisabled", isDisabled);

		model.put("TotalRows", totalRows);

		model.put("CurrentIndex", pageNum);

		model.put("OrderType", status);

		model.put("OrderDetailsDTO", orderDTOList);
		
		model.put("listSize", listSize);

		return new ModelAndView("searchOrder", model);

	}

	/**
	 * Based on URL it returns jsp page.
	 * 
	 * @param pageNum.
	 * @return OrderDetails jsp page.
	 */
	
	  @RequestMapping(value = { "/searchOrder" }, method = RequestMethod.POST)
	  public ModelAndView getSearchOrder(@ModelAttribute("userDetailsDTO") UserDetailsDTO userDetailsDTO,
		 HttpServletRequest request, HttpSession session, @RequestParam("User") String UserId) {
      String userId1 = (String) session.getAttribute("userId");
 	  String userId = userDetailsDTO.getUserId();
	  session.setAttribute("userId", userId);
	 return getOrderDetails("1", "Unassigned", request);
	  
	  }
	  
	  /** This is temporary method for GET Requets from ReadOrder Page to
	 * Orderlist. This can be removed after making integratin continue url from
	 * userdetails page.
	 * 
	 * @param pageNum.
	 * 
	 * @return OrderDetails jsp page.
	 */
	@RequestMapping(value = { "/searchOrder" }, method = RequestMethod.GET)
	public ModelAndView getSearchOrderTemp(@ModelAttribute("userDetailsDTO") UserDetailsDTO userDetailsDTO,
			HttpServletRequest request) {
		return getOrderDetails("1", "Unassigned", request);

	}
	

	


}