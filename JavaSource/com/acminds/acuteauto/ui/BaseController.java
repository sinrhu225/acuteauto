/**
 * 
 */
package com.acminds.acuteauto.ui;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.persistence.dto.Client;
import com.acminds.acuteauto.persistence.dto.FeatureGroup;
import com.acminds.acuteauto.persistence.dto.Image;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.service.BaseService;
import com.acminds.acuteauto.utils.Constants;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
public class BaseController {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	protected BaseService baseService = new BaseService();
	protected Client dealer;
	private List<FeatureGroup> allFeatureGroups;
	
	@ManagedProperty(value="#{authorizedUser}")
	protected UserInfo authorizedUser;
	public UserInfo getAuthorizedUser() {
		return authorizedUser;
	}
	public void setAuthorizedUser(UserInfo authorizedUser) {
		this.authorizedUser = authorizedUser;
	}

	private List<SelectItem> years = new ArrayList<SelectItem>();
	private List<SelectItem> prices = new ArrayList<SelectItem>();
	
	public List<SelectItem> getYears(boolean isDefault, String defaultValue) {
		if(Utils.isEmpty(years)) {
			if(isDefault) years.add(WebUtils.getDefaultSelectItem(defaultValue));
			for(int i= 2012; i>1991; i--)
				years.add(new SelectItem(i, String.valueOf(i)));
		}
		return years;
	}
	
	public List<SelectItem> getPrices(boolean isDefault, String defaultValue) {
		if(Utils.isEmpty(prices)) {
			if(isDefault) prices.add(WebUtils.getDefaultSelectItem(defaultValue));	
			for(int i= 3000; i<=15000; i=i+3000) {
				prices.add(new SelectItem(i, String.valueOf(i)));
			}
			for(int i= 20000; i<=50000; i=i+5000) {
				prices.add(new SelectItem(i, String.valueOf(i)));
			}
		}
		return prices;
	}
	
	public List<FeatureGroup> getAllFeatureGroups() {
		if(Utils.isEmpty(allFeatureGroups)) {
			allFeatureGroups = baseService.createNamedQuery("getFeatureGrpByName", FeatureGroup.class).setParameter("name", "OPTIONS").getSingleResult().getFeatureGroups();
		}
		return allFeatureGroups;
	}
	
	public Client getDealer() {
		if(Utils.isEmpty(dealer)) {
			dealer = baseService.createNamedQuery("getDealer", Client.class).getResultList().get(0);			
		}
		return dealer;
	}
		
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> T manageManyToMany(BaseDTO parent, List<T> masterCopy, List<T> workingCopy, String propName) throws Exception {
		List<T> rem = new ArrayList<T>();
		for(T t:masterCopy) {
			if(!workingCopy.contains(t)) {
				rem.add(t);
				((List)PropertyUtils.getProperty(t, propName)).remove(parent);
			}					
		}
		masterCopy.removeAll(rem);
		for(T t:workingCopy) {
			if(!masterCopy.contains(t)) {
				masterCopy.add(t);
				((List)PropertyUtils.getProperty(t, propName)).add(parent);
			}
			baseService.saveOrUpdate((BaseDTO)t, false);
		}
		return null;
	}*/
	
	public String getMaxMemory() {
		return String.valueOf(java.lang.Runtime.getRuntime().maxMemory()/1024/1024)+" MB";
	}
	
	@SuppressWarnings("unchecked")
	protected List<Image> getUploadedImages(HttpServletRequest request) {
		Object obj = request.getSession(false).getAttribute(Constants.UPLOADED_IMAGES);
		if(obj == null)
			request.getSession(false).setAttribute(Constants.UPLOADED_IMAGES, new ArrayList<Image>());
		return (List<Image>) request.getSession(false).getAttribute(Constants.UPLOADED_IMAGES);
	}
	
	protected boolean listLoadedAlready(List<Image> list) {
		for(Image i:list)
			if(i.isPersistent()) return true;
		return false;
	}
}
