package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.OrderDetailsDTO;
import junit.framework.TestCase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;




public class OrderSearchControllerTest extends TestCase {

  /**
   * DispatcherServlet.
   */
  private DispatcherServlet servlet;

  /**
   * @param name.
   * @param servlet.
   */

  public OrderSearchControllerTest(String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }

  /**
   * @throws Exception.
   * 
   */

  public void testGetOrderDetails() throws Exception {

    System.out.println("Entering the OrderSearchControllerTest - Method testGetOrderDetails");
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    request.setRequestURI("/searchOrder/Unassigned/1");

    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.service(request, response);
    List<OrderDetailsDTO> orderDetailsList = (List<OrderDetailsDTO>) request.getAttribute("OrderDetailsDTO");
    assertEquals(200, response.getStatus());
    assertNotNull(response.getContentAsString());
    assertEquals(30, orderDetailsList.size());
    assertNotNull(orderDetailsList);

    System.out.println("Exiting from OrderSearchControllerTest - Method testGetOrderDetails");

  }
}