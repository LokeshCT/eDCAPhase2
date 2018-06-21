package com.bt.edca.dao;

import java.util.List;
import com.bt.edca.da.domain.VpnPageDomain;

/**
 * The Interface VpnPageDao.
 */
public interface VpnPageDao {

	/** The get vpn page details. */
	String GET_VPN_PAGE_DETAILS = "getVpnPageByServiceInstanceId";

	/**
	 * Gets the vpn page details.
	 * 
	 * @param serviceInstanceId
	 *            the service instance id
	 * @return the vpn page details
	 */
	List<VpnPageDomain> getVpnPageDetails(String serviceInstanceId);

}
