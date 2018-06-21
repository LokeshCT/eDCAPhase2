package com.bt.edca.dao.impl;

import java.util.List;

import com.bt.edca.da.domain.SiteLocationDetailsDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.dao.SiteLocationsDao;
/**
 * Description : Class used to execute Query. Fetching the SiteLocationDetails.
 * 
 * @author SATHYA.
 *
 */
public class SiteLocationsDaoImpl implements SiteLocationsDao {
  /**
   * Format of siteId.
   */
  private static final String SITE_ID = "siteId";
  /**
   * Description : Gets SIteLocations page using this parameters.
   * 
   * @param siteId.
   * @return siteLocationDetailsList.
   */

  @SuppressWarnings("unchecked")
  @Override
  public List<SiteLocationDetailsDomain> getsiteLocationsDetails(String siteId) {
   
    List<SiteLocationDetailsDomain> siteLocationDetailsList = HibernateQueryUtil.executeQuery(
        GET_SITE_LOCATION_DETAILS_LIST_BY_SITE_ID, 
        new String[] { "siteId" }, new Object[] { siteId }); 
    return siteLocationDetailsList;
  }

}
