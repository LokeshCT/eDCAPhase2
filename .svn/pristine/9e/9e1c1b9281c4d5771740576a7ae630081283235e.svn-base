package com.bt.edca.web.controller.test;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import com.bt.edca.common.dto.ConfigureWicnmpaDTO;

import junit.framework.TestCase;
/**
 * Description : Class for testing the ConfigureWicnmpaController .
 * @author Rohit .
 *
 */
public class ConfigureWicnmpaControllerTest extends TestCase {
/** 
 * 
 */
    private DispatcherServlet servlet;
    /**
     * 
     * @param name .
     * @param servlet .
     */
    public ConfigureWicnmpaControllerTest(
        String name, DispatcherServlet servlet) {
        super(name);
        this.servlet = servlet;
    }
/**
 * 
 * @throws Exception .
 */
    public void testGetConfigureWicnmpa() throws Exception {
            System.out.println(
                "Entering inside ConfigureWicnmpaControllerTest -"
                + " Method testGetConfigureWicnmpa");
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setMethod("GET");           
            request.setRequestURI("/configureWicnmpa/64519");
            MockHttpServletResponse response = new MockHttpServletResponse();
            servlet.service(request, response);
            ConfigureWicnmpaDTO configureWicnmpaDTO = 
                (ConfigureWicnmpaDTO) request.getAttribute(
                    "configureWicnmpaDTO");
            assertEquals(1, configureWicnmpaDTO.getCpeSeqId());
                System.out.println(
                "Exiting from ConfigureWicnmpaControllerTest - "
                + "Method testGetConfigureWicnmpa");           
    }
}
