package com.bt.edca.service.impl;

import java.util.List;

import com.bt.edca.common.dto.VpnConnectionDTO;
import com.bt.edca.da.domain.VpnConnectionDomain;
import com.bt.edca.dao.VpnConnectionDao;
import com.bt.edca.service.VpnConnectionService;
import com.bt.edca.util.HelperMethods;
/**
 * Description :implementation of interface .
 * @author Rohit
 *
 */
public class VpnConnectionServiceImpl implements 
VpnConnectionService {
/**
 *Description: object of VpnConnectionDao  .
 */
  private VpnConnectionDao vpnConnectionDao;
  
/**
 * @param vpnId .
 * @return vpnConnectionDTO -object of  VpnServiceDTO .
 */
public VpnConnectionDTO getVpnConnection(String vpnId) {   
        
  VpnConnectionDTO vpnConnectionDTO = new VpnConnectionDTO();
        List<VpnConnectionDomain> vpnConnectionList = 
            vpnConnectionDao.getVpnConnection(vpnId);
        
        if (!HelperMethods.isNullOrEmpty(vpnConnectionList)) {
          for (VpnConnectionDomain vpnConnectionDomain
              : vpnConnectionList) {
            vpnConnectionDTO.setConnName(
                vpnConnectionDomain.getConnName());
            vpnConnectionDTO.setConnType(
                vpnConnectionDomain.getConnType());
            vpnConnectionDTO.setAccessCkt(
                vpnConnectionDomain.getAccessCkt());
            vpnConnectionDTO.setConnBandwidth(
                vpnConnectionDomain.getConnBandwidth());
            vpnConnectionDTO.setVpnFriendlyName(
                vpnConnectionDomain.getVpnFriendlyName());
            vpnConnectionDTO.setPrimaryConn(
                vpnConnectionDomain.getPrimaryConn());
            vpnConnectionDTO.setSecureExt(
                vpnConnectionDomain.getSecureExt());
            vpnConnectionDTO.setOriginatorCir(
                vpnConnectionDomain.getOriginatorCir());
            vpnConnectionDTO.setConnecType(
                vpnConnectionDomain.getConnecType());
            vpnConnectionDTO.setMultiServ(
                vpnConnectionDomain.getMultiServ());
        }
        
}
        return vpnConnectionDTO;
        
    }
    /**
     *Description :Method for getting the database connection.
     * @return object for VpnConnectionDao .
     */
    public VpnConnectionDao getVpnConnectionDao() {
        return vpnConnectionDao;
    }
/**
 * Description: Setter for VpnConnectionDao .
 * @param vpnConnectionDao .
 */
    public void setVpnConnectionDao(VpnConnectionDao vpnConnectionDao) {
        this.vpnConnectionDao = vpnConnectionDao;
    }
}
