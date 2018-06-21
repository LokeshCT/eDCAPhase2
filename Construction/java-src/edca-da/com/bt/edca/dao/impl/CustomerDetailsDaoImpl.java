package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.POADomain;
import com.bt.edca.da.domain.SubOrderDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.dao.CustomerDetailsDao;
import com.bt.edca.util.EdcaLogger;

import com.bt.edca.util.helper.CustomerDetailsType;

import java.util.List;

/**
 * Class used to execute Query.
 * 
 * @author Niranjan das Basa.
 * 
 */
public class CustomerDetailsDaoImpl implements CustomerDetailsDao {

	/**
	 * Format of customertype.
	 */
	private static final String CUSTOMERTYPE = "customertype";

	/**
	 * Format of orderId.
	 */
	private static final String ORDER_ID = "orderId";

	/**
	 * logger is to display log messages.
	 */
	private static EdcaLogger logger = EdcaLogger.getLogger(CustomerDetailsDaoImpl.class);

	/**
	 * Gets customer details using this parameters.
	 * 
	 * @param orderId
	 *            .
	 * @param customertype
	 *            .
	 * @return customerDetailsList.
	 */
	public List getCustomerDetails(String orderId, CustomerDetailsType customertype) {

		List customerDetailsList = HibernateQueryUtil.executeQuery(GET_CUSTOMER_DETAILS_LIST_BY_ORDER,
				new String[] { ORDER_ID, CUSTOMERTYPE }, new Object[] { orderId, customertype.getCustomertype() });

		return customerDetailsList;
	}

	/**
	 * Gets poaDetails details using this parameters.
	 * 
	 * @param orderId.
	 * @return poaDetailsList.
	 */
	public List<POADomain> getPoaDetails(String orderId) {

		@SuppressWarnings("unchecked")
		List<POADomain> poaDetailsList = HibernateQueryUtil.executeQuery(GETPOAREFERENCE, new String[] { ORDER_ID },
				new Object[] { orderId });

		return poaDetailsList;
	}

	/**
	 * Gets subOrder details using this parameters.
	 * 
	 * @param orderId.
	 * @return subOrderList.
	 */
	public List<SubOrderDomain> getSubDetails(String orderId) {

		@SuppressWarnings("unchecked")
		List<SubOrderDomain> subOrderList = HibernateQueryUtil.executeQuery(GETSUBORDERDETAILS,
				new String[] { ORDER_ID }, new Object[] { orderId });

		return subOrderList;
	}

}
