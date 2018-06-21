package com.bt.edca.web.controller.test;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import com.bt.edca.common.dto.AdditionalInformationDTO;

import junit.framework.TestCase;
/**
 * class for testing the AdditionalInformationController .
 * @author Rohit -
 *
 */
public class AdditionalInformationControllerTest extends TestCase {
/**	
 * 
 */
	private DispatcherServlet servlet;
	/**
	 * 
	 * @param name .
	 * @param servlet .
	 */
    public AdditionalInformationControllerTest(
        String name, DispatcherServlet servlet) {
        super(name);
        this.servlet = servlet;
    }
/**
 * 
 * @throws Exception .
 */
	public void testGetAdditionalInformation() throws Exception {
		    System.out.println(
		        "Entering inside AdditionalInformationControllerTest -"
		        + " Method testGetAdditionalInformation");
		    MockHttpServletRequest request = new MockHttpServletRequest();
	        request.setMethod("GET");	        
	        request.setRequestURI("/additionalInformation/122937");
	             
	        MockHttpServletResponse response = new MockHttpServletResponse();
	        servlet.service(request, response);
	        
	        AdditionalInformationDTO additionalInformationDTO = 
	            (AdditionalInformationDTO) request.getAttribute(
	                "additionalInformationDTO");
	        assertEquals("ghfh", additionalInformationDTO.getCotNotes());
	        assertEquals(null,additionalInformationDTO.getNotes());
	        assertEquals(null,additionalInformationDTO.getProductNotes());
	        assertEquals("Yes",additionalInformationDTO.getOverrideAuthorisation());
	        assertEquals("VermaRohitd",additionalInformationDTO.getAuthorisationOverrideReason());
	
	        
	        
	        System.out.println(
	            "Exiting from AdditionalInformationControllerTest - "
	            + "Method testGetAdditionalInformation");	        
	}
	
}
