/**
 * 
 */
package com.acminds.acuteauto.batch;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;

import com.acminds.acuteauto.batch.model.Batch;
import com.acminds.acuteauto.exceptions.AcuteAutoRuntimeException;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
public class BatchConfigManager {
	private static BatchConfigManager cfgMgr;
	private static final String BATCH_CONFIG = "cfg/Batch-Config.xml";
	private List<Batch> batches = new ArrayList<Batch>();
	
	public static synchronized BatchConfigManager getInstance() {
		if(cfgMgr == null) {
			cfgMgr = new BatchConfigManager();
		}
		return cfgMgr;
	}
	
	private BatchConfigManager() {
		initialize();
	}
	
	private void initialize() {
		try {
			Digester digester = new Digester();
			digester.push(this);
			digester.setValidating( false );
			digester.addObjectCreate( "batches/batch", Batch.class );
			digester.addSetProperties( "batches/batch", "id", "id" );
			digester.addBeanPropertySetter( "batches/batch/className");
			digester.addBeanPropertySetter( "batches/batch/filePath");
			digester.addSetNext( "batches/batch", "addBatch" );
			digester.parse( Utils.getResource(BATCH_CONFIG) );
		} catch (Exception e) {
			throw new AcuteAutoRuntimeException(e.getMessage(), e);
		}
	}
	
	public void addBatch(Batch batch) {
		batches.add(batch);
	}
	
	public Batch getBatch(String batchId) {
		for(Batch b:batches) {
			if(b.getId().equalsIgnoreCase(batchId))
				return b;
		}
		return null;
	}

}
