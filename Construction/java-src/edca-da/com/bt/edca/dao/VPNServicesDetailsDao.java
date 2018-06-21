package com.bt.edca.dao;



import com.bt.edca.da.domain.VpnServiceDomain;

import java.util.List;

/**
 * This interface calls the DB function with appropriate IN and OUT parameters.
 * Gets the VPN Services Details.
 * 
 * @author Mathivathana
 */
public interface VPNServicesDetailsDao {
	
	/**
	   * Format to get serviceName based on VPNserviceID.
	   */
	String  SERVICENAME_FUNC ="{?= call NVL(DD_DCA_PK.CONCAT_SERVICENAME(?),'N/A')}";
	

	/**
	   * Format to get VPNServiceId.
	   */
	String GET_VPN_SERVICEID = "getVPNServiceId";
	

	/**
	   * Format to getVPNServicesDetails based on vpnId
	   */
	String GET_VPN_SERVICES_DETAILS = "getVPNServicesDetails";
	
	 
	  /**
	   * This methods gets the VPNServicesDetails with parameters
	   * @param vpnID.
	   * @return List.
	   */
	List<VpnServiceDomain> getVPNServicesDetails(String vpnID);
	
}
