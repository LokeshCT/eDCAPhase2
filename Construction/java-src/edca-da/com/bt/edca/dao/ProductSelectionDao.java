package com.bt.edca.dao;

import java.util.List;

/**
 * 
 * @author Rohit
 *
 */
public interface ProductSelectionDao {
	/**
	 * 
	 */
	static String GET_PRODUCT_SELECTION_LIST_BY_ORDER = 
	    "getProductSelectionListByOrderId";
	/**
	 * 
	 * @param orderId .
	 * @return .
	 */
	public List getProductSelection(String orderId);
}
