package com.bt.edca.dao;

import java.util.List;

import com.bt.edca.da.domain.ConfigureLanSwitchDomain;
import com.bt.edca.da.domain.ConfigureVlanDomain;
import com.bt.edca.da.domain.ConfigureVlanSanDomain;
import com.bt.edca.da.domain.SitePageDomain;

/**
 * DAO layer for ConfigureVlan.
 * @author 609060481: Jagan kumar G.
 *
 */
public interface ConfigureVlanDao {

  /**
   * Description:DAO interface for ConfigureVlanUltraDetails. fetches the
   * orderId from Hibernate mapping file. declaration of constant that fetches
   * siteId.
   * 
   */
  String CONFIGURE_VLAN_ULTRA_DETAILS = "getConfigureVlanUltraDetails";

  String GET_DISTINCT_LAN_SWITCH = "getDistinctLanSwitch";

  String GET_CPE_MANUFACTURER = "{call pkg_cpe_spring.pr_get_cpe_manufacturer(?,?,?,?,?)}";

  String GET_ULTRA_ORDER_DETAILS = "getUltraOrderDetails";

  String GET_SERVICE_INSTANCE = "getServiceInstance";
  
  String GET_SAN_NAME="getSanName";
  
  String GET_SAN_VALUE="getSanValue";
  /**
   * @param serviceInstanceId.
   * @return List.
   */
  public List<ConfigureVlanDomain> getConfigureVlanUltraDetails(String serviceInstanceId);

  /**
   * @param serviceInstanceId.
   * @return lanSwitch.
   */
  public List<ConfigureLanSwitchDomain> getLanSwitch(String serviceInstanceId);

  /**
   * @param systemName.
   * @param cpeName.
   * @return cpeManufacturer.
   */
  public String getCpeManufacture(String systemName, String cpeName);

  /**
   * method to fetch lastMileaccess and accessoptionflag.
   * @param siteId.
   * @return list.
   */
  public List<SitePageDomain> getUltraOrderDetails(String siteId);

  /**
   * @param serviceInstanceId
   * @return serviceInstance.
   */
  public String getServiceInstance(String serviceInstanceId);

  /**
   * method declared to fetch SAN name.
   * @param serviceInstaceId.
   * @return sanName.
   */
  public List<ConfigureVlanSanDomain> getSanName(String serviceInstanceId);
  
  /**
   * @param sanId
   * @param vlanId
   * @param sanName
   * @return sanValue.
   */
  public List<ConfigureVlanSanDomain> getSanValue(int sanId,int vlanId,String sanName);
}
