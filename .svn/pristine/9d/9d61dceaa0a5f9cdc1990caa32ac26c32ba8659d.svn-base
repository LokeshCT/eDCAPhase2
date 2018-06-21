
package com.bt.edca.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import com.bt.edca.da.domain.ConfigureLanSwitchDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.ConfigureLanSwitchNetScreenDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;


/**
 * The Class ConfigureLanSwitchNetScreenDaoImpl. 
 * Author : Joseph Peter 
 */
public class ConfigureLanSwitchNetScreenDaoImpl implements
		ConfigureLanSwitchNetScreenDao {

	/** The Constant SERVICE_INSTANCE_ID. */
	private static final String SERVICE_INSTANCE_ID = "serviceInstanceId";

	/** The Constant CPE_SEQ_ID. */
	private static final String CPE_SEQ_ID = "cpeSeqId";

	/** The logger. */
	private static EdcaLogger logger = EdcaLogger
			.getLogger(ConfigureLanSwitchNetScreenDaoImpl.class);

	/**
	 *  
	 * Return the list of  Configure Lan Switch Details.
	 *
	 * @param serviceInstanceId the service instance id
	 * @param cpeSeqId the cpe seq id
	 * @return the configure lan switch details
	 */
	public List getConfigureLanSwitchDetails(String serviceInstanceId,
			String cpeSeqId) {

		List configureLanSwitchList = HibernateQueryUtil.executeQuery(
				GET_CPE_LAN_SWITCH, new String[] {
						SERVICE_INSTANCE_ID, CPE_SEQ_ID }, new Object[] {
						serviceInstanceId, cpeSeqId });
		return configureLanSwitchList;

	}
	
	
	/**
	 *  
	 * Return the list of  Configure Net Switch Details.
	 *
	 * @param serviceInstanceId the service instance id
	 * @param cpeSeqId the cpe seq id
	 * @return the configure Net screen details
	 */
	public List getConfigureNetScreenDetails(
			String serviceInstanceId,String cpeSeqId){
		List configureNetScreenList = HibernateQueryUtil.executeQuery(
				GET_CPE_NET_SCREEN, new String[] {
						SERVICE_INSTANCE_ID, CPE_SEQ_ID }, new Object[] {
						serviceInstanceId, cpeSeqId });
		return configureNetScreenList;
		
		
	}
	
	 /**
	 * Gets the configure lan Grid cards.
	 *
	 * @param serviceInstanceId the service instance id
	 * @return the configure lan Grid cards
	 */
	public List getConfigureLanGridDetails(String serviceInstanceId) {

		Session session = HibernateUtil.getSession();
		SessionImpl sessimpl = (SessionImpl) session;
		Connection conn = sessimpl.connection();
		CallableStatement statement = null;

		ResultSet rs = null;
		List<ConfigureLanSwitchDomain> configureLanGridList = new ArrayList<ConfigureLanSwitchDomain>();

		try {
			statement = conn.prepareCall(GET_ALL_LAN_DETAILS);

			statement.setString(1, serviceInstanceId);
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			rs = (ResultSet) statement.getObject(2);

			if (null != rs) {
				while (rs.next()) {

					ConfigureLanSwitchDomain configureLanSwitchDomain = new ConfigureLanSwitchDomain();

					configureLanSwitchDomain.setServiceInstanceId(rs.getLong(1));
					configureLanSwitchDomain.setCpeSeqId(rs.getInt(2));
					configureLanSwitchDomain.setLanUsage(rs.getString(3));
					configureLanSwitchDomain.setLanSwitch(rs.getString(4));
					configureLanSwitchDomain.setChassis(rs.getString(5));
					configureLanGridList.add(configureLanSwitchDomain);
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
				}				
			} catch (SQLException e) {
				throw new SystemException(e);
			}
		}
		return configureLanGridList;

	}
	
	/** 
	  * @param serviceInstanceId .
	  * @return qrefList .
	  * Description : Method for getting the qref value .
	  */
	  public  List getRackShelfValue(String serviceInstanceId) {	    
	    List rackShelfList = HibernateQueryUtil.executeQuery(
	    		GET_RACK_SHELF_BY_SERVICE_INSTANCE_ID, new String[] {
	            "serviceInstanceId" },
	        new Object[] { serviceInstanceId });
	          return rackShelfList;
	  }	
	  
	 
}
