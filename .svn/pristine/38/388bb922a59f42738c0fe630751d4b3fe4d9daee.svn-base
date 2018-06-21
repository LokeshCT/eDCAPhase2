package com.bt.edca.dao.impl;

import java.util.List;

import com.bt.edca.da.domain.VpnPageDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.dao.VpnPageDao;

/**
 * The Class VpnPageDaoImpl.
 */
public class VpnPageDaoImpl implements VpnPageDao {

	/** The Constant SERVICE_INSTANCE_ID. */
	private static final String SERVICE_INSTANCE_ID = "serviceInstanceId";

	/**
	 * Get the vpn details from the db through hibernate query return the list
	 * of VpnPageDomain details.
	 */
	public List<VpnPageDomain> getVpnPageDetails(String serviceInstanceId) {

		List vpnPageList = HibernateQueryUtil.executeQuery(
				GET_VPN_PAGE_DETAILS, new String[] { SERVICE_INSTANCE_ID },
				new Object[] { serviceInstanceId });
		return vpnPageList;

	}

}
