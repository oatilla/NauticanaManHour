package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectWbsQuantityId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int projectId;
	private int categoryId;
	private short year;
	private char termType;
	private short termId;

	public ProjectWbsQuantityId() {
	}

	public ProjectWbsQuantityId(int projectId, int categoryId, short year, char termType, short termId) {
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.year = year;
		this.termType = termType;
		this.termId = termId;
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

	@Column(name = "YEAR", nullable = false, precision = 4, scale = 0)
	public short getYear() {
		return this.year;
	}

	public void setYear(short year) {
		this.year = year;
	}

	@Column(name = "TERM_TYPE", nullable = false, length = 1)
	public char getTermType() {
		return this.termType;
	}

	public void setTermType(char termType) {
		this.termType = termType;
	}

	@Column(name = "TERM_ID", nullable = false, precision = 3, scale = 0)
	public short getTermId() {
		return this.termId;
	}

	public void setTermId(short termId) {
		this.termId = termId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof ProjectWbsQuantityId)) return false;
		ProjectWbsQuantityId castOther = (ProjectWbsQuantityId) other;

		return (this.getProjectId() == castOther.getProjectId()) && (this.getCategoryId() == castOther.getCategoryId())
				&& (this.getYear() == castOther.getYear()) && (this.getTermType() == castOther.getTermType())
				&& (this.getTermId() == castOther.getTermId());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProjectId();
		result = 37 * result + this.getCategoryId();
		result = 37 * result + this.getYear();
		result = 37 * result + this.getTermType();
		result = 37 * result + this.getTermId();
		return result;
	}

}
