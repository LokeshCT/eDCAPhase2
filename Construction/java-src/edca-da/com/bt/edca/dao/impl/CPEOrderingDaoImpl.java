package com.bt.edca.dao.impl;

import java.util.List;

import com.bt.edca.da.domain.CPEOrderingDomain;
import com.bt.edca.dao.CPEOrderingDao;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.util.EdcaLogger;


public class CPEOrderingDaoImpl implements CPEOrderingDao{
	
	/**
     *  Order Id Constant
     */
	private static final String ORDER_ID = "orderId";
	
	/**
     * logger is to display log messages.
     */
    private static EdcaLogger logger = EdcaLogger.getLogger(CustomerDetailsDaoImpl.class);
	
    /**
     * 
     *  Get Cpe Ordering Detils  List
     * 
     */
	@Override
	public List getCPEOrderingDetails(String orderId,
			String siteId) {
		
		List  cpeOrderingDetailsList =   HibernateQueryUtil.executeQuery(GET_CPEORDERING_BY_ORDERID,
				new String[]{ORDER_ID},
				new Object[]{orderId});
		return cpeOrderingDetailsList;
	}
	
	

	
	
}
