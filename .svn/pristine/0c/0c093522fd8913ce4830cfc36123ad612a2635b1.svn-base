package com.bt.edca.dao;

import com.bt.edca.da.domain.SubOrderDomain;
import com.bt.edca.util.helper.CustomerDetailsType;

import java.util.List;

/**
 * CustomerDetailsDaoImpl Implements CustomerDetailsDao interface. Gets the
 * customer details in list by OrderId.
 * 
 * @author Niranjan das Basa.
 * 
 */
public interface CustomerDetailsDao {

	/**
	 * Format to get customer details in list by OrderId.
	 */
	String GET_CUSTOMER_DETAILS_LIST_BY_ORDER = "getCustomerDetailsListByOrderId";

	/**
	 * Gets customer details using this parameters.
	 * 
	 * @param orderId.
	 * @param customertype.
	 * @return.
	 */
	public List getCustomerDetails(String orderId, CustomerDetailsType customertype);

	/**
	 * Format to get customer details in list by OrderId.
	 */
	String GETPOAREFERENCE = "getPoaRefNO";

	/**
	 * Gets customer details using this parameters.
	 * 
	 * @param orderId.
	 * 
	 * @return.
	 */
	public List getPoaDetails(String orderId);

	/**
	 * Format to get customer details in list by OrderId.
	 */
	String GETSUBORDERDETAILS = "getSubOrderValue";

	/**
	 * Gets customer details using this parameters.
	 * 
	 * @param orderId.
	 * 
	 * @return.
	 */
	public List<SubOrderDomain> getSubDetails(String orderId);

}
