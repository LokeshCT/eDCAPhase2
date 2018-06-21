package com.bt.edca.dao;

import java.util.List;
/**
 * Description : Interface .
 * @author Rohit
 *
 */

public interface ConfigureAccessTypeDao {
/**
 * Description : Constant declaration for query name .
 */
    static String GET_CONFIGURE_ACCESS_TYPE_LIST_BY_SERVICE_INSTANCE_ID = 
        "getConfigureAccessTypeListByServiceInstanceId";
/**    
 * Description : Constant declaration for query name .
 */
    static String GET_CONFIGURE_DELEGATION_QUES_BY_SERVICE_INSTANCE_ID = 
        "getConfigureDelegationQuesByServiceInstanceId";
    /**
     * Description : Constant declaration for query name .
     */
    String GET_CONFIGURE_DELEGATION_QUES_PROCEDURE =
        "{? = call DDDCA_SPRING_AAF_DELEGATION"
        + ".pr_get_aaf_delegation_ques(?,?,?,?,?,?,?,?,?,?,?)}";
    /**
     * Description : Constant declaration for query name .
     */
    static String GET_AGGREGATED_MULTICAST_BANDWIDTH =
        "getAggregatedMulticastBandwidth";
    /**
     * Description : Constant declaration for query name .
     */
    static String GET_QREF_VALUE_BY_SERVICE_INSTANCE_ID = 
        "getQrefValueByServiceInstanceId";
    
    /**
     * 
     * @param serviceInstanceId .
     * @return configureAccessTypeList .
     */
    public List getConfigureAccessType(String serviceInstanceId);
    /**
     * 
     * @param serviceInstanceId .
     * @return ConfigureAccessDTOList .
     */
    
    public List getConfigureDelegation(String serviceInstanceId);
   /**
    * 
    * @param serviceInstanceId .
    * @return aggregatedMulti .
    */
    public List getAggregatedMulticastValue(String serviceInstanceId);
    /**
     * 
     * @param serviceInstanceId .
     * @return qrefList .
     */
    public List getQrefValue(String serviceInstanceId);
}
