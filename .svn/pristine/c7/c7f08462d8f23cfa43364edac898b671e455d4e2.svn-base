package com.bt.edca.web.controller;

import com.bt.edca.common.dto.SiteContactDetailsDTO;
import com.bt.edca.common.dto.SiteContactTypesDTO;
import com.bt.edca.service.SiteContactDetailsService;
import com.bt.edca.service.constants.EdcaServiceConstants;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description : This is controller for Site Contact Detail page. When ever
 * request comes to display the site contact details of a customer this will be
 * called first .
 * 
 * @author Rohit.
 */

@Controller
public class SiteContactDetailsController {

    /** 
     * Description : Creating object of SiteContactDetailsService to calling.
     * the service method .
     * 
     */

  @Autowired
  private SiteContactDetailsService siteContactDetailsService;

  /**
  * Description : Its a getter for siteContactDetailsService. Returning the
  * object of SiteContactDetailsService .
  * 
  * @return siteContactDetailsService .
  */

	public SiteContactDetailsService getSiteContactDetailsService() {
		return siteContactDetailsService;
	}

	/**
	 * Description: Its a setter for setting the value of object
	 * siteContactDetailsService .
	 * 
	 * @param siteContactDetailsService
	 *            .
	 */
	public void setSiteContactDetailsService(SiteContactDetailsService siteContactDetailsService) {
		this.siteContactDetailsService = siteContactDetailsService;
	}

	/**
	 * @param siteLocationId
	 *            .
	 * @return model and view object .
	 */
	@RequestMapping(value = { "/siteContactDetails/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView getSiteContactDetail(@RequestParam(EdcaWebConstants.SITE_LOCATION_ID) String siteLocationId,
			@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			@RequestParam(EdcaWebConstants.SITE_ID) String siteId, HttpSession session) {

    Map<String, List> siteContactDetailsMap = new HashMap<String, List>();
    
   Map<String, Object> model = new HashMap<String, Object>();
    session.setAttribute("orderId", orderId);
    session.setAttribute("siteLocationId", siteLocationId);

    siteContactDetailsMap = siteContactDetailsService.getSiteContactDetails(siteLocationId);
    SiteContactTypesDTO siteContactTypesDTO = populateSiteContactTypes(siteContactDetailsMap);

    model.put("siteContactTypesDTO", siteContactTypesDTO);
    model.put("isDisabled", "false");

    return new ModelAndView("siteContactDetails", model);

  }

  /**
  * Description:method that has the two lists adding to single DTO.
  * Two lists: PrimarySiteContactDetails and SecondarySiteContactDetails.
  * @param siteContactTypeMap.
  * @return Dto of two lists in Single Dto.
  */
  @SuppressWarnings("unchecked")
  public SiteContactTypesDTO populateSiteContactTypes(Map<String, List> siteContactTypeMap) {
  SiteContactTypesDTO siteContactTypesDTO = new SiteContactTypesDTO();

    for (Map.Entry<String, List> siteContactDetailsMapValue : siteContactTypeMap.entrySet()) {

    if (EdcaServiceConstants.PRIMARYCONTACTTYPELIST
        .equalsIgnoreCase(siteContactDetailsMapValue.getKey())) {
        siteContactTypesDTO.setPrimaryContactTypeList(siteContactDetailsMapValue.getValue());

      } else if (EdcaServiceConstants.SECONDARYCONTACTTYPELIST
          .equalsIgnoreCase(siteContactDetailsMapValue.getKey())) {

        siteContactTypesDTO.setSecondaryContactTypeList(siteContactDetailsMapValue.getValue());

      }

    }

    return siteContactTypesDTO;

  }

  /**
   * Method to save the SiteContactDetails into DB. Details are fetched from.
   * ui after saving.
   * 
   * @param request.
   * @param session.
   * @return Model to the next service.
   */

  @RequestMapping(value = "/saveSiteContactDetails", method = RequestMethod.GET)
  public @ResponseBody String saveSiteContactDetails(@ModelAttribute("siteContactTypesDTO") 
  SiteContactTypesDTO siteContactTypesDTO, HttpServletRequest request) {

    HttpSession session = request.getSession();
    String siteLocationId = (String) session.getAttribute("siteLocationId");

    List<SiteContactDetailsDTO> primaryContactTypeList = 
        siteContactTypesDTO.getPrimaryContactTypeList();
    List<SiteContactDetailsDTO> secondaryContactTypeList = 
        siteContactTypesDTO.getSecondaryContactTypeList();
    
    Map<String, List<SiteContactDetailsDTO>> siteContactTypeMap = 
         new HashMap<String, List<SiteContactDetailsDTO>>();

    siteContactTypeMap.put(EdcaServiceConstants.PRIMARYCONTACTTYPELIST, primaryContactTypeList);
    siteContactTypeMap.put(EdcaServiceConstants.SECONDARYCONTACTTYPELIST, secondaryContactTypeList);

    siteContactDetailsService.saveSiteContactDetails(siteContactTypeMap, siteLocationId);
    
    String success="successs";

    return success;
  }

}
