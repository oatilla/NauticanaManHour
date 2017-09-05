package com.nauticana.manhour.model;

import java.math.BigDecimal;
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
@Table(name = "PROJECT_WBS_QUANTITY", schema = "ADSAAT")
public class ProjectWbsQuantity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"PROJECT_ID",
			"CATEGORY_ID",
			"YEAR",
			"TERM_TYPE",
			"TERM_ID",
			"QUANTITY",
			"IS_SUBCONTRACTOR"};
	private ProjectWbsQuantityId id;
	private ProjectWbs projectWbs;
	private BigDecimal quantity;
	private char isSubcontractor;

	public ProjectWbsQuantity() {
    }

	public ProjectWbsQuantity(ProjectWbsQuantityId id, ProjectWbs projectWbs, BigDecimal quantity, char isSubcontractor) {
       this.id = id;
       this.projectWbs = projectWbs;
       this.quantity = quantity;
       this.isSubcontractor = isSubcontractor;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="projectId", column=@Column(name="PROJECT_ID", nullable=false, precision=8, scale=0) ), 
        @AttributeOverride(name="categoryId", column=@Column(name="CATEGORY_ID", nullable=false, precision=8, scale=0) ), 
        @AttributeOverride(name="year", column=@Column(name="YEAR", nullable=false, precision=4, scale=0) ), 
        @AttributeOverride(name="termType", column=@Column(name="TERM_TYPE", nullable=false, length=1) ), 
        @AttributeOverride(name="termId", column=@Column(name="TERM_ID", nullable=false, precision=3, scale=0) ) } )
    public ProjectWbsQuantityId getId() {
		return this.id;
	}

	public void setId(ProjectWbsQuantityId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="PROJECT_ID", referencedColumnName="PROJECT_ID", nullable=false, insertable=false, updatable=false), 
        @JoinColumn(name="CATEGORY_ID", referencedColumnName="CATEGORY_ID", nullable=false, insertable=false, updatable=false) } )
    public ProjectWbs getProjectWbs() {
        return this.projectWbs;
    }

	public void setProjectWbs(ProjectWbs projectWbs) {
		this.projectWbs = projectWbs;
	}

	@Column(name="QUANTITY", nullable=false, precision=8)
    public BigDecimal getQuantity() {
        return this.quantity;
    }

	public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

	@Column(name = "IS_SUBCONTRACTOR", nullable = false, length = 1)
	public char getIsSubcontractor() {
		return this.isSubcontractor;
	}

	public void setIsSubcontractor(char isSubcontractor) {
		this.isSubcontractor = isSubcontractor;
	}

}
