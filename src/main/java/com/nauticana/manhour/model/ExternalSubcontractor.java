package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XXADSA_EXT_SUBCONTRACTOR")
public class ExternalSubcontractor implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "XXADSA_EXT_SUBCONTRACTOR";
	public static final String[] fieldNames = new String[] { "EXT_SUBCONTRACTOR", "CAPTION"};
	public static final String rootMapping = "gamaSubcontractor";

	private  String extSubcontractor;
	private  String caption;
	
	public ExternalSubcontractor() {

	}
	
	public ExternalSubcontractor(String extSubcontractor, String caption) {
		super();
		this.extSubcontractor = extSubcontractor;
		this.caption = caption;
	}
	
	@Id
	@Column(name = "EXT_SUBCONTRACTOR", nullable = false, length = 40)
	public String getExtSubcontractor() {
		return extSubcontractor;
	}
	public void setExtSubcontractor(String extSubcontractor) {
		this.extSubcontractor = extSubcontractor;
	}
	
	
	@Column(name = "CAPTION", length = 80)
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
}
