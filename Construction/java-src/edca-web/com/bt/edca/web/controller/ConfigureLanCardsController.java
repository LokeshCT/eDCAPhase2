package com.bt.edca.web.controller;

import com.bt.edca.service.ConfigureLanCardsService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.web.constants.EdcaWebConstants;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Description : Controller Class to return the JSP page using
 * serviceInstanceId.
 * 
 * @author Jagankumnar G.
 *
 */
@Controller
public class ConfigureLanCardsController {

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(OrderSearchController.class);

  /**
   * Service Class for ConfigureLanCardsController.
   */
  @Autowired
  private ConfigureLanCardsService configureLanCardsService;

  /**
   * @return configureLanCardsService.
   */

  public ConfigureLanCardsService getConfigureLanCardsService() {
    return configureLanCardsService;
  }

  public void setConfigureLanCardsService(ConfigureLanCardsService configureLanCardsService) {
    this.configureLanCardsService = configureLanCardsService;
  }

  /**
   * Description : Based on URL it returns jsp page.
   * 
   * @param serviceInstanceId.
   * @return configureLanCards jsp page.
   */

  @RequestMapping(value = { "/configurelancards/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getConfigrueLanCardsDetails(
		  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE) String serviceInstance,
	      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) int siteLocationId,
	      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
		  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
		  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) String serviceInstanceId
		  ,HttpServletRequest request) {
    Map<String, List> customerContactDetailsMap = new HashMap<String, List>();

    Map<String, Object> model = new HashMap<String, Object>();
    customerContactDetailsMap = configureLanCardsService.getConfigureLan(serviceInstanceId);

    System.out.println("SIZE OF MAP" + customerContactDetailsMap.size());

    model.put("configureLanCardsDTO", customerContactDetailsMap);
    model.put(EdcaWebConstants.IS_DISABLED, "true");

    return new ModelAndView("configureLanCards", model);

  }

}