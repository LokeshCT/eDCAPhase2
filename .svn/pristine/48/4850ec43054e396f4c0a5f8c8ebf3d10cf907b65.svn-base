package com.bt.edca.service.impl;

import com.bt.edca.common.dto.SiteContactDetailsDTO;

import com.bt.edca.da.domain.SiteContactDetailsDomain;
import com.bt.edca.dao.SiteContactDetailsDao;
import com.bt.edca.service.SiteContactDetailsService;
import com.bt.edca.service.constants.EdcaServiceConstants;
import com.bt.edca.util.HelperMethods;
import com.bt.edca.util.helper.SiteContactTypeDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description : Implementation of interface(SiteContactDetailsService) .
 * 
 * @author Rohit
 */
public class SiteContactDetailsServiceImpl implements SiteContactDetailsService {
  /**
   * Description : Object of SiteContactDetailsDao(for getting the Database
   * connection) .
   */
  private SiteContactDetailsDao siteContactDetailsDao;

  /**
  * Description : Method for performing operation over the values fetched
  * from the data base(service method) .
  * 
  * @param siteLocationId.
  * @return customerContactDetailsMap.
  */
  @SuppressWarnings("unchecked")
  public Map<String, List> getSiteContactDetails(String siteLocationId) {

    String[] siteContactTypeArray = getSiteContactRelatedTypes();
    List<SiteContactDetailsDomain> customerContactDetailsList = 
        (List<SiteContactDetailsDomain>) siteContactDetailsDao
          .getSiteContactDetails(siteLocationId, siteContactTypeArray);

    Map<String, List> customerContactDetailsMap = 
        populateSiteContactTypes(customerContactDetailsList);

    return customerContactDetailsMap;

  }

  /**
  * method to get the contactTypes from enum class.
  * 
  * @return contactTypes array.
  */
  private String[] getSiteContactRelatedTypes() {
    String[] siteContactTypeArray = new String[2];
    siteContactTypeArray[0] = SiteContactTypeDetails.PRIMARYCONTACTTYPE.getContactType();
    siteContactTypeArray[1] = SiteContactTypeDetails.SECONDARYCONTACTYPE.getContactType();

    return siteContactTypeArray;
  }

  /**
  * method to return the contactLists of specific types.
  * 
  * @param siteContactTypeDetailsList.
  * @return contactType Map.
  */
  private Map<String, List> populateSiteContactTypes(
        List<SiteContactDetailsDomain> siteContactTypeDetailsList) {
    Map<String, List> contactTypeMap = new HashMap<String, List>();

    List<SiteContactDetailsDTO> primaryContactTypeList = new ArrayList<SiteContactDetailsDTO>();
    List<SiteContactDetailsDTO> secondaryContactTypeList = new ArrayList<SiteContactDetailsDTO>();

    if (!HelperMethods.isNullOrEmpty(siteContactTypeDetailsList)) {
      for (SiteContactDetailsDomain siteContactTypeDomain : siteContactTypeDetailsList) {
        if (SiteContactTypeDetails.PRIMARYCONTACTTYPE.getContactType()
            .equalsIgnoreCase(siteContactTypeDomain.getContactType())) {
          primaryContactTypeList.add(convertContactTypeDomainToDTO(siteContactTypeDomain));
        } else if (SiteContactTypeDetails.SECONDARYCONTACTYPE.getContactType()
            .equalsIgnoreCase(siteContactTypeDomain.getContactType())) {
          secondaryContactTypeList.add(convertContactTypeDomainToDTO(siteContactTypeDomain));
        }
      }
    }

    contactTypeMap.put(EdcaServiceConstants.PRIMARYCONTACTTYPELIST, primaryContactTypeList);
    contactTypeMap.put(EdcaServiceConstants.SECONDARYCONTACTTYPELIST, secondaryContactTypeList);

    return contactTypeMap;
  }
  /**
  * method to set the values to dto fetching from domain.
  * 
  * @param siteContactTypeDomain.
  * @return SiteContactDetailsDTO.
  */
  public SiteContactDetailsDTO convertContactTypeDomainToDTO(
      SiteContactDetailsDomain siteContactTypeDomain) {

		SiteContactDetailsDTO siteContactDetailsDTO = new SiteContactDetailsDTO();

    siteContactDetailsDTO.setContactId(siteContactTypeDomain.getContactId());
    siteContactDetailsDTO.setContactType(siteContactTypeDomain.getContactType());
    siteContactDetailsDTO.setFirstName(siteContactTypeDomain.getFirstName());
    siteContactDetailsDTO.setLastName(siteContactTypeDomain.getLastName());
    siteContactDetailsDTO.setJobTitle(siteContactTypeDomain.getJobTitle());
    siteContactDetailsDTO.setEmail(siteContactTypeDomain.getEmail());
    siteContactDetailsDTO.setTelephone(siteContactTypeDomain.getTelephone());
    siteContactDetailsDTO.setMobilePager(siteContactTypeDomain.getMobilePager());
    siteContactDetailsDTO.setSiteLocationId(siteContactTypeDomain.getSiteLocationId());

    return siteContactDetailsDTO;

  }

