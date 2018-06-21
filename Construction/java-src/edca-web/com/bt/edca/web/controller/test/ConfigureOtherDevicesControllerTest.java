package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.ConfigureOtherDevicesDTO;

import junit.framework.TestCase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

public class ConfigureOtherDevicesControllerTest extends TestCase {

  /**
   * DispatcherServlet.
   */
  private DispatcherServlet servlet;

  /**
   * @param name.
   * @param servlet.
   */

  public ConfigureOtherDevicesControllerTest(String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }

  /**
   * Description : Method for testing the the controller .
   * 
   * @throws Exception
   *           .
   */
  public void testGetOtherDetails() throws Exception {

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    request.setRequestURI("/configureOtherDevice/50358");
    request.setParameter("serviceInstance","Primary");
    request.setParameter("siteLocationId","47430");
	request.setParameter("siteId","48606");	
	request.setParameter("serviceInstanceId","64920");
    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.service(request, response);
    List<ConfigureOtherDevicesDTO> cableList = (List<ConfigureOtherDevicesDTO>) request.getAttribute("cpeList");
    for (ConfigureOtherDevicesDTO configureCableDTO : cableList) {
      assertEquals(null, configureCableDTO.getBaseRouter());
      assertEquals(null, configureCableDTO.getChassis());
      assertEquals(null, configureCableDTO.getCpeUsage());

    }
  }

}
