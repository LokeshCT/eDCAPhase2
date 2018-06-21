package com.bt.edca.util;

import com.bt.edca.exception.ApplicationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

/**
 * @author dhanesh.
 *
 */
public class EdcaLogger {

  /**
   * logger.
   */
  private Logger logger = null;

  private EdcaLogger(String logName) {
    logger = LogManager.getLogger(logName);
  }

  private EdcaLogger(Class component) {
    logger = LogManager.getLogger(component);
  }

  /**
   * @param logName.
   * @return EdcaLogger(logName).
   */
  public static synchronized EdcaLogger getLogger(String logName) {
    return new EdcaLogger(logName);
  }

  /**
   * @param component.
   * @return EdcaLogger(component).
   */
  public static synchronized EdcaLogger getLogger(Class component) {
    return new EdcaLogger(component);
  }

  /**
   * @param message.
   */
  public void debug(Object message) {
    if (logger.isDebugEnabled()) {
      logger.debug(message);
    }
  }

  /**
   * @param message.
   * @param throwable.
   */
  public void debug(Object message, Throwable throwable) {
    if (logger.isDebugEnabled()) {
      logger.debug(message, throwable);
    }
  }

  /**
   * @param message.
   * @param orderId.
   */
  public void error(Object message, Long orderId) {
    if (orderId != null) {
      logger.error("Order " + orderId + " " + message);
    } else {
      logger.error(message);
    }
  }

  /**
   * @param message.
   * @param throwable.
   * @param orderId.
   */
  public void error(Object message, Throwable throwable, Long orderId) {
    if (orderId != null) {
      logger.error("Order " + orderId + " " + message, throwable);
    } else {
      logger.error(message, throwable);
    }
  }

  /**
   * @param message.
   */
  public void error(Object message) {
    Long myLong = null;
    error(message, myLong);
  }

  /**
   * @param message.
   * @param throwable.
   */
  public void error(Object message, Throwable throwable) {
    error(message, throwable, null);
  }

  /**
   * @param orderId.
   * @param message.
   */
  public void error(Object orderId, Object message) {
    logger.error(message + " :: " + orderId);
  }

  /**
   * @param message.
   * @param throwable.
   * @param orderId.
   */
  public void error(Object message, Throwable throwable, Object orderId) {
    logger.error(message + " :: " + orderId);
    logger.error(throwable);
  }

  /**
   * @param message.
   */
  public void fatal(Object message) {
    logger.fatal(message);
  }

  /**
   * @param message.
   * @param throwable.
   */
  public void fatal(Object message, Throwable throwable) {
    logger.fatal(message, throwable);
  }

  /**
   * @param message.
   */
  public void info(Object message) {
    if (logger.isInfoEnabled()) {
      logger.info(message);
    }
  }

  /**
   * @param message.
   * @param throwable.
   */
  public void info(Object message, Throwable throwable) {
    if (logger.isInfoEnabled()) {
      logger.info(message, throwable);
    }
  }

  /**
   * @param message.
   */
  public void warn(Object message) {
    logger.warn(message);
  }

  /**
   * @param message.
   * @param throwable.
   */
  public void warn(Object message, Throwable throwable) {
    logger.warn(message, throwable);
  }

  /**
   * @return isDebugEnabled.
   */
  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }

  /**
   * @return isInfoEnabled.
   */
  public boolean isInfoEnabled() {
    return logger.isInfoEnabled();
  }

  /**
   * @param condition.
   * @param conditionFailureMessage.
   */
  public void assertion(boolean condition, String conditionFailureMessage) {
    if (!condition) {
      logger.info("Assertion Failure: " + conditionFailureMessage);

      if (isDebugEnabled()) {
        throw new ApplicationException("Debug Assertion Failure causing Fatal Error:"
            + " " + conditionFailureMessage);
      }
    }
  }

  /*
   * public void setLogLevel(String level) {
   * logger.setLevel(Level.toLevel(level));
   * 
   * }
   */

  /**
   * @return getLevel.
   */
  public String getLogLevel() {
    return logger.getLevel().toString();
  }

  /**
   * @return RootLogger.
   */
  public Logger getRootLogger() {
    return LogManager.getRootLogger();
  }

  /**
   * @param method.
   * @param args.
   */
  public void enter(Method method, Object... args) {
    if (logger.isInfoEnabled()) {
      try {
        logger.info(">>> " + LoggerUtils.getMethodSignature(method));

        arguments(args);
      } catch (Exception e) {
        logger.error("enter logging failed", e);
      }
    }
  }

  /**
   * @param method.
   */
  public void exit(Method method) {
    if (logger.isInfoEnabled()) {
      try {
        logger.info("<<< " + LoggerUtils.getMethodSignature(method));
      } catch (Exception e) {
        logger.error("exit logging failed", e);
      }
    }
  }

  private void arguments(Object... args) {
    if (logger.isDebugEnabled()) {
      try {
        for (int a = 0; a < args.length; a++) {
          if (null != args[a]) {
            String className = args[a].getClass().getName();
            logger.debug("Argument " + (a + 1) + ": " + className + ": " + args[a]);
          } else {
            logger.debug("Argument " + (a + 1) + ": null");
          }
        }
      } catch (Exception e) {
        logger.error("arguments logging failed", e);
      }
    }
  }

  /**
   * @param variableName.
   * @param value.
   */
  public void variable(String variableName, Object value) {
    if (logger.isDebugEnabled()) {
      try {
        if (null != value) {
          String className = value.getClass().getName();
          logger.debug("Variable " + variableName + ": " + className + ": " + value);
        } else {
          logger.debug("Variable " + variableName + ": null");
        }
      } catch (Exception e) {
        logger.error("variable logging failed", e);
      }
    }
  }

  /**
   * @param obj.
   */
  public void safeInfo(Object obj) {
    if (null != obj && logger.isInfoEnabled()) {
      try {
        logger.info(obj);
      } catch (Exception e) {
        logger.error("info logging failed", e);
      }
    }
  }

  /**
   * @param obj.
   */
  public void safeDebug(Object obj) {
    if (null != obj && logger.isDebugEnabled()) {
      try {
        logger.debug(obj);
      } catch (Exception e) {
        logger.error("debug logging failed", e);
      }
    }
  }
}
