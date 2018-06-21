package com.bt.edca.web.controller.test;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;
import com.bt.edca.common.dto.ConfigureAccessTypeDTO;

import junit.framework.TestCase;
/**
 *Description : Class for testing the ConfigureAccessTypeController .
 * @author Rohit .
 *
 */
public class ConfigureAccessTypeControllerTest extends TestCase {
/** 
 * 
 */
    private DispatcherServlet servlet;
    /**
     * 
     * @param name .
     * @param servlet .
     */
    public ConfigureAccessTypeControllerTest(
        String name, DispatcherServlet servlet) {
        super(name);
        this.servlet = servlet;
    }
/**
 * 
 * @throws Exception .
 */
    public void testGetConfigureAccessType() throws Exception {
            System.out.println(
                "Entering inside ConfigureAccessTypeControllerTest -"
                + " Method testGetConfigureAccessType");
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setMethod("GET");           
            request.setRequestURI("/configureAccessType/50358");
            request.setParameter("serviceInstance","Primary");
            request.setParameter("siteLocationId","47430");
        	request.setParameter("siteId","48606");	
        	request.setParameter("serviceInstanceId","64920");
                        
            MockHttpServletResponse response = new MockHttpServletResponse();
            servlet.service(request, response);
            
            ConfigureAccessTypeDTO configureAccessTypeDTO = 
                (ConfigureAccessTypeDTO) request.getAttribute(
                    "configureAccessTypeDTO");
            assertEquals(null, configureAccessTypeDTO.getAccessBearerPhyConn());
            assertEquals(null, configureAccessTypeDTO.getAccessOffer());
            assertEquals(null, configureAccessTypeDTO.getAccessType());
            assertEquals(null, configureAccessTypeDTO.getAdditionalAccess());
            assertEquals(null, configureAccessTypeDTO.getBandwidthCircuit());
            assertEquals(null, configureAccessTypeDTO.getBtPopLocation());
            assertEquals(null, configureAccessTypeDTO.getInterfaceType());
            assertEquals(null, configureAccessTypeDTO.getQref());
            assertEquals(null, configureAccessTypeDTO.getCurrency());
            assertEquals(null, configureAccessTypeDTO.getSupplierQuotedNrc());
            assertEquals(null, configureAccessTypeDTO.getSupplierQuotedMrc());
            assertEquals(null, configureAccessTypeDTO.getOptimalAccessTech());
            assertEquals(null, configureAccessTypeDTO.getRegionalLimitl());
            assertEquals(null, configureAccessTypeDTO.getCeLimit());
            assertEquals(null, configureAccessTypeDTO.
                getPlPhysicalConnectoreType());
            assertEquals(null, configureAccessTypeDTO.getPlAccessSpeed());
            assertEquals(null, configureAccessTypeDTO.
                getPlElectricalInterface());
            assertEquals(null, configureAccessTypeDTO.getBtPopLocation());
            assertEquals(null, configureAccessTypeDTO.getCheapestSolution());
            assertEquals(null, configureAccessTypeDTO.getConvertedCurrency());
            assertEquals(null, configureAccessTypeDTO.getCurrency());
            assertEquals(null, configureAccessTypeDTO.getEthPhaseType());
            assertEquals(000, configureAccessTypeDTO.
                getAggregatedMulticastBandwidth());
            assertEquals(000, configureAccessTypeDTO.getServiceInstanceId());
            assertEquals(null, configureAccessTypeDTO.getLmi());
            assertEquals(null, configureAccessTypeDTO.getInterfaceType());
            assertEquals(null, configureAccessTypeDTO.getFraming());
            assertEquals(null, configureAccessTypeDTO.getNetwork());
            assertEquals(null, configureAccessTypeDTO.
                getElectricalInterface());
            assertEquals(null, configureAccessTypeDTO.getPortSpeed());
            assertEquals(null, configureAccessTypeDTO.
                getPhysicalAccessSpeed());
            assertEquals(null, configureAccessTypeDTO.getExistingCircuit());
            assertEquals(null, configureAccessTypeDTO.
                getAccessCktResilenceDiversity());
            assertEquals(null, configureAccessTypeDTO.
                getPhysicalConnector());
            assertEquals(null, configureAccessTypeDTO.getFrPvcClassOfService());
            assertEquals(null, configureAccessTypeDTO.getJdsuProbeOnPe());
            assertEquals(null, configureAccessTypeDTO.getPortType());
            assertEquals(null, configureAccessTypeDTO.
                getPorReferenceNumber());           
            System.out.println(
                "Exiting from ConfigureAccessTypeControllerTest - "
                + "Method testGetConfigureAccessType");           
    }
}
