package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.CustomerContactDetailsDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.CustomerContactDetailsDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.List;
/**
 * Implementation for DAO layer.
 * 
 * @author 609060481:Jagankumar G.
 *
 */
public class CustomerContactDetailsDaoImpl implements CustomerContactDetailsDao {

  /**
   * captures logs onto console.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(CustomerContactDetailsDaoImpl.class);

  /**
   * Description:fetches the order and contacttype from DB returns the list .
   * 
   * @param orderId,contactType.
   * @return List.
   */
  public List getCustomerContactDetails(String orderId, String[] contactTypes) {

    List customerContactDetailsList = HibernateQueryUtil
        .executeQuery(GET_CUSTOMER_CONTACT_DETAILS_LIST_BY_ORDER,
        new String[] { "orderId", "contactType" }, new Object[] { orderId, contactTypes });

    return customerContactDetailsList;

  }

  /**
   * Description:Implemenation of the method that is declared in Dao.
   * 
   * @see saves the OrderContactDetails into DB.
   */
  public void saveOrderContactdetails(CustomerContactDetailsDomain 
      customerContactDetailsDomain, String orderId) {

    String contactType = "OM";

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    ResultSet rs = null;

    try {

      statement = conn.prepareCall(SAVE_ORDER_CONTACT_DETAILS);
      statement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
      statement.setString(2, contactType);
      statement.setString(3, orderId);
      statement.setString(4, customerContactDetailsDomain.getFirstName());
      statement.setString(5, customerContactDetailsDomain.getLastName());
      statement.setString(6, customerContactDetailsDomain.getJobTitle());
      statement.setString(7, customerContactDetailsDomain.getEmail());
      statement.setString(8, customerContactDetailsDomain.getPhone());
      statement.setString(9, customerContactDetailsDomain.getMobilePager());
      statement.setString(10, customerContactDetailsDomain.getAccountMgrId());
      statement.setString(11, customerContactDetailsDomain.getSalesRepClassicId());
      
     
      statement.execute();
      
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

    finally {
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

  }

  /**
   * Description:Implemenation of the method that is declared in Dao.
   * @see saves the TechnicalContactDetails into DB.
   */
  public void saveTechnicalContactdetails(CustomerContactDetailsDomain 
      customerContactDetailsDomain, String orderId) {

    String contactType = "T";

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    ResultSet rs = null;

    try {
      statement = conn.prepareCall(SAVE_TECHNICAL_CONTACT_DETAILS);

      statement.setString(1, orderId);
      statement.setString(2, contactType);
      statement.setString(3, customerContactDetailsDomain.getContactId());
      statement.setString(4, customerContactDetailsDomain.getFirstName());
      statement.setString(5, customerContactDetailsDomain.getLastName());
      statement.setString(6, customerContactDetailsDomain.getJobTitle());
      statement.setString(7, customerContactDetailsDomain.getEmail());
      statement.setString(8, customerContactDetailsDomain.getPhone());
      statement.setString(9, customerContactDetailsDomain.getMobilePager());
      statement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);

      statement.execute();

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
   }

    finally {
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

  }
}
