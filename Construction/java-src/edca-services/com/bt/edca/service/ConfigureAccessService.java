package com.bt.edca.service;



import com.bt.edca.common.dto.ConfigureAccessDTO;
/**
 * This interface invokes the serviceImpl method and 
 * returns the configure access details.
 * 
 * @author Mathivathana
 */
public interface ConfigureAccessService {
  
  /**
   * Declaration of method to be implemented in ConfigureAccessServiceImpl. 
   * @param siteId.
   * @param serviceInstanceId.
   * @return ConfigureAccessDTO.
   */
  public ConfigureAccessDTO getConfigureAccessDetails(String siteId,String serviceInstance);
  
  /**
   * Declaration of method to be implemented in ConfigureAccessServiceImpl. 
   * @param siteLocationId.
   * @param serviceInstance.
   * @param gpop.
   * @return GpopColour.
   */
  public String getGpopColour(int siteLocationId,String serviceInstance,String gpop);
}
