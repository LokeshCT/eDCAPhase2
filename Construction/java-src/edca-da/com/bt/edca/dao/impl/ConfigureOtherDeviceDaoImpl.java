package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.CPEConfigureDomain;

import com.bt.edca.da.domain.OtherDevicesDomain;
import com.bt.edca.da.domain.OtherDomain;

import com.bt.edca.da.domain.ServiceInstancesDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.ConfigureOtherDeviceDao;
import com.bt.edca.exception.SystemException;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description : Class used to execute Query. Fetching the
 * ConfigureOtherDeviceDetails.
 * 
 * @author SATHYA.
 *
 */
public class ConfigureOtherDeviceDaoImpl implements ConfigureOtherDeviceDao {
  /**
   * Format of serviceInsatanceId.
   */
  private static final String SERVICEINSTANCEID = "serviceInstanceId";
  private static final String SITEID = "siteId";
  private static final String ORDERID = "orderId";

  /**
   * Description : Gets ConfigureOtherDevice page using this parameters.F
   * 
   * @param serviceInstanceId.
   * @return cpeConfigureDetailsList.
   */
  @Override

  public List<OtherDomain> getVpnDetails(int serviceInstanceId) {

    @SuppressWarnings("unchecked")
    List<OtherDomain> siteList = HibernateQueryUtil.executeQuery(GET_VPN_BY_SERVICEINSTANCEID,
        new String[] { SERVICEINSTANCEID }, new Object[] { serviceInstanceId });

    return siteList;
  }

  /**
   * Description : Gets ConfigureOtherDevice page using this parameters.F
   * 
   * @param serviceInstanceId.
   * @return list.
   */
  public List<ServiceInstancesDomain> getSiteDetails(int serviceInstanceId) {
    @SuppressWarnings("unchecked")
    List<ServiceInstancesDomain> siteList = HibernateQueryUtil.executeQuery(GET_SERVICEINSTANCES_BY_SERVICEINSTANCEID,
        new String[] { SERVICEINSTANCEID }, new Object[] { serviceInstanceId });

    return siteList;
  }

  /**
   * Description : Gets ConfigureOtherDevice page using this parameters.F
   * 
   * @param serviceInstanceId.
   * @return list.
   */
  public List<OtherDevicesDomain> getSiteDetails(String siteId, String orderId) {
    @SuppressWarnings("unchecked")
    List<OtherDevicesDomain> siteList = HibernateQueryUtil.executeQuery(
        GET_CONFIGUREOTHER_DEVICES_BY_SITEIDAND_ORDER_ID, new String[] { SITEID, ORDERID },
        new Object[] { siteId, orderId });

    return siteList;
  }

  /**
   * Description : Gets ConfigureOtherDevice page using this parameters.F
   * 
   * @param serviceInstanceId.
   * @return list.
   */
  public List<CPEConfigureDomain> getallDevices(int serviceInstanceId) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection connection = sessimpl.connection();
    ResultSet resultSet = null;
    CallableStatement statement = null;
    List<CPEConfigureDomain> cpeList = new ArrayList<CPEConfigureDomain>();

    try {
      statement = connection.prepareCall(GET_AllDEVICE);

      if (null != statement) {
        statement.setInt(1, serviceInstanceId);
        statement.registerOutParameter(2, OracleTypes.CURSOR);

        statement.execute();

        resultSet = (ResultSet) statement.getObject(2);
        if (null != resultSet) {

          while (resultSet.next()) {
            CPEConfigureDomain cpeConfigure = new CPEConfigureDomain();
            cpeConfigure.setCpeSequenceId(resultSet.getString("CPESEQID"));
            cpeConfigure.setCpeUsage(resultSet.getString("CPEUSAGE"));
            cpeConfigure.setBaseRouter(resultSet.getString("BASEROUTER"));
            cpeConfigure.setChassis(resultSet.getString("CHASSIS"));

            cpeList.add(cpeConfigure);
          }
        }
      }
    } catch (SQLException e) {

    } finally {
      try {
        if (null != resultSet) {
          resultSet.close();
        }
        if (null != statement) {
          statement.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }

    return cpeList;
  }
}
