package com.bt.edca.service;
import com.bt.edca.common.dto.ProductSelectionDTO;
/**
 * Interface .
 * @author Rohit
 *
 */
public interface ProductSelectionService {
	/**
	 * 
	 * @param orderId .
	 * @return .
	 */
  public ProductSelectionDTO getProductSelection(String orderId);
}
