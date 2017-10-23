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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_TEAM")
public class ProjectTeam implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "PROJECT_TEAM";
	public static final String[] fieldNames = new String[] { "PROJECT_ID", "TEAM_ID", "CAPTION" };
	public static final String rootMapping = "projectTeam";
	public static final String[] actions = new String[] { "APPROVE_MANHOUR" };

	private ProjectTeamId id;
	private Project project;
	private String caption;
	
	private Set<ProjectTeamPerson> projectTeamPersonnel = new HashSet<ProjectTeamPerson>(0);
	private Set<Category> projectTeamCategory = new HashSet<Category>(0);
	private Set<ProjectWbsQuantity> projectWbsQuantities = new HashSet<ProjectWbsQuantity>(0);
	
	public ProjectTeam() {
	}

	public ProjectTeam(ProjectTeamId id, Project project, String caption) {
		this.id = id;
		this.project = project;
		this.caption = caption;
	}

	public ProjectTeam(ProjectTeamId id, Project project, String caption, Set<ProjectTeamPerson> projectTeamPersonnel) {
		this.id = id;
		this.project = project;
		this.caption = caption;
		this.projectTeamPersonnel = projectTeamPersonnel;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)) })
	public ProjectTeamId getId() {
		return this.id;
	}

	public void setId(ProjectTeamId id) {
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

	@Column(name = "CAPTION", nullable = false, length = 50)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectTeam")
	@OrderBy("TEAM_LEAD DESC")
	public Set<ProjectTeamPerson> getProjectTeamPersonnel() {
		return this.projectTeamPersonnel;
	}

	public void setProjectTeamPersonnel(Set<ProjectTeamPerson> projectTeamPersonnel) {
		this.projectTeamPersonnel = projectTeamPersonnel;
	}

	@ManyToMany
	@OrderBy("TREE_CODE")
	@JoinTable(name="PROJECT_TEAM_TEMPLATE",
		joinColumns= {
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false, insertable = false, updatable = false)},
		inverseJoinColumns=@JoinColumn(name="CATEGORY_ID"))
	public Set<Category> getProjectTeamCategory() {
		return projectTeamCategory;
	}

	public void setProjectTeamCategory(Set<Category> projectTeamCategory) {
		this.projectTeamCategory = projectTeamCategory;
	}

	@OrderBy("BEGDA DESC")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectTeam")
	public Set<ProjectWbsQuantity> getProjectWbsQuantities() {
		return this.projectWbsQuantities;
	}

	public void setProjectWbsQuantities(Set<ProjectWbsQuantity> projectWbsQuantities) {
		this.projectWbsQuantities = projectWbsQuantities;
	}

	
}
