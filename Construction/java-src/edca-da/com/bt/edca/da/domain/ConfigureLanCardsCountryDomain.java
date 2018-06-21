package com.bt.edca.da.domain;

public class ConfigureLanCardsCountryDomain {
  
  private String country;
  
  private long siteLocationId;
  
  private int serviceInstanceId;
  
  private int siteId;

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public long getSiteLocationId() {
    return siteLocationId;
  }

  public void setSiteLocationId(long siteLocationId) {
    this.siteLocationId = siteLocationId;
  }

  public int getServiceInstanceId() {
    return serviceInstanceId;
  }

  public void setServiceInstanceId(int serviceInstanceId) {
    this.serviceInstanceId = serviceInstanceId;
  }

  public int getSiteId() {
    return siteId;
  }

  public void setSiteId(int siteId) {
    this.siteId = siteId;
  }
  
  
}
