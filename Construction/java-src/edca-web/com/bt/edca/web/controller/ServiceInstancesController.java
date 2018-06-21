package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.common.dto.ServiceInstancesDTO;
import com.bt.edca.service.ServiceInstancesService;
import com.bt.edca.web.constants.EdcaWebConstants;

@Controller

public class ServiceInstancesController {
  
  @Autowired
  private ServiceInstancesService serviceInstancesService;

  /**
   * Description : Its a getter for ServiceInstancesService. Returning the object of
   * ServiceInstancesService .
   * 
   * @return ServiceInstancesService .
   */
  public ServiceInstancesService getServiceInstancesService() {
    return serviceInstancesService;
  }

  public void setServiceInstancesService(ServiceInstancesService serviceInstancesService) {
    this.serviceInstancesService = serviceInstancesService;
  }


  /**
   * @param siteLocationId.
   * @return model and view object.
   */
  @RequestMapping(value = { "/serviceInstance/{orderId}" }, method = RequestMethod.GET)
  public ModelAndView getServiceInstancesDetails(@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			@RequestParam(EdcaWebConstants.SITE_LOCATION_ID) String siteLocationId,
			  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
      @ModelAttribute("serviceInstancesDTO") ServiceInstancesDTO serviceInstancesDTO) {
    List<ServiceInstancesDTO> serviceInstancesDetailsList;
    Map<String, Object> model = new HashMap<String, Object>();
    serviceInstancesDetailsList = serviceInstancesService.getServiceInstancesDetails(siteLocationId);
    model.put("serviceInstancesDetailsList", serviceInstancesDetailsList);
    model.put("siteId",siteId);
    model.put("siteLocationId",siteLocationId);
    return new ModelAndView("serviceInstances", model);
  }

 

}
