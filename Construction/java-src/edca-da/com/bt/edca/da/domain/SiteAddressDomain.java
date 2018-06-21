package com.bt.edca.da.domain;

/**
 * Description:Domain Class used to fetch the fields of UI.
 * 
 * @author 609060481:Jagankumar G.
 *
 */
public class SiteAddressDomain {

  /**
   * declaration of floor.
   */

  private String floor;
  /**
   * declaration of country.
   */
  private String country;
  /**
   * declaration of room.
   */
  private String room;
  
  /**
   * declaration of buildingNumber.
   */
  private String buildingNumber;
  
  /**
   * declaration of addressLine1.
   */
  private String addressLine1;
  
  /**
   * declaration of addressLine2.
   */
  private String addressLine2;
  
  /**
   * declaration of city.
   */
  private String city;
  
  /**
   * declaration of postcode.
   */
  private String postcode;
  
  /**
   * declaration of state.
   */
  private String state;
  
  /**
   * declaration of siteLocationId.
   */
  private int siteLocationId;
  
  /**
   * getter for siteLocationId.
   * @return siteLocationId.
   */
  public int getSiteLocationId() {
    return siteLocationId;
  }

  /**
   * setter for siteLocationId.
   * @param siteLocationId.
   */
  public void setSiteLocationId(int siteLocationId) {
    this.siteLocationId = siteLocationId;
  }

  /**
   * getter for Floor.
   * @return getFloor.
   */
  public String getFloor() {
    return floor;
  }

  /**
   * setter for floor.
   * @param floor.
   */
  public void setFloor(String floor) {
    this.floor = floor;
  }

  /**
   * getter for room.
   * @return room.
   */
  public String getRoom() {
    return room;
  }

  /**
   * setter for room.
   * @param room.
   */
  public void setRoom(String room) {
    this.room = room;
  }

  /**
   * getter for buildingNumber.
   * @return buildingNumber.
   */
  public String getBuildingNumber() {
    return buildingNumber;
  }

  /**
   * setter for buildingNumber.
   * @param buildingNumber.
   */
  public void setBuildingNumber(String buildingNumber) {
    this.buildingNumber = buildingNumber;
  }

  /**
   * getter for addressLine1.
   * @return addressLine1.
   */
  public String getAddressLine1() {
    return addressLine1;
  }

  /**
   * setter for addressLine1.
   * @param addressLine1.
   */
  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  /**
   * getter for addressLine2.
   * @return addressLine2.
   */
  public String getAddressLine2() {
    return addressLine2;
  }

  /**
   * setter for addressLine2.
   * @param addressLine2.
   */
  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  /**
   * getter for city.
   * @return city.
   */
  public String getCity() {
    return city;
  }

  /**
   * setter for city.
   * @param city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * getter for postcode.
   * @return postcode.
   */
  public String getPostcode() {
    return postcode;
  }

  /**
   * setter for postcode.
   * @param postcode.
   */
  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  /**
   * getter for state.
   * @return state.
   */
  public String getState() {
    return state;
  }

  /**
   * setter for state.
   * @param state.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }
  
  
}
