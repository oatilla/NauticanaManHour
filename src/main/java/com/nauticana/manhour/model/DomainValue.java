package com.nauticana.manhour.model;

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
@Table(name = "DOMAIN_VALUE", schema = "ADSAAT")
public class DomainValue implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"DOMAIN","REFVALUE","CAPTION"};
	private DomainValueId id;
	private DomainName domainName;
	private String caption;

	public DomainValue() {
    }

	public DomainValue(DomainValueId id, DomainName domainName, String caption) {
       this.id = id;
       this.domainName = domainName;
       this.caption = caption;
    }
   
     @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="domain", column=@Column(name="DOMAIN", nullable=false, length=30) ), 
        @AttributeOverride(name="refvalue", column=@Column(name="REFVALUE", nullable=false, length=30) ) } )
    public DomainValueId getId() {
		return this.id;
	}

	public void setId(DomainValueId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOMAIN", nullable=false, insertable=false, updatable=false)
    public DomainName getDomainName() {
        return this.domainName;
    }

	public void setDomainName(DomainName domainName) {
        this.domainName = domainName;
    }

	@Column(name="CAPTION", nullable=false, length=30)
    public String getCaption() {
        return this.caption;
    }

	public void setCaption(String caption) {
        this.caption = caption;
    }




}
