package com.bt.edca.da.util;

import com.bt.edca.util.EdcaLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author dhanesh.
 *
 */
public class HibernateUtil {

  /**
   * Creation of threadSession.
   */
  private static final ThreadLocal threadSession = new ThreadLocal();

  /**
   * SessionFactory.
   */
  private static SessionFactory sessionFactory;

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(HibernateUtil.class);

  /**
   * Gets the Session.
   * @return session.
   */
  public static Session getSession() {
    Session session = (Session) threadSession.get();

    if ((null == session) || !session.isOpen()) {
      logger.info("Session is null. Creating a new Session");
      session = sessionFactory.openSession();
      logger.info("Session Opened");
      threadSession.set(session);
    }

    return session;
  }

 
  /**
   * Closing the Session object.
   */
  public static void closeSession() {
    Session session = (Session) threadSession.get();

    if (null != session) {
      session.close();
      session = null;
      threadSession.set(null);
    }
  }

  /**
   * @return sessionFactory.
   */
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  /**
   * @param sessionFactory.
   */
  public void setSessionFactory(SessionFactory sessionFactory) {
    logger.info("Inside set session Factory");
    this.sessionFactory = sessionFactory;
    logger.info("After set session Factory");
  }

  /**
   * @param obj save.
   */
  public static void save(Object obj) {
    getSession().save(obj);
    getSession().flush();
  }

  /**
   * @param obj saveOrUpdate.
   */
  public static void saveOrUpdate(Object obj) {
    getSession().saveOrUpdate(obj);
    getSession().flush();
  }

  /**
   * @param obj batchUpdate.
   */
  public static void batchUpdate(Object obj) {
    getSession().saveOrUpdate(obj);
    getSession().flush();
  }

  /**
   * @param obj update.
   */
  public static void update(Object obj) {
    getSession().update(obj);
    getSession().flush();
  }

  /**
   * @param obj delete.
   */
  public static void delete(Object obj) {
    getSession().delete(obj);
    getSession().flush();
  }
}
