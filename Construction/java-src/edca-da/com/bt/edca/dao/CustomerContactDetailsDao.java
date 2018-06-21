package com.bt.edca.dao;


import com.bt.edca.da.domain.CustomerContactDetailsDomain;

import java.util.List;
/**
 * Description:DAO interface for CustomerContactDetails.
 * fetches the orderId from Hibernate mapping file.
 * @author Jagankumar G.
 */
public interface CustomerContactDetailsDao {

  /**
   * declaration of constant that fetches the orderId.
   */
  String GET_CUSTOMER_CONTACT_DETAILS_LIST_BY_ORDER = "getCustomerContactDetailsListByOrderId";

  /**
   * Description: Method is implemented in CustomerContactDertailsDaoImpl.
   * @param orderId.
   * @param contactType.
   * @return List.
   */
  public List<CustomerContactDetailsDomain> getCustomerContactDetails(
      String orderId, String[] contactType);
  
   
  /**
   * declaration of the constant.
   * Constant for the procedure that saves the OrderContactDetails.
   */
  String SAVE_ORDER_CONTACT_DETAILS = "{?= call "
      + "dd_dca_pk.dca_addupdate_order_contact(?,?,?,?,?,?,?,?,?,?)}";
  
  /**
   * declaration of the constant.
   * Constant for the procedure that saves the technicalContactDetails.
   */
  String SAVE_TECHNICAL_CONTACT_DETAILS = "{call DDDCA_SPRING_COMM_CONTACT"
      + ".DDDCA_ADDUPD_CUSTOMER_CONTACT(?,?,?,?,?,?,?,?,?,?)}";

  /**
   * Description:Declaration of the method to save OrderContactDetails.
   * @param customerContactDetailsDomain.
   * @param orderId.
   */
  public void saveOrderContactdetails(CustomerContactDetailsDomain 
      customerContactDetailsDomain,String orderId);
  
  /**
   * Description:Declaration of the method to save Technicalcontactdetails.
   * @param customerContactDetailsDomain.
   * @param orderId.
   */
  public void saveTechnicalContactdetails(CustomerContactDetailsDomain 
      customerContactDetailsDomain,String orderId);
  
}

