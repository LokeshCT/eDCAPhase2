package com.bt.edca.service;

import java.util.List;

import com.bt.edca.common.dto.SiteLocationDetailsDTO;
/**
 * This interface invokes the serviceImpl method and returns the list of SiteLocations
 * Details.
 * 
 * @author SATHYA.
 */
public interface SiteLocationsService {
  /**
   * Gets the values from ServiceImpl.
   * 
   * @param siteId.
   * @return list.
   */
  public List<SiteLocationDetailsDTO> getSiteLocationsDetails(String siteId);
}
