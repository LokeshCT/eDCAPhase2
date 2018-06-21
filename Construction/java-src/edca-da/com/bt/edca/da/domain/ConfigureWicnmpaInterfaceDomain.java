package com.bt.edca.da.domain;

public class ConfigureWicnmpaInterfaceDomain {

  private String interfaceType;
  private String accessType;
  private String electricalInterface;
  private int serviceInstanceId;
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
}
