package com.bt.edca.service.impl;

import java.util.List;

import com.bt.edca.common.dto.ProductSelectionDTO;
import com.bt.edca.da.domain.AdditionalInformationDomain;
import com.bt.edca.da.domain.ProductSelectionDomain;
import com.bt.edca.dao.ProductSelectionDao;
import com.bt.edca.service.ProductSelectionService;
import com.bt.edca.util.HelperMethods;

/**
 * implementation of interface .
 * 
 * @author Rohit
 *
 */
public class ProductSelectionServiceImpl implements ProductSelectionService {
  /**
   * object of ProductSelectionDao .
   */
  private ProductSelectionDao productSelectionDao;

  /**
   * @param orderId
   *          .
   * @return productSelectionDTO -object of ProductSelectionDTO .
   */
  public ProductSelectionDTO getProductSelection(String orderId) {

    ProductSelectionDTO productSelectionDTO = new ProductSelectionDTO();

    List<ProductSelectionDomain> productSelectionList = productSelectionDao.getProductSelection(orderId);

    if (!HelperMethods.isNullOrEmpty(productSelectionList)) {
      for (ProductSelectionDomain productSelectionDomain : productSelectionList) {

        productSelectionDTO.setProductName(productSelectionDomain.getProductName());
        productSelectionDTO.setCosModel(productSelectionDomain.getCosModel());
        productSelectionDTO.setCosPolicy(productSelectionDomain.getCosPolicy());
        productSelectionDTO.setCustomizationLevel(productSelectionDomain.getCustomizationLevel());
        productSelectionDTO.setOrderId(productSelectionDomain.getOrderId());
      }

    }
    return productSelectionDTO;
  }

  /**
   * method for getting the database connection from DAO .
   * 
   * @return productSelectionDao .
   */
  public ProductSelectionDao getProductSelectionDao() {
    return productSelectionDao;
  }

  /**
   * setter method for ProductSelectionDao .
   * 
   * @param productSelectionDao
   *          .
   */
  public void setProductSelectionDao(ProductSelectionDao productSelectionDao) {
    this.productSelectionDao = productSelectionDao;
  }
}
