package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.CPEConfigureDomain;
import com.bt.edca.da.domain.ConfigureDeviceCableDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.ConfigureCardCableDao;
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
 * ConfigureCardCableDetails.
 * 
 * @author SATHYA.
 *
 */
public class ConfigureCardCableDaoImpl implements ConfigureCardCableDao {
  /**
   * Description : Gets ConfigureCardCable page using this parameters.
   * 
   * @param serviceInstanceId.
   * @return list.
   */
  public String getConfigureCardCable(String serviceInstanceId) {

    List cableList = HibernateQueryUtil.executeQuery(GET_CONFIGURE_CARD_CABLE, new String[] { "serviceInstanceId" },
        new Object[] { serviceInstanceId });
    String list = cableList.get(0).toString();

    return list;

  }

  /**
   * Description : Gets ConfigureCardCable page using this parameters.
   * 
   * @param serviceInstanceId.
   * @return list.
   */
  public List<CPEConfigureDomain> getallDevices(String serviceInstanceId) {
    int serviceId = Integer.parseInt(serviceInstanceId);
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection connection = sessimpl.connection();
    ResultSet resultSet = null;
    CallableStatement statement = null;
    List<CPEConfigureDomain> cpeList = new ArrayList<CPEConfigureDomain>();

    try {
      statement = connection.prepareCall(GET_AllDEVICE);

      if (null != statement) {
        statement.setInt(1, serviceId);
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

  /**
   * Description : Gets ConfigureCardCable page using this parameters.
   * 
   * @param serviceInstanceId.
   * @return list.
   */
  public List<ConfigureDeviceCableDomain> getallDevicesCardCables(String baseRouterName, String pisystem_name,
      int p_i_eos_check) {

    /*
     * Session session = HibernateUtil.getSession(); SessionImpl sessimpl =
     * (SessionImpl) session; Connection connection = sessimpl.connection();
     * ResultSet resultSet = null; CallableStatement statement = null;
     * List<ConfigureDeviceCableDomain> cpeList = new
     * ArrayList<ConfigureDeviceCableDomain>();
     * 
     * try { statement = connection.prepareCall(GET_DEVICECARDCABLES);
     * 
     * if (null != statement) { statement.setString(1,"VEGA-VS0111-016");
     * statement.setString(2,"SPRING"); statement.setInt(3, 1);
     * statement.registerOutParameter(4, OracleTypes.CURSOR);
     * statement.registerOutParameter(5, OracleTypes.VARCHAR);
     * statement.registerOutParameter(6, OracleTypes.VARCHAR);
     * statement.execute(); resultSet = (ResultSet) statement.getObject(4); if
     * (null != resultSet) {
     * 
     * while (resultSet.next()) { ConfigureDeviceCableDomain cardConfigure = new
     * ConfigureDeviceCableDomain();
     * cardConfigure.setPartName(resultSet.getString("PART_NAME"));
     * cardConfigure.setPartType(resultSet.getString("PART_TYPE"));
     * cardConfigure.setSubElementType(resultSet.getString("SUB_ELEMENT_TYPE"));
     * cpeList.add(cardConfigure); } } } } catch (SQLException e) {
     * 
     * } finally { try { if (null != resultSet) { resultSet.close(); } if (null
     * != statement) { statement.close(); } } catch (SQLException e) { throw new
     * SystemException(e); } }
     */

    return null;
  }
}
