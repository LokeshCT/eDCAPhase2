package com.bt.edca.common.dto;

/**
 * This Class is used to declare,get and set the variables
 * for OrderDetails.
 * 
 * @author Mathivathana.
 * 
 */
public class OrderDetailsDTO {

  /**
   * Declaration of orderId.
   */
  private String orderId;

  /**
   * Declaration of orderReference.
   */
  private String orderReference;

  /**
   * Declaration of clientAccountId.
   */
  private String clientAccountId;

  /**
   * Declaration of clientName.
   */
  private String clientName;

  /**
   * Declaration of receivedDate.
   */
  private String receivedDate;
  
  /**
   * Declaration of cnt.
   */
  private String cnt;

 

  /**
   * Gets the orderId.
   * 
   * @return orderId.
   */
  public String getOrderId() {
    return orderId;
  }

  /**
   * @param orderId.
   */
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  /**
   * Gets the orderReference.
   * 
   * @return orderReference.
   */
  public String getOrderReference() {
    return orderReference;
  }

  /**
   * @param orderReference.
   */
  public void setOrderReference(String orderReference) {
    this.orderReference = orderReference;
  }

  /**
   * Gets the clientAccountId.
   * 
   * @return clientAccountId.
   */
  public String getClientAccountId() {
    return clientAccountId;
  }

  /**
   * @param clientAccountId.
   */
  public void setClientAccountId(String clientAccountId) {
    this.clientAccountId = clientAccountId;
  }

  /**
   * Gets the clientName.
   * 
   * @return clientName.
   */
  public String getClientName() {
    return clientName;
  }

  /**
   * @param clientName.
   */
  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  /**
   * Gets the receivedDate.
   * 
   * @return receivedDate.
   */
  public String getReceivedDate() {
    return receivedDate;
  }

  /**
   * @param receivedDate.
   */
  public void setReceivedDate(String receivedDate) {
    this.receivedDate = receivedDate;
  }
  
  public String getCnt() {
    return cnt;
  }

  public void setCnt(String cnt) {
    this.cnt = cnt;
  }
  
  /**
   * Converting into String and displaying values in console.
   */
  @Override

  public String toString() {

    final StringBuilder sb = new StringBuilder();

    sb.append("OrderDetailsDTO = {");

    sb.append("orderId = ").append(orderId).append(",");

    sb.append("orderReference = ").append(orderReference).append(",");

    sb.append("clientAccountId = ").append(clientAccountId).append(",");

    sb.append("clientName = ").append(clientName).append(",");

    sb.append("receivedDate = ").append(receivedDate).append(",");  
    
    sb.append("cnt = ").append(cnt).append(","); 

    sb.append("}");

    return sb.toString();

  }

}
