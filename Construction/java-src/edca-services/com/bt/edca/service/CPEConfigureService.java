package com.bt.edca.service;
import com.bt.edca.common.dto.CPEConfigureDTO;

import java.util.List;

/**
 * This interface invokes the serviceImpl method and returns the list of Cpe
 * Configure Details.
 * 
 * @author SATHYA.
 */

public interface CPEConfigureService {
  /**
   * Gets the values from ServiceImpl.
   * 
   * @param .serviceInstanceId
   * @return list.
   */
  public List<CPEConfigureDTO> getCPEConfigureDetails(String serviceInstanceId);

  /**
   * Gets the values from ServiceImpl.
   * 
   * @param .serviceInstanceId
   * @return CPEConfigureDTO.
   */

  public CPEConfigureDTO getCPEAccess(String serviceInstanceId);

}
