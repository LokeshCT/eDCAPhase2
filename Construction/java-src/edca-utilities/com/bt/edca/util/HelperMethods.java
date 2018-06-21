package com.bt.edca.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dhanesh.
 *
 */
public class HelperMethods {
  
  /**
   * logger is to display log messages.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(HelperMethods.class);

  /**
   * @param str.
   * @return.
   */
  public static boolean isNullOrEmpty(String str) {
    if (null == str) {
      return true;
    }

    if (str.length() == 0) {
      return true;
    }

    return false;
  }

  /**
   * @param str.
   * @param ignoreWhitespace.
   * @return.
   */
  public static boolean isNullOrEmpty(String str, boolean ignoreWhitespace) {
    if (ignoreWhitespace) {
      if (null == str || str.length() == 0) {
        return true;
      }
      for (int i = 0; i < str.length(); i++) {
        if (!Character.isWhitespace(str.charAt(i))) {
          return false;
        }
      }
      return true;
    } else {
      return isNullOrEmpty(str);
    }
  }

  /**
   * @param lst.
   * @return.
   */
  public static boolean isNullOrEmpty(List lst) {
    if (null == lst) {
      return true;
    }

    if (lst.isEmpty()) {
      return true;
    }

    return false;
  }

  /**
   * @param map.
   * @return.
   */
  public static boolean isNullOrEmpty(Map map) {
    if (null == map) {
      return true;
    }

    if (map.isEmpty()) {
      return true;
    }

    return false;
  }

  /**
   * @param arr.
   * @return.
   */
  public static boolean isNullOrEmpty(Object[] arr) {
    if (null == arr) {
      return true;
    }

    if (arr.length == 0) {
      return true;
    }

    return false;
  }

  /**
   * @param str.
   * @return.
   */
  public static boolean makeBool(String str) {
    if (null != str && (str.equalsIgnoreCase("y") || str.equalsIgnoreCase("yes"))) {
      return true;
    }

    return false;
  }

  /**
   * @param str.
   * @return.
   */
  public static String ifNullMakeEmpty(String str) {
    if (null == str) {
      return "";
    }

    return str;
  }

  /**
   * @param obj.
   * @return.
   */
  public static String safeToString(Object obj) {
    if (null == obj) {
      return null;
    }

    return obj.toString();
  }

