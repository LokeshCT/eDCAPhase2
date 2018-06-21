package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.CustomerDetailsDTO;
import junit.framework.TestCase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Test Class for Unit Testing.
 * 
 * @author Niranjan das Basa.
 *
 */
public class CustomerDetailsControllerTest extends TestCase {

	/**
	 * DispatcherServlet.
	 */
	private DispatcherServlet servlet;

	/**
	 * @param name.
	 * @param servlet.
	 */
	public CustomerDetailsControllerTest(String name, DispatcherServlet servlet) {
		super(name);
		this.servlet = servlet;
	}

	/**
	 * @throws Exception.
	 * 
	 */
	public void testGetCustomerDetails() throws Exception {
		System.out.println("Entering inside CustomerDetailsControllerTest" + " - Method testGetCustomerDetails");
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setRequestURI("/customerdetails/125015");

		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);

		CustomerDetailsDTO customerDetailsDTO = (CustomerDetailsDTO) request.getAttribute("customer");

		assertEquals(200, response.getStatus());
		assertNotNull(response.getContentAsString());
		assertEquals(null, customerDetailsDTO.getCustomerName());
		assertEquals("01000906", customerDetailsDTO.getMasterCustomerId());
		assertEquals("Add", customerDetailsDTO.getOrderType());
		// assertEquals(200, customerDetailsDTO.getSubOrderType());
		assertEquals("BT Global Services", customerDetailsDTO.getDistributorLegalName());
		assertEquals("COT", customerDetailsDTO.getExternalSystemName());
		assertEquals("295465ABC", customerDetailsDTO.getExternalSystemReference());
		assertEquals("01000906-00001", customerDetailsDTO.getBillingId());
		assertEquals("01000906", customerDetailsDTO.getContractId());
		assertEquals("GBP", customerDetailsDTO.getCurrency());
		assertEquals("BT", customerDetailsDTO.getOpportunityId());
		assertEquals(125015, customerDetailsDTO.getOrderId());
		assertEquals("NO", customerDetailsDTO.getPerformanceReportRequired());

		System.out.println("Exiting from CustomerDetailsControllerTest -" + " Method testGetCustomerDetails");
	}

	/**
	 * @throws Exception.
	 * 
	 */
	public void testGetInfrastructureCustomer() throws Exception {
		System.out.println("Entering inside CustomerDetailsControllerTest -" + " Method testGetInfrastructureCustomer");
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setRequestURI("/infrastructurecustomer/125015");

		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);

		CustomerDetailsDTO customerDetailsDTO = (CustomerDetailsDTO) request.getAttribute("customer");

		assertEquals(200, response.getStatus());
		assertNotNull(response.getContentAsString());
		assertEquals(null, customerDetailsDTO.getCustomerName());
		assertEquals("01000906", customerDetailsDTO.getMasterCustomerId());
		assertEquals("Add", customerDetailsDTO.getOrderType());
		// assertEquals(200, customerDetailsDTO.getSubOrderType());
		assertEquals("BT Global Services", customerDetailsDTO.getDistributorLegalName());
		assertEquals("COT", customerDetailsDTO.getExternalSystemName());
		assertEquals("295465ABC", customerDetailsDTO.getExternalSystemReference());
		assertEquals("01000906-00001", customerDetailsDTO.getBillingId());
		assertEquals("01000906", customerDetailsDTO.getContractId());
		assertEquals("GBP", customerDetailsDTO.getCurrency());
		assertEquals("BT", customerDetailsDTO.getOpportunityId());
		assertEquals(125015, customerDetailsDTO.getOrderId());
		assertEquals("NO", customerDetailsDTO.getPerformanceReportRequired());

		System.out.println("Exiting from CustomerDetailsControllerTest -" + " Method testGetInfrastructureCustomer");
	}
}