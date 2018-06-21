package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.SitePageDTO;
import junit.framework.TestCase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;


/**
 * class for testing the SitesPageDetailsController.
 * @author Mathivathana 
 *
 */

public class SitesPageDetailsControllerTest extends TestCase {
	
	/**
	   * DispatcherServlet.
	   */
	  private DispatcherServlet servlet;

	  /**
	   * @param name.
	   * @param servlet.
	   */
	  
	  public SitesPageDetailsControllerTest(String name, DispatcherServlet servlet) {
		    super(name);
		    this.servlet = servlet;
		  }

		  /**
		   * Description : Used to Test the values.
		 * @throws IOException 
		 * @throws ServletException 
		   * @throws Exception.
		   * 
		   */
	  
	  public void testGetSitesDetails() throws Exception
	  {
		  System.out.println("Entering inside SitesPageDetailsControllerTest"
			        + " - Method testGetSitesDetails");
			    MockHttpServletRequest request = new MockHttpServletRequest();
			    request.setMethod("GET");
			    request.setRequestURI("/sitesDetailspage/291638");
			    request.setParameter("orderStatus", "UA");

			    MockHttpServletResponse response = new MockHttpServletResponse();
			    servlet.service(request, response);
			   
			    List<SitePageDTO> sitesDetailsList = (List<SitePageDTO>)request.getAttribute("sitePageList");
			    
			    assertEquals(200, response.getStatus());
			    assertNotNull(response.getContentAsString());
			    assertEquals(234368,sitesDetailsList.get(0).getSiteId());
			    assertEquals("Null",sitesDetailsList.get(0).getDcaReferenceId());
			    assertEquals("Secure +",sitesDetailsList.get(0).getResilience());
			    assertEquals("Global",sitesDetailsList.get(0).getPricing());
			    
			    System.out.println("Exiting  SitesPageDetailsControllerTest"
				        + " - Method testGetSitesDetails");
	  }

}
