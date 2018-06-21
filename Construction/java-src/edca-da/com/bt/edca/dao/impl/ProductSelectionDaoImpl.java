package com.bt.edca.dao.impl;


import java.util.List;

import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.dao.ProductSelectionDao;
/**
 * 
 * @author Rohit
 *
 */
public class ProductSelectionDaoImpl implements ProductSelectionDao {
	/**
	 * @param orderId .
	 * @return .
	 */
  public List getProductSelection(String orderId) {
		
		List productSelectionList = 
		    HibernateQueryUtil.executeQuery(
		        GET_PRODUCT_SELECTION_LIST_BY_ORDER,
                new String[] { "orderId" }, new Object[] {orderId});
	return productSelectionList;
	}
}

