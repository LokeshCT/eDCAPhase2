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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.common.dto.SitePageDTO;
import com.bt.edca.service.SitesPageDetailsService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.web.constants.EdcaWebConstants;

@Controller
public class SitesPageDetailsController {

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(SitesPageDetailsController.class);

  /**
   * Service Class for SitePageController.
   */
  @Autowired
  private SitesPageDetailsService sitesPageDetailsService;

  /**
   * @return the sitesPageDetailsService
   */
  public SitesPageDetailsService getSitesPageDetailsService() {
    return sitesPageDetailsService;
  }

  /**
   * @param sitesPageDetailsService
   *          the sitesPageDetailsService to set
   */
  public void setSitesPageDetailsService(SitesPageDetailsService sitesPageDetailsService) {
    this.sitesPageDetailsService = sitesPageDetailsService;
  }

  /**
   * Description : Based on URL it returns jsp page.
   * 
   * @param orderId.
   * @return sitePage jsp page.
   */
  @RequestMapping(value = { "/sitesDetailspage/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getsitesDetailsPage(@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
      @RequestParam(EdcaWebConstants.ORDER_STATUS_CODE) String orderStatus,
      @ModelAttribute("sitePage") SitePageDTO sitePage,HttpSession session) {

    SitePageDTO sitePageDTO = new SitePageDTO();

    int resilience = sitesPageDetailsService.getResilienceCount(orderId, EdcaWebConstants.RESILIENCE);

    String satelliteAccess = sitesPageDetailsService.getSatelliteAccess(orderId);
   
    session.setAttribute("satelliteAccess",satelliteAccess);
    
    String orderType = sitesPageDetailsService.getOrderType(orderId);

    List sitesList = sitesPageDetailsService.getSitesDetails(orderId, resilience, satelliteAccess, orderType,
        orderStatus);

    Map<String, Object> model = new HashMap<String, Object>();
    if (!(resilience > 1 || satelliteAccess == "YES")) {
      model.put("hideServiceInstance", "hideServiceInstance");
    }

    model.put("resilience", resilience);
    model.put("sitePageList", sitesList);
    return new ModelAndView("sitesDetailspage", model);

  }
}
