package com.bt.edca.util.helper;

/**
 * Enum to store the fixed values of customertype.
 * @author Niranjan das Basa.
 *
 */
public enum CustomerDetailsType {

  CUSTOMER("S"), INFRASTRUCTURE("I");

  /**
   * Declaration of customer type.
   */
  private String customertype;

  private CustomerDetailsType(String customertype) {
    this.customertype = customertype;
  }

  /**
   * Gets the customertype.
   * @return customertype.
   */
  public String getCustomertype() {
    return customertype;
  }

}
