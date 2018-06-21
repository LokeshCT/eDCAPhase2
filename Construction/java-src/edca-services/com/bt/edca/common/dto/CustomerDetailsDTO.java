package com.bt.edca.common.dto;

/**
 * Class used to Declare variables.
 * @author Niranjan das Basa.
 *     
 */
public class CustomerDetailsDTO {

  /**
   * Declaration of customerId.
   */
  private long customerId;
  
  private String poaReference;

  /**
   * Declaration of fullLegalCompanyName.
   */
  private String fullLegalCompanyName;

  /**
   * Declaration of CustomerName.
   */
  private String customerName;

  /**
   * Declaration of masterCustomerId.
   */
  private String masterCustomerId;

  /**
   * Declaration of orderType.
   */
  private String orderType;

  /**
   * Declaration of distributorLegalName.
   */
  private String distributorLegalName;

  /**
   * Declaration of externalSystemName.
   */
  private String externalSystemName;

  /**
   * Declaration of externalSystemReference.
   */
  private String externalSystemReference;

  /**
   * Declaration of billingId.
   */
  private String billingId;

  /**
   * Declaration of contractId.
   */
  private String contractId;

  /**
   * Declaration of currency.
   */
  private String currency;

  /**
   * Declaration of performanceReportRequired.
   */
  private String performanceReportRequired;

  /**
   * Declaration of customertype.
   */
  private String customertype;

  /**
   * Declaration of orderId.
   */
  private int orderId;

  /**
   * Declaration of opportunityId.
   */
  private String opportunityId;

 
  /**
   * Declaration of subOrderType.
   */
  private String subOrderType;

  /**
   * Gets the customerId.
   * 
   * @return customerId.
   */
  public long getCustomerId() {
    return customerId;
  }

  /**
   * @param customerId.
   */
  
  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  /**
   * Gets the fullLegalCompanyName.
   * 
   * @return fullLegalCompanyName.
   */
  public String getFullLegalCompanyName() {
    return fullLegalCompanyName;
  }

  /**
   * @param fullLegalCompanyName.
   */
  public void setFullLegalCompanyName(String fullLegalCompanyName) {
    this.fullLegalCompanyName = fullLegalCompanyName;
  }

  /**
   * Gets the customerName.
   * 
   * @return customerName.
   */
  public String getCustomerName() {
    return customerName;
  }

  /**
   * @param customerName.
   */
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  /**
   * Gets the masterCustomerId.
   * 
   * @return masterCustomerId.
   */
  public String getMasterCustomerId() {
    return masterCustomerId;
  }

  /**
   * @param masterCustomerId.
   */
  public void setMasterCustomerId(String masterCustomerId) {
    this.masterCustomerId = masterCustomerId;
  }

  /**
   * Gets the orderType.
   * 
   * @return orderType.
   */
  public String getOrderType() {
    return orderType;
  }

  /**
   * @param orderType.
   */
  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  /**
   * Gets the distributorLegalName.
   * 
   * @return distributorLegalName.
   */
  public String getDistributorLegalName() {
    return distributorLegalName;
  }

  /**
   * @param distributorLegalName.
   */
  public void setDistributorLegalName(String distributorLegalName) {
    this.distributorLegalName = distributorLegalName;
  }

  /**
   * Gets the externalSystemName.
   * 
   * @return externalSystemName.
   */
  public String getExternalSystemName() {
    return externalSystemName;
  }

  /**
   * @param externalSystemName.
   */
  public void setExternalSystemName(String externalSystemName) {
    this.externalSystemName = externalSystemName;
  }

  /**
   * Gets the externalSystemReference
   * 
   * @return externalSystemReference.
   */
  public String getExternalSystemReference() {
    return externalSystemReference;
  }

  /**
   * @param externalSystemReference.
   */
  public void setExternalSystemReference(String externalSystemReference) {
    this.externalSystemReference = externalSystemReference;
  }

  /**
   * Gets the billingId.
   * 
   * @return billingId.
   */
  public String getBillingId() {
    return billingId;
  }

  /**
   * @param billingId.
   */
  public void setBillingId(String billingId) {
    this.billingId = billingId;
  }

  /**
   * Gets the contractId.
   * 
   * @return contractId.
   */
  public String getContractId() {
    return contractId;
  }

  /**
   * @param contractId.
   */
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  /**
   * Gets the currency.
   * 
   * @return currency.
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * @param currency.
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Gets the performanceReportRequired.
   * 
   * @return performanceReportRequired.
   */
  public String getPerformanceReportRequired() {
    return performanceReportRequired;
  }

  /**
   * @param performanceReportRequired.
   */
  public void setPerformanceReportRequired(String performanceReportRequired) {
    this.performanceReportRequired = performanceReportRequired;
  }

  /**
   * Gets the customertype.
   * 
   * @return customertype.
   */
  public String getCustomertype() {
    return customertype;
  }

  /**
   * @param customertype.
   */
  public void setCustomertype(String customertype) {
    this.customertype = customertype;
  }

  /**
   * Gets the orderId.
   * 
   * @return orderId.
   */
  public int getOrderId() {
    return orderId;
  }

  /**
   * @param orderId.
   */
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  /**
   * Gets the opportunityId.
   * 
   * @return opportunityId.
   */
  public String getOpportunityId() {
    return opportunityId;
  }

  /**
   * @param opportunityId.
   */
  public void setOpportunityId(String opportunityId) {
    this.opportunityId = opportunityId;
  }

  
  /**
   * Gets the subOrderType.
   * 
   * @return subOrderType.
   */
  public String getSubOrderType() {
    return subOrderType;
  }

  /**
   * @param subOrderType.
   */
  public void setSubOrderType(String subOrderType) {
    this.subOrderType = subOrderType;
  }

  
  
  

  /**
 * @return the poaReference
 */
public String getPoaReference() {
	return poaReference;
}

/**
 * @param poaReference the poaReference to set
 */
public void setPoaReference(String poaReference) {
	this.poaReference = poaReference;
}

/**
   * Converting into String and displaying values in console.
   */
  
  
  
  
  @Override

  public String toString() {

    final StringBuilder sb = new StringBuilder();

    sb.append("CustomerDetailsDTO = {");

    sb.append("fullLegalCompanyName = ").append(fullLegalCompanyName).append(",");

    sb.append("customerName = ").append(customerName).append(",");

    sb.append("masterCustomerId = ").append(masterCustomerId).append(",");

    sb.append("orderId = ").append(orderId).append(",");

    sb.append("subOrderType = ").append(subOrderType).append(",");

    sb.append("distributorLegalName = ").append(distributorLegalName).append(",");

    sb.append("externalSystemName = ").append(externalSystemName).append(",");

    sb.append("externalSystemReference = ").append(externalSystemReference).append(",");

    sb.append("billingId = ").append(billingId).append(",");

    sb.append("contractId = ").append(contractId).append(",");

    sb.append("currency = ").append(currency).append(",");

    sb.append("opportunityId = ").append(opportunityId).append(",");

    sb.append("performanceReportRequired = ").append(performanceReportRequired);

    sb.append("}");

    return sb.toString();

  }

}
