package com.bt.edca.service.impl;

import com.bt.edca.common.dto.OrderContactDTO;
import com.bt.edca.da.domain.CustomerContactDetailsDomain;
import com.bt.edca.dao.CustomerContactDetailsDao;
import com.bt.edca.service.CustomerContactDetailsService;
import com.bt.edca.service.constants.EdcaServiceConstants;
import com.bt.edca.util.HelperMethods;
import com.bt.edca.util.helper.CustomerContactDetailsType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Description: Implementation of CustomerDetailsService Interface. Class
 * implements the two methods defined in service layer.
 * 
 * @author Jagankumar G.
 *
 */

public class CustomerContactDetailsServiceImpl implements CustomerContactDetailsService {

  /**
   * Description:Interface CustomerContactDetailsDao. Declaration of reference
   * variable customerContactDetailsDao.
   */
  private CustomerContactDetailsDao customerContactDetailsDao;

  /**
   * Description: Interface CustomerContactDetailsDao. fetches the orderId
   * mapping file with CustomerContactDetailsDomain class.
   * 
   * @return customerContactDetailsDao.
   */
  public CustomerContactDetailsDao getCustomerContactDetailsDao() {
    return customerContactDetailsDao;
  }

  /**
   * Description:Interface CustomerContactDetailsDao. Sets the value from
   * CustomerContactDetailsDao.
   * 
   * @param customerContactDetailsDao.
   */
  public void setCustomerContactDetailsDao(CustomerContactDetailsDao customerContactDetailsDao) {
    this.customerContactDetailsDao = customerContactDetailsDao;
  }

  /**
   * Description: Fetches the contactType based on orderId and returns DTO.
   * 
   * @param orderId.
   * @return OrderContactDTO.
   */

  public OrderContactDTO getOrderContactType(String orderId) {

    OrderContactDTO orderContactDTO = null;

    String[] orderContactTypeArray = new String[1];

    orderContactTypeArray[0] = CustomerContactDetailsType.ORDERCONTACTTYPE.getContactType();

    List<CustomerContactDetailsDomain> customerContactDetailsList = 
        (List<CustomerContactDetailsDomain>) customerContactDetailsDao
        .getCustomerContactDetails(orderId, orderContactTypeArray);
    List<OrderContactDTO> orderDetailsList = new ArrayList<OrderContactDTO>();

    if (!HelperMethods.isNullOrEmpty(customerContactDetailsList)) {
      for (CustomerContactDetailsDomain orderContactDomainDetails : customerContactDetailsList) {

        orderDetailsList.add(convertContactDomainToDTO(orderContactDomainDetails));
        orderContactDTO = (OrderContactDTO) orderDetailsList.get(0);
      }
    }

    if (orderDetailsList.isEmpty()) {
      orderDetailsList.add(new OrderContactDTO());
      orderContactDTO = (OrderContactDTO) orderDetailsList.get(0);
    }
    return orderContactDTO;
  }

  /**
   * Description: reference variable for Map.
   * 
   * @return the map of three lists of contacttypes.
   */

  Map<String, List> customerContactDetailsMap = new HashMap<String, List>();

  /**
   * returns the Map of three ContactDetails based on orderId.
   * 
   * @param orderId.
   * @return Map.
   */
  public Map<String, List> contactDetailsList(String orderId) {

    String[] customerContactTypeArray = getCustomerContactRelatedTypes();
    List<CustomerContactDetailsDomain> customerContactDetailsList = 
        (List<CustomerContactDetailsDomain>) customerContactDetailsDao
        .getCustomerContactDetails(orderId, customerContactTypeArray);

    Map<String, List> customerContactDetailsMap = populateContactTypes(customerContactDetailsList);

    return customerContactDetailsMap;

  }

  /**
   * Description:Fetches three contactTypes into array.
   * 
   * @return customerContactTypeArray.
   */
  private String[] getCustomerContactRelatedTypes() {
    String[] customerContactTypeArray = new String[3];
    customerContactTypeArray[0] = CustomerContactDetailsType.COMMERCIALCONTACTYPE.getContactType();
    customerContactTypeArray[1] = CustomerContactDetailsType.REUTERSCONTACTTYPE.getContactType();
    customerContactTypeArray[2] = CustomerContactDetailsType.TECHNICALCONTACTTYPE.getContactType();
    return customerContactTypeArray;
  }

  /**
   * Description:Checking for the contactType and adding to corresponding list.
   * 
   * @param customerContactDetailsList.
   * @return contactTypeMap.
   */
  private Map<String, List> populateContactTypes(
      List<CustomerContactDetailsDomain> customerContactDetailsList) {
    Map<String, List> contactTypeMap = new HashMap<String, List>();

    List<OrderContactDTO> reutersList = new ArrayList<OrderContactDTO>();
    List<OrderContactDTO> commercialList = new ArrayList<OrderContactDTO>();
    List<OrderContactDTO> technicalDetailsList = new ArrayList<OrderContactDTO>();

    if (!HelperMethods.isNullOrEmpty(customerContactDetailsList)) {
      for (CustomerContactDetailsDomain customerContactDetailsDomain : customerContactDetailsList) {
        if (CustomerContactDetailsType.COMMERCIALCONTACTYPE.getContactType()
            .equalsIgnoreCase(customerContactDetailsDomain.getContactType())) {
          commercialList.add(convertContactDomainToDTO(customerContactDetailsDomain));
        } else if (CustomerContactDetailsType.REUTERSCONTACTTYPE.getContactType()
            .equalsIgnoreCase(customerContactDetailsDomain.getContactType())) {
          reutersList.add(convertContactDomainToDTO(customerContactDetailsDomain));
        } else if (CustomerContactDetailsType.TECHNICALCONTACTTYPE.getContactType()
            .equalsIgnoreCase(customerContactDetailsDomain.getContactType())) {
          technicalDetailsList.add(convertContactDomainToDTO(customerContactDetailsDomain));
        }
      }
    }

    if (technicalDetailsList.isEmpty()) {
      technicalDetailsList.add(new OrderContactDTO());
    }

    contactTypeMap.put(EdcaServiceConstants.REUTERSLIST, reutersList);
    contactTypeMap.put(EdcaServiceConstants.COMMERCIALLIST, commercialList);
    contactTypeMap.put(EdcaServiceConstants.TECHNICALDETAILSLIST, technicalDetailsList);

    return contactTypeMap;
  }

