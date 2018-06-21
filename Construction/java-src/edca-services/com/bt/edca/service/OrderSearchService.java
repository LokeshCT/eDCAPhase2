package com.bt.edca.service;

import com.bt.edca.common.dto.OrderDetailsDTO;

import java.util.List;

  /**
   * This interface invokes the serviceImpl method and returns the list of order
   * details.
   * 
   * @author Mathivathana
   */
  public interface OrderSearchService {

  /**
   * Gets the values from ServiceImpl.
   * 
   * @param pageNum.
   * @param codeValue.
   * @param userId.
   * @param teamId.
   * @param profileId.
   * @param productCatagoryName.  
   * @param pageNum.
   * @return orderdetaillist.
   */
  public List<OrderDetailsDTO> getOrderDetails(String pageNum, String codeValue,String userId,String teamId,String profileId,String productCatagoryName);
 
}
