package com.bt.edca.web.interceptor;


import com.bt.edca.util.EdcaLogger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.util.UrlPathHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


/**
 * @author dhanesh.
 *
 */
public class ControllerInterceptor implements MethodInterceptor {

  /**
   * Format of BT_CMPS_SERVICE_M001.
   */
  private static final String BT_CMPS_SERVICE_M001 = "bt_cmps__services_M001";

  /**
   * Format of INPUT_XML.
   */
  private static final String INPUT_XML = "inputXml";

  /**
   * Format of PRINT_XML.
   */
  private static final String PRINT_XML = "print_input_xml";

  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(ControllerInterceptor.class);

  /**
   * UrlPathHelper.
   */
  private UrlPathHelper urlPathHelper;

  /**
   * throws Throwable.
   */
  public Object invoke(MethodInvocation invocation) throws Throwable {
    Object returnValue = null;
    Object[] argumentList = invocation.getArguments();

    logger.info("Controller Interceptor --> ");

    if (argumentList != null) {
      logger.info("argumentList length --> " + argumentList.length);

      for (int i = 0; i < argumentList.length; i = i + 1) {
        logger.info("argumentList values before decoding --> " + argumentList[i]);
      }
    }

    if ((argumentList != null) && (argumentList.length > 0)) {
      // decodeparameters(argumentList);
    }

    if (argumentList != null) {
      for (int i = 0; i < argumentList.length; i = i + 1) {
        logger.info("argumentList values after decoding --> " + argumentList[i]);
      }
    }

    returnValue = invocation.proceed();

    return returnValue;
  }

  private void decodeparameters(Object[] argumentList) {
    logger.info("Inside getHttpHeaderInfo");

    HttpServletRequest httpServletRequest = null;
    String strInputXml = null;
    String finalInputXml = null;
    for (int i = 0; i < argumentList.length; i = i + 1) {
      if (argumentList[i] instanceof HttpServletRequest) {
        httpServletRequest = (HttpServletRequest) argumentList[i];
        logger.info("HttpServletRequest --> " + argumentList[i].toString());
      }
    }

    for (int i = 0; i < argumentList.length; i = i + 1) {
      if (argumentList[i] != null) {
        logger.info("argument class --> " + argumentList[i].getClass());
      }

      if (argumentList[i] instanceof String) {
        logger.info("String type -->" + argumentList[i]);
        argumentList[i] = urlPathHelper.decodeRequestString(httpServletRequest, 
            argumentList[i].toString());
      }
    }
  }

  private String removeExtraNodes(String strInputXml) {
    String finalXml = null;
    try {

      Pattern pattern = Pattern.compile("ns\\d{1,2}:");
      // Create a matcher with an input string
      Matcher matcher = pattern.matcher(strInputXml);
      StringBuffer matcherSb = new StringBuffer();
      boolean result = matcher.find();
      // Loop through and create a new String
      // with the replacements
      while (result) {
        matcher.appendReplacement(matcherSb, "");
        result = matcher.find();
      }
      // Add the last segment of input to
      // the new String
      matcher.appendTail(matcherSb);
      logger.info("matcher inputXml-->" + matcherSb.toString());
      // String output = matcherSb.toString();
      String removeVersion = matcherSb.toString().substring(0, matcherSb.indexOf("?>") + 2);
      String output = matcherSb.toString().replace(removeVersion, "");
      String rootNamespace = output.substring(output.indexOf(" "), output.indexOf(">"));
      finalXml = output.replace(rootNamespace, "");
    } catch (Exception exp) {
      logger.error(exp);
    }
    return finalXml;
  }

  /**
   * This method gets the UrlPathHelper.
   *
   * @return urlPathHelper
   */
  public UrlPathHelper getUrlPathHelper() {
    return urlPathHelper;
  }

  /**
   * This method sets the UrlPathHelper.
   *
   * @param urlPathHelper
   *          urlPathHelper
   */
  public void setUrlPathHelper(UrlPathHelper urlPathHelper) {
    this.urlPathHelper = urlPathHelper;
  }
}
