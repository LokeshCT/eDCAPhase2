package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.SiteLocationDetailsDTO;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Unit Test for SiteLocationDetails.
 * 
 * @author Jagankumar G.
 *
 */
public class SiteLocationDetailsTest extends TestCase {

  /**
   * handles the requests and responses.
   */
  private DispatcherServlet servlet;

  /**
   * @param name.
   * @param servlet.
   */
  public SiteLocationDetailsTest(String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }

  /**
   * throws Exception. When the user try access with invalid orderId or URL.
   */

  public void testGetSiteLocationDetails() throws Exception {
    System.out.println("Entering inside SiteLocationDetailsTest - "
        + "" + "Method testGetSiteLocationDetails");
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    request.setRequestURI("/sitelocationdetails/50358");
    request.setParameter("siteId","48606");
    request.setParameter("siteLocationId","47430");
    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.service(request, response);

    SiteLocationDetailsDTO siteLocationDetailsDTO = (SiteLocationDetailsDTO) request
        .getAttribute("siteLocationDetailsDTO");

    assertEquals(200, response.getStatus());
    assertNotNull(response.getContentAsString());
    assertEquals("KYOCERA CORPORATION (KYOTO)|1+JPBX+23457#|KYOTO|JP|ASIA", 
        siteLocationDetailsDTO.getSiteName());
    assertEquals("Japan", siteLocationDetailsDTO.getCountry());
    assertEquals("S1034317", siteLocationDetailsDTO.getClassicSiteId());
    assertEquals("S1034317", siteLocationDetailsDTO.getSerClassicSiteId());
    assertEquals(47430, siteLocationDetailsDTO.getSiteLocationId());
    assertEquals(48606, siteLocationDetailsDTO.getSiteId());
    
    System.out.println("Exiting from SiteLocationDetailsTest -"
        + "" + " Method testGetSiteLocationDetails");
  }
}
