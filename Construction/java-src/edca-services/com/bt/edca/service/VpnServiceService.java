package com.bt.edca.service;

import com.bt.edca.common.dto.VpnServiceDTO;
/**
 * Description: Interface .
 * @author Rohit
 *
 */
public interface VpnServiceService {
    /**
     * @param vpnServiceId .
     * @return vpnServiceDTO .
     */
    public VpnServiceDTO getVpnService(String vpnServiceId);

}
