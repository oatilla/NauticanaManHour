package com.nauticana.nams.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuthorityObjectActionId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String objectType;
	private String action;
	
	
	public AuthorityObjectActionId() {
	}

	public AuthorityObjectActionId(String objectType, String action) {
		this.objectType = objectType;
		this.action = action;
	}

	public AuthorityObjectActionId(String keys) {
		String[] s = keys.split(",");
		this.objectType = s[0];
		this.action = s[1];
	}

	@Column(name = "OBJECT_TYPE", nullable = false, length = 30)
	public String getObjectType() {
		return objectType;
	}


	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}


	@Column(name = "ACTION", nullable = false, length = 30)
	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof AuthorityObjectActionId)) return false;
		AuthorityObjectActionId castOther = (AuthorityObjectActionId) other;

		return ((this.getObjectType() == castOther.getObjectType()) || (this.getObjectType() != null
				&& castOther.getObjectType() != null && this.getObjectType().equals(castOther.getObjectType())))
				&& ((this.getAction() == castOther.getAction()) || (this.getAction() != null
						&& castOther.getAction() != null && this.getAction().equals(castOther.getAction())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getObjectType() == null ? 0 : this.getObjectType().hashCode());
		result = 37 * result + (getAction() == null ? 0 : this.getAction().hashCode());
		return result;
	}

	
}
