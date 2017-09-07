package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ProjectWbsId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int projectId;
	private int categoryId;

	public ProjectWbsId() {
	}

	public ProjectWbsId(int projectId, int categoryId) {
		this.projectId = projectId;
		this.categoryId = categoryId;
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

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProjectWbsId))
			return false;
		ProjectWbsId castOther = (ProjectWbsId) other;

		return (this.getProjectId() == castOther.getProjectId()) && (this.getCategoryId() == castOther.getCategoryId());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProjectId();
		result = 37 * result + this.getCategoryId();
		return result;
	}

}
