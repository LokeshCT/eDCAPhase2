package com.bt.edca.dao;

import java.util.List;
import com.bt.edca.da.domain.ConfigureLanSwitchDomain;


/**
 * The Interface ConfigureLanSwitchNetScreenDao.
 * Author : Joseph
 */

public interface ConfigureLanSwitchNetScreenDao {

	/** The get cpe lan switch net screent. */
	String GET_CPE_LAN_SWITCH = "getCPELanSwitchByServiceInstanceAndCpeSeqId";

	/** The get cpe net screen. */
	String GET_CPE_NET_SCREEN = "getCPENetScreenByServiceInstanceAndCpeSeqId";
	
	
	/** The get out of hours inst req. */
	String GET_OUT_OF_HOURS_INST_REQ = "getConfigureAccessTypeListByServiceInstanceId";		

	
	/** The get rack shelf by service instance id. */
	String GET_RACK_SHELF_BY_SERVICE_INSTANCE_ID ="getQrefValueByServiceInstanceId";

	/** The get service instance id. */
	String GET_ALL_LAN_DETAILS = "{call DD_DCA_PK.GET_ALL_LAN(?,?)}";

	/**
	 * Gets the configure lan switch net screen details.
	 * 
	 * @param serviceInstanceId
	 *            the service instance id
	 * @param cpeSeqId
	 *            the cpe seq id
	 * @return the configure lan switch net screen details
	 */
	List getConfigureLanSwitchDetails(String serviceInstanceId, String cpeSeqId);

	/**
	 * Gets the configure net screen details.
	 *
	 * @param serviceInstanceId the service instance id
	 * @param cpeSeqId the cpe seq id
	 * @return the configure net screen details
	 */
	List getConfigureNetScreenDetails(String serviceInstanceId, String cpeSeqId);

	/**
	 * Gets the configure lan cards.
	 *
	 * @param serviceInstanceId the service instance id
	 * @return the configure lan cards
	 */
	List<ConfigureLanSwitchDomain> getConfigureLanGridDetails(String serviceInstanceId);
	
	/**
	 * Gets the rack shelf value.
	 *
	 * @param serviceInstanceId the service instance id
	 * @return the rack shelf value
	 */
	List getRackShelfValue(String serviceInstanceId);


}
