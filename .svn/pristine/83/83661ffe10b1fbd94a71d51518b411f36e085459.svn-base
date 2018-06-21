package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bt.edca.service.VpnPageService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.web.constants.EdcaWebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class VpnPageController.
 * Author : Joseph Peter
 */
@Controller
public class VpnPageController {

	/** The Constant VPN_DETAILS. */
	private static final String VPN_DETAILS = "vpnPage";

	/** The Constant VPN_SITE_DETAILS. */
	private static final String VPN_SITE_DETAILS = "vpnSiteList";
	
	/** The vpn page service. */
	@Autowired
	private VpnPageService vpnPageService;

	/**
	 * Gets the vpn page service.
	 * 
	 * @return the vpn page service
	 */
	public VpnPageService getVpnPageService() {
		return vpnPageService;
	}

	/**
	 * Sets the vpn page service.
	 * 
	 * @param vpnPageService
	 *            the new vpn page service
	 */
	public void setVpnPageService(VpnPageService vpnPageService) {
		this.vpnPageService = vpnPageService;
	}

	/**
	 * Gets the.
	 * 
	 * @param serviceInstanceId
	 *            the service instance id
	 * @return the model and view
	 */
	@RequestMapping(value = { "/vpnPage/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView get(
			HttpServletRequest request,  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE) String serviceInstance,
		      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) int siteLocationId,
		      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
			  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) String serviceInstanceId ) {

		List vpnSiteList = vpnPageService.getVpnDetails(serviceInstanceId);

		Map<String, Object> model = new HashMap<String, Object>();

		model.put(VPN_SITE_DETAILS, vpnSiteList);

		return new ModelAndView(VPN_DETAILS, model);
	}
}
