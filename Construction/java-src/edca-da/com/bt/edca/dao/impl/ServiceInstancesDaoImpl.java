package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.ServiceInstancesDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.dao.ServiceInstancesDao;

import java.util.List;

/**
 * Description : Class used to execute Query. Fetching the
 * ServiceInstancesDetails.
 * 
 * @author SATHYA.
 *
 */
public class ServiceInstancesDaoImpl implements ServiceInstancesDao {

  @SuppressWarnings("unchecked")
  @Override
  public List<ServiceInstancesDomain> getServiceInstancesDetails(String siteLocationId) {

    /**
     * Description : Gets serviceInstances page using this parameters.
     * 
     * @param siteLocationId.
     * @return serviceInstanceDetailsList.
     */

    List<ServiceInstancesDomain> serviceInstanceDetailsList = HibernateQueryUtil.executeQuery(
        GET_SERVICEINSTANCES_BY_SITELOCATIONID, new String[] { "siteLocationId" }, new Object[] { siteLocationId });
    return serviceInstanceDetailsList;
  }

}
