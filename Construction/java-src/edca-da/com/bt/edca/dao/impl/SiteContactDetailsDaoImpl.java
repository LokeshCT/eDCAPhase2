package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.SiteContactDetailsDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.SiteContactDetailsDao;
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
 * Description: SiteContactDetailsDaoImpl implements SiteContactDetailsDao and
 * getting the Database connection and saving the result in the .
 * siteContactDetailsList List.
 * 
 * @author Rohit
 * 
 */
public class SiteContactDetailsDaoImpl implements SiteContactDetailsDao {

  /**
   * captures logs onto console.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(SiteContactDetailsDao.class);

  /**
   * Description : Getting the DB connection and fetching all the values and
   * returning in the values in the siteContactDetailsList.
   * 
   * @param siteLocationId
   *          .
   * @return siteContactDetailsList .
   */
  public List getSiteContactDetails(String siteLocationId, String[] contactTypes) {

    List siteContactDetailsList = HibernateQueryUtil
         .executeQuery(GET_SITE_CONTACT_DETAILS_LIST_BY_SITE_LOCATION_ID,
        new String[] { "siteLocationId", "contactType" },
        new Object[] { siteLocationId, contactTypes });
    return siteContactDetailsList;
  }

  /**
  * method implementation to save sitecontactdetails into DB. Values are
  * getting stored with procedure.
  *
  */
  public void savePrimarySiteContactDetails(
      List<SiteContactDetailsDomain> saveSiteContactDetails,String siteLocationId) {

    Session sessions = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) sessions;
    Connection conn = sessimpl.connection();
    CallableStatement pstatement = null;

    String pcontactType = "P";
    String pcontactId = null;
    String pfirstname = null;
    String plastname = null;
    String pjobTitle = null;
    String pemail = null;
    String ptelephone = null;
    String pmobilePager = null;

    for (SiteContactDetailsDomain primaryDomain : saveSiteContactDetails) {
      pcontactId = primaryDomain.getContactId();
      pfirstname = primaryDomain.getFirstName();
      plastname = primaryDomain.getLastName();
      pjobTitle = primaryDomain.getJobTitle();
      ptelephone = primaryDomain.getTelephone();
      pemail = primaryDomain.getEmail();
      pmobilePager = primaryDomain.getMobilePager();
    }

    savePrimaryDetails(pstatement, conn, siteLocationId, pcontactType, pcontactId,
        pfirstname, plastname, pjobTitle,pemail, ptelephone, pmobilePager);
  }
  
  /**
 * method saves the details of secondarysitecontacts.
 */
  public void saveSecondarySiteContactDetails(
      List<SiteContactDetailsDomain> saveSiteContactDetails,String siteLocationId) {

    Session sessions = HibernateUtil.getSession();
    SessionImpl sessimpl = (SessionImpl) sessions;
    Connection conn = sessimpl.connection();
    CallableStatement sstatement = null;

    String scontactType = "S";
    String scontactId = null;
    String sfirstname = null;
    String slastname = null;
    String sjobTitle = null;
    String semail = null;
    String stelephone = null;
    String smobilePager = null;

    for (SiteContactDetailsDomain secondaryDetailsDomain : saveSiteContactDetails) {
      scontactId = secondaryDetailsDomain.getContactId();
      sfirstname = secondaryDetailsDomain.getFirstName();
      slastname = secondaryDetailsDomain.getLastName();
      sjobTitle = secondaryDetailsDomain.getJobTitle();
      stelephone = secondaryDetailsDomain.getTelephone();
      semail = secondaryDetailsDomain.getEmail();
      smobilePager = secondaryDetailsDomain.getMobilePager();
    }
    	
    saveSecondaryDetails(sstatement, conn, siteLocationId, scontactType, scontactId, 
        sfirstname, slastname,sjobTitle, semail, stelephone, smobilePager);
  }

  /**
  * Description:Method saves the values for primarySiteContactDetails.
  * @param pstatement.
  * @param conn.
  * @param siteLocationIdDao.
  * @param pcontactType.
  * @param pcontactId.
  * @param pfirstname.
  * @param plastname.
  * @param pjobTitle.
  * @param pemail.
  * @param ptelephone.
  * @param pmobilePager.
  */
  public void savePrimaryDetails(CallableStatement pstatement, Connection conn, 
      String siteLocationIdDao,String pcontactType, String pcontactId, 
      String pfirstname, String plastname, String pjobTitle,
      String pemail, String ptelephone, String pmobilePager) {

    String primaryContactId = pcontactId.trim();

    try {
      pstatement = conn.prepareCall(SAVE_SITE_CONTACT_DETAILS);
      pstatement.setString(1, siteLocationIdDao);
      pstatement.setString(2, "P");
      pstatement.setString(3, primaryContactId);
      pstatement.setString(4, pfirstname);
      pstatement.setString(5, plastname);
      pstatement.setString(6, pjobTitle);
      pstatement.setString(7, pemail);
      pstatement.setString(8, ptelephone);
      pstatement.setString(9, pmobilePager);
      pstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
      pstatement.execute();

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

    finally {
      try {
        if (null != pstatement) {
          pstatement.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }
  }

  /**
  * Description:Method to save SecondaryContactDetails.
  * 
  * @param sstatement.
  * @param conn.
  * @param siteLocationIdDao.
  * @param scontactType.
  * @param scontactId.
  * @param sfirstname.
  * @param slastname.
  * @param sjobTitle.
  * @param semail.
  * @param stelephone.
  * @param smobilePager.
  */
  public void saveSecondaryDetails(CallableStatement sstatement, Connection conn, 
      String siteLocationIdDao,String scontactType, String scontactId, 
       String sfirstname, String slastname, String sjobTitle,
      String semail, String stelephone, String smobilePager) {
	  
	  String secodaryContactId = scontactId.trim();
	  
    try {
      sstatement = conn.prepareCall(SAVE_SITE_CONTACT_DETAILS);
      sstatement.setString(1, siteLocationIdDao);
      sstatement.setString(2, scontactType);
      sstatement.setString(3, secodaryContactId);
      sstatement.setString(4, sfirstname);
      sstatement.setString(5, slastname);
      sstatement.setString(6, sjobTitle);
      sstatement.setString(7, semail);
      sstatement.setString(8, stelephone);
      sstatement.setString(9, smobilePager);
      sstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
      sstatement.execute();

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);

    }

    finally {
      try {
        if (null != sstatement) {
          sstatement.close();
        }
      } catch (SQLException e) {
        throw new SystemException(e);
      }
    }

  }

}
