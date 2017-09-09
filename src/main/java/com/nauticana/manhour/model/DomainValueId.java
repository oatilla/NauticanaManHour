package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DomainValueId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String domain;
	private String refvalue;

	public DomainValueId() {
	}

	public DomainValueId(String domain, String refvalue) {
		this.domain = domain;
		this.refvalue = refvalue;
	}

	public DomainValueId(String keys) {
		String[] s = keys.split(",");
		this.domain = s[0];
		this.refvalue = s[1];
	}
	
	@Column(name = "DOMAIN", nullable = false, length = 30)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "REFVALUE", nullable = false, length = 30)
	public String getRefvalue() {
		return this.refvalue;
	}

	public void setRefvalue(String refvalue) {
		this.refvalue = refvalue;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DomainValueId))
			return false;
		DomainValueId castOther = (DomainValueId) other;

		return ((this.getDomain() == castOther.getDomain()) || (this.getDomain() != null
				&& castOther.getDomain() != null && this.getDomain().equals(castOther.getDomain())))
				&& ((this.getRefvalue() == castOther.getRefvalue()) || (this.getRefvalue() != null
						&& castOther.getRefvalue() != null && this.getRefvalue().equals(castOther.getRefvalue())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getDomain() == null ? 0 : this.getDomain().hashCode());
		result = 37 * result + (getRefvalue() == null ? 0 : this.getRefvalue().hashCode());
		return result;
	}

}
