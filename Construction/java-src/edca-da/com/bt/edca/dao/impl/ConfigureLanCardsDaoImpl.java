package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.ConfigureLanCardsCountryDomain;

import com.bt.edca.da.domain.ConfigureLanCardsDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.ConfigureLanCardsDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;
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
 * class that implements the method declared in Configurelancardsdao interface.
 * @author Jagankumar G.
 *
 */
public class ConfigureLanCardsDaoImpl implements ConfigureLanCardsDao {

  private static final String SERVICE_INSTANCE_ID = "serviceInstanceId";
  private static final String LANSEQID = "seqId";
/**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(ConfigureLanCardsDaoImpl.class);

  /**
   *implementation of dao layer method.
   *fetches configurelancards details. 
   */
  public List getConfigureLanCards(String serviceInstanceId) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;

    ResultSet rs = null;
    List<ConfigureLanCardsDomain> configureLanCardDTOList = 
        new ArrayList<ConfigureLanCardsDomain>();

    try {
     
      statement = conn.prepareCall(GET_SERVICE_INSTANCE_ID);
      statement.setString(1, serviceInstanceId);
      statement.registerOutParameter(2, OracleTypes.CURSOR);
      statement.execute();
      rs = (ResultSet) statement.getObject(2);

      if (null != rs) {
        while (rs.next()) {

          ConfigureLanCardsDomain configureLanCardDetails = new ConfigureLanCardsDomain();

          configureLanCardDetails.setServiceInstanceId(rs.getInt(1));
          configureLanCardDetails.setSeqId(rs.getInt(2));
          configureLanCardDetails.setLanUsage(rs.getString(3));
          configureLanCardDetails.setLanSwitch(rs.getString(4));
          configureLanCardDetails.setChassis(rs.getString(5));

          configureLanCardDTOList.add(configureLanCardDetails);
        }
      }

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
          session.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }
   
