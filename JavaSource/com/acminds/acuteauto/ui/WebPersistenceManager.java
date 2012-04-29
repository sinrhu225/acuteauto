/**
 * 
 */
package com.acminds.acuteauto.ui;

import javax.persistence.EntityManager;

import com.acminds.acuteauto.persistence.PersistenceManager;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author MANSUR
 *
 */
public class WebPersistenceManager extends PersistenceManager{
	private ThreadLocal<EntityManager> local = new ThreadLocal<EntityManager>();
	
	@Override
	public EntityManager getCurrentEntityManager() {
		EntityManager em = null;
		if(Utils.isEmpty(local.get())) {
			em = getEntityManagerFactory().createEntityManager();
			local.set(em);
		} else {
			em = local.get();
			if(!em.isOpen()) {
				em = getEntityManagerFactory().createEntityManager();
				local.set(em);
			}
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

}
