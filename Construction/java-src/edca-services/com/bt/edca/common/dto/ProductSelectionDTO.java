package com.bt.edca.common.dto;

/**
 * class for setting and getting the values .
 * @author Rohit
 *
 */
public class ProductSelectionDTO {
 private String productName;
 private String cosModel;
 private String cosPolicy;
 private String customizationLevel;
  private long orderId;
  /**
   * @param productName - setter for productName .
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }
  /** 
   * @return productName -gettter for productName .
   */
  public String getProductName() {
    return productName;
  }
  /**
   * @param cosModel - setter for cosModel .
   */
  public void setCosModel(String cosModel) {
    this.cosModel = cosModel;
  }
  /**
   * @return cosModel -getter for cosModel .
   */
  public String getCosModel() {
    return cosModel;
  }
  /**
   * @param cosPolicy -setter for cosPolicy.
   */
  public void setCosPolicy(String cosPolicy) {
    this.cosPolicy = cosPolicy;
  }
  /**
   * @return cosPolicy -getter for cosPolicy .
   */
  public String getCosPolicy() {
    return cosPolicy;
  }
  /** 
   * @param customizationLevel -setter for customizationLevel .
   */
  public void setCustomizationLevel(String customizationLevel) {
    this.customizationLevel = customizationLevel;
  }
  /**
   * @return customizationLevel -getter for customizationLevel .
   */
  public String getCustomizationLevel() {
    return customizationLevel;
  }
  /**
   * @param orderId - setter for orderId .
   */
  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }
  /** 
   * @return orderId -getter for orderId .
   */
  public long getOrderId() {
    return orderId;
  }
}
