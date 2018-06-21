package com.bt.edca.service;

import java.util.List;
import java.util.Map;

/**
 * servicelayer for Configurelancards.
 * @author Jagankumar G.
 *
 */
public interface ConfigureLanCardsService {
  
  public Map<String,List> getConfigureLan(String serviceInstanceId);

}
