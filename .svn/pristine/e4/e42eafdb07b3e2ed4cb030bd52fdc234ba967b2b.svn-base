package com.bt.edca.web.test;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.DispatcherServlet;

import com.bt.edca.web.controller.test.AdditionalInformationControllerTest;
import com.bt.edca.web.controller.test.CPEConfigureControllerTest;
import com.bt.edca.web.controller.test.ConfigureAccessTypeControllerTest;
import com.bt.edca.web.controller.test.ConfigureCableCardsControllerTest;
import com.bt.edca.web.controller.test.ConfigureOtherDevicesControllerTest;
import com.bt.edca.web.controller.test.ConfigureWicnmpaControllerTest;
import com.bt.edca.web.controller.test.CustomerContactDetailsControllerTest;
import com.bt.edca.web.controller.test.CustomerDetailsControllerTest;
import com.bt.edca.web.controller.test.OrderContactDetailsControllerTest;
import com.bt.edca.web.controller.test.OrderSearchControllerTest;
import com.bt.edca.web.controller.test.ProductSelectionControllerTest;
import com.bt.edca.web.controller.test.ServiceInstancesControllerTest;
import com.bt.edca.web.controller.test.SiteAddressControllerTest;
import com.bt.edca.web.controller.test.SiteContactDetailsControllerTest;
import com.bt.edca.web.controller.test.SiteLocationDetailsTest;
import com.bt.edca.web.controller.test.SiteLocationsControllerTest;
import com.bt.edca.web.controller.test.SitePageControllerTest;
import com.bt.edca.web.controller.test.UserDetailsProfileControllerTest;
import com.bt.edca.web.controller.test.VpnConnectionControllerTest;
import com.bt.edca.web.controller.test.VpnServiceControllerTest;
import com.bt.edca.web.controller.test.VPNServicesDetailsControllerTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author SATHYA.
 *
 */

public class WebTestSuite extends TestSuite {

  static {
    try {
      SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
      builder.bind("JDBC/MDSLD", new SimpleDriverDataSource(new oracle.jdbc.driver.OracleDriver(),
          "jdbc:oracle:thin:@172.31.235.216:1521:COTPLUSLOCAL", "mdsld", "mdsld"));
    } catch (Exception e) {
      System.out.println("Exception in JNDI.................................");

    }
  }
 
  /**
   * DispatcherServlet.
   */
  DispatcherServlet servlet = new DispatcherServlet();

  /**
   * @param name.
   * @throws Exception.
   * 
   */
  public WebTestSuite(String name) throws Exception {
    super(name);

    MockServletConfig servletConfig = new MockServletConfig("edca-spring-restful");
    servletConfig.addInitParameter("contextConfigLocation", "junitContext.xml");
    servlet.init(servletConfig);
  }

  /**
   * @return.
   * 
   * @throws Exception.
   * 
   */

  /**
   * @return.
   * 
   * @throws Exception.
   * 
   */
  public static Test suite() throws Exception {

    final WebTestSuite testSuit = new WebTestSuite("TestSuite");
    testSuit.addTest(new CustomerDetailsControllerTest("testGetCustomerDetails", testSuit.servlet));
    testSuit.addTest(new CustomerDetailsControllerTest("testGetInfrastructureCustomer", testSuit.servlet));
    testSuit.addTest(new UserDetailsProfileControllerTest("testGetUserDetails", testSuit.servlet));
    testSuit.addTest(new SitePageControllerTest("testGetSitePage", testSuit.servlet));
    testSuit.addTest(new AdditionalInformationControllerTest("testGetAdditionalInformation", testSuit.servlet));
    testSuit.addTest(new ProductSelectionControllerTest("testGetProductSelection", testSuit.servlet));
    testSuit.addTest(new SiteContactDetailsControllerTest("testGetSiteContactDetails", testSuit.servlet));
  // testSuit.addTest(new OrderSearchControllerTest("testGetOrderDetails", testSuit.servlet));
   testSuit.addTest(new OrderContactDetailsControllerTest("testGetOrderContactDetails", testSuit.servlet));
   testSuit
        .addTest(new CustomerContactDetailsControllerTest("testGetReutersCustomerContactDetails", testSuit.servlet));
    testSuit
     .addTest(new CustomerContactDetailsControllerTest("testGetCommercialCustomerContactDetails", testSuit.servlet));
   testSuit.addTest(new ConfigureAccessTypeControllerTest("testGetConfigureAccessType", testSuit.servlet));

    testSuit.addTest(new SiteLocationDetailsTest("testGetSiteLocationDetails", testSuit.servlet));
    testSuit.addTest(new SiteAddressControllerTest("testGetSiteAddress", testSuit.servlet));
    testSuit.addTest(new CPEConfigureControllerTest("testGetCPEConfigureDetails", testSuit.servlet));

    testSuit.addTest(new SiteLocationsControllerTest("testGetSiteLocationsDetails", testSuit.servlet));

   testSuit.addTest(new ServiceInstancesControllerTest("testGetServiceInstancesDetails", testSuit.servlet));
   // testSuit.addTest(new ConfigureWicnmpaControllerTest("testGetConfigureWicnmpa", testSuit.servlet));
  /*  testSuit.addTest(new VpnConnectionControllerTest("testGetVpnConnection", testSuit.servlet));
    testSuit.addTest(new VpnServiceControllerTest("testGetVpnService", testSuit.servlet));
    testSuit.addTest(new VPNServicesDetailsControllerTest("testVPNServicesDetails", testSuit.servlet));
    */
   testSuit.addTest(new ConfigureOtherDevicesControllerTest("testGetOtherDetails", testSuit.servlet));
  /* testSuit.addTest(new ConfigureCableCardsControllerTest("testGetCableDetails", testSuit.servlet));*/
    return testSuit;

  }

}
