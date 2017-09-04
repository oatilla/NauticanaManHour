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
@Table(name = "CAPTION_TRANSLATION", schema = "ADSAAT")
public class CaptionTranslation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"LANGCODE","CAPTION","LABELUPPER","LABELLOWER"};
	private CaptionTranslationId id;
	private Language language;
	private String labelupper;
	private String labellower;

	public CaptionTranslation() {
    }

	public CaptionTranslation(CaptionTranslationId id, Language language, String labelupper, String labellower) {
       this.id = id;
       this.language = language;
       this.labelupper = labelupper;
       this.labellower = labellower;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="caption", column=@Column(name="CAPTION", nullable=false, length=30) ), 
        @AttributeOverride(name="langcode", column=@Column(name="LANGCODE", nullable=false, length=2) ) } )
    public CaptionTranslationId getId() {
		return this.id;
	}

	public void setId(CaptionTranslationId id) {
        this.id = id;
    }

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LANGCODE", nullable = false, insertable = false, updatable = false)
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Column(name = "LABELUPPER", nullable = false, length = 80)
	public String getLabelupper() {
		return this.labelupper;
	}

	public void setLabelupper(String labelupper) {
		this.labelupper = labelupper;
	}

	@Column(name = "LABELLOWER", nullable = false, length = 80)
	public String getLabellower() {
		return this.labellower;
	}

	public void setLabellower(String labellower) {
		this.labellower = labellower;
	}

}
