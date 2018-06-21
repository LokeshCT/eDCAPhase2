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

import com.bt.edca.common.dto.VpnConnectionDTO;
import com.bt.edca.service.VpnConnectionService;
import com.bt.edca.web.constants.EdcaWebConstants;

/** 
 *Description : This is controller for Vpn Connection page.
 *              When ever request comes to display the Vpn
 *              Connection of a customer ,
 *              this will be called first .
 * @author Rohit .
 */
@Controller
public class VpnConnectionController {
  /**
   * Description: Creating the object for VpnConnectionService .
   */
  
  @Autowired
  private VpnConnectionService vpnConnectionService;

  /**
   * Description : getter for vpnConnectionService .
   * .
   * @return vpnConnectionService .
   */
  public VpnConnectionService getVpnConnectionService() {
    return vpnConnectionService;
  }
/**
 * Description: Its a setter for setting the 
 *               value of object vpnConnectionService .
 * @param vpnConnectionService .
 */
  public void setVpnConnectionService(
      VpnConnectionService vpnConnectionService) {
    this.vpnConnectionService = vpnConnectionService;
  }
  /**
   * @param vpnId -vpnId value will be passed here .
   * @return ModelAndView.- returning the vpnConnection page
   *  for displaying the value
   */
  @RequestMapping(
      value = { "/vpnConnection/{orderId}" }, 
      method = RequestMethod.GET)
  public  ModelAndView getVpnService(		 
	      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,		  
		  @RequestParam(EdcaWebConstants.VPN_ID) String vpnId,
		  HttpServletRequest request) {
    VpnConnectionDTO vpnConnectionDTO;
    Map<String, Object> model = new HashMap<String, Object>();
    vpnConnectionDTO = 
        vpnConnectionService.getVpnConnection(vpnId);
    model.put("vpnConnectionDTO", vpnConnectionDTO);
    model.put("isDisabled", "true");
    return new ModelAndView("vpnConnection", model);
  }
}