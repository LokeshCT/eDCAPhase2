package com.bt.edca.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import com.bt.edca.da.domain.ConfigureAccessTypeDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.ConfigureAccessTypeDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;

/**
 *Description : This is a dao class where we fetch the values from the db .
 * @author Rohit
 *
 */
public class ConfigureAccessTypeDaoImpl implements ConfigureAccessTypeDao {
  /**
   *  Logger for capturing the exception .
   */
  private static EdcaLogger logger = EdcaLogger.
      getLogger(CustomerDetailsDaoImpl.class);
  
  /**
   * @param serviceInstanceId .
   * @return configureAccessTypeList .
   */

  public  List getConfigureAccessType(String serviceInstanceId) {
  List configureAccessTypeList = HibernateQueryUtil.executeQuery(
        GET_CONFIGURE_ACCESS_TYPE_LIST_BY_SERVICE_INSTANCE_ID, 
        new String[] { "serviceInstanceId" },
        new Object[] { serviceInstanceId });
        return configureAccessTypeList;
  }
 /**
 * Description : In this List we are calling a procedure/method 
 *               from a db.For this we are not using hibernate mapping file.
 *@param serviceInstanceId .
 *@return  ConfigureAccessDTOList .          
 */
  public  List getConfigureDelegation(String serviceInstanceId) {
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    ResultSet rs = null;
    List<ConfigureAccessTypeDomain> configureAccessDTOList = 
        new ArrayList<ConfigureAccessTypeDomain>();
    try {
      statement = conn.prepareCall(GET_CONFIGURE_DELEGATION_QUES_PROCEDURE);
      statement.setString(1, serviceInstanceId);
      statement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
      statement.execute();
      rs = (ResultSet) statement.getObject(2);
      rs = (ResultSet) statement.getObject(3);
      rs = (ResultSet) statement.getObject(4);
      rs = (ResultSet) statement.getObject(5);
      rs = (ResultSet) statement.getObject(6);
      rs = (ResultSet) statement.getObject(7);
      rs = (ResultSet) statement.getObject(8);
      rs = (ResultSet) statement.getObject(9);
      rs = (ResultSet) statement.getObject(10);
      rs = (ResultSet) statement.getObject(11);
      if (null != rs) {
        while (rs.next()) {
          ConfigureAccessTypeDomain configureType = 
              new ConfigureAccessTypeDomain();
          configureType.setCurrency(rs.getString(2));
          configureType.setSupplierQuotedNrc(rs.getString(3));
          configureType.setSupplierQuotedMrc(rs.getString(4));
          configureType.setConvertedCurrency(rs.getString(5));
          configureType.setReason(rs.getString(6));
          configureType.setBandwidthCircuit(rs.getString(7));
          configureType.setCheapestSolution(rs.getString(8));
          configureType.setOptimalAccessTech(rs.getString(9));
          configureType.setCeLimit(rs.getString(10));
          configureType.setRegionalLimitl(rs.getString(11));
          configureAccessDTOList.add(configureType);
        }
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);    
  } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (null != statement) {
          statement.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }
    return configureAccessDTOList;

  }
 /** 
  *@param serviceInstanceId .
  *@return  aggregatedMulti . 
  */
  public  List getAggregatedMulticastValue(String serviceInstanceId) {
       List aggregatedMulticastList = HibernateQueryUtil.executeQuery(
        GET_AGGREGATED_MULTICAST_BANDWIDTH, 
        new String[] { "serviceInstanceId" },
        new Object[] { Integer.parseInt(serviceInstanceId) });
        return aggregatedMulticastList;
    }
 /** 
  * @param serviceInstanceId .
  * @return qrefList .
  * Description : Method for getting the qref value .
  */
  public  List getQrefValue(String serviceInstanceId) {
    
    List qrefList = HibernateQueryUtil.executeQuery(
        GET_QREF_VALUE_BY_SERVICE_INSTANCE_ID, new String[] {
            "serviceInstanceId" },
        new Object[] { serviceInstanceId });
          return qrefList;
  }
}
