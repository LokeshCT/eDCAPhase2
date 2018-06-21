package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.CPEConfigureDTO;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

/**
 * Description : Test Class for Unit Testing.
 * 
 * @author SATHYA.
 *
 */

public class CPEConfigureControllerTest extends TestCase {

  /**
   * DispatcherServlet.
   */
  private DispatcherServlet servlet;

  /**
   * @param name.
   * @param servlet.
   */

  public CPEConfigureControllerTest(String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }

  /**
   * Description : Method for testing the the controller .
   * 
   * @throws Exception
   *           .
   */
  public void testGetCPEConfigureDetails() throws Exception {

    System.out.println("Entering the EnteredIntoCPEConfigureTest " + " - Method testGetCPEConfigureDetails");
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    request.setRequestURI("/cpeConfigure/50358");
    request.setParameter("serviceInstance","Primary");
    request.setParameter("siteLocationId","47430");
	request.setParameter("siteId","48606");	
	request.setParameter("serviceInstanceId","64920");
    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.service(request, response);

    List<CPEConfigureDTO> cpeConfigureDTOList = (List<CPEConfigureDTO>) request.getAttribute("cPEConfigureDTOList");

    for (CPEConfigureDTO cpeConfigureDTO : cpeConfigureDTOList) {
      assertEquals("CISCO1841", cpeConfigureDTO.getBaseRouter());
      assertEquals("CISCO1841 MODULAR RTR PKG 2XFE 2XWAN SLTS 32F/128D", cpeConfigureDTO.getChassis());
      
    }

    System.out.println("Existing the CPEConfigureTest" + "- Method testGetCPEConfigureDetails");
  }

}
