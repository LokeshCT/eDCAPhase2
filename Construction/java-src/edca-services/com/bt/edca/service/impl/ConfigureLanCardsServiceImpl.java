package com.bt.edca.service.impl;

import com.bt.edca.common.dto.ConfigureLanCardsDTO;
import com.bt.edca.da.domain.ConfigureLanCardsCountryDomain;
import com.bt.edca.da.domain.ConfigureLanCardsDomain;
import com.bt.edca.dao.ConfigureLanCardsDao;
import com.bt.edca.service.ConfigureLanCardsService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.HelperMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description : Service implementation class which copies data to
 * ConfigureLanCards.
 * 
 * @author Jagankumar G.
 * 
 */
public class ConfigureLanCardsServiceImpl implements ConfigureLanCardsService {

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(SitePageServiceImpl.class);

  /**
   * Reference variable.
   */
  private ConfigureLanCardsDao configureLanCardsDao;

  /**
   * implementaion of the method declared in Configurelancards service.
   * 
   * @return the map of all the lists of Configurelancards.
   */
  public Map<String, List> getConfigureLan(String serviceInstanceId) {

    String interfaceType = null;
    String electricInterface = null;
    String orderType = null;
    int noOfCircuits = 0;
    String cpeUsage = null;
    String country = null;
    int siteId = 0;

    List<ConfigureLanCardsDomain> configureLanCardsSelectionList = null;
    List<ConfigureLanCardsDomain> configureLanCardColumnList = null;
    List<ConfigureLanCardsDomain> opticalDetailsList = null;

    Map<String, List> configureLanCardsMap = new HashMap<String, List>();

    List<ConfigureLanCardsDTO> configureLanCardsDetailsList = new ArrayList<ConfigureLanCardsDTO>();
    List<ConfigureLanCardsDTO> configureLanSwitchDetailsList = 
        new ArrayList<ConfigureLanCardsDTO>();
    List<ConfigureLanCardsDTO> cardColumnList = new ArrayList<ConfigureLanCardsDTO>(); // Details
    List<ConfigureLanCardsDTO> cableShortNameList = new ArrayList<ConfigureLanCardsDTO>();
    List<ConfigureLanCardsDTO> opticalGridFirstColumnDTO = 
        new ArrayList<ConfigureLanCardsDTO>();
    List<ConfigureLanCardsDomain> cableSelectionList = new ArrayList<ConfigureLanCardsDomain>();
    List<ConfigureLanCardsDTO> cableSelectionTotalDTOList = new ArrayList<ConfigureLanCardsDTO>();
    List<ConfigureLanCardsDTO> opticalTotalModuleColumnDTO = new ArrayList<ConfigureLanCardsDTO>();

    List<ConfigureLanCardsDomain> lanCablesColumnList = null;
    List<ConfigureLanCardsDomain> opticalModulesList = null;
    List<ConfigureLanCardsDomain> cableSelectionTotalCableList = null;
    List<ConfigureLanCardsCountryDomain> siteDetailsList = null;

    List<ConfigureLanCardsDomain> configureLanCardsList =
        configureLanCardsDao.getConfigureLanCards(serviceInstanceId);
    for (ConfigureLanCardsDomain list : configureLanCardsList) {

      String cpename = list.getLanSwitch();
      int sequenceId = list.getSeqId();
      int serviceInstance = list.getServiceInstanceId();

      configureLanCardsSelectionList = configureLanCardsDao
          .getCardSelection(serviceInstanceId, sequenceId);
      configureLanCardColumnList =
          configureLanCardsDao.getCardColumnDetails(serviceInstanceId, sequenceId);
      String opticalCableType = "MISC";
      opticalDetailsList =
          configureLanCardsDao.getOpticalDetails(serviceInstanceId, sequenceId, opticalCableType);
      cableSelectionList = configureLanCardsDao.getCableSelection(serviceInstanceId, sequenceId);
      String totalCableType = "LAN";
      cableSelectionTotalCableList = 
          configureLanCardsDao.getConfigureLanCable(serviceInstanceId, sequenceId,
          totalCableType);
      siteDetailsList = configureLanCardsDao.getConfigureSiteDetails(serviceInstanceId);

      for (ConfigureLanCardsCountryDomain lanCardsSiteDetails : siteDetailsList) {
        country = lanCardsSiteDetails.getCountry();
        siteId = lanCardsSiteDetails.getSiteId();
      }

      for (ConfigureLanCardsDomain domains : cableSelectionList) {
        interfaceType = domains.getInterfaceType();
        electricInterface = domains.getElectricalInterface();
        orderType = domains.getCpeOrderingType();
      }

      for (ConfigureLanCardsDomain lanCardsDomainList : configureLanCardsList) {
        noOfCircuits = lanCardsDomainList.getNoOfCircuits();
        cpeUsage = lanCardsDomainList.getLanUsage();
      }
      String cableType = "LAN";
      String cablePort = "LAN";
      int satelliteSupport = 0;
      String systemName = "Spring";
      int eosCheck;
      if (orderType == "Adopt") {
        eosCheck = 1;
      } else {
        eosCheck = 0;
      }
      lanCablesColumnList =
          configureLanCardsDao.getLanCableSelectionLanCablesList(cpename, cableType, cablePort,
          interfaceType, electricInterface, country, satelliteSupport, systemName, siteId, orderType, noOfCircuits,
          cpeUsage, eosCheck);

      String opticalcablePort = "MISC";
      opticalModulesList =configureLanCardsDao.
          getLanCableSelectionLanCablesList(cpename, cableType, opticalcablePort,
          interfaceType, electricInterface, country, satelliteSupport, systemName, siteId, orderType, noOfCircuits,
          cpeUsage, eosCheck);

    }

    if (!HelperMethods.isNullOrEmpty(opticalDetailsList)) {
      for (ConfigureLanCardsDomain opticalDetailsDomain : opticalDetailsList) {
        ConfigureLanCardsDTO configureLanCardsDTO = new ConfigureLanCardsDTO();
        configureLanCardsDTO.setLanSwitch(opticalDetailsDomain.getLanSwitch());
        configureLanCardsDTO.setCableName(opticalDetailsDomain.getCableName());
        configureLanCardsDTO.setNumberOfCables(opticalDetailsDomain.getNumberOfCables());
        opticalTotalModuleColumnDTO.add(configureLanCardsDTO);
        configureLanCardsMap.put("opticalTotalModuleColumnDTO", opticalTotalModuleColumnDTO);
      }
    }
    if (!HelperMethods.isNullOrEmpty(cableSelectionTotalCableList)) {
      for (ConfigureLanCardsDomain totalCablesDomain : cableSelectionTotalCableList) {
        ConfigureLanCardsDTO configureLanCardsDTO = new ConfigureLanCardsDTO();

        configureLanCardsDTO.setLanSwitchId(totalCablesDomain.getLanSwitchId());
        configureLanCardsDTO.setCableName(totalCablesDomain.getCableName());
        configureLanCardsDTO.setNumberOfCables(totalCablesDomain.getNumberOfCables());
        cableSelectionTotalDTOList.add(configureLanCardsDTO);
        configureLanCardsMap.put("cableSelectionTotalDTOList", cableSelectionTotalDTOList);
      }
    }

    if (!HelperMethods.isNullOrEmpty(opticalModulesList)) {
      for (ConfigureLanCardsDomain cableShortNameDomainList : opticalModulesList) {
        ConfigureLanCardsDTO configureLanCardsDTO = new ConfigureLanCardsDTO();
        configureLanCardsDTO.setCableShortName(cableShortNameDomainList.getCableShortName());
        opticalGridFirstColumnDTO.add(configureLanCardsDTO);
        configureLanCardsMap.put("opticalGridFirstColumnDTO", opticalGridFirstColumnDTO);
      }
    }

    if (!HelperMethods.isNullOrEmpty(lanCablesColumnList)) {
      for (ConfigureLanCardsDomain cableShortNameDomainList : lanCablesColumnList) {
        ConfigureLanCardsDTO configureLanCardsDTO = new ConfigureLanCardsDTO();
        configureLanCardsDTO.setCableShortName(cableShortNameDomainList.getCableShortName());
        cableShortNameList.add(configureLanCardsDTO);

        configureLanCardsMap.put("cableShortNameList", cableShortNameList);
      }
    }

    if (!HelperMethods.isNullOrEmpty(configureLanCardColumnList)) {
      for (ConfigureLanCardsDomain cardColumnDomain : configureLanCardColumnList) {
        ConfigureLanCardsDTO configureLanCardsDTO = new ConfigureLanCardsDTO();
        configureLanCardsDTO.setCardId(cardColumnDomain.getCardId());
        configureLanCardsDTO.setCardDescription(cardColumnDomain.getCardDescription());
        configureLanCardsDTO.setCardPosition(cardColumnDomain.getCardPosition());
        configureLanCardsDTO.setPortType(cardColumnDomain.getPortType());
        cardColumnList.add(configureLanCardsDTO);

        configureLanCardsMap.put("cardColumnList", cardColumnList);

      }

    }

    if (!HelperMethods.isNullOrEmpty(configureLanCardsList)) {
      for (ConfigureLanCardsDomain configureLanCardsDomain : configureLanCardsList) {
        ConfigureLanCardsDTO configureLanCardsDTO = new ConfigureLanCardsDTO();
        configureLanCardsDTO.setLanSwitch(configureLanCardsDomain.getLanSwitch());
        System.out.println("LANSWITCH +++++++" + configureLanCardsDomain.getLanSwitch());
        configureLanCardsDTO.setLanUsage(configureLanCardsDomain.getLanUsage());
        configureLanCardsDTO.setChassis(configureLanCardsDomain.getChassis());
        configureLanSwitchDetailsList.add(configureLanCardsDTO);

        configureLanCardsMap.put("configureLanSwitchDetailsList", configureLanSwitchDetailsList);
      }

    }

    if (!HelperMethods.isNullOrEmpty(configureLanCardsSelectionList)) {
      for (ConfigureLanCardsDomain configureLanCardsDomain : configureLanCardsSelectionList) {

        ConfigureLanCardsDTO configureLanCardsDTO = new ConfigureLanCardsDTO();

        configureLanCardsDTO.setSharedPortAdapters(configureLanCardsDomain.getSharedPortAdapters());
        configureLanCardsDTO.setWicSlots(configureLanCardsDomain.getWicSlots());
        configureLanCardsDTO.setNetworkModules(configureLanCardsDomain.getNetworkModules());
        configureLanCardsDTO.setPortAdapters(configureLanCardsDomain.getPortAdapters());
        configureLanCardsDTO.setCarrirerSlots(configureLanCardsDomain.getCarrirerSlots());
        configureLanCardsDTO.setProcessoreSlots(configureLanCardsDomain.getProcessoreSlots());
        configureLanCardsDTO.setSwitchingSlots(configureLanCardsDomain.getSwitchingSlots());
        configureLanCardsDetailsList.add(configureLanCardsDTO);

        configureLanCardsMap.put("configureLanCardsDetailsList", configureLanCardsDetailsList);
      }

    }

    return configureLanCardsMap;

  }

  public ConfigureLanCardsDao getConfigureLanCards() {
    return configureLanCardsDao;
  }

  /**
   * Description : Sets the value in ConfigureLanCardsao.
   * 
   * @param configureLanCardsDao.
   */
  public void setConfigureLanCardsDao(ConfigureLanCardsDao configureLanCardsDao) {
    this.configureLanCardsDao = configureLanCardsDao;
  }

}
