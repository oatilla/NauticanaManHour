package com.nauticana.manhour.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class ViewWbsQuantity {

	public static final String[] fieldNames = new String[] {"PROJECT_ID", "CATEGORY_ID", "PRJ_CAPTION", "TREE_CODE", "CAT_CAPTION", "UNIT", "METRIC", "QUANTITY", "PUP_METRIC", "PUP_QUANTITY", "SUM_QUANTITY", "SUM_MANHOUR"};//, "BEGDA", "ENDDA", "LAST_QUANTITY", "NEXT_BEGDA"};
	
	private ViewWbsQuantityId id;
	private String prjCaption;
	private String treeCode;
	private String catCaption;
	private String unit;
	private float  metric;
	private float  quantity;
	private float  pupMetric;
	private float  pupQuantity;
	private float  sumQuantity;
	private int    sumManhour;
//	private Date   begda;
//	private Date   endda;
//	private float  lastQuantity;
//	private Date   nextBegda;
	
	// transient values
	private float workforce;
	private float pupWorkforce;
	private float sumMetric;
	
	private float progress;
	private float earnedManhour;
	private float performans;
	private float performansMultip;
	private float estimatedCompMH;
	private float remainingMH;
	private float deviation;
	



	

	public ViewWbsQuantity(ViewWbsQuantityId id, String prjCaption, String treeCode, String catCaption, String unit,
			float metric, float quantity, float pupMetric, float pupQuantity, float sumQuantity, int sumManhour) {//, Date begda, Date endda, float lastQuantity) {
		super();
		this.id = id;
		this.prjCaption = prjCaption;
		this.treeCode = treeCode;
		this.catCaption = catCaption;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
		this.pupMetric = pupMetric;
		this.pupQuantity = pupQuantity;
		this.sumQuantity = sumQuantity;
		this.sumManhour = sumManhour;
//		this.begda = begda;
//		this.endda = endda;
//		this.lastQuantity = lastQuantity;
//		Calendar c = Calendar.getInstance();
//		if (endda == null)
//			c.setTimeInMillis(System.currentTimeMillis());
//		else
//			c.setTimeInMillis(endda.getTime());
//		c.add(Calendar.DATE, 1);
//		this.nextBegda = c.getTime();

	
		if (metric == 0)
			this.workforce = 0;
		else
			this.workforce = quantity/metric;
		
		if (pupMetric == 0)
			this.pupWorkforce = 0;
		else
			this.pupWorkforce = pupQuantity/pupMetric;
		
		if (sumManhour == 0)
			sumMetric = 0;
		else
			sumMetric = sumQuantity/sumManhour;
	
		if(quantity == 0 )  this.progress=0;
		else this.progress= (sumQuantity/quantity)*100;
		
		this.earnedManhour = this.progress * this.pupWorkforce;
		
		if (this.sumManhour == 0) this.performans = 0;
		else this.performans = this.earnedManhour / this.sumManhour;
		
		if (this.performans == 0) this.performansMultip = 0;
		else this.performansMultip = 1 / this.performans;
		
		this.estimatedCompMH = this.performansMultip * this.workforce;
		
		this.remainingMH = this.estimatedCompMH - this.sumManhour;
		
		if (this.workforce == 0) this.deviation = 0;
		else this.deviation = 1 - this.remainingMH / this.workforce;
	
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)) })
	public ViewWbsQuantityId getId() {
		return id;
	}

	public void setId(ViewWbsQuantityId id) {
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

	@Column(name="PUP_QUANTITY")
	public float getPupQuantity() {
		return pupQuantity;
	}

	public void setPupQuantity(float pupQuantity) {
		this.pupQuantity = pupQuantity;
	}

	@Column(name="PUP_METRIC")
	public float getPupMetric() {
		return pupMetric;
	}

	public void setPupMetric(float pupMetric) {
		this.pupMetric = pupMetric;
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

//	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	@Column(name="BEGDA")
//	public Date getBegda() {
//		return begda;
//	}
//
//	public void setBegda(Date begda) {
//		this.begda = begda;
//	}
//
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	@Column(name="ENDDA")
//	public Date getEndda() {
//		return endda;
//	}
//
//	public void setEndda(Date endda) {
//		this.endda = endda;
//	}
//
//	@Column(name="LAST_QUANTITY")
//	public float getLastQuantity() {
//		return lastQuantity;
//	}
//
//	public void setLastQuantity(float lastQuantity) {
//		this.lastQuantity = lastQuantity;
//	}
//	
	@Transient
	public float getWorkforce() {
		return this.workforce;
	}
	
	@Transient
	public float getPupWorkforce() {
		return this.pupWorkforce;
	}
	
	@Transient
	public float getSumMetric() {
		return this.sumMetric;
	}
	
	
	
	@Transient
	public float getProgress() {
		return progress;
	}

	@Transient
	public float getEarnedManhour() {
		return earnedManhour;
	}

	@Transient
	public float getPerformans() {
		return performans;
	}
	@Transient
	public float getPerformansMultip() {
		return performansMultip;
	}
	@Transient
	public float getEstimatedCompMH() {
		return estimatedCompMH;
	}
	@Transient
	public float getRemainingMH() {
		return remainingMH;
	}
	@Transient
	public float getDeviation() {
		return deviation;
	}
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	@Column(name="NEXT_BEGDA")
//	public Date getNextBegda() {
//		return nextBegda;
//	}
//
//	public void setNextBegda(Date nextBegda) {
//		this.nextBegda = nextBegda;
//	}

	@Override
	public String toString() {
		return id.getProjectId() + "," + id.getCategoryId() + "," + prjCaption + "," + treeCode + "," + catCaption + "," + unit  + "," + metric  + "," + quantity  + "," + sumQuantity + "," + sumManhour;// + "," + begda + "," + endda + "," + lastQuantity + "," + nextBegda;
	}
}
