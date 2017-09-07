package com.nauticana.manhour.query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProjectCategory {

	private int categoryId;
	private Integer parentId;
	private String treeCode;
	private String caption;
	private Integer projectId;
	
	
	public ProjectCategory(int categoryId, Integer parentId, String treeCode, String caption, Integer projectId) {
		super();
		this.categoryId = categoryId;
		this.parentId = parentId;
		this.treeCode = treeCode;
		this.caption = caption;
		this.projectId = projectId;
	}


	@Id
	@Column(name="CATEGORY_ID")
	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	@Column(name="PARENT_ID")
	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	@Column(name="TREE_CODE")
	public String getTreeCode() {
		return treeCode;
	}


	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}


	@Column(name="CAPTION")
	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	@Column(name="PROJECT_ID")
	public Integer getProjectId() {
		return projectId;
	}


	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
