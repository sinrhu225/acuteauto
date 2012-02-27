/**
 * 
 */
package com.acminds.acuteauto.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.acminds.acuteauto.ui.WebPersistenceManager;

/**
 * @author MANSUR
 *
 */
public abstract class PersistenceManager {
	
	private EntityManagerFactory entityManagerFactory;
	private static PersistenceManager instance;
	
	
	private static PersistenceManager getInstance() {
		if(instance == null)
			instance = new WebPersistenceManager();
		return instance;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	protected PersistenceManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("acuteauto");
	}
	
	public static EntityManager getEntityManager() {
		return getInstance().getCurrentEntityManager();
	}
	
	public static void closeEnityManager() {
		 getInstance().closeCurrentEntityManager();
	}
	
	public abstract EntityManager getCurrentEntityManager();
	public abstract void closeCurrentEntityManager();

}
