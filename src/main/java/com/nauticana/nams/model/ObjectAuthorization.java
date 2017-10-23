package com.nauticana.nams.model;

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
@Table(name = "OBJECT_AUTHORIZATION")
public class ObjectAuthorization implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "OBJECT_AUTHORIZATION";
	public static final String[] fieldNames = new String[] { "AUTHORITY_GROUP", "OBJECT_TYPE", "ACTION", "KEY_VALUE" };
	public static final String rootMapping = "objectAuthorization";

	private ObjectAuthorizationId id;
	private AuthorityGroup authorityGroup;
	private AuthorityObjectAction authorityObjectAction;
	
	public ObjectAuthorization() {
		
	}

	public ObjectAuthorization(ObjectAuthorizationId id, AuthorityGroup authorityGroup,	AuthorityObjectAction authorityObjectAction) {
		this.id = id;
		this.authorityGroup = authorityGroup;
		this.authorityObjectAction = authorityObjectAction;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "authorityGroup", column = @Column(name = "AUTHORITY_GROUP", nullable = false, length = 30)),
			@AttributeOverride(name = "objectType", column = @Column(name = "OBJECT_TYPE", nullable = false, length = 30)),
			@AttributeOverride(name = "action", column = @Column(name = "ACTION", nullable = false, length = 30)),
			@AttributeOverride(name = "keyValue", column = @Column(name = "KEY_VALUE", nullable = false, length = 100)) })
	public ObjectAuthorizationId getId() {
		return id;
	}

	public void setId(ObjectAuthorizationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHORITY_GROUP", nullable = false, insertable = false, updatable = false)
	public AuthorityGroup getAuthorityGroup() {
		return authorityGroup;
	}

	public void setAuthorityGroup(AuthorityGroup authorityGroup) {
		this.authorityGroup = authorityGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OBJECT_TYPE", referencedColumnName = "OBJECT_TYPE", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "ACTION", referencedColumnName = "ACTION", nullable = false, insertable = false, updatable = false) })
	public AuthorityObjectAction getAuthorityObjectAction() {
		return authorityObjectAction;
	}

	public void setAuthorityObjectAction(AuthorityObjectAction authorityObjectAction) {
		this.authorityObjectAction = authorityObjectAction;
	}


}
