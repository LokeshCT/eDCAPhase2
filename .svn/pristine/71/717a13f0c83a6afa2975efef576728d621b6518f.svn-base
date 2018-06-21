package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.DSCPCosDomain;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.DSCPCosDao;
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
 * DAO implementation for interface DSCPCosDao. fetches the TIR,CIPR and MIPR
 * values.
 * 
 * @author Jagan kumar G.
 *
 */
public class DSCPCosDaoImpl implements DSCPCosDao {

  private static EdcaLogger logger = EdcaLogger.getLogger(DSCPCosDaoImpl.class);

  /**
   * Method to lblCosModel and lbl Customization.
   * 
   * @input param orderId.
   */
  public List<DSCPCosDomain> getDSCPCosDetails(String orderId) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    List list = new ArrayList();
    ResultSet rs = null;
    DSCPCosDomain dscpDomainValuesList = new DSCPCosDomain();

    try {

      statement = conn.prepareCall(GET_DSCP_COS_DETAILS);
      statement.setString(1, orderId);
      statement.setInt(2, OracleTypes.VARCHAR);
      statement.registerOutParameter(3, OracleTypes.CURSOR);
      statement.execute();
      rs = (ResultSet) statement.getObject(3);

      if (null != rs) {
        while (rs.next()) {

          DSCPCosDomain dscpDomainList = new DSCPCosDomain();

          dscpDomainList.setOrderId(rs.getString(1));
          dscpDomainList.setProductName(rs.getString(2));
          dscpDomainList.setLblCosModel(rs.getString(3));
          dscpDomainList.setLblCustomization(rs.getString(4));
          dscpDomainList.setCosPolicy(rs.getString(5));
          list.add(dscpDomainList);

        }
      }

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

    finally {
      try {
        if (rs != null) {
        }

        if (null != statement) {
          statement.close();
          session.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }
    System.out.println("LIST DETAILS  " + dscpDomainValuesList);
    return list;

  }

  /**
   * method to fetch TIR bandwidth.
   * 
   * @input vpnId.
   */
  public DSCPCosDomain getTirBandwidth(int vpnId) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;

    ResultSet rs = null;
    DSCPCosDomain dscpDomainValuesList = new DSCPCosDomain();

    try {

      statement = conn.prepareCall(GET_TIR_DETAILS);
      statement.setInt(1,123);
      statement.setInt(2, OracleTypes.VARCHAR);
      statement.registerOutParameter(3, OracleTypes.CURSOR);
      statement.execute();
      rs = (ResultSet) statement.getObject(3);
      
      if (null != rs) {
        while (rs.next()) {

          dscpDomainValuesList.setConnectionBandWidth(rs.getInt(1));

        }
      }

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

    finally {
      try {
        if (rs != null) {
        }

        if (null != statement) {
          statement.close();
          session.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }
    System.out.println("LIST DETAILS  " + dscpDomainValuesList);
    return dscpDomainValuesList;

  }

  /**
   * fetches DSCP COS( CIPR and MIPR)details from SP.
   * 
   * @input vpnId.
   */
  public DSCPCosDomain getProcDscpCosDetails(int vpnId) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;

    ResultSet rs = null;
    DSCPCosDomain dscpCosDomainValues = new DSCPCosDomain();

    try {

      statement = conn.prepareCall(GET_PROC_DSCP_COS_DETAILS);
      statement.setInt(1,123);
      statement.setInt(2, OracleTypes.VARCHAR);
      statement.registerOutParameter(3, OracleTypes.CURSOR);
      statement.execute();
      rs = (ResultSet) statement.getObject(3);
      
      if (null != rs) {
        while (rs.next()) {

          dscpCosDomainValues.setVpnId(rs.getInt(1));
          dscpCosDomainValues.setCiprEf(rs.getInt(2));
          dscpCosDomainValues.setCiprAf1(rs.getInt(3));
          dscpCosDomainValues.setCiprAf2(rs.getInt(4));
          dscpCosDomainValues.setCiprAf3(rs.getInt(5));
          dscpCosDomainValues.setCiprAf3(rs.getInt(6));
          dscpCosDomainValues.setMiprAf1(rs.getInt(7));
          dscpCosDomainValues.setMiprAf2(rs.getInt(8));
          dscpCosDomainValues.setMiprAf3(rs.getInt(9));
          dscpCosDomainValues.setMiprAf4(rs.getInt(10));
          dscpCosDomainValues.setMiprDe(rs.getInt(11));
          dscpCosDomainValues.setMiprEf(rs.getInt(12));
          dscpCosDomainValues.setCipr(rs.getInt(13));
          dscpCosDomainValues.setCiprMgt(rs.getInt(14));
          dscpCosDomainValues.setCosBleaching(rs.getInt(15));
          dscpCosDomainValues.setCosVoip(rs.getInt(16));

        }
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

    finally {
      try {
        if (null != statement) {
          statement.close();
          session.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }

    return dscpCosDomainValues;

  }

}
