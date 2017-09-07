package com.nauticana.manhour.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DOMAIN_NAME")
public class DomainName implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "DOMAIN_NAME";
	public static final String[] fieldNames = new String[] { "DOMAIN", "KEYSIZE", "CAPTION" };
	private String domain;
	private short keysize;
	private String caption;

	private Set<DomainValue> domainValues = new HashSet<DomainValue>(0);

	public DomainName() {
	}

	public DomainName(String domain, short keysize, String caption) {
		this.domain = domain;
		this.keysize = keysize;
		this.caption = caption;
	}

	public DomainName(String domain, short keysize, String caption, Set<DomainValue> domainValues) {
		this.domain = domain;
		this.keysize = keysize;
		this.caption = caption;
		this.domainValues = domainValues;
	}

	@Id
	@Column(name = "DOMAIN", unique = true, nullable = false, length = 30)
	public String getId() {
		return this.domain;
	}

	public void setId(String domain) {
		this.domain = domain;
	}

	@Column(name = "KEYSIZE", nullable = false, precision = 4, scale = 0)
	public short getKeysize() {
		return this.keysize;
	}

	public void setKeysize(short keysize) {
		this.keysize = keysize;
	}

	@Column(name = "CAPTION", nullable = false, length = 30)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domainName")
	public Set<DomainValue> getDomainValues() {
		return this.domainValues;
	}

	public void setDomainValues(Set<DomainValue> domainValues) {
		this.domainValues = domainValues;
	}

}
