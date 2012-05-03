/**
 * 
 */
package com.acminds.acuteauto.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.batch.model.Batch;
import com.acminds.acuteauto.exceptions.AcuteAutoApplicationException;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
public class BatchManager {
	private BatchConfigManager cfgMgr;
	private Log logger = LogFactory.getLog(BatchManager.class);
	
	public BatchManager() {
		logger.info("Initializing BatchProcessor");
		cfgMgr = BatchConfigManager.getInstance();
		logger.info("BatchProcessor Initialized");
	}
	
	public boolean startBatch(String batchId) {
		Batch b = cfgMgr.getBatch(batchId);
		try {
			BatchProcessor processor = b.getProcessor();
			logger.info("File to be processed: "+Utils.getUserHome()+b.getFilePath());
			processor.execute(Utils.getUserHome()+b.getFilePath());
		} catch (AcuteAutoApplicationException e) {
			e.printStackTrace();
			logger.info("Batch Process Failed with the following message: "+e.getMessage());
			return false;
		}
		logger.info("Batch Process Successful");
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BatchManager manager = new BatchManager();
		manager.startBatch("featureLoader");
	}

}
