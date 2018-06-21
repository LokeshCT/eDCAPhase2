package com.bt.edca.dao;

import java.util.List;

/**
 * DAO layer for SiteAddress. 
 * Interface gets the DB connection from mappingfile.
 * 
 * @author Jagankumar G.
 *
 */
public interface SiteAddressDao {

  /**
   * Format to get siteadress details in list by SiteLocationId.
   */
  static String GET_SITE_ADDRESS_LIST_BY_SITE_LOCATION_ID 
  = "getSiteAdressListBySiteLocationId";
  static String GET_COUNTRY_BY_SITE_LOCATION_ID
  = "getCountryBySiteLocationId" ;
  static String GET_STATE_BY_COUNTRY_NAME 
  ="{call PKG_ACCESS_SPRING.pr_get_state_name(?,?,?,?)}";
  static String SAVE_ADDRESS=
      "{? = call DD_DCA_PK.DCA_ADUPD_SITE_ADDRESS("
      + "?,?,?,?,?,?,?,?,?)}";
  /**
   * @param siteLocationId.
   * @return List of the fields fetched by siteLocationId.
   */
  public List getSiteAddress(String siteLocationId);
  public List getCountry(String siteLocationId);
  public List getState(String country);
  public void saveAddress(String siteLocationId, String floor,
      String room, String buildingNumber , String addressLine1, 
      String addressLine2 , String city , String postcode , String state);

}