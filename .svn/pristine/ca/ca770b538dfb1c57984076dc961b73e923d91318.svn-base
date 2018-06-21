package com.bt.edca.dao.impl;

import java.util.List;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.dao.VpnConnectionDao;
/**
 * 
 * @author Rohit
 *Description : class for getting the vpn connection page value. 
 */
public class VpnConnectionDaoImpl 
implements VpnConnectionDao {
    /**
     * @param vpnId .
     * @return vpnConnectionList .
     */
 
  public List getVpnConnection(String vpnId) {
    List vpnConnectionList = HibernateQueryUtil.executeQuery(
        GET_VPN_CONNECTION_LIST_BY_VPN_ID,
                new String[] { "vpnId" }, new Object[] {vpnId});
                return vpnConnectionList;
    }
}
