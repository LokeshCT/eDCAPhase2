package com.bt.edca.dao;

import java.util.List;

/**
 * Description : interface .
 * @author Rohit
 *
 */

public interface ConfigureWicnmpaDao {
  
    static String GET_CONFIGURE_WICNMPA_BY_SERVICE_INSTANCE_ID = 
        "{call DD_DCA_PK.get_all_cpe(?,?)}";
    static String GET_CARD_SLOT_LIST= 
        "getCardSlotList";
    static String GET_PORT_TYPE_LIST = 
        "{call DD_DCA_PK.DCA_GET_CPE_CARD_CONFIG(?,?,?,?)}";
    static String GET_INTERFACE_BY_SERVICE_INSTANCE_ID=
        "getInterfaceByServiceInstanceId";
    static String GET_NO_OF_CKT_=
        "{call DD_DCA_PK.GET_SAVEDNOOFCIRCUITS(?,?,?)}";
    static String GETSITEIDCOUNTRYBYSERVICEINSTANCEID=
        "getSiteIdCountryByServiceInstanceId";
    static String GETEOSBYSERVICEINSTANCEID=
        "getEosByServiceInstanceId";
    static String GETCABLEBYSERVICEINSTANCEID=
        "{call PKG_CPE_SPRING.PR_GET_CPE_CABLES("
        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    static String GETTOTALCABLE=
        "{call DD_DCA_pk.DCA_GET_CPE_CABLE_CONFIG(?,?,?,?,?)}";   
    /**
     * 
     * @param serviceInstanceId .
     * @return .
     */
    public List getConfigureWicnmpa(String serviceInstanceId);
    public List getCardSlot(String serviceInstanceId , String cpeSeqId);
    public List getPortType(String  serviceInstanceId , String cpeSeqId);
    public List getInterface(String serviceInstanceId);
    public List getNoOfCircuits(String serviceInstanceId);
    public List getSiteCountry(String serviceInstanceId);
    public List getEosCheck(String serviceInstanceId);
    public List getCable(String cpeName,String cableType , String cablePort , String interfaceType , 
        String electricalInterface , String country , int satelliteSupport
        , String systemName , int siteId , String orderType ,
        int noOfCircuits , String cpeUsage , int eosCheck);
    public List getTotalCable(String serviceInstanceId,String cpeSeqId,String cableType);
}
