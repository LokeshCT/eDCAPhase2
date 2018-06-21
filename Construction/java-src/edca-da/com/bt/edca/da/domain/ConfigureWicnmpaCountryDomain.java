package com.bt.edca.da.domain;

/**
 * 
 * @author Rohit .
 * Description : Domain class for Configure Wicnmpa( for getting the country) .
 *
 */
public class ConfigureWicnmpaCountryDomain {
  private int siteLocationId;
  private String country;
  private int siteId;
  private int serviceInstanceId;

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
