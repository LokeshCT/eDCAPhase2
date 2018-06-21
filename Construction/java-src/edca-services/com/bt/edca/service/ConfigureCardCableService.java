package com.bt.edca.service;

import java.util.List;

import com.bt.edca.common.dto.ConfigureCardCableDTO;
/**
 * This interface invokes the serviceImpl method and returns the list of 
 * ConfigureCableCards Details.
 * 
 * @author SATHYA.
 */
public interface ConfigureCardCableService {
  /**
   * Gets the values from ServiceImpl.
   * 
   * @param .serviceInstanceId.
   * @return string.
   */
  public String cardCable(String serviceInstanceId);
  /**
   * Gets the values from ServiceImpl.
   * 
   * @param .serviceInstanceId
   * @return ConfigureCardCableDTO.
   */
  public List<ConfigureCardCableDTO> getMultipleDetails(String serviceInstanceId);
  /**
   * Gets the values from ServiceImpl.
   * 
   * @param .serviceInstanceId.
   * @return list.
   */
  public List<ConfigureCardCableDTO> getCardDetails(String baseRouterName, String pisystem_name, int p_i_eos_check);

}
