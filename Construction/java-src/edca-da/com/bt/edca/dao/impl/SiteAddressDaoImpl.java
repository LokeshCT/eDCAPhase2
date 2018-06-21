package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.SiteAddressCountryDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.SiteAddressDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

/**
 * Implementation of DAO layer for SiteAddress.
 * 
 * @author 609060481:Jagankumar G.
 *
 */
public class SiteAddressDaoImpl implements SiteAddressDao {

  /**
   * Captures the logs onto console.
   */
  private static EdcaLogger logger = 
      EdcaLogger.getLogger(SiteAddressDaoImpl.class);

  /**
   * Description:fetches the siteLocationId from DB 
   * and returns list of SiteAddress.
   * 
   * @param siteLocationId.
   * @return List.
   */

  public List getSiteAddress(String siteLocationId) {

    List siteAddressList = HibernateQueryUtil.executeQuery(
        GET_SITE_ADDRESS_LIST_BY_SITE_LOCATION_ID,
        new String[] { "siteLocationId" }, new Object[] { siteLocationId });

    return siteAddressList;

  }
  /**
   * Description : fetches the country for giving input to below method .
   * @param siteLocationId .
   * @return countryList .
   */
  public List getCountry(String siteLocationId){
    List countryList = HibernateQueryUtil.executeQuery(
        GET_COUNTRY_BY_SITE_LOCATION_ID,
        new String[] { "siteLocationId" }, new Object[] { siteLocationId });
    return countryList;
  }
  /**
   * Description : fetches the state .
   * @param country .
   * @return configureAccessDTOList .
   */
  public List getState(String country){
   
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    ResultSet rs = null;
    List<SiteAddressCountryDomain> configureAccessDTOList = 
        new ArrayList<SiteAddressCountryDomain>();
    try {
      statement = conn.prepareCall(GET_STATE_BY_COUNTRY_NAME);
      statement.setString(1, country);
      statement.registerOutParameter(2, OracleTypes.CURSOR);
      statement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
      statement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
      statement.execute();
      rs = (ResultSet) statement.getObject(2);
      
      if (null != rs) {
        while (rs.next()) {
          SiteAddressCountryDomain configureType = 
              new SiteAddressCountryDomain();
          configureType.setState(rs.getString(1));
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
   * Description : method for saving the data .
   */
  public void saveAddress(String siteLocationId,String floor,
      String room, String buildingNumber,String addressLine1
      , String addressLine2 ,String city , String postcode
      , String state){
    
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    try {
      statement = conn.prepareCall(SAVE_ADDRESS);
      statement.registerOutParameter(1, java.sql.Types.VARCHAR);
      statement.setString(2, siteLocationId);
      statement.setString(3, floor);
      statement.setString(4, room);
      statement.setString(5, buildingNumber);
      statement.setString(6, addressLine1);
      statement.setString(7, addressLine2);
      statement.setString(8, city);
      statement.setString(9, postcode);
      statement.setString(10, state);
      
      statement.execute();
    } catch (SQLException e) {
      } finally {
        try {
            if (null != statement) {
          statement.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    } 
      }
    
   }
