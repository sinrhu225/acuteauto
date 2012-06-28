/**
 * 
 */
package com.acminds.acuteauto.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.batch.BatchPersistenceManager;
import com.acminds.acuteauto.ui.WebPersistenceManager;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author MANSUR
 *
 */
public abstract class PersistenceManager {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	public static final String EM_HOLDER = "EMHolder";
	private EntityManagerFactory entityManagerFactory;
	private static PersistenceManager instance;
	
	
	public static PersistenceManager getInstance() {
		if(instance == null) {
			if(WebUtils.isWebRequest()) {
				instance = new WebPersistenceManager();
				instance.logger.info("WebPersistenceManager Instantiated.");
			} else { 
				instance = new BatchPersistenceManager();
				instance.logger.info("BatchPersistenceManager Instantiated.");
			}
		}
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
		logger.info("EntityManagerFactory Created: "+entityManagerFactory.hashCode());
	}
	
	public static EntityManager getEntityManager() {
		return getInstance().getCurrentEntityManager(true);
	}
	
	public static void setEntityManager(EntityManager em) {
		getInstance().setCurrentEntityManager(em);
	}
	
	public static void closeEnityManager() {
		 getInstance().closeCurrentEntityManager();
	}
	
	public abstract EntityManager getCurrentEntityManager(boolean createIfEmpty);
	public abstract void setCurrentEntityManager(EntityManager em);
	public abstract void closeCurrentEntityManager();

}
