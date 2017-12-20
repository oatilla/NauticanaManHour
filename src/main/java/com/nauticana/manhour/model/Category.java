package com.nauticana.manhour.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "CATEGORY";
	public static final String[] fieldNames = new String[] {"CATEGORY_ID","PARENT_ID","CAPTION","CAT_INDEX","DETAILS","UNIT","UNIT2","CAT_LEVEL","TREE_CODE","MAIN_FLAG","PROJECT_ID"};
	public static final String rootMapping = "category";

	private int categoryId;
	private Integer parentId;
	private String caption;
	private String catIndex;
	private String details;
	private String unit;
	private String unit2;
	private byte catLevel;
	private String treeCode;
	private String cbsCode;
	private char mainFlag;
	private Integer projectId;
	

	private Set<ProjectWbs> projectWbses = new HashSet<ProjectWbs>(0);
	private Set<CategoryText> categoryTexts = new HashSet<CategoryText>(0);

	public Category() {
	}

	public Category(int categoryId) {
		this.categoryId = categoryId;
	}

	public Category(int categoryId, Integer parentId, String caption, String catIndex, String details, String unit, byte catLevel, String treeCode, String cbsCode, char mainFlag, Integer projectId, Set<ProjectWbs> projectWbses) {
       this.categoryId = categoryId;
       this.parentId = parentId;
       this.caption = caption;
       this.catIndex = catIndex;
       this.details = details;
       this.unit = unit;
       this.catLevel = catLevel;
       this.treeCode = treeCode;
       this.cbsCode = cbsCode;
       this.mainFlag = mainFlag;
       this.projectId= projectId;
       this.projectWbses = projectWbses;
    }

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORY_ID_SEQ")
	@SequenceGenerator(name="CATEGORY_ID_SEQ", sequenceName="CATEGORY_ID_SEQ")
	@Column(name = "CATEGORY_ID", unique = true, nullable = false)
	public int getId() {
		return this.categoryId;
	}

	public void setId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "PARENT_ID")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name="CAPTION", length=250, nullable = false)
    public String getCaption() {
        return this.caption;
    }

	public void setCaption(String caption) {
        this.caption = caption;
    }

	@Column(name = "CAT_INDEX", length = 20, nullable = false)
	public String getCatIndex() {
		return this.catIndex;
	}

	public void setCatIndex(String catIndex) {
		this.catIndex = catIndex;
	}

	@Column(name="DETAILS", length=100)
    public String getDetails() {
        return this.details;
    }

	public void setDetails(String details) {
        this.details = details;
    }

	@Column(name="UNIT", length=3)
    public String getUnit() {
        return this.unit;
    }

	public void setUnit(String unit) {
        this.unit = unit;
    }

	@Column(name="UNIT2", length=3)
    public String getUnit2() {
        return this.unit2;
    }

	public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

	@Column(name = "CAT_LEVEL", nullable = false)
	public byte getCatLevel() {
		return this.catLevel;
	}

	public void setCatLevel(byte catLevel) {
		this.catLevel = catLevel;
	}

	@OrderBy("TREE_CODE")
	@Column(name = "TREE_CODE", length = 20, nullable = false)
	public String getTreeCode() {
		return this.treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	@Column(name = "CBS_CODE", length = 20)
	public String getCbsCode() {
		return this.cbsCode;
	}

	public void setCbsCode(String cbsCode) {
		this.cbsCode = cbsCode;
	}

	@Column(name="MAIN_FLAG", length=1)
    public char getMainFlag() {
        return this.mainFlag;
    }

	public void setMainFlag(char mainFlag) {
        this.mainFlag = mainFlag;
    }

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
	public Set<CategoryText> getCategoryTexts() {
		return this.categoryTexts;
	}

	public void setCategoryTexts(Set<CategoryText> categoryTexts) {
		this.categoryTexts = categoryTexts;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<ProjectWbs> getProjectWbses() {
		return this.projectWbses;
	}

	public void setProjectWbses(Set<ProjectWbs> projectWbses) {
		this.projectWbses = projectWbses;
	}

	@Column(name="PROJECT_ID")
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
