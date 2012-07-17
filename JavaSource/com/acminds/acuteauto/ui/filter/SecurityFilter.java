/**
 * 
 */
package com.acminds.acuteauto.ui.filter;

import java.io.IOException;
import java.net.SocketException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.utils.Constants;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author MANSUR
 *
 */
public class SecurityFilter implements Filter {
	private Log logger = LogFactory.getLog(SecurityFilter.class);
	
	private static final String UNAUTH_URL = "/home.jsf";
	private static final String ERROR_URL = "/error.jsf";

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		UserInfo authorizedUser = null;
		if(isImageRequest(request.getRequestURI()) && request.getSession(false)==null)
			return;
		
		//boolean authorized = false;
		try {
			if(isURISecure(request.getRequestURI())) {
				authorizedUser = (UserInfo)request.getSession().getAttribute(Constants.AUTH_USER);
				if(Utils.isEmpty(authorizedUser)) {
					response.sendRedirect(request.getContextPath() + UNAUTH_URL);					
				} else {
					/*List<Privilege> privs = authorizedUser.getRole().getPrivileges();
					for(Privilege priv:privs) {
						if(request.getRequestURI().contains(priv.getTranUri())) {
							authorized = true;
							break;
						}
					}
					if(!authorized) {
						response.sendRedirect(request.getContextPath() + UNAUTH_URL);						
					} else*/
						arg2.doFilter(arg0, arg1);
				}
			} else {
				arg2.doFilter(arg0, arg1);
			}
		} catch(SocketException e) {
			logger.error("Network Issues detected", e);
			response.sendRedirect(request.getContextPath() + ERROR_URL);
		} catch(Throwable e) {
			logger.error("Error occured while trying to serve a request", e);
			//response.sendRedirect(request.getContextPath() + ERROR_URL);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	private boolean isURISecure(String uri){
		return uri.contains("/sec/");
	}
	
	private boolean isImageRequest(String uri) {
		return uri.contains("/img");
	}

}
