package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.common.dto.OrderContactDTO;
import com.bt.edca.service.CustomerContactDetailsService;
import com.bt.edca.web.constants.EdcaWebConstants;

/**
 * Description: Controller returns to view with model.
 * Implements the method of Service layer.
 * @author Jagankumar G.
 *
 */
@Controller
public class OrderContactDetailsController {

  /**
   * Holds the model for the View.
   */
  private static final String ORDER_CONTACT_DTO = "orderContactDTO";
  /**
   * Constant for JSP orderContactDetails.
   */
  private static final String ORDER_CONTACT_DETAILS = "orderContactDetails";
  /**
   * All the fields on the view are disabled.
   */
  private static final String IS_DISABLED = "isDisabled";
  /**
   * Boolean value to enable or disable.
   */
  private static final String TRUE = "false";
  /**
   * declaration of interface of service layer.
   * 
   * @param CustomerContactDetailsService.
   */
  @Autowired
  private CustomerContactDetailsService customerContactDetailsService;

  /**
   * method from Service layer gets the orderId.
   * 
   * @return getCustomerContactDetailsService.
   */
  public CustomerContactDetailsService getCustomerContactDetailsService() {
    return customerContactDetailsService; 
  }

  /**
   * Derscription:sets the orderId for Servicelayer.
   * @param orderContactDetailsService.
   */
  public void setCustomerContactDetailsService(
      CustomerContactDetailsService customerContactDetailsService) {
    this.customerContactDetailsService = customerContactDetailsService;
  }

  /**
   * Description: Returns model with data.
   * @param  orderId.
   * @View   orderContactDetails model
   * @model  orderContactDTO.
   * @return view with model.
   */
  @RequestMapping(value = { "/ordercontactdetails/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getOrderContactDetails(
      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,HttpSession session) {
    session.setAttribute("orderId", orderId);
    
    OrderContactDTO orderContactDTO;
    Map<String, Object> model = new HashMap<String, Object>();

    orderContactDTO = customerContactDetailsService.getOrderContactType(orderId);

    model.put(ORDER_CONTACT_DTO, orderContactDTO);
    model.put(IS_DISABLED, TRUE);

    return new ModelAndView(ORDER_CONTACT_DETAILS, model);
  }
    /**
   * Description:Method saves the technicalContactDetails.
   * @param orderContactDTO.
   * @param session.
   * @return to the next page OrderContactDetails after saving the details.
   */
  @RequestMapping(value = { "/saveOrderContactDetails"}, method = RequestMethod.GET)
 
  public  @ResponseBody String saveOrderContactDetails(
      @ModelAttribute("orderContactDTO") OrderContactDTO orderContactDTO,HttpSession session) {
    
    ModelAndView model = new ModelAndView("orderContactDetails");
    String orderId =  (String)session.getAttribute("orderId");
    
    List<OrderContactDTO>  orderContactDetails= customerContactDetailsService.saveContactDetails(orderContactDTO,orderId);
    
    String success="Success";
    return success;
  }
  
}
