package com.bt.edca.common.dto;
/**
 * Description : DTO class for ConfigureWicnmpaDTO .
 * @author Rohit .
 *
 */
public class ConfigureWicnmpaDTO {
  private int serviceInstanceId;
  private String baseRouter;
  private String chassis;
  private String cpeUsage;
  private String cpeSeqId;
  private String hwicSlots;
  private String wicSlots;
  private String networkModules;
  private String portAdapters;
  private String sharedPort;
  private int builtinSlots;
  private int carrierSlots;
  private int processorSlots;
  private int switchingSlots;
  private String cardPosition;
  private String portType;
  private String cardDesc;
  private String interfaceType;
  private String accessType;
  private String electricalInterface;
  private int noOfCircuits;
  private int siteLocationId;
  private String country;
  private int siteId;
  private String cpeOrderingType;
  private String cableShortName;
  private String totalCable;
  private String cableDesc;

 
  /**
   * @return the siteLocationId
   */
  public int getSiteLocationId() {
    return siteLocationId;
  }
  /**
   * @param siteLocationId the siteLocationId to set
   */
  public void setSiteLocationId(int siteLocationId) {
    this.siteLocationId = siteLocationId;
  }
  /**
   * @return the country
   */
  public String getCountry() {
    return country;
  }
  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }
  /**
   * @return the siteId
   */
  public int getSiteId() {
    return siteId;
  }
  /**
   * @param siteId the siteId to set
   */
  public void setSiteId(int siteId) {
    this.siteId = siteId;
  }
  /**
   * @return the hwicSlots
   */
  public String getHwicSlots() {
    return hwicSlots;
  }
  /**
   * @param hwicSlots the hwicSlots to set
   */
  public void setHwicSlots(String hwicSlots) {
    this.hwicSlots = hwicSlots;
  }
  /**
   * @return the wicSlots
   */
  public String getWicSlots() {
    return wicSlots;
  }
  /**
   * @param wicSlots the wicSlots to set
   */
  public void setWicSlots(String wicSlots) {
    this.wicSlots = wicSlots;
  }
  /**
   * @return the networkModules
   */
  public String getNetworkModules() {
    return networkModules;
  }
  /**
   * @param networkModules the networkModules to set
   */
  public void setNetworkModules(String networkModules) {
    this.networkModules = networkModules;
  }
  /**
   * @return the portAdapters
   */
  public String getPortAdapters() {
    return portAdapters;
  }
  /**
   * @param portAdapters the portAdapters to set
   */
  public void setPortAdapters(String portAdapters) {
    this.portAdapters = portAdapters;
  }
  /**
   * @return the sharedPort
   */
  public String getSharedPort() {
    return sharedPort;
  }
  /**
   * @param sharedPort the sharedPort to set
   */
  public void setSharedPort(String sharedPort) {
    this.sharedPort = sharedPort;
  }
  /**
   * @return the builtinSlots
   */
  public int getBuiltinSlots() {
    return builtinSlots;
  }
  /**
   * @param builtinSlots the builtinSlots to set
   */
  public void setBuiltinSlots(int builtinSlots) {
    this.builtinSlots = builtinSlots;
  }
  /**
   * @return the carrierSlots
   */
  public int getCarrierSlots() {
    return carrierSlots;
  }
  /**
   * @param carrierSlots the carrierSlots to set
   */
  public void setCarrierSlots(int carrierSlots) {
    this.carrierSlots = carrierSlots;
  }
  /**
   * @return the processorSlots
   */
  public int getProcessorSlots() {
    return processorSlots;
  }
  /**
   * @param processorSlots the processorSlots to set
   */
  public void setProcessorSlots(int processorSlots) {
    this.processorSlots = processorSlots;
  }
  /**
   * @return the switchingSlots
   */
  public int getSwitchingSlots() {
    return switchingSlots;
  }
  /**
   * @param switchingSlots the switchingSlots to set
   */
  public void setSwitchingSlots(int switchingSlots) {
    this.switchingSlots = switchingSlots;
  }
  /**
   * @return the serviceInstanceId
   */
  public int getServiceInstanceId() {
    return serviceInstanceId;
  }
  /**
   * @param serviceInstanceId the serviceInstanceId to set
   */
  public void setServiceInstanceId(int serviceInstanceId) {
    this.serviceInstanceId = serviceInstanceId;
  }
  /**
   * @return the baseRouter
   */
  public String getBaseRouter() {
    return baseRouter;
  }
  /**
   * @param baseRouter the baseRouter to set
   */
  public void setBaseRouter(String baseRouter) {
    this.baseRouter = baseRouter;
  }
  /**
   * @return the chassis
   */
  public String getChassis() {
    return chassis;
  }
  /**
   * @param chassis the chassis to set
   */
  public void setChassis(String chassis) {
    this.chassis = chassis;
  }
  /**
   * @return the cpeUsage
   */
  public String getCpeUsage() {
    return cpeUsage;
  }
  /**
   * @param cpeUsage the cpeUsage to set
   */
  public void setCpeUsage(String cpeUsage) {
    this.cpeUsage = cpeUsage;
  }
  /**
   * @return the cpeSeqId
   */
  public String getCpeSeqId() {
    return cpeSeqId;
  }
  /**
   * @param cpeSeqId the cpeSeqId to set
   */
  public void setCpeSeqId(String cpeSeqId) {
    this.cpeSeqId = cpeSeqId;
  }
  /**
   * @return the cardPosition
   */
  public String getCardPosition() {
    return cardPosition;
  }
  /**
   * @param cardPosition the cardPosition to set
   */
  public void setCardPosition(String cardPosition) {
    this.cardPosition = cardPosition;
  }
  /**
   * @return the portType
   */
  public String getPortType() {
    return portType;
  }
  /**
   * @param portType the portType to set
   */
  public void setPortType(String portType) {
    this.portType = portType;
  }
  /**
   * @return the cardDesc
   */
  public String getCardDesc() {
    return cardDesc;
  }
  /**
   * @param cardDesc the cardDesc to set
   */
  public void setCardDesc(String cardDesc) {
    this.cardDesc = cardDesc;
  }
  /**
   * @return the interfaceType
   */
  public String getInterfaceType() {
    return interfaceType;
  }
  /**
   * @param interfaceType the interfaceType to set
   */
  public void setInterfaceType(String interfaceType) {
    this.interfaceType = interfaceType;
  }
  /**
   * @return the accessType
   */
  public String getAccessType() {
    return accessType;
  }
  /**
   * @param accessType the accessType to set
   */
  public void setAccessType(String accessType) {
    this.accessType = accessType;
  }
  /**
   * @return the electricalInterface
   */
  public String getElectricalInterface() {
    return electricalInterface;
  }
  /**
   * @param electricalInterface the electricalInterface to set
   */
  public void setElectricalInterface(String electricalInterface) {
    this.electricalInterface = electricalInterface;
  }
  /**
   * @return the noOfCircuits
   */
  public int getNoOfCircuits() {
    return noOfCircuits;
  }
  /**
   * @param noOfCircuits the noOfCircuits to set
   */
  public void setNoOfCircuits(int noOfCircuits) {
    this.noOfCircuits = noOfCircuits;
  }
  /**
   * @return the cpeOrderingType
   */
  public String getCpeOrderingType() {
    return cpeOrderingType;
  }
  /**
   * @param cpeOrderingType the cpeOrderingType to set
   */
  public void setCpeOrderingType(String cpeOrderingType) {
    this.cpeOrderingType = cpeOrderingType;
  }
  /**
   * @return the cable
   */
  /**
   * @return the cableShortName
   */
  public String getCableShortName() {
    return cableShortName;
  }
  /**
   * @param cableShortName the cableShortName to set
   */
  public void setCableShortName(String cableShortName) {
    this.cableShortName = cableShortName;
  }
  /**
   * @return the totalCable
   */
  public String getTotalCable() {
    return totalCable;
  }
  /**
   * @param totalCable the totalCable to set
   */
  public void setTotalCable(String totalCable) {
    this.totalCable = totalCable;
  }
  /**
   * @return the cableDesc
   */
  public String getCableDesc() {
    return cableDesc;
  }
  /**
   * @param cableDesc the cableDesc to set
   */
  public void setCableDesc(String cableDesc) {
    this.cableDesc = cableDesc;
  }
  
}
