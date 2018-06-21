package com.bt.edca.dao;

import com.bt.edca.da.domain.CPEConfigureDomain;
import com.bt.edca.da.domain.ConfigureCpeDomain;

import java.util.List;

/**
 * CPEConfigureDaoImpl Implements CPEConfigureDao interface. Gets the
 * cpeConfigure details in list by serviceinstanceId.
 * 
 * @author SATHYA.
 * 
 */
public interface CPEConfigureDao {

  /**
   * Format to get cpeConfigureDetails in list by serviceInstanceId.
   */
  String GET_CPECONFIGURE_BY_SERVICEINSTANCEID = "getCPEConfigureByServiceInstanceId";

  /**
   * Gets cpeConfigureDetails using this parameters.
   * 
   * @param serviceInstanceId.
   * @return.
   */

  public List<CPEConfigureDomain> getCPEConfigureDetails(String serviceInstanceId);

  /**
   * Format to get Configure details based on siteId and ServiceInstance.
   */
  String GET_CONFIGURE_ACCESS_DETAILS = "getConfigureCpeDetails";

  /**
   * This methods gets the configure access details with parameters
   * 
   * @param serviceInstance.
   * @return configureAccessDomain.
   */
  public List<ConfigureCpeDomain> getConfigureAccessDetails(String serviceInstanceId);
}
