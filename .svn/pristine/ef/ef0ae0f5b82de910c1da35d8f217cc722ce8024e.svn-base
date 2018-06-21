package com.bt.edca.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bt.edca.common.dto.VpnServiceDTO;

import com.bt.edca.da.domain.VpnServiceDomain;
import com.bt.edca.dao.VPNServicesDetailsDao;
import com.bt.edca.service.VPNServicesDetailsService;

import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.HelperMethods;

public class VPNServicesDetailsServiceImpl implements VPNServicesDetailsService {

	/**
	 * logger is to display log messages.
	 */

	private static EdcaLogger logger = EdcaLogger.getLogger(VPNServicesDetailsServiceImpl.class);

	/**
	 * Reference variable.
	 */
	private VPNServicesDetailsDao vpnServicesDetailsDao;


	/**
	 * @return the vpnServicesDetailsDao
	 */
	public VPNServicesDetailsDao getVpnServicesDetailsDao() {
		return vpnServicesDetailsDao;
	}

	/**
	 * @param vpnServicesDetailsDao the vpnServicesDetailsDao to set
	 */
	public void setVpnServicesDetailsDao(VPNServicesDetailsDao vpnServicesDetailsDao) {
		this.vpnServicesDetailsDao = vpnServicesDetailsDao;
	}

	/**
	 * This method invokes the getVPNServicesDetails from vpnServicesDao and
	 * returns the list of VPN Services values.
	 * 
	 * @param vpnID.
	 * @return List.
	 */
	@SuppressWarnings("unchecked")
	public List<VpnServiceDTO> getVPNServicesDetails(String vpnID) {
		List<VpnServiceDomain> vpnList = vpnServicesDetailsDao.getVPNServicesDetails(vpnID);

		List<VpnServiceDTO> servicesDetailsList = new ArrayList<VpnServiceDTO>();
	
		if (!HelperMethods.isNullOrEmpty(vpnList)) {

			for (VpnServiceDomain vpnServiceDomain : vpnList) {

				servicesDetailsList.add(convertVPNServicesDomainToDTO(vpnServiceDomain));

			}
		}

		return servicesDetailsList;
	}

	/**
	 * This method fetches the values from VPNServicesDomain class. sets by
	 * VPNServicesDTO.
	 * 
	 * @param vpnServicesDomain.
	 * @return vpnServicesDTO.
	 */

	public VpnServiceDTO convertVPNServicesDomainToDTO(VpnServiceDomain vpnServiceDomain) {
		VpnServiceDTO vpnServiceDTO = new VpnServiceDTO();
		vpnServiceDTO.setVpnServiceId(vpnServiceDomain.getVpnServiceId());
		vpnServiceDTO.setServiceName(vpnServiceDomain.getServiceName());
		vpnServiceDTO.setBurstOption(vpnServiceDomain.getBurstOption());
		vpnServiceDTO.setSlaPackage(vpnServiceDomain.getSlaPackage());
		vpnServiceDTO.setServiceResilience(vpnServiceDomain.getServiceResilience());
		vpnServiceDTO.setCoreDiversity(vpnServiceDomain.getCoreDiversity());
		vpnServiceDTO.setPreAllocatedCoreDiversity(vpnServiceDomain.getPreAllocatedCoreDiversity());
		vpnServiceDTO.setProviderCounterParty(vpnServiceDomain.getProviderCounterParty());
		vpnServiceDTO.setServiceType(vpnServiceDomain.getServiceType());
		vpnServiceDTO.setVpnId(vpnServiceDomain.getVpnId());
		vpnServiceDTO.setProductName(vpnServiceDomain.getProductName());
		vpnServiceDTO.setCommunity(vpnServiceDomain.getCommunity());

		return vpnServiceDTO;
	}

}
