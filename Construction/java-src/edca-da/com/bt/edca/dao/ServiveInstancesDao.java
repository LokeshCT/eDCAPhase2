package com.bt.edca.dao;

import java.util.List;

import com.bt.edca.da.domain.ServiceInstancesDomain;
/**
 * Description:ServiceInsatncesDaoImpl Implements ServiceInstancesDao interface. Gets the
 * serviceInstances details in list by siteLocationId.
 * 
 * @author SATHYA.
 * 
 */
public interface ServiveInstancesDao {
  /**
   * Format to get serviceInstancesDetails in list by siteLocationId.
   */
  static String GET_SERVICEINSTANCES_BY_SITELOCATIONID = "getserviceInstancesbysiteLocationId";
  /**
   * Gets serviceInstancesDetails using this parameters.
   * 
   * @param siteLocationId.
   * @return serviceInstnacesDetails.
   */
  public List<ServiceInstancesDomain> getServiceInstancesDetails(int siteLocationId);

}
