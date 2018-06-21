package com.bt.edca.exception;

/**
 * @author dhanesh.
 *
 */
public class ApplicationException extends RuntimeException {

  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = -3096345334052322389L;

  /**
   * Throwable.
   */
  private Throwable cause;

  /**
   * Declaration of errorCode.
   */
  private String errorCode;

  /**
   * Declaration of errorMessage.
   */
  private String errorMessage;

  /**
   * @param msg.
   */
  public ApplicationException(String msg) {
    super(msg);
    this.errorCode = msg;
    this.errorMessage = msg;
  }

  /**
   * super msg.
   * @param msg.
   * @param cause.
   */
  public ApplicationException(String msg, Throwable cause) {
    super(msg);
    this.cause = cause;
  }

  /**
   * @param errorCode.
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * Gets the ErrorCode.
   * @return errorCode.
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * Gets the Cause.
   * @return cause.
   */
  public Throwable getCause() {
    return cause;
  }

  /** 
   * Gets the Message.
   * @return message.
   */
  public String getMessage() {
    String message = null;

    if (null == cause) {
      message = super.getMessage();
    } else {
      message = super.getMessage() + " (see below for lower-level details)";
    }

    return message;
  }

  /**
   * @param errorMessage.
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * Gets the ErrorMessage.
   * @return errorMessage.
   */
  public String getErrorMessage() {
    return errorMessage;
  }
}
