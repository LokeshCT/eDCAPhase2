package com.bt.edca.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bt.edca.common.dto.SiteLocationDetailsDTO;
import com.bt.edca.da.domain.SiteLocationDetailsDomain;
import com.bt.edca.dao.SiteLocationsDao;
import com.bt.edca.service.SiteLocationsService;
import com.bt.edca.util.HelperMethods;
/**
 * Description : This class implements the SiteLocationsService Interface. This
 * class mainly invokes the interface method and return the siteLocation List
 * values.
 * 
 * @author SATHYA.
 */
public class SiteLocationsServiceImpl implements SiteLocationsService {
  /**
   * Description : Reference variable.
   */
  private SiteLocationsDao siteLocationsDao;
  /**
   * Description : Gets the value from siteLocationsDao
   * 
   * @return siteLocationsDao.
   */
  public SiteLocationsDao getSiteLocationsDao() {
    return siteLocationsDao;
  }
  /**
   * Description : Sets the value from SiteLocationsDao
   * 
   * @return siteLocationsDao.
   */
  public void setSiteLocationsDao(SiteLocationsDao siteLocationsDao) {
    this.siteLocationsDao = siteLocationsDao;
  }
  /**
   * Description : This methods invokes the   getSiteLocationsDetails from
   * siteLocationsDao with this parameters for getting siteLocation Details from
   * Dao.
   * 
   * @param siteId.
   * 
   * @return siteLocationsDTOList.
   */
  public List<SiteLocationDetailsDTO> getSiteLocationsDetails(String siteId)
  {
    SiteLocationDetailsDTO siteLocationDetailsDTO = new SiteLocationDetailsDTO();
    List<SiteLocationDetailsDTO> siteLocationsDTOList=new ArrayList();
    List<SiteLocationDetailsDomain> siteLocationDetailsList = 
        siteLocationsDao.getsiteLocationsDetails(siteId);
 if (!HelperMethods.isNullOrEmpty(siteLocationDetailsList)) {
      for (SiteLocationDetailsDomain siteLocationDetailsDomain : siteLocationDetailsList) {
         siteLocationDetailsDTO.setSiteName(siteLocationDetailsDomain.getSiteName());
         siteLocationDetailsDTO.setSiteLocationId(siteLocationDetailsDomain.getSiteLocationId());
         siteLocationsDTOList.add(siteLocationDetailsDTO);
     }
    
   
  }
  return siteLocationsDTOList;
  }

}
