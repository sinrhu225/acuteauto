/**
 * 
 */
package com.acminds.acuteauto.ui.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.acminds.acuteauto.persistence.PersistenceManager;

/**
 * @author Mansur
 *
 */
public class DefaultSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Entity Manager is destroyed");
		PersistenceManager.closeEnityManager();
	}

}
