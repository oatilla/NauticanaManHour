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
@Table(name = "TABLE_AUTHORIZATION", schema = "ADSAAT")
public class TableAuthorization implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"AUTHORITY_GROUP",
			"TABLENAME",
			"ALLOW_SELECT",
			"ALLOW_UPDATE",
			"ALLOW_INSERT",
			"ALLOW_DELETE"};
	private TableAuthorizationId id;
	private AuthorityGroup authorityGroup;
	private char allowSelect;
	private char allowUpdate;
	private char allowInsert;
	private char allowDelete;

	public TableAuthorization() {
    }

	public TableAuthorization(TableAuthorizationId id, AuthorityGroup authorityGroup, char allowSelect, char allowUpdate, char allowInsert, char allowDelete) {
       this.id = id;
       this.authorityGroup = authorityGroup;
       this.allowSelect = allowSelect;
       this.allowUpdate = allowUpdate;
       this.allowInsert = allowInsert;
       this.allowDelete = allowDelete;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="authorityGroup", column=@Column(name="AUTHORITY_GROUP", nullable=false, length=30) ), 
        @AttributeOverride(name="tablename", column=@Column(name="TABLENAME", nullable=false, length=30) ) } )
    public TableAuthorizationId getId() {
		return this.id;
	}

	public void setId(TableAuthorizationId id) {
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

	@Column(name = "ALLOW_SELECT", nullable = false, length = 1)
	public char getAllowSelect() {
		return this.allowSelect;
	}

	public void setAllowSelect(char allowSelect) {
		this.allowSelect = allowSelect;
	}

	@Column(name = "ALLOW_UPDATE", nullable = false, length = 1)
	public char getAllowUpdate() {
		return this.allowUpdate;
	}

	public void setAllowUpdate(char allowUpdate) {
		this.allowUpdate = allowUpdate;
	}

	@Column(name = "ALLOW_INSERT", nullable = false, length = 1)
	public char getAllowInsert() {
		return this.allowInsert;
	}

	public void setAllowInsert(char allowInsert) {
		this.allowInsert = allowInsert;
	}

	@Column(name = "ALLOW_DELETE", nullable = false, length = 1)
	public char getAllowDelete() {
		return this.allowDelete;
	}

	public void setAllowDelete(char allowDelete) {
		this.allowDelete = allowDelete;
	}

}
