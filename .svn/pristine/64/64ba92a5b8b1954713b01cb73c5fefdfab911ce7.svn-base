package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.bt.edca.common.dto.CPEOrderingDTO;
import com.bt.edca.service.CPEOrderingService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.web.constants.EdcaWebConstants;

/**
 * Controller Class to return the JSP page using orderId.
 * 
 * @author Joseph.
 */
@Controller
public class CPEOrderingController {

	/**
	 * Format of Disabled.
	 */
	private static final String IS_DISABLED = "isDisabled";

	/**
	 * Format of CustomerDetailsDTO.
	 */
	private static final String CPE_ORDERING_DTO = "cpeOrderingDTO";
	
	private static final String CPE_ORDERING_DETAILS = "cpeOrderingDetails"; 
	/**
	 * logger is to display log messages.
	 */
	private static EdcaLogger logger = EdcaLogger
			.getLogger(CPEOrderingController.class);

	/**
	 * Service Class for CustomerDetailsController.
	 */
	@Autowired
	private CPEOrderingService cpeOrderingService;

	/**
	 * @return cpeOrderingService.
	 */
	public CPEOrderingService getCpeOrderingService() {
		return cpeOrderingService;
	}

	/**
	 * @param cpeOrderingService
	 *            .
	 */
	public void setCpeOrderingService(CPEOrderingService cpeOrderingService) {
		this.cpeOrderingService = cpeOrderingService;
	}

	/**
	 * Based on URL it returns jsp page.
	 * 
	 * @param orderId
	 *            .
	 * @return CpeOrderingDetails model back to jsp.
	 */
	@RequestMapping(value = { "/cpeOrderingDetails/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView getCPEOrderingDetails(@PathVariable(EdcaWebConstants.ORDER_ID) String orderId,
			@RequestParam(EdcaWebConstants.SITE_LOCATION_ID) String siteLocationId,
			  @RequestParam(EdcaWebConstants.SITE_ID) String siteId) {

		CPEOrderingDTO cpeOrderingDTO;
		Map<String, Object> model = new HashMap<String, Object>();

		cpeOrderingDTO = cpeOrderingService
				.getCPEOrderingDetails(orderId, null);

		model.put(CPE_ORDERING_DTO, cpeOrderingDTO);
		model.put(IS_DISABLED, "true");

		return new ModelAndView(CPE_ORDERING_DETAILS, model);
	}
}
