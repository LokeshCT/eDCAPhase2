package com.bt.edca.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bt.edca.common.dto.ConfigureWicnmpaDTO;
import com.bt.edca.da.domain.ConfigureWicnmpaCountryDomain;
import com.bt.edca.da.domain.ConfigureWicnmpaDomain;
import com.bt.edca.da.domain.ConfigureWicnmpaEosCheckDomain;
import com.bt.edca.da.domain.ConfigureWicnmpaInterfaceDomain;
import com.bt.edca.dao.ConfigureWicnmpaDao;
import com.bt.edca.service.ConfigureWicnmpaService;
import com.bt.edca.util.HelperMethods;

/**
 *Description : implementation of interface .
 * 
 * @author Rohit .
 *
 */
public class ConfigureWicnmpaServiceImpl implements ConfigureWicnmpaService {
  /**
   *Description: object of configureWicnmpaDao .
   */
  private ConfigureWicnmpaDao configureWicnmpaDao;

/**
 * Description : List for saving the cpe usage,base router,chasis,
 *               cpeseqid,cardslot,porttype,card .
 *@param serviceInstanceId .
 *@return gridOneMap- its a map in which we have many list.
 */
 public Map<String, List> getConfigureWicnmpa(String serviceInstanceId) {
//---------1st and 2 nd  grid-----//
    Map<String, List> gridOneMap = new HashMap<String, List>();
    List<ConfigureWicnmpaDomain> configureWicnmpaList = 
        configureWicnmpaDao.getConfigureWicnmpa(serviceInstanceId);
    List<ConfigureWicnmpaDTO> configCardList = 
        new ArrayList<ConfigureWicnmpaDTO>();
    List<ConfigureWicnmpaDTO> portList = 
        new ArrayList<ConfigureWicnmpaDTO>();
    List<ConfigureWicnmpaDTO> slotList = 
        new ArrayList<ConfigureWicnmpaDTO>();
    
    if (!HelperMethods.isNullOrEmpty(configureWicnmpaList)) {
      for (ConfigureWicnmpaDomain configureWicnmpaDomain  
        :configureWicnmpaList) {
                ConfigureWicnmpaDTO configureWicnmpaDTO = 
                    new ConfigureWicnmpaDTO();
        String cpeSeqId = configureWicnmpaDomain.getCpeSeqId();
        configureWicnmpaDTO.setBaseRouter(configureWicnmpaDomain
            .getBaseRouter());
        configureWicnmpaDTO.setChassis(configureWicnmpaDomain.getChassis());
        configureWicnmpaDTO.setCpeUsage(configureWicnmpaDomain.getCpeUsage());
        configureWicnmpaDTO.setServiceInstanceId(configureWicnmpaDomain
            .getServiceInstanceId());
        configureWicnmpaDTO.setCpeSeqId(configureWicnmpaDomain.getCpeSeqId());
        configCardList.add(configureWicnmpaDTO);
        gridOneMap.put("configCardList", configCardList);
        List<ConfigureWicnmpaDomain> cardSlotList = configureWicnmpaDao
            .getCardSlot(serviceInstanceId, cpeSeqId);
        List<ConfigureWicnmpaDomain> configurePortList = configureWicnmpaDao
            .getPortType(serviceInstanceId, cpeSeqId);
              
        if (!HelperMethods.isNullOrEmpty(cardSlotList)) {

          for (ConfigureWicnmpaDomain configureWicnmpaDomainCard
              : cardSlotList) {
            ConfigureWicnmpaDTO configureSlotDTO = new ConfigureWicnmpaDTO();
            configureSlotDTO.setBuiltinSlots(
                configureWicnmpaDomainCard.getBuiltinSlots());
            configureSlotDTO.setCarrierSlots(
                configureWicnmpaDomainCard.getCarrierSlots());
            configureSlotDTO.setHwicSlots(
                configureWicnmpaDomainCard.getHwicSlots());
            configureSlotDTO.setNetworkModules(
                configureWicnmpaDomainCard.getNetworkModules());
            configureSlotDTO.setPortAdapters(
                configureWicnmpaDomainCard.getPortAdapters());
            configureSlotDTO.setProcessorSlots(
                configureWicnmpaDomainCard.getProcessorSlots());
            configureSlotDTO.setSharedPort(
                configureWicnmpaDomainCard.getSharedPort());
            configureSlotDTO.setSwitchingSlots(
                configureWicnmpaDomainCard.getSwitchingSlots());
            configureSlotDTO.setWicSlots(
                configureWicnmpaDomainCard.getWicSlots());
            configureSlotDTO.setSwitchingSlots(
                configureWicnmpaDomainCard.getSwitchingSlots());
            slotList.add(configureSlotDTO);
            gridOneMap.put("slotList", slotList);

          }
        }
        
        if (!HelperMethods.isNullOrEmpty(configurePortList)) {
          for (ConfigureWicnmpaDomain configurePortDomain : configurePortList) {
            ConfigureWicnmpaDTO configurePortDTO = new ConfigureWicnmpaDTO();
            configurePortDTO.setPortType(configurePortDomain.getPortType());
            configurePortDTO.setCardPosition(
                configurePortDomain.getCardPosition());
            configurePortDTO.setCardDesc(configurePortDomain.getCardDesc());
            configurePortDTO.setTotalCable(configurePortDomain.getTotalCable());
            configurePortDTO.setCableDesc(configurePortDomain.getCableDesc());
            portList.add(configurePortDTO);
            gridOneMap.put("portList", portList);
          }
        }

      }

    }
    return gridOneMap;

  }
//1st  and 2 nd grid ends--------------------//
/**  
 * Description : List for getting the card selection .
 * @param serviceInstanceId .
 * @return mapList .
 */


