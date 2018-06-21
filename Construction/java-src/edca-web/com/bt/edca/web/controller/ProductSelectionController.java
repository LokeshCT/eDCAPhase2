package com.bt.edca.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bt.edca.common.dto.ProductSelectionDTO;
import com.bt.edca.service.ProductSelectionService;
import com.bt.edca.web.constants.EdcaWebConstants;
/**
 * This is controller for ProductInformation page.
 * When ever request comes to display the product selected of a
 * customer this will be called first.
 * @author Rohit
 *
 */
@Controller
public class ProductSelectionController {
	/**
	 * creating the object of ProductSelectionService .
	 */
	@Autowired
	private ProductSelectionService productSelectionService;
	/**	   
	 * Its a getter for productSelectionService.
	 * returning the object of ProductSelectionService .
	 * @return productSelectionService
	 */
	public ProductSelectionService getProductSelectionService() {
		return productSelectionService;
	}
	
	/**
	 * Its a setter for setting the value of object productSelectionService.
	 * @param productSelectionService .
	 */
	public  void setProductSelectionService(
	    ProductSelectionService productSelectionService) {
		this.productSelectionService = productSelectionService;
	}
/**
 * 
 * @param orderId -order id value will be passed here..
 * @return ModelAndView -returning the productSelection
 *  page for displaying the value
 */
	@RequestMapping(
	    value = {"/productSelection/{orderId}"}, method = RequestMethod.GET)
  public ModelAndView getProductSelection(
      @PathVariable(EdcaWebConstants.ORDER_ID) String orderId) {	
			ProductSelectionDTO productSelectionDTO;
    	Map<String, Object> model = new HashMap<String, Object>();
    	    	
    	productSelectionDTO = 
    	    productSelectionService.getProductSelection(orderId);
    	model.put("productSelectionDTO", productSelectionDTO);
    	model.put("isDisabled", "true");
        return new ModelAndView("productSelection", model);
	}
}