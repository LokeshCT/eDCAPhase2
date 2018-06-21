package com.bt.edca.web.controller;

import com.bt.edca.common.dto.ConfigureAccessDTO;
import com.bt.edca.service.ConfigureAccessService;
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
 * This Controller Class returns the ConfigureAccessDTO to the Model and View
 * based on the request mapping URL.
 * 
 * @author Mathivathana
 */
@Controller
public class ConfigureAccessController {

  /**
   * @param ConfigureAccessService
   *          declaration.
   * 
   */
  @Autowired
  private ConfigureAccessService configureAccessService;

  /**
   * Getter for ConfigureAccessService.
   * 
   * @return getConfigureAccessService.
   */
  public ConfigureAccessService getConfigureAccessService() {
    return configureAccessService;
  }

  /**
   * Setter for ConfigureAccessService.
   * 
   * @param configureAccessService.
   */
  public void setConfigureAccessService(ConfigureAccessService configureAccessService) {
    this.configureAccessService = configureAccessService;
  }

  /**
   * Based on URL it returns jsp page.
   * 
   * @param pageNum.
   * @return OrderDetails jsp page.
   */
  @RequestMapping(value = { "/configureAccess/{orderId}" }, method = RequestMethod.GET)

  public ModelAndView getAccessDetails(@RequestParam(EdcaWebConstants.SERVICE_INSTANCE) String serviceInstance,
      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) int siteLocationId,
      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId, @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
      @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) String serviceInstanceId) {

    ConfigureAccessDTO configureAccessDto = new ConfigureAccessDTO();
    configureAccessDto = configureAccessService.getConfigureAccessDetails(siteId, serviceInstance);
    String gpop = configureAccessDto.getGpop();
    String serviceInst = configureAccessDto.getServiceInstance();

    String gpopColour;
    if (gpop != "None") {
      gpopColour = configureAccessService.getGpopColour(siteLocationId, serviceInst, gpop);
    } else {
      gpopColour = " ";
    }

    Map<String, Object> model = new HashMap<String, Object>();
    model.put(EdcaWebConstants.CONFIGURE_ACCESS_DTO, configureAccessDto);
    model.put(EdcaWebConstants.GPOP_COLOUR, gpopColour);
    model.put(EdcaWebConstants.IS_DISABLED, "true");
    model.put(EdcaWebConstants.SERVICE_INSTANCE, serviceInst);
    return new ModelAndView(EdcaWebConstants.CONFIGURE_ACCESS, model);
  }

}
