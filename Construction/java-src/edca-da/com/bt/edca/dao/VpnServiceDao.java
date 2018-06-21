package com.bt.edca.dao;

import java.util.List;
/**
 * Description : Interface .
 * @author Rohit .
 *
 */

public interface VpnServiceDao {
   /**
    *Description : Constant declaration .
    */
    static String GET_VPN_SERVICE_LIST_BY_VPN_SERVICE_ID = 
        "getVpnServiceListByVpnServiceId";
    /**
     * 
     * @param vpnServiceId .
     * @return .
     */
    public List getVpnService(String vpnServiceId);
}
