package com.bt.edca.service.impl;

import java.util.List;

import com.bt.edca.common.dto.ConfigureAccessTypeDTO;
import com.bt.edca.da.domain.ConfigAccTypeBandwidthDomain;
import com.bt.edca.da.domain.ConfigureAccessTypeDomain;
import com.bt.edca.da.domain.ConfigAccTypeQrefDomain;
import com.bt.edca.dao.ConfigureAccessTypeDao;
import com.bt.edca.service.ConfigureAccessTypeService;
import com.bt.edca.util.HelperMethods;

 /**
 * Description : Implementation of interface ConfigureAccessTypeService . 
 * @author Rohit
 */
public class ConfigureAccessTypeServiceImpl implements 
ConfigureAccessTypeService {
/**
   * object of ConfigureAccessTypeDao .
   */
private ConfigureAccessTypeDao configureAccessTypeDao;
   /**
   * @param serviceInstanceId .
   *          .
   * @return configureAccessTypeDTO -object of ConfigureAccessTypeDTODTO .
   */
  
  public ConfigureAccessTypeDTO getConfigureAccessType(
      String serviceInstanceId) {
      ConfigureAccessTypeDTO configureAccessTypeDTO = 
        new ConfigureAccessTypeDTO();
    
    List<ConfigureAccessTypeDomain> configureAccessTypeList = 
        configureAccessTypeDao.getConfigureAccessType(serviceInstanceId);

    if (!HelperMethods.isNullOrEmpty(configureAccessTypeList)) {
      for (ConfigureAccessTypeDomain configureAccessTypeDomain  
        : configureAccessTypeList) {
        configureAccessTypeDTO.setAccessType(
            configureAccessTypeDomain.getAccessType());
        configureAccessTypeDTO.setAccessCktResilenceDiversity(
            configureAccessTypeDomain.getAccessCktResilenceDiversity());
        configureAccessTypeDTO.setExistingCircuit(
            configureAccessTypeDomain.getExistingCircuit());
        configureAccessTypeDTO.setInterfaceType(
            configureAccessTypeDomain.getInterfaceType());
        configureAccessTypeDTO.setJdsuProbeOnPe(
            configureAccessTypeDomain.getJdsuProbeOnPe());
        configureAccessTypeDTO.setElectricalInterface(
            configureAccessTypeDomain.getElectricalInterface());
        configureAccessTypeDTO.setPhysicalConnector(
            configureAccessTypeDomain.getPhysicalConnector());
        configureAccessTypeDTO.setAccessBearerPhyConn(
            configureAccessTypeDomain.getAccessBearerPhyConn());
        configureAccessTypeDTO.setPhysicalAccessSpeed(
            configureAccessTypeDomain.getPhysicalAccessSpeed());
        configureAccessTypeDTO.setAdditionalAccess(
            configureAccessTypeDomain.getAdditionalAccess());
        configureAccessTypeDTO.setEthPhaseType(
            configureAccessTypeDomain.getEthPhaseType());
        configureAccessTypeDTO.setPortSpeed(
            configureAccessTypeDomain.getPortSpeed());
        configureAccessTypeDTO.setNetwork(
            configureAccessTypeDomain.getNetwork());
        configureAccessTypeDTO.setFraming(
            configureAccessTypeDomain.getFraming());
        configureAccessTypeDTO.setLmi(
            configureAccessTypeDomain.getLmi());
        configureAccessTypeDTO.setFrameType(
            configureAccessTypeDomain.getFrameType());
        configureAccessTypeDTO.setFrPvcClassOfService(
            configureAccessTypeDomain.getFrPvcClassOfService());
        configureAccessTypeDTO.setNumOfTimeSlot(
            configureAccessTypeDomain.getNumOfTimeSlot());
      }
    }
    return configureAccessTypeDTO;
  }
  
  /**
   * @param serviceInstanceId .
   * @return configureAccessTypeDTO .
   */
  public ConfigureAccessTypeDTO getConfigureAccessTypeProcedure(
      String serviceInstanceId) {
    List<ConfigureAccessTypeDomain> configureAccessTypeProcedureList = 
        configureAccessTypeDao.getConfigureDelegation(serviceInstanceId);    
    ConfigureAccessTypeDTO configureAccessTypeDTO = 
        new ConfigureAccessTypeDTO();
    if (!HelperMethods.isNullOrEmpty(configureAccessTypeProcedureList)) {
      for (ConfigureAccessTypeDomain configureAccessTypeDomain 
          : configureAccessTypeProcedureList) {       
        configureAccessTypeDTO.setCurrency(
            configureAccessTypeDomain.getCurrency());
        configureAccessTypeDTO.setSupplierQuotedNrc(
            configureAccessTypeDomain.getSupplierQuotedNrc());
        configureAccessTypeDTO.setSupplierQuotedMrc(
            configureAccessTypeDomain.getSupplierQuotedMrc());
        configureAccessTypeDTO.setConvertedCurrency(
            configureAccessTypeDomain.getConvertedCurrency());
        configureAccessTypeDTO.setBandwidthCircuit(
            configureAccessTypeDomain.getBandwidthCircuit());
        configureAccessTypeDTO.setCheapestSolution(
            configureAccessTypeDomain.getCheapestSolution());
        configureAccessTypeDTO.setOptimalAccessTech(
            configureAccessTypeDomain.getOptimalAccessTech());
        configureAccessTypeDTO.setCeLimit(
            configureAccessTypeDomain.getCeLimit());
        configureAccessTypeDTO.setRegionalLimitl(
            configureAccessTypeDomain.getRegionalLimitl());       
      }   
  }  
    return configureAccessTypeDTO;
  }
  
  /**
   * @param serviceInstanceId .
   * @return aggregatedValueList .
   */
  
  public ConfigureAccessTypeDTO getAggregatedValue(String serviceInstanceId) {
  List<ConfigAccTypeBandwidthDomain> aggregatedValueList = 
      configureAccessTypeDao.getAggregatedMulticastValue(serviceInstanceId);
    ConfigureAccessTypeDTO configureAccessTypeDTO = 
      new ConfigureAccessTypeDTO();
      if (!HelperMethods.isNullOrEmpty(aggregatedValueList)) {
      for (ConfigAccTypeBandwidthDomain configAccTypeBandwidthDomain 
          : aggregatedValueList) {
          configureAccessTypeDTO.setAggregatedMulticastBandwidth(
      configAccTypeBandwidthDomain.getAggregatedMulticastBandwidth());
        }
       }
    return  configureAccessTypeDTO;     
  }
/**
 *@param serviceInstanceId .
 *@return configureAccessTypeDTO . 
 */
 public ConfigureAccessTypeDTO getQrefValue(String serviceInstanceId) {
 ConfigureAccessTypeDTO configureAccessTypeDTO = 
        new ConfigureAccessTypeDTO();
    List<ConfigAccTypeQrefDomain> qrefValueList = 
           configureAccessTypeDao.getQrefValue(serviceInstanceId);
    if (!HelperMethods.isNullOrEmpty(qrefValueList)) {
      for (ConfigAccTypeQrefDomain configAccTypeQrefDomain 
          : qrefValueList) {
        configureAccessTypeDTO.setQref(configAccTypeQrefDomain.getQref());
        configureAccessTypeDTO.setAccessOffer(
            configAccTypeQrefDomain.getAccessOffer());     
      }
    }    
    return configureAccessTypeDTO;    
  }
   /**
   * Description : method for getting the database connection . 
   * @return configureAccessTypeDao .
   */
  public ConfigureAccessTypeDao getConfigureAccessTypeDao() {
    return configureAccessTypeDao;
  }

  /**
   * Description : setter for ConfigureAccessTypeDao .
   * 
   * @param configureAccessTypeDao .
   *          .
   */
  public void setConfigureAccessTypeDao(ConfigureAccessTypeDao 
      configureAccessTypeDao) {
    this.configureAccessTypeDao = configureAccessTypeDao;
  }
}