  /**
  * Description : Getter method for getting the database connection from DAO
  * .
  * 
  * @return siteContactDetailsDao .
  */
  public SiteContactDetailsDao getSiteContactDetailsDao() {
    return siteContactDetailsDao;
  }

  /**
  * Description : Setter method for SiteContactDetailsDao .
  * 
  * @param SiteContactDetailsDao
  *            . .
  */
  public void setSiteContactDetailsDao(SiteContactDetailsDao siteContactDetailsDao) {
    this.siteContactDetailsDao = siteContactDetailsDao;
  }

  /**
  * method implementation to save SiteContactDetails.
  */
  public List<SiteContactDetailsDTO> saveSiteContactDetails(
      Map<String, List<SiteContactDetailsDTO>> siteContactDetailsMap, String siteLocationId) {

    List<SiteContactDetailsDTO> primaryDetailsList = new ArrayList<SiteContactDetailsDTO>();
    List<SiteContactDetailsDTO> secondaryDetailsList = new ArrayList<SiteContactDetailsDTO>();

    List<SiteContactDetailsDomain> domainprimaryDetailsList = 
        new ArrayList<SiteContactDetailsDomain>();
    List<SiteContactDetailsDomain> domainsecondaryDetailsList = 
         new ArrayList<SiteContactDetailsDomain>();

    for (Map.Entry<String, List<SiteContactDetailsDTO>> 
                siteContactDetailsMapValue : siteContactDetailsMap.entrySet()) {
      if (EdcaServiceConstants.PRIMARYCONTACTTYPELIST
            .equalsIgnoreCase(siteContactDetailsMapValue.getKey())) {
        primaryDetailsList.addAll(siteContactDetailsMapValue.getValue());

      } else if (EdcaServiceConstants.SECONDARYCONTACTTYPELIST
            .equalsIgnoreCase(siteContactDetailsMapValue.getKey())) {
        secondaryDetailsList.addAll(siteContactDetailsMapValue.getValue());

      }
    }
    
    domainprimaryDetailsList.add(convertSiteContactDetailsDomain(primaryDetailsList));
    domainsecondaryDetailsList.add(convertSiteContactDetailsDomain(secondaryDetailsList));

    siteContactDetailsDao.savePrimarySiteContactDetails(domainprimaryDetailsList, siteLocationId);
    siteContactDetailsDao.saveSecondarySiteContactDetails(
         domainsecondaryDetailsList, siteLocationId);

    return null;
  }

  /**
   * Description:Method sets the values to Domain which are from UI.
 * @param primaryDetailsList.
 * @return daoDomainDetails.
 */
  public SiteContactDetailsDomain convertSiteContactDetailsDomain(
      List<SiteContactDetailsDTO> siteContactDetailsList) {

    SiteContactDetailsDomain daoDomainDetails = new SiteContactDetailsDomain();

      for (SiteContactDetailsDTO saveContactDetailsDTO : siteContactDetailsList) {
      daoDomainDetails.setContactId(saveContactDetailsDTO.getContactId());
      daoDomainDetails.setFirstName(saveContactDetailsDTO.getFirstName());
      daoDomainDetails.setLastName(saveContactDetailsDTO.getLastName());
      daoDomainDetails.setTelephone(saveContactDetailsDTO.getTelephone());
      daoDomainDetails.setContactType(saveContactDetailsDTO.getContactType());
      daoDomainDetails.setEmail(saveContactDetailsDTO.getEmail());
      daoDomainDetails.setMobilePager(saveContactDetailsDTO.getMobilePager());
      daoDomainDetails.setJobTitle(saveContactDetailsDTO.getJobTitle());
    }

    return daoDomainDetails;
  }
}
