package com.bt.edca.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import com.bt.edca.da.domain.VpnServiceDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.VPNServicesDetailsDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;

public class VPNServicesDetailsDaoImpl implements VPNServicesDetailsDao {

	/**
	 * Captures the logs onto console.
	 */
	private static EdcaLogger logger = EdcaLogger.getLogger(VPNServicesDetailsDaoImpl.class);

	/**
	 * Format of result.
	 */
	private static Object[] result;

	private static Object serviceNames;

	/**
	 * This methods is used to get the VPNServicesNameList
	 * 
	 * @param vpnID.
	 * 
	 * @return List.
	 */

	public List<VpnServiceDomain> getVPNServicesDetails(String vpnID) {
		String vpnId = vpnID;

		
		List serviceIdList = HibernateQueryUtil.executeQuery(GET_VPN_SERVICEID, new String[] { "vpnID" },
				new Object[] { vpnID });
		List<VpnServiceDomain> finalList = new ArrayList<VpnServiceDomain>();

		Session session = HibernateUtil.getSession();
		SessionImpl sessimpl = (SessionImpl) session;
		Connection conn = sessimpl.connection();
		CallableStatement statement = null;
		List vpnServiceNameList = new ArrayList();

		try {
			statement = conn.prepareCall(SERVICENAME_FUNC);
			if (null != statement) {
				for (int i = 0; i < serviceIdList.size(); i++) {
					statement.registerOutParameter(1, java.sql.Types.VARCHAR);
					statement.setInt(2, Integer.parseInt(serviceIdList.get(i).toString()));
					statement.execute();
				
					String serviceID = (String) statement.getObject(1);
					vpnServiceNameList.add(serviceID);
			
				}
			}
			
		}

		catch (SQLException e) {
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

		finalList = getVPNServiceNames(vpnServiceNameList, vpnID);
		return finalList;
	}

	/**
	 * This methods is used to get the VPNServicesDetails
	 * 
	 * @param serviceName.
	 * @param vpnID.
	 * 
	 * @return List.
	 */
	public List<VpnServiceDomain> getVPNServiceNames(List serviceName, String vpnID) {
		
		List vpnDetailsList = HibernateQueryUtil.executeQuery(GET_VPN_SERVICES_DETAILS, new String[] { "vpnID" },
				new Object[] { vpnID });
		
		List<VpnServiceDomain> serviceList = serviceName;
		List<VpnServiceDomain> vpnServiceNameList = new ArrayList<VpnServiceDomain>();
		List<VpnServiceDomain> vpnDomainList = new ArrayList<VpnServiceDomain>();
		List<VpnServiceDomain> vpnServicesList = new ArrayList<VpnServiceDomain>();

		for (int i = 0; i < vpnDetailsList.size(); i++) {

				result = (Object[]) vpnDetailsList.get(i);
				serviceNames = serviceList.get(i);
				VpnServiceDomain vpnServiceDomain = new VpnServiceDomain();

				vpnServiceDomain.setVpnServiceId(Integer.parseInt(result[0].toString()));				
				vpnServiceDomain.setBurstOption(evaluateString(result[1]));
				vpnServiceDomain.setSlaPackage(evaluateString(result[2]));
				vpnServiceDomain.setServiceResilience(evaluateString(result[3]));
				vpnServiceDomain.setCoreDiversity(evaluateString(result[4]));				
				vpnServiceDomain.setPreAllocatedCoreDiversity(evaluateString(result[5]));	
				vpnServiceDomain.setProviderCounterParty(evaluateString(result[6]));
				vpnServiceDomain.setServiceType(evaluateString(result[7]));		
				vpnServiceDomain.setVpnId(Integer.parseInt(result[8].toString()));				
				vpnServiceDomain.setCommunity(evaluateString(result[9]));	
				vpnServiceDomain.setProductName(checkProductName(result[9],result[10]));
				vpnServiceDomain.setServiceName(serviceNames.toString());		
				vpnServicesList.add(vpnServiceDomain);
			
		}
		
		return vpnServicesList;
	}

	/**
	 * This methods is used to handle the null and set the value to
	 * ConfigureAccessDomain
	 * 
	 * @param value.
	 * 
	 * @return String.
	 */
	public String evaluateString(Object value) {
		String result;
		if (value == null) {

			result = " ";
		} else {
			result = value.toString();
		}
		return result;
	}
	
	/**
	 * This methods is used to set the productName based on 
	 * the community value.
	 * 
	 * @param community.
	 * @param productName.
	 * 
	 * @return String.
	 */
	public String checkProductName(Object community,Object productName){

		String result;
		if(community == null)
		{		
			result= evaluateString(productName);
		}
		else if(community.toString().equalsIgnoreCase("REUTERS")){
			result=" ";
		}
		else {

			result= evaluateString(productName);
		}
		return result;
	}
	
	
}
