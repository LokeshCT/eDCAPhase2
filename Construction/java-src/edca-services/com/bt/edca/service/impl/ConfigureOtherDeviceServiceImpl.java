package com.bt.edca.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bt.edca.common.dto.ConfigureOtherDevicesDTO;
import com.bt.edca.da.domain.CPEConfigureDomain;
import com.bt.edca.da.domain.ServiceInstancesDomain;
import com.bt.edca.da.domain.OtherDevicesDomain;
import com.bt.edca.da.domain.OtherDomain;
import com.bt.edca.dao.ConfigureOtherDeviceDao;
import com.bt.edca.service.ConfigureOtherDeviceService;
import com.bt.edca.util.HelperMethods;
/**
 * Description : This class implements the ConfigureOtherDeviceService Interface. This
 * class mainly invokes the interface method and return the ConfigureOtherDevice List
 * values.
 * 
 * @author SATHYA.
 */
public class ConfigureOtherDeviceServiceImpl implements ConfigureOtherDeviceService {
  /**
   * Description : Reference variable.
   */
  private ConfigureOtherDeviceDao configureOtherDeviceDao;
  /**
   * Description : Gets the value from ConfigureOtherDeviceDao
   * 
   * @return configureOtherDeviceDao.
   */
  public ConfigureOtherDeviceDao getConfigureOtherDeviceDao() {
    return configureOtherDeviceDao;
  }

  /**
   * Description : Sets the value from ConfigureOtherDeviceDao
   * 
   * @return configureOtherDeviceDao.
   */
  public void setConfigureOtherDeviceDao(ConfigureOtherDeviceDao configureOtherDeviceDao) {
    this.configureOtherDeviceDao = configureOtherDeviceDao;
  }
  /**
   * Description : This methods invokes the getConfigureDetails from
   * configureOtherDeviceDao with this parameters for getting configureOtherDevice Details from
   * Dao.
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */
  @Override
  public List<ConfigureOtherDevicesDTO> getConfigureDetails(int serviceInstanceId) {
    List<ConfigureOtherDevicesDTO> configureOtherDevicesDTOList = null;
    List<ServiceInstancesDomain> siteList = null;
    List<OtherDomain> isMarketPulse = configureOtherDeviceDao.getVpnDetails(serviceInstanceId);
    int count = GetMarketpulseStatus(isMarketPulse);
    siteList = configureOtherDeviceDao.getSiteDetails(serviceInstanceId);
    configureOtherDevicesDTOList = new ArrayList<ConfigureOtherDevicesDTO>();
    ConfigureOtherDevicesDTO configureOtherDevicesDTO = new ConfigureOtherDevicesDTO();
    if (!HelperMethods.isNullOrEmpty(siteList)) {

      if (count > 0) {
        
      
        if (siteList.get(0).getServiceDemarcationOption() != null) {

          if (siteList.get(0).getServiceDemarcationOption().indexOf("TDM/CAS") >= 0) 
         {
            if (!HelperMethods.isNullOrEmpty(siteList)) {
              for (ServiceInstancesDomain siteDomain : siteList) {
                configureOtherDevicesDTO.setServiceDemarcationOption(siteDomain.getServiceDemarcationOption());
                configureOtherDevicesDTO.setLanSwitch(siteDomain.getLanSwitch());
                configureOtherDevicesDTOList.add(configureOtherDevicesDTO);
              }
            }
          }
                 
        }
      }
    }

    return configureOtherDevicesDTOList;
  }
  /**
   * Description : This method used for count the values of marketPulse
   * 
   * 
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */
  public int GetMarketpulseStatus(List<OtherDomain> isMarketPulse) {
    int count = 0;
    for (OtherDomain domain : isMarketPulse) {

      String connectionName = domain.getConnectionName();
      String connectionType = domain.getConnectiionType();
      int name = connectionName.indexOf("Market Pulse");
      int type = connectionType.indexOf("Market Pulse");

      if ((name >= 0) && (type >= 0)) {

        count = count + 1;

      }

    }

    return count;

  }
  /**
   * Description : This methods invokes the getConfigureDetails from
   * configureOtherDeviceDao with this parameters for getting configureOtherDevice Details from
   * Dao.
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */
  public List<ConfigureOtherDevicesDTO> getSiteDetails(String siteId, String orderId, int serviceInstanceId) {

    ConfigureOtherDevicesDTO configureOtherDevicesDTO = new ConfigureOtherDevicesDTO();

    List<OtherDevicesDomain> otherDevicesDomains = configureOtherDeviceDao.getSiteDetails(siteId, orderId);
    List<ConfigureOtherDevicesDTO> configureList = new ArrayList<ConfigureOtherDevicesDTO>();

    if (!HelperMethods.isNullOrEmpty(otherDevicesDomains)) {

      for (OtherDevicesDomain otherDevicesDomain : otherDevicesDomains) {
        configureOtherDevicesDTO.setBtradianzanalytics(otherDevicesDomain.getBtradianzanalytics());
        configureOtherDevicesDTO.setBtradianzmonitor(otherDevicesDomain.getBtradianzmonitor());
        configureList.add(configureOtherDevicesDTO);
      }
    }

    return configureList;
  }
  /**
   * Description : This methods invokes the getConfigureDetails from
   * configureOtherDeviceDao with this parameters for getting configureOtherDevice Details from
   * Dao.
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */
  public List<ConfigureOtherDevicesDTO> getAlldeviceCpe(int serviceInstanceId) {
    List<CPEConfigureDomain> cpeDetails = configureOtherDeviceDao.getallDevices(serviceInstanceId);
    List<ConfigureOtherDevicesDTO> configureOtherDevicesDTOList = new ArrayList<ConfigureOtherDevicesDTO>();

    if (!HelperMethods.isNullOrEmpty(cpeDetails)) {
      for (CPEConfigureDomain cpeDomain : cpeDetails) {
        ConfigureOtherDevicesDTO configureOtherDevicesDTO = new ConfigureOtherDevicesDTO();
        configureOtherDevicesDTO.setBaseRouter(cpeDomain.getBaseRouter());
        configureOtherDevicesDTO.setChassis(cpeDomain.getChassis());
        configureOtherDevicesDTO.setCpeSequenceId(cpeDomain.getCpeSequenceId());
        configureOtherDevicesDTO.setCpeUsage(cpeDomain.getCpeUsage());
        configureOtherDevicesDTOList.add(configureOtherDevicesDTO);

      }
    }
    return configureOtherDevicesDTOList;
  }
  /**
   * Description : This method used find the size of the lists.
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */
   public int size(int serviceInstanceId,String siteId,String orderId)
   {
     
     List<OtherDomain> isMarketPulse = configureOtherDeviceDao.getVpnDetails(serviceInstanceId);
     List<ServiceInstancesDomain>   siteList = configureOtherDeviceDao.getSiteDetails(serviceInstanceId);
     List<OtherDevicesDomain> otherDevicesDomains = configureOtherDeviceDao.getSiteDetails(siteId, orderId);
     int one= isMarketPulse.size();
     int two= siteList.size();
     int three= otherDevicesDomains.size();
     int  total=one+two+three;
   
    return total;
          
    }
  
  
  
}
