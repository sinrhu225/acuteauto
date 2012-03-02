/**
 * 
 */
package com.acminds.acuteauto.batch;

import javax.persistence.EntityManager;

import com.acminds.acuteauto.persistence.PersistenceManager;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
public class BatchPersistenceManager extends PersistenceManager {

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
