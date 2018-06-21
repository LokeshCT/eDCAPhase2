package com.bt.edca.dao.impl;

import com.bt.edca.da.domain.OrderDetailsDomain;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.da.util.HibernateUtil;
import com.bt.edca.dao.OrderSearchDao;
import com.bt.edca.exception.SystemException;
import com.bt.edca.util.EdcaLogger;

import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is mainly used to establish the session and execute the procedure
 * call based on few Input parameters and return the Output values in a List.
 * 
 * @author Mathivathana
 */
public class OrderSearchDaoImpl implements OrderSearchDao {

	/**
	 * Format of start.
	 */
	public int start = 0;

	/**
	 * Format of end.
	 */

	public int end = 0;

	/**
	 * logger is to display log messages.
	 */
	private static EdcaLogger logger = EdcaLogger.getLogger(OrderSearchDaoImpl.class);

	/**
	 * Gets order details using this parameters.
	 * 
	 * @param pageNum.
	 * @param codeValue.
	 * @param userId.
	 * @param teamId.
	 * @param profileId.
	 * @param productCatagoryName.
	 * 
	 * @return orderDetailsList.
	 */

	public List<OrderDetailsDomain> getOrderDetails(String pageNum, String codeValue, String userId, String teamId,
			String profileId, String productCatagoryName) {

		Session session = HibernateUtil.getSession();
		SessionImpl sessimpl = (SessionImpl) session;
		Connection conn = sessimpl.connection();
		CallableStatement statement = null;
		ResultSet rs = null;
		int pgNum = Integer.parseInt(pageNum);
		setPageSize(pgNum);
		start = getStartPage(start);
		end = getEndPage(end);
		List<OrderDetailsDomain> orderDetailsDTOList = new ArrayList<OrderDetailsDomain>();
		List accesslist = HibernateQueryUtil.executeQuery(GET_TEAM_CATEGORY_ID, new String[] { "teamId" },
				new Object[] { teamId });
		String teamCategoryId = accesslist.get(0).toString();
		logger.info("teamCategoryId is: " + teamCategoryId);
		try {

			if (codeValue.equals("AAFD") || codeValue.equals("AAFP")) {
				logger.info("Inside AAF Orders");

				statement = conn.prepareCall(AAF_ORDER_PROCEDURE);
				setAAFProcedure(statement, codeValue, userId, teamId, profileId, productCatagoryName, teamCategoryId);
				statement.execute();
				rs = (ResultSet) statement.getObject(10);
				orderDetailsDTOList = setResult(rs, codeValue);
			}

			else if (codeValue.equalsIgnoreCase("Inventory")) {
				logger.info("Inside Inventory Orders");

				statement = conn.prepareCall(INVY_PROCEDURE);
				setInvyProcedure(statement, userId, teamId);
				statement.execute();
				rs = (ResultSet) statement.getObject(6);
				orderDetailsDTOList = setResult(rs, codeValue);
			}

			else {
				logger.info("Inside General Orders");

				statement = conn.prepareCall(SEARCH_PROCEDURE);
				setSearchProcedure(statement, codeValue, userId, teamId, profileId, productCatagoryName,
						teamCategoryId);
				statement.execute();
				rs = (ResultSet) statement.getObject(10);
				orderDetailsDTOList = setResult(rs, codeValue);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (null != statement) {
					rs.close();
					statement.close();
				}
			} catch (SQLException e) {
				throw new SystemException(e);
			}
		}

		return orderDetailsDTOList;
	}

	/**
	 * Set the page size using this parameters.
	 * 
	 * @param pageNum.
	 */
	private void setPageSize(int pageNumber) {

		if (pageNumber == 1) {
			start = pageNumber;
			end = 30;
		} else {
			end = (pageNumber * 30);
			start = end - 29;
		}

	}

	/**
	 * Gets the start of the page.
	 * 
	 * @return start.
	 */

	private int getStartPage(int start) {
		return this.start = start;
	}

