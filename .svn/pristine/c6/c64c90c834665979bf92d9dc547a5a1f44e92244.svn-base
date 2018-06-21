package com.bt.edca.dao;

import com.bt.edca.da.domain.DSCPCosDomain;

import java.util.List;

/**
 * interface to fetch  dscoCos values.
 * @author Jagankumar G.
 *
 */
public interface DSCPCosDao {

  String GET_DSCP_COS_DETAILS = "{call DDDCA_PK_DSCPCOS_SPRING" + ""
      + ".proc_get_order_product_details(?,?,?)}";

  String GET_TIR_DETAILS = "{call DDDCA_PK_DSCPCOS_SPRING.proc_get_vpn_bw(?,?,?)}";

  String GET_PROC_DSCP_COS_DETAILS = "{call DDDCA_PK_DSCPCOS_SPRING" + ""
      + ".proc_get_DSCPCos_detail(?,?,?)}";

  /**
   * method to fetch dscpcosdetails.
   * 
   * @param orderId.
   * @return list of dscpcos.
   */
  public List<DSCPCosDomain> getDSCPCosDetails(String orderId);

  /**
   * method to fetch tirbandwidth.
   * 
   * @param vpnId.
   * @return tir bandwidth.
   */
  public DSCPCosDomain getTirBandwidth(int vpnId);

  /**
   * method to fetch mipr and cipr values.
   * 
   * @param vpnId.
   * @return mipr,cipr.
   */
  public DSCPCosDomain getProcDscpCosDetails(int vpnId);

}
