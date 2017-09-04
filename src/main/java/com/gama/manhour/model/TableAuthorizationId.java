package com.gama.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class TableAuthorizationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String authorityGroup;
	private String tablename;

	public TableAuthorizationId() {
    }

	public TableAuthorizationId(String authorityGroup, String tablename) {
       this.authorityGroup = authorityGroup;
       this.tablename = tablename;
    }

	@Column(name="AUTHORITY_GROUP", nullable=false, length=30)
    public String getAuthorityGroup() {
        return this.authorityGroup;
    }

	public void setAuthorityGroup(String authorityGroup) {
        this.authorityGroup = authorityGroup;
    }

	@Column(name = "TABLENAME", nullable = false, length = 30)
	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TableAuthorizationId) ) return false;
		 TableAuthorizationId castOther = ( TableAuthorizationId ) other; 
         
		 return ( (this.getAuthorityGroup()==castOther.getAuthorityGroup()) || ( this.getAuthorityGroup()!=null && castOther.getAuthorityGroup()!=null && this.getAuthorityGroup().equals(castOther.getAuthorityGroup()) ) )
 && ( (this.getTablename()==castOther.getTablename()) || ( this.getTablename()!=null && castOther.getTablename()!=null && this.getTablename().equals(castOther.getTablename()) ) );
   }

	public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAuthorityGroup() == null ? 0 : this.getAuthorityGroup().hashCode() );
         result = 37 * result + ( getTablename() == null ? 0 : this.getTablename().hashCode() );
         return result;
   }

}
