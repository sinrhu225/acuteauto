/**
 * 
 */
package com.acminds.acuteauto.batch.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.acminds.acuteauto.batch.BatchProcessor;
import com.acminds.acuteauto.exceptions.AcuteAutoApplicationException;
import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.dto.Feature;
import com.acminds.acuteauto.persistence.dto.FeatureGroup;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
public class FeatureLoader implements BatchProcessor{
	private Hashtable<Integer, FeatureGroup> modelMap = new Hashtable<Integer, FeatureGroup>();
	private BaseDAO dao = new BaseDAO();
	
	@Override
	public void execute(String filePath) throws AcuteAutoApplicationException {
		execute(new File(filePath));
		
	}

	@Override
	public void execute(File file) throws AcuteAutoApplicationException {
		try {
			execute(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new AcuteAutoApplicationException(e.getMessage(), e);
		}		
	}

	@Override
	public void execute(InputStream inputStream) throws AcuteAutoApplicationException {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			for(Row row:sheet) {
				for(Cell cell:row) {
					populateModel(cell);										
				}				
			}
			printBook();
			saveModel();
		} catch (Exception e) {
			throw new AcuteAutoApplicationException(e.getMessage(), e);
		}		
	}
	
	private void populateModel(Cell cell) {
		int colIndex = cell.getColumnIndex();
		FeatureGroup fg = modelMap.get(colIndex);
		if(fg == null) {
			fg = new FeatureGroup();
			fg.setName(cell.getRichStringCellValue().getString());
			modelMap.put(colIndex, fg);
		} else {
			Feature f = new Feature();
			f.setFeatureValue(cell.getRichStringCellValue().getString());
			f.setFeatureGroup(fg);
			fg.getFeatures().add(f);			
		}
	}
	
	private void saveModel() {
		BaseDAO dao = new BaseDAO();
		FeatureGroup mainGroup= getFGByName("Options");//(FeatureGroup) dao.createNamedQuery("getFeatureGrpByName").setParameter("name", "Options").getSingleResult();
		if(mainGroup == null) {
			mainGroup = new FeatureGroup();
			mainGroup.setName("Options");
			dao.save(mainGroup, false);
		}
		for(int i=0; i<modelMap.size(); i++){
			FeatureGroup fg = modelMap.get(i);
			FeatureGroup fgp = getFGByName(fg.getName());
			if(fgp == null) {
				fg.setFeatureGroup(mainGroup);
				dao.save(fg, false);
				dao.saveAll(fg.getFeatures(), false);
			} else {
				for(Feature val:fg.getFeatures()) {
					if(getFeatureByName(val.getFeatureValue(), fgp)==null) {
						fgp.getFeatures().add(val);
						val.setFeatureGroup(fgp);
					}
				}
			//	dao.save(fgp, false);
			 dao.saveAll(fgp.getFeatures(), false);	
			}			
		}
		dao.commit();
	}
	
	private FeatureGroup getFGByName(String name) {
		List<FeatureGroup> l = dao.createNamedQuery("getFeatureGrpByName", FeatureGroup.class).setParameter("name", name).getResultList();
		if(!Utils.isEmpty(l))
			return l.get(0);
		return null;
	}
	
	private Feature getFeatureByName(String name, FeatureGroup fg) {
		for(Feature val:fg.getFeatures()) {
			if(val.getFeatureValue().trim().equalsIgnoreCase(name))
				return val;
		}		
		return null;
	}
	
	private void printBook() {
		for(int i=0; i<modelMap.size(); i++){
			FeatureGroup fg = modelMap.get(i);
			System.out.println("Header: "+fg.getName());
			for(Feature val:fg.getFeatures())
				System.out.println("Values: "+val.getFeatureValue());
		}
	}

}
