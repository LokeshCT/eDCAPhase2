package com.bt.edca.service;

import com.bt.edca.common.dto.SitePageDTO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Description : Service layer which is implemented in SitePageServiceImpl.
 *
 * @author SATHYA.
 *
 */
public interface SitePageService {

	/**
	 * Description : Method implemented in ServiceImpl.
	 *
	 * @param orderId.
	 * @return.
	 */
	public SitePageDTO getSitePage(String orderId, String siteId, String siteLocationId);

	/**
	 * Description : Gets DCA ID REFERENCE Using OrderId.
	 * 
	 * @param orderId.
	 * @return.
	 */
	public long getSiteDcaId(String orderId);

	/**
	 * Description : Gets SPECIAL BID REFERENCE Using OrderId.
	 * 
	 * @param orderId.
	 * @return.
	 */
	public List<SitePageDTO> getSitepageSpecialBid(String orderId);

	/**
	 * Description : Gets SitePage Details by Using OrderId.
	 * 
	 * @param orderId.
	 * @return.
	 */
	public List<SitePageDTO> getSiteLocationsDetails(String siteId, String orderId);

	/**
	 * Description : Gets RECILIENCE by Using OrderId.
	 * 
	 * @param orderId.
	 * @return.
	 */
	public List getResilience(String siteId, String orderId, String secure, HttpServletRequest request);

	/**
	 * Description : Gets Price Details.
	 * 
	 * @param orderId.
	 * @return.
	 */
	public List<SitePageDTO> getPrice();

	/**
	 * Description : Gets SecureOverriden Values by Using siteId.
	 * 
	 * @param orderId.
	 * @return.
	 */
	public int getOverriden(String siteId);

	/**
	 * Description : Save the values into DB.
	 * 
	 * @param orderId.
	 * @return.
	 */
	public String saveSitePageDetails(SitePageDTO sitePageDTO, String orderId, String siteId, String specialBidreference);
}
