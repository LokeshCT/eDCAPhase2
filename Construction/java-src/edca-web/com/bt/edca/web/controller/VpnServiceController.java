package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.common.dto.VpnServiceDTO;
import com.bt.edca.service.VpnServiceService;
import com.bt.edca.web.constants.EdcaWebConstants;

/** 
 *Description : This is controller for Vpn Service page.
 *              When ever request comes to display the Vpn
 *              Service of a customer ,
 *              this will be called first .
 * @author Rohit .
 */
@Controller
public class VpnServiceController {
  /**
   * Description: Creating the object for VpnServiceService .
   */
    @Autowired
  private VpnServiceService vpnServiceService;

   /**
   * Description : getter for vpnServiceService .
   * .
   * @return vpnServiceService .
   */
  public VpnServiceService getVpnServiceService() {
    return vpnServiceService;
  }
/**
 * Description: Its a setter for setting the value of object vpnServiceService .
 * @param vpnServiceService .
 */
  public void setVpnServiceService(
      VpnServiceService vpnServiceService) {
    this.vpnServiceService = vpnServiceService;
  }

  /**
   * @param vpnServiceId -vpnServiceId value will be passed here .
   * @return ModelAndView.- returning the vpnService page
   *  for displaying the value
   */
  @RequestMapping(
      value = { "/vpnService/{orderId}" }, 
      method = RequestMethod.GET)
  public  ModelAndView getVpnService(@PathVariable(EdcaWebConstants.ORDER_ID) String orderId, 
		  @RequestParam(EdcaWebConstants.VPN_SERVICE_ID) String vpnServiceId) {
    VpnServiceDTO vpnServiceDTO;
    Map<String, Object> model = new HashMap<String, Object>();
    vpnServiceDTO = 
        vpnServiceService.getVpnService(vpnServiceId);
    model.put("vpnServiceDTO", vpnServiceDTO);
    model.put("isDisabled", "true");
    return new ModelAndView("vpnService", model);
  }
}