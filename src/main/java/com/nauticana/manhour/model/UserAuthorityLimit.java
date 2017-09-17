package com.nauticana.manhour.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_AUTHORITY_LIMIT")
public class UserAuthorityLimit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "USER_AUTHORITY_LIMIT";
	public static final String[] fieldNames = new String[] { "USERNAME", "AUTHORITY_GROUP", "OBJECT_NAME", "OBJECT_KEYS" };

	private UserAuthorityLimitId id;
	private UserAuthorization userAuthorization;
	private String objectKeys;

	public UserAuthorityLimit() {
	}

	public UserAuthorityLimit(UserAuthorityLimitId id, String objectKeys) {
		this.id = id;
		this.objectKeys = objectKeys;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "username", column = @Column(name = "USERNAME", nullable = false, length = 30)),
			@AttributeOverride(name = "authorityGroup", column = @Column(name = "AUTHORITY_GROUP", nullable = false, length = 30)),
			@AttributeOverride(name = "objectName", column = @Column(name = "OBJECT_NAME", nullable = false, length = 30)) })
	public UserAuthorityLimitId getId() {
		return this.id;
	}

	public void setId(UserAuthorityLimitId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "AUTHORITY_GROUP", referencedColumnName = "AUTHORITY_GROUP", nullable = false, insertable = false, updatable = false) })
	public UserAuthorization getUserAuthorization() {
		return this.userAuthorization;
	}

	public void setUserAuthorization(UserAuthorization userAuthorization) {
		this.userAuthorization = userAuthorization;
	}

	@Column(name = "OBJECT_KEYS", nullable = false, length = 250)
	public String getObjectKeys() {
		return objectKeys;
	}

	public void setObjectKeys(String objectKeys) {
		this.objectKeys = objectKeys;
	}

	
}
