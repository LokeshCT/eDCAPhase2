package com.bt.edca.web.controller.test;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import com.bt.edca.common.dto.VpnConnectionDTO;

import junit.framework.TestCase;
/**
 * Description : Class for testing the VpnConnectionController .
 * @author Rohit . 
 *
 */
public class VpnConnectionControllerTest extends TestCase {
/** 
 * 
 */
    private DispatcherServlet servlet;
    /**
     * 
     * @param name .
     * @param servlet .
     */
    public VpnConnectionControllerTest(
        String name, DispatcherServlet servlet) {
        super(name);
        this.servlet = servlet;
    }
/**
 * 
 * @throws Exception .
 */
    public void testGetVpnConnection() throws Exception {
            System.out.println(
                "Entering inside VpnConnectionTest -"
                + " Method testGetVpnConnection");
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setMethod("GET");           
            request.setRequestURI("/vpnConnection/11192");
                        
            MockHttpServletResponse response = new MockHttpServletResponse();
            servlet.service(request, response);
            
            VpnConnectionDTO vpnConnectionDTO = 
                (VpnConnectionDTO) request.getAttribute(
                    "vpnConnectionDTO");
            assertEquals("B_Composite", vpnConnectionDTO.getConnName());
            assertEquals("Extranet", vpnConnectionDTO.getConnType());
            assertEquals(null, vpnConnectionDTO.getAccessCkt());
            assertEquals(432, vpnConnectionDTO.getConnBandwidth());
            assertEquals(null, vpnConnectionDTO.getVpnFriendlyName());
            assertEquals(null, vpnConnectionDTO.getSecureExt());
            assertEquals(null, vpnConnectionDTO.getOriginatorCir());
            assertEquals("N/A (defined at Extranet Service level)", vpnConnectionDTO.getConnecType());
            assertEquals(null, vpnConnectionDTO.getMultiServ());
            System.out.println(
                "Exiting from VpnConnectionControllerTest - "
                + "Method testGetVpnConnection");           
    }
}
