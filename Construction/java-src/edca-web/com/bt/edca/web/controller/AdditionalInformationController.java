package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.common.dto.AdditionalInformationDTO;
import com.bt.edca.service.AdditionalInformationService;
import com.bt.edca.web.constants.EdcaWebConstants;


/**
 * @author Rohit.
 *Description :This is controller for AdditionalInformation page.
 *             When ever request comes to display the Additonal 
 *             information of a customer ,
 *             this will be called first.
 */
@Controller
public class AdditionalInformationController {
  /**
   *Description : creating the object for AdditionalInformationService .
   */
  
  @Autowired
  private AdditionalInformationService additionalInformationService;

  /**
   *Description : getter for additionalInformationService.
   * .
   * @return additionalInformationService-returning the object of 
   * AdditionalInformationService 
   */
  public AdditionalInformationService getAdditionalInformationService() {
    return additionalInformationService;
  }
/**
 *Description :Its a setter for setting the value of object additionalInformationService.
 * @param additionalInformationService .
 */
  public void setAdditionalInformationService(
      AdditionalInformationService additionalInformationService) {
    this.additionalInformationService = additionalInformationService;
  }
  /**
   * @param request .
   * @param session .
   * @param orderId -order id value will be passed here.
   * @return ModelAndView.- returning the additionalInformation page
   *  for displaying the value
   */
  @RequestMapping(
      value = { "/additionalInformation/{orderId}" }, 
      method = RequestMethod.GET)
  public  ModelAndView getAdditionalInformation(
      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
      HttpServletRequest request, HttpSession session) {
 
 session.setAttribute("orderId", orderId);  
    AdditionalInformationDTO additionalInformationDTO;
    Map<String, Object> model = new HashMap<String, Object>();

     additionalInformationDTO = 
        additionalInformationService.getAdditionalInformation(orderId);
    model.put("additionalInformationDTO", additionalInformationDTO);
    model.put("isDisabled", "false");

    return new ModelAndView("additionalInformation", model);
  }
  
 /**
  * Description :this mapping will be called for saving the data .
  * @param addsave .
  * @param session .
  * @return ModelAndView .
  */
  
  @RequestMapping(value = { "/saveadditional"}, 
      method = RequestMethod.GET)
public @ResponseBody String saveData(@ModelAttribute("addsave")AdditionalInformationDTO
      addsave,HttpSession session) {
    Map<String, Object> model = new HashMap<String, Object>();
    
 String orderId = (String) session.getAttribute("orderId");
    String notes = addsave.getNotes();
    String cotNotes = addsave.getCotNotes();
    String productNotes = addsave.getProductNotes();
    String override = addsave.getOverrideAuthorisation();
    String overrideReason = addsave.getAuthorisationOverrideReason();
    AdditionalInformationDTO additionalInformationDTO;
    additionalInformationService.saveAdditionalInformation(
        notes , cotNotes , productNotes , override , overrideReason , orderId);
    additionalInformationDTO =  additionalInformationService.
        getAdditionalInformation(orderId);
    model.put("additionalInformationDTO", additionalInformationDTO);
    
    String response="Success";
    return response;
    
  }
  
  
}