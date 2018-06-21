package com.bt.edca.service;

import com.bt.edca.common.dto.OrderContactDTO;

import java.util.List;
import java.util.Map;

/**
 * Service layer interface for declaring methods.
 * Common for CustomerContactDetiails and OrderContactDetails.
 * @author Jagankumar G.
 *
 */
public interface CustomerContactDetailsService {

  /**
   * method of CustomerContactdetails.
   * @param orderId.
   * @return Map.
   */
  public Map<String, List> contactDetailsList(String orderId);

  /**
   * method for OrderContactDetails.
   * @param orderId.
   * @return OrderContactDTO
   */
  public OrderContactDTO getOrderContactType(String orderId); 
  /**
   * Descritiop:Declaration of the method to save the orderContactDetails.
   * @param orderContactDTO.
   * @param orderId.
   * @return List of OrderContactDetails.
   */
  public List saveContactDetails(OrderContactDTO orderContactDTO,String orderId);
  
  /**
   * Descritiop:Declaration of the method to save the customerContactDetails.
   * @param orderContactDTO.
   * @param orderId.
   * @return List of Technicalcontactdetails.
   */
  public List saveTechnicalContactDetails(OrderContactDTO orderContactDTO,String orderId);

}