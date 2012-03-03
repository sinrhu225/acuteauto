/**
 * 
 */
package com.acminds.acuteauto.ui.listener;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import com.acminds.acuteauto.persistence.PersistenceManager;

/**
 * @author MANSUR
 *
 */
public class DefaultRequestListener implements ServletRequestListener {
	private Logger log = Logger.getLogger(getClass().getName());

	/**
	 * 
	 */
	public DefaultRequestListener() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.ServletRequestEvent)
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		log.log(Level.INFO, "Closing Entity Manager on ServletRequest destroyed");
		PersistenceManager.closeEnityManager();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestListener#requestInitialized(javax.servlet.ServletRequestEvent)
	 */
	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		log.log(Level.FINE, "Servlet Request Initialized");
	}

}
