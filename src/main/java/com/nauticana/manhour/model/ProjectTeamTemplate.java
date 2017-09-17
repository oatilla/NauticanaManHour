package com.nauticana.manhour.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_TEAM_TEMPLATE")
public class ProjectTeamTemplate implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "PROJECT_TEAM_TEMPLATE";
	public static final String[] fieldNames = new String[] { "PROJECT_ID", "CATEGORY_ID", "TEAM_ID" };
	private ProjectTeamTemplateId id;
	private ProjectTeam projectTeam;
	private ProjectWbs projectWbs;

	public ProjectTeamTemplate() {
	}

	public ProjectTeamTemplate(ProjectTeamTemplateId id, ProjectTeam projectTeam, ProjectWbs projectWbs) {
		this.id = id;
		this.projectTeam = projectTeam;
		this.projectWbs = projectWbs;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)) })
	public ProjectTeamTemplateId getId() {
		return this.id;
	}

	public void setId(ProjectTeamTemplateId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false, insertable = false, updatable = false) })
	public ProjectTeam getProjectTeam() {
		return this.projectTeam;
	}

	public void setProjectTeam(ProjectTeam projectTeam) {
		this.projectTeam = projectTeam;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", nullable = false, insertable = false, updatable = false) })
	public ProjectWbs getProjectWbs() {
		return this.projectWbs;
	}

	public void setProjectWbs(ProjectWbs projectWbs) {
		this.projectWbs = projectWbs;
	}

}
