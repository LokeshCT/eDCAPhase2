package com.bt.edca.web.controller;

import com.bt.edca.common.dto.SiteAddressDTO;
import com.bt.edca.service.SiteAddressService;
import com.bt.edca.web.constants.EdcaWebConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import javax.servlet.http.HttpSession;

/**
 * Description: Controller returns to view with model.
 * 
 * @author ROHIT
 */
@Controller
public class SiteAddressController {
  
  /**
   * Constant for JSP of SiteAddress.
   */
  private static final String SITE_ADDRESS = "siteAddress";
  /**
   * Holds the model for the View.
   */
  private static final String SITE_ADDRESS_DTO = "siteAddressDTO";
  /**
   * All the fields on the view are disabled.
   */
  private static final String IS_DISABLED = "isDisabled";
  /**
   * Boolean value to enable or disable.
   */
  private static final String TRUE = "true";
  /**
   * @param siteAddressService
   *          declaration..
   * 
   */
  @Autowired
  private SiteAddressService siteAddressService;

  /**
   * Getter for SiteAddressService.
   * 
   * @return getSiteAddressService.
   */
  public SiteAddressService getSiteLocationDetailsService() {
    return siteAddressService;
  }

  /**
   * Setter for SiteAddressService.
   * 
   * @param siteAddressService.
   */
  public void setSiteAddressService(SiteAddressService siteAddressService) {
    this.siteAddressService = siteAddressService;
  }

  /**
   * Description:Method returns to view with model based on sitelocationId.
   * 
   * @param siteLocationId.
   * @param orderId .
   * @View siteAddressDetails
   * @model siteAddresssDTO.
   * @return view with model .
   */
  @RequestMapping(value = { "/siteaddress/{orderId}" },
      method = RequestMethod.GET)
  public ModelAndView getSiteAddress( 
      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) String siteLocationId,
	      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
		  @RequestParam(EdcaWebConstants.SITE_ID) String siteId, HttpServletRequest req, HttpSession session
		  ) {
    session.setAttribute("siteLocationId", siteLocationId);
    SiteAddressDTO siteAddressDTO;
    List siteCountry;
    Map<String, Object> model = new HashMap<String, Object>();

    siteAddressDTO = siteAddressService.getSiteDetails(siteLocationId);
    siteCountry = siteAddressService.getCountryName(siteLocationId);
    int size = siteCountry.size();     
    model.put("siteCountry", siteCountry);
    model.put("size", size);
    model.put(SITE_ADDRESS_DTO, siteAddressDTO);
    model.put(IS_DISABLED, "false");
    return new ModelAndView(SITE_ADDRESS, model);
  }

  /**
   *  Description:Method for saving the data in edit mode .
   * @param addsave .
   * @param req .
   * @param session .
   * @return .
   */
  @RequestMapping(value = { "/saveSiteAddress" }, method = RequestMethod.GET)
  public @ResponseBody String saveAddress(
      @ModelAttribute("addsave")SiteAddressDTO addsave ,
      HttpServletRequest req, HttpSession session) {
    
    SiteAddressDTO siteAddressDTO;
    List siteCountry;
    Map<String, Object> model = new HashMap<String, Object>();
    String siteLocationId =  (String) session.getAttribute("siteLocationId");
    siteCountry = siteAddressService.getCountryName(siteLocationId);  
    int size = siteCountry.size(); 
    String floor = addsave.getFloor();
    String room = addsave.getRoom();
    String buildingNumber = addsave.getBuildingNumber();
    String addressLine1 = addsave.getAddressLine1();
    String addressLine2 = addsave.getAddressLine2();
    String city = addsave.getCity();
    String postcode = addsave.getPostcode();
    String state = addsave.getState();
    siteAddressService.saveAddress(
        siteLocationId, floor, room, buildingNumber, 
        addressLine1, addressLine2, city, postcode, state);
    siteAddressDTO = siteAddressService.getSiteDetails(siteLocationId);
    model.put(SITE_ADDRESS_DTO, siteAddressDTO);
    model.put("siteCountry", siteCountry);
    model.put("size", size);
    model.put(IS_DISABLED, "false");
   
    String response="Success";
    return response;
  }
}