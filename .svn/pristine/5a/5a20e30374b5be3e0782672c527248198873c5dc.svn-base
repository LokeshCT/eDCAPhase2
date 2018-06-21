package com.bt.edca.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.sql.SQLException;
import java.util.List;

/*
* WARNING: THIS IS A SINGLETON SPRING BEAN
*          INSTANTIATION OF THE SINGLE (PER PROCESS) INSTANCE OF THIS
*          CLASS IS MANAGED BY SPRING
*          DO NOT INSTANTIATE THIS CLASS DIRECTLY
*          DO NOT ADD ANY INSTANCE VARIABLES TO THIS CLASS
*/
/**
 * @author dhanesh.
 *
 */
public class Repository implements PersistanceBroker {

  /**
   * HibernateTemplate.
   */
  private HibernateTemplate hibernateTemplate;

  /**
   * SessionFactory.
   */
  private SessionFactory sessionFactory;

  /**
   * @param t.
   */
  public <T> void save(T t) {
    getHibernateTemplate().save(t);
  };

  /**
   * saveOrUpdate.
   */
  public <T> void saveOrUpdate(T t) {
    getHibernateTemplate().saveOrUpdate(t);
  };
  
  /**
   * @param entities.
   */
  public <T> void batchInsert(final List<T> entities) {
    HibernateCallback action = new HibernateCallback() {
      /**
       * throws HibernateException, SQLException.
       */
      public Object doInHibernate(Session session) throws HibernateException, SQLException {
        for (T t : entities) {
          session.save(t);
          if (entities.indexOf(t) % 50 == 0) {
            session.flush();
            session.clear();
          }
        }
        return null;
      }
    };

    getHibernateTemplate().execute(action);
  }

  /**
   * @param sessionFactory.
   */
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    this.sessionFactory = sessionFactory;

  }

  // Dhanesh
  /*
   * public <T> List<T> getData(final String query, String[] paramNames,
   * Object[] values) { List<T> entities =
   * getHibernateTemplate().findByNamedQueryAndNamedParam(query, paramNames,
   * values);
   * 
   * if (null == entities){ entities = new ArrayList<T>(); }
   * 
   * return entities; }
   */

  /*
   * public <T> T find(String query, String[] paramNames, Object[] values){
   * List<T> data = this.getData(query, paramNames, values); if
   * (data.isEmpty()){ return null; }
   * 
   * return data.get(0); }
   */

  /**
   * List execution.
   * @param action.
   * @return.
   */
  public <T> List<T> execute(HibernateCallback action) {
    return (List<T>) getHibernateTemplate().execute(action);
  }

  /**
   * Gets the HibernateTemplate.
   * @return hibernateTemplate.
   */
  public HibernateTemplate getHibernateTemplate() {
    return hibernateTemplate;
  }

  /**
   * Gets the SessionFactory.
   * @return sessionFactory.
   */
  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

}
