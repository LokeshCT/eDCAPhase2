package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.ConfigureAccessDomain;
import com.bt.edca.da.domain.ConfigureLanSwitchDomain;
import com.bt.edca.da.domain.ConfigureVlanDomain;
import com.bt.edca.da.domain.ConfigureVlanSanDomain;
import com.bt.edca.da.domain.SitePageDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.ConfigureVlanDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ConfigureVlanDaoImpl implements ConfigureVlanDao {

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(ConfigureVlanDaoImpl.class);

  private static Object[] result;

  /**
   * @param serviceInstanceId.
   * 
   * @return configureVlanUltraDetailsList .
   */

  public List<ConfigureVlanDomain> getConfigureVlanUltraDetails(String serviceInstanceId) {
    List<ConfigureVlanDomain> configureVlanUltraDetailsList = HibernateQueryUtil.executeQuery(
        CONFIGURE_VLAN_ULTRA_DETAILS, new String[] { "serviceInstanceId" }, new Object[] { serviceInstanceId });

    System.out.println("DAO IMPL" + configureVlanUltraDetailsList.get(0).getVlanType());
    return configureVlanUltraDetailsList;

  }

  /**
   * fetches lanSwitch(CPE name) to find manufacturer.
   * 
   * @input serviceInstanceId
   * 
   */
  public List<ConfigureLanSwitchDomain> getLanSwitch(String serviceInstanceId) {
    List lanSwitchDomain = HibernateQueryUtil.executeQuery(GET_DISTINCT_LAN_SWITCH,
        new String[] { "serviceInstanceId" }, new Object[] { serviceInstanceId });

    return lanSwitchDomain;
  }

  /**
   * method to fetch the manufacturer.
   * 
   */
  public String getCpeManufacture(String systemName, String cpeName) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection connection = sessimpl.connection();
    CallableStatement statement = null;
    // int userid = Integer.parseInt(nUserId);

    String cpeManufacturer = null;

    try {

      statement = connection.prepareCall(GET_CPE_MANUFACTURER);

      if (null != statement) {
        statement.setString(1, "Spring");
        statement.setString(2, cpeName);
        statement.registerOutParameter(3, Types.VARCHAR);
        statement.registerOutParameter(4, Types.VARCHAR);
        statement.registerOutParameter(5, Types.VARCHAR);

        statement.execute();
        cpeManufacturer = statement.getString(3);
      }

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    } finally {
      try {
        if (null != statement) {
          statement.close();

        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }

    }
    return cpeManufacturer;
  }

  /**
   * method to fetch lastMileAccess and accessoptionFlag.
   * 
   */
  public List<SitePageDomain> getUltraOrderDetails(String siteId) {
    List<SitePageDomain> ultraOrderDetailsDomain 
    = HibernateQueryUtil.executeQuery(GET_ULTRA_ORDER_DETAILS,
        new String[] { "siteId" }, new Object[] { siteId });
    for (SitePageDomain ultra : ultraOrderDetailsDomain) {
      ultra.getLastMileAccess();
      ultra.getAccessOptionFlag();
    }
    return ultraOrderDetailsDomain;
  }

  /**
   * fetches the serviceInstance with serviceInstanId as input.
   * 
   */
  public String getServiceInstance(String serviceInstanceId) {
    String serviceInstance = null;
    List<ConfigureAccessDomain> serviceInstanceDomain = HibernateQueryUtil.executeQuery(GET_SERVICE_INSTANCE,
        new String[] { "serviceInstanceId" }, new Object[] { serviceInstanceId });
    for (ConfigureAccessDomain domain : serviceInstanceDomain) {
      serviceInstance = domain.getServiceInstance();
     }

    return serviceInstance;
  }

  /**
   * implementation for the method declared in ConfigureVlanDao. fetches the
   * sanName.
   */
  public List<ConfigureVlanSanDomain> getSanName(String serviceInstanceId) {
    List<ConfigureVlanSanDomain> serviceInstance = new ArrayList<ConfigureVlanSanDomain>();
    List serviceInstanceDomain = HibernateQueryUtil
        .executeQuery(GET_SAN_NAME, new String[] { "serviceInstanceId" },
        new Object[] { serviceInstanceId });
    System.out.println("IN SAN NAME DAO IMPL");

    System.out.println("********** list size ******* " + serviceInstanceDomain.size());
    for (int i = 0; i < serviceInstanceDomain.size(); i++) {
      result = (Object[]) serviceInstanceDomain.get(i);
      ConfigureVlanSanDomain configureVlanSanDomain = new ConfigureVlanSanDomain();

      configureVlanSanDomain.setSanName(result[0].toString());
      configureVlanSanDomain.setSanId(Integer.parseInt(result[1].toString()));
      serviceInstance.add(configureVlanSanDomain);
    }

    return serviceInstance;

  }

  public List<ConfigureVlanSanDomain> getSanValue(int sanId, int vlanId, String sanName) {
    List sanvalueDomain = HibernateQueryUtil.executeQuery(GET_SAN_VALUE, new String[] { "sanId", "vlanId", "sanName" },
        new Object[] { sanId, vlanId, sanName });
    List<ConfigureVlanSanDomain> sanValueList = new ArrayList<ConfigureVlanSanDomain>();
    for (int i = 0; i < sanvalueDomain.size(); i++) {
      ConfigureVlanSanDomain configureVlanSanDomain = new ConfigureVlanSanDomain();
      configureVlanSanDomain.setSanValue(sanvalueDomain.get(i).toString());
      sanValueList.add(configureVlanSanDomain);
    }
    return sanValueList;
  }

}
