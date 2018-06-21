package com.bt.edca.web.controller.test;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import com.bt.edca.common.dto.VpnServiceDTO;

import junit.framework.TestCase;
/**
 * Description : Class for testing the VpnServiceController .
 * @author Rohit . 
 *
 */
public class VpnServiceControllerTest extends TestCase {
/** 
 * 
 */
    private DispatcherServlet servlet;
    /**
     * 
     * @param name .
     * @param servlet .
     */
    public VpnServiceControllerTest(
        String name, DispatcherServlet servlet) {
        super(name);
        this.servlet = servlet;
    }
/**
 * 
 * @throws Exception .
 */
    public void testGetVpnService() throws Exception {
            System.out.println(
                "Entering inside VpnServiceTest -"
                + " Method testGetVpnService");
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setMethod("GET");           
            request.setRequestURI("/vpnService/871");
                        
            MockHttpServletResponse response = new MockHttpServletResponse();
            servlet.service(request, response);
            VpnServiceDTO vpnServiceDTO = 
                (VpnServiceDTO) request.getAttribute(
                    "vpnServiceDTO");
            assertEquals(48, vpnServiceDTO.getBandwidth());
            assertEquals("Full Burst", vpnServiceDTO.getBurstOption());
            assertEquals(null, vpnServiceDTO.getCoreDiversity());
            assertEquals("IDN-Contributions", vpnServiceDTO.getServiceName());
            assertEquals("Failover Primary", vpnServiceDTO.getServiceResilience());
            assertEquals("Silver Standard", vpnServiceDTO.getSlaPackage());
            assertEquals(null, vpnServiceDTO.getProductName());
            System.out.println(
                "Exiting from VpnServiceControllerTest - "
                + "Method testGetVpnService");           
    }
}
