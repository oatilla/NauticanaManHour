package com.gama.manhour.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAGE_AUTHORIZATION", schema = "ADSAAT")
public class PageAuthorization implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private PageAuthorizationId id;private AuthorityGroup authorityGroup;

	public PageAuthorization() {
    }

	public PageAuthorization(PageAuthorizationId id, AuthorityGroup authorityGroup) {
       this.id = id;
       this.authorityGroup = authorityGroup;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="authorityGroup", column=@Column(name="AUTHORITY_GROUP", nullable=false, length=30) ), 
        @AttributeOverride(name="pagename", column=@Column(name="PAGENAME", nullable=false, length=30) ) } )
    public PageAuthorizationId getId() {
		return this.id;
	}

	public void setId(PageAuthorizationId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="AUTHORITY_GROUP", nullable=false, insertable=false, updatable=false)
    public AuthorityGroup getAuthorityGroup() {
        return this.authorityGroup;
    }

	public void setAuthorityGroup(AuthorityGroup authorityGroup) {
        this.authorityGroup = authorityGroup;
    }




}
