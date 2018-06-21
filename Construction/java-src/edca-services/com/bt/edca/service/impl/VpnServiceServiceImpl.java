package com.bt.edca.service.impl;

import java.util.List;

import com.bt.edca.common.dto.VpnServiceDTO;
import com.bt.edca.da.domain.VpnServiceDomain;
import com.bt.edca.dao.VpnServiceDao;
import com.bt.edca.service.VpnServiceService;
import com.bt.edca.util.HelperMethods;
/**
 * Description :implementation of interface .
 * @author Rohit
 *
 */
public class VpnServiceServiceImpl implements 
VpnServiceService {
/**
 *Description: object of VpnServiceDao  .
 */
  private VpnServiceDao vpnServiceDao;
  
/**
 * @param vpnServiceId .
 * @return vpnServiceDTO -object of  VpnServiceDTO .
 */
public VpnServiceDTO getVpnService(String vpnServiceId) {   
        
  VpnServiceDTO vpnServiceDTO = new VpnServiceDTO();
        List<VpnServiceDomain> vpnServiceList = 
            vpnServiceDao.getVpnService(vpnServiceId);
        
        if (!HelperMethods.isNullOrEmpty(vpnServiceList)) {
          for (VpnServiceDomain vpnServiceDomain
              : vpnServiceList) {
            vpnServiceDTO.setServiceName(
                vpnServiceDomain.getServiceName());
            vpnServiceDTO.setBandwidth(
                vpnServiceDomain.getBandwidth());
            vpnServiceDTO.setBurstOption(
                vpnServiceDomain.getBurstOption());
            vpnServiceDTO.setSlaPackage(
                vpnServiceDomain.getSlaPackage());
            vpnServiceDTO.setServiceResilience(
                vpnServiceDomain.getServiceResilience());
            vpnServiceDTO.setProductName(
                vpnServiceDomain.getProductName());
            vpnServiceDTO.setCoreDiversity(
                vpnServiceDomain.getCoreDiversity());
        }
        
}
        return vpnServiceDTO;
        
    }
    /**
     *Description :Method for getting the database connection.
     * @return object for VpnServiceDao .
     */
    public VpnServiceDao getVpnServiceDao() {
        return vpnServiceDao;
    }
/**
 * Description: Setter for VpnServiceDao .
 * @param vpnServiceDao .
 */
    public void setVpnServiceDao(VpnServiceDao vpnServiceDao) {
        this.vpnServiceDao = vpnServiceDao;
    }
}
