package com.bt.edca.common.dto;


/**
 * Description : Its a DTO class for userdetails page. setting value from
 * UsertDetailDomain .
 * 
 * @author Sathya
 *
 */
/**
 * @author 608479048
 *
 */
public class UserDetailsDTO {

  /**
   * Declaration of productCatagoryId.
   */

  private String productCatagoryId;

  /**
   * Declaration of profileName.
   */
  private String profileName;

  /**
   * Declaration of defaultFlag.
   */
  private String profileId;

  /**
   * Declaration of defaultFlag.
   */
  private String defaultFlag;
  /**
   * Declaration of teamId.
   */
  private String teamId;
  
 
  
  /**
   * Declaration of productCatagoryName.
   */
  private String productCatagoryName;
  /**
   * Declaration of userId.
   */
  private String userId;
  
  /**
   * @return userId.
   *
   */
	  public String getUserId() {
		return userId;
	}
	  

	  /**
	   * @set the userId.
	   *
	   */
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

/**
   * @return ProductCatagoryName.
   *
   */

  public String getProductCatagoryName() {
    return productCatagoryName;
  }

  /**
   * @set the ProductCatagoryName.
   *
   */
  public void setProductCatagoryName(String productCatagoryName) {
    this.productCatagoryName = productCatagoryName;
  }

  /**
   * @this method used to get the TeamId.
   *
   */
  public String getTeamId() {
    return teamId;
  }

  /**
   * @set the TeamId.
   *
   */
  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }
  
 /**
   * @this method used to get the ProfileId.
   *
   */
    public String getProfileId() {
    return profileId;
  }
  /**
   * @set the profileId.
   *
   */
  public void setProfileId(String profileId) {
    this.profileId = profileId;
  }

  /**
   * @this method used to get the ProfileName.
   *
   */
  public String getProfileName() {
    return profileName;
  }

  /**
   * @set the ProfileName.
   */
  public void setProfileName(String profileName) {
    this.profileName = profileName;
  }
  /**
   * @this method used to get the defaultFlag.
   *
   */
  public String getDefaultFlag() {
    return defaultFlag;
  }

  /**
   * @set the setDefaultFlag.
   *
   */
  public void setDefaultFlag(String defaultFlag) {
    this.defaultFlag = defaultFlag;
  }

  /**
   * @this method used to get the productCatagoryId.
   *
   */

  public String getProductCatagoryId() {
    return productCatagoryId;
  }

  /**
   * @set the setProductCatagoryId.
   *
   */
  public void setProductCatagoryId(String productCatagoryId) {
    this.productCatagoryId = productCatagoryId;
  }

}
