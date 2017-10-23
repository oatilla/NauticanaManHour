package com.nauticana.nams.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY_GROUP")
public class AuthorityGroup implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "AUTHORITY_GROUP";
	public static final String[] fieldNames = new String[] { "AUTHORITY_GROUP", "CAPTION" };
	public static final String rootMapping = "authorityGroup";
	
	private String authorityGroup;
	private String caption;

	private Set<UserAuthorization> userAuthorizations = new HashSet<UserAuthorization>(0);
	private Set<ObjectAuthorization> objectAuthorizations = new HashSet<ObjectAuthorization>(0);

	public AuthorityGroup() {
	}

	public AuthorityGroup(String authorityGroup, String caption) {
		this.authorityGroup = authorityGroup;
		this.caption = caption;
	}

	public AuthorityGroup(String authorityGroup, String caption, Set<UserAuthorization> userAuthorizations, Set<ObjectAuthorization> objectAuthorizations) {
		this.authorityGroup = authorityGroup;
		this.caption = caption;
		this.userAuthorizations = userAuthorizations;
		this.objectAuthorizations = objectAuthorizations;
	}

	@Id
	@Column(name = "AUTHORITY_GROUP", unique = true, nullable = false, length = 30)
	public String getId() {
		return this.authorityGroup;
	}

	public void setId(String authorityGroup) {
		this.authorityGroup = authorityGroup;
	}

	@Column(name = "CAPTION", nullable = false, length = 30)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authorityGroup")
	public Set<UserAuthorization> getUserAuthorizations() {
		return this.userAuthorizations;
	}

	public void setUserAuthorizations(Set<UserAuthorization> userAuthorizations) {
		this.userAuthorizations = userAuthorizations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authorityGroup")
	public Set<ObjectAuthorization> getObjectAuthorizations() {
		return this.objectAuthorizations;
	}

	public void setObjectAuthorizations(Set<ObjectAuthorization> objectAuthorizations) {
		this.objectAuthorizations = objectAuthorizations;
	}

}
