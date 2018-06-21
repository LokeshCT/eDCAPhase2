package com.bt.edca.service.impl;

import com.bt.edca.common.dto.ConfigureAccessDTO;
import com.bt.edca.da.domain.ConfigureAccessDomain;
import com.bt.edca.dao.ConfigureAccessDao;
import com.bt.edca.service.ConfigureAccessService;
import com.bt.edca.util.EdcaLogger;


public class ConfigureAccessServiceImpl implements ConfigureAccessService {
  
  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(ConfigureAccessServiceImpl.class);

  /**
   * Reference variable.
   */
  private ConfigureAccessDao configureAccessDao;

  /**
   * @return the configureAccessDao
   */
  public ConfigureAccessDao getConfigureAccessDao() {
    return configureAccessDao;
  }

  /**
   * @param configureAccessDao the configureAccessDao to set
   */
  public void setConfigureAccessDao(ConfigureAccessDao configureAccessDao) {
    this.configureAccessDao = configureAccessDao;
  }

  /**
   * This method invokes getConfigureAccessDetails() from ConfigureAccessDao
   * with this parameters 
   * @param siteId.
   * @param serviceInstanceId.
   * @return ConfigureAccessDTO.
   */
  @Override
  public  ConfigureAccessDTO getConfigureAccessDetails(String siteId,String serviceInstance)
  {
    ConfigureAccessDTO configureAccessDTO = new ConfigureAccessDTO();
    
    ConfigureAccessDomain domainList=configureAccessDao.getConfigureAccessDetails(siteId, serviceInstance);
    
        configureAccessDTO.setServiceInstance(domainList.getServiceInstance());
        configureAccessDTO.setGpop(domainList.getGpop());
        configureAccessDTO.setDvbpop(domainList.getDvbpop());
        configureAccessDTO.setApop(domainList.getApop());
        configureAccessDTO.setAccessSupplier(domainList.getAccessSupplier());
        configureAccessDTO.setAccessEnhancedServiceRest(domainList.getAccessEnhancedServiceRest());
        configureAccessDTO.setAccessEntryPoint(domainList.getAccessEntryPoint());
        configureAccessDTO.setBgpCount(domainList.getBgpCount());
        configureAccessDTO.setPortSplBidId(domainList.getPortSplBidId());
        configureAccessDTO.setSiteLocationId(domainList.getSiteLocationId());

    return configureAccessDTO;
    
  }
  
  /**
   * This method invokes getGpopColour() from ConfigureAccessDao
   * with this parameters 
   * @param siteLocationId.
   * @param serviceInstance.
   * @param gpop.
   * @return GpopColour.
   */   
  
  public String getGpopColour(int siteLocationId,String serviceInstance,String gpop)
  {
    String gpopcolour=configureAccessDao.getGpopColour(siteLocationId, serviceInstance, gpop);
    return gpopcolour;
  }
 
}
