package com.bt.edca.da.domain;

/**
 * 
 * @author Rohit .
 * Description : Domain class for Configure Wicnmpa
 *               (for getting the eos value) .
 *
 */
public class ConfigureWicnmpaEosCheckDomain {
  private int serviceInstanceId;
  private String cpeOrderingType;
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

}
