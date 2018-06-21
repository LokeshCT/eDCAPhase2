package com.bt.edca.service.impl;

import com.bt.edca.common.dto.SitePageDTO;
import com.bt.edca.da.domain.CPEOrderingDomain;
import com.bt.edca.da.domain.ConfigureAccessDomain;
import com.bt.edca.da.domain.SiteListValuesDomain;
import com.bt.edca.da.domain.SiteLocationDetailsDomain;
import com.bt.edca.da.domain.SitePageDomain;
import com.bt.edca.dao.SitePageDao;
import com.bt.edca.service.SitePageService;
import com.bt.edca.service.constants.EdcaServiceConstants;
import com.bt.edca.util.HelperMethods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Description : Service implementation class which copies data to SitePage in
 * domain package.
 *
 * @author SATHYA.
 *
 */
public class SitePageServiceImpl implements SitePageService {

	/**
	 * Reference variable.
	 */
	private SitePageDao sitePageDao;

	/**
	 * Description : Gets the values from table using orderId.
	 *
	 * @param orderId.
	 * 
	 * @return sitePageDTO.
	 */
	@SuppressWarnings("unchecked")
	public SitePageDTO getSitePage(String orderId, String siteId, String siteLocationId) {

		SitePageDTO sitePageDTO = new SitePageDTO();

		List<SitePageDomain> sitePageList = sitePageDao.getSitePage(orderId);
		List<ConfigureAccessDomain> daoDetails = sitePageDao.getLanswitchDetails(siteId);
		if (daoDetails.size() > 0) {
			for (Object obj : daoDetails.toArray()) {

				if (obj != null) {

					String lanSwitch = obj.toString();
					sitePageDTO.setLanSwitch(lanSwitch);
				}
			}
		}

		if (sitePageList.size() != 0) {
			if (!HelperMethods.isNullOrEmpty(sitePageList)) {
				for (SitePageDomain sitePageDomain : sitePageList) {
					sitePageDTO.setResilience(sitePageDomain.getResilience());
					sitePageDTO.setPricing(sitePageDomain.getPricing());
					sitePageDTO.setCrd(sitePageDomain.getCrd());
					sitePageDTO.setOrderId(sitePageDomain.getOrderId());
					sitePageDTO.setHighestSiteSla(sitePageDomain.getHighestSiteSla());
					sitePageDTO.setStructureCabling(sitePageDomain.getStructureCabling());
					sitePageDTO.setZone(sitePageDomain.getZone());
					sitePageDTO.setAccessOption(sitePageDomain.getAccessOption());
					sitePageDTO.setNativeultraset(sitePageDomain.getNativeultraset());
					sitePageDTO.setOnvDemarcOption(sitePageDomain.getOnvDemarcOption());
					sitePageDTO.setSecureOverride(sitePageDomain.getSecureOverride());
					sitePageDTO.setCoreDiversity(sitePageDomain.getCoreDiversity());
				}
			}
		}
		List<SitePageDomain> sitePage = sitePageDao.getFiberConnectivity(siteId, siteLocationId, orderId);

		if (sitePage.size() != 0) {

			if (!HelperMethods.isNullOrEmpty(sitePage)) {
				for (Object obj : sitePage.toArray()) {
					if (obj != null) {
						String fiberConnectivuty = obj.toString();
						sitePageDTO.setFiberConnectivity(fiberConnectivuty);
					}
				}
			}
		}

		return sitePageDTO;

	}

	/**
	 * Description : Gets DCA ID REFERENCE Using OrderId.
	 *
	 * @param orderId
	 *            .
	 * @return sitePageDcaId.
	 */
	public long getSiteDcaId(String orderId) {

		long sitePageDcaId = sitePageDao.getSiteDcaId(orderId);

		return sitePageDcaId;

	}

