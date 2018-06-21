package com.bt.edca.service.impl;

import com.bt.edca.common.dto.UserDetailsDTO;
import com.bt.edca.da.domain.ProductDetailsDomain;
import com.bt.edca.da.domain.UserDetailsDomain;

import com.bt.edca.dao.UserDetailsDao;
import com.bt.edca.service.UserDetailsService;
import com.bt.edca.service.constants.EdcaServiceConstants;
import com.bt.edca.util.HelperMethods;


import java.util.ArrayList;

import java.util.List;


/**
 * Description : This class implements the UserDetailsService Interface. This
 * class mainly invokes the interface method and return the userDetails List
 * values.
 * 
 * @author SATHYA.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

  /**
   * Description : Reference variable.
   */
  UserDetailsDao userDetailsDao;

  /**
   * Description : Gets the value from UserDetailsDao
   * 
   * @return userDetailsDao.
   */

  public UserDetailsDao getUserDetailsDao() {
    return userDetailsDao;
  }

  /**
   * Description : Sets the value from UserDetailsDao
   * 
   * @return userDetailsDao.
   */
  public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
    this.userDetailsDao = userDetailsDao;
  }

  /**
   * Description : This methods invokes the getUserDetails() from UserDetailsDao
   * with this parameters for getting the profileNames and teamId and
   * DefaultFlag from Dao.
   * 
   * @param nUserId
   *          .
   * @return userList.
   */
  @SuppressWarnings("unchecked")
  public List<UserDetailsDTO> getUserDetails(String nUserId) {

    List<UserDetailsDomain> userDetailsDomainList = userDetailsDao.getUserDetails(nUserId);

    List<ProductDetailsDomain> productDetailsList = null;
    
     List<UserDetailsDTO> userDetailsDTOList = new ArrayList<UserDetailsDTO>();
    if (!HelperMethods.isNullOrEmpty(userDetailsDomainList)) {
      for (UserDetailsDomain userDetailsDomain : userDetailsDomainList) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setTeamId(userDetailsDomain.getTeamId());
        userDetailsDTO.setProfileName(userDetailsDomain.getProfileName());
        userDetailsDTO.setDefaultFlag(userDetailsDomain.getDefaultFlag());
        userDetailsDTOList.add(userDetailsDTO);
      }
    }
    for (UserDetailsDomain list : userDetailsDomainList) {
      String teamId = list.getTeamId();
      String[] teams = teamId.split(EdcaServiceConstants.COMMA_DELIMITER);
      String team = teams[1];
      String flag = list.getDefaultFlag();

      productDetailsList = userDetailsDao.getProductDetails(team);

      if (!HelperMethods.isNullOrEmpty(productDetailsList)) {
        for (ProductDetailsDomain productDetailsDomain : productDetailsList) {
          UserDetailsDTO productDetailsDTO = new UserDetailsDTO();

          if ("T".equals(flag)) {
            productDetailsDTO.setProductCatagoryName(productDetailsDomain.getProductCatagoryName());
          }
          productDetailsDTO.setProductCatagoryId(productDetailsDomain.getProductCatagoryId());
          userDetailsDTOList.add(productDetailsDTO);
        }
      }
    }

    return userDetailsDTOList;
  }


  /**
   * Description : This method is used to split the string based on the teamId
   * and return the teamId.
   * 
   * @param nUserId.
   * @return teamId.
   */

  public String getTeamid(String id) {
    String[] teams = id.split(EdcaServiceConstants.COMMA_DELIMITER);
    String teamId = teams[1];
    return teamId;
  }

  /**
   * Description : This methods invokes the saveUserDefaultFlag() from
   * UserDetailsDao with this parameters for saving the usersData.
   * 
   * @param profileteamId.
   * 
   * @return response.
   */

  public String saveMakeAsDefaultFlag(String nUserId, String profileteamId) {

    String[] teams = profileteamId.split(EdcaServiceConstants.COMMA_DELIMITER);
    String profileId = teams[0];
    String teamId = teams[1];
    String response = userDetailsDao.saveUserDefaultFlag(nUserId, profileId, teamId);
    return response;
  }

  /**
   * Description : This methods invokes the loadProductNames() from
   * UserDetailsDao with this parameters.
   * 
   * @param profileteamId
   *          .
   * @return productNamesList.
   */
  @SuppressWarnings("unchecked")
  public List<UserDetailsDTO> loadProductNames(String profileteamId) {
    List<ProductDetailsDomain> productDetailslist = userDetailsDao.getProductDetails(getTeamid(profileteamId));
    List<UserDetailsDTO> productlist = new ArrayList<UserDetailsDTO>();
    if (!HelperMethods.isNullOrEmpty(productDetailslist)) {
      for (ProductDetailsDomain productDetailsDomain : productDetailslist) {
        UserDetailsDTO productDetailsDTO = new UserDetailsDTO();
        productDetailsDTO.setProductCatagoryName(productDetailsDomain.getProductCatagoryName());
        productDetailsDTO.setProductCatagoryId(productDetailsDomain.getProductCatagoryId());
        productlist.add(productDetailsDTO);

      }
    }

    return productlist;
  }

}