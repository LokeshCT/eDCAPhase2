package com.bt.edca.web.controller.test;



import com.bt.edca.common.dto.ConfigureAccessDTO;

import junit.framework.TestCase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * class for testing the SitesPageDetailsController.
 * @author Mathivathana 
 *
 */
public class ConfigureAccessControllerTest extends TestCase {
	
	/**
	   * DispatcherServlet.
	   */
	  private DispatcherServlet servlet;

	  /**
	   * @param name.
	   * @param servlet.
	   */
	  
	  public ConfigureAccessControllerTest(String name, DispatcherServlet servlet)
	  {
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
     public void testGetConfigureAccessDetails() throws Exception
     {
    	  System.out.println("Entering inside ConfigureAccessControllerTest"
			        + " - Method testGetConfigureAccessDetails");
			    MockHttpServletRequest request = new MockHttpServletRequest();
			    request.setMethod("GET");
			    request.setRequestURI("/configureAccess/51078");
                request.setParameter("siteLocationId", "48059"); 
                request.setParameter("siteId", "49247");
                request.setParameter("serviceInstanceId", "67617");
                request.setParameter("serviceInstance", "Primary");
               
			    MockHttpServletResponse response = new MockHttpServletResponse();
			    servlet.service(request, response);
			    
			    ConfigureAccessDTO configureAccessDto = new ConfigureAccessDTO();
			    configureAccessDto = (ConfigureAccessDTO) request.getAttribute("configureAccessDTO");
			    assertEquals(200, response.getStatus());
			    assertNotNull(response.getContentAsString());
			    assertEquals("Primary", configureAccessDto.getServiceInstance());
			    assertEquals("Berlin, Kitzingstraﬂe 15-19", configureAccessDto.getGpop());
			    assertEquals("None", configureAccessDto.getDvbpop());
			    assertEquals("Berlin", configureAccessDto.getApop());
			    assertEquals("ENET", configureAccessDto.getAccessSupplier());
			    assertEquals("24*7 in tariff (non chargable)", configureAccessDto.getAccessEnhancedServiceRest());
			    assertEquals("None", configureAccessDto.getAccessEntryPoint());
			    assertEquals(1, configureAccessDto.getBgpCount());	
			    
			    String serviceInst = (String) request.getAttribute("serviceInstance");
			    request.setParameter("serviceInst", serviceInst);
			   
			    String gpop = "Berlin, Kitzingstrabe 15-19";
			    request.setParameter("gpop", gpop);
			  	String gpop_Color = (String) request.getAttribute("gpopColour");
			   
			    assertEquals("invalid constraining of numeric or character data", gpop_Color);
			    assertEquals("None", configureAccessDto.getPortSplBidId());
			    System.out.println("Exiting  ConfigureAccessControllerTest"
				        + " - Method testGetConfigureAccessDetails");

     }

}
