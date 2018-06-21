package com.bt.edca.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.bt.edca.common.dto.ConfigureLanSwitchDTO;
import com.bt.edca.common.dto.ConfigureNetScreenDTO;
import com.bt.edca.da.domain.ConfigAccTypeQrefDomain;
import com.bt.edca.da.domain.ConfigureLanSwitchDomain;
import com.bt.edca.da.domain.ConfigureNetScreenDomain;
import com.bt.edca.dao.ConfigureLanSwitchNetScreenDao;
import com.bt.edca.service.ConfigureLanSwitchNetScreenService;
import com.bt.edca.util.HelperMethods;

/**
 * The Class ConfigureLanSwitchNetScreenServiceImpl.
 */
public class ConfigureLanSwitchNetScreenServiceImpl implements
		ConfigureLanSwitchNetScreenService {

	/** The configure lan switch net screen dao. */
	private ConfigureLanSwitchNetScreenDao configureLanSwitchNetScreenDao;

	/**
	 * Gets the configure lan switch net screen dao.
	 * 
	 * @return the configure lan switch net screen dao
	 */
	public ConfigureLanSwitchNetScreenDao getConfigureLanSwitchNetScreenDao() {
		return configureLanSwitchNetScreenDao;
	}

	/**
	 * Sets the configure lan switch net screen dao.
	 * 
	 * @param configureLanSwitchNetScreenDao
	 *            the new configure lan switch net screen dao
	 */
	public void setConfigureLanSwitchNetScreenDao(
			ConfigureLanSwitchNetScreenDao configureLanSwitchNetScreenDao) {
		this.configureLanSwitchNetScreenDao = configureLanSwitchNetScreenDao;
	}
	/**
	 * return the configureLanSwicht Map to the controller
	 * 
	 * @param serviceInstanceId
	 *            the new service Instance Id
	 *@param cpeSeqId
	 *            the new cpe Sequence Id           
	 */
	
	public Map<String, Object> getConfigureLanSwitchNetScreenDetails(
			String serviceInstanceId, String cpeSeqId) {
		ConfigureLanSwitchDTO configureLanSwitchDTO = getConfigureLanSwitch(
				serviceInstanceId, cpeSeqId);

		ConfigureNetScreenDTO configureNetScreenDTO = getConfigureNetScreen(
				serviceInstanceId, cpeSeqId);

		List<ConfigureLanSwitchDTO> configureLanSwitchGridList = getConfigureLanGridDetails(serviceInstanceId);

		String lanUsage = getLanUsageDetails(configureLanSwitchGridList);

		Map<String, Object> configureLanSwitchMap = new HashMap<String, Object>();
		configureLanSwitchMap.put(GET_CONFIGURE_LAN_SWITCH, configureLanSwitchDTO);
		configureLanSwitchMap.put(GET_CONFIGURE_NET_SCREEN, configureNetScreenDTO);
		configureLanSwitchMap.put(GET_CONFIGURE_LAN_SWITCH_GRID_LIST,
				configureLanSwitchGridList);
		configureLanSwitchMap.put(GET_LAN_USAGE, lanUsage);

		return configureLanSwitchMap;
	}

	/**
	 * Gets the lan usage details.
	 * 
	 * @param configureLanSwitchGridList
	 *            the configure lan switch grid list
	 * @return the lan usage details
	 */
	private String getLanUsageDetails(
			List<ConfigureLanSwitchDTO> configureLanSwitchGridList) {

		String lanUsage = null;
		if (!HelperMethods.isNullOrEmpty(configureLanSwitchGridList)) {
			for (ConfigureLanSwitchDTO configureLanSwitchDTO : configureLanSwitchGridList) {

				lanUsage = configureLanSwitchDTO.getLanUsage();
			}
		}
		return lanUsage;
	}

	/**
	 * Gets the configure lan switch.
	 * 
	 * @param serviceInstanceId
	 *            the service instance id
	 * @param cpeSeqId
	 *            the cpe seq id
	 * @return the configure lan switch
	 */
	public ConfigureLanSwitchDTO getConfigureLanSwitch(
			String serviceInstanceId, String cpeSeqId) {

		ConfigureLanSwitchDTO configureLanSwitchDTO = new ConfigureLanSwitchDTO();
		List<ConfigureLanSwitchDomain> configureLanSwitchList = getConfigureLanSwitchNetScreenDao()
				.getConfigureLanSwitchDetails(serviceInstanceId, cpeSeqId);

		if (!HelperMethods.isNullOrEmpty(configureLanSwitchList)) {
			for (ConfigureLanSwitchDomain configureLanSwitchDomain : configureLanSwitchList) {

				configureLanSwitchDTO
						.setCpeOrderingType(configureLanSwitchDomain
								.getCpeOrderingType());
				configureLanSwitchDTO.setLanSwitch(configureLanSwitchDomain
						.getLanSwitch());
				configureLanSwitchDTO.setCpeTerm(configureLanSwitchDomain
						.getCpeTerm());
				configureLanSwitchDTO.setModel(configureLanSwitchDomain
						.getModel());
				configureLanSwitchDTO.setChassis(configureLanSwitchDomain
						.getChassis());
				configureLanSwitchDTO.setRps(configureLanSwitchDomain.getRps());
				configureLanSwitchDTO.setPowerCables(configureLanSwitchDomain
						.getPowerCables());
				configureLanSwitchDTO.setPowerCord(configureLanSwitchDomain
						.getPowerCord());
				configureLanSwitchDTO.setFlashMemory(configureLanSwitchDomain
						.getFlashMemory());
				configureLanSwitchDTO.setDram(configureLanSwitchDomain
						.getDram());
				configureLanSwitchDTO.setIos(configureLanSwitchDomain.getIos());
				configureLanSwitchDTO
						.setMaintenanceOption(configureLanSwitchDomain
								.getMaintenanceOption());
				configureLanSwitchDTO
						.setDemarcationExtnReqd(configureLanSwitchDomain
								.getDemarcationExtnReqd());
				configureLanSwitchDTO.setWorkType(configureLanSwitchDomain
						.getWorkType());
				configureLanSwitchDTO
						.setOutOfHoursWorkType(configureLanSwitchDomain
								.getOutOfHoursWorkType());
				configureLanSwitchDTO.setCpeOwner(configureLanSwitchDomain
						.getCpeOwner());
				configureLanSwitchDTO
						.setEquipmentFinancing(configureLanSwitchDomain
								.getEquipmentFinancing());
				configureLanSwitchDTO.setDartNumber(configureLanSwitchDomain
						.getDartNumber());
				configureLanSwitchDTO.setSerialNo(configureLanSwitchDomain
						.getSerialNo());
				configureLanSwitchDTO
						.setSharedPortAdapters(configureLanSwitchDomain
								.getSharedPortAdapters());
				configureLanSwitchDTO.setWicSlots(configureLanSwitchDomain
						.getWicSlots());
				configureLanSwitchDTO
						.setNetworkModules(configureLanSwitchDomain
								.getNetworkModules());
				configureLanSwitchDTO.setPortAdapter(configureLanSwitchDomain
						.getPortAdapter());
				configureLanSwitchDTO.setCarrierSlots(configureLanSwitchDomain
						.getCarrierSlots());
				configureLanSwitchDTO
						.setProcessorSlots(configureLanSwitchDomain
								.getProcessorSlots());
				configureLanSwitchDTO
						.setSwitchingSlots(configureLanSwitchDomain
								.getSwitchingSlots());
				configureLanSwitchDTO
						.setIsNotStdSwitch(configureLanSwitchDomain
								.getIsNotStdSwitch());
				configureLanSwitchDTO.setBaseConfig(configureLanSwitchDomain
						.getBaseConfig());
				configureLanSwitchDTO
						.setExtendedPortfolio(configureLanSwitchDomain
								.getExtendedPortfolio());
				configureLanSwitchDTO
						.setVlanRackShelf(getRackShelfValue(serviceInstanceId));
				configureLanSwitchDTO
						.setOutOfHoursInsReqd(configureLanSwitchDomain
								.getOutOfHoursInsReqd());
				configureLanSwitchDTO.setServiceInstanceId(configureLanSwitchDomain
								.getServiceInstanceId());

			}
		}
		return configureLanSwitchDTO;
	}

	/**
	 * Gets the configure net screen.
	 * 
	 * @param serviceInstanceId
	 *            the service instance id
	 * @param cpeSeqId
	 *            the cpe seq id
	 * @return the configure net screen
	 */
	public ConfigureNetScreenDTO getConfigureNetScreen(
			String serviceInstanceId, String cpeSeqId) {

		ConfigureNetScreenDTO configureNetScreenDTO = new ConfigureNetScreenDTO();
		List<ConfigureNetScreenDomain> configureNetScreenList = getConfigureLanSwitchNetScreenDao()
				.getConfigureNetScreenDetails(serviceInstanceId, cpeSeqId);
		if (!HelperMethods.isNullOrEmpty(configureNetScreenList)) {
			for (ConfigureNetScreenDomain configureNetScreenDomain : configureNetScreenList) {
				configureNetScreenDTO
						.setNetScreenOrderingType(configureNetScreenDomain
								.getNetScreenOrderingType());
				configureNetScreenDTO.setNetScreen(configureNetScreenDomain
						.getNetScreen());
				configureNetScreenDTO.setCpeTerm(configureNetScreenDomain
						.getCpeTerm());
				configureNetScreenDTO.setModel(configureNetScreenDomain
						.getModel());
				configureNetScreenDTO.setChassis(configureNetScreenDomain
						.getChassis());
				configureNetScreenDTO.setFlashMemory(configureNetScreenDomain
						.getFlashMemory());
				configureNetScreenDTO.setDram(configureNetScreenDomain
						.getDram());
				configureNetScreenDTO.setIos(configureNetScreenDomain.getIos());
				configureNetScreenDTO
						.setInstallationType(configureNetScreenDomain
								.getInstallationType());
				configureNetScreenDTO.setHaType(configureNetScreenDomain
						.getHaType());
				configureNetScreenDTO.setUsb(configureNetScreenDomain.getUsb());
				configureNetScreenDTO.setHaLicense(configureNetScreenDomain
						.getHaLicense());
				configureNetScreenDTO.setCpeOwner(configureNetScreenDomain
						.getCpeOwner());
				configureNetScreenDTO
						.setEquipmentFinancing(configureNetScreenDomain
								.getEquipmentFinancing());
				configureNetScreenDTO.setSwitchPort(configureNetScreenDomain
						.getSwitchPort());
				configureNetScreenDTO.setServiceInstanceId(configureNetScreenDomain
						.getServiceInstanceId());
			}
		}
		return configureNetScreenDTO;
	}
	
	
	/**
	 * Gets the configure Grid List.
	 * 
	 * @param serviceInstanceId
	 *            the service instance id
	 * @return configureLanSwitchList
	 */
	
	public List<ConfigureLanSwitchDTO> getConfigureLanGridDetails(
			String serviceInstanceId) {

		List configureLanSwitchList = new ArrayList();
		List<ConfigureLanSwitchDomain> configureLanGridDetailsList = configureLanSwitchNetScreenDao
				.getConfigureLanGridDetails(serviceInstanceId);

		if (!HelperMethods.isNullOrEmpty(configureLanGridDetailsList)) {
			for (ConfigureLanSwitchDomain configureLanSwitchDomain : configureLanGridDetailsList) {
				ConfigureLanSwitchDTO configureLanSwitchDTO = new ConfigureLanSwitchDTO();
				configureLanSwitchDTO.setLanSwitch(configureLanSwitchDomain
						.getLanSwitch());
				configureLanSwitchDTO.setLanUsage(configureLanSwitchDomain
						.getLanUsage());
				configureLanSwitchDTO.setChassis(configureLanSwitchDomain
						.getChassis());
				configureLanSwitchList.add(configureLanSwitchDTO);
			}
		}

		return configureLanSwitchList;

	}

	/**
	 * Gets the rack shelf value.
	 * 
	 * @param serviceInstanceId
	 *            the service instance id
	 * @return the rack shelf value
	 */
	public String getRackShelfValue(String serviceInstanceId) {

		List<ConfigAccTypeQrefDomain> rackShelfList = getConfigureLanSwitchNetScreenDao()
				.getRackShelfValue(serviceInstanceId);
		String rackShelf = null;

		if (!HelperMethods.isNullOrEmpty(rackShelfList)) {
			for (ConfigAccTypeQrefDomain configAccTypeQrefDomain : rackShelfList) {
				rackShelf = configAccTypeQrefDomain.getVlanRackShelf();
			}
		}
		return rackShelf;
	}

}
