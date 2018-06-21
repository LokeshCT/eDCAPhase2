package com.bt.edca.dao.impl;

import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.dao.SiteLocationDetailsDao;
import com.bt.edca.util.EdcaLogger;

import java.util.List;

/**
 * Implementation of DAO layer for SiteLocationDetails.
 * 
 * @author 609060481:Jagankumar G.
 *
 */
public class SiteLocationDetailsDaoImpl implements SiteLocationDetailsDao {

  /**
   * Captures the logs onto console.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(SiteLocationDetailsDaoImpl.class);

  /**
   * Description:fetches the siteId from DB and returns list of 
   *  siteLocationDetails to ServiceImpl Class.
   * @param siteId.
   * @return List.
   */

  public List getSiteLocationDetails(String siteLocationId) {

    List siteLocationDetailsList = HibernateQueryUtil.executeQuery(
    		GET_SITE_LOCATION_DETAILS_LIST_BY_SITE_LOCATION_ID, 
        new String[] { "siteLocationId" }, new Object[] { siteLocationId });

    return siteLocationDetailsList;
  }
}