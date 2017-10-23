package com.nauticana.nams.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY_OBJECT")
public class AuthorityObject implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "AUTHORITY_OBJECT";
	public static final String[] fieldNames = new String[] {"OBJECT_TYPE"};
	public static final String rootMapping = "authorityObject";

	private String objectType;
	
	private Set<AuthorityObjectAction> authorityObjectActions = new HashSet<AuthorityObjectAction>(0);

	public AuthorityObject() {
	}

	public AuthorityObject(String objectType) {
		this.objectType = objectType;
	}

	@Id
	@OrderBy("OBJECT_TYPE")
	@Column(name="OBJECT_TYPE", length=30)
    public String getId() {
        return this.objectType;
    }

	public void setId(String id) {
        this.objectType = id;
    }

	@OrderBy("ACTION")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authorityObject")
	public Set<AuthorityObjectAction> getAuthorityObjectActions() {
		return this.authorityObjectActions;
	}

	public void setAuthorityObjectActions(Set<AuthorityObjectAction> authorityObjectActions) {
		this.authorityObjectActions = authorityObjectActions;
	}

}
