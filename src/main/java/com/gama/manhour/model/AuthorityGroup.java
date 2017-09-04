package com.gama.manhour.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "AUTHORITY_GROUP", schema = "ADSAAT")
public class AuthorityGroup implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"AUTHORITY_GROUP","CAPTION"};

	private String authorityGroup;
	private String caption;

	private Set<UserAuthorization> userAuthorizations = new HashSet<UserAuthorization>(0);
     private Set<PageAuthorization> pageAuthorizations = new HashSet<PageAuthorization>(0);
     private Set<TableAuthorization> tableAuthorizations = new HashSet<TableAuthorization>(0);

    public AuthorityGroup() {
    }

	public AuthorityGroup(String authorityGroup, String caption) {
        this.authorityGroup = authorityGroup;
        this.caption = caption;
    }
    public AuthorityGroup(String authorityGroup, String caption, Set<UserAuthorization> userAuthorizations, Set<PageAuthorization> pageAuthorizations, Set<TableAuthorization> tableAuthorizations) {
       this.authorityGroup = authorityGroup;
       this.caption = caption;
       this.userAuthorizations = userAuthorizations;
       this.pageAuthorizations = pageAuthorizations;
       this.tableAuthorizations = tableAuthorizations;
    }

	@Id 
    @Column(name="AUTHORITY_GROUP", unique=true, nullable=false, length=30)
    public String getAuthorityGroup() {
        return this.authorityGroup;
    }

	public void setAuthorityGroup(String authorityGroup) {
        this.authorityGroup = authorityGroup;
    }

	@Column(name="CAPTION", nullable=false, length=30)
    public String getCaption() {
        return this.caption;
    }

	public void setCaption(String caption) {
        this.caption = caption;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="authorityGroup")
    public Set<UserAuthorization> getUserAuthorizations() {
        return this.userAuthorizations;
    }

	public void setUserAuthorizations(Set<UserAuthorization> userAuthorizations) {
        this.userAuthorizations = userAuthorizations;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="authorityGroup")
    public Set<PageAuthorization> getPageAuthorizations() {
        return this.pageAuthorizations;
    }

	public void setPageAuthorizations(Set<PageAuthorization> pageAuthorizations) {
        this.pageAuthorizations = pageAuthorizations;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="authorityGroup")
    public Set<TableAuthorization> getTableAuthorizations() {
        return this.tableAuthorizations;
    }

	public void setTableAuthorizations(Set<TableAuthorization> tableAuthorizations) {
        this.tableAuthorizations = tableAuthorizations;
    }

}
