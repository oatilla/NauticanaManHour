package com.gama.manhour.model;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LANGUAGE", schema = "ADSAAT")
public class Language implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"LANGCODE","CAPTION"};
	private String langcode;
	private String caption;

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
	public String getLangcode() {
		return this.langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

	@Column(name="CAPTION", nullable=false, length=30)
    public String getCaption() {
        return this.caption;
    }

	public void setCaption(String caption) {
        this.caption = caption;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="language")
    public Set<CaptionTranslation> getCaptionTranslations() {
        return this.captionTranslations;
    }

	public void setCaptionTranslations(Set<CaptionTranslation> captionTranslations) {
        this.captionTranslations = captionTranslations;
    }

	public String getText(String caption) {
		return "TBD";
	}


}
