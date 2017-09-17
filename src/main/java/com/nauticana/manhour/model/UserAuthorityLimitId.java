package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UserAuthorityLimitId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String authorityGroup;
	private String objectName;

	public UserAuthorityLimitId() {
	}

	public UserAuthorityLimitId(String username, String authorityGroup, String objectName) {
		this.username = username;
		this.authorityGroup = authorityGroup;
		this.objectName = objectName;
	}
	
	public UserAuthorityLimitId(String keys) {
		String[] s = keys.split(",");
		this.username = s[0];
		this.authorityGroup = s[1];
		this.objectName = s[2];
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

	@Column(name = "OBJECT_NAME", nullable = false, length = 30)
	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof UserAuthorityLimitId)) return false;
		UserAuthorityLimitId castOther = (UserAuthorityLimitId) other;

		return	((this.getUsername() == castOther.getUsername()) ||
				(this.getUsername() != null	&& castOther.getUsername() != null && this.getUsername().equals(castOther.getUsername())))
				 &&
				((this.getAuthorityGroup() == castOther.getAuthorityGroup()) ||
				(this.getAuthorityGroup() != null && castOther.getAuthorityGroup() != null	&& this.getAuthorityGroup().equals(castOther.getAuthorityGroup())))
				 &&
				((this.getObjectName() == castOther.getObjectName()) ||
				(this.getObjectName() != null && castOther.getObjectName() != null	&& this.getObjectName().equals(castOther.getObjectName())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result + (getAuthorityGroup() == null ? 0 : this.getAuthorityGroup().hashCode());
		result = 37 * result + (getObjectName() == null ? 0 : this.getObjectName().hashCode());
		return result;
	}

}
