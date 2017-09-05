package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectTeamPersonId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int projectId;
	private int teamId;
	private int workerId;

	public ProjectTeamPersonId() {
	}

	public ProjectTeamPersonId(int projectId, int teamId, int workerId) {
		this.projectId = projectId;
		this.teamId = teamId;
		this.workerId = workerId;
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

	@Column(name = "WORKER_ID", nullable = false, precision = 8, scale = 0)
	public int getWorkerId() {
		return this.workerId;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProjectTeamPersonId))
			return false;
		ProjectTeamPersonId castOther = (ProjectTeamPersonId) other;

		return (this.getProjectId() == castOther.getProjectId()) && (this.getTeamId() == castOther.getTeamId())
				&& (this.getWorkerId() == castOther.getWorkerId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProjectId();
		result = 37 * result + this.getTeamId();
		result = 37 * result + this.getWorkerId();
		return result;
	}

}
