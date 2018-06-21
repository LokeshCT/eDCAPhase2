package com.bt.edca.service;

import com.bt.edca.common.dto.ConfigureVlanDTO;

import java.util.List;

public interface ConfigureVlanService {

  /**
   * Gets the values from ServiceImpl.
   * 
   * @param .serviceInstanceId
   * @return list.
   */
  public List<ConfigureVlanDTO> getConfigureVlanDetails(String serviceInstanceId);

  /**
   * method declared to fetch serviceInstance.
   * 
   * @param serviceInstanceId.
   * @return serviceInstance.
   */
  public String getServiceInstance(String serviceInstanceId);

  /**
   * method declaration to fetch lastmileaccess and accessoptionflag.
   * 
   * @param siteId.
   * @return list.
   */
  public List getUltraDetails(String siteId);

  /**
   * method declaration to fetch sanName.
   * 
   * @param serviceInstanceId.
   * @return sanName.
   */
  public List<ConfigureVlanDTO> getSanNameDetails(String serviceInstanceId);

  /**
   * declared to fetch sanValue.
   * 
   * @param sanId.
   * @param vlanId.
   * @param sanName.
   * @return sanvalue.
   */
  public List<ConfigureVlanDTO> getSanValue(int sanId, int vlanId, String sanName);

}