    return configureLanCardDTOList;

  }

  
  public List getConfigureLanCable(String serviceInstanceId, int seqId, String cableType) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    ResultSet rs = null;
    List<ConfigureLanCardsDomain> cableDetailsList = new ArrayList<ConfigureLanCardsDomain>();

    try {
      System.out.println("=============insidetryblock GET_CABLE_SELECTION_DETAILS   ========JAGAN");
      statement = conn.prepareCall(GET_CABLE_SELECTION_DETAILS);
      statement.setString(1, serviceInstanceId);
      statement.setInt(2, seqId);
      statement.setString(3, cableType);
      statement.registerOutParameter(4, java.sql.Types.VARCHAR);
      statement.registerOutParameter(5, OracleTypes.CURSOR);

      statement.execute();

      rs = (ResultSet) statement.getObject(5);

      if (null != rs) {
        while (rs.next()) {

          ConfigureLanCardsDomain cableDomainDetails = new ConfigureLanCardsDomain();

          cableDomainDetails.setLanSwitch(rs.getString(1));
          cableDomainDetails.setCableName(rs.getString(2));
          cableDomainDetails.setNumberOfCables(rs.getString(3));

          cableDetailsList.add(cableDomainDetails);

        }
      }

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

    finally {
      try {
        if (rs != null) {
          rs.close();
          session.close();
        }

        if (null != statement) {
          statement.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }
   
    return cableDetailsList;

  }

  /**
   * method to fetch details of Cardselection.
   * 
   * @inpout serviceInstanceId,seqId.
   */
  public List<ConfigureLanCardsDomain> getCardSelection(String serviceInstanceId, int seqId) {

    System.out.println("====inside Lancards============");
    int sequenseid = seqId;
    ConfigureLanCardsDomain details = new ConfigureLanCardsDomain();
    List<ConfigureLanCardsDomain> cardList = new ArrayList<ConfigureLanCardsDomain>();
    List lanCardslist = HibernateQueryUtil.executeQuery(GET_CARD_DETAILS,
        new String[] { SERVICE_INSTANCE_ID, LANSEQID }, 
        new Object[] { serviceInstanceId, sequenseid });

    return lanCardslist;
  }

  private int evaluateInt(Object value) {

    int result;
    if (value == null) {
      result = 0;
    } else {
      result = (Integer) value;

    }
    return result;
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
   * @see fetches the Card Column details.
   * @param serviceInstanceId,seqId.
   */
  public List<ConfigureLanCardsDomain> getCardColumnDetails(String serviceInstanceId, int seqId) {

   
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement1 = null;

    ResultSet rs = null;
    List<ConfigureLanCardsDomain> cardColumnDetailsList = new ArrayList<ConfigureLanCardsDomain>();

    try {
     
      statement1 = conn.prepareCall(GET_CARD_COLUMN_DETAILS);

      statement1.setString(1, serviceInstanceId);
      statement1.setInt(2, seqId);
      statement1.registerOutParameter(3, OracleTypes.VARCHAR);
      statement1.registerOutParameter(4, OracleTypes.CURSOR);

      statement1.execute();
      rs = (ResultSet) statement1.getObject(4);

      if (null != rs) {
        while (rs.next()) {

          ConfigureLanCardsDomain configureLanCardDetails = new ConfigureLanCardsDomain();

          configureLanCardDetails.setCardId(rs.getInt(1));
          configureLanCardDetails.setCardDescription(rs.getString(2));
          configureLanCardDetails.setCardPosition(rs.getString(3));
          configureLanCardDetails.setPortType(rs.getString(4));

          cardColumnDetailsList.add(configureLanCardDetails);
        }
      }

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

    finally {
      try {
        if (rs != null) {
          rs.close();
        }

        if (null != statement1) {
          statement1.close();
          session.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }
    System.out.println("LIST DETAILS" + cardColumnDetailsList);
    return cardColumnDetailsList;

  }

  /**
   * method to fetch the CableSelection details.
   * 
   * @param serviceInstanceid,seqId.
   */
  public List getCableSelection(String serviceInstanceId, int seqId) {

   
    int sequenseid = seqId;

    List<ConfigureLanCardsDomain> list = HibernateQueryUtil.executeQuery(GET_LAN_CABLE_DETAILS,
        new String[] { SERVICE_INSTANCE_ID, LANSEQID }, new Object[] { serviceInstanceId, sequenseid });
   
    return list;
  }
  /**
   * method to fetch lanCables.
   * 
   * @return lanCables list.
   */
  public List getLanCableSelectionLanCablesList(String cpename, String cableType, String cablePort,
      String interfaceType, String electricInterface, String country, 
      int satelliteSupport, String systemName,
      int siteId, String orderType, int noOfCircuits, String cpeUsage, int eosCheck) {

   
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement cableStatement = null;

    ResultSet rs = null;
    List lanCablesDetailsList = new ArrayList();

    try {
      
      cableStatement = conn.prepareCall(GET_LAN_CABLE_SELECTION_LAN_CABLES);

      cableStatement.setString(1, cpename);
      cableStatement.setString(2, cableType);
      cableStatement.setString(3, cablePort);
      cableStatement.setString(4, interfaceType);
      cableStatement.setString(5, electricInterface);
      cableStatement.setString(6, country);
      cableStatement.setInt(7, satelliteSupport);
      cableStatement.setString(8, systemName);
      cableStatement.setInt(9, siteId);
      cableStatement.setString(10, orderType);
      cableStatement.setInt(11, noOfCircuits);
      cableStatement.setString(12, cpeUsage);
      cableStatement.setInt(13, eosCheck);
      cableStatement.registerOutParameter(14, OracleTypes.CURSOR);
      cableStatement.registerOutParameter(15, OracleTypes.VARCHAR);
      cableStatement.registerOutParameter(16, OracleTypes.VARCHAR);

      cableStatement.execute();
      rs = (ResultSet) cableStatement.getObject(14);

      if (null != rs) {
        while (rs.next()) {

          ConfigureLanCardsDomain configureLanCardDetails = new ConfigureLanCardsDomain();

          configureLanCardDetails.setCableShortName(rs.getString(1));
          configureLanCardDetails.setTotalCables(rs.getInt(2));

          lanCablesDetailsList.add(configureLanCardDetails);

        }
      }

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

    finally {
      try {
        if (rs != null) {
          rs.close();
        }

        if (null != cableStatement) {
          cableStatement.close();
          session.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }
    
    return lanCablesDetailsList;

  }

  /**
   * method to fetch the OpticalModules grid.
   * @param serviceinstanceId.
   * @param seqId.
   * @param cableType.
   * 
   */
  public List<ConfigureLanCardsDomain> getOpticalDetails(
      String serviceInstanceId, int seqId, String cableType) {

    
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;

    ResultSet rs = null;
    List<ConfigureLanCardsDomain> opticalColumnDetailsList =
        new ArrayList<ConfigureLanCardsDomain>();

    try {
      
      statement = conn.prepareCall(GET_OPTICAL_DETAILS);

      statement.setString(1, serviceInstanceId);
      statement.setInt(2, seqId);
      statement.setString(3, cableType);
      statement.registerOutParameter(4, OracleTypes.VARCHAR);
      statement.registerOutParameter(5, OracleTypes.CURSOR);

      statement.execute();
      rs = (ResultSet) statement.getObject(5);

      if (null != rs) {
        while (rs.next()) {

          ConfigureLanCardsDomain configureLanCardDetails = new ConfigureLanCardsDomain();

          configureLanCardDetails.setLanSwitch(rs.getString(1));
          configureLanCardDetails.setCableName(rs.getString(2));
          configureLanCardDetails.setNumberOfCables(rs.getString(3));

          opticalColumnDetailsList.add(configureLanCardDetails);
        }
      }

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
           session.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }
    return opticalColumnDetailsList;

  }

  /**
   * merthod to fetch country.
   * @param serviceInstanceId.
   */
  public List<ConfigureLanCardsCountryDomain> getConfigureSiteDetails(String serviceInstanceId) {
    List configureSiteDetailsList = HibernateQueryUtil
        .executeQuery(SITE_DETAILS, new String[] { SERVICE_INSTANCE_ID },
        new Object[] { serviceInstanceId.toString() });
   

    return configureSiteDetailsList;
  }

}
