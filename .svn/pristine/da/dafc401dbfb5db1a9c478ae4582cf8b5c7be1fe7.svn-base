package com.bt.edca.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bt.edca.common.dto.ConfigureVlanDTO;
import com.bt.edca.da.domain.ConfigureLanSwitchDomain;
import com.bt.edca.da.domain.ConfigureVlanDomain;
import com.bt.edca.da.domain.ConfigureVlanSanDomain;
import com.bt.edca.dao.ConfigureVlanDao;
import com.bt.edca.service.ConfigureVlanService;
import com.bt.edca.util.HelperMethods;

/**
 * Description : This class implements the ConfigureVlanService Interface. This
 * class invokes the interface method and returns the
 * configureVlanDomainUltraList.
 * 
 * @author Jagan kumar G.
 */

public class ConfigureVlanServiceImpl implements ConfigureVlanService {

  /**
   * Description : Reference variable.
   */
  private ConfigureVlanDao configureVlanDao;

  /**
   * Description : Gets the value from ConfigureVlanDao
   * 
   * @return configureVlanDao.
   */
  public ConfigureVlanDao getConfigureVlanDao() {
    return configureVlanDao;
  }

  /**
   * Description : Sets the value from ConfigureVlanDao
   * 
   * @return configureVlanDao.
   */
  public void setConfigureVlanDao(ConfigureVlanDao configureVlanDao) {
    this.configureVlanDao = configureVlanDao;
  }

  /**
   * Description : This methods invokes the getConfigureVlanUltraDetails from
   * ConfigureVlanDao .
   * 
   * @param serviceInstanceId.
   * 
   * @return configureVlanDTOUltraList.
   */
  public List<ConfigureVlanDTO> getConfigureVlanDetails(String serviceInstanceId) {

    String cpeName = null;
    boolean juniper = false;
    boolean adva = false;
    boolean bfd = false;
    String vlanTypeManufacturer = null;
    String lblError = null;
 
    List<ConfigureVlanDTO> configureVlanDTOUltraList = new ArrayList<ConfigureVlanDTO>();
    List<ConfigureVlanDomain> configureVlanDomainUltraList = configureVlanDao
        .getConfigureVlanUltraDetails(serviceInstanceId);
    List<ConfigureLanSwitchDomain> distinctLanSwitchList = configureVlanDao.getLanSwitch(serviceInstanceId);

    if (!HelperMethods.isNullOrEmpty(configureVlanDomainUltraList)) {
      for (ConfigureLanSwitchDomain vlanUltraDomain : distinctLanSwitchList) {
        cpeName = vlanUltraDomain.getLanSwitch();
      }
    }
    String manufacturer = configureVlanDao.getCpeManufacture("Spring", cpeName);
    List sanName = configureVlanDao.getSanName(serviceInstanceId);

    if (manufacturer == "JUNIPER") {
      juniper = true;
    } else if (manufacturer == "ADVA") {
      adva = true;
    }

    if (adva == true && juniper == false) {
      for (ConfigureVlanDomain vlanDomain : configureVlanDomainUltraList) {

        vlanTypeManufacturer = vlanDomain.getVlanType();

        if (vlanTypeManufacturer == "BFD VLAN") {
          bfd = true;
          if (bfd == true) {
            lblError = "For ADVA Devices BFD VLAN is not supported";
            ConfigureVlanDTO configure = new ConfigureVlanDTO();
            configure.setLblError(lblError);
            configureVlanDTOUltraList.add(configure);

          }
        }

      }
    }
    if (!HelperMethods.isNullOrEmpty(configureVlanDomainUltraList)) {
      for (ConfigureVlanDomain vlanUltraDomain : configureVlanDomainUltraList) {
        ConfigureVlanDTO configureVlanDTO = new ConfigureVlanDTO();

        configureVlanDTO.setPafName(vlanUltraDomain.getPafName());
        configureVlanDTO.setVlanId(vlanUltraDomain.getVlanId());
        configureVlanDTO.setVlanType(vlanUltraDomain.getVlanType());
        configureVlanDTO.setaEndSite(vlanUltraDomain.getaEndSite());
        configureVlanDTO.setzEndSite(vlanUltraDomain.getzEndSite());
        configureVlanDTO.setBandWidth(vlanUltraDomain.getBandWidth());
        configureVlanDTO.setBroadCast(vlanUltraDomain.getBroadCast());
        configureVlanDTO.setaEndVlanId(vlanUltraDomain.getaEndVlanId());
        configureVlanDTO.setzEndVlanId(vlanUltraDomain.getzEndVlanId());
        configureVlanDTO.setOsiLayer(vlanUltraDomain.getOsiLayer());
        configureVlanDTO.setProviderName(vlanUltraDomain.getProviderName());
        configureVlanDTO.setMemberName(vlanUltraDomain.getMemberName());
        configureVlanDTO.setVlanIdentifier(vlanUltraDomain.getVlanIdentifier());
        configureVlanDTO.setCustomerHandOffMode(vlanUltraDomain.getCustomerHandOffMode());

        configureVlanDTOUltraList.add(configureVlanDTO);

      }
    }

    return configureVlanDTOUltraList;
  }

  /**
   * implementation for the method declared in ConfigureVlanService.
   *  fetches serviceInstance. 
   */
  public String getServiceInstance(String serviceInstanceId) {

    String serviceInstance = configureVlanDao.getServiceInstance(serviceInstanceId);

    return serviceInstance;
  }

  /**
   * method fetches the ultra order details.
   * accessOptionFlag and lastMileAccess.
   * 
   */
  public List getUltraDetails(String siteId) {

    List ultraOrderDetails = configureVlanDao.getUltraOrderDetails(siteId);

    return ultraOrderDetails;
  }

  /**
   *implemtation to fetch sanName. 
   *    
   */
  public List<ConfigureVlanDTO> getSanNameDetails(String serviceInstanceId) {
    List<ConfigureVlanSanDomain> sanNameList = configureVlanDao.getSanName(serviceInstanceId);
    List<ConfigureVlanDTO> sanNameDTOList = new ArrayList<ConfigureVlanDTO>();
    for (ConfigureVlanSanDomain configVlan : sanNameList) {
      ConfigureVlanDTO configureVlanDTO = new ConfigureVlanDTO();
      configureVlanDTO.setSanName(configVlan.getSanName());
      configureVlanDTO.setSanId(configVlan.getSanId());
      sanNameDTOList.add(configureVlanDTO);

      System.out.println("Inservice IMpl SAN NAme" + configVlan.getSanName());

    }

    return sanNameDTOList;
  }

  /**
   *method fetches the san value.
   */
  public List<ConfigureVlanDTO> getSanValue(int sanId, int vlanId, String sanName) {
    List<ConfigureVlanSanDomain> sanNameList = configureVlanDao.getSanValue(sanId, vlanId, sanName);
    List<ConfigureVlanDTO> sanNameDTOList = new ArrayList<ConfigureVlanDTO>();
    for (ConfigureVlanSanDomain configVlan : sanNameList) {
      ConfigureVlanDTO configureVlanDTO = new ConfigureVlanDTO();
      configureVlanDTO.setSanName(configVlan.getSanName());
      configureVlanDTO.setSanValue(configVlan.getSanValue());
      sanNameDTOList.add(configureVlanDTO);

      System.out.println("Inservice IMpl SAN NAme" + configVlan.getSanValue());

    }

    return sanNameDTOList;
  }
}
