package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "XXADSA_CBS")
public class Cbs implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "XXADSA_CBS";
	public static final String[] fieldNames = new String[] { "CBS_CODE", "CAPTION", "SUMMARY_FLAG" };
	public static final String rootMapping = "cbs";

	private String cbsCode;
	private String caption;
	private char   summaryFlag;
	
	public Cbs() {
		
	}
	
	public Cbs(String cbsCode, String caption, char summaryFlag) {
		super();
		this.cbsCode = cbsCode;
		this.caption = caption;
		this.summaryFlag = summaryFlag;
	}

	@Id
	@OrderBy
	@Column(name = "CBS_CODE", unique = true, nullable = false, length = 20)
	public String getId() {
		return cbsCode;
	}

	public void setId(String id) {
		this.cbsCode = id;
	}

	@Column(name = "CAPTION", nullable = false, length = 100)
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "SUMMARY_FLAG", nullable = false, length = 1)
	public char getSummaryFlag() {
		return summaryFlag;
	}

	public void setSummaryFlag(char summaryFlag) {
		this.summaryFlag = summaryFlag;
	}

	
	
}
