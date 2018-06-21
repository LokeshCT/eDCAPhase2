package com.bt.edca.web.controller;

import com.bt.edca.common.dto.DSCPCosDTO;
import com.bt.edca.service.DSCPCosService;
import com.bt.edca.web.constants.EdcaWebConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * Description: Controller returns to view with model. Implements the method of
 * Service layer.
 * 
 * @author Jagankumar G.
 *
 */
@Controller
public class DSCPCosController {

  private static final String IS_DISABLED = "isDisabled";
  /**
   * declaration of interface of service layer.
   * 
   * @param dscpCosService.
   */
  @Autowired
  private DSCPCosService dscpCosService;

  /**
   * method from Service layer gets the orderId.
   * 
   * @return getDSCPCosService.
   */
  public DSCPCosService getDSCPCosService() {
    return dscpCosService;
  }

  /**
   * description:sets the orderId for Servicelayer.
   * 
   * @param dscpCosService.
   */
  public void setDSCPCosService(DSCPCosService dscpCosService) {
    this.dscpCosService = dscpCosService;
  }

  /**
   * Description: Returns model with data.
   * 
   * @param orderId.
   * @View getDSCPCosDetails model
   * @model DSCPCosDO.
   * @return view with model.
   */
  @RequestMapping(value = { "/dscpcosvalues/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getDSCPCosDetails(@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,		  
		  @RequestParam(EdcaWebConstants.VPN_ID) String vpnId,
		  HttpServletRequest request) {

    DSCPCosDTO dscpCosDTO = new DSCPCosDTO();

    dscpCosDTO = dscpCosService.getDSCPdetails(orderId);

    Map<String, Object> model = new HashMap<String, Object>();
    model.put("dscpCosDTO", dscpCosDTO);
    model.put(IS_DISABLED, "true");

    return new ModelAndView("dscpCosValues", model);
  }
}
