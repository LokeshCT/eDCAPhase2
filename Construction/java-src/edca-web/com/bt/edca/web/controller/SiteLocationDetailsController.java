package com.bt.edca.web.controller;

import com.bt.edca.common.dto.SiteLocationDetailsDTO;
import com.bt.edca.service.SiteLocationDetailsService;
import com.bt.edca.util.EdcaLogger;
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

/**
 * Description: Controller returns to view with model. 
 * @author Jagankumar G.
 */
@Controller
public class SiteLocationDetailsController {
  /**
   * Constant for JSP orderContactDetails.
   */
  private static final String SITE_LOCATION_DETAILS = "siteLocationDetails";
  /**
   * Holds the model for the View.
   */
  private static final String SITE_LOCATION_DETAILS_DTO = "siteLocationDetailsDTO";
  /**
   * All the fields on the view are disabled.
   */
  private static final String IS_DISABLED = "isDisabled";
  /**
   * Boolean value to enable or disable.
   */
  private static final String TRUE = "true";
  /**
   * Captures Logs on to consoles.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(OrderSearchController.class);
  /**
   * @param siteLocationDetailsService  declaration.
   *         
   */
  @Autowired
  private SiteLocationDetailsService siteLocationDetailsService;

  /**
   * Getter for SiteLocationDetailsService. 
   * @return getSiteLocationDetailsService. 
   */
  public SiteLocationDetailsService getSiteLocationDetailsService() {
    return siteLocationDetailsService;
  }

  /**
   * Setter for SiteLocationDetailsService.
   * @param serviceDetailsService.
   */
  public void setSiteLocationDetailsService(SiteLocationDetailsService siteLocationDetailsService) {
    this.siteLocationDetailsService = siteLocationDetailsService;
  }

  /**
   * Description:Method returns to view with model based on siteId.
   * @param siteId.
   * @View siteLocationDetails 
   * @model siteLocationDetailsDTO.
   * @returns view with model.
   */
  @RequestMapping(value = { "/sitelocationdetails/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getSiteLocationDetails(
		  @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) String siteLocationId,
      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
	  @RequestParam(EdcaWebConstants.SITE_ID) String siteId) {

    SiteLocationDetailsDTO siteLocationDetailsDTO;
    Map<String, Object> model = new HashMap<String, Object>();

    siteLocationDetailsDTO = siteLocationDetailsService.getSiteDetails(siteLocationId);
    model.put(SITE_LOCATION_DETAILS_DTO, siteLocationDetailsDTO);
    model.put(IS_DISABLED, TRUE);

    return new ModelAndView(SITE_LOCATION_DETAILS, model);
  }
}