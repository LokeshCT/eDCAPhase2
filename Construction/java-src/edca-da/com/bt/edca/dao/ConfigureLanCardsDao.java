package com.bt.edca.dao;

import com.bt.edca.da.domain.ConfigureLanCardsCountryDomain;

import java.util.List;

public interface ConfigureLanCardsDao {

  String GET_SERVICE_INSTANCE_ID = "{call DD_DCA_PK.GET_ALL_LAN(?,?)}";

  String GET_CABLE_SELECTION_DETAILS = "{call DD_DCA_PK.DCA_GET_LAN_SWITCH_CABLES(?,?,?,?,?)}";

  String GET_CARD_DETAILS = "getLanCardsDetails";

  String GET_CARD_COLUMN_DETAILS = "{call DD_DCA_PK.DCA_GET_LAN_CARD_CONFIG(?,?,?,?)}";

  String GET_LAN_CABLE_DETAILS = "getLanCableDetails";

  String GET_LAN_CABLE_SELECTION_LAN_CABLES = "{"
      + "call pkg_cpe_spring.pr_get_cpe_cables(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

  String GET_OPTICAL_DETAILS = "{call DD_DCA_PK.dca_get_lan_cable_config(?,?,?,?,?)}";

  String SITE_DETAILS = "getSiteDetails";

  /**
   * method that fetches the lanCarddetails.
   * @param serviceInstanceId.
   */
  public List getConfigureLanCards(String serviceInstanceId);

  /**
   * @param serviceInstanceId.
   * @param seqId.
   * @param cableType.
   * @return list of cable types.
   */
  public List getConfigureLanCable(String serviceInstanceId, int seqId, String cableType);

  /**
   * @param serviceInstanceId.
   * @param sequenceId.
   * @return list of CardSelection.
   */
  public List getCardSelection(String serviceInstanceId, int sequenceId);

  /**
   * method to fetch the card column in Cardselection grid
   * @param serviceInstanceId.
   * @param seqId.
   * @return list of card column details.
   */
  public List getCardColumnDetails(String serviceInstanceId, int seqId);

  /**
   * method to fetch cableselection details.
   * @param serviceInstanceId.
   * @param seqId.
   * @return list of cables.
   */
  public List getCableSelection(String serviceInstanceId, int seqId);

  /**
   * method to fetch lanCables.
   * @param cpename.
   * @param cableType.
   * @param cablePort.
   * @param interfaceType.
   * @param electricInterface.
   * @param country.
   * @param satelliteSupport.
   * @param systemName.
   * @param siteId.
   * @param orderType.
   * @param noOfCircuits.
   * @param cpeUsage.
   * @param eosCheck.
   * @return list of lancables.
   */
  public List getLanCableSelectionLanCablesList(String cpename, String cableType, String cablePort,
      String interfaceType, String electricInterface,
      String country, int satelliteSupport, String systemName,
      int siteId, String orderType, int noOfCircuits, String cpeUsage, int eosCheck);

  /**
   * method to populate optical details.
   * @param serviceInstanceId.
   * @param seqId.
   * @param cableType.
   * @return list of opticaldetails.
   */
  public List getOpticalDetails(String serviceInstanceId, int seqId, String cableType);

  /**
   * method to fetch country.
   * @param serviceInstanceId.
   * @return details of country and serviceInstacneId.
   */
  public List<ConfigureLanCardsCountryDomain> getConfigureSiteDetails(String serviceInstanceId);

}
