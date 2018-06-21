package com.bt.edca.dao;

import java.util.List;

public interface SitesPageDetailsDao {
  
  /**
   * Format to get RESILIENCE_COUNT.
   */
 
  
  String GET_RESILIENCE_BY_ORDER_ID = "getResilienceByOrderId";
  
  String SATELLITE_ACCESS_PROCEDURE = "{call DD_DCA_PK.Pr_Get_Satelite_Access(?,?,?)}";
  
  String GET_ORDER_TYPE_BY_ORDER_ID = "getOrderTypeByOrderId";
  
 // String GET_SITE_DETAILS_1 = "getSitesDetails1";
  
  
  /**
   * Description : Gets DCA ID REFERENCE Using OrderId.
   * @param orderId.
   * @return.
   */
 // public long getSiteDcaId(String orderId);
  
  public int getResilienceCount(String orderId,String resilience);
  
  public String getSatelliteAccess(String orderId);
  
  public String getOrderType(String orderId);
  
  public List getSitesDetails(String orderId, int resilience,String satelliteAccess,String orderType, String orderStatus);

}
