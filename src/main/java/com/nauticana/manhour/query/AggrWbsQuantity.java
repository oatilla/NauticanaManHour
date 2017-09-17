package com.nauticana.manhour.query;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class AggrWbsQuantity {

	public static final String[] fieldNames = new String[] {"PROJECT_ID", "CATEGORY_ID", "PRJ_CAPTION", "TREE_CODE", "CAT_CAPTION", "UNIT", "METRIC", "QUANTITY", "SUM_QUANTITY", "SUM_MANHOUR", "SUM_OVERTIME", "BEGDA", "ENDDA", "LAST_QUANTITY", "NEXT_BEGDA"};
	
	private AggrWbsQuantityId id;
	private String prjCaption;
	private String treeCode;
	private String catCaption;
	private String unit;
	private float  metric;
	private float  quantity;
	private float  sumQuantity;
	private int    sumManhour;
	private int    sumOvertime;
	private Date   begda;
	private Date   endda;
	private float  lastQuantity;
	private Date  nextBegda;



	public AggrWbsQuantity(AggrWbsQuantityId id, String prjCaption, String treeCode, String catCaption, String unit,
			float metric, float quantity, float sumQuantity, int sumManhour, int sumOvertime, Date begda, Date endda, float lastQuantity) {
		super();
		this.id = id;
		this.prjCaption = prjCaption;
		this.treeCode = treeCode;
		this.catCaption = catCaption;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
		this.sumQuantity = sumQuantity;
		this.sumManhour = sumManhour;
		this.sumOvertime = sumOvertime;
		this.begda = begda;
		this.endda = endda;
		this.lastQuantity = lastQuantity;
		Calendar c = Calendar.getInstance();
		c.setTime(endda);
		c.add(Calendar.DATE, 1);
		this.nextBegda = c.getTime();
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)) })
	public AggrWbsQuantityId getId() {
		return id;
	}

	public void setId(AggrWbsQuantityId id) {
		this.id = id;
	}

	@Column(name="TREE_CODE")
	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	@Column(name="CAT_CAPTION")
	public String getCatCaption() {
		return catCaption;
	}

	public void setCatCaption(String catCaption) {
		this.catCaption = catCaption;
	}

	@Column(name="PRJ_CAPTION")
	public String getPrjCaption() {
		return prjCaption;
	}

	public void setPrjCaption(String prjCaption) {
		this.prjCaption = prjCaption;
	}

	@Column(name="UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name="METRIC")
	public float getMetric() {
		return metric;
	}

	public void setMetric(float metric) {
		this.metric = metric;
	}

	@Column(name="QUANTITY")
	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Column(name="SUM_QUANTITY")
	public float getSumQuantity() {
		return sumQuantity;
	}

	public void setSumQuantity(float sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	@Column(name="SUM_MANHOUR")
	public int getSumManhour() {
		return sumManhour;
	}

	public void setSumManhour(int sumManhour) {
		this.sumManhour = sumManhour;
	}

	@Column(name="SUM_OVERTIME")
	public int getSumOvertime() {
		return sumOvertime;
	}

	public void setSumOvertime(int sumOvertime) {
		this.sumOvertime = sumOvertime;
	}

	@Column(name="BEGDA")
	public Date getBegda() {
		return begda;
	}

	public void setBegda(Date begda) {
		this.begda = begda;
	}

	@Column(name="ENDDA")
	public Date getEndda() {
		return endda;
	}

	public void setEndda(Date endda) {
		this.endda = endda;
	}

	@Column(name="LAST_QUANTITY")
	public float getLastQuantity() {
		return lastQuantity;
	}

	public void setLastQuantity(float lastQuantity) {
		this.lastQuantity = lastQuantity;
	}
	
	@Column(name="NEXT_BEGDA")
	public Date getNextBegda() {
		return nextBegda;
	}

	public void setNextBegda(Date nextBegda) {
		this.nextBegda = nextBegda;
	}

}
