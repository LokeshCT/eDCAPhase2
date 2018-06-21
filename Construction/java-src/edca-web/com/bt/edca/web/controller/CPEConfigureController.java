package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.common.dto.CPEConfigureDTO;
import com.bt.edca.service.CPEConfigureService;
import com.bt.edca.web.constants.EdcaWebConstants;

/**
 * Description : This is controller for cpeConfigure page. When ever request
 * comes to display the cpeConfigure details of a user this will be called
 * first. .
 * 
 * @author SATHYA.
 */

@Controller
public class CPEConfigureController {

  private static final String IS_DISABLED = "isDisabled";

  @Autowired
  private CPEConfigureService cpeConfigureService;

  /**
   * Description : Its a getter for cpeConfigureService. Returning the object of
   * CPEConfigureService .
   * 
   * @return cpeConfigureService.
   */

  public CPEConfigureService getCpeConfigureService() {
    return cpeConfigureService;
  }

  /**
   * Description: Its a setter for setting the value of object
   * cpeConfigureService .
   * 
   * @param cpeConfigureService
   *          .
   */

  public void setCpeConfigureService(CPEConfigureService cpeConfigureService) {
    this.cpeConfigureService = cpeConfigureService;
  }

  /**
   * @param serviceInstanceId.
   * @return model and view object.
   */
  @RequestMapping(value = { "/cpeConfigure/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getCPEConfigureDetails(@RequestParam(EdcaWebConstants.SERVICE_INSTANCE) String serviceInstance,
	      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) int siteLocationId,
	      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
		  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
		  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) String serviceInstanceId
		  ,HttpServletRequest request) {

    CPEConfigureDTO cPEConfigureDTO = new CPEConfigureDTO();
      
    List<CPEConfigureDTO> cpeConfigureDtoList = null;
   
    Map<String, Object> model = new HashMap<String, Object>();
    cpeConfigureDtoList = cpeConfigureService.getCPEConfigureDetails(serviceInstanceId);
    cPEConfigureDTO = cpeConfigureService.getCPEAccess(serviceInstanceId);
    model.put("cPEConfigureDTOList", cpeConfigureDtoList);
    model.put("ConfigureAccess",cPEConfigureDTO);
    model.put("cPEConfigureDTO", cPEConfigureDTO);
    model.put(IS_DISABLED, "true");
    return new ModelAndView("cpeConfigure", model);
  }
}
