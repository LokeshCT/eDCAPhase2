package com.bt.edca.exception;

/**
 * @author dhanesh.
 *
 */
public class SystemException extends RuntimeException {

  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 4316221964543168378L;

  /**
   * Throwable.
   */
  private Throwable cause = null;

  /**
   * SystemException super.
   */
  public SystemException() {
    super();
  }

  /**
   * @param message.
   * @param cause.
   */
  public SystemException(String message, Throwable cause) {
    super(message, cause);
    this.cause = cause;
  }

  /**
   * super message.
   * @param message.
   */
  public SystemException(String message) {
    super(message);
  }

  /**
   * @param cause.
   */
  public SystemException(Throwable cause) {
    super(cause);
    this.cause = cause;
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

    if (null != super.getMessage()) {
      message = super.getMessage();
    } else {
      if (null != cause) {
        message = cause.toString();
      }
    }
    return message;
  }
}
