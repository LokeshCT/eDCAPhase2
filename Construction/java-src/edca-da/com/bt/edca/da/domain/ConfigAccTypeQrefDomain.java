package com.bt.edca.da.domain;
// TODO: Auto-generated Javadoc
/**
 * Description : Configure Access Type Domain class .
 * @author Rohit 
 *
 */
public class ConfigAccTypeQrefDomain {
  
  /** The qref. */
  private String qref;
  
  /** The service instance id. */
  private int serviceInstanceId;
  
  /** The access offer. */
  private String accessOffer;  
  
  /** The vlan rack_ shelf. */
  private String vlanRackShelf;
  
  /**
   * Gets the vlan rack_ shelf.
   *
   * @return the vlan rack_ shelf
   */
  public String getVlanRackShelf() {
	return vlanRackShelf;
}

/**
 * Sets the vlan rack_ shelf.
 *
 * @param vlanRack_Shelf the new vlan rack_ shelf
 */
public void setVlanRackShelf(String vlanRackShelf) {
	this.vlanRackShelf = vlanRackShelf;
}

/**
 * Gets the qref.
 *
 * @return the qref
 */
  public String getQref() {
    return qref;
  }

  /**
   * Sets the qref.
   *
   * @param qref the qref to set
   */
  public void setQref(String qref) {
    this.qref = qref;
  }

  /**
   * Gets the service instance id.
   *
   * @return the serviceInstanceId
   */
  public int getServiceInstanceId() {
    return serviceInstanceId;
  }

  /**
   * Sets the service instance id.
   *
   * @param serviceInstanceId the serviceInstanceId to set
   */
  public void setServiceInstanceId(int serviceInstanceId) {
    this.serviceInstanceId = serviceInstanceId;
  }

  /**
   * Gets the access offer.
   *
   * @return the accessOffer
   */
  public String getAccessOffer() {
    return accessOffer;
  }

  /**
   * Sets the access offer.
   *
   * @param accessOffer the accessOffer to set
   */
  public void setAccessOffer(String accessOffer) {
    this.accessOffer = accessOffer;
  }
  
  
  
  
  

}
