/**
 * 
 */
package com.acminds.acuteauto.utils;

import java.math.BigDecimal;
import java.text.MessageFormat;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.dto.Advertisement;
import com.acminds.acuteauto.persistence.dto.Category;
import com.acminds.acuteauto.persistence.dto.Client;
import com.acminds.acuteauto.persistence.dto.Image;
import com.acminds.acuteauto.persistence.dto.Location;
import com.acminds.acuteauto.persistence.dto.Make;
import com.acminds.acuteauto.persistence.dto.Model;
import com.acminds.acuteauto.persistence.dto.Role;
import com.acminds.acuteauto.persistence.dto.Style;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.utils.EnumConstants.AdStatus;
import com.acminds.acuteauto.utils.EnumConstants.AdUnits;
import com.acminds.acuteauto.utils.EnumConstants.CategoryType;
import com.acminds.acuteauto.utils.EnumConstants.LocationType;
import com.acminds.acuteauto.utils.EnumConstants.UserStatus;
import com.acminds.acuteauto.utils.EnumConstants.UserType;
import com.acminds.acuteauto.utils.EnumConstants.VehicleCondition;
import com.acminds.acuteauto.utils.EnumConstants.VehicleStatus;


/**
 * @author Mansur
 *
 */
public class TestDataCreator {
	BaseDAO dao = new BaseDAO();
	String desc = "{0}-{1}: One of the best cars we have in our inventory. Fully Loaded with Super mileage & performance. Simply the best car you would surely love to ride.";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestDataCreator cr = new TestDataCreator();
		try {
			
			/*Role r = cr.createRole("ADMIN", "Administrator");
			Client cl = cr.createClient("Acute Auto", "Best Trusted Car Dealers in your town", "Acute Auto Inc.");
			UserInfo ui = cr.createUser("Mansur", "Mohammed", "admin", "admin", r, cl);*/
			/* ADDING VEHICLES & IMAGES */
			UserInfo ui = cr.dao.get(UserInfo.class, 3);
			/*//1
			Vehicle v = cr.createVehicle(cr.getMake(47), cr.getModel(465), cr.getStyle(2165), 2010, "", 9500, 15000, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);
			//2
			v = cr.createVehicle(cr.getMake(48), cr.getModel(471), cr.getStyle(2176), 2010, "", 12500, 13200, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);
			//3
			v = cr.createVehicle(cr.getMake(49), cr.getModel(476), cr.getStyle(2206), 2006, "", 8700, 10300, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);
			//4
			v = cr.createVehicle(cr.getMake(50), cr.getModel(483), cr.getStyle(2227), 2009, "", 14200, 18000, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);
			//5
			v = cr.createVehicle(cr.getMake(51), cr.getModel(488), cr.getStyle(2243), 2007, "", 6000, 8500, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);
			//6
			v = cr.createVehicle(cr.getMake(52), cr.getModel(494), cr.getStyle(2255), 2002, "", 4500, 5000, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);
			//7
			v = cr.createVehicle(cr.getMake(53), cr.getModel(525), cr.getStyle(2370), 2005, "", 6500, 7500, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);
			//8
			v = cr.createVehicle(cr.getMake(54), cr.getModel(526), cr.getStyle(2372), 2006, "", 8500, 10000, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);
			//9
			v = cr.createVehicle(cr.getMake(55), cr.getModel(541), cr.getStyle(2422), 2001, "", 3500, 4500, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);
			//10
			v = cr.createVehicle(cr.getMake(56), cr.getModel(583), cr.getStyle(2610), 2008, "", 11500, 16000, ui);
			cr.createImage("Image 1", null, ImageType.PRIMARY, "/images/vehicles/"+v.getVehicleId()+"/img1.jpg", false, v);
			cr.createImage("Image 2", null, null, "/images/vehicles/"+v.getVehicleId()+"/banner1.jpg", true, v);*/
			/*Category cat = new Category();
			cat.setName("Home Page");
			cat.setDescription("Home Page link for main menu");
			cat.setMiscData("/home.jsf");
			cat.setCategoryType(CategoryType.MAIN_MENU);
			cat.setUserInfo(ui);
			cat.setEffectiveDate(Utils.today());
			cat.setExpiryDate(Utils.toDate("12/31/2030"));
			cat.setSeqOrder(1);
			cr.dao.save(cat, false);
			Category cat1 = new Category();
			cat1.setName("Inventory");
			cat1.setDescription("Inventory link for Main Menu");
			cat1.setMiscData("/pub/inv/invList.jsf");
			cat1.setCategoryType(CategoryType.MAIN_MENU);
			cat1.setUserInfo(ui);
			cat1.setEffectiveDate(Utils.today());
			cat1.setExpiryDate(Utils.toDate("12/31/2030"));
			cat1.setSeqOrder(2);
			cr.dao.save(cat1, false);
			Category cat2 = new Category();
			cat2.setName("Vehicle Finance");
			cat2.setDescription("Vehicle Finance for Main Menu");
			cat2.setMiscData("/pub/loan/loanApp.jsf");
			cat2.setCategoryType(CategoryType.MAIN_MENU);
			cat2.setUserInfo(ui);
			cat2.setEffectiveDate(Utils.today());
			cat2.setExpiryDate(Utils.toDate("12/31/2030"));
			cat2.setSeqOrder(3);
			cr.dao.save(cat2, false);
			Category cat3 = new Category();
			cat3.setName("Directions");
			cat3.setDescription("Directions for Main Menu");
			cat3.setMiscData("/pub/directions.jsf");
			cat3.setCategoryType(CategoryType.MAIN_MENU);
			cat3.setUserInfo(ui);
			cat3.setEffectiveDate(Utils.today());
			cat3.setExpiryDate(Utils.toDate("12/31/2030"));
			cat3.setSeqOrder(4);
			cr.dao.save(cat3, false);
			Category cat4 = new Category();
			cat4.setName("Find a Car");
			cat4.setDescription("Find a Car for Main Menu");
			cat4.setMiscData("/pub/findCar.jsf");
			cat4.setCategoryType(CategoryType.MAIN_MENU);
			cat4.setUserInfo(ui);
			cat4.setEffectiveDate(Utils.today());
			cat4.setExpiryDate(Utils.toDate("12/31/2030"));
			cat4.setSeqOrder(5);
			cr.dao.save(cat4, false);*/
			Category cat5 = new Category();
			cat5.setName("Talk to Us");
			cat5.setDescription("Talk to Us for Main Menu");
			cat5.setMiscData("/pub/contactUs.jsf");
			cat5.setCategoryType(CategoryType.MAIN_MENU);
			cat5.setUserInfo(ui);
			cat5.setEffectiveDate(Utils.today());
			cat5.setExpiryDate(Utils.toDate("12/31/2030"));
			cat5.setSeqOrder(5);
			cr.dao.save(cat5, false);
			/*for(int i=2; i<6; i++) {
				Vehicle v = cr.dao.get(Vehicle.class, i);
				v.getCategories().add(cat1);
				if(i==5)
					v.getCategories().add(cat2);
				cr.dao.save(v, false);
			}
			for(int i=6; i<9; i++) {
				Vehicle v = cr.dao.get(Vehicle.class, i);
				v.getCategories().add(cat2);
				cr.dao.save(v, false);
			}*/
			/*Client c = cr.dao.createNamedQuery("getDealer", Client.class).getSingleResult();
			Location l = new Location();
			l.setAddress1("500 Ivy Meadow Ln.");
			l.setAddress2("Apt 3B");
			l.setCity("Durham");
			l.setState("NC");
			l.setZip("27707");
			l.setCountry("USA");
			l.setBusNumber("9193990565");
			l.setEmail("support@acuteauto.com");
			l.setLocationType(LocationType.PRIMARY);
			l.setMailing(true);
			l.setClient(c);
			cr.dao.save(l, false);*/
			cr.dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			cr.dao.rollback();
		}
	}
	
	public Image createImage(String name, String desc, Integer type, String loc, boolean banner, Vehicle v) {
		Image i = new Image();
		i.setName(name);
		i.setDescription(desc);
		i.setImageExtension("jpg");
		i.setMimeType("image/jpg");
		i.setImageType(type);
		i.setImageLocation(loc);
		i.setBanner(banner);
		i.setCreateDate(Utils.today());
		i.setVehicle(v);
		dao.save(i, false);
		return i;
	}
	public Vehicle createVehicle(Make mk, Model md, Style st, int year, String dsc, int dp, int sp, UserInfo ui) {
		Vehicle v = new Vehicle();
		v.setMake(mk);
		v.setModel(md);
		v.setStyle(st);
		v.setYear(year);
		v.setDescription(MessageFormat.format(desc, new Object[]{mk.getName(), md.getName()}));
		v.setDealerPrice(new BigDecimal(dp));
		v.setSalePrice(new BigDecimal(sp));
		v.setStatus(VehicleStatus.AVAILABLE);
		v.setVehCondition(VehicleCondition.USED); 
		v.setMileage(40000);
		v.setCreateDate(Utils.today());
		v.setUserInfo(ui);
		dao.save(v, true);
		return v;
	}
	
	public Client createClient(String name, String description, String regName) {
		Client cl = new Client();
		cl.setClientName(name);
		cl.setClientDesc(description);
		cl.setYearEstd(2010);
		cl.setRegisteredName(regName);
		dao.save(cl, false);
		return cl;
	}
	
	public UserInfo createUser(String fn, String ln, String un, String pas, Role r, Client cl) {
		UserInfo ui = new UserInfo();
		ui.setFirstName(fn);
		ui.setLastName(ln);
		ui.setUserName(un);
		ui.setPassword(pas);
		ui.setStatus(UserStatus.ACTIVE);
		ui.setUserType(UserType.CLIENT);
		ui.setRole(r);
		ui.setClient(cl);
		ui.setCreateDate(Utils.today());
		dao.save(ui, true);
		return ui;
	}
	
	public Role createRole(String nm, String desc) {
		Role r = new Role();
		r.setRoleName(nm);
		r.setDescription(desc);
		dao.save(r, false);
		return r;
	}
	
	public Advertisement createAd(Vehicle v, UserInfo ui, int price) {
		Advertisement a = new Advertisement();
		a.setCreateDate(Utils.today());
		a.setEffectiveDate(Utils.today());
		a.setStatus(AdStatus.ACTIVE);
		a.setUnitPrice(new BigDecimal(price));
		a.setUnits(AdUnits.MONTHLY);
		a.setVehicle(v);
		a.setUserInfo(ui);
		dao.save(a, false);
		return a;
	}
	
	public Make getMake(int id) {
		return dao.get(Make.class, id);
	}
	
	public Model getModel(int id) {
		return dao.get(Model.class, id);
	}
	
	public Style getStyle(int id) {
		return dao.get(Style.class, id);
	}

}
