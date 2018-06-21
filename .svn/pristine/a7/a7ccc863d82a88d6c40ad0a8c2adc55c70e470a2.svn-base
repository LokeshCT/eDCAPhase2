package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.ConfigureAccessDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.ConfigureAccessDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

/**
 * This Class is mainly used to get the configure access details and to
 * establish the session and call DB function based on few Input parameters in
 * order to return gpop colour.
 * 
 * @author Mathivathana
 */

public class ConfigureAccessDaoImpl implements ConfigureAccessDao {

  /**
   * Captures the logs onto console.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(ConfigureAccessDaoImpl.class);

  /**
   * Format of result.
   */
  private static Object[] result;

  /**
   * This methods gets the configure access details with parameters
   * 
   * @param siteId.
   * @param serviceInstance.
   * @return configureAccessDomain.
   */
  public ConfigureAccessDomain getConfigureAccessDetails(String siteId, String serviceInstance)
      throws NullPointerException {

    ConfigureAccessDomain details = new ConfigureAccessDomain();
    List accesslist = HibernateQueryUtil.executeQuery(GET_CONFIGURE_ACCESS_DETAILS,
        new String[] { "siteId", "serviceInstance" }, new Object[] { siteId, serviceInstance });

    result = (Object[]) accesslist.get(0);
    details.setServiceInstance(evaluateString(result[0]));
    details.setGpop(evaluateString(result[1]));
    details.setDvbpop(evaluateString(result[2]));
    details.setApop(evaluateString(result[3]));
    details.setAccessSupplier(evaluateString(result[4]));
    details.setAccessEnhancedServiceRest(evaluateString(result[5]));
    details.setAccessEntryPoint(evaluateString(result[6]));
    details.setBgpCount(evaluateInt(result[7]));
    details.setPortSplBidId(evaluateString(result[8]));
    details.setSiteLocationId(evaluateInt(result[9]));
    return details;
  }

  /**
   * This methods gets the gpopcolour with parameters
   * 
   * @param siteLocationId.
   * @param serviceInstance.
   * @param gpop.
   * @return gpopcolour.
   */

  public String getGpopColour(int siteLocationId, String serviceInstance, String gpop) {
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    String gpopClr = null;

    try {
      statement = conn.prepareCall(GPOP_COLOR_FUNCTION);
      if (null != statement) {

        statement.registerOutParameter(1, java.sql.Types.VARCHAR);
        statement.setInt(2, siteLocationId);
        statement.setString(3, serviceInstance);
        statement.setString(4, gpop);
        statement.execute();
        gpopClr = statement.getString(1);

      }
    }

    catch (SQLException e) {
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
    return gpopClr;
  }

  /**
   * This methods is used to handle the null and set the value to
   * ConfigureAccessDomain
   * 
   * @param value.
   * 
   * @return String.
   */

  public String evaluateString(Object value) {
    String result;
    if (value == null) {

      result = "None";
    } else {
      result = value.toString();
    }
    return result;
  }

  /**
   * This methods is used to handle the null and set the value to
   * ConfigureAccessDomain
   * 
   * @param value.
   * 
   * @return int.
   */
  public int evaluateInt(Object value) {

    int reset;
    if (value == null) {
      reset = 0;
    } else {
      reset = Integer.parseInt(value.toString());
    }

    return reset;
  }

}
