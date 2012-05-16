package com.acminds.acuteauto.persistence.entities;

// Generated May 13, 2012 8:21:43 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
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
 * LoanApplication generated by customhbm2java
 */
@MappedSuperclass
public abstract class AbstractLoanApplication extends
		com.acminds.acuteauto.persistence.BaseDTO implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer loanApplicationId;
	private Vehicle vehicle;
	private UserInfo userInfoByUpdatedBy;
	private UserInfo userInfoByCreatedBy;
	private int status;
	private Integer financeType;
	private Integer loanTerm;
	private BigDecimal loanAmount;
	private BigDecimal downpayment;
	private String additionalInfo;
	private Date createDate;
	private Date updateDate;
	private List<Applicant> applicants = new ArrayList<Applicant>(0);
	private List<TradeinInfo> tradeinInfos = new ArrayList<TradeinInfo>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LOAN_APPLICATION_ID", unique = true, nullable = false)
	public Integer getLoanApplicationId() {
		return this.loanApplicationId;
	}

	public void setLoanApplicationId(Integer loanApplicationId) {
		this.loanApplicationId = loanApplicationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEHICLE_ID", nullable = false)
	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	public UserInfo getUserInfoByUpdatedBy() {
		return this.userInfoByUpdatedBy;
	}

	public void setUserInfoByUpdatedBy(UserInfo userInfoByUpdatedBy) {
		this.userInfoByUpdatedBy = userInfoByUpdatedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	public UserInfo getUserInfoByCreatedBy() {
		return this.userInfoByCreatedBy;
	}

	public void setUserInfoByCreatedBy(UserInfo userInfoByCreatedBy) {
		this.userInfoByCreatedBy = userInfoByCreatedBy;
	}

	@Column(name = "STATUS", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "FINANCE_TYPE")
	public Integer getFinanceType() {
		return this.financeType;
	}

	public void setFinanceType(Integer financeType) {
		this.financeType = financeType;
	}

	@Column(name = "LOAN_TERM")
	public Integer getLoanTerm() {
		return this.loanTerm;
	}

	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	@Column(name = "LOAN_AMOUNT", precision = 8)
	public BigDecimal getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	@Column(name = "DOWNPAYMENT", precision = 8)
	public BigDecimal getDownpayment() {
		return this.downpayment;
	}

	public void setDownpayment(BigDecimal downpayment) {
		this.downpayment = downpayment;
	}
	
	@Column(name = "ADDITIONAL_INFO", length = 1000)
	public String getAdditionalInfo() {
		return this.additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false, insertable=false, updatable=false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE", nullable = false, length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loanApplication")
	public List<Applicant> getApplicants() {
		return this.applicants;
	}

	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loanApplication")
	public List<TradeinInfo> getTradeinInfos() {
		return this.tradeinInfos;
	}

	public void setTradeinInfos(List<TradeinInfo> tradeinInfos) {
		this.tradeinInfos = tradeinInfos;
	}

}
