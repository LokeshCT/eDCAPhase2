package com.bt.edca.service.impl;

import java.util.List;
import com.bt.edca.common.dto.CPEOrderingDTO;
import com.bt.edca.da.domain.CPEOrderingDomain;
import com.bt.edca.dao.CPEOrderingDao;
import com.bt.edca.service.CPEOrderingService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.HelperMethods;

public class CPEOrderingServiceImpl implements CPEOrderingService {

	/**
	 * logger is to display log messages.
	 */
	private static EdcaLogger logger = EdcaLogger
			.getLogger(CPEOrderingServiceImpl.class);

	/**
	 * Reference variable.
	 */
	private CPEOrderingDao cpeOrderingDao;

	/**
	 * @return
	 */
	public CPEOrderingDao getCpeOrderingDao() {
		return cpeOrderingDao;
	}

	/**
	 * @param cpeOrderingDao
	 */
	public void setCpeOrderingDao(CPEOrderingDao cpeOrderingDao) {
		this.cpeOrderingDao = cpeOrderingDao;
	}

	/**
	 * Get Cpe Ordering Details List 
	 * @param orderId
	 * @param siteId
	 */
	public CPEOrderingDTO getCPEOrderingDetails(String orderId, String siteId) {
		CPEOrderingDTO cpeOrderingDTO = new CPEOrderingDTO();
		List<CPEOrderingDomain> cpeOrderingList = cpeOrderingDao.getCPEOrderingDetails(orderId, null);
		if (!HelperMethods.isNullOrEmpty(cpeOrderingList)) {
			for (CPEOrderingDomain cpeOrderingDomain : cpeOrderingList) {

				cpeOrderingDTO.setProgramName(cpeOrderingDomain
						.getProgramName());
				cpeOrderingDTO.setDealId(cpeOrderingDomain.getDealId());
				cpeOrderingDTO.setIcrlOption(cpeOrderingDomain.getIcrlOption());
				cpeOrderingDTO.setIcrlRequiredOverInfra(cpeOrderingDomain
						.getIcrlRequiredOverInfra());
				cpeOrderingDTO.setOverrideCPEInstaller(cpeOrderingDomain
						.getOverrideCPEInstaller());
				cpeOrderingDTO.setPartnerName(cpeOrderingDomain
						.getPartnerName());
				cpeOrderingDTO.setCpeInstaller(cpeOrderingDomain
						.getCpeInstaller());
				cpeOrderingDTO.setCpeMaintainer(cpeOrderingDomain
						.getCpeMaintainer());
				cpeOrderingDTO.setPartnerBidReference(cpeOrderingDomain
						.getPartnerBidReference());
				cpeOrderingDTO.setCallOffAgentName(cpeOrderingDomain
						.getCallOffAgentName());
				cpeOrderingDTO.setTaxId(cpeOrderingDomain.getTaxId());
				cpeOrderingDTO.setCustomerTopology(cpeOrderingDomain
						.getCustomerTopology());
				cpeOrderingDTO.setOrderId(cpeOrderingDomain.getOrderId());
				cpeOrderingDTO.setSiteId(cpeOrderingDomain.getSiteId());
				cpeOrderingDTO.setDartNumber(cpeOrderingDomain.getDartNumber());

			}
		}
		return cpeOrderingDTO;
	}

}
