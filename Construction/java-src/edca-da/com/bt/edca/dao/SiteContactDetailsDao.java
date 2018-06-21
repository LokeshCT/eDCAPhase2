package com.bt.edca.dao;


import com.bt.edca.da.domain.SiteContactDetailsDomain;

import java.util.List;


/**
 * Description : Interface for getting the database connection .
 * 
 * @author Rohit .
 */
public interface SiteContactDetailsDao {
  /**
   * Static variable .
   */
  String GET_SITE_CONTACT_DETAILS_LIST_BY_SITE_LOCATION_ID = 
      "getSiteContactDetailsListBySiteLocationId";

  /**
   * decalaration of the constant. 
   * Constant for PrimarySiteContactDetails.
   * constant declared to call procedure to save the values into DB
   */
  String SAVE_SITE_CONTACT_DETAILS = "{"
      + "call DDDCA_SPRING_COMM_CONTACT.DDDCA_ADDUPD_SITE_CONTACT(?,?,?,?,?,?,?,?,?,?)}";
  /**
   * Description : Method for getting the values from the database .
   * 
   * @param siteLocationId
   *          .
   * @return siteContactDetailsList .
   */
  public List getSiteContactDetails(String siteLocationId, String[] siteContactTypeArray);

  /**
   * method declaration to save siteContactDetails in DB.
   * 
   * @param siteLocationId.
   * @param request.
   */
  public void savePrimarySiteContactDetails(List<SiteContactDetailsDomain> 
      saveSiteContactDetails,String siteLocationId);
  
   /**
   * Description:method declaration to save secondaryContactDetails
   * @param saveSiteContactDetails.
   * @param siteLocationId.
   */
  public void saveSecondarySiteContactDetails(List<SiteContactDetailsDomain> 
      saveSiteContactDetails,String siteLocationId);

  
}
