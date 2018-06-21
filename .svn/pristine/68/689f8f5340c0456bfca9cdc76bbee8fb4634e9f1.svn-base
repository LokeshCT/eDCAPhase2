package com.bt.edca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bt.edca.common.dto.OrderDetailsDTO;
import com.bt.edca.common.dto.SitePageDTO;
import com.bt.edca.da.domain.OrderDetailsDomain;
import com.bt.edca.da.domain.SitePageDomain;
import com.bt.edca.dao.SitePageDao;
import com.bt.edca.dao.SitesPageDetailsDao;
import com.bt.edca.service.SitesPageDetailsService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.HelperMethods;

public class SitesPageDetailsServiceImpl implements SitesPageDetailsService {

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(SitesPageDetailsServiceImpl.class);

  /**
   * Reference variable.
   */
  private SitesPageDetailsDao sitesPageDetailsDao;

  /**
   * Reference variable.
   */
  private SitePageDomain sitePageDomain;

  /**
   * Description : Gets DCA ID REFERENCE Using OrderId.
   * 
   * @param orderId
   *          .
   * @return sitePageDcaId.
   */

  public int getResilienceCount(String orderId, String resilience) {

    int resilienceCount = sitesPageDetailsDao.getResilienceCount(orderId, resilience);

    return resilienceCount;
  }

  public String getSatelliteAccess(String orderId) {

    String satellite = sitesPageDetailsDao.getSatelliteAccess(orderId);

    return satellite;
  }

  public String getOrderType(String orderId) {

    String orderType = sitesPageDetailsDao.getOrderType(orderId);

    return orderType;

  }

  public List getSitesDetails(String orderId, int resilience, String satelliteAccess, String orderType,
      String orderStatus) {

    List<SitePageDomain> details1 = sitesPageDetailsDao.getSitesDetails(orderId, resilience, satelliteAccess, orderType,
        orderStatus);

    List<SitePageDTO> siteList = new ArrayList<SitePageDTO>();

    if (details1 != null) {
      for (SitePageDomain sitePagedomain : details1) {

        siteList.add(convertSitesPageDetailsToDTO(sitePagedomain));

      }

    }
    return siteList;
  }

  /**
   * @return the sitesPageDetailsDao
   */
  public SitesPageDetailsDao getSitesPageDetailsDao() {
    return sitesPageDetailsDao;
  }

  /**
   * @param sitesPageDetailsDao
   *          the sitesPageDetailsDao to set
   */
  public void setSitesPageDetailsDao(SitesPageDetailsDao sitesPageDetailsDao) {
    this.sitesPageDetailsDao = sitesPageDetailsDao;
  }

  public SitePageDTO convertSitesPageDetailsToDTO(SitePageDomain sitePageDomain) {

    SitePageDTO sitePageDTO = new SitePageDTO();
    sitePageDTO.setSiteId(sitePageDomain.getSiteId());
    sitePageDTO.setDcaReferenceId(sitePageDomain.getDcaReferenceId());
    sitePageDTO.setResilience(sitePageDomain.getResilience());
    sitePageDTO.setPricing(sitePageDomain.getPricing());
    sitePageDTO.setServiceInstance(sitePageDomain.getServiceInstance());

    return sitePageDTO;

  }

}
