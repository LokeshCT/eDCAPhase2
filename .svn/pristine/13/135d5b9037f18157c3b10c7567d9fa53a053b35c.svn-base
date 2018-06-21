package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.SiteAddressDTO;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Unit Test for SiteAddress.
 * 
 * @author Jagankumar G.
 *
 */
public class SiteAddressControllerTest extends TestCase {

  /**
   * handles the requests and responses.
   */
  private DispatcherServlet servlet;

  /**
   * @param name.
   * @param servlet.
   */
  public SiteAddressControllerTest(String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }

  /**
   * throws Exception. When the user try access with invalid orderId or URL.
   */

  public void testGetSiteAddress() throws Exception {
    System.out.println("Entering inside SiteAddressTest - "
        + "" + "Method testGetSiteAddressDetails");
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    request.setRequestURI("/siteaddress/94450");
    request.setParameter("siteId","88002");
    request.setParameter("siteLocationId","86074");
   
    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.service(request, response);

   SiteAddressDTO siteAddressDTO = (SiteAddressDTO) request.getAttribute("siteAddressDTO");
    assertEquals("Floor1",siteAddressDTO.getFloor());
    assertEquals("Room11", siteAddressDTO.getRoom());
    assertEquals(null, siteAddressDTO.getBuildingNumber());
    assertEquals("18 SCIENCE PARK DRIVE", siteAddressDTO.getAddressLine1());
    assertEquals(null, siteAddressDTO.getAddressLine2());
    assertEquals("KOLKATA", siteAddressDTO.getCity());
    assertEquals("07110", siteAddressDTO.getPostcode());
    assertEquals("WEST BENGAL", siteAddressDTO.getState());
    
    System.out.println("Exiting from SiteAddressTest -"
        + "" + " Method testGetSiteAddress");
  }
  
}
