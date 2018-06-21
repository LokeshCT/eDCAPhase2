package com.bt.edca.dao;

import java.util.List;

public interface CPEOrderingDao {
	/**
	 * Format to get cpeOrdering Details in list by OrderId.
	 */
	String GET_CPEORDERING_BY_ORDERID = "getCPEOrderingDetailsByOrderId";	

	/**
	 * Gets cpeordering details using this parameters.
	 * 
	 * @param orderId
	 *            - String
	 * @param siteId
	 *            - String
	 * @return
	 */
	public List getCPEOrderingDetails(String orderId, String siteId);
}
