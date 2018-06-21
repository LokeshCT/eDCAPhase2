package com.bt.edca.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bt.edca.common.dto.ConfigureCardCableDTO;
import com.bt.edca.da.domain.CPEConfigureDomain;
import com.bt.edca.da.domain.ConfigureDeviceCableDomain;
import com.bt.edca.dao.ConfigureCardCableDao;
import com.bt.edca.service.ConfigureCardCableService;
import com.bt.edca.util.HelperMethods;

/**
 * Description : This class implements the ConfigureCardCableService Interface. This
 * class mainly invokes the interface method and return the configure cable List
 * values.
 * 
 * @author SATHYA.
 */
public class ConfigureCardCableServiceImpl implements ConfigureCardCableService {
  /**
   * Description : Reference variable.
   */
  private ConfigureCardCableDao configureCardCableDao;
  /**
   * Description : Gets the value from ConfigureCardCableDao
   * 
   * @return configureCardCableDao.
   */
  public ConfigureCardCableDao getConfigureCardCableDao() {
    return configureCardCableDao;
  }
  /**
   * Description : Sets the value from ConfigureCardCableDao
   * 
   * @return configureCardCableDao.
   */
  public void setConfigureCardCableDao(ConfigureCardCableDao configureCardCableDao) {
    this.configureCardCableDao = configureCardCableDao;
  }
  /**
   * Description : This methods invokes the getConfigureCardDetails from
   * ConfigureCardCableDao with this parameters for getting getConfigureCardDetails from
   * Dao.
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */
  @Override
  public String cardCable(String serviceInstanceId) {

    String configure = configureCardCableDao.getConfigureCardCable(serviceInstanceId);

    return configure;
  }
  /**
   * Description : This methods invokes the getConfigureCardDetails from
   * ConfigureCardCableDao with this parameters for getting getConfigureCardDetails from
   * Dao.
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */
  public List<ConfigureCardCableDTO> getMultipleDetails(String serviceInstanceId) {
    List<ConfigureCardCableDTO> configureCableListDTO = new ArrayList<ConfigureCardCableDTO>();
    List<CPEConfigureDomain> cpeDetails = configureCardCableDao.getallDevices(serviceInstanceId);
    String baseRouterName = null, pisystem_name = null;
    int p_i_eos_check = 0;
    List<ConfigureDeviceCableDomain> cards = configureCardCableDao.getallDevicesCardCables(baseRouterName,
        pisystem_name, p_i_eos_check);
    if (!HelperMethods.isNullOrEmpty(cpeDetails)) {
      for (CPEConfigureDomain cpeDomain : cpeDetails) {

        ConfigureCardCableDTO ConfigureCardCableDTO = new ConfigureCardCableDTO();
        ConfigureCardCableDTO.setBaseRouter(cpeDomain.getBaseRouter());
        ConfigureCardCableDTO.setChassis(cpeDomain.getChassis());
        ConfigureCardCableDTO.setCpeSequenceId(cpeDomain.getCpeSequenceId());
        ConfigureCardCableDTO.setCpeUsage(cpeDomain.getCpeUsage());
        configureCableListDTO.add(ConfigureCardCableDTO);
      }
    }

    return configureCableListDTO;
  }
  /**
   * Description : This methods invokes the getConfigureCardDetails from
   * ConfigureCardCableDao with this parameters for getting getConfigureCardDetails from
   * Dao.
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */
  public List<ConfigureCardCableDTO> getCardDetails(String baseRouterName, String pisystem_name, int p_i_eos_check) {
    List<ConfigureCardCableDTO> configureCableListDTO = new ArrayList<ConfigureCardCableDTO>();
    List<ConfigureDeviceCableDomain> cards = configureCardCableDao.getallDevicesCardCables(baseRouterName,
        pisystem_name, p_i_eos_check);

    if (!HelperMethods.isNullOrEmpty(cards)) {
      for (ConfigureDeviceCableDomain cpeDomain : cards) {

        ConfigureCardCableDTO ConfigureCardCableDTO = new ConfigureCardCableDTO();
        ConfigureCardCableDTO.setPartName(cpeDomain.getPartName());
        ConfigureCardCableDTO.setPartType(cpeDomain.getPartType());
        ConfigureCardCableDTO.setSubElementType(cpeDomain.getSubElementType());

        configureCableListDTO.add(ConfigureCardCableDTO);
      }
    }

    return configureCableListDTO;
  }

}
