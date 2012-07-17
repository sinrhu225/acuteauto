/**
 * 
 */
package com.acminds.acuteauto.ui;

import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import com.acminds.acuteauto.persistence.PersistenceManager;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author MANSUR
 *
 */
public class WebPersistenceManager extends PersistenceManager{
	private ThreadLocal<EntityManager> local = new ThreadLocal<EntityManager>();
	
	@Override
	public EntityManager getCurrentEntityManager(boolean createIfEmpty) {
		if(!createIfEmpty)
			return local.get();
		HttpSession sess = getSession();
		EntityManager em = null;
		if(sess == null)  {
			em = local.get();
			logger.info("Getting EntityManager from Local");
		} else {
			em = (EntityManager)sess.getAttribute(EM_HOLDER);
			logger.log(Level.FINE, "Getting EntityManager from Session: "+sess.getId());
		}
		
		if(em == null || !em.isOpen()) {
			if(sess!=null && em!=null && !em.isOpen()) logger.info("EntityManager: "+em.hashCode()+" was found in Session: "+sess.getId()+", but closed");
			
			em = getEntityManagerFactory().createEntityManager();
			if(sess!=null) {
				sess.setAttribute(EM_HOLDER, em);
			}
			logger.info("Creating EntityManager: "+em.hashCode()+" from Factory: "+getEntityManagerFactory().hashCode());
			local.set(em);
		}
		return em;
	}
	
	@Override
	public void setCurrentEntityManager(EntityManager em) {
		if(!Utils.isEmpty(em)) {
			if(Utils.isEmpty(local.get())) {
				local.set(em);
			}				
		}
	}

	@Override
	public void closeCurrentEntityManager() {
		EntityManager em = local.get();
		if(em != null) {
			if(em.isOpen())
				em.close();
			local.remove();
		}
	}

	@Override
	public void setExecutionContext(Object context) {
		executionContext.set(context);
	}
	
	private HttpSession getSession() {
		return (HttpSession)executionContext.get();
	}

}
