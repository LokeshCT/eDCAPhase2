package com.bt.edca.web.controller;

import com.bt.edca.service.ConfigureWicnmpaService;
import com.bt.edca.web.constants.EdcaWebConstants;
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
import javax.servlet.http.HttpServletRequest;
/**
 *Description :This is controller for ConfigureWicnmpa page.
 *             When ever request comes to display the ConfigureWicnmpa
 *              of a customer ,
 *             this will be called first.
 * @author Rohit.

 */
@Controller
public class ConfigureWicnmpaController {
   /**
   *Description : creating the object for ConfigureWicnmpaService .
   */
  
 @Autowired
 private ConfigureWicnmpaService configureWicnmpaService;
  /**
   * Description :Getter for configureWicnmpaService.
   * @return configureWicnmpaService-returning the object of 
   * ConfigureWicnmpaService 
   */
  public ConfigureWicnmpaService getConfigureWicnmpaService() {
    return configureWicnmpaService;
  }
/**
 * Description: Its a setter for setting the value of
 *              object configureWicnmpaService.
 * @param configureWicnmpaService .
 */
  public void setConfigureWicnmpaService(
      ConfigureWicnmpaService configureWicnmpaService) {
    this.configureWicnmpaService = configureWicnmpaService;
  }
  /**
   * 
   * @param serviceInstanceId -serviceInstanceId value will be passed here.
   * @return ModelAndView.- returning the configureWicnmpa page
   *  for displaying the value
   */
  @RequestMapping(
      value = { "/configureWicnmpa/{orderId}" }, 
      method = RequestMethod.GET)
   public  ModelAndView getConfigureWicnmpa(
		   @RequestParam(EdcaWebConstants.SERVICE_INSTANCE) String serviceInstance,
		      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) int siteLocationId,
		      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
			  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) String serviceInstanceId
			  ,HttpServletRequest request) {
    Map<String, List> configureWicnmpaDTOList;
    Map<String, List> configureWicnmpaDTO;
    Map<String, Object> model = new HashMap<String, Object>();
    configureWicnmpaDTOList = configureWicnmpaService.getConfigureWicnmpa(
        serviceInstanceId);
    configureWicnmpaDTO = configureWicnmpaService.getCableCardSelection(
        serviceInstanceId);
    model.put("configureWicnmpaDTOList", configureWicnmpaDTOList);
    model.put("cable", configureWicnmpaDTO);
    model.put("isDisabled", "true");
    return new ModelAndView("configureWicnmpa", model);
  }
}