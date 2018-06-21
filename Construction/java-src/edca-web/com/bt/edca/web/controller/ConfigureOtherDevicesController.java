package com.bt.edca.web.controller;

import com.bt.edca.common.dto.ConfigureOtherDevicesDTO;
import com.bt.edca.service.ConfigureOtherDeviceService;
import com.bt.edca.util.HelperMethods;
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
 * Description : This is controller for configureOtherDevice page. When ever
 * request comes to display the configureOtherDevice details of a user this will
 * be called first. .
 * 
 * @author SATHYA.
 */

@Controller
public class ConfigureOtherDevicesController {

  private static final String IS_DISABLED = "isDisabled";
  @Autowired

  private ConfigureOtherDeviceService configureOtherDeviceService;

  /**
   * Description : Its a getter for configureOtherDeviceService. Returning the
   * object of ConfigureOtherDeviceService .
   * 
   * @return configureOtherDeviceService.
   */
  public ConfigureOtherDeviceService getConfigureOtherDeviceService() {
    return configureOtherDeviceService;
  }

  /**
   * Description: Its a setter for setting the value of object
   * configureOtherDeviceService .
   * 
   * @param configureOtherDeviceService.
   * 
   */
  public void setConfigureOtherDeviceService(ConfigureOtherDeviceService configureOtherDeviceService) {
    this.configureOtherDeviceService = configureOtherDeviceService;
  }

  /**
   * @param serviceInstanceId.
   * @return model and view object.
   */
  @RequestMapping(value = { "/configureOtherDevice/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getConfigureOtherDetails(
      @ModelAttribute("configureOtherDevicesDTO") ConfigureOtherDevicesDTO configureOtherDevicesDTO,
      HttpServletRequest request,  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE) String serviceInstance,
      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) int siteLocationId,
      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
	  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
	  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) int serviceInstanceId) {
    List<ConfigureOtherDevicesDTO> configure;
    List<ConfigureOtherDevicesDTO> configureList;
    Map<String, Object> model = new HashMap<String, Object>();
    configure = configureOtherDeviceService.getConfigureDetails(serviceInstanceId);
    configureList = configureOtherDeviceService.getSiteDetails(siteId, orderId, serviceInstanceId);
    List<ConfigureOtherDevicesDTO> cpeList = configureOtherDeviceService.getAlldeviceCpe(serviceInstanceId);

    int size = configureOtherDeviceService.size(serviceInstanceId, siteId, orderId);
    request.setAttribute("cpeList", cpeList);
    if (!HelperMethods.isNullOrEmpty(configureList)) {
      String btradianzanalytics = configureList.get(0).getBtradianzanalytics();
      String btradianzmonitor = configureList.get(0).getBtradianzmonitor();

      if (!(btradianzanalytics == null) || !(btradianzmonitor == null)) {
        if ("No".equalsIgnoreCase(btradianzanalytics) || "No".equalsIgnoreCase(btradianzmonitor)) {
          model.put("hideSelectDevice", "hideSelectDevice");
        }
      } else {
        if ((btradianzanalytics == null) || (btradianzmonitor == null)) {
          model.put("hideSelectDevice", "hideSelectDevice");
        }
      }
    }

    model.put("configureList", configureList);
    model.put("configure", configure);
    model.put("configureOtherDevicesDTO", configureOtherDevicesDTO);
    model.put("cpeList", cpeList);
    model.put(IS_DISABLED, "true");
    model.put("size", size);
    return new ModelAndView("configureOtherDevice", model);
  }

}