  /**
   * @param str.
   * @return.
   */
  public static Long safeParseLong(String str) {
    if (null == str) {
      return null;
    }

    try {
      return Long.parseLong(str);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

  /**
   * @param str.
   * @return.
   */
  public static Integer safeParseInteger(String str) {
    if (null == str) {
      return null;
    }

    try {
      return Integer.parseInt(str);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

  /**
   * @param siteId.
   * @param setName.
   * @return.
   */
  public static String generateSetIdentifier(Long siteId, String setName) {
    if (null == siteId || isNullOrEmpty(setName)) {
      return null;
    }

    return siteId + "/" + setName;
  }

  /**
   * @param stringArray.
   * @return.
   */
  public static ArrayList<String> stringArrayToList(String[] stringArray) {
    return new ArrayList<String>(Arrays.asList(stringArray));
  }

  /**
   * @param boolVal.
   * @return.
   */
  public static String boolToString(Boolean boolVal) {
    if (boolVal) {
      return "Yes";
    } else {
      return "No";
    }
  }

  /**
   * @param value.
   * @param equalTo.
   * @return.
   */
  public static boolean notNullAndEquals(String value, String equalTo) {
    return (null != value && value.equalsIgnoreCase(equalTo));
  }

  /**
   * @param list.
   * @param valueToAdd.
   */
  public static <T> void addNewValuesOnly(List<T> list, T valueToAdd) {
    addNewValuesOnly(list, Arrays.asList(valueToAdd));
  }

  /**
   * @param list.
   * @param valuesToAdd.
   */
  public static <T> void addNewValuesOnly(List<T> list, List<T> valuesToAdd) {
    if (null == list || null == valuesToAdd) {
      return;
    }

    for (T obj : valuesToAdd) {
      if (!list.contains(obj)) {
        list.add(obj);
      }
    }
  }

  /**
   * @param orderReference.
   * @return.
   */
  public static boolean refStartsWith2Caps(String orderReference) {
    if (HelperMethods.isNullOrEmpty(orderReference) || orderReference.length() < 0) {
      return false;
    }

    try {
      if (Character.isUpperCase(orderReference.charAt(0)) 
          && Character.isUpperCase(orderReference.charAt(1))) {
        return true;
      }
    } catch (Exception e) {
      logger.warn(e.getMessage(), e);
    }

    return false;
  }

  /**
   * @param value1.
   * @param value2.
   * @return.
   */
  public static boolean stringMatch(String value1, String value2) {
    if (null == value1 && null == value2) {
      return true;
    }
    if (null == value1 && null != value2) {
      return false;
    }
    return value1.equalsIgnoreCase(value2);
  }

  /**
   * @param value1.
   * @param value2.
   * @return.
   */
  public static boolean stringMatchWithCase(String value1, String value2) {
    if (null == value1 && null == value2) {
      return true;
    }
    if (null == value1 && null != value2) {
      return false;
    }
    return value1.equals(value2);
  }

  /**
   * @param originalString.
   * @param wordDelimiter.
   * @return.
   */
  public static String toCamelCase(String originalString, String wordDelimiter) {
    if (isNullOrEmpty(originalString)) {
      return "";
    }

    String result = "";
    String[] words = originalString.split(wordDelimiter);

    for (String word : words) {
      result += toProperCase(word);
      result += wordDelimiter;
    }

    return result.substring(0, result.length() - 1);
  }

  /**
   * @param originalString.
   * @param wordDelimiter.
   * @return.
   */
  public static String toFirstCamelCase(String originalString, String wordDelimiter) {
    if (isNullOrEmpty(originalString)) {
      return "";
    }

    String result = "";
    String[] words = originalString.split(wordDelimiter);

    for (int i = 0; i < words.length; i++) {
      if (i == 0) {
        result += toProperCase(words[i]);
        result += wordDelimiter;
      }
    }

    return result.substring(0, result.length() - 1);
  }

  static String toProperCase(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
  }

  /**
   * @param theClass.
   * @param methodName.
   * @param paramTypes.
   * @return.
   */
  public static Method getMethod(Class theClass, String methodName, Class... paramTypes) {
    Method method = null;

    try {
      method = theClass.getDeclaredMethod(methodName, paramTypes);
    } catch (NoSuchMethodException e) {
      logger.warn(
          "Could not get method directly! Please check the supplied method signature"
          + " (i.e. method name and given param types) is correct.",e);

      for (Method currentMethod : theClass.getDeclaredMethods()) {
        if (methodName.equalsIgnoreCase(currentMethod.getName())) {
          method = currentMethod;
          break;
        }
      }
    }

    if (null == method) {
      logger.error("Could not find requested method " + methodName + " in class "
          + " " + theClass.getName());
    }

    return method;
  }

  /**
   * @param value.
   * @param compareSet.
   * @return.
   */
  public static boolean equalsIgnoreCase(String value, Set<String> compareSet) {
    if (null == value) {
      return false;

    }

    for (String string : compareSet) {
      if (value.equalsIgnoreCase(string)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param stringElement.
   * @return.
   */
  public static String makeStringElement(String stringElement) {
    if (stringElement == null) {
      return "";
    }

    return stringElement;
  }

  /**
   * @param clobObj.
   * @return.
   */
  public static String convertClobToString(Clob clobObj) {
    StringBuffer xmlcontents = new StringBuffer();
    if (clobObj == null) {
      return "";
    }
    String tempString;
    BufferedReader bufferRead;
    try {
      bufferRead = new BufferedReader(clobObj.getCharacterStream());
      while ((tempString = bufferRead.readLine()) != null) {
        xmlcontents.append(tempString);
      }
      bufferRead.close();
    } catch (SQLException e) {
      e.printStackTrace();
      return "";
    } catch (IOException e) {
      e.printStackTrace();
    }

    return xmlcontents.toString();
  }
}
