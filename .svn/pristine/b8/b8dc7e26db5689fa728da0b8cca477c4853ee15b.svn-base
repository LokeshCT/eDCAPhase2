package com.bt.edca.service;

import java.util.List;

import com.bt.edca.common.dto.SiteAddressDTO;

/**
 * Service layer interface for SiteAddress .
 * @author Jagankumar G.
 *
 */
public interface SiteAddressService {


  /**
   * declaration of method to be implemented in SiteAddressServiceImpl. 
   * @param siteLocationId .
   * @return SiteAddressDTO .
   */
  public SiteAddressDTO getSiteDetails(String siteLocationId);
  

  public List getCountryName(String siteLocationId);
  public void saveAddress(String siteLocationId,
      String floor,String room, String buildingNumber,
      String addressLine1 , String addressLine2 ,String city ,
      String postcode , String state);


}
