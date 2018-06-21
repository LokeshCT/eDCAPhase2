package com.bt.edca.service.impl;

import java.util.List;

import com.bt.edca.common.dto.AdditionalInformationDTO;
import com.bt.edca.da.domain.AdditionalInformationDomain;
import com.bt.edca.dao.AdditionalInformationDao;
import com.bt.edca.service.AdditionalInformationService;
import com.bt.edca.util.HelperMethods;
/**
 * Description :implementation of interface .
 * @author Rohit
 *
 */
public class AdditionalInformationServiceImpl implements 
AdditionalInformationService {
/**
 *Description :object of AdditionalInformationDao  .
 */
  private AdditionalInformationDao additionalInformationDao;
  
/**
 * @param orderId .
 * @return additionalInformationDTO -object of AdditionalInformationDTO .
 */
public AdditionalInformationDTO getAdditionalInformation(String orderId) {   
		
		AdditionalInformationDTO additionalInformationDTO = 
		    new AdditionalInformationDTO();
		List<AdditionalInformationDomain> additionalInformationList = 
		    additionalInformationDao.getAdditonalInformation(orderId);
		
		if (!HelperMethods.isNullOrEmpty(additionalInformationList)) {
		  for (AdditionalInformationDomain additionalInformationDomain
		      : additionalInformationList) {
		  additionalInformationDTO.setCotNotes(
		      additionalInformationDomain.getCotNotes());
	         additionalInformationDTO.setNotes(
	             additionalInformationDomain.getNotes());
	         additionalInformationDTO.setOrderId(
	             additionalInformationDomain.getOrderId());
	         additionalInformationDTO.setAuthorisationOverrideReason(
	             additionalInformationDomain.getAuthorisationOverrideReason());
	         additionalInformationDTO.setOverrideAuthorisation(
	             additionalInformationDomain.getOverrideAuthorisation());
	         additionalInformationDTO.setProductNotes(
	             additionalInformationDomain.getProductNotes());
		}
		
}
		return additionalInformationDTO;
		
	} 
/**
 * Description : This interface will be called when data has to be saved .
 * @param cotNotes .
 * @param notes .
 * @param productNotes .
 * @param override .
 * @param overrideReason .
 * @param orderId .
 */
public void saveAdditionalInformation(String notes ,
    String cotNotes,String productNotes,String override,
    String overrideReason,String orderId){
  int notesTotalLength = notes.length();
  int notesLen1 = notesTotalLength / 2;
  int notesLen2 = notesTotalLength - notesLen1;
  String notes1 = notes.substring(0, notesLen1);
  String notes2 = notes.substring(notesLen1, notesTotalLength);
  
  int cotNotesTotalLength = cotNotes.length();
  int cotNotesLen1 = cotNotesTotalLength / 2;
  int cotNotesLen2 = cotNotesTotalLength - cotNotesLen1;
  String cotNotes1 = cotNotes.substring(0, cotNotesLen1);
  String coNotes2 = cotNotes.substring(cotNotesLen1, cotNotesTotalLength);
  
  int productNotesTotalLength = productNotes.length();
  int productNotesLen1 = productNotesTotalLength / 2;
  int productNotesLen2 = productNotesTotalLength - productNotesLen1;
  String productNotes1 = productNotes.substring(0, productNotesLen1);
  String productNotes2 = productNotes.substring(
      productNotesLen1, productNotesTotalLength);
  
  int reasonTotalLength = overrideReason.length();
  int reasonLen1 = reasonTotalLength / 2;
  int reasonLen2 =  reasonTotalLength - reasonLen1;
  String reason1 = overrideReason.substring(0, reasonLen1);
  String reason2 = overrideReason.substring(reasonLen1, reasonTotalLength);  
      additionalInformationDao.saveAdditionalInformation(
          notes1, notes2, cotNotes1, coNotes2, productNotes1, productNotes2
          , reason1, reason2, orderId, override);
      }
	/**
	 *Description : method for getting the database connection.
	 * @return object for AdditionalInformationDao .
	 */
	public AdditionalInformationDao getAdditionalInformationDao() {
		return additionalInformationDao;
	}
/**
 *Description : setter for AdditionalInformationDao .
 * @param additionalInformationDao .
 */
	public void setAdditionalInformationDao(
	    AdditionalInformationDao additionalInformationDao) {
		this.additionalInformationDao = additionalInformationDao;
	}
}
