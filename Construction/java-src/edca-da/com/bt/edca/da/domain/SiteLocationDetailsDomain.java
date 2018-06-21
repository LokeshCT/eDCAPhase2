package com.bt.edca.da.domain;

/**
 * Domain class for all the fields of SiteLocationDetails.
 * @author Jagankumar G.
 *
 */
public class SiteLocationDetailsDomain {

  private String siteName;

  private String country;

  private String classicSiteId;

  private String serClassicSiteId;

  private String hubSpoke;
  
  private int siteId;
  
  private long siteLocationId;
  
  /**
   * getter of SiteLocationId.
   * @return siteLocationId.
   */
  public long getSiteLocationId() {
    return siteLocationId;
  }

  public void setSiteLocationId(long siteLocationId) {
    this.siteLocationId = siteLocationId;
  }

  public int getSiteId() {
    return siteId;
  }

  public void setSiteId(int siteId) {
    this.siteId = siteId;
  }

  public String getSiteName() {
    return siteName;
  }

  public void setSiteName(String siteName) {
    this.siteName = siteName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getClassicSiteId() {
    return classicSiteId;
  }

  public void setClassicSiteId(String classicSiteId) {
    this.classicSiteId = classicSiteId;
  }

  public String getSerClassicSiteId() {
    return serClassicSiteId;
  }

  public void setSerClassicSiteId(String serClassicSiteId) {
    this.serClassicSiteId = serClassicSiteId;
  }

  public String getHubSpoke() {
    return hubSpoke;
  }

  public void setHubSpoke(String hubSpoke) {
    this.hubSpoke = hubSpoke;
  }
  

}