  /**
   * Description: Method fetches the values from CustomerContactDetailsDomain
   * class. sets by OrderContactDTO.
   * 
   * @param orderId.
   * @param customerContactDetailsDomain.
   * @return orderContactDTO.
   */
  public OrderContactDTO convertContactDomainToDTO(
      CustomerContactDetailsDomain customerContactDetailsDomain) {

    OrderContactDTO orderContactDTO = new OrderContactDTO();

    orderContactDTO.setContactId(customerContactDetailsDomain.getContactId());
    orderContactDTO.setFirstName(customerContactDetailsDomain.getFirstName());
    orderContactDTO.setLastName(customerContactDetailsDomain.getLastName());
    orderContactDTO.setEmail(customerContactDetailsDomain.getEmail());
    orderContactDTO.setPhone(customerContactDetailsDomain.getPhone());
    orderContactDTO.setMobilePager(customerContactDetailsDomain.getMobilePager());
    orderContactDTO.setJobTitle(customerContactDetailsDomain.getJobTitle());
    orderContactDTO.setFax(customerContactDetailsDomain.getFax());
    orderContactDTO.setAccountMgrId(customerContactDetailsDomain.getAccountMgrId());
    orderContactDTO.setOrderId(customerContactDetailsDomain.getOrderId());
    orderContactDTO.setAccountmgrEin(customerContactDetailsDomain.getAccountmgrEin());
    orderContactDTO.setSalesRepEin(customerContactDetailsDomain.getSalesRepEin());
    orderContactDTO.setSalesRepClassicId(customerContactDetailsDomain.getSalesRepClassicId());
    orderContactDTO.setCity(customerContactDetailsDomain.getCity());
    orderContactDTO.setPostcode(customerContactDetailsDomain.getPostcode());
    orderContactDTO.setState(customerContactDetailsDomain.getState());
    orderContactDTO.setOrderDesingerEin(customerContactDetailsDomain.getOrderDesingerEin());
    orderContactDTO.setObjId(customerContactDetailsDomain.getObjId());
    orderContactDTO.setFax(customerContactDetailsDomain.getFax());
 

    return orderContactDTO;

  }

  /**
   * description:implementation of the method declared in Customercontactdetailsservice.
   * method sends the OrderContactDetails to DAO which are to be saved.
   */
  public List saveContactDetails(OrderContactDTO orderContactDTO, String orderId) {
	  
	
    
    List<CustomerContactDetailsDomain> saveOrderDetailsList = new ArrayList();

    saveOrderDetailsList.add(convertCustomerOrderContactDetails(orderContactDTO));
    customerContactDetailsDao.saveOrderContactdetails(
        convertCustomerOrderContactDetails(orderContactDTO), orderId);

    return saveOrderDetailsList;
  }

  /**
   * description:implementation of the method declared in Customercontactdetailsservice.
   * method to send send the OrderDetails from controller to dao.
   */
  public List saveTechnicalContactDetails(OrderContactDTO orderContactDTO, String orderId) {

    List<CustomerContactDetailsDomain> savetechnicalDetailsList = 
        new ArrayList<CustomerContactDetailsDomain>();  	
    savetechnicalDetailsList.add(convertCustomerOrderContactDetails(orderContactDTO));
    customerContactDetailsDao.saveTechnicalContactdetails(
        convertCustomerOrderContactDetails(orderContactDTO), orderId);

    return savetechnicalDetailsList;
  }
  
 /**
  * Description:Setting the domain fields from domain fetched from UI.
 * @param orderContactDTO.
 * @return customerContactDetailsDomain.
 */
  public CustomerContactDetailsDomain convertCustomerOrderContactDetails(
OrderContactDTO orderContactDTO){
	
    CustomerContactDetailsDomain customerOrderDetailsDomain = new CustomerContactDetailsDomain();
    customerOrderDetailsDomain.setFirstName(orderContactDTO.getFirstName());
    customerOrderDetailsDomain.setLastName(orderContactDTO.getLastName());
    customerOrderDetailsDomain.setJobTitle(orderContactDTO.getJobTitle());
    customerOrderDetailsDomain.setEmail(orderContactDTO.getEmail());
    customerOrderDetailsDomain.setPhone(orderContactDTO.getPhone());
    customerOrderDetailsDomain.setMobilePager(orderContactDTO.getMobilePager());
    customerOrderDetailsDomain.setContactId(orderContactDTO.getContactId());
    customerOrderDetailsDomain.setAccountMgrId(orderContactDTO.getAccountMgrId());  
    return customerOrderDetailsDomain;
  }
}