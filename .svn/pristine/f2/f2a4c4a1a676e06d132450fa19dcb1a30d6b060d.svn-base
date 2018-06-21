package com.bt.edca.dao;

import com.bt.edca.da.domain.ConfigureAccessDomain;

/**
 * This interface calls the DB function with appropriate IN and OUT parameters.
 * Gets the Configure Access Details.
 * 
 * @author Mathivathana
 */

public interface ConfigureAccessDao {

  /**
   * Format to get Configure details based on siteId and ServiceInstance.
   */
  String GET_CONFIGURE_ACCESS_DETAILS = "getConfigureAccessDetails";
  
  /**
   * Format to get gpopColour.
   */
  String GPOP_COLOR_FUNCTION = "{? = call DD_DCA_PK.Get_Color(?,?,?) }";
  
  /**
   * This methods gets the configure access details with parameters
   * @param siteId.
   * @param serviceInstance.
   * @return configureAccessDomain.
   */
  public ConfigureAccessDomain getConfigureAccessDetails(String siteId, String serviceInstance);
  
  /**
   * This methods gets the gpopcolour with parameters
   * @param siteLocationId.
   * @param serviceInstance.
   * @param gpop.
   * @return gpopcolour.
   */

  public String getGpopColour(int siteLocationId, String serviceInstance, String gpop);

}
