package com.bt.edca.dao.impl;

import com.bt.edca.dao.SitePageDao;

import com.bt.edca.da.constants.EdcaDaConstants;

import com.bt.edca.da.domain.CPEOrderingDomain;
import com.bt.edca.da.domain.ConfigureAccessDomain;
import com.bt.edca.da.domain.SiteListValuesDomain;
import com.bt.edca.da.domain.SiteLocationDetailsDomain;
import com.bt.edca.da.domain.SitePageDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;

import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.HelperMethods;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : Class used to execute Query. Fetching the Site Page Details.
 *
 * @author SATHYA.
 *
 */
public class SitePageDaoImpl implements SitePageDao {

	/**
	 * Format of orderId.
	 */
	private static final String ORDER_ID = "orderId";
	/**
	 * Format of name.
	 */
	private static final String NAME = "name";
	/**
	 * Format of siteLocationId.
	 */
	private static final String SITELOCATION_ID = "siteLocationId";
	/**
	 * Format of orderId.
	 */
	private static final String SITE_ID = "siteId";
	String name = "PRICING";

	/**
	 * logger is to display log messages.
	 */
	private static EdcaLogger logger = EdcaLogger.getLogger(SitePageDaoImpl.class);

	/**
	 * Description : Gets site page using this parameters.
	 *
	 * @param orderId
	 *            .
	 * @return sitePageList.
	 */
	@SuppressWarnings("unchecked")
	public List<SitePageDomain> getSitePage(String orderId) {

		List<SitePageDomain> sitePageList = HibernateQueryUtil.executeQuery(GET_SITE_PAGE_LIST_BY_ORDER,
				new String[] { ORDER_ID }, new Object[] { orderId });
		return sitePageList;

	}

	/**
	 * Description : Gets DCA ID REFERENCE Using OrderId.
	 *
	 * @param orderId
	 *            .
	 * @return siteDcaId.
	 */
	public long getSiteDcaId(String orderId) {

		long siteDcaId = 0;

		List sitePageDcaId = HibernateQueryUtil.executeQuery(GET_DCA_REFERENCE_ID, new String[] { ORDER_ID },
				new Object[] { orderId });

		if (!HelperMethods.isNullOrEmpty(sitePageDcaId) && sitePageDcaId.get(0) != null) {
			siteDcaId = Long.parseLong(sitePageDcaId.get(0).toString());
		}

		return siteDcaId;
	}

	/**
	 * Description : Gets SPECIAL BID REFERENCE Using OrderId.
	 *
	 * @param orderId
	 *            .
	 * @return sitePageDTOList.
	 */
	public List<SitePageDomain> getSitepageSpecialBid(String orderId) {

		int sitePageBid = 0;

		List sitePageSpecialBid = HibernateQueryUtil.executeQuery(GET_SPECIAL_BID_REFERENCE, new String[] { ORDER_ID },
				new Object[] { orderId });

		List<SitePageDomain> sitePageDTOList = new ArrayList<SitePageDomain>();
		if (!HelperMethods.isNullOrEmpty(sitePageSpecialBid)) {
			sitePageBid = Integer.parseInt(sitePageSpecialBid.get(0).toString());

			Session session = HibernateUtil.getSession();
			SessionImpl sessimpl = (SessionImpl) session;
			Connection conn = sessimpl.connection();
			CallableStatement statement = null;
			ResultSet rs = null;

			try {

				statement = conn.prepareCall(GET_SPECIAL_BID_REFERENCE_PROCEDURE);

				if (null != statement) {

					statement.setInt(1, sitePageBid);

					statement.registerOutParameter(2, OracleTypes.CURSOR);

					statement.execute();

					rs = (ResultSet) statement.getObject(2);

					if (null != rs) {

						while (rs.next()) {

							SitePageDomain sitePage = new SitePageDomain();

							sitePage.setSpecialBidReference(rs.getString(1));

							sitePageDTOList.add(sitePage);

						}

					}
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					if (null != statement) {
						statement.close();
						rs.close();
					}
				} catch (SQLException e) {
					throw new SystemException(e);
				}
			}
		}

		return sitePageDTOList;
	}

	/**
	 * Description : Gets CountryDetails By Using OrderId.
	 *
	 * @param orderId.
	 * 
	 * @return sitePageDTOList.
	 */
	@SuppressWarnings("unchecked")
	public List countryDetails(String siteId) {

		List<SiteLocationDetailsDomain> siteLocationDetailsList = HibernateQueryUtil.executeQuery(
				GET_SITE_LOCATION_DETAILS_LIST_BY_SITE_ID, new String[] { "siteId" }, new Object[] { siteId });
		return siteLocationDetailsList;
	}

	/**
	 * Description :saveSpecialBidReference By Using OrderId.
	 *
	 * @param orderId.
	 * @return.
	 */

