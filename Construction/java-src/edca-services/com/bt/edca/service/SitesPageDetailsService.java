package com.bt.edca.service;

import java.util.List;

public interface SitesPageDetailsService {
  
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
