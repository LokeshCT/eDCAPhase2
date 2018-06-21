package com.bt.edca.util;

import java.lang.reflect.Method;

/**
 * @author dhanesh.
 *
 */
public class LoggerUtils {
  /**
   * Return Separator.
   */
  private static final String RETURN_SEPARATOR = " ";
  /**
   * Prefix.
   */
  private static final String ARG_PREFIX = "(";
  /**
   * Suffix.
   */
  private static final String ARG_SUFFIX = ")";
  /**
   * Param Separator.
   */
  private static final String PARAM_SEPARATOR = ",";

  /**
   * @param method.
   * @return.
   */
  public static String getMethodSignature(Method method) {
    StringBuffer methodSignature = new StringBuffer();

    methodSignature.append(method.getReturnType().getSimpleName());
    methodSignature.append(RETURN_SEPARATOR);
    methodSignature.append(method.getName());
    methodSignature.append(ARG_PREFIX);
    methodSignature.append(getParameterTypeList(method));
    methodSignature.append(ARG_SUFFIX);

    return methodSignature.toString();
  }

  /**
   * @param method.
   * @return.
   */
  public static String getParameterTypeList(Method method) {
    StringBuffer parameterTypeList = new StringBuffer();

    Class<?>[] paramTypes = method.getParameterTypes();

    for (int i = 0; i < paramTypes.length; i++) {
      if (i > 0) {
        parameterTypeList.append(PARAM_SEPARATOR);
      }
      String typeName = paramTypes[i].getName();

      if (typeName.lastIndexOf('.') > 0) {
        typeName = typeName.substring(typeName.lastIndexOf('.') + 1);
      }

      parameterTypeList.append(paramTypes[i].getSimpleName());
    }

    return parameterTypeList.toString();
  }
}
