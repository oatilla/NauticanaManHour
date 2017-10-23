package com.nauticana.manhour.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PROJECT_WBS")
public class ProjectWbs implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "PROJECT_WBS";
	public static final String[] fieldNames = new String[] { "PROJECT_ID", "CATEGORY_ID", "CBS_CODE", "UNIT", "METRIC", "QUANTITY", "WORKFORCE", "PUP_METRIC", "PUP_QUANTITY", "PUP_WORKFORCE", "PLANNED_METRIC", "PLANNED_QUANTITY", "PLANNED_WORKFORCE", "CUSTOMER_WBS_CODE", "CUSTOMER_WBS_CAPTION" };
	public static final String rootMapping = "projectWbs";
	public static final String[] actions = new String[] { "APPROVE_QUANTITY" };

	private ProjectWbsId id;
	private Project project;
	private Category category;
	private Cbs cbs;
	private String unit;
	private float metric;
	private float quantity;
	private Float pupMetric;
	private Float pupQuantity;
	private Float plannedMetric;
	private Float plannedQuantity;
	private String customerWbsCode;
	private String customerWbsCaption;

	private Set<ProjectWbsManhour> projectWbsManhours = new HashSet<ProjectWbsManhour>(0);
	private Set<ProjectWbsQuantity> projectWbsQuantities = new HashSet<ProjectWbsQuantity>(0);

	public ProjectWbs() {
	}

	public ProjectWbs(ProjectWbsId id, Project project, Category category, Cbs cbs, String unit, float metric,
			float quantity) {
		this.id = id;
		this.project = project;
		this.category = category;
		this.cbs = cbs;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
	}

	public ProjectWbs(ProjectWbsId id, Project project, Category category, Cbs cbs, String unit, float metric, float quantity,
			Float pupMetric, Float pupQuantity, Float plannedMetric, Float plannedQuantity, String customerWbsCode, String customerWbsCaption,
			Set<ProjectWbsManhour> projectWbsManhours, Set<ProjectWbsQuantity> projectWbsQuantities) {
		this.id = id;
		this.project = project;
		this.category = category;
		this.cbs = cbs;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
		this.pupMetric = pupMetric;
		this.pupQuantity = pupQuantity;
		this.plannedMetric = plannedMetric;
		this.plannedQuantity = plannedQuantity;
		this.customerWbsCode = customerWbsCode;
		this.customerWbsCaption = customerWbsCaption;
		this.projectWbsManhours = projectWbsManhours;
		this.projectWbsQuantities = projectWbsQuantities;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)) })
	public ProjectWbsId getId() {
		return this.id;
	}

	public void setId(ProjectWbsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID", nullable = false, insertable = false, updatable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CBS_CODE", nullable = true, insertable = true, updatable = true)
	public Cbs getCbs() {
		return this.cbs;
	}

	public void setCbs(Cbs cbs) {
		this.cbs = cbs;
	}

	@Column(name = "UNIT", nullable = false, length = 3)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "METRIC", nullable = false, precision = 8, scale = 2)
	public float getMetric() {
		return this.metric;
	}

	public void setMetric(float metric) {
		this.metric = metric;
	}

	@Column(name = "QUANTITY", nullable = false, precision = 8, scale = 2)
	public float getQuantity() {
		return this.quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PUP_METRIC", precision = 8, scale = 2)
	public Float getPupMetric() {
		return this.pupMetric;
	}

	public void setPupMetric(Float pupMetric) {
		this.pupMetric = pupMetric;
	}

	@Column(name = "PUP_QUANTITY", precision = 8, scale = 2)
	public Float getPupQuantity() {
		return this.pupQuantity;
	}

	public void setPupQuantity(Float pupQuantity) {
		this.pupQuantity = pupQuantity;
	}

	@Column(name = "PLANNED_METRIC", precision = 8, scale = 2)
	public Float getPlannedMetric() {
		return this.plannedMetric;
	}

	public void setPlannedMetric(Float plannedMetric) {
		this.plannedMetric = plannedMetric;
	}
	
	@Column(name = "PLANNED_QUANTITY", precision = 8, scale = 2)
	public Float getPlannedQuantity() {
		return this.plannedQuantity;
	}

	public void setPlannedQuantity(Float plannedQuantity) {
		this.plannedQuantity = plannedQuantity;
	}

	@Column(name = "CUSTOMER_WBS_CODE")
	public String getCustomerWbsCode() {
		return this.customerWbsCode;
	}

	public void setCustomerWbsCode(String customerWbsCode) {
		this.customerWbsCode = customerWbsCode;
	}
	@Column(name = "CUSTOMER_WBS_CAPTION")
	public String getCustomerWbsCaption() {
		return this.customerWbsCaption;
	}

	public void setCustomerWbsCaption(String customerWbsCaption) {
		this.customerWbsCaption = customerWbsCaption;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectWbs")
	public Set<ProjectWbsManhour> getProjectWbsManhours() {
		return this.projectWbsManhours;
	}

	public void setProjectWbsManhours(Set<ProjectWbsManhour> projectWbsManhours) {
		this.projectWbsManhours = projectWbsManhours;
	}

	@OrderBy("BEGDA DESC")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectWbs")
	public Set<ProjectWbsQuantity> getProjectWbsQuantities() {
		return this.projectWbsQuantities;
	}

	public void setProjectWbsQuantities(Set<ProjectWbsQuantity> projectWbsQuantities) {
		this.projectWbsQuantities = projectWbsQuantities;
	}

	@Transient
	@Column(name = "WORKFORCE", precision = 8, scale = 2)
	public float getWorkforce() {
		return quantity * metric;
	}

	@Transient
	@Column(name = "PUP_WORKFORCE", precision = 8, scale = 2)
	public Float getPupWorkforce() {
		try {return pupQuantity * pupMetric;} catch (Exception e) { return null;}
	}

	@Transient
	@Column(name = "PLANNED_WORKFORCE", precision = 8, scale = 2)
	public Float getPlannedWorkforce() {
		try {return plannedQuantity * plannedMetric;} catch (Exception e) { return null;}
	}


}
