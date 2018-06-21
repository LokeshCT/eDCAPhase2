package com.bt.edca.service.impl;

import com.bt.edca.common.dto.DSCPCosDTO;
import com.bt.edca.da.domain.DSCPCosDomain;
import com.bt.edca.dao.DSCPCosDao;
import com.bt.edca.service.DSCPCosService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.HelperMethods;

import java.util.List;

/**
 * class implemenmts interface DSCPCosService
 * 
 * @author Jagankumar G.
 *
 */
public class DSCPCosServiceImpl implements DSCPCosService {

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(DSCPCosServiceImpl.class);

  /**
   * Reference variable.
   */
  private DSCPCosDao dscpCosDao;

  @Override
  public DSCPCosDTO getDSCPdetails(String orderId) {
    DSCPCosDTO dscpCosDTO = new DSCPCosDTO();

    List<DSCPCosDomain> domainLists = dscpCosDao.getDSCPCosDetails(orderId);
    int vpnId = 0;
    DSCPCosDomain tirDomain = dscpCosDao.getTirBandwidth(123);
    DSCPCosDomain dscpCosDomain = dscpCosDao.getProcDscpCosDetails(vpnId);

    dscpCosDTO.setConnectionBandWidth(tirDomain.getConnectionBandWidth());
    dscpCosDTO.setVpnId(dscpCosDomain.getVpnId());
    dscpCosDTO.setCiprEf(dscpCosDomain.getCiprEf());
    dscpCosDTO.setCiprAf1(dscpCosDomain.getCiprAf1());
    dscpCosDTO.setCiprAf2(dscpCosDomain.getCiprAf2());
    dscpCosDTO.setCiprAf3(dscpCosDomain.getCiprAf3());
    dscpCosDTO.setCiprAf4(dscpCosDomain.getCiprAf4());
    dscpCosDTO.setMiprAf1(dscpCosDomain.getMiprAf1());
    dscpCosDTO.setMiprAf2(dscpCosDomain.getMiprAf2());
    dscpCosDTO.setMiprAf3(dscpCosDomain.getMiprAf3());
    dscpCosDTO.setMiprAf4(dscpCosDomain.getMiprAf4());
    dscpCosDTO.setMiprDe(dscpCosDomain.getMiprDe());
    dscpCosDTO.setMiprEf(dscpCosDomain.getMiprEf());
    dscpCosDTO.setCipr(dscpCosDomain.getCipr());
    dscpCosDTO.setCiprMgt(dscpCosDomain.getCiprMgt());
    dscpCosDTO.setCosBleaching(dscpCosDomain.getCosBleaching());
    dscpCosDTO.setCosVoip(dscpCosDomain.getCosVoip());

    if (!HelperMethods.isNullOrEmpty(domainLists)) {
      for (DSCPCosDomain domainList : domainLists) {

        dscpCosDTO.setOrderId(domainList.getOrderId());
        dscpCosDTO.setProductName(domainList.getProductName());
        dscpCosDTO.setLblCosModel(domainList.getLblCosModel());
        dscpCosDTO.setCosPolicy(domainList.getCosPolicy());
        dscpCosDTO.setLblCustomization(domainList.getLblCustomization());
      }
    }

    return dscpCosDTO;

  }

  public DSCPCosDao getDscpCosDao() {
    return dscpCosDao;
  }

  public void setDscpCosDao(DSCPCosDao dscpCosDao) {
    this.dscpCosDao = dscpCosDao;
  }

}
