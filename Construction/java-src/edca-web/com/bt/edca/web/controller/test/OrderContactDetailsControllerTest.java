package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.OrderContactDTO;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * UnitTest to for OrderContactdetails.
 * 
 * @author Jagankumar G.
 *
 */
public class OrderContactDetailsControllerTest extends TestCase {

  /**
   * DispatcherServlet.
   */
  private DispatcherServlet servlet;

  /**
   * @param name.
   * @param servlet.
   */
  public OrderContactDetailsControllerTest(String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }

  /**
   * throws Exception. When the user try access with invalid orderId or URL.
   */

  public void testGetOrderContactDetails() throws Exception {
    System.out.println("Entering inside OrderContactDetailsControllerTest - "
        + "" + "Method testGetOrderContactDetails");
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    request.setRequestURI("/ordercontactdetails/723");

    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.service(request, response);

    OrderContactDTO orderContactDTO = (OrderContactDTO) request.getAttribute("orderContactDTO");

    assertEquals(200, response.getStatus());
    assertNotNull(response.getContentAsString());
    assertEquals("Order", orderContactDTO.getFirstName());
    assertEquals("Contact", orderContactDTO.getLastName());
    assertEquals("order@tm.com", orderContactDTO.getEmail());
    assertEquals("121314 131", orderContactDTO.getPhone());
    assertEquals("1131 2222", orderContactDTO.getMobilePager());

    System.out.println("Exiting from OrderContactDetailsControllerTest -"
        + "" + " Method testGetCustomerDetails");
  }
}
