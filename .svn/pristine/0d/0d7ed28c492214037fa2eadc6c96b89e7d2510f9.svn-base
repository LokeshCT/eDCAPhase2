package com.bt.edca.web.controller;

import com.bt.edca.common.dto.UserDetailsDTO;

import com.bt.edca.service.UserDetailsService;
import com.bt.edca.web.constants.EdcaWebConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description : This is controller for UserDetails page. When ever request
 * comes to display the ProfileSelection details of a user this will be called
 * first .
 * 
 * @author SATHYA.
 */
@Controller
public class UserDetailsProfileController {

  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * Description : Its a getter for userDetailsService. Returning the object of
   * UserDetailsService .
   * 
   * @return userDetailsService .
   */

  public UserDetailsService getUserDetailsService() {
    return userDetailsService;
  }

  /**
   * Description: Its a setter for setting the value of object
   * userDetailsService .
   * 
   * @param userDetailsService
   *          .
   */
  public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  /**
   * @param nUserId.
   * @return model and view object .
   */
  @RequestMapping(value = { "/userDetails/{NUSERID}" }, method = RequestMethod.GET)
  public ModelAndView getUserDetails(@PathVariable(EdcaWebConstants.NUSERID) String nUserId, ModelMap model1,
      HttpServletRequest request, @ModelAttribute("userDetailsDTO") UserDetailsDTO userDetailsDto) {
    List<UserDetailsDTO> userDetailsDtoList = null;
    UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
    request.setAttribute("userid", nUserId);
    request.setAttribute("userDetailsDtoList", userDetailsDtoList);
    Map<String, Object> model = new HashMap<String, Object>();
    userDetailsDtoList = userDetailsService.getUserDetails(nUserId);
    model.put("userDetailsDtoList", userDetailsDtoList);
    model.put("userDetailsDTO", userDetailsDTO);
    return new ModelAndView("userDetails", model);
  }

  /**
   * Description : This method is used to save theuserProfile as Default one
   * based on the UserId.
   * 
   * @return procedure result.
   */

  @RequestMapping(value = "/userDetails/{PROFILETEAMID}/{NUSERID}", method = RequestMethod.GET)
  public @ResponseBody String makeAsDefault(@PathVariable(EdcaWebConstants.NUSERID) String nUserId,
      @PathVariable(EdcaWebConstants.PROFILETEAMID) String profileteamId, HttpServletRequest request,
      HttpServletResponse response) {
    String success = userDetailsService.saveMakeAsDefaultFlag(nUserId, profileteamId);
    return success;
  }

  /**
   * Description :This method is used to get the productNames based on the user
   * selected profileNames.
   * 
   */

  @RequestMapping(value = "/products/{PROFILETEAMID}", method = RequestMethod.GET, headers = "Accept=application/json")
  public @ResponseBody List<UserDetailsDTO> loadProducts(
      @PathVariable(EdcaWebConstants.PROFILETEAMID) String profileteamId, HttpServletRequest request, Model model,
      @ModelAttribute("userDetailsDTO") UserDetailsDTO userDetailsDTO) {
    List<UserDetailsDTO> productDetailslist = userDetailsService.loadProductNames(profileteamId);
    model.addAttribute("productDetailslist", productDetailslist);
    return productDetailslist;

  }

  /**
   * Description :This method is used to Navigate from profileSelection Details
   * to OrderSearch details when a user click on the Continue button.
   * 
   */
  @RequestMapping(value = "/Continue", method = RequestMethod.POST)
  public String continues(@ModelAttribute("userDetailsDTO") UserDetailsDTO userDetailsDTO, HttpSession session,
      @RequestParam("User") String userId) {
    String profileTeamId = userDetailsDTO.getProfileName();
    String[] teams = profileTeamId.split(EdcaWebConstants.COMMA_DELIMITER);
    String profileId = teams[0];
    String teamId = teams[1];
    String productCatagoryValues = userDetailsDTO.getProductCatagoryName();
    String[] productCategory = productCatagoryValues.split(EdcaWebConstants.COMMA_DELIMITER);
    String productCatagoryName = productCategory[0];
    String productCatagoryId = productCategory[1];
    session.setAttribute("UserId", userId);
    session.setAttribute("profileId", profileId);
    session.setAttribute("teamId", teamId);
    session.setAttribute("productCatagoryName", productCatagoryName);
    session.setAttribute("productCatagoryId", productCatagoryId);

    return "redirect:searchOrder";
  }

}
