package com.bt.edca.web.controller.test;

import com.bt.edca.common.dto.UserDetailsDTO;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.ArrayList;
import java.util.List;
/**
 * Description: This is a class for testing the controller .
 * 
 * @author SATHYA.
 *
 */

public class UserDetailsProfileControllerTest extends TestCase {

  /**
   * DispatcherServlet.
   */
  private DispatcherServlet servlet;

  /**
   * @param name.
   * @param servlet.
   */

  public UserDetailsProfileControllerTest(String name, DispatcherServlet servlet) {
    super(name);
    this.servlet = servlet;
  }

  /**
   * Description : Method for testing the the controller .
   * 
   * @throws Exception
   *           .
   */
  public void testGetUserDetails() throws Exception {

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("GET");
    request.setRequestURI("/userDetails/24");
    MockHttpServletResponse response = new MockHttpServletResponse();
    servlet.service(request, response);
    List<UserDetailsDTO> userDetailsDTOList = new ArrayList<UserDetailsDTO>();
    for (UserDetailsDTO userDetailsDTO : userDetailsDTOList) {
      assertEquals("24,38", userDetailsDTO.getTeamId());
      assertEquals("24", userDetailsDTO.getProfileId());
      assertEquals("Keystone Service Delivery - ASIAPAC A End Service Delivery",
          userDetailsDTO.getProfileName());
      assertEquals("T", userDetailsDTO.getDefaultFlag());
      assertEquals("SPRING", userDetailsDTO.getProductCatagoryName());
    }
  }
  
  /**
   * Description : Method for testing the the controller .
   * 
   * @throws Exception
   *           .
   */
    public void testloadProducts() throws Exception {
      MockHttpServletRequest request = new MockHttpServletRequest();
      request.setMethod("GET");
      request.setRequestURI("/products/24,38");
      MockHttpServletResponse response = new MockHttpServletResponse();
      servlet.service(request, response);
      List<UserDetailsDTO> userDetailsDTOList = new ArrayList<UserDetailsDTO>();
      for (UserDetailsDTO userDetailsDTO : userDetailsDTOList) {
        assertEquals("SPRING", userDetailsDTO.getProductCatagoryName());
      }

  }

}
