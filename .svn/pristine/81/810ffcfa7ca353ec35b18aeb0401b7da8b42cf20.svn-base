package com.bt.edca.util.loggers;

import com.bt.edca.util.EdcaLogger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Class used to capture the Entry and Exit events on any methods along with the time taken in logs.
 * 
 * @author Jeno J
 */
public class EntryExitLoggers {


  /**
   * Format of Entry Logs defined.
   */
  private static final String ENTRY_LOG_FORMAT = ">>> %s";

  /**
   * Format of Exit logs defined.
   */
  private static final String EXIT_LOG_FORMAT = "<<< %s";

  /**
   * Argument Format defined for entry logs.
   */
  private static final String ARGUMENT_FORMAT = "Argument: %3d: %s: %s";

  /**
   * Return variable and format defined.
   */
  private static final String RETURN_FORMAT = "Return: %s: %s";

  /**
   * This is a method configured as around advice using spring AOP config.
   * 
   * @param proceedingJoinPoints
   *          - object contains the Signature,Target and class level details.
   * @return - value returned by the calling method
   * @throws Throwable 
   */
  public final Object edcaAroundAdvice(final ProceedingJoinPoint proceedingJoinPoints) throws Throwable {
    Object returnValue = null;

   
      logBeforeExecution(proceedingJoinPoints);
      Date startTime = new Date();

      returnValue = proceedingJoinPoints.proceed();

      Date endTime = new Date();
      logAfterExecution(proceedingJoinPoints,returnValue, startTime, endTime);

    
    return returnValue;
  }

  /**
   * method used to log the entry logs with parameters.
   * 
   * @param proceedingJoinPoints
   *          - object contains the Signature,Target and class level details.
   */
  public final void logBeforeExecution(final ProceedingJoinPoint proceedingJoinPoints) {

    EdcaLogger logger = EdcaLogger.getLogger(proceedingJoinPoints.getTarget().getClass());

    if (logger.isInfoEnabled()) {
      String entryLog = String.format(ENTRY_LOG_FORMAT, proceedingJoinPoints.getSignature());
      logger.info(entryLog);
    }

    if (logger.isDebugEnabled()) {
      String parameter;
      Object[] args = proceedingJoinPoints.getArgs();

      Method method = ((MethodSignature) proceedingJoinPoints.getSignature()).getMethod();
      Class<?>[] parameterTypes = method.getParameterTypes();

      for (int i = 0; i < args.length; i++) {
        parameter = String.format(ARGUMENT_FORMAT, (i + 1), parameterTypes[i].getSimpleName(),
            args[i]);
        logger.debug(parameter);
      }
    }

  }

  /**
   * method used to log the entry logs with parameters.
   * 
   * @param proceedingJoinPoints
   *          - object contains the Signature,Target and class level details.
   * @param startTime
   *          - date object created before execution
   * @param endTime
   *          - date object created after execution
   */
  public final void logAfterExecution(final ProceedingJoinPoint proceedingJoinPoints,
      Object returnValue,final Date startTime, final Date endTime) {

    EdcaLogger logger = EdcaLogger.getLogger(proceedingJoinPoints.getTarget().getClass());

    if (logger.isDebugEnabled()) {
      Method method = ((MethodSignature) proceedingJoinPoints.getSignature()).getMethod();
      String returnlog = String.format(RETURN_FORMAT, method.getReturnType().getSimpleName(),
          returnValue);
      logger.debug(returnlog);
    }
    Long diffTime = endTime.getTime() - startTime.getTime();
    logger.info("<<< Exiting " + proceedingJoinPoints.getSignature() + "(Time Taken : " + diffTime
        + " ms )");

  }

}
