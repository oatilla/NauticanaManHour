package com.nauticana.manhour.model;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.nams.utils.Labels;

@Embeddable
public class ProjectWbsQuantityId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int projectId;
	private int categoryId;
	private int teamId;
	private Date begda;

	public ProjectWbsQuantityId() {
	}

	public ProjectWbsQuantityId(int projectId, int categoryId, int teamId, Date begda) {
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.teamId = teamId;
		this.begda = begda;
	}

	public ProjectWbsQuantityId(String keys) {
		String[] s = keys.split(",");
		this.projectId = Integer.parseInt(s[0]);
		this.categoryId = Integer.parseInt(s[1]);
		try {
			this.begda = Labels.dmyDF.parse(s[2]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
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

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "BEGDA", nullable = false)
	public Date getBegda() {
		return this.begda;
	}

	public void setBegda(Date begda) {
		this.begda = begda;
	}
	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof ProjectWbsQuantityId)) return false;
		ProjectWbsQuantityId castOther = (ProjectWbsQuantityId) other;

		return (this.getProjectId() == castOther.getProjectId()) && (this.getCategoryId() == castOther.getCategoryId())  && (this.getTeamId() == castOther.getTeamId())
				&& (this.getBegda() == castOther.getBegda());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProjectId();
		result = 37 * result + this.getCategoryId();
		result = 37 * result + this.getTeamId();
		result = 37 * result + this.getBegda().hashCode();
		return result;
	}

}
