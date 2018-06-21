package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.ServiceInstancesDTO;

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
public class ServiceInstancesControllerTest extends TestCase {

	/**
	 * DispatcherServlet.
	 */
	private DispatcherServlet servlet;

	/**
	 * @param name.
	 * @param servlet.
	 */
	public ServiceInstancesControllerTest(String name, DispatcherServlet servlet) {
		super(name);
		this.servlet = servlet;
	}

	/**
	 * Description : Method for testing the the controller .
	 * 
	 * @throws Exception.
	 * 
	 */
	public void testGetServiceInstancesDetails() throws Exception {
		System.out.println(
				"Entering the EnteredIntoServiceInsatncesControllerTest " + " - Method testGetServiceInstancesDetails");
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setRequestURI("/serviceInstance/50358");
		request.setParameter("siteId","48606");
		request.setParameter("siteLocationId", "47430");
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
		ServiceInstancesDTO serviceInstancesDT=new ServiceInstancesDTO();		
		List<ServiceInstancesDTO> serviceInstancessDTOList = (List<ServiceInstancesDTO>) request
				.getAttribute("serviceInstancesDetailsList");	
		for (ServiceInstancesDTO serviceInstancesDTO : serviceInstancessDTOList) {
		assertEquals("Primary", serviceInstancesDTO.getServiceInstance());
		assertEquals(64920, serviceInstancesDTO.getServiceInstanceId());
		}
		System.out.println(
				"Existing  Into the ServiceInsatncesControllerTest " + " - Method testGetServiceInstancesDetails");
	}

}
