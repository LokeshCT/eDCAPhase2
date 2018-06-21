package com.bt.edca.dao;

import java.util.List;

import com.bt.edca.da.domain.CPEConfigureDomain;
import com.bt.edca.da.domain.OtherDomain;
import com.bt.edca.da.domain.ServiceInstancesDomain;
import com.bt.edca.da.domain.OtherDevicesDomain;
/**
 * ConfigureOtherDeviceDaoImpl Implements ConfigureOtherDeviceDao interface. Gets the
 * configureOtherDevic details in list by serviceinstanceId.
 * 
 * @author SATHYA.
 * 
 */
public interface ConfigureOtherDeviceDao {
  /**
   * Format to get cpeConfigureDetails in list by serviceInstanceId.
   */
  String GET_VPN_BY_SERVICEINSTANCEID = "getVpnByServiceInstanceId";
  /**
   * Gets cpeConfigureDetails using this parameters.
   * 
   * @param serviceInstanceId.
   * @return.
   */
  public List<OtherDomain> getVpnDetails(int serviceInstanceId);
  /**
   * Format to get Configure details based on siteId and ServiceInstance.
   */
  static String GET_SERVICEINSTANCES_BY_SERVICEINSTANCEID = "getServicebyServiceInstanceId";
  /**
   * This methods gets the configure access details with parameters
   * 
   * @param serviceInstance.
   * @return configureAccessDomain.
   */
  public List<ServiceInstancesDomain> getSiteDetails(int serviceInstanceId);
  /**
   * Format to get Configure details based on siteId and ServiceInstance.
   */
  static String GET_CONFIGUREOTHER_DEVICES_BY_SITEIDAND_ORDER_ID = "getConfigureOtherDevicesbySiteIdandorderId";
  /**
   * This methods gets the configure access details with parameters
   * 
   * @param serviceInstance.
   * @return configureAccessDomain.
   */
  public List<OtherDevicesDomain> getSiteDetails(String siteId, String orderId);
  /**
   * Format to get Configure details based on siteId and ServiceInstance.
   */
  String GET_AllDEVICE = "{call DD_DCA_PK.Get_All_Device(?,?)}";
  /**
   * This methods gets the configure access details with parameters
   * 
   * @param serviceInstance.
   * @return configureAccessDomain.
   */
  public List<CPEConfigureDomain> getallDevices(int serviceInstanceId);
}
