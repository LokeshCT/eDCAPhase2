package com.bt.edca.service;

import com.bt.edca.common.dto.UserDetailsDTO;


import java.util.List;

/**
 * Description:This interface invokes the serviceImpl method and returns the list of profile
 *             selection User Details.
 * 
 * @author SATHYA.
 */

public interface UserDetailsService {
  /**
   * Gets the values from ServiceImpl.
   * 
   * @param .nUserId
   * @return profileTeamId,profileNames,defaultFlag.
   */
  public List<UserDetailsDTO> getUserDetails(String nUserId);

  /**
   * Gets the values from ServiceImpl.
   * 
   * @param nUserId,profileteamId.
   * @return outParameter.
   */
  public String saveMakeAsDefaultFlag(String nUserId, String profileteamId);

  /**
   * Gets the values from ServiceImpl.
   * 
   * @param profileTeamId.
   * @return productNames.
   */
  public List<UserDetailsDTO> loadProductNames(String profileteamId);
  

}
