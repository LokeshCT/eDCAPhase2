package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.SiteLocationDetailsDTO;

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

public class SiteLocationsControllerTest extends TestCase {

  /**
   * DispatcherServlet.
   */
  private DispatcherServlet servlet;

  /**
   * @param name.
   * @param servlet.
   */
  public SiteLocationsControllerTest(String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }

  /**
   * Description : Method for testing the the controller.
   * 
   * @throws Exception
   *           .
   */

  public void testGetSiteLocationsDetails() throws Exception {
    System.out.println("Entering inside SiteLocationsDetailsControllerTest -" + " Method SiteLocationsDetails");
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    request.setRequestURI("/siteLocations/9821");
    request.setParameter("siteId","9323");
    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.service(request, response);
    List<SiteLocationDetailsDTO> siteLocationsDetailsDTOList = (List<SiteLocationDetailsDTO>) request
        .getAttribute("siteLocationDetailsList");
    for (SiteLocationDetailsDTO siteLocationDetailsDTO : siteLocationsDetailsDTOList) {
      System.out.println("SiteLocationIdt -" + siteLocationDetailsDTO.getSiteLocationId());
      assertEquals(9020, siteLocationDetailsDTO.getSiteLocationId());
      assertEquals("THE CITY OF LONDON CORPORATION|1+JPBX+39280#|LONDON|GB|UKI", siteLocationDetailsDTO.getSiteName());

    }
    System.out.println("Existing SiteLocationsDetailsControllerTest -" + " Method SiteLocationsDetails");
  }

}
