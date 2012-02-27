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
	private static ThreadLocal<EntityManager> local = new ThreadLocal<EntityManager>();
	private EntityManager em;

	@Override
	public EntityManager getCurrentEntityManager() {
		if(Utils.isEmpty(em)) {
			if(Utils.isEmpty(local.get())) {
				em = getEntityManagerFactory().createEntityManager();
				local.set(em);
			} else {
				em = local.get();
			}				
		}
		return em;
	}

	@Override
	public void closeCurrentEntityManager() {
		if(getCurrentEntityManager().isOpen())
			getCurrentEntityManager().close();
	}

}
