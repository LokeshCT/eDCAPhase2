package com.bt.edca.web.controller.test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import com.bt.edca.common.dto.ProductSelectionDTO;

import junit.framework.TestCase;
/**
 * class for testing the ProductSelectionController .
 * @author Rohit
 *
 */
public class ProductSelectionControllerTest extends TestCase {
	/**
	 * 
	 */
	private DispatcherServlet servlet;
	/**
	 * 
	 * @param name .
	 * @param servlet .
	 */
    public ProductSelectionControllerTest(
        String name, DispatcherServlet servlet) {
        super(name);
        this.servlet = servlet;
    }
/**
 * 
 * @throws Exception .
 */
	public void testGetProductSelection() throws Exception {
		    System.out.println("Entering inside "
		        + "ProductSelectionControllerTest - "
		        + "Method testGetProductSelection");
		    MockHttpServletRequest request = new MockHttpServletRequest();
	        request.setMethod("GET");	        
	        request.setRequestURI("/productSelection/195460");
	        	        
	        MockHttpServletResponse response = new MockHttpServletResponse();
	        servlet.service(request, response);
	        
	        ProductSelectionDTO productSelectionDTO = 
	            (ProductSelectionDTO) request.getAttribute(
	                "productSelectionDTO");
	        assertEquals(null, productSelectionDTO.getCosModel());  
	        System.out.println("Exiting from ProductSelectionControllerTest - "
	            + "Method testGetProductSelection");	        
	}
}
