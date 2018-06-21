package com.bt.edca.dao;

import com.bt.edca.da.domain.OrderDetailsDomain;

import java.util.List;

/**
 * This interface calls the procedure with appropriate IN and OUT parameters.
 * Gets the order details in List.
 * 
 * @author Mathivathana
 */
public interface OrderSearchDao {

	/**
	 * Format to get the order details in list.
	 */
	String SEARCH_PROCEDURE = "{call DDDCA_PK_USER_PROFILE.GETORDERS(?,?,?,?,?,?,?,?,?,?,?)}";

	String STATUS_PROCEDURE = "{call DDDCA_PK_USER_PRO.GetUserMenuList(?,?,?,?)}";

	String AAF_ORDER_PROCEDURE = "{call DDDCA_PK_USER_PROFILE.GETAAFORDERS(?,?,?,?,?,?,?,?,?,?,?)}";

	String INVY_PROCEDURE = "{call PK_MOD_GENERAL.GetInventoryOrders(?,?,?,?,?,?)}";

	String GET_TEAM_CATEGORY_ID = "getTeamCategoryId";

	/**
	 * Gets order details using this parameters.
	 * 
	 * @param pageNum.
	 * @param codeValue.
	 * @param userId.
	 * @param teamId.
	 * @param profileId.
	 * @param productCatagoryName.
	 * @return.
	 */
	List<OrderDetailsDomain> getOrderDetails(String pageNum, String codeValue, String userId, String teamId,
			String profileId, String productCatagoryName);

}
