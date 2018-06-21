package com.bt.edca.web.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 603118607
 * Description: This Class acts as a Exception Handler advice for all controllers in edca.The exception details
 * are presented to user in a understandable format.
 */
@ControllerAdvice
public class EDCAExceptionControllerAdvice {

  /**
   * Description: This method is used to handle the error raised in controllers and redirect to error page with
   * error details in user understandable format.
   *
   * @param request - the request Object is used to get the URL of the request.
   * @param exception -  the exception object is used to get the details of the exception.
   * @return - return the Model and View.
   */
  @ExceptionHandler(Exception.class)
  public final ModelAndView handleException(final HttpServletRequest request, final Exception exception)
  {

    final ModelAndView modelAndView = new ModelAndView("exception");
    modelAndView.addObject("name", exception.getClass().getSimpleName());
    modelAndView.addObject("message", exception.getMessage());
    modelAndView.addObject("url", request.getRequestURI());
    modelAndView.addObject("errorType", exception.getClass().getSimpleName());
    modelAndView.addObject("detailedError", getStackTrace(exception));
    return modelAndView;
  }

  /**
   * Description: This method reads the stack trace information and convert it to String.
   *
   * @param throwable - used to get the stack trace.
   * @return stack trace will be return as a String object.
   */
  public final String getStackTrace(final Throwable throwable) {

    final StringWriter stringWriter = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(stringWriter);
    throwable.printStackTrace(printWriter);
    final String stackTrace = stringWriter.toString();
    // stackTrace.replace(System.getProperty("line.separator"), "<br/>\n");
    return stackTrace;
  }

}
