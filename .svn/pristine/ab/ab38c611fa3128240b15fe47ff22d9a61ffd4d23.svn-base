package com.bt.edca.da.util;

import org.hibernate.Query;

import java.util.Collection;
import java.util.List;

/**
 * @author dhanesh.
 *
 */
public class HibernateQueryUtil {

  /**
   * @param hibQuery.
   * @param param.
   * @param values.
   * @param isCacheable.
   * @return.
   */
  public static List executeQuery(String hibQuery, String[] param,
      Object[] values, boolean isCacheable) {
    Query query = HibernateUtil.getSession().getNamedQuery(hibQuery);
    processQuery(query, isCacheable);

    if ((null != param) && (null != values)) {
      for (int i = 0; i < values.length; i++) {
        applyNamedParameterToQuery(query, param[i], values[i]);
      }
    }

    return query.list();
  }

  /**
   * @param hibQuery.
   * @param param.
   * @param values.
   * @param isCacheable.
   * @param isReadOnly.
   * @return.
   */
  public static List executeQuery(String hibQuery, String[] param, 
      Object[] values, boolean isCacheable,
      boolean isReadOnly) {
    Query query = HibernateUtil.getSession().getNamedQuery(hibQuery);
    processQuery(query, isCacheable);

    if ((null != param) && (null != values)) {
      for (int i = 0; i < values.length; i++) {
        applyNamedParameterToQuery(query, param[i], values[i]);
      }
    }
    query.setReadOnly(true);
    return query.list();
  }

  /**
   * @param hibQuery.
   * @param param.
   * @param values.
   * @return.
   */
  public static List executeQuery(String hibQuery, String[] param, Object[] values) {
    return executeQuery(hibQuery, param, values, false);
  }

  /**
   * @param hibQuery.
   * @return.
   */
  public static List executeQuery(String hibQuery) {
    return executeQuery(hibQuery, null, null, false);
  }

  /**
   * @param hibQuery.
   * @param isCacheable.
   * @return.
   */
  public static List executeQuery(String hibQuery, boolean isCacheable) {
    return executeQuery(hibQuery, null, null, isCacheable);
  }

  private static void applyNamedParameterToQuery(Query queryObject, 
      String paramName, Object value) {
    if (value instanceof Collection) {
      queryObject.setParameterList(paramName, (Collection) value);
    } else {
      if (value instanceof Object[]) {
        queryObject.setParameterList(paramName, (Object[]) value);
      } else {
        queryObject.setParameter(paramName, value);
      }
    }
  }

  private static void processQuery(Query queryObject, boolean isCacheable) {
    if (isCacheable) {
      queryObject.setCacheable(isCacheable);
    }
  }
}
