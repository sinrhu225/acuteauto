/**
 * 
 */
package com.acminds.acuteauto.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author MANSUR
 *
 */
public class BaseDAO {
	private EntityManager em = PersistenceManager.getEntityManager();
	
	public <T>T get(Class<T> clazz, Integer id) {
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
	 * Queries
	 * 
	 * ************************************************************************
	 * ************************************************************************
	 */

	public Query createQuery(String query) {
		return em.createQuery(query);
	}
	
	public <T>TypedQuery<T> createQuery(String query, Class<T> clazz) {
		return em.createQuery(query, clazz);
	}
	
	public Query createNamedQuery(String name) {
		return em.createNamedQuery(name);
	}
	
	public <T>TypedQuery<T> createNamedQuery(String query, Class<T> clazz) {
		return em.createNamedQuery(query, clazz);
	}
	
	public Query createNativeQuery(String query) {
		return em.createNativeQuery(query);
	}
	
}
