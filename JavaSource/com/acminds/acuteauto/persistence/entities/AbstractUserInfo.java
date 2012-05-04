package com.acminds.acuteauto.persistence.entities;

// Generated May 4, 2012 3:55:01 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.acminds.acuteauto.persistence.dto.*;

/**
 * UserInfo generated by customhbm2java
 */
@MappedSuperclass
public abstract class AbstractUserInfo extends
		com.acminds.acuteauto.persistence.BaseDTO implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userInfoId;
	private Client client;
	private Role role;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dateOfBirth;
	private Integer ssn;
	private Integer dmvLicenseNum;
	private String licenseState;
	private Date licenseExpiry;
	private String userName;
	private String password;
	private int userType;
	private int status;
	private Date lastLoginDate;
	private Date createDate;
	private List<Inquiry> inquiries = new ArrayList<Inquiry>(0);
	private List<Employment> employments = new ArrayList<Employment>(0);
	private List<Advertisement> advertisements = new ArrayList<Advertisement>(0);
	private List<Vehicle> vehicles = new ArrayList<Vehicle>(0);
	private List<LoanApplication> loanApplicationsForCreatedBy = new ArrayList<LoanApplication>(
			0);
	private List<Account> accounts = new ArrayList<Account>(0);
	private List<LoanApplication> loanApplicationsForUpdatedBy = new ArrayList<LoanApplication>(
			0);
	private List<Location> locations = new ArrayList<Location>(0);
	private List<LoanApplication> loanApplicationsForApplicant = new ArrayList<LoanApplication>(
			0);
	private List<Category> categories = new ArrayList<Category>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_INFO_ID", unique = true, nullable = false)
	public Integer getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID")
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 60)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "MIDDLE_NAME", length = 60)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 60)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", length = 10)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "SSN")
	public Integer getSsn() {
		return this.ssn;
	}

	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}

	@Column(name = "DMV_LICENSE_NUM")
	public Integer getDmvLicenseNum() {
		return this.dmvLicenseNum;
	}

	public void setDmvLicenseNum(Integer dmvLicenseNum) {
		this.dmvLicenseNum = dmvLicenseNum;
	}

	@Column(name = "LICENSE_STATE", length = 2)
	public String getLicenseState() {
		return this.licenseState;
	}

	public void setLicenseState(String licenseState) {
		this.licenseState = licenseState;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LICENSE_EXPIRY", length = 10)
	public Date getLicenseExpiry() {
		return this.licenseExpiry;
	}

	public void setLicenseExpiry(Date licenseExpiry) {
		this.licenseExpiry = licenseExpiry;
	}

	@Column(name = "USER_NAME", unique = true, nullable = false, length = 40)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "USER_TYPE", nullable = false)
	public int getUserType() {
		return this.userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Column(name = "STATUS", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_LOGIN_DATE", length = 10)
	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
	public List<Inquiry> getInquiries() {
		return this.inquiries;
	}

	public void setInquiries(List<Inquiry> inquiries) {
		this.inquiries = inquiries;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
	public List<Employment> getEmployments() {
		return this.employments;
	}

	public void setEmployments(List<Employment> employments) {
		this.employments = employments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
	public List<Advertisement> getAdvertisements() {
		return this.advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfoByCreatedBy")
	public List<LoanApplication> getLoanApplicationsForCreatedBy() {
		return this.loanApplicationsForCreatedBy;
	}

	public void setLoanApplicationsForCreatedBy(
			List<LoanApplication> loanApplicationsForCreatedBy) {
		this.loanApplicationsForCreatedBy = loanApplicationsForCreatedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfoByUpdatedBy")
	public List<LoanApplication> getLoanApplicationsForUpdatedBy() {
		return this.loanApplicationsForUpdatedBy;
	}

	public void setLoanApplicationsForUpdatedBy(
			List<LoanApplication> loanApplicationsForUpdatedBy) {
		this.loanApplicationsForUpdatedBy = loanApplicationsForUpdatedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfoByApplicant")
	public List<LoanApplication> getLoanApplicationsForApplicant() {
		return this.loanApplicationsForApplicant;
	}

	public void setLoanApplicationsForApplicant(
			List<LoanApplication> loanApplicationsForApplicant) {
		this.loanApplicationsForApplicant = loanApplicationsForApplicant;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
