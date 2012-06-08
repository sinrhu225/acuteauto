/**
 * 
 */
package com.acminds.acuteauto.ui.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.persistence.PersistenceManager;

/**
 * @author Mansur
 *
 */
public class DefaultSessionListener implements HttpSessionListener {
	private Log logger = LogFactory.getLog(DefaultSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		logger.info("Session created with SessionId: "+arg0.getSession().getId());		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		logger.info("Session destroyed. Entity Manager is destroyed");
		PersistenceManager.closeEnityManager();
	}

}
