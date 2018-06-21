package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.bt.edca.service.ConfigureLanSwitchNetScreenService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.web.constants.EdcaWebConstants;

/**
 * The Class ConfigureLanSwitchNetScreenController. Author : Joseph Peter
 */
@Controller
public class ConfigureLanSwitchNetScreenController {

     /** The Constant IS_DISABLED. */
     private static final String IS_DISABLED = "isDisabled";
      /** The Constant CONFIGURE_LAN_NET_SCREEN. */
     private static final String CONFIGURE_LAN_NET_SCREEN =
                                     "configureLanNetScreen";

    /**
     * logger is to display log messages.
    */
      private static EdcaLogger logger = EdcaLogger
              .getLogger(ConfigureLanSwitchNetScreenController.class);

     /** The configure lan switch net screen service. */
     @Autowired
     private ConfigureLanSwitchNetScreenService
            configureLanSwitchNetScreenService;
     


	/**
	 * Gets the configure lan switch net screen service.
	 *
	 * @return the configure lan switch net screen service
	 */
	public ConfigureLanSwitchNetScreenService getConfigureLanSwitchNetScreenService() {
		return configureLanSwitchNetScreenService;
	}



	/**
	 * Sets the configure lan switch net screen service.
	 *
	 * @param configureLanSwitchNetScreenService the new configure lan switch net screen service
	 */
	public void setConfigureLanSwitchNetScreenService(
			ConfigureLanSwitchNetScreenService configureLanSwitchNetScreenService) {
		this.configureLanSwitchNetScreenService = configureLanSwitchNetScreenService;
	}



	/**
	 * Gets the.
	 *
	 * @param serviceInstanceId
	 *            the service instance id
	 * @param cpeSeqId
	 *            the cpe seq id
	 * @param request
	 *            the request
	 * @return the model and view
	 */
	@RequestMapping(value = { "/configureLANNetScreen/{orderId}" }, method = RequestMethod.GET)
	public  ModelAndView get(
			  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE) String serviceInstance,
		      @RequestParam(EdcaWebConstants.SITE_LOCATION_ID) int siteLocationId,
		      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			  @RequestParam(EdcaWebConstants.SITE_ID) String siteId,
			  @RequestParam(EdcaWebConstants.SERVICE_INSTANCE_ID) String serviceInstanceId
			  ,HttpServletRequest request
			) {

		Map<String, Object> model = new HashMap<String, Object>();
		String cpeSeqId="1";
		model = configureLanSwitchNetScreenService.getConfigureLanSwitchNetScreenDetails(serviceInstanceId,cpeSeqId);
		model.put(IS_DISABLED, "true");
		return new ModelAndView(CONFIGURE_LAN_NET_SCREEN, model);
	}

}
