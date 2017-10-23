package com.nauticana.nams.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY_OBJECT_ACTION")
public class AuthorityObjectAction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "AUTHORITY_OBJECT_ACTION";
	public static final String[] fieldNames = new String[] {"OBJECT_TYPE", "ACTION"};
	public static final String rootMapping = "authorityObjectAction";

	private AuthorityObjectActionId id;
	private AuthorityObject authorityObject;
	private Set<ObjectAuthorization> objectAuthorizations = new HashSet<ObjectAuthorization>(0);

	
	public AuthorityObjectAction() {
	}

	public AuthorityObjectAction(AuthorityObjectActionId id) {
		this.id = id;
	}

	public AuthorityObjectAction(AuthorityObjectActionId id, AuthorityObject authorityObject, Set<ObjectAuthorization> objectAuthorizations) {
		this.id = id;
		this.authorityObject = authorityObject;
		this.objectAuthorizations = objectAuthorizations;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "objectType", column = @Column(name = "OBJECT_TYPE", nullable = false, length = 30)),
			@AttributeOverride(name = "action", column = @Column(name = "ACTION", nullable = false, length = 30)) })
	public AuthorityObjectActionId getId() {
		return this.id;
	}

	public void setId(AuthorityObjectActionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OBJECT_TYPE", nullable = false, insertable = false, updatable = false)
	public AuthorityObject getAuthorityObject() {
		return this.authorityObject;
	}

	public void setAuthorityObject(AuthorityObject authorityObject) {
		this.authorityObject = authorityObject;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authorityObjectAction")
	public Set<ObjectAuthorization> getObjectAuthorizations() {
		return objectAuthorizations;
	}

	public void setObjectAuthorizations(Set<ObjectAuthorization> objectAuthorizations) {
		this.objectAuthorizations = objectAuthorizations;
	}


}
