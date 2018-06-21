package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.CPEConfigureDomain;
import com.bt.edca.da.domain.ConfigureCpeDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.dao.CPEConfigureDao;

import java.util.List;

/**
 * Description : Class used to execute Query. Fetching the CPEConfigureDetails.
 * 
 * @author SATHYA.
 *
 */
public class CPEConfigureDaoImpl implements CPEConfigureDao {
  /**
   * Format of serviceInsatanceId.
   */
  private static final String SERVICEINSTANCEID = "serviceInstanceId";

  /**
   * Description : Gets CpeConfigure page using this parameters.
   * 
   * @param serviceInstanceId.
   * @return cpeConfigureDetailsList.
   */
  @Override
  public List<CPEConfigureDomain> getCPEConfigureDetails(String serviceInstanceId) {
    @SuppressWarnings("unchecked")
    List<CPEConfigureDomain> cpeConfigureDetailsList = HibernateQueryUtil.executeQuery(
        GET_CPECONFIGURE_BY_SERVICEINSTANCEID, new String[] { SERVICEINSTANCEID }, new Object[] { serviceInstanceId });
    return cpeConfigureDetailsList;
  }

  /**
   * Description : Gets ConfigureAcces page using this parameters.
   * 
   * @param serviceInstanceId.
   * @return configureAccess.
   */
  public List<ConfigureCpeDomain> getConfigureAccessDetails(String serviceInstanceId) {
    @SuppressWarnings("unchecked")
    List<ConfigureCpeDomain> cpeConfigureDetailsList = HibernateQueryUtil.executeQuery(GET_CONFIGURE_ACCESS_DETAILS,
        new String[] { SERVICEINSTANCEID }, new Object[] { serviceInstanceId });
    return cpeConfigureDetailsList;
  }

}
