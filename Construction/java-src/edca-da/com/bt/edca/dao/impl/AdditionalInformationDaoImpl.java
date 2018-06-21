package com.bt.edca.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.AdditionalInformationDao;
import com.bt.edca.exception.SystemException;
/**
 * 
 * @author Rohit
 *
 */
public class AdditionalInformationDaoImpl implements AdditionalInformationDao {
	/**
	 * @param orderId .
	 * @return additionalInformationList .
	 */
 
  public List getAdditonalInformation(String orderId) {
	List additionalInformationList = HibernateQueryUtil.executeQuery(
	    GET_ADDITIONAL_INFORMATION_LIST_BY_ORDER,
                new String[] { "orderId" }, new Object[] {orderId});
		        return additionalInformationList;
	}
/**
 * Description : method for saving the data in db .
 * @param notes1 .
 * @param notes2 .
 * @param cotNotes1 .
 * @param coNotes2 .
 * @param productNotes1 .
 * @param productNotes2 .
 * @param reason1 .
 * @param reason2 .
 * @param orderId .
 * @param override .
 */
  public  void saveAdditionalInformation(String notes1, String notes2,
      String cotNotes1, 
      String coNotes2, String productNotes1,
      String productNotes2, String reason1,
      String reason2, String orderId, String override) {
Session session = HibernateUtil.getSession();
SessionImpl sessimpl = (SessionImpl) session;
Connection conn = sessimpl.connection();
CallableStatement statement = null;
try {
  statement = conn.prepareCall(SAVE_ADDITIONAL_INFORMATION);
  statement.registerOutParameter(1, java.sql.Types.VARCHAR);
  statement.setString(2, orderId);
  statement.setString(3, cotNotes1);
  statement.setString(4, coNotes2);
  statement.setString(5, notes1);
  statement.setString(6, notes2);
  statement.setString(7, productNotes1);
  statement.setString(8, productNotes2);
  statement.setString(9, override);
  statement.setString(10, reason1);
  statement.setString(11, reason2);
  statement.execute();
} catch (SQLException e) {
  } finally {
    try {
        if (null != statement) {
      statement.close();
    }
  } catch (SQLException e) {
    throw new SystemException(e);
  }
} 
  }
}

