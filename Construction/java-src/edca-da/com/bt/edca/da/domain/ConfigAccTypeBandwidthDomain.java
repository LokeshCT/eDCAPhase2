
package com.bt.edca.da.domain;
/**
 * Description :Its domain class for the remaining attributes of 
 *              ConfigureAccess type page.
 *              Here we are setting the aggregatedMulticastBandwidth attribute .
 * @author Rohit 
 *
 */
public class ConfigAccTypeBandwidthDomain {
  private int aggregatedMulticastBandwidth;
  private int serviceInstanceId;
   /**
   * @return the aggregatedMulticastBandwidth
   */
  public int getAggregatedMulticastBandwidth() {
    return aggregatedMulticastBandwidth;
  }

  /**
   * @param aggregatedMulticastBandwidth the 
   * aggregatedMulticastBandwidth to set
   */
  public void setAggregatedMulticastBandwidth(int aggregatedMulticastBandwidth) {
    this.aggregatedMulticastBandwidth = aggregatedMulticastBandwidth;
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
