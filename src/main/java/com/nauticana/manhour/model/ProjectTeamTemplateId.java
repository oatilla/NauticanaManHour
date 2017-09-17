package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectTeamTemplateId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int projectId;
	private int categoryId;
	private int teamId;

	public ProjectTeamTemplateId() {
	}

	public ProjectTeamTemplateId(int projectId, int categoryId, int teamId) {
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.teamId = teamId;
	}

	public ProjectTeamTemplateId(String keys) {
		String[] s = keys.split(",");
		this.projectId = Integer.parseInt(s[0]);
		this.categoryId = Integer.parseInt(s[1]);
		this.teamId = Integer.parseInt(s[2]);
	}

	
	@Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)
	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)
	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof ProjectTeamTemplateId)) return false;
		ProjectTeamTemplateId castOther = (ProjectTeamTemplateId) other;

		return (this.getProjectId() == castOther.getProjectId()) && (this.getCategoryId() == castOther.getCategoryId())
				&& (this.getTeamId() == castOther.getTeamId());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProjectId();
		result = 37 * result + this.getCategoryId();
		result = 37 * result + this.getTeamId();
		return result;
	}
	
	public String toString() {
		return projectId + "," + categoryId + "," + teamId;
	}

}
