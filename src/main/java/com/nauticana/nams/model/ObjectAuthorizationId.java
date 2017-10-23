package com.nauticana.nams.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ObjectAuthorizationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String authorityGroup;
	private String objectType;
	private String action;
	private String keyValue;

	public ObjectAuthorizationId() {
		
	}
	
	public ObjectAuthorizationId(String keys) {
		String[] s = keys.split(",");
		this.authorityGroup = s[0];
		this.objectType = s[1];
		this.action = s[2];
		this.keyValue = s[3];
	}

	public ObjectAuthorizationId(String authorityGroup, String objectType, String action, String keyValue) {
		super();
		this.authorityGroup = authorityGroup;
		this.objectType = objectType;
		this.action = action;
		this.keyValue = keyValue;
	}

	@Column(name = "AUTHORITY_GROUP", nullable = false, length = 30)
	public String getAuthorityGroup() {
		return authorityGroup;
	}

	public void setAuthorityGroup(String authorityGroup) {
		this.authorityGroup = authorityGroup;
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

	@Column(name = "KEY_VALUE", nullable = false, length = 100)
	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
	
}
