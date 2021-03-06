package com.nauticana.nams.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UserAuthorizationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String authorityGroup;

	public UserAuthorizationId() {
	}

	public UserAuthorizationId(String username, String authorityGroup) {
		this.username = username;
		this.authorityGroup = authorityGroup;
	}
	
	public UserAuthorizationId(String keys) {
		String[] s = keys.split(",");
		this.username = s[0];
		this.authorityGroup = s[1];
	}

	@Column(name = "USERNAME", nullable = false, length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "AUTHORITY_GROUP", nullable = false, length = 30)
	public String getAuthorityGroup() {
		return this.authorityGroup;
	}

	public void setAuthorityGroup(String authorityGroup) {
		this.authorityGroup = authorityGroup;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof UserAuthorizationId)) return false;
		UserAuthorizationId castOther = (UserAuthorizationId) other;

		return ((this.getUsername() == castOther.getUsername()) || (this.getUsername() != null
				&& castOther.getUsername() != null && this.getUsername().equals(castOther.getUsername())))
				&& ((this.getAuthorityGroup() == castOther.getAuthorityGroup())
						|| (this.getAuthorityGroup() != null && castOther.getAuthorityGroup() != null
								&& this.getAuthorityGroup().equals(castOther.getAuthorityGroup())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result + (getAuthorityGroup() == null ? 0 : this.getAuthorityGroup().hashCode());
		return result;
	}

}
