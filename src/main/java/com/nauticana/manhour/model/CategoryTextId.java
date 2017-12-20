package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategoryTextId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int categoryId;
	private String langcode;

	public CategoryTextId() {
	}

	public CategoryTextId(int categoryId, String langcode) {
		this.categoryId = categoryId;
		this.langcode = langcode;
	}

	public CategoryTextId(String keys) {
		String[] s = keys.split(",");
		this.categoryId = Integer.parseInt(s[0]);
		this.langcode = s[1];
	}

	@Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "LANGCODE", nullable = false, length = 0)
	public String getLangcode() {
		return this.langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CategoryTextId))
			return false;
		CategoryTextId castOther = (CategoryTextId) other;

		return (this.getCategoryId() == castOther.getCategoryId()) && (this.getLangcode().equals(castOther.getLangcode()));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCategoryId();
		result = 37 * result + (getLangcode() == null ? 0 : this.getLangcode().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return this.categoryId + "," + this.langcode;
	}


}