	public void saveSpecialBidReference(String orderId, String siteId, String specialBidreference) {

		Session session = HibernateUtil.getSession();
		SessionImpl sessimpl = (SessionImpl) session;
		Connection conn = sessimpl.connection();
		CallableStatement statement = null;
		try {
			statement = conn.prepareCall(SAVESPECIALBIDREFERENCE);
			statement.registerOutParameter(1, java.sql.Types.VARCHAR);
			statement.setString(2, orderId);
			statement.setString(3, siteId);
			statement.setString(4, specialBidreference);
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

	/**
	 * Description :getPriceDetails By Using OrderId.
	 *
	 * @param orderId.
	 * @return.
	 */
	@SuppressWarnings("unchecked")
	public List<SiteListValuesDomain> getPriceDetails() {

		List<SiteListValuesDomain> sitePageList = HibernateQueryUtil.executeQuery(GET_PRICINGDETAILS,
				new String[] { NAME }, new Object[] { name });
		return sitePageList;

	}

	/**
	 * Description :saveSpecialBidReference By Using OrderId.
	 *
	 * @param orderId.
	 * @return.
	 */

	public List<SitePageDomain> getResilienceType(String country, String productName, String hsa, int override) {

		List<SitePageDomain> list = new ArrayList<SitePageDomain>();

		ResultSet resultSet = null;
		Session session = HibernateUtil.getSession();
		SessionImpl sessimpl = (SessionImpl) session;
		Connection conn = sessimpl.connection();
		CallableStatement statement = null;
		try {
			statement = conn.prepareCall(GET_Resiliencedetails);

			statement.setString(1, country);
			statement.setString(2, productName);
			statement.setString(3, hsa);
			statement.setInt(4, override);
			statement.registerOutParameter(5, OracleTypes.CURSOR);
			statement.registerOutParameter(6, OracleTypes.VARCHAR);
			statement.registerOutParameter(7, OracleTypes.VARCHAR);
			statement.execute();

			resultSet = (ResultSet) statement.getObject(5);

			if (null != resultSet) {

				while (resultSet.next()) {
					SitePageDomain sitePage = new SitePageDomain();
					sitePage.setResilience(resultSet.getString("RESILIENCE_TYPE"));

					list.add(sitePage);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try {
				if (null != statement) {
					statement.close();
					resultSet.close();
				}
			} catch (SQLException e) {
				throw new SystemException(e);
			}
		}

		return list;

	}

	/**
	 * Description :saveSpecialBidReference By Using OrderId.
	 *
	 * @param orderId
	 *            . @return.
	 */
	public int getOverridenFlag(String country, String productName)

	{
		int response = 0;
		Session session = HibernateUtil.getSession();
		SessionImpl sessimpl = (SessionImpl) session;
		Connection conn = sessimpl.connection();
		CallableStatement statement = null;
		try {
			statement = conn.prepareCall(GET_OverriddenFlag);
			statement.setString(1, country);
			statement.setString(2, productName);
			statement.registerOutParameter(3, OracleTypes.NUMBER);
			statement.registerOutParameter(4, OracleTypes.VARCHAR);
			statement.registerOutParameter(5, OracleTypes.VARCHAR);
			statement.execute();

			response = statement.getInt(3);
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

		return response;

	}

	/**
	 * Description :saveSpecialBidReference By Using OrderId.
	 *
	 * @param orderId
	 *            . @return.
	 */
	public List<CPEOrderingDomain> getCPEOrderingDetails(String orderId) {

		@SuppressWarnings("unchecked")
		List<CPEOrderingDomain> cpeOrderingDetailsList = HibernateQueryUtil.executeQuery(GET_CPEORDERING_BY_ORDERID,
				new String[] { ORDER_ID }, new Object[] { orderId });

		return cpeOrderingDetailsList;
	}

	/**
	 * Description :saveSpecialBidReference By Using OrderId.
	 *
	 * @param orderId
	 *            . @return.
	 */
	@SuppressWarnings("unchecked")
	public List<SitePageDomain> getFiberConnectivity(String siteId, String siteLocationId, String orderId) {

		List<SitePageDomain> fiberConnectivityDetails = (List<SitePageDomain>) HibernateQueryUtil
				.executeQuery(FIBERCONNECTIVITYDETAILS, new String[] { ORDER_ID }, new Object[] { orderId });

		return fiberConnectivityDetails;

	}

	/**
	 * Description :saveSitePageDetails By Using OrderId.
	 *
	 * @param orderId.
	 * @return.
	 */
	public String sitePageDetails(SitePageDomain domain, int override, String orderId, String siteId) {

		String scable = domain.getStructureCabling();
		String coreDiver = domain.getCoreDiversity();
		String fConnectivity = domain.getFiberConnectivity();
		String pricing = domain.getPricing();
		String dmcOption = domain.getOnvDemarcOption();
		String recilience = domain.getResilience();

		String opticalCable;
		if (fConnectivity == null) {
			opticalCable = EdcaDaConstants.NO;
		} else {
			opticalCable = fConnectivity;
		}

		String date=null;
		if (domain.getCrd() == null) {
			date ="0";
		}

		else {

			SimpleDateFormat sdf = new SimpleDateFormat(EdcaDaConstants.DATE_FORMAT);
			date = sdf.format(domain.getCrd());
		}

		Session session = HibernateUtil.getSession();
		SessionImpl sessimpl = (SessionImpl) session;
		Connection conn = sessimpl.connection();
		CallableStatement statement = null;
		String response = null;
		try {

			statement = conn.prepareCall(SAVESITEDETAILS);
			statement.registerOutParameter(1, java.sql.Types.VARCHAR);
			statement.setString(2, siteId);

			statement.setString(3, recilience);

			statement.setString(4, pricing);

			statement.setString(5, date);

			statement.setString(6, orderId);

			statement.setString(7, coreDiver);

			statement.setString(8, opticalCable);

			statement.setString(9, scable);

			statement.setInt(10, override);

			statement.setString(11, dmcOption);

			statement.execute();

			response = statement.getString(1);

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

		return response;
	}

	/**
	 * Description :GetLanswitchDetails By Using siteId.
	 *
	 * @param orderId.
	 * @return.
	 */

	@SuppressWarnings("unchecked")
	public List<ConfigureAccessDomain> getLanswitchDetails(String siteId)

	{

		List<ConfigureAccessDomain> lanSwitchDetails = (List<ConfigureAccessDomain>) HibernateQueryUtil
				.executeQuery(GETLANSWITCHDETAILS, new String[] { SITE_ID }, new Object[] { siteId });
		return lanSwitchDetails;

	}

}
