package com.bt.edca.dao;

import java.util.List;
/**
 * Description : Interface .
 * @author Rohit .
 *
 */

public interface VpnConnectionDao {
   /**
    *Description : Constant declaration .
    */
    static String GET_VPN_CONNECTION_LIST_BY_VPN_ID = 
        "getVpnConnectionListByVpnId";
    /**
     * 
     * @param vpnId .
     * @return .
     */
    public List getVpnConnection(String vpnId);
}
