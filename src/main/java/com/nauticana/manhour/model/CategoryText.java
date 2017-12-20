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
@Table(name = "CATEGORY_TEXT")
public class CategoryText implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "CATEGORY_TEXT";
	public static final String[] fieldNames = new String[] { "CATEGORY_ID", "LANGCODE", "CAPTION" };
	public static final String rootMapping = "categoryText";

	private CategoryTextId id;
	private Category category;
	private String caption;
	
	public CategoryText() {
		
	}

	public CategoryText(CategoryTextId id, Category category, String caption) {
		this.id = id;
		this.category = category;
		this.caption = caption;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "caption", column = @Column(name = "CAPTION", nullable = false, length = 200)),
			@AttributeOverride(name = "langcode", column = @Column(name = "LANGCODE", nullable = false, length = 2)) })
	public CategoryTextId getId() {
		return id;
	}
	
	public void setId(CategoryTextId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "CAPTION", nullable = false, length = 100)
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}


}
