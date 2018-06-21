package com.bt.edca.dao;


import com.bt.edca.da.domain.CPEOrderingDomain;
import com.bt.edca.da.domain.ConfigureAccessDomain;
import com.bt.edca.da.domain.SiteListValuesDomain;
import com.bt.edca.da.domain.SitePageDomain;


import java.util.List;



/**
 * Description : SitePageDaoImpl Implements SitePageDao interface. Gets the site
 * page in list by OrderId.
 *
 * @author SATHYA.
 */
public interface SitePageDao {

	/**
	 * Format to get site details in list by OrderId.
	 */
	String GET_SITE_PAGE_LIST_BY_ORDER = "getSitePageListByOrderId";

	/**
	 * Format to get SPECIAL BID REFERENCE.
	 */
	String GET_SPECIAL_BID_REFERENCE = "getSpecialBidReference";

	/**
	 * Format to get SPECIAL BID REFERENCE procedure values.
	 */
	String GET_SPECIAL_BID_REFERENCE_PROCEDURE = "{call DD_DCA_SPRINGSPECIALBID.GETACCOUNT(?,?)}";

	/**
	 * Format to get DCA ID REFERENCE.
	 */
	String GET_DCA_REFERENCE_ID = "getDcaReferenceId";

	/**
	 * Description : Gets site page using this parameters.
	 *
	 * @param orderId
	 *            . @return.
	 */
	public List getSitePage(String orderId);

	/**
	 * Description : Gets DCA ID REFERENCE Using OrderId.
	 *
	 * @param orderId
	 *            . @return.
	 */
	public long getSiteDcaId(String orderId);

	/**
	 * Description : Gets SPECIAL BID REFERENCE Using OrderId.
	 *
	 * @param orderId
	 *            . @return.
	 */
	public List<SitePageDomain> getSitepageSpecialBid(String orderId);

	/**
	 * Format to get GET_SITE_LOCATION_DETAILS_LIST.
	 */
	String GET_SITE_LOCATION_DETAILS_LIST_BY_SITE_ID = "getSiteLocationDetailsListBySiteId";

	/**
	 * Description : Gets country details by using this parameters.
	 *
	 * @param siteId
	 *            . @return.
	 */
	public List countryDetails(String siteId);

	/**
	 * Format to save SPECIAL BID REFERENCE procedure values.
	 */
	String SAVESPECIALBIDREFERENCE = "{?=call DD_DCA_SPRINGSPECIALBID." + " SPRINGADDUPDATESPBIDREFERENCE(?,?,?)}";

	/**
	 * Description : save SPECIAL BID REFERENCE using this parameters.
	 *
	 * @param orderId
	 *            . @return.
	 */
	public void saveSpecialBidReference(String orderId, String siteId, String specialBidreference);

	/**
	 * Format to GET_PRICINGDETAILS.
	 */
	String GET_PRICINGDETAILS = "getSitePagePriceDetailsbyname";

	/**
	 * Description : getPriceDetails by using this parameters.
	 *
	 * @param orderId
	 *            . @return.
	 */
	public List<SiteListValuesDomain> getPriceDetails();

	/**
	 * Format to GET_Reciliencedetails procedure values.
	 */
	String GET_Resiliencedetails = "{call pkg_access_spring.pr_get_Resilience_Type(?,?,?,?,?,?,?)}";

	/**
	 * Description : Gets site page using this parameters.
	 *
	 * @param orderId
	 *            . @return.
	 */
	public List getResilienceType(String country, String productName, String hsa, int override);

	/**
	 * Format to GET_OverriddenFlag procedure values.
	 */
	String GET_OverriddenFlag = "{call pkg_access_spring.pr_get_overridden_flag(?,?,?,?,?)}";

	/**
	 * Description :getOverridenFlag using this parameters.
	 *
	 * @param orderId.
	 * @return.
	 */
	public int getOverridenFlag(String country, String productName);

	/**
	 * Format to get DCA ID REFERENCE.
	 */
	String GET_CPEORDERING_BY_ORDERID = "getCPEOrderingDetailsByOrderId";

	/**
	 * Gets cpeordering details using this parameters.
	 *
	 * @param orderId
	 *            - String
	 * @param siteId
	 *            - String
	 * @return
	 */
	public List<CPEOrderingDomain> getCPEOrderingDetails(String orderId);

	/**
	 * Format to get FIBERCONNECTIVITYDETAILS.
	 */
	String FIBERCONNECTIVITYDETAILS = "getFiberConnectivityDetails";

	/**
	 * Description :getFiberConnectivity by using parameters.
	 *
	 * @param orderId
	 *            . @return.
	 */
	public List<SitePageDomain> getFiberConnectivity(String siteId, String SiteLocationId, String orderId);

	/**
	 * Format to SAVESITEDETAILS procedure values.
	 */
	String SAVESITEDETAILS = "{?=call DD_DCA_PK." + " DCA_ADDUPDATE_SITE(?,?,?,?,?,?,?,?,?,?)}";

	/**
	 * Description : GetssitePageDetails this parameters.
	 *
	 * @param orderId.
	 * @return.
	 */
	public String sitePageDetails(SitePageDomain domain, int override, String orderId, String siteId);

	/**
	 * Format to GETLANSWITCHDETAILS.
	 */
	String GETLANSWITCHDETAILS = "getLanswitchDetails";

	/**
	 * Description : getLanswitchDetails using this parameters.
	 *
	 * @param orderId.
	 * @return.
	 */
	public List<ConfigureAccessDomain> getLanswitchDetails(String siteId);

}
