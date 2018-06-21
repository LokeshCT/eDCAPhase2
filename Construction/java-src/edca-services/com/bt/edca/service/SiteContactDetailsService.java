package com.bt.edca.service;

import java.util.List;
import java.util.Map;

import com.bt.edca.common.dto.SiteContactDetailsDTO;

/**
 * Description : Interface for service method .
 * 
 * @author Rohit
 */
public interface SiteContactDetailsService {
  /**
   * @param siteLocationId
   *          .
   * @return siteContactDetailsDTOList .
   */
  public Map<String, List> getSiteContactDetails(String siteLocationId);

  /**
   * method declaration to save SiteContactDetails in servicelayer.
   * 
   * @param siteLocationId.
   * @param request.
   * @return String.
   */
  public List<SiteContactDetailsDTO> saveSiteContactDetails(Map<String, List<SiteContactDetailsDTO>> siteContactTypeMap,String siteLocationId);

}
