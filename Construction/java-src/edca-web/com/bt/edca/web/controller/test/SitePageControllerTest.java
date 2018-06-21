package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.SitePageDTO;
import junit.framework.TestCase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Description : Test Class for Unit Testing.
 * @author SATHYA.
 *
 */
public class SitePageControllerTest extends TestCase {

  /**
   * DispatcherServlet.
   */
  private DispatcherServlet servlet;

  /**
   * @param name.
   * @param servlet.
   */
  public SitePageControllerTest(String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }

  /**
   * Description : Used to Test the values.
   * @throws Exception.
   * 
   */
  public void testGetSitePage() throws Exception {
    System.out.println("Entering inside SitePageControllerTest"
        + " - Method testGetSitePage");
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    MockHttpServletResponse response = new MockHttpServletResponse(); 

    request.setRequestURI("/sitepage/9821");
    request.setParameter("siteId","9323");
  
    servlet.service(request, response);

    SitePageDTO sitePageDTO = 
        (SitePageDTO) request.getAttribute("SitePageDTO");  
      assertEquals(null, sitePageDTO.getDcaReferenceId());
    assertEquals("Gold", sitePageDTO.getHighestSiteSla());
    assertEquals("S", sitePageDTO.getStructureCabling());
 
    request.setMethod("GET");
    request.setRequestURI("/saveSitePage");
  
       
    
    
    System.out.println("Exiting from SitePageControllerTest -"
        + " Method testGetSitePage");
  }
}