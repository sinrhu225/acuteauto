/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import com.acminds.acuteauto.persistence.dto.Privilege;
import com.acminds.acuteauto.persistence.dto.Role;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ViewScoped
@ManagedBean(name="rlCtrl")
public class RoleController extends BaseController {
	
	private List<Role> roles;
	private List<Privilege> privs;
	private Role role;
	private Privilege priv;
	
	public List<Role> getRoles() {
		if(Utils.isEmpty(roles))
			roles = baseService.createNamedQuery("getRoles", Role.class).getResultList();
		return roles;
	}
	public List<Privilege> getPrivs() {
		if(Utils.isEmpty(privs))
			privs = baseService.createNamedQuery("getPrivileges", Privilege.class).getResultList();
		return privs;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		if(role == null && (this.role!=null && this.role.getRoleId()==null))
			;// do nothing.		
		else
			this.role = role;
	}
	public Privilege getPriv() {
		return priv;
	}
	public void setPriv(Privilege priv) {
		if(priv == null && (this.priv!=null && this.priv.getPrivilegeId()==null))
			;// do nothing.		
		else
			this.priv = priv;
	}
	
	public void loadPrivilege(ComponentSystemEvent event) {
		if(priv == null) {
			priv = new Privilege();
		}
	}
	
	public String submitPrivilege() {
		try {
			logger.info("Saving Privilege.");
			baseService.saveOrUpdate(priv, true);
			if(!getPrivs().contains(priv))
				getPrivs().add(priv);
			logger.info("Privilege saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "savePrivSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Privilege could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return "/sec/rom/privList?faces-redirect=true";
	}
	
	public void loadRole(ComponentSystemEvent event) {
		if(role == null) {
			role = new Role();
		}
	}
	
	public String submitRole() {
		try {
			logger.info("Saving Role.");
			List<Privilege> rem = new ArrayList<Privilege>();
			
			for(Privilege p:role.getPrivileges()) {
				if(!role.getSelectedPrivs().contains(p)) {
					p.getRoles().remove(role);
					rem.add(p);						
				}					
			}
			role.getPrivileges().removeAll(rem);
			for(Privilege p:role.getSelectedPrivs()) {
				if(!role.getPrivileges().contains(p)) {
					role.getPrivileges().add(p);
					p.getRoles().add(role);
				}
				baseService.saveOrUpdate(p, false);
			}
			baseService.saveOrUpdate(role, false);
			baseService.commit();
			if(!getRoles().contains(role))
				getRoles().add(role);
			logger.info("Role saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveRoleSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Role could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return "/sec/rom/roleList?faces-redirect=true";
	}
	
	public String deletePriv() {
		try {
			logger.info("Deleting a Privilege: "+priv.getPrivCode());
			baseService.delete(priv, true);
			getPrivs().remove(priv);
			logger.info("Privilege: "+priv.getPrivCode()+" deleted successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delPrivSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Privilege could not be deleted due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "deleteFailed");
		}
		return "/sec/rom/privList";
	}
	
	public String deleteRole() {
		try {
			logger.info("Deleting a Role: "+role.getRoleName());
			if(!Utils.isEmpty(role.getUserInfos())) {
				WebUtils.addMessage(FacesMessage.SEVERITY_WARN, "activeRoleUsers");
				return "sec/rom/roleList";
			}
			baseService.delete(role, true);
			getRoles().remove(role);
			logger.info("Role: "+role.getRoleName()+" deleted successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delRoleSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Role could not be deleted due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "deleteFailed");
		}
		return "/sec/rom/roleList";
	}

}
