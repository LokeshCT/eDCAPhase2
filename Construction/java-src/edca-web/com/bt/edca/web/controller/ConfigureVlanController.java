package com.bt.edca.web.controller;

import com.bt.edca.common.dto.ConfigureVlanDTO;
import com.bt.edca.da.domain.SitePageDomain;
import com.bt.edca.service.ConfigureVlanService;
import com.bt.edca.web.constants.EdcaWebConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Description : This is controller for configureVlan page.
 * 
 * @author Jagankumar G.
 */

@Controller
public class ConfigureVlanController {

  private static final String IS_DISABLED = "isDisabled";

  @Autowired
  private ConfigureVlanService configureVlanService;

  /**
   * Description : Its a getter for configureVlamnService. Returning the object
   * of CPEConfigureService .
   * 
   * @return configureVlanService.
   */
  @RequestMapping(value = { "/configurevlan/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getConfigureVlanDetails(
		  HttpServletRequest request,
	      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,		 
		  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) String serviceInstanceId) {

    List<ConfigureVlanDTO> configureVlanDTOUltraList = new ArrayList<ConfigureVlanDTO>();
    List<SitePageDomain> ultraDetails = new ArrayList<SitePageDomain>();
    String serviceInstance = null;
    Map<String, Object> model = new HashMap<String, Object>();
    configureVlanDTOUltraList = configureVlanService.getConfigureVlanDetails(serviceInstanceId);
    serviceInstance = configureVlanService.getServiceInstance(serviceInstanceId);
    ultraDetails = configureVlanService.getUltraDetails("98176");

    String lastMileAccess = null;
    String accessOptionFlag = null;
    for (SitePageDomain ultraDetailsDomain : ultraDetails) {
      lastMileAccess = ultraDetailsDomain.getLastMileAccess();
      accessOptionFlag = ultraDetailsDomain.getAccessOptionFlag();
    }
    String lblError = null;
    if (("UPPER".equals(serviceInstance)) || ("ULTRA".equalsIgnoreCase(lastMileAccess))
        || ("MTCE Commercial".equals(accessOptionFlag))) {
      lblError = "Y";
    }
    List<ConfigureVlanDTO> sanNameList = configureVlanService.getSanNameDetails(serviceInstanceId);

    String sanName = null;
    int vpnId = 0;
    int sanId = 0;

    for (ConfigureVlanDTO configureVlanDTO : sanNameList) {
      sanName = configureVlanDTO.getSanName();
      sanId = configureVlanDTO.getSanId();

    }

    for (ConfigureVlanDTO vlanDto : configureVlanDTOUltraList) {
      vpnId = vlanDto.getVlanId();
    }

    List<ConfigureVlanDTO> sanValueList = configureVlanService.getSanValue(sanId, vpnId, sanName);

    model.put("lblError", lblError);
    model.put("sanValueList", sanValueList);
    model.put("sanNameList", sanNameList);
    model.put("configureVlanDTOUltraList", configureVlanDTOUltraList);
    model.put(IS_DISABLED, "true");

    return new ModelAndView("configureVlan", model);
  }

  public ConfigureVlanService getConfigureVlanService() {
    return configureVlanService;
  }

  public void setConfigureVlanService(ConfigureVlanService configureVlanService) {
    this.configureVlanService = configureVlanService;
  }

}
