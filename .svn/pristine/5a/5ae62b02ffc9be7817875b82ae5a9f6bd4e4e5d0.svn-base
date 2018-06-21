package com.bt.edca.web.controller;

import java.util.ArrayList;
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

import com.bt.edca.common.dto.VpnServiceDTO;
import com.bt.edca.service.VPNServicesDetailsService;
import com.bt.edca.web.constants.EdcaWebConstants;

/**
 * This Controller Class returns the VPNServicesDTO to the Model and View based
 * on the request mapping URL.
 * 
 * @author Mathivathana
 */
@Controller
public class VPNServicesDetailsController {

	/**
	 * @param VPNServicesService
	 *            declaration.
	 * 
	 */
	@Autowired
	private VPNServicesDetailsService vpnServicesDetailsService;

	/**
	 * Getter for VPNServicesService.
	 * 
	 * @return getVpnServicesService.
	 */
	public VPNServicesDetailsService getVpnServicesDetailsService() {
		return vpnServicesDetailsService;
	}


	/**
	 * Setter for VPNServicesService.
	 * 
	 * @param vpnServicesService.
	 */
		
	public void setVpnServicesDetailsService(VPNServicesDetailsService vpnServicesDetailsService) {
		this.vpnServicesDetailsService = vpnServicesDetailsService;
	}

	/**
	 * Based on URL it returns jsp page.
	 * 
	 * @param vpnId.
	 * @return vpnServices jsp page.
	 */
	@RequestMapping(value = { "/vpnServices/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView getVPNServices (@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
	  @RequestParam(EdcaWebConstants.VPN_ID) String vpnId,
	  HttpServletRequest request) {
		List<VpnServiceDTO> vpnServicesDTOList = new ArrayList<VpnServiceDTO>();
		vpnServicesDTOList = vpnServicesDetailsService.getVPNServicesDetails(vpnId);
		String connectionName = "B_Composite";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ConnectionName", connectionName);
		model.put("vpnServicesDTO", vpnServicesDTOList);
		return new ModelAndView("vpnServices", model);
	}
}
