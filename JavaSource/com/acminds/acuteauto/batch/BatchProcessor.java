/**
 * 
 */
package com.acminds.acuteauto.batch;

import java.io.File;
import java.io.InputStream;

import com.acminds.acuteauto.exceptions.AcuteAutoApplicationException;

/**
 * @author Mansur
 *
 */
public interface BatchProcessor {

	public void execute(String filePath) throws AcuteAutoApplicationException;
	public void execute(File file) throws AcuteAutoApplicationException;
	public void execute(InputStream inputStream) throws AcuteAutoApplicationException;
	
}
