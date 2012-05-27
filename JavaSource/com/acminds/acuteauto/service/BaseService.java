/**
 * 
 */
package com.acminds.acuteauto.service;

import java.util.Collection;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.BaseDTO;

/**
 * @author Mansur
 *
 */
public class BaseService {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	protected BaseDAO baseDao = BaseDAO.getInstance();
	public BaseDAO getBaseDao() {
		return baseDao;
	}

	public <T>T get(Class<T> clazz, Integer id) {
		return baseDao.get(clazz, id);
	}
	
	public void saveOrUpdate(BaseDTO entity, boolean commit) {
		baseDao.saveOrUpdate(entity, commit);
	}
	
	@SuppressWarnings("rawtypes")
	public void saveOrUpdateAll(Collection entities, boolean commit) {
		baseDao.saveOrUpdateAll(entities, commit);
	}
	
	public void delete(BaseDTO entity, boolean commit) {
		baseDao.delete(entity, commit);
	}
	
	@SuppressWarnings("rawtypes")
	public void deleteAll(Collection entities, boolean commit) {
		baseDao.deleteAll(entities, commit);
	}
	
	public void detach(BaseDTO entity) {
		baseDao.detach(entity);
	}
	
	public boolean isPersistent(BaseDTO entity) {
		return baseDao.isPersistent(entity);
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
		baseDao.commit();
	}
	
	public void beginTxn() {
		baseDao.beginTxn();
	}
	
	public final void rollback()
	{
		baseDao.rollback();
	}
	
	public final void flush()
	{
		baseDao.flush();
	}
	
	public void refresh(BaseDTO entity) {
		baseDao.refresh(entity);
	}
	
	@SuppressWarnings("rawtypes")
	public void refreshAll(Collection entities) {
		baseDao.refreshAll(entities);
	}
	
	public final void clearEntityManager()
	{
		baseDao.clearEntityManager();
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
		return baseDao.createQuery(query);
	}
	
	public <T>TypedQuery<T> createQuery(String query, Class<T> clazz) {
		return baseDao.createQuery(query, clazz);
	}
	
	public Query createNamedQuery(String name) {
		return baseDao.createNamedQuery(name);
	}
	
	public <T>TypedQuery<T> createNamedQuery(String query, Class<T> clazz) {
		return baseDao.createNamedQuery(query, clazz);
	}
	
	public Query createNativeQuery(String query) {
		return baseDao.createNativeQuery(query);
	}
	


}
