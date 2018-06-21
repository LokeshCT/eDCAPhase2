package com.bt.edca.dao;

import java.util.List;

import com.bt.edca.da.domain.CPEConfigureDomain;
import com.bt.edca.da.domain.ConfigureDeviceCableDomain;
/**
 * ConfigureCardCableDaoImpl Implements ConfigureCardCableDao interface. Gets the
 * ConfigureCardCable details in list by serviceinstanceId.
 * 
 * @author SATHYA.
 * 
 */
public interface ConfigureCardCableDao {
  /**
   * Format to get cpeConfigureDetails in list by serviceInstanceId.
   */
  String GET_CONFIGURE_CARD_CABLE = "getConfigureCardCable";

  /**
   * Gets  ConfigureCardCable using this parameters.
   * 
   * @param serviceInstanceId.
   * @return.
   */
  public String getConfigureCardCable(String serviceInstanceID);
  /**
   * Format to getConfigureCardCable in list by serviceInstanceId.
   */
  String GET_AllDEVICE = "{call DD_DCA_PK.Get_All_Device(?,?)}";

  /**
   * getallDevices using this parameters.
   * 
   * @param serviceInstanceId.
   * @return.
   */
  public List<CPEConfigureDomain> getallDevices(String serviceInstanceId);
  /**
   * Format to get cpeConfigureDetails in list by serviceInstanceId.
   */
  String GET_DEVICECARDCABLES = "{call PKG_CPE_SPRING.pr_get_Corvil_components(?,?,?,?,?,?)}";

  /**
   * Gets getallDevicesCardCables using this parameters.
   * 
   * @param serviceInstanceId.
   * @return.
   */
  public List<ConfigureDeviceCableDomain> getallDevicesCardCables(String baseRouterName, String pisystem_name,
      int p_i_eos_check);

}
