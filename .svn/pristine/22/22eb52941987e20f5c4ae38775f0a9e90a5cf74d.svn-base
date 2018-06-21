package com.bt.edca.repository;

import org.springframework.orm.hibernate3.HibernateCallback;

import java.util.List;


/**
 * @author dhanesh.
 *
 */
public interface PersistanceBroker {

  /**
   * batchInsert
   * @param entities.
   */
  public <T> void batchInsert(List<T> entities);

  /**
   * saveOrUpdate.
   * @param t.
   */
  public <T> void saveOrUpdate(T t);

  // public <T> T find(String query, String[] paramNames, Object[] values);
  // public <T> List<T> getData(String query, String[] paramNames, Object[]
  // values);
  /**
   * List execution.
   * @param action.
   * @return.
   */
  public <T> List<T> execute(HibernateCallback action);

}
