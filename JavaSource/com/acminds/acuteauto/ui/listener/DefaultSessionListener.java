/**
 * 
 */
package com.acminds.acuteauto.ui.listener;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
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
		PersistenceManager.getInstance().setExecutionContext(arg0.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		EntityManager em = getEntityManager(arg0.getSession());
		if(em!=null && em.isOpen()) {
			logger.info("Destroying Entity Manager: "+em.hashCode());
			em.close();			
		}
		PersistenceManager.closeEnityManager();
		logger.info("Session destroyed with SessionId: "+arg0.getSession().getId());		
	}
	
	private EntityManager getEntityManager(HttpSession session) {
		return (EntityManager)session.getAttribute(PersistenceManager.EM_HOLDER);		
	}

}
