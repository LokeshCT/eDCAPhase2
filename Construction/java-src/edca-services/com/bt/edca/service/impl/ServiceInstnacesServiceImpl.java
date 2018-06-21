package com.bt.edca.service.impl;

import com.bt.edca.common.dto.ServiceInstancesDTO;
import com.bt.edca.da.domain.ServiceInstancesDomain;
import com.bt.edca.dao.ServiceInstancesDao;

import com.bt.edca.service.ServiceInstancesService;
import com.bt.edca.util.HelperMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : This class implements the ServiceInstancesService Interface.
 * This class mainly invokes the interface method and return the
 * serviceInstances List values.
 * 
 * @author SATHYA.
 */
public class ServiceInstnacesServiceImpl implements ServiceInstancesService {

  /**
   * Description : Reference variable.
   */
  private ServiceInstancesDao serviceInstancesDao;

  /**
   * Description : Gets the value from ServiceInstancesDao
   * 
   * @return serviceInstancesDao.
   */
  public ServiceInstancesDao getServiceInstancesDao() {
    return serviceInstancesDao;
  }

  /**
   * Description : Sets the value from ServiceInstancesDao
   * 
   * @return serviceInstancesDao.
   */
  public void setServiceInstancesDao(ServiceInstancesDao serviceInstancesDao) {
    this.serviceInstancesDao = serviceInstancesDao;
  }

  /**
   * Description : This methods invokes the getServiceInstancesDetails from
   * ServiceInstancesDao with this parameters for getting ServiceInstances
   * Details from Dao.
   * 
   * @param siteLocationId.
   * 
   * @return serviceInstncesDTOList.
   */

  @Override
  public List<ServiceInstancesDTO> getServiceInstancesDetails(String siteLocationId) {

    List<ServiceInstancesDTO> serviceInstncesDTOList = new ArrayList<ServiceInstancesDTO>();
    List<ServiceInstancesDomain> serviceInstancesDetailsList = serviceInstancesDao
        .getServiceInstancesDetails(siteLocationId);

    if (!HelperMethods.isNullOrEmpty(serviceInstancesDetailsList)) {
      for (ServiceInstancesDomain serviceInstancesDomain : serviceInstancesDetailsList) {
        ServiceInstancesDTO serviceInstancessDTO = new ServiceInstancesDTO();
        serviceInstancessDTO.setServiceInstanceId(serviceInstancesDomain.getServiceInstanceId());
        serviceInstancessDTO.setServiceInstance(serviceInstancesDomain.getServiceInstance());
        serviceInstancessDTO.setLanSwitch(serviceInstancesDomain.getLanSwitch());
        serviceInstancessDTO.setTotalBandWidth(serviceInstancesDomain.getTotalBandWidth());
        serviceInstncesDTOList.add(serviceInstancessDTO);
      }

    }

    return serviceInstncesDTOList;
  }

}
