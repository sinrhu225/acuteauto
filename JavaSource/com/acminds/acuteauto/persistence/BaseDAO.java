/**
 * 
 */
package com.acminds.acuteauto.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author MANSUR
 *
 */
public class BaseDAO {
	private EntityManager em = PersistenceManager.getEntityManager();
	
	public BaseDTO get(Class<BaseDTO> clazz, Integer id) {
		return em.find(clazz, id);
	}
	
	public void save(BaseDTO entity, boolean commit) {
		beginTxn();
		em.persist(entity);
		if(commit) commit();
	}
	
	@SuppressWarnings("rawtypes")
	public void saveAll(Collection entities, boolean commit) {
		beginTxn();
		for(Object entity:entities)
			em.persist(entity);
		if(commit) commit();
	}
	
	public void delete(BaseDTO entity, boolean commit) {
		beginTxn();
		em.remove(entity);
		if(commit) commit();
	}
	
	public void deleteAll(List<BaseDTO> entities, boolean commit) {
		beginTxn();
		for(BaseDTO entity:entities)
			em.remove(entity);
		if(commit) commit();
	}
	
	public void detach(BaseDTO entity) {
		em.detach(entity);
	}
	
	
	/*
	 * ************************************************************************
	 * ************************************************************************
	 * 
	 * Transaction management: begin, commit, rollback, flush, clear
	 * 
	 * ************************************************************************
	 * ************************************************************************
	 */
	
	
	public void commit() {
		if(em.getTransaction().isActive())
			em.getTransaction().commit();
	}
	
	public void beginTxn() {
		if(!em.getTransaction().isActive())
			em.getTransaction().begin();
	}
	
	public final void rollback()
	{
		clearEntityManager();
		if(em.getTransaction().isActive())
			em.getTransaction().rollback();
	}
	
	public final void flush()
	{
		em.flush();
	}
	
	public final void clearEntityManager()
	{
		em.clear();
	}
	
	/*
	 * ************************************************************************
	 * ************************************************************************
	 * 
	 * Named Queries
	 * 
	 * ************************************************************************
	 * ************************************************************************
	 */

	public Query createNamedQuery(String name) {
		return em.createNamedQuery(name);
	}
	
	public Query createNativeQuery(String query) {
		return em.createNativeQuery(query);
	}
	
}
