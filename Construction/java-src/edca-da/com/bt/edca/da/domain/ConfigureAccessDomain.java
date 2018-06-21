package com.bt.edca.da.domain;

/**
 * Class used to Declare variables.
 *
 * @author Mathivathana.
 *
 */
public class ConfigureAccessDomain {

  private String serviceInstance;
  private String gpop;
  private String dvbpop;
  private String apop;
  private String accessSupplier;
  private String accessEnhancedServiceRest;
  private String accessEntryPoint;
  private int bgpCount;
  private String portSplBidId;
  private int siteLocationId;

  private String lanSwitch;
   private String lanSwitchType;

  /**
   * declaration of serviceInstanceId.
   */
  private int serviceInstanceId;

  /**
   * declaration of cPE_Rack_Shelf.
   */
  private String cPE_Rack_Shelf;


  public String getcPE_Rack_Shelf() {
    return cPE_Rack_Shelf;
  }

  /**
   * @return cPE_Rack_Shelf.
   */
  public void setcPE_Rack_Shelf(String cPE_Rack_Shelf) {
    this.cPE_Rack_Shelf = cPE_Rack_Shelf;
  }

  /**
   * @param serviceInstanceId.
   */
  public int getServiceInstanceId() {
    return serviceInstanceId;
  }

  /**
   * @return serviceInstanceId.
   */
  public void setServiceInstanceId(int serviceInstanceId) {
    this.serviceInstanceId = serviceInstanceId;
  }

  /**
   * @return the serviceInstance.
   */
  public String getServiceInstance() {
    return serviceInstance;
  }
  /**
   * @param serviceInstance the serviceInstance to set.
   */
  public void setServiceInstance(String serviceInstance) {
    this.serviceInstance = serviceInstance;
  }
  /**
   * @return the gpop.
   */
  public String getGpop() {
    return gpop;
  }
  /**
   * @param gpop the gpop to set.
   */
  public void setGpop(String gpop) {
    this.gpop = gpop;
  }
  /**
   * @return the dvbpop.
   */
  public String getDvbpop() {
    return dvbpop;
  }
  /**
   * @param dvbpop the dvbpop to set.
   */
  public void setDvbpop(String dvbpop) {
    this.dvbpop = dvbpop;
  }
  /**
   * @return the apop.
   */
  public String getApop() {
    return apop;
  }
  /**
   * @param apop the apop to set.
   */
  public void setApop(String apop) {
    this.apop = apop;
  }
  /**
   * @return the accessSupplier.
   */
  public String getAccessSupplier() {
    return accessSupplier;
  }
  /**
   * @param accessSupplier the accessSupplier to set.
   */
  public void setAccessSupplier(String accessSupplier) {
    this.accessSupplier = accessSupplier;
  }
  /**
   * @return the accessEnhancedServiceRest.
   */
  public String getAccessEnhancedServiceRest() {
    return accessEnhancedServiceRest;
  }
  /**
   * @param accessEnhancedServiceRest the accessEnhancedServiceRest to set.
   */
  public void setAccessEnhancedServiceRest(String accessEnhancedServiceRest) {
    this.accessEnhancedServiceRest = accessEnhancedServiceRest;
  }
  /**
   * @return the accessEntryPoint.
   */
  public String getAccessEntryPoint() {
    return accessEntryPoint;
  }
  /**
   * @param accessEntryPoint the accessEntryPoint to set.
   */
  public void setAccessEntryPoint(String accessEntryPoint) {
    this.accessEntryPoint = accessEntryPoint;
  }
  /**
   * @return the bgpCount.
   */
  public int getBgpCount() {
    return bgpCount;
  }
  /**
   * @param bgpCount the bgpCount to set.
   */
  public void setBgpCount(int bgpCount) {
    this.bgpCount = bgpCount;
  }
  /**
   * @return the portSplBidId.
   */
  public String getPortSplBidId() {
    return portSplBidId;
  }
  /**
   * @param portSplBidId the portSplBidId to set.
   */
  public void setPortSplBidId(String portSplBidId) {
    this.portSplBidId = portSplBidId;
  }
  /**
   * @return the siteLocationId.
   */
  public int getSiteLocationId() {
    return siteLocationId;
  }
  /**
   * @param siteLocationId the siteLocationId to set.
   */
  public void setSiteLocationId(int siteLocationId) {
    this.siteLocationId = siteLocationId;
  }

/**
 * @return the lanSwitch
 */
public String getLanSwitch() {
	return lanSwitch;
}

/**
 * @param lanSwitch the lanSwitch to set
 */
public void setLanSwitch(String lanSwitch) {
	this.lanSwitch = lanSwitch;
}

/**
 * @return the lanSwitchType
 */
public String getLanSwitchType() {
	return lanSwitchType;
}

/**
 * @param lanSwitchType the lanSwitchType to set
 */
public void setLanSwitchType(String lanSwitchType) {
	this.lanSwitchType = lanSwitchType;
}


}
