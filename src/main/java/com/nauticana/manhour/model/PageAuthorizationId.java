package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PageAuthorizationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String authorityGroup;
	private String pagename;

	public PageAuthorizationId() {
	}

	public PageAuthorizationId(String authorityGroup, String pagename) {
		this.authorityGroup = authorityGroup;
		this.pagename = pagename;
	}

	@Column(name = "AUTHORITY_GROUP", nullable = false, length = 30)
	public String getAuthorityGroup() {
		return this.authorityGroup;
	}

	public void setAuthorityGroup(String authorityGroup) {
		this.authorityGroup = authorityGroup;
	}

	@Column(name = "PAGENAME", nullable = false, length = 30)
	public String getPagename() {
		return this.pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PageAuthorizationId))
			return false;
		PageAuthorizationId castOther = (PageAuthorizationId) other;

		return ((this.getAuthorityGroup() == castOther.getAuthorityGroup())
				|| (this.getAuthorityGroup() != null && castOther.getAuthorityGroup() != null
						&& this.getAuthorityGroup().equals(castOther.getAuthorityGroup())))
				&& ((this.getPagename() == castOther.getPagename()) || (this.getPagename() != null
						&& castOther.getPagename() != null && this.getPagename().equals(castOther.getPagename())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getAuthorityGroup() == null ? 0 : this.getAuthorityGroup().hashCode());
		result = 37 * result + (getPagename() == null ? 0 : this.getPagename().hashCode());
		return result;
	}

}
