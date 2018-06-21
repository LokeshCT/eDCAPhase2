package com.bt.edca.service.impl;

import com.bt.edca.common.dto.CustomerDetailsDTO;
import com.bt.edca.da.domain.CustomerDetailsDomain;
import com.bt.edca.da.domain.POADomain;
import com.bt.edca.da.domain.SubOrderDomain;
import com.bt.edca.dao.CustomerDetailsDao;
import com.bt.edca.service.CustomerDetailsService;
import com.bt.edca.util.EdcaLogger;
import com.bt.edca.util.HelperMethods;
import com.bt.edca.util.helper.CustomerDetailsType;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation class which copies data to CustomerDetails in domain
 * package.
 * 
 * @author Niranjan das Basa.
 * 
 */
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	/**
	 * logger is to display log messages.
	 */
	private static EdcaLogger logger = EdcaLogger.getLogger(CustomerDetailsServiceImpl.class);

	/**
	 * Reference variable.
	 */
	private CustomerDetailsDao customerDetailsDao;

	/**
	 * Reference variable.
	 */
	private CustomerDetailsDomain customerDetailsDomain;

	/**
	 * 
	 * Description : This methods invokes the getCustomerDetails() from
	 * CustomerDetailsDao with this parameters for getting the
	 * customerDetailsList.
	 * 
	 * @param orderId.
	 * @param customertype.
	 * @return customerDetailsList.
	 */
	public CustomerDetailsDTO getCustomerDetails(String orderId, CustomerDetailsType customertype) {
		CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();

		List<CustomerDetailsDomain> customerDetailsList = customerDetailsDao.getCustomerDetails(orderId, customertype);

		if (!HelperMethods.isNullOrEmpty(customerDetailsList)) {
			for (CustomerDetailsDomain customerDetailsDomain : customerDetailsList) {
				customerDetailsDTO = new CustomerDetailsDTO();
				customerDetailsDTO.setFullLegalCompanyName(customerDetailsDomain.getFullLegalCompanyName());
				customerDetailsDTO.setCustomerName(customerDetailsDomain.getCustomerName());
				customerDetailsDTO.setMasterCustomerId(customerDetailsDomain.getMasterCustomerId());
				customerDetailsDTO.setOrderType(customerDetailsDomain.getOrderType());
				customerDetailsDTO.setDistributorLegalName(customerDetailsDomain.getDistributorLegalName());
				customerDetailsDTO.setExternalSystemName(customerDetailsDomain.getExternalSystemName());
				customerDetailsDTO.setExternalSystemReference(customerDetailsDomain.getExternalSystemReference());
				customerDetailsDTO.setBillingId(customerDetailsDomain.getBillingId());
				customerDetailsDTO.setContractId(customerDetailsDomain.getContractId());
				customerDetailsDTO.setCurrency(customerDetailsDomain.getCurrency());
				customerDetailsDTO.setOpportunityId(customerDetailsDomain.getOpportunityId());
				customerDetailsDTO.setPerformanceReportRequired(customerDetailsDomain.getPerformanceReportRequired());
				customerDetailsDTO.setOrderId(customerDetailsDomain.getOrderId());
				customerDetailsDTO.setCustomerId(customerDetailsDomain.getCustomerId());

			}
		}

		return customerDetailsDTO;

	}

	/**
	 * Description : This methods invokes the getSubOrderType() from
	 * CustomerDetailsDao with this parameters for getting the subOrderType
	 * 
	 * @param orderId.
	 * @return.
	 */
	public List<CustomerDetailsDTO> getSubOrderType(String orderId) {

		List<CustomerDetailsDTO> subOrderList = new ArrayList<CustomerDetailsDTO>();
		List<SubOrderDomain> sublist = customerDetailsDao.getSubDetails(orderId);
		if (!HelperMethods.isNullOrEmpty(sublist)) {
			for (SubOrderDomain customerDetailsDomain : sublist) {
				CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
				customerDetailsDTO.setSubOrderType(customerDetailsDomain.getSubOrderType());
				subOrderList.add(customerDetailsDTO);
			}
		}

		return subOrderList;
	}

	/**
	 * Description : This methods invokes the getPoaReference() from
	 * CustomerDetailsDao with this parameters for getting the poaReference.
	 * 
	 * @param orderId.
	 * @return.
	 */
	public List<CustomerDetailsDTO> getPoaReference(String orderId) {
		@SuppressWarnings("unchecked")
		List<POADomain> poaList = customerDetailsDao.getPoaDetails(orderId);
		List<CustomerDetailsDTO> poaReferencelist = new ArrayList<CustomerDetailsDTO>();
		if (!HelperMethods.isNullOrEmpty(poaList)) {
			for (POADomain customerDetailsDomain : poaList) {
				CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
				customerDetailsDTO.setPoaReference(customerDetailsDomain.getPoaReference());

				poaReferencelist.add(customerDetailsDTO);
			}
		}
		return poaReferencelist;
	}

	/**
	 * Gets the value from CustomerDetailsDao
	 * 
	 * @return customerDetailsDao.
	 */
	public CustomerDetailsDao getCustomerDetailsDao() {
		return customerDetailsDao;
	}

	/**
	 * Sets the value in CustomerDetailsDao
	 * 
	 * @param customerDetailsDao.
	 */
	public void setCustomerDetailsDao(CustomerDetailsDao customerDetailsDao) {
		this.customerDetailsDao = customerDetailsDao;
	}
}
