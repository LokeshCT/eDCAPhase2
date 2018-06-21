package com.bt.edca.service;

import java.util.List;

import com.bt.edca.common.dto.CustomerDetailsDTO;
import com.bt.edca.util.helper.CustomerDetailsType;

/**
 * Service layer which is implemented in CustomerDetailsServiceImpl.
 * 
 * @author Niranjan das Basa.
 * 
 */
public interface CustomerDetailsService {

	/**
	 * Method implemented in ServiceImpl.
	 * 
	 * @param orderId.
	 * @param customertype.
	 * @return.
	 */
	public CustomerDetailsDTO getCustomerDetails(String orderId, CustomerDetailsType customertype);

	/**
	 * Method implemented in ServiceImpl.
	 * 
	 * @param orderId.
	 * 
	 * @return.
	 */
	public List<CustomerDetailsDTO> getSubOrderType(String orderId);

	/**
	 * Method implemented in ServiceImpl.
	 * 
	 * @param orderId.
	 * 
	 * @return.
	 */
	public List<CustomerDetailsDTO> getPoaReference(String orderId);

}