	/**
	 * Description : Gets SPECIAL BID REFERENCE Using OrderId.
	 *
	 * @param orderId
	 *            .
	 * @return sitePageList.
	 */
	public List<SitePageDTO> getSitepageSpecialBid(String orderId) {

		List<SitePageDomain> sitePageSpecialBidList = sitePageDao.getSitepageSpecialBid(orderId);

		List<SitePageDTO> sitePageSpecialBidreferencesList = new ArrayList<SitePageDTO>();
		if (sitePageSpecialBidList.size() != 0) {
			if (!HelperMethods.isNullOrEmpty(sitePageSpecialBidList)) {
				for (SitePageDomain domain : sitePageSpecialBidList) {
					SitePageDTO sitePageDTO = new SitePageDTO();
					sitePageDTO.setSpecialBidReference((domain.getSpecialBidReference()));
					sitePageSpecialBidreferencesList.add(sitePageDTO);
				}
			}
		}
		return sitePageSpecialBidreferencesList;
	}

	/**
	 * Description : Gets the value from SitePageDao
	 *
	 * @return sitePageDao.
	 */
	public SitePageDao getSitePageDao() {
		return sitePageDao;
	}

	/**
	 * Description : Sets the value in SitePageDao
	 *
	 * @param sitePageDao
	 *            .
	 */
	public void setSitePageDao(SitePageDao sitePageDao) {
		this.sitePageDao = sitePageDao;
	}

	/**
	 * Description : Gets the values from table using orderId.
	 *
	 * @param orderId
	 *            .
	 * @return sitePageDTO.
	 */
	@SuppressWarnings("unchecked")
	public List<SitePageDTO> getSiteLocationsDetails(String siteId, String orderId) {
		SitePageDTO siteLocationDetailsDTO = new SitePageDTO();

		List<CPEOrderingDomain> cpeOrderingList = sitePageDao.getCPEOrderingDetails(orderId);

		if (cpeOrderingList.size() != 0) {
			if (!HelperMethods.isNullOrEmpty(cpeOrderingList)) {
				for (CPEOrderingDomain cpeOrderingDomain : cpeOrderingList) {

					siteLocationDetailsDTO.setIcrlOverCt(cpeOrderingDomain.getIcrlRequiredOverInfra());
				}
			}
		}
		List<SitePageDTO> siteLocationsDTOList = new ArrayList<SitePageDTO>();
		List<SiteLocationDetailsDomain> siteLocationDetailsList = sitePageDao.countryDetails(siteId);

		if (siteLocationDetailsList.size() != 0) {
			if (!HelperMethods.isNullOrEmpty(siteLocationDetailsList)) {
				for (SiteLocationDetailsDomain siteLocationDetailsDomain : siteLocationDetailsList) {
					siteLocationDetailsDTO.setCountry(siteLocationDetailsDomain.getCountry());

					siteLocationsDTOList.add(siteLocationDetailsDTO);
				}

			}
		}
		return siteLocationsDTOList;

	}

	/**
	 * Description : Gets the values from table by using orderId.
	 *
	 * @param orderId
	 * @param siteId
	 *            .
	 * @return sitePageDTO.
	 */

	@SuppressWarnings("unchecked")
	public List getResilience(String siteId, String orderId, String secure, HttpServletRequest request) {

		List<SiteLocationDetailsDomain> siteLocationDetailsList = sitePageDao.countryDetails(siteId);
		List<SitePageDomain> list = new ArrayList<SitePageDomain>();
		List<SitePageDomain> sitePageList = sitePageDao.getSitePage(orderId);

		if (!HelperMethods.isNullOrEmpty(sitePageList)) {
			if (!HelperMethods.isNullOrEmpty(siteLocationDetailsList)) {
				if (sitePageList.get(0).getHighestSiteSla() != null) {
					String hsa = sitePageList.get(0).getHighestSiteSla();
					if (siteLocationDetailsList.get(0).getCountry() != null) {
						String country = siteLocationDetailsList.get(0).getCountry();
						if (("Silver".equals(hsa)) || ("FCE Resilient Split".equals(hsa))
								|| ("FCE Resilient".equals(hsa)) || ("SILVER SPLIT SITE".equals(hsa))) {

							list = getResilience(hsa, country, "spring", secure);

						}

					}
				}

			}
		}

		return list;

	}

