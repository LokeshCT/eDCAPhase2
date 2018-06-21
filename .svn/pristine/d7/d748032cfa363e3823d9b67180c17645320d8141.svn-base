package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.CustomerContactDTO;
import com.bt.edca.common.dto.OrderContactDTO;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * Description: JUnit test Class for CustomerContactDetailsController.
 * 
 * @author Jagan kumar G.
 *
 */
public class CustomerContactDetailsControllerTest extends TestCase {

	/**
	 * DispatcherServlet.
	 */
	private DispatcherServlet servlet;

	/**
	 * @param name.
	 * @param servlet.
	 */
	public CustomerContactDetailsControllerTest(String name, DispatcherServlet servlet) {
		super(name);
		this.servlet = servlet;
	}

	/**
	 * method to test ReutersContactTypes and TechnicalContactTypes. throws
	 * Exception if URI is not matched.
	 */
	public void testGetReutersCustomerContactDetails() throws Exception {
		System.out.println("Entering inside CustomerContactDetailsControllerTest - " + ""
				+ "Method testGetCustomerContactDetails");
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setRequestURI("/customercontactdetails/710");

		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);

		CustomerContactDTO reutersContactList = (CustomerContactDTO) request.getAttribute("customerContactDTO");

		CustomerContactDTO customerContactDTO = (CustomerContactDTO) reutersContactList;

		List<OrderContactDTO> orderContactDTO = (List<OrderContactDTO>) customerContactDTO.getReutersContactList();
		OrderContactDTO customerContactDetailsDTO = orderContactDTO.get(0);
		assertEquals(200, response.getStatus());
		assertNotNull(response.getContentAsString());
		assertEquals("0000002491", customerContactDetailsDTO.getContactId());
		assertEquals("Administrator", customerContactDetailsDTO.getFirstName());
		assertEquals("Reuter", customerContactDetailsDTO.getLastName());
		assertEquals("Roger@reuters.com", customerContactDetailsDTO.getEmail());
		assertEquals("01473 647726", customerContactDetailsDTO.getPhone());
		assertEquals(null, customerContactDetailsDTO.getMobilePager());

		CustomerContactDTO technicalContactList = (CustomerContactDTO) request.getAttribute("customerContactDTO");

		CustomerContactDTO customertechnicalDTO = (CustomerContactDTO) technicalContactList;

		List<OrderContactDTO> orderTechnicalDTO = (List<OrderContactDTO>) customertechnicalDTO
				.getTechnicalContactList();

		OrderContactDTO technicalDTO = orderTechnicalDTO.get(0);

		assertEquals("0000002494", technicalDTO.getContactId());

		assertEquals(null, technicalDTO.getFirstName());
		assertEquals(null, technicalDTO.getLastName());
		assertEquals(null, technicalDTO.getJobTitle());
		assertEquals(null, technicalDTO.getEmail());
		assertEquals(null, technicalDTO.getPhone());
		assertEquals(null, technicalDTO.getMobilePager());

		
		MockHttpServletRequest saveRequest = new MockHttpServletRequest();
		
		MockHttpServletResponse saveresponse = new MockHttpServletResponse();
		servlet.service(request, response);
		saveRequest.setMethod("GET");
		saveRequest.setRequestURI("/saveTechnicalContactDetails");
		HttpSession session = null;
		
		OrderContactDTO savedTechnicalDetails=(OrderContactDTO)saveRequest.getAttribute("customerContactDTO");
		
	
		
		assertEquals(200,saveresponse.getStatus());
		assertNotNull(response.getContentAsString());
		
		
		
		
	}

	/**
	 * Description: Method to test CommercialContactTypes and
	 * TechnicalContactTypes. throws Exception if URI is not matched.
	 */
	public void testGetCommercialCustomerContactDetails() throws Exception {
		System.out.println("Entering inside CommercialCustomerContactDetailsControllertest" + " -"
				+ "Method testGetCustomerContactDetails");
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setRequestURI("/customercontactdetails/28324");

		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);

		CustomerContactDTO commercialContactList = (CustomerContactDTO) request.getAttribute("customerContactDTO");

		CustomerContactDTO customertechnicalDTO = (CustomerContactDTO) commercialContactList;

		List<OrderContactDTO> orderContactDTO = (List<OrderContactDTO>) customertechnicalDTO.getCommercialContactList();

		OrderContactDTO commercialDTO = orderContactDTO.get(0);

		assertEquals(200, response.getStatus());
		assertNotNull(response.getContentAsString());

		assertEquals("ID2029", commercialDTO.getContactId());
		assertEquals("Ben", commercialDTO.getFirstName());
		assertEquals("jones", commercialDTO.getLastName());
		assertEquals("ben.jones@thomson .com", commercialDTO.getEmail());
		assertEquals("442073249424", commercialDTO.getPhone());
		assertEquals(null, commercialDTO.getMobilePager());

		OrderContactDTO commercialContactDTO = orderContactDTO.get(1);

		assertEquals("ID2030", commercialContactDTO.getContactId());
		assertEquals("Ben", commercialContactDTO.getFirstName());
		assertEquals("jones", commercialContactDTO.getLastName());
		assertEquals("ben.jones@thomson .com", commercialContactDTO.getEmail());
		assertEquals("442073249424", commercialContactDTO.getPhone());
		assertEquals(null, commercialContactDTO.getMobilePager());
	}
}
