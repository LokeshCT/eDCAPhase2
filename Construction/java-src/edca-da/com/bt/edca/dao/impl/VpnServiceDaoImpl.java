package com.bt.edca.dao.impl;

import java.util.List;
import com.bt.edca.da.util.HibernateQueryUtil;
import com.bt.edca.dao.VpnServiceDao;
/**
 * 
 * @author Rohit
 *Description :Class for getting the vpn service page value from db .
 */
public class VpnServiceDaoImpl 
implements VpnServiceDao {
    /**
     * @param vpnServiceId .
     * @return vpnServiceList .
     */
 
  public List getVpnService(String vpnServiceId) {
    List vpnServiceList = HibernateQueryUtil.executeQuery(
        GET_VPN_SERVICE_LIST_BY_VPN_SERVICE_ID,
                new String[] { "vpnServiceId" }, new Object[] {vpnServiceId});
                return vpnServiceList;
    }
}
