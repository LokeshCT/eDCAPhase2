package com.bt.edca.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import com.bt.edca.da.domain.SitePageDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.SitesPageDetailsDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.HelperMethods;

import oracle.jdbc.OracleTypes;

public class SitesPageDetailsDaoImpl implements SitesPageDetailsDao {

  /**
   * Format of orderId.
   */
  private static final String ORDER_ID = "orderId";
  
  String service_Flag= "Y";

  /**
   * Format of RESILIENCE.
   */
  private static final String RESILIENCE = "resilience";

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(SitesPageDetailsDaoImpl.class);

  /**
   * Description : Gets ResilienceCount Using OrderId.
   * 
   * @param orderId.
   * @return resilienceCount.
   */

  public int getResilienceCount(String orderId, String resilience) {

    int resilienceCount = 0;

    List siteResilience = HibernateQueryUtil.executeQuery(GET_RESILIENCE_BY_ORDER_ID,
        new String[] { ORDER_ID, RESILIENCE }, new Object[] { orderId, resilience });

    if (!HelperMethods.isNullOrEmpty(siteResilience) && siteResilience.get(0) != null) {
      resilienceCount = Integer.parseInt(siteResilience.get(0).toString());

    }

    return resilienceCount;
  }

  public String getSatelliteAccess(String orderId) {

    Session session = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) session;
    Connection conn = sessimpl.connection();
    CallableStatement statement = null;
    String orderID = orderId;

    String satelliteAccess = null;

