package com.bt.edca.web.controller;

import com.bt.edca.common.dto.ConfigureAccessTypeDTO;

import com.bt.edca.service.ConfigureAccessTypeService;
import com.bt.edca.web.constants.EdcaWebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 *Description : This is controller for Configure Access Type page.
 *When ever request comes to display the Configure Access Type of a customer ,
 *this will be called first.
 *@author Rohit.
 */
@Controller
public class ConfigureAccessTypeController {
  /**
   * Description : Creating the object for ConfigureAccessTypeService .
   */
  
  @Autowired
  private ConfigureAccessTypeService configureAccessTypeService;

  /**
   *Description: Getter for configureAccessTypeService.
   * @return configureAccessTypeService-returning the object of 
   * ConfigureAccessTypeService 
   */
  public ConfigureAccessTypeService getConfigureAccessTypeService() {
    return configureAccessTypeService;
  }
/**
 * Description :Its a setter for setting the 
 * value of object
 *  configureAccessTypeService.
 * @param configureAccessTypeService .
 */
  public void setConfigureAccessTypeService(
      ConfigureAccessTypeService configureAccessTypeService) {
    this.configureAccessTypeService = configureAccessTypeService;
  }
  /**
   * 
   * @param serviceInstanceId -service Instance Id value will be passed here.
   * @return ModelAndView.- returning the configureAccessType page
   *  for displaying the value .
   */
  @RequestMapping(
      value = { "/configureAccessType/{orderId}" }, 
      method = RequestMethod.GET)
  public  ModelAndView getConfigureAccessType(@RequestParam(EdcaWebConstants.SERVICE_INSTANCE) String serviceInstance,
	      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) String siteLocationId,
	      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
		  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
		  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) String serviceInstanceId, 
     HttpServletRequest request) {
    ConfigureAccessTypeDTO configureAccessTypeDTO;
    ConfigureAccessTypeDTO configureAccess;
    ConfigureAccessTypeDTO qrefAccess;
    ConfigureAccessTypeDTO aggregated;    
    
    
    Map<String, Object> model = new HashMap<String, Object>();
    configureAccessTypeDTO = 
        configureAccessTypeService.getConfigureAccessType(serviceInstanceId);
        configureAccess = 
        configureAccessTypeService.getConfigureAccessTypeProcedure(
            serviceInstanceId);
    aggregated = 
        configureAccessTypeService.getAggregatedValue(serviceInstanceId);
    qrefAccess = 
        configureAccessTypeService.getQrefValue(serviceInstanceId);
        model.put("configureAccessTypeDTO", configureAccessTypeDTO);
    model.put("configureAccess", configureAccess);
    model.put("aggregated", aggregated);
    model.put("qrefAccess", qrefAccess);
    model.put("isDisabled", "true");
    return new ModelAndView("configureAccessType", model);
  }
}
