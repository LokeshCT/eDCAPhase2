package com.bt.edca.service;
import java.util.List;
import java.util.Map;
/**
 * Description : Interface .
 * @author Rohit
 *
 */
public interface ConfigureWicnmpaService {
    /**
     * Description : Map for storing the list values .
     * @param serviceInstanceId .
     * @return .
     */
    public  Map<String, List> getConfigureWicnmpa(String serviceInstanceId);
    /**
     * 
     * @param serviceInstanceId
     * @return .
     */
    public  Map<String, List> getCableCardSelection(String serviceInstanceId);
   
   
}
