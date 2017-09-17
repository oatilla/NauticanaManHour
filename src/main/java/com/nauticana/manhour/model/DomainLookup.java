package com.nauticana.manhour.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DOMAIN_LOOKUP")
public class DomainLookup implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "DOMAIN_LOOKUP";
	public static final String[] fieldNames = new String[] { "DOMAIN", "TABLENAME", "FIELDNAME" };
	private DomainLookupId id;
	private DomainName domainName;
	private String fieldName;

	public DomainLookup() {
	}

	public DomainLookup(DomainLookupId id, DomainName domainName, String fieldName) {
		this.id = id;
		this.domainName = domainName;
		this.fieldName = fieldName;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "domain", column = @Column(name = "DOMAIN", nullable = false, length = 30)),
			@AttributeOverride(name = "tableName", column = @Column(name = "TABLENAME", nullable = false, length = 30)) })
	public DomainLookupId getId() {
		return this.id;
	}

	public void setId(DomainLookupId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOMAIN", nullable = false, insertable = false, updatable = false)
	public DomainName getDomainName() {
		return this.domainName;
	}

	public void setDomainName(DomainName domainName) {
		this.domainName = domainName;
	}

	@Column(name = "FIELDNAME", nullable = false, length = 30)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
