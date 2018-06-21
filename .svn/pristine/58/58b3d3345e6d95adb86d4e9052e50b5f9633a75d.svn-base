package com.bt.edca.service.impl;

import com.bt.edca.common.dto.CPEConfigureDTO;
import com.bt.edca.da.domain.CPEConfigureDomain;
import com.bt.edca.da.domain.ConfigureCpeDomain;
import com.bt.edca.dao.CPEConfigureDao;
import com.bt.edca.service.CPEConfigureService;
import com.bt.edca.util.HelperMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : This class implements the CPECopnfigureService Interface. This
 * class mainly invokes the interface method and return the cpeConfiogure List
 * values.
 * 
 * @author SATHYA.
 */

public class CPEConfigureServiceImpl implements CPEConfigureService {

  /**
   * Description : Reference variable.
   */
  private CPEConfigureDao cpeConfigureDao;

  /**
   * Description : Gets the value from CPEConfigureDao
   * 
   * @return cpeConfigureDao.
   */
  public CPEConfigureDao getCpeConfigureDao() {
    return cpeConfigureDao;
  }

  /**
   * Description : Sets the value from CPEConfigureDao
   * 
   * @return cpeConfigureDao.
   */
  public void setCpeConfigureDao(CPEConfigureDao cpeConfigureDao) {
    this.cpeConfigureDao = cpeConfigureDao;
  }

