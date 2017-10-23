package com.nauticana.manhour.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PROJECT")
public class Project implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "PROJECT";
	public static final String[] fieldNames = new String[] { "PROJECT_ID", "CAPTION", "STATUS", "CUSTOMER", "COUNTRY", "LOCATION", "CONTRACT_DATE", "AREA_HANDOVER", "DURATION", "REVIZED_DURATION", "REVIZED_COMPLETION", "EXPECTED_COMPLETION", "END_OF_WARRANTY", "CONTRACTED_AMOUNT", "CONTRACT_EXCHANGE", "EXPECTED_COST", "ADVANCE_PERCENT", "LETTER_OF_ADVANCE", "LETTER_OF_WARRANTY", "ORGANIZATION_ID" };
	public static final String rootMapping = "project";
	public static final String[] actions = new String[] { "APPROVE_FINAL", "APPROVE_WBS", "APPROVE_WITHDRAW" };

	private int     projectId;
	private String  caption;
	private String  status;
	private String  customer;
	private String  country;
	private String  location;
	private Date    contractDate;
	private Date    areaHandover;
	private Integer duration;
	private Integer revizedDuration;
	private Date    revizedCompletion;
	private Date    expectedCompletion;
	private Date    endOfWarranty;
	private Long    contractedAmount;
	private String  contractExchange;
	private Long    expectedCost;
	private Short   advancePercent;
	private Short   letterOfAdvance;
	private Short   letterOfWarranty;
	private Integer organizationId;
	
	private Set<ProjectTeam> projectTeams = new HashSet<ProjectTeam>(0);
	
	private Set<ProjectWbs> projectWbses = new HashSet<ProjectWbs>(0);

	public Project() {
		status = "INITIAL";
	}

	public Project(int projectId, String caption, String status, String customer, String country, String location,
			Date contractDate, Date areaHandover, Integer duration, Integer revizedDuration, Date revizedCompletion,
			Date expectedCompletion, Date endOfWarranty, Long contractedAmount, String contractExchange,
			Long expectedCost, Short advancePercent, Short letterOfAdvance, Short letterOfWarranty,
			Integer organizationId) {
		super();
		this.projectId = projectId;
		this.caption = caption;
		this.status = status;
		this.customer = customer;
		this.country = country;
		this.location = location;
		this.contractDate = contractDate;
		this.areaHandover = areaHandover;
		this.duration = duration;
		this.revizedDuration = revizedDuration;
		this.revizedCompletion = revizedCompletion;
		this.expectedCompletion = expectedCompletion;
		this.endOfWarranty = endOfWarranty;
		this.contractedAmount = contractedAmount;
		this.contractExchange = contractExchange;
		this.expectedCost = expectedCost;
		this.advancePercent = advancePercent;
		this.letterOfAdvance = letterOfAdvance;
		this.letterOfWarranty = letterOfWarranty;
		this.organizationId = organizationId;
		if (status == null || status.equals(""))
			status = "INITIAL";
	}


	public Project(int projectId, String caption, String status, String customer, String country, String location,
			Date contractDate, Date areaHandover, Integer duration, Integer revizedDuration, Date revizedCompletion,
			Date expectedCompletion, Date endOfWarranty, Long contractedAmount, String contractExchange,
			Long expectedCost, Short AdvancePercent, Short letterOfAdvance, Short letterOfWarranty,
			Integer organizationId, Set<ProjectTeam> projectTeams, Set<ProjectWbs> projectWbses) {
		super();
		this.projectId = projectId;
		this.caption = caption;
		this.status = status;
		this.customer = customer;
		this.country = country;
		this.location = location;
		this.contractDate = contractDate;
		this.areaHandover = areaHandover;
		this.duration = duration;
		this.revizedDuration = revizedDuration;
		this.revizedCompletion = revizedCompletion;
		this.expectedCompletion = expectedCompletion;
		this.endOfWarranty = endOfWarranty;
		this.contractedAmount = contractedAmount;
		this.contractExchange = contractExchange;
		this.expectedCost = expectedCost;
		this.advancePercent = AdvancePercent;
		this.letterOfAdvance = letterOfAdvance;
		this.letterOfWarranty = letterOfWarranty;
		this.organizationId = organizationId;
		this.projectTeams = projectTeams;
		this.projectWbses = projectWbses;
		if (status == null || status.equals(""))
			status = "INITIAL";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROJECT_ID_SEQ")
	@SequenceGenerator(name="PROJECT_ID_SEQ", sequenceName="PROJECT_ID_SEQ")
	@Column(name = "PROJECT_ID", unique = true, nullable = false, precision = 8, scale = 0)
	public int getId() {
		return this.projectId;
	}

	public void setId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name = "CAPTION", nullable = false, length = 50)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "COUNTRY", nullable = false, length = 2)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "DURATION", nullable = false, precision = 8, scale = 0)
	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Column(name = "STATUS", nullable = true, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ORGANIZATION_ID", precision = 8, scale = 0)
	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<ProjectTeam> getProjectTeams() {
		return this.projectTeams;
	}

	public void setProjectTeams(Set<ProjectTeam> projectTeams) {
		this.projectTeams = projectTeams;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<ProjectWbs> getProjectWbses() {
		return this.projectWbses;
	}

	public void setProjectWbses(Set<ProjectWbs> projectWbses) {
		this.projectWbses = projectWbses;
	}

	@Column(name = "CUSTOMER", nullable = true, length = 100)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Column(name = "LOCATION", nullable = true, length = 50)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "CONTRACT_DATE")
	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "AREA_HANDOVER")
	public Date getAreaHandover() {
		return areaHandover;
	}

	public void setAreaHandover(Date areaHandover) {
		this.areaHandover = areaHandover;
	}

	@Column(name = "REVIZED_DURATION", precision = 8, scale = 0)
	public Integer getRevizedDuration() {
		return revizedDuration;
	}

	public void setRevizedDuration(Integer revizedDuration) {
		this.revizedDuration = revizedDuration;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "REVIZED_COMPLETION")
	public Date getRevizedCompletion() {
		return revizedCompletion;
	}

	public void setRevizedCompletion(Date revizedCompletion) {
		this.revizedCompletion = revizedCompletion;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "EXPECTED_COMPLETION")
	public Date getExpectedCompletion() {
		return expectedCompletion;
	}

	public void setExpectedCompletion(Date expectedCompletion) {
		this.expectedCompletion = expectedCompletion;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "END_OF_WARRANTY")
	public Date getEndOfWarranty() {
		return endOfWarranty;
	}

	public void setEndOfWarranty(Date endOfWarranty) {
		this.endOfWarranty = endOfWarranty;
	}

	@Column(name = "CONTRACTED_AMOUNT", precision = 12, scale = 0)
	public Long getContractedAmount() {
		return contractedAmount;
	}

	public void setContractedAmount(Long contractedAmount) {
		this.contractedAmount = contractedAmount;
	}

	@Column(name = "CONTRACT_EXCHANGE", nullable = true, length = 3)
	public String getContractExchange() {
		return contractExchange;
	}

	public void setContractExchange(String contractExchange) {
		this.contractExchange = contractExchange;
	}

	@Column(name = "EXPECTED_COST", precision = 12, scale = 0)
	public Long getExpectedCost() {
		return expectedCost;
	}

	public void setExpectedCost(Long expectedCost) {
		this.expectedCost = expectedCost;
	}

	@Column(name = "ADVANCE_PERCENT", precision = 3, scale = 0)
	public Short getAdvancePercent() {
		return advancePercent;
	}

	public void setAdvancePercent(Short advancePercent) {
		this.advancePercent = advancePercent;
	}

	@Column(name = "LETTER_OF_ADVANCE", precision = 3, scale = 0)
	public Short getLetterOfAdvance() {
		return letterOfAdvance;
	}

	public void setLetterOfAdvance(Short letterOfAdvance) {
		this.letterOfAdvance = letterOfAdvance;
	}

	@Column(name = "LETTER_OF_WARRANTY", precision = 3, scale = 0)
	public Short getLetterOfWarranty() {
		return letterOfWarranty;
	}

	public void setLetterOfWarranty(Short letterOfWarranty) {
		this.letterOfWarranty = letterOfWarranty;
	}

}
