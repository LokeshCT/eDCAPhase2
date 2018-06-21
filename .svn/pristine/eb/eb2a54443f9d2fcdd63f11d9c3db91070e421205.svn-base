package com.bt.edca.service.impl;

import com.bt.edca.common.dto.OrderDetailsDTO;

import com.bt.edca.da.domain.OrderDetailsDomain;
import com.bt.edca.dao.OrderSearchDao;
import com.bt.edca.service.OrderSearchService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.HelperMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the OrderSearchService Interface. This class mainly
 * invokes the interface method and return the orderDetails List values.
 * 
 * @author Mathivathana
 */
public class OrderSearchServiceImpl implements OrderSearchService {

	/**
	 * logger is to display log messages.
	 */
	private static EdcaLogger logger = EdcaLogger.getLogger(OrderSearchServiceImpl.class);
	/**
	 * Reference variable.
	 */
	private OrderSearchDao orderSearchDao;

	/**
	 * Gets the value from OrderSearchDao
	 * 
	 * @return orderSearchDao.
	 */
	public OrderSearchDao getOrderSearchDao() {
		return orderSearchDao;
	}

	/**
	 * Sets the value from OrderSearchDao
	 * 
	 * @return orderSearchDao.
	 */

	public void setOrderSearchDao(OrderSearchDao orderSearchDao) {
		this.orderSearchDao = orderSearchDao;
	}

	/**
	 * This methods invokes the getOrderDetails() from orderSearchDao with this
	 * parameters
	 * 
	 * @param pageNum
	 * 
	 * @param codeValue.
	 * @param userId.
	 * @param teamId.
	 * @param profileId.
	 * @param productCatagoryName.
	 * @param pageNum.
	 *            .
	 * @return orderDetailsList.
	 */

	public List<OrderDetailsDTO> getOrderDetails(String pageNum, String codeValue, String userId, String teamId,
			String profileId, String productCatagoryName) {

		List<OrderDetailsDomain> orderDetailsDomainList = orderSearchDao.getOrderDetails(pageNum, codeValue, userId,
				teamId, profileId, productCatagoryName);

		List<OrderDetailsDTO> orderDetailsList = new ArrayList<OrderDetailsDTO>();
		System.out.println("***** List size *****" + orderDetailsDomainList.size());

		if (!HelperMethods.isNullOrEmpty(orderDetailsDomainList)) {

			for (OrderDetailsDomain orderDetailsDomain : orderDetailsDomainList) {

				orderDetailsList.add(convertOrderDetailsDomainToDTO(orderDetailsDomain));

			}
		}

		return orderDetailsList;
	}

	/**
	 * This method fetches the values from OrderDetailsDomain class. sets by
	 * OrderDetailsDTO.
	 * 
	 * @param orderDetailsDomain.
	 * @return orderDetailsDTO.
	 */
	public OrderDetailsDTO convertOrderDetailsDomainToDTO(OrderDetailsDomain orderDetailsDomain) {

		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

		orderDetailsDTO.setOrderId(orderDetailsDomain.getOrderId());
		orderDetailsDTO.setOrderReference(orderDetailsDomain.getOrderReference());
		orderDetailsDTO.setClientAccountId(orderDetailsDomain.getClientAccountId());
		orderDetailsDTO.setClientName(orderDetailsDomain.getClientName());
		orderDetailsDTO.setReceivedDate(orderDetailsDomain.getReceivedDate());
		orderDetailsDTO.setCnt(orderDetailsDomain.getCnt());

		return orderDetailsDTO;

	}

}
