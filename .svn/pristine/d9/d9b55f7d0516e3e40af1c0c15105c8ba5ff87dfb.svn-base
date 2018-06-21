package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.ProductDetailsDomain;
import com.bt.edca.da.domain.UserDetailsDomain;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.UserDetailsDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;

import java.util.List;

/**
 * Description :This Class is mainly used to establish the session and execute
 * the procedure call based on few Input parameters and return the Output values
 * in a List.
 * 
 * @author SATHYA.
 */

public class UserDetailsDaoImpl implements UserDetailsDao {

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(UserDetailsDaoImpl.class);

  /**
   * Description :Gets userProfile details using this parameters. This method is
   * used to get the profileNames,default flag.
   * 
   * @param nuserId.
   * @return userDetailsList.
   */

  public List<UserDetailsDomain> getUserDetails(String nUserId) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection connection = sessimpl.connection();
    ResultSet resultSet = null;
    CallableStatement statement = null;
    int userId = Integer.parseInt(nUserId);
    List<UserDetailsDomain> userDetailsList = new ArrayList<UserDetailsDomain>();

    try {
      statement = connection.prepareCall(GET_USER_PROFILE);

      if (null != statement) {
        statement.setInt(1, userId);
        statement.registerOutParameter(2, OracleTypes.CURSOR);
        statement.execute();
        resultSet = (ResultSet) statement.getObject(2);
        if (null != resultSet) {

          while (resultSet.next()) {
            UserDetailsDomain userDetailsDomain = new UserDetailsDomain();
            userDetailsDomain.setProfileName(resultSet.getString("PROFILENAME"));
            userDetailsDomain.setDefaultFlag(resultSet.getString("DEFAULTFLAG"));
            userDetailsDomain.setTeamId(resultSet.getString("PROFILETEAMID"));
            userDetailsDomain.setProfileId(resultSet.getString("PROFILEID"));
            userDetailsList.add(userDetailsDomain);

          }
        }
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
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

    return userDetailsList;

  }

  /**
   * Description : Gets product details using this parameters.
   * 
   * @param teamId.
   * @return productDetailsList.
   */

  public List<ProductDetailsDomain> getProductDetails(String teamId) {

    List<ProductDetailsDomain> productDetailsList = null;
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection connection = sessimpl.connection();
    ResultSet resultSet = null;
    CallableStatement statement = null;
    int teamid = Integer.parseInt(teamId);

    try {

      statement = connection.prepareCall(GET_PRODUCT_DETAILS);

      if (null != statement) {
        productDetailsList = new ArrayList<ProductDetailsDomain>();
        statement.setInt(1, teamid);
        statement.registerOutParameter(2, OracleTypes.CURSOR);
        statement.execute();
        resultSet = (ResultSet) statement.getObject(2);
        if (null != resultSet) {

          while (resultSet.next()) {
            ProductDetailsDomain productDetailsDomain = new ProductDetailsDomain();
            productDetailsDomain.setProductCatagoryName(resultSet.getString("PRODUCTCATEGORYNAME"));
            productDetailsDomain.setProductCatagoryId(resultSet.getString("PRODUCTCATEGORYID"));
            productDetailsList.add(productDetailsDomain);
          }
        }

      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
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
    logger.info("Exiting from ProductDaoImp in getProductDetails");
    return productDetailsList;

  }

  /**
   * Description:Save as Make As Default Profile in Database.
   * 
   * @param teamId,userId,profileId.
   * @return response.
   */

  public String saveUserDefaultFlag(String nUserId, String profileId, String teamId) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection connection = sessimpl.connection();
    CallableStatement statement = null;
    int userid = Integer.parseInt(nUserId);
    int profileid = Integer.parseInt(profileId);
    int teamid = Integer.parseInt(teamId);

    String response = null;

    try {

      statement = connection.prepareCall(GET_SAVEUSERDEFAULT_PROFILE);

      if (null != statement) {
        statement.setInt(1, userid);
        statement.setInt(2, profileid);
        statement.setInt(3, teamid);
        statement.registerOutParameter(4, Types.VARCHAR);
        statement.execute();
        response = statement.getString(4);
      }

    } catch (SQLException e) {
    	e.printStackTrace();
    } finally {
      try {
        if (null != statement) {
          statement.close();

        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }

    }
    return response;
  }
}
