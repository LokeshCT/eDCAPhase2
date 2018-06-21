package com.bt.edca.web.controller.test;



import java.util.List;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;


import com.bt.edca.common.dto.VpnServiceDTO;

import junit.framework.TestCase;

public class VPNServicesDetailsControllerTest extends TestCase{
	/**
	   * DispatcherServlet.
	   */
	  private DispatcherServlet servlet;

	  /**
	   * @param name.
	   * @param servlet.
	   */
	public VPNServicesDetailsControllerTest(String name, DispatcherServlet servlet) {
	    super(name);
	    this.servlet = servlet;
	  }
	 /**
	   * @throws Exception.
	   * 
	   */

	  public void testVPNServicesDetails() throws Exception {

	    System.out.println("Entering the VPNServicesDetailsControllerTest - Method testVPNServicesDetails");
	   
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setMethod("GET");           
            request.setRequestURI("/vpnServices/8440");
                        
            MockHttpServletResponse response = new MockHttpServletResponse();
            servlet.service(request, response);
        
            List VPNServicesList = (List) request.getAttribute("vpnServicesDTO");
            assertEquals(200, response.getStatus());
            assertNotNull(response.getContentAsString());
            assertNotNull(VPNServicesList);
          
          
	    System.out.println("Exiting from VPNServicesDetailsControllerTest - Method testVPNServicesDetails");

	  }
}
