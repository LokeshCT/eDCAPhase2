package com.bt.edca.dao.impl;


import com.bt.edca.da.domain.ConfigureWicnmpaDomain;
import com.bt.edca.da.domain.ConfigureWicnmpaInterfaceDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.ConfigureWicnmpaDao;
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
 * 
 * @author Rohit .
 * Description : Class for getting the value from db .
 *
 */
public class ConfigureWicnmpaDaoImpl 
implements ConfigureWicnmpaDao {
  /**
   * Description : Variable logger for getting the logger value.
   */
  private static EdcaLogger logger = EdcaLogger.
      getLogger(ConfigureWicnmpaDaoImpl.class);
  /**
   * Description : List for getting the  1st grid value .
   * @return configureWicnmpaList .
   */
  public List getConfigureWicnmpa(String serviceInstanceId) {
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    ResultSet rs = null;
    List<ConfigureWicnmpaDomain> configureWicnmpaList =
        new ArrayList<ConfigureWicnmpaDomain>();
    try {
      statement = conn.prepareCall(
          GET_CONFIGURE_WICNMPA_BY_SERVICE_INSTANCE_ID);
      
      if (null != statement) {

        statement.setString(1, serviceInstanceId);
        statement.registerOutParameter(2, OracleTypes.CURSOR);
        statement.execute();

        rs = (ResultSet) statement.getObject(2);

        if (null != rs) {

          while (rs.next()) {
            ConfigureWicnmpaDomain configureWicnmpa = 
                new ConfigureWicnmpaDomain();
            configureWicnmpa.setCpeSeqId(rs.getString(2));
            configureWicnmpa.setCpeUsage(rs.getString(3));
            configureWicnmpa.setBaseRouter(rs.getString(4));
            configureWicnmpa.setChassis(rs.getString(5));
            
            configureWicnmpaList.add(configureWicnmpa);
          }
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
        return configureWicnmpaList;

      }
  /**
   * Description: for getting the card slot .
   * @return cardSlotList .
   * @param serviceInstanceId .
   * @param cpeSeqId .
   */
  public List<ConfigureWicnmpaDomain> getCardSlot(
      String serviceInstanceId,String cpeSeqId) {
    
    System.out.println("=asassasa====" + cpeSeqId);
    List<ConfigureWicnmpaDomain> cardSlotList = HibernateQueryUtil.executeQuery(
        GET_CARD_SLOT_LIST, new String[] { "serviceInstanceId", "cpeSeqId" },
        new Object[] {serviceInstanceId, cpeSeqId});
    return cardSlotList;
    
  }
  /**
   * Description : List for getting the portType .
   * @return portTypeList .
   * @param serviceInstanceId .
   * @param cpeSeqId .
   * 
   */
  public List getPortType(String  serviceInstanceId , String cpeSeqId) {
    
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    ResultSet rs = null;
    List<ConfigureWicnmpaDomain> portTypeList =
        new ArrayList<ConfigureWicnmpaDomain>();
    try {
      statement = conn.prepareCall(GET_PORT_TYPE_LIST);
      
      if (null != statement) {

        statement.setString(1, serviceInstanceId);
        statement.setString(2, cpeSeqId);
        statement.registerOutParameter(3, OracleTypes.VARCHAR);
        statement.registerOutParameter(4, OracleTypes.CURSOR);
        System.out.println("enterd into port type");
        statement.execute();

        rs = (ResultSet) statement.getObject(4);

        if (null != rs) {

          while (rs.next()) {
            ConfigureWicnmpaDomain configPort = 
                new ConfigureWicnmpaDomain();
            configPort.setCardDesc(rs.getString(2));
            configPort.setCardPosition(rs.getString(3));
            configPort.setPortType(rs.getString(4));
           System.out.println("value of portTYpe" + configPort.getPortType());
            
            portTypeList.add(configPort);
          }
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
        return portTypeList;         
  }
  
  
  /**
   * Description: List for getting the interface type .
   * @return interfaceList .
   */
  public List getInterface(String serviceInstanceId) {
    List<ConfigureWicnmpaInterfaceDomain> interfaceList =
        HibernateQueryUtil.executeQuery(
        GET_INTERFACE_BY_SERVICE_INSTANCE_ID, 
     new String[] { "serviceInstanceId" },
     new Object[] { serviceInstanceId });
     return interfaceList;
 }
  /**
   * Description : List for getting the noOf circuit .
   * @return cktList .
   */
  public List getNoOfCircuits(String serviceInstanceId) {
    
    
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    List cktList = new ArrayList();
    try {
      statement = conn.prepareCall(GET_NO_OF_CKT_);
      
      if (null != statement) {

        statement.setString(1, serviceInstanceId);
        statement.registerOutParameter(2, OracleTypes.INTEGER);
        statement.registerOutParameter(3, OracleTypes.VARCHAR);
        System.out.println("enterd into no of circuits");
        statement.execute();

        ConfigureWicnmpaDomain configPort = 
            new ConfigureWicnmpaDomain();
        
        
        configPort.setNoOfCircuits(statement.getInt(2));
        System.out.println("============bahdbadhadab========"+statement.getInt(2));

        cktList.add(configPort);
            
         
           
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
        return cktList;
       
  }
  
 /** 
  * List for getting the country .
  * @return idCountryList .
  * @param serviceInstanceId .
  */
  public List getSiteCountry(String serviceInstanceId){
    
    List idCountryList = HibernateQueryUtil.executeQuery(
        GETSITEIDCOUNTRYBYSERVICEINSTANCEID, 
     new String[] { "serviceInstanceId" },
     new Object[] { serviceInstanceId });
     return idCountryList;
   
  }
  /**
   * Description : List for getting the eos value .
   * @return getEosCheck .
   * @param serviceInstanceId .
   */
  public List getEosCheck(String serviceInstanceId){
    
    List getEosCheck = HibernateQueryUtil.executeQuery(
        GETEOSBYSERVICEINSTANCEID, 
     new String[] { "serviceInstanceId" },
     new Object[] { serviceInstanceId });
     return getEosCheck;
    
    
    
  }
  /**
   * description : list for getting the Cable  Short name.
   * @return configureWicnmpaList .
   * @param cpeName .
   * @param cableType .
   * @param cablePort .
   * @param interfaceType .
   * @param electricalInterface .
   * @param country .
   * @param satelliteSupport .
   * @param systemName .
   * @param siteId .
   * @param orderType .
   * @param noOfCircuits .
   * @param cpeUsage .
   * @param eos .
   */
  public List getCable(String cpeName, String cableType, String cablePort,
      String interfaceType, String electricalInterface,
      String country, int satelliteSupport, String systemName,
      int siteId, String orderType, int noOfCircuits,
      String cpeUsage, int eos) {
     
    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    ResultSet rs = null;
    List<ConfigureWicnmpaDomain> configureWicnmpaList =
        new ArrayList<ConfigureWicnmpaDomain>();
    try {
      statement = conn.prepareCall(GETCABLEBYSERVICEINSTANCEID);
      
      if (null != statement) {
        System.out.println("enterd into biggest procedure");
        statement.setString(1, cpeName);
        statement.setString(2, cableType);
        statement.setString(3, cablePort);
        statement.setString(4, interfaceType);
        statement.setString(5, electricalInterface);
        statement.setString(6, country);
        statement.setInt(7, satelliteSupport);
        statement.setString(8, systemName);
        statement.setInt(9, siteId);
        statement.setString(10, orderType);
        statement.setInt(11, noOfCircuits);
        statement.setString(12, cpeUsage);
        statement.setInt(13, eos);
        statement.registerOutParameter(14, OracleTypes.CURSOR);
        statement.registerOutParameter(15, OracleTypes.VARCHAR);
        statement.registerOutParameter(16, OracleTypes.VARCHAR);
        
        statement.execute();
        rs = (ResultSet) statement.getObject(14);
        if (null != rs) {
          while (rs.next()) {
            
            ConfigureWicnmpaDomain configureWicnmpa = 
                new ConfigureWicnmpaDomain();
            configureWicnmpa.setCableShortName(rs.getString(1));
            System.out.println("============Inside cable dao big procedure========"+configureWicnmpa.getCableShortName());
            configureWicnmpaList.add(configureWicnmpa);
          }
        }
        System.out.println("existing from biggest procedure");
        
        
       
        
        
        System.out.println("============Inside cable dao big procedure========");

       
            
         
           
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
        return configureWicnmpaList;
     
    }
  
  /**
   * Description : List for getting the total  number of cable .
   * @return configureTotalCable .
   * @param serviceInstanceId .
   * @param cpeSeqId .
   * @param cableType .
   */
  public List getTotalCable(String serviceInstanceId , 
      String cpeSeqId, String cableType) {
    
     Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    ResultSet rs = null;
    List<ConfigureWicnmpaDomain> configureTotalCable =
        new ArrayList<ConfigureWicnmpaDomain>();
    try {
      statement = conn.prepareCall(GETTOTALCABLE);
      
      if (null != statement) {
        System.out.println("enterd into TOTAL NUMBER OF CABLE");
        statement.setString(1, serviceInstanceId);
        statement.setString(2, cpeSeqId);
        statement.setString(3, cableType);
       
        statement.registerOutParameter(4, OracleTypes.VARCHAR);
        statement.registerOutParameter(5, OracleTypes.CURSOR);
       
        
        statement.execute();
        rs = (ResultSet) statement.getObject(5);
        if (null != rs) {
          while (rs.next()) {
            
            ConfigureWicnmpaDomain configureWicnmpa = 
                new ConfigureWicnmpaDomain();
            configureWicnmpa.setTotalCable(rs.getString(2));
            configureWicnmpa.setTotalCable(rs.getString(3));
            System.out.println("============Inside dao total cable========"+configureWicnmpa.getTotalCable());
            configureTotalCable.add(configureWicnmpa);
          }
        }
        System.out.println("existing from total cable dao");

       }
           
     
    }catch (SQLException e) {
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
        return configureTotalCable;
      
  }
  
  
  
  
  
  }
