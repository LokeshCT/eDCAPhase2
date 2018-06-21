package com.bt.edca.dao;

import java.util.List;




/**
 *  Description :This interface calls the procedure with appropriate IN and OUT parameters.
 * Gets the user and product details in List.
 * 
 * @author Sathya
 */
public interface UserDetailsDao {

  /**
   * Description : Format to get the user details in list.
   */
  String GET_USER_PROFILE = "{call DDDCA_PK_USER_PROFILE. GetUserProfile(?,?)}";
  /**
   *  Description :Format to get the product details in list.
   */
  String GET_PRODUCT_DETAILS = "{call DDDCA_PK_USER_PRO. GetProductListByTeamId(?,?)}";
  /**
   *  Description :Format to save the user details in list.
   */
  String GET_SAVEUSERDEFAULT_PROFILE =
      "{call DDDCA_PK_USER_PROFILE." + " SaveUserDefaultProfile(?,?,?,?)}";

  /**
   *  Description :Gets User details using this parameters.
   * 
   * @param userid.
   * @return.
   */
  public List getUserDetails(String nUserId);

  /**
   *  Description :Gets product details using this parameters.
   * 
   * @param teamId.
   * @return.
   */
  public List getProductDetails(String teamId);

  /**
   * Description : Save default profile details using this parameters.
   * 
   * @param teamId,profileId,userId.
   * @return.
   */

  public String saveUserDefaultFlag(String nUserId, String profileId, String teamId);
}
