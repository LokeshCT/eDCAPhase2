package com.bt.edca.dao;

import java.util.List;

/**
 * 
 * @author Rohit
 *
 */

public interface AdditionalInformationDao {
	/**
	 * Description : query for calling the db .
	 */
	static String GET_ADDITIONAL_INFORMATION_LIST_BY_ORDER = 
	    "getAdditionalInformationListByOrderId";
	/**
	 * Description : query for saving the value in db .
	 */
	static String SAVE_ADDITIONAL_INFORMATION=
	    "{? = call DD_DCA_PK.dca_addupdate_additionalinfo("
	    + "?,?,?,?,?,?,?,?,?,?)}";
	/**
	 * 
	 * @param orderId .
	 * @return .
	 */
	public List getAdditonalInformation(String orderId);
	/**
	 * 
	 * @param notes1 .
	 * @param notes2 .
	 * @param cotNotes1 .
	 * @param coNotes2 .
	 * @param productNotes1 .
	 * @param productNotes2 .
	 * @param reason1 .
	 * @param reason2 .
	 * @param orderId .
	 * @param override .
	 */
	public  void saveAdditionalInformation(String notes1, String notes2,
	    String cotNotes1, String coNotes2, String productNotes1,
	    String productNotes2, String reason1, String reason2,
	    String orderId, String override);
}