	/**
	 * Description : This method used to get the RecilienceType.
	 *
	 * @param orderId
	 * @param siteId
	 *            .
	 * @return sitePageDTO.
	 */

	public List getResilience(String hsa, String country, String productName, String secure) {
		int override = 0;
		if (secure == null || secure == "0") {
			override = 0;
		} else {
			override = 1;
		}
		productName = "SPRING";
		List list = sitePageDao.getResilienceType(country, productName, hsa, override);

		return list;
	}

	/**
	 * Description : Gets the values from table by using orderId.
	 *
	 * @param orderId
	 * @param siteId
	 *            .
	 * @return sitePageDTO.
	 */

	public int getOverriden(String siteId) {

		List<SiteLocationDetailsDomain> siteLocationDetailsList = sitePageDao.countryDetails(siteId);
		int overriden = 0;
		String productName = "SPRING";
		if (!HelperMethods.isNullOrEmpty(siteLocationDetailsList)) {
			if (siteLocationDetailsList.get(0).getCountry() != null) {
				String country = siteLocationDetailsList.get(0).getCountry();

				overriden = sitePageDao.getOverridenFlag(country, productName);

			}
		}

		return overriden;

	}

	/**
	 * Description : Gets the Price values from table by using orderId.
	 *
	 * @param orderId
	 * @param siteId
	 *            .
	 * @return sitePageDTO.
	 */

	public List<SitePageDTO> getPrice() {
		List<SiteListValuesDomain> price = sitePageDao.getPriceDetails();
		List<SitePageDTO> sitePageDtoList = new ArrayList<SitePageDTO>();

		if (!HelperMethods.isNullOrEmpty(price)) {
			for (SiteListValuesDomain SiteListValuesDomain : price) {

				SitePageDTO sitePageDTO = new SitePageDTO();
				sitePageDTO.setPricing(SiteListValuesDomain.getLabel());
				sitePageDtoList.add(sitePageDTO);
			}
		}

		return sitePageDtoList;

	}

	/**
	 * Description : Save the values into DB.
	 *
	 * @param orderId
	 * @param siteId
	 *            .
	 * @return sitePageDTO.
	 */

	public String saveSitePageDetails(SitePageDTO sitePageDTO, String orderId, String siteId,
			String specialBidreference) {
		String StrCable = sitePageDTO.getStructureCabling();
		String secureOverriden = sitePageDTO.getSecureOverride();

		int override = 0;
		String sCable;
		if (secureOverriden == null) {
			override = 0;
		} else {

			override = 1;
		}

		if (EdcaServiceConstants.EMPTY_STRING.equals(StrCable) || StrCable == null) {
			sCable = EdcaServiceConstants.D;
		} else {
			sCable = StrCable;
		}
		SitePageDomain domain = new SitePageDomain();
		domain.setSpecialBidReference(sitePageDTO.getSpecialBidReference());
		domain.setOrderId(sitePageDTO.getOrderId());
		domain.setCoreDiversity(sitePageDTO.getCoreDiversity());
		domain.setCrd(sitePageDTO.getCrd());
		domain.setFiberConnectivity(sitePageDTO.getFiberConnectivity());
		domain.setOnvDemarcOption(sitePageDTO.getOnvDemarcOption());
		domain.setPricing(sitePageDTO.getPricing());
		domain.setResilience(sitePageDTO.getResilience());
		domain.setStructureCabling(sCable);
		domain.setSiteId(sitePageDTO.getOrderId());
		domain.setSecureOverride(sitePageDTO.getSecureOverride());

		sitePageDao.saveSpecialBidReference(orderId, siteId, specialBidreference);
		String response = sitePageDao.sitePageDetails(domain, override, orderId, siteId);

		return response;
	}

}