	/**
	 * Gets the end of the page.
	 * 
	 * @return end.
	 */
	private int getEndPage(int end) {
		return this.end = end;
	}

	/**
	 * Set the parameter value for the stored procedure using this parameters.
	 * 
	 * @param statement.
	 * @param pageNum.
	 * @param codeValue.
	 * @param userId.
	 * @param teamId.
	 * @param profileId.
	 * @param productCatagoryName.
	 * @param teamCategoryId.
	 */
	private void setSearchProcedure(CallableStatement statement, String codeValue, String userId, String teamId,
			String profileId, String productCatagoryName, String teamCategoryId) {

		logger.info("Start Page is: " + start);
		logger.info("End Page is : " + end);

		try {

			statement.setString(1, userId);

			statement.setString(2, teamId);

			statement.setString(3, productCatagoryName);

			statement.setString(4, codeValue);

			statement.setString(5, teamCategoryId);

			statement.setString(6, "N");

			statement.setString(7, profileId);

			statement.setInt(8, start);

			statement.setInt(9, end);

			statement.registerOutParameter(10, OracleTypes.CURSOR);

			statement.registerOutParameter(11, java.sql.Types.VARCHAR);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Sets resultset values.
	 * 
	 * @return orderResultList.
	 */
	private List setResult(ResultSet rs, String codeValue) {

		List orderResultList = new ArrayList();

		if (null != rs) {

			try {

				while (rs.next()) {

					OrderDetailsDomain orderDetailsDomain = new OrderDetailsDomain();

					if (codeValue.equalsIgnoreCase("Inventory")) {

						orderDetailsDomain.setOrderId(rs.getString("OID"));
						orderDetailsDomain.setClientName(rs.getString("CLIENT_NAME"));
						orderDetailsDomain.setReceivedDate(rs.getString("ORDER_RECEIVED_DATE"));

					} else {

						orderDetailsDomain.setOrderId(rs.getString("ORDER_ID"));
						orderDetailsDomain.setClientName(rs.getString("CLIENT_NAME"));
						orderDetailsDomain.setReceivedDate(rs.getString("RECEIVED_DATE"));
					}

					orderDetailsDomain.setOrderReference(rs.getString("ORDER_REFERENCE"));
					orderDetailsDomain.setClientAccountId(rs.getString("CLIENT_ACCOUNT_ID"));
					orderDetailsDomain.setCnt(rs.getString("CNT"));
					orderResultList.add(orderDetailsDomain);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return orderResultList;
	}

	/**
	 * Set the parameter value for the stored procedure of AAF Pending and AAF
	 * Delegated orders using this parameters.
	 * 
	 * @param statement.
	 * 
	 * @param codeValue.
	 * @param userId.
	 * @param teamId.
	 * @param profileId.
	 * @param productCatagoryName.
	 * @param teamCategoryId.
	 */
	private void setAAFProcedure(CallableStatement statement, String codeValue, String userId, String teamId,
			String profileId, String productCatagoryName, String teamCategoryId) {

		try {

			statement.setString(1, userId);

			statement.setString(2, teamId);

			statement.setString(3, productCatagoryName);

			statement.setString(4, codeValue);

			statement.setString(5, teamCategoryId);

			statement.setString(6, "N");

			statement.setString(7, profileId);

			statement.setInt(8, start);

			statement.setInt(9, end);

			statement.registerOutParameter(10, OracleTypes.CURSOR);

			statement.registerOutParameter(11, java.sql.Types.VARCHAR);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Set the parameter value for the stored procedure of Inventory orders
	 * using this parameters.
	 * 
	 * @param statement.
	 * @param userId.
	 * @param teamId.
	 */
	private void setInvyProcedure(CallableStatement statement, String userId, String teamId) {
		try {

			statement.setString(1, userId);

			statement.setString(2, teamId);

			statement.setString(3, "N");

			statement.setInt(4, start);

			statement.setInt(5, end);

			statement.registerOutParameter(6, OracleTypes.CURSOR);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
