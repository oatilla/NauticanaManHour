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
@Table(name = "USER_AUTHORIZATION", schema = "ADSAAT")
public class UserAuthorization implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"USERNAME","AUTHORITY_GROUP"};
	private UserAuthorizationId id;private AuthorityGroup authorityGroup;

	public UserAuthorization() {
    }

	public UserAuthorization(UserAuthorizationId id, AuthorityGroup authorityGroup) {
       this.id = id;
       this.authorityGroup = authorityGroup;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="username", column=@Column(name="USERNAME", nullable=false, length=30) ), 
        @AttributeOverride(name="authorityGroup", column=@Column(name="AUTHORITY_GROUP", nullable=false, length=30) ) } )
    public UserAuthorizationId getId() {
		return this.id;
	}

	public void setId(UserAuthorizationId id) {
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
