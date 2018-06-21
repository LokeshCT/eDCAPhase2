package com.bt.edca.web.controller;

import com.bt.edca.common.dto.ConfigureCardCableDTO;
import com.bt.edca.service.ConfigureCardCableService;
import com.bt.edca.web.constants.EdcaWebConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
 * Description : This is controller for configureCable page. When ever request
 * comes to display the configureCable details of a user this will be called
 * first. .
 * 
 * @author SATHYA.
 */

@Controller
public class ConfigureCardCableController {

  @Autowired
  private ConfigureCardCableService configureCardCableService;

  /**
   * Description : Its a getter for configureCardCableService. Returning the
   * object of ConfigureCardCableService .
   * 
   * @return configureCardCableService.
   */
  public ConfigureCardCableService getConfigureCardCableService() {
    return configureCardCableService;
  }

  /**
   * Description: Its a setter for setting the value of object
   * configureCardCableService .
   * 
   * @param configureCardCableService.
   * 
   */
  public void setConfigureCardCableService(ConfigureCardCableService configureCardCableService) {
    this.configureCardCableService = configureCardCableService;
  }

  /**
   * @param serviceInstanceId.
   * @return model and view object.
   */
  @RequestMapping(value = { "/configureCableCards/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView cableSelection( HttpServletRequest request,  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE) String serviceInstance,
      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) int siteLocationId,
      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
	  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
	  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) String serviceInstanceId, 
	  @ModelAttribute("configureCardCable ") ConfigureCardCableDTO configureCardCable) {
    ConfigureCardCableDTO configureCardCableDTO = new ConfigureCardCableDTO();
    Map<String, Object> model = new HashMap<String, Object>();
    String configureCable = configureCardCableService.cardCable(serviceInstanceId);
    String baseRouterName = null, pisystem_name = null;
    int p_i_eos_check = 0;
    List<ConfigureCardCableDTO> cards = configureCardCableService.getCardDetails(baseRouterName, pisystem_name,
        p_i_eos_check);
    List<ConfigureCardCableDTO> cableList = configureCardCableService.getMultipleDetails(serviceInstanceId);

    request.setAttribute("cableList", cableList);
    model.put("cableList", cableList);
    model.put("configureCardCableDTO", configureCardCableDTO);
    request.setAttribute("configureCable", configureCable);
    model.put("configureCable", configureCable);
    model.put("cards", cards);
    return new ModelAndView("configureCableCards", model);

  }

}
