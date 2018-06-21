package com.bt.edca.da.domain;

/**
 * Description : This is a Domain class DTO which will have all the values from
 * the database.
 * 
 * @author SATHYA.
 *
 */
public class OtherDevicesDomain {
  /**
   * Declaration of btradianzanalytics.
   */
  private String btradianzanalytics;
  /**
   * Declaration of btradianzmonitor.
   */
  private String btradianzmonitor ;
  /**
   * Declaration of siteId.
   */
  private int siteId;
  /**
   * Declaration of orderId.
   */
  private int orderId;
  
  
  /**
   * this method used to get the orderId.
   *
   */
  public int getOrderId() {
    return orderId;
  }
  /**
   * set the orderId.
   *
   */
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
  /**
   * this method used to get the siteId.
   *
   */
  public int getSiteId() {
    return siteId;
  }
  /**
   * set the siteId.
   *
   */
  public void setSiteId(int siteId) {
    this.siteId = siteId;
  }
  /**
   * this method used to get the btradianzanalytics.
   *
   */
  public String getBtradianzanalytics() {
    return btradianzanalytics;
  }
  /**
   * set the btradianzanalytics.
   *
   */
  public void setBtradianzanalytics(String btradianzanalytics) {
    this.btradianzanalytics = btradianzanalytics;
  }
  /**
   * this method used to get the getBtradianzmonitor.
   *
   */
  public String getBtradianzmonitor() {
    return btradianzmonitor;
  }
  /**
   * set the btradianzmonitor.
   *
   */
  public void setBtradianzmonitor(String btradianzmonitor) {
    this.btradianzmonitor = btradianzmonitor;
  }
   

}
