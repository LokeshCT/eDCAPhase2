package com.bt.edca.web.controller.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;
import com.bt.edca.common.dto.SiteContactDetailsDTO;
import junit.framework.TestCase;
/**
 * Description:  This is a class for testing the controller .
 * @author Rohit
 *
 */
public class SiteContactDetailsControllerTest extends TestCase {
/**
 * Description :  Creating the object servlet .
 */
  private DispatcherServlet servlet;

  public SiteContactDetailsControllerTest(
      String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }
  /**
   * Description : Method for testing the the controller .
   * @throws Exception .
   */
  public void testGetSiteContactDetails() throws Exception {
    System.out.println("Entering inside " 
     + "SiteContactDetailsControllerTest - " + "Method testGetSiteContactDetails");
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    request.setRequestURI("/siteContactDetails/1088");
    
    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.service(request, response);

    List<SiteContactDetailsDTO> siteContactDetailsDTOList = 
        new ArrayList<SiteContactDetailsDTO>();

    for (SiteContactDetailsDTO siteContactDetailsDTO 
        : siteContactDetailsDTOList) {
      assertEquals("1088", siteContactDetailsDTO.getSiteLocationId());
    }
    System.out.println("Exiting from SiteContactDetailsControllerTest - " 
    + "Method testGetSiteContactDetails");
  }
}
