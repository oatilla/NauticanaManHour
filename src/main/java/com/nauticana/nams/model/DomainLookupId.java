package com.nauticana.nams.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DomainLookupId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String domain;
	private String tableName;

	public DomainLookupId() {
	}

	public DomainLookupId(String domain, String tableName) {
		this.domain = domain;
		this.tableName = tableName;
	}

	public DomainLookupId(String keys) {
		String[] s = keys.split(",");
		this.domain = s[0];
		this.tableName = s[1];
	}
	
	@Column(name = "DOMAIN", nullable = false, length = 30)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "TABLENAME", nullable = false, length = 30)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DomainLookupId))
			return false;
		DomainLookupId castOther = (DomainLookupId) other;

		return ((this.getDomain() == castOther.getDomain()) || (this.getDomain() != null
				&& castOther.getDomain() != null && this.getDomain().equals(castOther.getDomain())))
				&& ((this.getTableName() == castOther.getTableName()) || (this.getTableName() != null
						&& castOther.getTableName() != null && this.getTableName().equals(castOther.getTableName())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getDomain() == null ? 0 : this.getDomain().hashCode());
		result = 37 * result + (getTableName() == null ? 0 : this.getTableName().hashCode());
		return result;
	}

}
