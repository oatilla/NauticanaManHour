package com.nauticana.nams.model;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "LANGUAGE")
public class Language implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "LANGUAGE";
	public static final String[] fieldNames = new String[] { "LANGCODE", "CAPTION", "DIRECTION", "FLAG" };
	public static final String rootMapping = "language";

	private String langcode;
	private String caption;
	private String direction;
	private String flag;

	private Set<CaptionTranslation> captionTranslations = new HashSet<CaptionTranslation>(0);
	public Hashtable<String, String> translations;

	public Language() {
	}

	public Language(String langcode, String caption) {
		this.langcode = langcode;
		this.caption = caption;
		translations = new Hashtable<String, String>();
	}

	public Language(String langcode, String caption, Set<CaptionTranslation> captionTranslations) {
		this.langcode = langcode;
		this.caption = caption;
		this.captionTranslations = captionTranslations;
	}

	@Id
	@Column(name = "LANGCODE", unique = true, nullable = false, length = 2)
	public String getId() {
		return this.langcode;
	}

	public void setId(String langcode) {
		this.langcode = langcode;
	}

	@OrderBy("CAPTION DESC")
	@Column(name = "CAPTION", nullable = false, length = 30)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "DIRECTION", nullable = false, length = 10)
	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Column(name = "FLAG", nullable = false, length = 30)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
	public Set<CaptionTranslation> getCaptionTranslations() {
		return this.captionTranslations;
	}

	public void setCaptionTranslations(Set<CaptionTranslation> captionTranslations) {
		this.captionTranslations = captionTranslations;
	}

	@Transient
	public String getText(String caption) {
		return translations.get(caption);
	}

}
