/**
 * 
 */
package com.acminds.acuteauto.ui.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acminds.acuteauto.persistence.PersistenceManager;
import com.acminds.acuteauto.persistence.dto.Privilege;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author MANSUR
 *
 */
public class SecurityFilter implements Filter {
	private static final String UNAUTH_URL = "/home.jsf";

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
		boolean authorized = false;
		if(isURISecure(request.getRequestURI())) {
			authorizedUser = (UserInfo)request.getSession().getAttribute("authorizedUser");
			if(Utils.isEmpty(authorizedUser)) {
				response.sendRedirect(request.getContextPath() + UNAUTH_URL);
				PersistenceManager.closeEnityManager();
			} else {
				List<Privilege> privs = authorizedUser.getRole().getPrivileges();
				for(Privilege priv:privs) {
					if(request.getRequestURI().contains(priv.getTranUri())) {
						authorized = true;
						break;
					}
				}
				if(!authorized) {
					response.sendRedirect(request.getContextPath() + UNAUTH_URL);
					PersistenceManager.closeEnityManager();
				} else
					arg2.doFilter(arg0, arg1);
			}
		} else {
			arg2.doFilter(arg0, arg1);
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

}
