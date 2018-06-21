package com.bt.edca.service.impl;

import com.bt.edca.common.dto.SiteAddressDTO;
import com.bt.edca.da.domain.SiteAddressCountryDomain;
import com.bt.edca.da.domain.SiteAddressDomain;
import com.bt.edca.dao.SiteAddressDao;
import com.bt.edca.service.SiteAddressService;
import com.bt.edca.util.HelperMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * implementation of SiteAddressService.
 * 
 * @author Jagankumar G.
 *
 */
public class SiteAddressServiceImpl implements SiteAddressService {

  /**
   * Description:Interface SiteAddressDAO. Declaration of reference variable
   * siteAddressDao.
   */
  private SiteAddressDao siteAddressDao;

  /**
   * Description: Interface SiteAddressDAO. fetches the siteLocationId mapping
   * file with SiteAddressDomain class.
   * 
   * @returns siteAddressDAO.
   */
  public SiteAddressDao getSiteAddressDao() {
    return siteAddressDao;
  }

  /**
   * Sets the siteLocationId for SiteAddressDao interface
   * 
   * @param siteAddressDao.
   */
  public void setSiteAddressDao(SiteAddressDao siteAddressDao) {
    this.siteAddressDao = siteAddressDao;
  }

  /**
   * Description:returns DTO by siteLocationId. Values will fetched from domain
   * class and set by DTO class.
   * 
   * @param siteLocationId.
   * @returns DTO to controller.
   */
  public SiteAddressDTO getSiteDetails(String siteLocationId) {
    SiteAddressDTO siteAddressDTO = new SiteAddressDTO();
    List<SiteAddressDomain> siteAddressList = siteAddressDao.getSiteAddress(siteLocationId);

    if (!HelperMethods.isNullOrEmpty(siteAddressList)) {
      for (SiteAddressDomain siteAddressDomain : siteAddressList) {

        siteAddressDTO.setFloor(siteAddressDomain.getFloor());
        siteAddressDTO.setRoom(siteAddressDomain.getRoom());
        siteAddressDTO.setBuildingNumber(siteAddressDomain.getBuildingNumber());
        siteAddressDTO.setAddressLine1(siteAddressDomain.getAddressLine1());
        siteAddressDTO.setAddressLine2(siteAddressDomain.getAddressLine2());
        siteAddressDTO.setCity(siteAddressDomain.getCity());
        siteAddressDTO.setPostcode(siteAddressDomain.getPostcode());
        siteAddressDTO.setState(siteAddressDomain.getState());
        siteAddressDTO.setSiteLocationId(siteAddressDomain.getSiteLocationId());
      }
    }

    
List<SiteAddressCountryDomain> siteAddressCountry = 
siteAddressDao.getCountry(siteLocationId);
    
    if (!HelperMethods.isNullOrEmpty(siteAddressList)) {
      for (SiteAddressCountryDomain siteAddressDomain : siteAddressCountry) {
        String country = siteAddressDomain.getCountry();
        siteAddressDTO.setCountry(siteAddressDomain.getCountry());
       
      }
    }
    
    
    
    return siteAddressDTO;
  }
  
  /**
   * Description : A List having the state name for a particulars countries .
   *@param siteLocationId .
   */
  
  
  public List getCountryName(String siteLocationId){
    SiteAddressDTO siteAddressDTO = new SiteAddressDTO();
    List  stateList = new ArrayList();
    
    List<SiteAddressCountryDomain> siteAddressList = 
        siteAddressDao.getCountry(siteLocationId);
    
    if (!HelperMethods.isNullOrEmpty(siteAddressList)) {
      for (SiteAddressCountryDomain siteAddressDomain : siteAddressList) {
        String country = siteAddressDomain.getCountry();
        siteAddressDTO.setCountry(siteAddressDomain.getCountry());
        stateList.add(siteAddressDTO);
        
        List<SiteAddressCountryDomain> siteStateList = 
            siteAddressDao.getState(country);
       if (!HelperMethods.isNullOrEmpty(siteStateList)) {
        for (SiteAddressCountryDomain sitestateDomain : siteStateList) {
          SiteAddressDTO siteStateDTO = new SiteAddressDTO();
          siteStateDTO.setState(sitestateDomain.getState());
          stateList.add(siteStateDTO);
        }
       }
  }
    }
      return stateList;
}
 /**
  * Descriotion : Method for saving the data in the edit mode .
  * @param siteLocationId .
  * @param floor .
  * @param room .
  * @param buildingNumber .
  * @param addressLine1 .
  * @param addressLine2 .
  * @param city .
  * @param postcode .
  * @param state .
  */
  public void saveAddress(String siteLocationId,String floor,String room,
      String buildingNumber,String addressLine1 ,
      String addressLine2 ,String city ,
      String postcode , String state){
    siteAddressDao.saveAddress(siteLocationId, floor
        , room, buildingNumber, addressLine1,
        addressLine2, city, postcode, state);
    
    
  }
}
