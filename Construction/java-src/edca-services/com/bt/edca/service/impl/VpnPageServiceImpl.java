package com.bt.edca.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.bt.edca.common.dto.VpnPageDTO;
import com.bt.edca.da.domain.VpnPageDomain;
import com.bt.edca.dao.VpnPageDao;
import com.bt.edca.service.VpnPageService;
import com.bt.edca.util.HelperMethods;

/**
 * The Class VpnPageServiceImpl.
 */
public class VpnPageServiceImpl implements VpnPageService {

	/** The vpn page dao. */
	private VpnPageDao vpnPageDao;

	/**
	 * Gets the vpn page dao.
	 * 
	 * @return the vpn page dao
	 */
	public VpnPageDao getVpnPageDao() {
		return vpnPageDao;
	}

	/**
	 * Sets the vpn page dao.
	 * 
	 * @param vpnPageDao
	 *            the new vpn page dao
	 */
	public void setVpnPageDao(VpnPageDao vpnPageDao) {
		this.vpnPageDao = vpnPageDao;
	}

	/**
	 * Gets the vpn page details and assign it to a DTO return list of
	 * VpnPageDTO
	 */
	public List<VpnPageDTO> getVpnDetails(String serviceInstanceId) {

		List<VpnPageDomain> vpnPageList = vpnPageDao
				.getVpnPageDetails(serviceInstanceId);

		List<VpnPageDTO> vpnPageDetails = new ArrayList<VpnPageDTO>();

		if (!HelperMethods.isNullOrEmpty(vpnPageList)) {
			for (VpnPageDomain vpnPageDomain : vpnPageList) {

				VpnPageDTO vpnPageDTO = new VpnPageDTO();
				vpnPageDTO.setVpnId(vpnPageDomain.getVpnId());
				vpnPageDTO.setConnectionName(vpnPageDomain.getConnectionName());
				vpnPageDTO.setConnectionType(vpnPageDomain.getConnectionType());
				vpnPageDTO.setResilience(vpnPageDomain.getResilience());
				vpnPageDTO.setConnectionBandWidth(vpnPageDomain
						.getConnectionBandWidth());
				vpnPageDTO.setMcast_flag(vpnPageDomain.getMcast_flag());
				vpnPageDetails.add(vpnPageDTO);
			}
		}

		return vpnPageDetails;
	}

}
