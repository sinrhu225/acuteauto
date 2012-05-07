/**
 * 
 */
package com.acminds.acuteauto.ui.listener;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

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
	//	log.log(Level.INFO, "ServletRequest destroyed");
	//	((HttpServletRequest)arg0.getServletRequest()).getSession().setAttribute("EMHolder", PersistenceManager.getEntityManager());		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestListener#requestInitialized(javax.servlet.ServletRequestEvent)
	 */
	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		EntityManager em = (EntityManager)((HttpServletRequest)arg0.getServletRequest()).getSession().getAttribute(PersistenceManager.EM_HOLDER);
		if(em!=null) PersistenceManager.setEntityManager(em);		
		log.log(Level.FINE, "Servlet Request Initialized");
	}

}