  public Map<String, List> getCableCardSelection(String serviceInstanceId) {
    Map<String, List> mapList = new HashMap<String, List>();
    
    // input parameter initialisation//
    String cpeName = null;
    String cableType = "NETWORK_CABLE";
    String cablePort = "LAN";
    String cablePortWan = "WAN";
    String cablePortMiscell = "MISC";
    String systemName = "SPRING";
    String interfaceType = null;
    String electricalInterface = null;
    String country = null;
    int satelliteSupport = 0;
    int siteId = 0;
    String orderType = "ADD";
    int noOfCircuits = 0;
    String cpeUsage = null;
    int eos = 0;
    String cpeseq = null;

    // for getting the input parameter of lan ,wan and miscellaneous procedure//
    ConfigureWicnmpaDTO configureWicnmpaDTO = new ConfigureWicnmpaDTO();
    List<ConfigureWicnmpaInterfaceDomain> configureinterfaceList = 
        configureWicnmpaDao.getInterface(serviceInstanceId);
    List<ConfigureWicnmpaDomain> configureCkt = configureWicnmpaDao
        .getNoOfCircuits(serviceInstanceId);
    List<ConfigureWicnmpaCountryDomain> configureCountry = configureWicnmpaDao
        .getSiteCountry(serviceInstanceId);
    List<ConfigureWicnmpaEosCheckDomain> eosCheck = configureWicnmpaDao
        .getEosCheck(serviceInstanceId);
    List<ConfigureWicnmpaDomain> cpeNameRepeat = configureWicnmpaDao
        .getConfigureWicnmpa(serviceInstanceId);
    if (!HelperMethods.isNullOrEmpty(cpeNameRepeat)) {
      for (ConfigureWicnmpaDomain configureWicnmpaDomain : cpeNameRepeat) {
        cpeName = configureWicnmpaDomain.getBaseRouter();
        cpeseq = configureWicnmpaDomain.getCpeSeqId();
        cpeUsage = configureWicnmpaDomain.getCpeUsage();
     

    if (!HelperMethods.isNullOrEmpty(configureinterfaceList)) {
      for (ConfigureWicnmpaInterfaceDomain configureWicnmpaInterfaceDomain
          : configureinterfaceList) {
        electricalInterface = configureWicnmpaInterfaceDomain
            .getElectricalInterface();
        interfaceType = configureWicnmpaInterfaceDomain.getInterfaceType();
      }
    }
    if (!HelperMethods.isNullOrEmpty(configureCkt)) {
      for (ConfigureWicnmpaDomain configureWicnmpaInterfaceDomain
          : configureCkt) {
        noOfCircuits = configureWicnmpaInterfaceDomain.getNoOfCircuits();
      }
    }

    if (!HelperMethods.isNullOrEmpty(configureCountry)) {
      for (ConfigureWicnmpaCountryDomain configureWicnmpaCountryDomain
          : configureCountry) {
        siteId = configureWicnmpaDTO.getSiteId();
        country = configureWicnmpaCountryDomain.getCountry();
      }
    }

    if (!HelperMethods.isNullOrEmpty(eosCheck)) {
      for (ConfigureWicnmpaEosCheckDomain configureWicnmpaEocDomain
          : eosCheck) {
        int eosc;
        if (configureWicnmpaEocDomain.getCpeOrderingType() == "Adopt") {
          eosc = 0;
          eos = eosc;
        } else {
          eosc = 1;
          eos = eosc;
        }
      }
    }
    // 3rd grid . Getting the LAN cables.....//
    List<ConfigureWicnmpaDomain> lanCableListDao = 
        configureWicnmpaDao.getCable(cpeName, cableType, cablePort,
        interfaceType, electricalInterface, country,
        satelliteSupport, systemName, siteId, orderType, noOfCircuits,
        cpeUsage, eos);
    List<ConfigureWicnmpaDTO> lancableList =
        new ArrayList<ConfigureWicnmpaDTO>();
    if (!HelperMethods.isNullOrEmpty(lanCableListDao)) {
      for (ConfigureWicnmpaDomain configureLanDomain : lanCableListDao) {
        ConfigureWicnmpaDTO configureLanDTO = new ConfigureWicnmpaDTO();
        configureLanDTO.setCableShortName(
            configureLanDomain.getCableShortName());
        lancableList.add(configureLanDTO);
        mapList.put("lanCable", lancableList);

      }
    }

    // 3rd grid for getting the Total number of LAN cable for respective LAN cables...//
    List<ConfigureWicnmpaDomain> lanNoOfCableDao = 
        configureWicnmpaDao.getTotalCable(serviceInstanceId, cpeseq, cableType);
    List<ConfigureWicnmpaDTO> noOfLanCableList =
        new ArrayList<ConfigureWicnmpaDTO>();
    if (!HelperMethods.isNullOrEmpty(lanNoOfCableDao)) {
      for (ConfigureWicnmpaDomain configureNoOfLanDomain : lanNoOfCableDao) {

        ConfigureWicnmpaDTO configureNoOfLanDTO = new ConfigureWicnmpaDTO();
        configureNoOfLanDTO.setTotalCable(
            configureNoOfLanDomain.getTotalCable());
        configureNoOfLanDTO.setCableDesc(configureNoOfLanDomain.getCableDesc());
        noOfLanCableList.add(configureNoOfLanDTO);
        mapList.put("totalLanCable", noOfLanCableList);

      }
    }

    // 4th grid.Getting the wan cables..//

    List<ConfigureWicnmpaDomain> wanCableDao = configureWicnmpaDao
        .getCable(cpeName, cableType, cablePortWan,
        interfaceType, electricalInterface, country, satelliteSupport,
        systemName, siteId, orderType, noOfCircuits,
        cpeUsage, eos);
    List<ConfigureWicnmpaDTO> wanCableList = 
        new ArrayList<ConfigureWicnmpaDTO>();

    if (!HelperMethods.isNullOrEmpty(wanCableDao)) {
      for (ConfigureWicnmpaDomain configureWanDomain : wanCableDao) {
        ConfigureWicnmpaDTO configureWanDTO = new ConfigureWicnmpaDTO();
        configureWanDTO.setCableShortName(
            configureWanDomain.getCableShortName());        
        wanCableList.add(configureWanDTO);
        mapList.put("wanCable", wanCableList);
      }
    }
    
    //4th grid for getting the number of wan cables of respectives cable//
List<ConfigureWicnmpaDomain> wanNoOfCableDao = 
configureWicnmpaDao.getTotalCable(serviceInstanceId, cpeseq, cableType);
List<ConfigureWicnmpaDTO> noOfWanCableList = 
new ArrayList<ConfigureWicnmpaDTO>();    
    if (!HelperMethods.isNullOrEmpty(wanNoOfCableDao)) {
      for (ConfigureWicnmpaDomain configureWanNoOFDomain : wanNoOfCableDao) {

        ConfigureWicnmpaDTO configureWanNoOfDTO = new ConfigureWicnmpaDTO();
        configureWanNoOfDTO.setTotalCable(
            configureWanNoOFDomain.getTotalCable());
        configureWanNoOfDTO.setCableDesc(
            configureWanNoOFDomain.getCableDesc());
        noOfWanCableList.add(configureWanNoOfDTO);
        mapList.put("totalWanCable", noOfWanCableList);
      }
    }
        // 5th grid. Getting the Miscellaneous Parts//

    List<ConfigureWicnmpaDomain> miscellaneousCableDao = 
        configureWicnmpaDao.getCable(cpeName, cableType,
        cablePortMiscell, interfaceType, electricalInterface, 
        country, satelliteSupport, systemName, siteId, orderType,
        noOfCircuits, cpeUsage, eos);
    List<ConfigureWicnmpaDTO> miscellaneousCableList = 
        new ArrayList<ConfigureWicnmpaDTO>();
    if (!HelperMethods.isNullOrEmpty(miscellaneousCableDao)) {
      for (ConfigureWicnmpaDomain configureMiscellDomain
          : miscellaneousCableDao) {
        ConfigureWicnmpaDTO configureMiscDTO = new ConfigureWicnmpaDTO();
        configureMiscDTO.setCableShortName(configureMiscellDomain
            .getCableShortName());
        miscellaneousCableList.add(configureMiscDTO);
        mapList.put("miscellaneousCable", miscellaneousCableList);
      }
    }
    //5th grid for getting the total number of miscellaneous parts//
List<ConfigureWicnmpaDomain> miscellaneousNoOfCableDao = configureWicnmpaDao
.getTotalCable(serviceInstanceId, cpeseq, cableType);
List<ConfigureWicnmpaDTO> miscellaneousNoOfCableList =
new ArrayList<ConfigureWicnmpaDTO>();   
    if (!HelperMethods.isNullOrEmpty(miscellaneousNoOfCableDao)) {
      for (ConfigureWicnmpaDomain configuremiscellaneousNoOFDomain
          : miscellaneousNoOfCableDao) {

        ConfigureWicnmpaDTO configureMiscellaneousNoOfDTO =
            new ConfigureWicnmpaDTO();
        configureMiscellaneousNoOfDTO.setTotalCable(
            configuremiscellaneousNoOFDomain.getTotalCable());
        configureMiscellaneousNoOfDTO.setCableDesc(
            configuremiscellaneousNoOFDomain.getCableDesc());
        miscellaneousNoOfCableList.add(configureMiscellaneousNoOfDTO);
        mapList.put("totalmiscellaneousCable", miscellaneousNoOfCableList);

      }
    }
      }
    }


    return mapList;
  }

  /**
   * method for getting the database connection.
   * 
   * @return object for ConfigureWicnmpaDao .
   */
  public ConfigureWicnmpaDao getConfigureWicnmpaDao() {
    return configureWicnmpaDao;
  }

  /**
   * setter for ConfigureWicnmpaDao .
   * 
   * @param configureWicnmpaDao
   *          .
   */
  public void setConfigureWicnmpaDao(
      ConfigureWicnmpaDao configureWicnmpaDao) {
    this.configureWicnmpaDao = configureWicnmpaDao;
  }

}
