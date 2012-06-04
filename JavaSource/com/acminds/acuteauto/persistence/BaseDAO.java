/**
 * 
 */
package com.acminds.acuteauto.persistence;

import java.util.Collection;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author MANSUR
 *
 */
public class BaseDAO {
	private static BaseDAO dao;
	
	public static synchronized BaseDAO getInstance() {
		if(dao == null)
			dao = new BaseDAO();
		return dao;
	}
	
	public <T>T get(Class<T> clazz, Integer id) {
		return PersistenceManager.getEntityManager().find(clazz, id);
	}
	
	public void saveOrUpdate(BaseDTO entity, boolean commit) {
		beginTxn();
		PersistenceManager.getEntityManager().persist(entity);
		if(commit) commit();
	}
	
	@SuppressWarnings("rawtypes")
	public void saveOrUpdateAll(Collection entities, boolean commit) {
		beginTxn();
		for(Object entity:entities)
			saveOrUpdate((BaseDTO)entity, false);
		if(commit) commit();
	}
	
	public void delete(BaseDTO entity, boolean commit) {
		beginTxn();
		PersistenceManager.getEntityManager().remove(entity);
		if(commit) commit();
	}
	
	@SuppressWarnings("rawtypes")
	public void deleteAll(Collection entities, boolean commit) {
		beginTxn();
		for(Object entity:entities)
			PersistenceManager.getEntityManager().remove(entity);
		if(commit) commit();
	}
	
	public void detach(BaseDTO entity) {
		PersistenceManager.getEntityManager().detach(entity);
	}
	
	public boolean isPersistent(BaseDTO entity) {
		return PersistenceManager.getEntityManager().contains(entity);
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
		if(PersistenceManager.getEntityManager().getTransaction().isActive())
			PersistenceManager.getEntityManager().getTransaction().commit();
	}
	
	public void beginTxn() {
		if(!PersistenceManager.getEntityManager().getTransaction().isActive())
			PersistenceManager.getEntityManager().getTransaction().begin();
	}
	
	public final void rollback()
	{
		clearEntityManager();
		if(PersistenceManager.getEntityManager().getTransaction().isActive())
			PersistenceManager.getEntityManager().getTransaction().rollback();
	}
	
	public final void flush()
	{
		PersistenceManager.getEntityManager().flush();
	}
	
	public void refresh(BaseDTO entity) {
		PersistenceManager.getEntityManager().refresh(entity);
	}
	
	@SuppressWarnings("rawtypes")
	public void refreshAll(Collection entities) {
		for(Object entity:entities)
			refresh((BaseDTO)entity);
	}
	
	public final void clearEntityManager()
	{
		PersistenceManager.getEntityManager().clear();
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
		return PersistenceManager.getEntityManager().createQuery(query);
	}
	
	public <T>TypedQuery<T> createQuery(String query, Class<T> clazz) {
		return PersistenceManager.getEntityManager().createQuery(query, clazz);
	}
	
	public Query createNamedQuery(String name) {
		return PersistenceManager.getEntityManager().createNamedQuery(name);
	}
	
	public <T>TypedQuery<T> createNamedQuery(String query, Class<T> clazz) {
		return PersistenceManager.getEntityManager().createNamedQuery(query, clazz);
	}
	
	public Query createNativeQuery(String query) {
		return PersistenceManager.getEntityManager().createNativeQuery(query);
	}
	
}
