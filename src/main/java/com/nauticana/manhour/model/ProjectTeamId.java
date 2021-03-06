package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectTeamId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int projectId;
	private int teamId;

	public ProjectTeamId() {
	}

	public ProjectTeamId(int projectId, int teamId) {
		this.projectId = projectId;
		this.teamId = teamId;
	}

	public ProjectTeamId(String keys) {
		String[] s = keys.split(",");
		this.projectId = Integer.parseInt(s[0]);
		this.teamId = Integer.parseInt(s[1]);
	}

	@Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)
	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProjectTeamId))
			return false;
		ProjectTeamId castOther = (ProjectTeamId) other;

		return (this.getProjectId() == castOther.getProjectId()) && (this.getTeamId() == castOther.getTeamId());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProjectId();
		result = 37 * result + this.getTeamId();
		return result;
	}

	@Override
	public String toString() {
		return this.projectId + "," + this.teamId;
	}


}
