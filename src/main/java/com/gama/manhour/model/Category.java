package com.gama.manhour.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY", schema = "ADSAAT")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"CATEGORY_ID","PARENT_ID","CAPTION","CAT_INDEX","DETAILS","UNIT","CAT_LEVEL","TREE_CODE","MAIN_FLAG"};
	private BigDecimal categoryId;
	private BigDecimal parentId;
	private String caption;
	private String catIndex;
	private String details;
	private String unit;
	private BigDecimal catLevel;
	private String treeCode;
	private Character mainFlag;
	private Set<ProjectWbs> projectWbses = new HashSet<ProjectWbs>(0);

	public Category() {
	}

	public Category(BigDecimal categoryId) {
		this.categoryId = categoryId;
	}

	public Category(BigDecimal categoryId, BigDecimal parentId, String caption, String catIndex, String details, String unit, BigDecimal catLevel, String treeCode, Character mainFlag, Set<ProjectWbs> projectWbses) {
       this.categoryId = categoryId;
       this.parentId = parentId;
       this.caption = caption;
       this.catIndex = catIndex;
       this.details = details;
       this.unit = unit;
       this.catLevel = catLevel;
       this.treeCode = treeCode;
       this.mainFlag = mainFlag;
       this.projectWbses = projectWbses;
    }

	@Id

	@Column(name = "CATEGORY_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(BigDecimal categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "PARENT_ID", precision = 22, scale = 0)
	public BigDecimal getParentId() {
		return this.parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

	@Column(name="CAPTION", length=250)
    public String getCaption() {
        return this.caption;
    }

	public void setCaption(String caption) {
        this.caption = caption;
    }

	@Column(name = "CAT_INDEX", length = 250)
	public String getCatIndex() {
		return this.catIndex;
	}

	public void setCatIndex(String catIndex) {
		this.catIndex = catIndex;
	}

	@Column(name="DETAILS", length=250)
    public String getDetails() {
        return this.details;
    }

	public void setDetails(String details) {
        this.details = details;
    }

	@Column(name="UNIT", length=50)
    public String getUnit() {
        return this.unit;
    }

	public void setUnit(String unit) {
        this.unit = unit;
    }

	@Column(name = "CAT_LEVEL", precision = 22, scale = 0)
	public BigDecimal getCatLevel() {
		return this.catLevel;
	}

	public void setCatLevel(BigDecimal catLevel) {
		this.catLevel = catLevel;
	}

	@Column(name = "TREE_CODE", length = 4000)
	public String getTreeCode() {
		return this.treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	@Column(name="MAIN_FLAG", length=1)
    public Character getMainFlag() {
        return this.mainFlag;
    }

	public void setMainFlag(Character mainFlag) {
        this.mainFlag = mainFlag;
    }

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<ProjectWbs> getProjectWbses() {
		return this.projectWbses;
	}

	public void setProjectWbses(Set<ProjectWbs> projectWbses) {
		this.projectWbses = projectWbses;
	}

}
