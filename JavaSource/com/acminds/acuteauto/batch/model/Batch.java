/**
 * 
 */
package com.acminds.acuteauto.batch.model;

import com.acminds.acuteauto.batch.BatchProcessor;
import com.acminds.acuteauto.exceptions.AcuteAutoRuntimeException;

/**
 * @author Mansur
 *
 */
public class Batch {
	private String id;
	private String className;
	private String filePath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@SuppressWarnings("unchecked")
	public BatchProcessor getProcessor()
	{
		BatchProcessor processor = null;
		try {
			Class<BatchProcessor> mapperClass = (Class<BatchProcessor>) Thread.currentThread().getContextClassLoader().loadClass(getClassName());
			processor = mapperClass.newInstance();
		} catch(Exception e) {
			throw new AcuteAutoRuntimeException("Error while instantiating class: "+getClassName());
		}
		return processor;
	}
}
