package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.common.dto.SiteLocationDetailsDTO;
import com.bt.edca.service.SiteLocationsService;
import com.bt.edca.web.constants.EdcaWebConstants;


/**
 * Description : This is controller for siteLocations page. When ever request
 * comes to display the siteLocations details of a user this will be called
 * first. .
 * 
 * @author SATHYA.
 */

@Controller
public class SiteLocationsController {
 
  
  @Autowired
  private SiteLocationsService siteLocationsService;

  /**
   * Description : Its a getter for siteLocationsService. Returning the object of
   * SiteLocationsService .
   * 
   * @return cpeConfigureService .
   */
  public SiteLocationsService getSiteLocationsService() {
    return siteLocationsService;
  }
  /**
   * Description: Its a setter for setting the value of object
   * siteLocationsService .
   * 
   * @param siteLocationsService
   *          .
   */
  public void setSiteLocationsService(SiteLocationsService siteLocationsService) {
    this.siteLocationsService = siteLocationsService;
  }
  

  /**
   * @param siteId.
   * @return model and view object.
   */
  @RequestMapping(value = { "/siteLocations/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getSiteLOcationsDetails(@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
		  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
      @ModelAttribute("siteLocationDetailsDTO") SiteLocationDetailsDTO siteLocationDetailsDTO) {
    List<SiteLocationDetailsDTO> siteLocationDetailsList;
    Map<String, Object> model = new HashMap<String, Object>();
    siteLocationDetailsList = siteLocationsService.getSiteLocationsDetails(siteId);
    model.put("siteLocationDetailsList", siteLocationDetailsList);
    model.put("siteId", siteId);
    return new ModelAndView("siteLocations", model);
  }

 
  
  
  
  

}