  /**
   * Description : This methods invokes the getCPEConfigureDetails from
   * CPEConfigureDao with this parameters for getting CpeConfigure Details from
   * Dao.
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */
  @Override
  public List<CPEConfigureDTO> getCPEConfigureDetails(String serviceInstanceId) {

    List<CPEConfigureDomain> cpeConfigureList = cpeConfigureDao.getCPEConfigureDetails(serviceInstanceId);
    List<CPEConfigureDTO> cpeConfigureDTOList = new ArrayList<CPEConfigureDTO>();
    if (!HelperMethods.isNullOrEmpty(cpeConfigureList)) {
      for (CPEConfigureDomain cpeConfigureDomain : cpeConfigureList) {
        CPEConfigureDTO cpeConfigureDTO = new CPEConfigureDTO();

        cpeConfigureDTO.setBaseRouter(cpeConfigureDomain.getBaseRouter());
        cpeConfigureDTO.setCatCode(cpeConfigureDomain.getCatCode());
        cpeConfigureDTO.setCcme_Feature(cpeConfigureDomain.getCcme_Feature());
        cpeConfigureDTO.setChassis(cpeConfigureDomain.getChassis());
        cpeConfigureDTO.setCpe_Owner(cpeConfigureDomain.getCpe_Owner());
        cpeConfigureDTO.setCpe_Quote_Ref(cpeConfigureDomain.getCpe_Quote_Ref());
        cpeConfigureDTO.setCpeOrderingType(cpeConfigureDomain.getCpeOrderingType());
        cpeConfigureDTO.setCpeTerm(cpeConfigureDomain.getCpeTerm());
        cpeConfigureDTO.setCpeUsage(cpeConfigureDomain.getCpeUsage());
        cpeConfigureDTO.setdRam(cpeConfigureDomain.getdRam());
        cpeConfigureDTO.setEquipment_Financing(cpeConfigureDomain.getEquipment_Financing());
        cpeConfigureDTO.setFlashMemory(cpeConfigureDomain.getFlashMemory());
        cpeConfigureDTO.setHardDisk(cpeConfigureDomain.getHardDisk());
        cpeConfigureDTO.setHwic_slots(cpeConfigureDomain.getHwic_slots());
        cpeConfigureDTO.setIos(cpeConfigureDomain.getIos());
        cpeConfigureDTO.setLanType(cpeConfigureDomain.getLanType());
        cpeConfigureDTO.setLic_cue_Language(cpeConfigureDomain.getLic_cue_Language());
        cpeConfigureDTO.setLic_cue_Licence(cpeConfigureDomain.getLic_cue_Licence());
        cpeConfigureDTO.setLic_Ipt_Cme(cpeConfigureDomain.getLic_Ipt_Cme());
        cpeConfigureDTO.setLic_Ipt_Srst(cpeConfigureDomain.getLic_Ipt_Srst());
        cpeConfigureDTO.setLocalization_Kit(cpeConfigureDomain.getLocalization_Kit());
        cpeConfigureDTO.setMaintenanceOption(cpeConfigureDomain.getMaintenanceOption());
        cpeConfigureDTO.setMis_Item(cpeConfigureDomain.getMis_Item());
        cpeConfigureDTO.setModel(cpeConfigureDomain.getModel());
        cpeConfigureDTO.setNetwork_Modules(cpeConfigureDomain.getNetwork_Modules());
        cpeConfigureDTO.setPartnerBidReference(cpeConfigureDomain.getPartnerBidReference());
        cpeConfigureDTO.setPhysical_Site_Servey(cpeConfigureDomain.getPhysical_Site_Servey());
        cpeConfigureDTO.setPort_Adaptors(cpeConfigureDomain.getPort_Adaptors());
        cpeConfigureDTO.setPowerCables(cpeConfigureDomain.getPowerCables());
        cpeConfigureDTO.setPowerCord(cpeConfigureDomain.getPowerCord());
        cpeConfigureDTO.setProgramName(cpeConfigureDomain.getProgramName());
        cpeConfigureDTO.setProductType(cpeConfigureDomain.getProductType());
        cpeConfigureDTO.setRack_Mount_Kit(cpeConfigureDomain.getRack_Mount_Kit());
        cpeConfigureDTO.setRps(cpeConfigureDomain.getRps());
        cpeConfigureDTO.setSharedPortAdaptors(cpeConfigureDomain.getSharedPortAdaptors());
        cpeConfigureDTO.setUsb(cpeConfigureDomain.getUsb());
        cpeConfigureDTO.setVoiceCards(cpeConfigureDomain.getVoiceCards());
        cpeConfigureDTO.setWic_slots(cpeConfigureDomain.getWic_slots());
        cpeConfigureDTO.setCpe_firm_ware(cpeConfigureDomain.getCpe_firm_ware());
        cpeConfigureDTO.setBaseConfig(cpeConfigureDomain.getBaseConfig());
        cpeConfigureDTO.setBuiltinslots(cpeConfigureDomain.getBuiltinslots());
        cpeConfigureDTO.setDartNumber(cpeConfigureDomain.getDartNumber());
        cpeConfigureDTO.setLicense(cpeConfigureDomain.getLicense());
        cpeConfigureDTO.setSerialNumber(cpeConfigureDomain.getSerialNumber());
        cpeConfigureDTO.setPortFolioNeeded(cpeConfigureDomain.getPortFolioNeeded());
        cpeConfigureDTOList.add(cpeConfigureDTO);

      }
    }

    return cpeConfigureDTOList;
  }

  /**
   * Description : This methods invokes the getCPEAccess from ConfigureAccessDao
   * with this parameters for getting ConfigureAccess Details from Dao.
   * 
   * @param serviceInstanceId.
   * 
   * @return cpeList.
   */

  public CPEConfigureDTO getCPEAccess(String serviceInstanceId) {

    List<ConfigureCpeDomain> configureAccess = cpeConfigureDao.getConfigureAccessDetails(serviceInstanceId);
    CPEConfigureDTO cpeConfigureDTO = new CPEConfigureDTO();
    if (!HelperMethods.isNullOrEmpty(configureAccess)) {
      for (ConfigureCpeDomain cpeConfigureDomain : configureAccess) {
        cpeConfigureDTO.setcPE_Rack_Shelf(cpeConfigureDomain.getcPE_Rack_Shelf());
      }
    }
    return cpeConfigureDTO;
  }
}
