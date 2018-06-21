package com.bt.edca.common.dto;

public class OrderContactDTO {

  /**
   * declaration of firstName.
   */
  private String firstName;

  /**
   * declaration of lastName.
   */
  private String lastName;

  /**
   * declaration of jobTitle.
   */
  private String jobTitle;

  /**
   * declaration of phone.
   */
  private String phone;

  /**
   * declaration of MobilePager.
   */
  private String mobilePager;

  /**
   * declaration of fax.
   */
  private String fax;

  /**
   * declaration of email.
   */
  private String email;

  /**
   * declaration of contactType.
   */
  private String contactType;

  /**
   * declaration of accoutMgrId.
   */
  private String accountMgrId;

  /**
   * delcaration of orderId.
   */
  private int orderId;

  /**
   * declaration of accountmgrEin.
   */
  private String accountmgrEin;

  /**
   * declaration of salesRepein.
   */
  private String salesRepEin;

  /**
   * declaration of salesrepClassicId.
   */
  private String salesRepClassicId;

  /**
   * declaration of contactId. 
   */
  private String contactId;

  /**
   * declaration of city.
   */
  private String city;

  /**
   * declaration of objId.
   */
  private int objId;

  /**
   * @return objId.
   */
  public int getObjId() {
    return objId;
  }

  /**
   * @param objId.
   */
  public void setObjId(int objId) {
    this.objId = objId;
  }
  

  /**
   * declaration of postcode.
   */
  /**
   * declaration of postcode.
   */
  private String postcode;

  /**
   * declaration of state.
   */
  private String state;

  /**
   * declaration of orderDesignerEin.
   */
  private String orderDesingerEin;

  /**
   * @return firstName.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return lastName.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return jobTitle.
   */
  public String getJobTitle() {
    return jobTitle;
  }

  /**
   * @param jobTitle.
   */
  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  /**
   * @return phone.
   */
  public String getPhone() {
    return phone;
  }

  /**
   * @param phone.
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * @return mobilePager.
   */
  public String getMobilePager() {
    return mobilePager;
  }

  /**
   * @param mobilePager.
   */
  public void setMobilePager(String mobilePager) {
    this.mobilePager = mobilePager;
  }

  /**
   * @return fax.
   */
  public String getFax() {
    return fax;
  }

  /**
   * 
   * @param fax.
   */
  public void setFax(String fax) {
    this.fax = fax;
  }

  /**
   * @return email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return contactType.
   */
  public String getContactType() {
    return contactType;
  }

  /**
   * @param contactType.
   */
  public void setContactType(String contactType) {
    this.contactType = contactType;
  }

  /**
   * @return accountMgrId.
   */
  public String getAccountMgrId() {
    return accountMgrId;
  }

  /**
   * @param accountMgrId.
   */
  public void setAccountMgrId(String accountMgrId) {
    this.accountMgrId = accountMgrId;
  }

  /**
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
   * @return accountmgrEin.
   */
  public String getAccountmgrEin() {
    return accountmgrEin;
  }

  /**
   * @param accountmgrEin.
   */
  public void setAccountmgrEin(String accountmgrEin) {
    this.accountmgrEin = accountmgrEin;
  }

  /**
   * @return salesRepEin.
   */
  public String getSalesRepEin() {
    return salesRepEin;
  }

  /**
   * @param salesRepEin.
   */
  public void setSalesRepEin(String salesRepEin) {
    this.salesRepEin = salesRepEin;
  }

  /**
   * @return salesRepClassicId.
   */
  public String getSalesRepClassicId() {
    return salesRepClassicId;
  }

  /**
   * @param salesRepClassicId.
   */
  public void setSalesRepClassicId(String salesRepClassicId) {
    this.salesRepClassicId = salesRepClassicId;
  }

  /**
   * @return contactId.
   */
  public String getContactId() {
    return contactId;
  }

  /**
   * @param contactId.
   */
  public void setContactId(String contactId) {
    this.contactId = contactId;
  }

  /**
   * @return city.
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return postcode.
   */
  public String getPostcode() {
    return postcode;
  }

  /**
   * @param postcode.
   */
  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  /**
   * @return state.
   */
  public String getState() {
    return state;
  }

  /**
   * @param state.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return orderDesignerEin.
   */
  public String getOrderDesingerEin() {
    return orderDesingerEin;
  }

  /**
   * @param orderDesingerEin.
   */
  public void setOrderDesingerEin(String orderDesingerEin) {
    this.orderDesingerEin = orderDesingerEin;
  }
  
  /**
   * Description: method for null check.
   */
  public void initializeEmptyDTO(){
    setContactId(null);
    setFirstName(null);
    setLastName(null);
    setEmail(null);
    setPhone(null);
    setMobilePager(null);
    setJobTitle(null);
  }

}
