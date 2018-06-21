package com.bt.edca.web.controller;


import com.bt.edca.common.dto.SitePageDTO;

import com.bt.edca.service.SitePageService;

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
 * Description : Controller Class to return the JSP page using orderId.
 *
 * @author SATHYA.
 *
 */
@Controller
public class SitePageController {

	private static final String SITE_PAGE = "sitePage";

	/**
	 * Service Class for SitePageController.
	 */ 
	@Autowired
	 private SitePageService sitePageService;

	/**
	 * @return sitePageService.
	 */
	public SitePageService getSitePageService() {
		return sitePageService;
	}

	/**
	 * @param sitePageService
	 *            .
	 */
	public void setSitePageService(SitePageService sitePageService) {
		this.sitePageService = sitePageService;
	}

	/**
	 * Description : Based on URL it returns jsp page.
	 *
	 * @param orderId
	 *            .
	 * @return sitePage jsp page.
	 */
	@RequestMapping(value = { "/sitepage/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView getSitePage(@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			@RequestParam(EdcaWebConstants.SITE_ID) String siteId, HttpServletRequest request, HttpSession session,
			@ModelAttribute("sitePage") SitePageDTO sitePage) {
		
		request.setAttribute("siteId", siteId);
		String siteLocationId =(String)session.getAttribute("siteLocationId");
		
		session.setAttribute("orderId", orderId);
		session.setAttribute("siteId", siteId);
		String secure =sitePage.getSecureOverride() ;
		List sitePageRecelience = sitePageService.getResilience(siteId, orderId, secure, request);
		String satelliteAccess = (String) session.getAttribute("satelliteAccess");
		Map<String, Object> model = new HashMap<String, Object>();
		SitePageDTO sitePageDTO = sitePageService.getSitePage(orderId, siteId, siteLocationId);
     	List country = sitePageService.getSiteLocationsDetails(siteId, orderId);
    	long siteDcaIdReference = sitePageService.getSiteDcaId(orderId);
		List<SitePageDTO> siteSpecialBidReference = sitePageService.getSitepageSpecialBid(orderId);

		if (siteDcaIdReference == 0) {

			model.put(EdcaWebConstants.DCA_REFERENCE_ID, null);

		} else {

			model.put(EdcaWebConstants.DCA_REFERENCE_ID, siteDcaIdReference);
		}

		int overridenValue = sitePageService.getOverriden(siteId);
		model.put("country", country);
		model.put(EdcaWebConstants.SPECIAL_BID_REFERENCE, siteSpecialBidReference);
		List<SitePageDTO> priceing = sitePageService.getPrice();
		model.put("priceing", priceing);
		model.put(EdcaWebConstants.SITE_PAGE_DTO, sitePageDTO);
		model.put(EdcaWebConstants.IS_DISABLED, "true");
		model.put("satelliteAccess", satelliteAccess);
		model.put("siteId", siteId);
		model.put("sitePageRecelience", sitePageRecelience);
		model.put("overridenValue", overridenValue);
		return new ModelAndView(SITE_PAGE, model);
	}

	/**
	 * Description : This method is used to save the SitePage Details into DB.
	 *
	 * @param orderId
	 *            .
	 * @return sitePage jsp page.
	 */

	@RequestMapping(value = { "/saveSitePage" }, method = RequestMethod.GET)
	public @ResponseBody String saveData(HttpSession session, HttpServletRequest request,
			@ModelAttribute("SitePageDTO") SitePageDTO sitePageDTO) {

		String orderId = (String) session.getAttribute("orderId");
		String siteId = (String) session.getAttribute("siteId");
		String secure = request.getParameter("override");
		String specialBidreference = sitePageDTO.getSpecialBidReference();
		sitePageService.getResilience(siteId, orderId, secure, request);
		String response = sitePageService.saveSitePageDetails(sitePageDTO, orderId, siteId, specialBidreference);
		return response;
	}

}