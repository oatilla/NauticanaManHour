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

@Entity
@Table(name = "PROJECT_WBS")
public class ProjectWbs implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "PROJECT_WBS";
	public static final String[] fieldNames = new String[] { "PROJECT_ID", "CATEGORY_ID", "UNIT", "METRIC", "WORKFORCE", "PUP_METRIC", "PUP_WORKFORCE" };
	private ProjectWbsId id;
	private Project project;
	private Category category;
	private String unit;
	private float metric;
	private float quantity;
	private float pupMetric;
	private float pupQuantity;

	private Set<ProjectWbsManhour> projectWbsManhours = new HashSet<ProjectWbsManhour>(0);

	private Set<ProjectWbsQuantity> projectWbsQuantities = new HashSet<ProjectWbsQuantity>(0);

	public ProjectWbs() {
	}

	public ProjectWbs(ProjectWbsId id, Project project, Category category, String unit, float metric,
			float quantity) {
		this.id = id;
		this.project = project;
		this.category = category;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
	}

	public ProjectWbs(ProjectWbsId id, Project project, Category category, String unit, float metric,
			float quantity, float pupMetric, float pupQuantity,
			Set<ProjectWbsManhour> projectWbsManhours, Set<ProjectWbsQuantity> projectWbsQuantities) {
		this.id = id;
		this.project = project;
		this.category = category;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
		this.pupMetric = pupMetric;
		this.pupQuantity = pupQuantity;
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

	@Column(name = "UNIT", nullable = false, length = 3)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "METRIC", nullable = false, precision = 6)
	public float getMetric() {
		return this.metric;
	}

	public void setMetric(float metric) {
		this.metric = metric;
	}

	@Column(name = "QUANTITY", nullable = false, precision = 6)
	public float getQuantity() {
		return this.quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PUP_METRIC", precision = 6)
	public float getPupMetric() {
		return this.pupMetric;
	}

	public void setPupMetric(float pupMetric) {
		this.pupMetric = pupMetric;
	}

	@Column(name = "PUP_QUANTITY", precision = 6)
	public float getPupQuantity() {
		return this.pupQuantity;
	}

	public void setPupQuantity(float pupQuantity) {
		this.pupQuantity = pupQuantity;
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

}
