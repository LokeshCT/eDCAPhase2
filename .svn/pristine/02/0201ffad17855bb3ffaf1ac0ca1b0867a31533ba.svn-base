package com.bt.edca.da.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bt.edca.util.EdcaLogger;

/**
 * @author 603118607
 *
 * This filter class is used to open and close the session per request.
 * This will create a session when a request reaches the server and close it
 * when the response is ready to be rendered.
 *
 */
public class OpenSessionInViewFilter extends OncePerRequestFilter {

  /**
   * The logger.
   */
  private static EdcaLogger logger = EdcaLogger.getLogger(OpenSessionInViewFilter.class);

  /**
   * This method filter the incoming request and call the chain of filter.
   *
   * @param request
   *          - servlet request
   * @param response
   *          - servlet response
   * @param filterChain
   *          filterchain object
   *
   * @throws ServletException
   *           the servlet exception
   * @throws IOException
   *           Signals that an I/O exception has occurred.
   */
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    Session session;

    try {
      session = HibernateUtil.getSession();
      logger.info("Session has been created" + session);
      filterChain.doFilter(request, response);
    } finally {
      logger.info("inside Closing the Session");
      HibernateUtil.closeSession();
    }
  }
}
