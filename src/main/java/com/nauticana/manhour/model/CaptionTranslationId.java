package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CaptionTranslationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String caption;
	private String langcode;

	public CaptionTranslationId() {
	}

	public CaptionTranslationId(String caption, String langcode) {
		this.caption = caption;
		this.langcode = langcode;
	}
	
	public CaptionTranslationId(String keys) {
		String[] s = keys.split(",");
		this.caption = s[0];
		this.langcode = s[1];
	}

	@Column(name = "CAPTION", nullable = false, length = 30)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "LANGCODE", nullable = false, length = 2)
	public String getLangcode() {
		return this.langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof CaptionTranslationId)) return false;
		CaptionTranslationId castOther = (CaptionTranslationId) other;

		return ((this.getCaption() == castOther.getCaption()) || (this.getCaption() != null
				&& castOther.getCaption() != null && this.getCaption().equals(castOther.getCaption())))
				&& ((this.getLangcode() == castOther.getLangcode()) || (this.getLangcode() != null
						&& castOther.getLangcode() != null && this.getLangcode().equals(castOther.getLangcode())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCaption() == null ? 0 : this.getCaption().hashCode());
		result = 37 * result + (getLangcode() == null ? 0 : this.getLangcode().hashCode());
		return result;
	}

}
