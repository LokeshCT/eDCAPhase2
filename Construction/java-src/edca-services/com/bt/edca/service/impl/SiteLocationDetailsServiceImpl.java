package com.bt.edca.service.impl;

import com.bt.edca.common.dto.SiteLocationDetailsDTO;
import com.bt.edca.da.domain.SiteLocationDetailsDomain;
import com.bt.edca.dao.SiteLocationDetailsDao;
import com.bt.edca.service.SiteLocationDetailsService;
import com.bt.edca.util.HelperMethods;


import java.util.List;

/**
 * implementation of SiteLocationDetailsService.
 * @author Jagankumar G.
 *
 */
public class SiteLocationDetailsServiceImpl implements SiteLocationDetailsService {

  /**
   * Description:Interface SiteLocationDetailsDAO.
   * Declaration of reference variable siteLocationDetailsDao.
   */
  private SiteLocationDetailsDao siteLocationDetailsDao;

  /**
   * Description: Interface SiteLocationDetailsDao.
   * fetches the orderId mapping file with SiteLocationDetailsDomain class.
   * @return siteLocationDetailsDao.
   */
  public SiteLocationDetailsDao getSiteLocationDetailsDao() {
    return siteLocationDetailsDao;
  }

  /**
   * Sets the orderId for SiteLocationDetailsDao interface 
   * @param siteLocationDetailsDao.
   */
  public void setSiteLocationDetailsDao(SiteLocationDetailsDao siteLocationDetailsDao) {
    this.siteLocationDetailsDao = siteLocationDetailsDao;
  }
  /**
   * Description:returns DTO by siteId.
   * Values will fetched from domain class and set by DTO class.
   * @param siteId.
   * @returns DTO to controller.
   */
  public SiteLocationDetailsDTO getSiteDetails(String siteLocationId) {
    SiteLocationDetailsDTO siteLocationDetailsDTO = new SiteLocationDetailsDTO();
    List<SiteLocationDetailsDomain> siteLocationDetailsList = 
        siteLocationDetailsDao.getSiteLocationDetails(siteLocationId);

  if (!HelperMethods.isNullOrEmpty(siteLocationDetailsList)) {
      for (SiteLocationDetailsDomain siteLocationDetailsDomain : siteLocationDetailsList) {
        
        siteLocationDetailsDTO.setSiteName(siteLocationDetailsDomain.getSiteName());
        siteLocationDetailsDTO.setCountry(siteLocationDetailsDomain.getCountry());
        siteLocationDetailsDTO.setClassicSiteId(siteLocationDetailsDomain.getClassicSiteId());
        siteLocationDetailsDTO.setSerClassicSiteId(siteLocationDetailsDomain.getSerClassicSiteId());
        siteLocationDetailsDTO.setHubSpoke(siteLocationDetailsDomain.getHubSpoke());
        siteLocationDetailsDTO.setSiteLocationId(siteLocationDetailsDomain.getSiteLocationId());
        siteLocationDetailsDTO.setSiteId(siteLocationDetailsDomain.getSiteId());
        siteLocationDetailsDTO.setSiteLocationId(siteLocationDetailsDomain.getSiteLocationId());
        
     }
    }
  
    return siteLocationDetailsDTO;
  }

 
}