    try {
      statement = conn.prepareCall(SATELLITE_ACCESS_PROCEDURE);

      if (null != statement) {
        statement.setString(1, orderID);
        statement.registerOutParameter(2, OracleTypes.VARCHAR);
        statement.registerOutParameter(3, OracleTypes.VARCHAR);
        statement.execute();
        satelliteAccess = statement.getString(2);

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
    return satelliteAccess;
  }

  public String getOrderType(String orderId) {
    String orderType = null;

    List orderTypeList = HibernateQueryUtil.executeQuery(GET_ORDER_TYPE_BY_ORDER_ID, new String[] { ORDER_ID },
        new Object[] { orderId });

    if (!HelperMethods.isNullOrEmpty(orderTypeList) && orderTypeList.get(0) != null) {

      orderType = orderTypeList.get(0).toString();

    }
    return orderType;
  }

  public List<SitePageDomain> getSitesDetails(String orderId, int resilience, String satelliteAccess, String orderType,
      String orderStatus) {

    int orderID = Integer.parseInt(orderId);
    Session session = HibernateUtil.getSession();
    Query query = null;
    List<Object[]> result = new ArrayList();
    List<SitePageDomain> subResult = new ArrayList<SitePageDomain>();
    List<SitePageDomain> finalList = new ArrayList<SitePageDomain>();
    int flag = 0;
    try {

      if (resilience > 1 || satelliteAccess == "YES") {
        
        if (orderType.equalsIgnoreCase("ADD")
            && (orderStatus.equalsIgnoreCase("UTC") || orderStatus.equalsIgnoreCase("UTCT"))) {

          System.out.println("******* inside IF **********");
          query = session.createSQLQuery("select ds.SITE_ID,Q_SITE_ID,RESILIENCE,PRICING,SERVICEINSTANCE from "
              + "DDDCA_SITE_SERVICE_INSTANCE,DDDCA_SITE_LOCATION,dddca_site ds,"
              + "(select min(q_site_id) as Q_SITE_ID,app_site_id from dca_cla_site group by app_site_id)dcs "
              + "where ds.site_id=dcs.app_site_id(+) and " + "ds.SITE_ID= DDDCA_SITE_LOCATION.SITE_ID and "
              + "DDDCA_SITE_LOCATION.SITE_LOCATION_ID=DDDCA_SITE_SERVICE_INSTANCE.SITE_LOCATION_ID and " + "order_ID ="
              + orderId + " order by  DDDCA_SITE_SERVICE_INSTANCE.SERVICEINSTANCE asc");
          
          
          flag=5;
          result = query.list();
        

        } else {
          System.out.println("******* inside IF  Else **********");
          query = session
              .createSQLQuery("select ds.SITE_ID,ExternalSystemId as Q_SITE_ID,RESILIENCE,PRICING,SERVICEINSTANCE "
                  + "from DDDCA_SITE_SERVICE_INSTANCE,DDDCA_SITE_LOCATION,dddca_site ds,"
                  + "(select min(q_site_id) as Q_SITE_ID,app_site_id from dca_cla_site group by app_site_id) dcs "
                  + " where ds.site_id=dcs.app_site_id(+) and " + "ds.SITE_ID= DDDCA_SITE_LOCATION.SITE_ID and "
                  + "DDDCA_SITE_LOCATION.SITE_LOCATION_ID=DDDCA_SITE_SERVICE_INSTANCE.SITE_LOCATION_ID and "
                  + "order_ID =" + orderId + "order by  DDDCA_SITE_SERVICE_INSTANCE.SERVICEINSTANCE asc ");

          result = query.list();
          System.out.println("******** calling native query******" + query.list().size());
          flag=5;
    
        }
       // displayServiceInstance(result);
      } else {
        
        service_Flag ="N";        
        if (orderType.equalsIgnoreCase("ADD")
            && (orderStatus.equalsIgnoreCase("UTC") || orderStatus.equalsIgnoreCase("UTCT"))) {

          query = session.createSQLQuery("select SITE_ID,Q_SITE_ID,RESILIENCE,PRICING from dddca_site ds,"
              + "(select min(q_site_id) as Q_SITE_ID,app_site_id from dca_cla_site group by app_site_id) dcs"
              + " where ds.site_id=dcs.app_site_id(+) and " + "order_ID =" + orderId);
          result = query.list();
          flag=4;
          
        } else {

          query = session
              .createSQLQuery("select SITE_ID,ExternalSystemId as Q_SITE_ID,RESILIENCE,PRICING from dddca_site ds,"
                  + "(select min(q_site_id) as Q_SITE_ID,app_site_id from dca_cla_site group by app_site_id) dcs "
                  + "where ds.site_id=dcs.app_site_id(+) and " + "order_ID =" + orderId);
          result = query.list();
          flag=4;
        
        }
       // displayResult(result);
      }
      /* 
      if (orderType.equalsIgnoreCase("ADD")
          && (orderStatus.equalsIgnoreCase("UTC") || orderStatus.equalsIgnoreCase("UTCT"))) {

        //int neworder = 2002;
     query = session.createSQLQuery("select Q_SITE_ID from dddca_site ds, "
            + "(select min(q_site_id) as Q_SITE_ID,app_site_id from dca_cla_site group by app_site_id) dcs "
            + "where ds.site_id=dcs.app_site_id(+) and " + "order_ID =" + orderId);
        subResult = query.list();
        System.out.println("insidellllllaaaaatttttt if  " + result.size());
        result = compareResult(result, subResult);
      }
		*/
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    finalList =  setDomainValue(result,flag);
   
    return finalList;
  }

  /*public void displayServiceInstance(List result) {
    for (int i = 0; i < result.size(); i++) {
      System.out.println("Details of Sites " + (i + 1));

      Object[] data = (Object[]) result.get(i);
    }
  }

  public void displayResult(List result) {
    for (int i = 0; i < result.size(); i++) {
      System.out.println("Details of Sites " + (i + 1));

      Object[] data = (Object[]) result.get(i);
    }
  }
*/
  /*public List compareResult(List result, List subResult) {

    int i = 0;
    Object[] resultData = null;
   // String subResultData = null;
    List display = new ArrayList();
    try {
      while (i < result.size()) {

        resultData = (Object[]) result.get(i);
  
      Object obj=subResult.get(i);
        
       // subResultData = subResult.get(i).toString();
        if(obj != null)
        {
        resultData[1] = obj.toString();
        }
        else
        {
          resultData[1] = null;
        }
   
        i = i + 1;
        for (int j = 0; j < resultData.length; j++) {
          display.add(resultData[j]);
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return display;
  }*/
  
  
  public List setDomainValue(List<Object[]> result,int flag)
  {
   
    List<SitePageDomain> siteDetailsList = new ArrayList<SitePageDomain>();    
      
      /**
       * 
       * Joseph Added
       */
    	if(!result.isEmpty()){
    		
    	
      for (Object[] row : result) {
    	  SitePageDomain sitePageDomain = new SitePageDomain();
    	  sitePageDomain.setSiteId(Long.parseLong(evaluateString(row[0])));
    	  sitePageDomain.setDcaReferenceId(evaluateString(row[1]));
    	  sitePageDomain.setResilience(evaluateString(row[2]));
    	  sitePageDomain.setPricing(evaluateString(row[3]));
    	  if(flag == 5)
    	  {
    	  sitePageDomain.setServiceInstance(evaluateString(row[4]));
    	  }
    	  else
    	  {
    		  sitePageDomain.setServiceInstance(null);
    	  }
    	  
    	  siteDetailsList.add(sitePageDomain);
      }
    	}
      //List<SitePageDomain> pageList= result;
      // int m=0;
      //Object[] obj = null;
      
    
   /*  try
     {
        
    	 	//obj= result.toArray();
     
        while(m<result.size())
        { 
          SitePageDomain sitePageDomain = new SitePageDomain();
                   
           //sitePageDomain.setSiteId(Long.parseLong(obj[0].toString()));
         sitePageDomain.setSiteId(Long.parseLong((evaluateString(obj[0]))));
          sitePageDomain.setPricing(result.get(3).toString());
           sitePageDomain.setDcaReferenceId(evaluateString(obj[1]));
          sitePageDomain.setResilience(evaluateString(obj[2]));
          sitePageDomain.setPricing(evaluateString(obj[3]));
          sitePageDomain.setServiceInstance(evaluateString(obj[4]));
         //  sitePageDomain.setSiteId(result)
        
          
         
         siteDetailsList.add(sitePageDomain);
        m=m+1;
        }
        */
       /* 
        for(SitePageDomain details : pageList)
        {
          
          
          SitePageDomain sitePageDomain = new SitePageDomain();
          
        
         
        
          sitePageDomain.setSiteId(details.getSiteId());
          sitePageDomain.setDcaReferenceId(details.getAccessOption());
          sitePageDomain.setResilience(details.getResilience());
          sitePageDomain.setPricing(details.getPricing());
          sitePageDomain.setServiceInstance(details.getServiceInstance());
         
           siteDetailsList.add(sitePageDomain);
         
          
        }
     
      
       
      }catch (Exception e) {
        logger.error(e.getMessage(), e);
      } */  
  
    return siteDetailsList;
  }
  
  public String evaluateString(Object value) {
    String result;
    if (value == null) {

      result = "Null";
    } else {
      result = value.toString();
    }
    return result;
  }
  
}
