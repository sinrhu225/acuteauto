/**
 * 
 */
package com.acminds.acuteauto.ui;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
public class BaseController {
	
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

}
