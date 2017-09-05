package com.nauticana.manhour.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "PROJECT_WBS", schema = "ADSAAT")
public class ProjectWbs implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"PROJECT_ID","CATEGORY_ID","UNIT","METRIC","WORKFORCE","PUP_METRIC","PUP_WORKFORCE"};
	private ProjectWbsId id;
	private Project project;
	private Category category;
	private String unit;
	private BigDecimal metric;
	private BigDecimal workforce;
	private BigDecimal pupMetric;
	private BigDecimal pupWorkforce;
	private Set<ProjectWbsManhour> projectWbsManhours = new HashSet<ProjectWbsManhour>(0);

	private Set<ProjectWbsQuantity> projectWbsQuantities = new HashSet<ProjectWbsQuantity>(0);

    public ProjectWbs() {
    }

	public ProjectWbs(ProjectWbsId id, Project project, Category category, String unit, BigDecimal metric, BigDecimal workforce) {
        this.id = id;
        this.project = project;
        this.category = category;
        this.unit = unit;
        this.metric = metric;
        this.workforce = workforce;
    }

	public ProjectWbs(ProjectWbsId id, Project project, Category category, String unit, BigDecimal metric, BigDecimal workforce, BigDecimal pupMetric, BigDecimal pupWorkforce, Set<ProjectWbsManhour> projectWbsManhours, Set<ProjectWbsQuantity> projectWbsQuantities) {
       this.id = id;
       this.project = project;
       this.category = category;
       this.unit = unit;
       this.metric = metric;
       this.workforce = workforce;
       this.pupMetric = pupMetric;
       this.pupWorkforce = pupWorkforce;
       this.projectWbsManhours = projectWbsManhours;
       this.projectWbsQuantities = projectWbsQuantities;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="projectId", column=@Column(name="PROJECT_ID", nullable=false, precision=8, scale=0) ), 
        @AttributeOverride(name="categoryId", column=@Column(name="CATEGORY_ID", nullable=false, precision=8, scale=0) ) } )
    public ProjectWbsId getId() {
        return this.id;
    }

	public void setId(ProjectWbsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID", nullable = false, insertable = false, updatable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name="UNIT", nullable=false, length=3)
    public String getUnit() {
        return this.unit;
    }

	public void setUnit(String unit) {
        this.unit = unit;
    }

	@Column(name="METRIC", nullable=false, precision=6)
    public BigDecimal getMetric() {
        return this.metric;
    }

	public void setMetric(BigDecimal metric) {
        this.metric = metric;
    }

	@Column(name = "WORKFORCE", nullable = false, precision = 6)
	public BigDecimal getWorkforce() {
		return this.workforce;
	}

	public void setWorkforce(BigDecimal workforce) {
		this.workforce = workforce;
	}

	@Column(name="PUP_METRIC", precision=6)
    public BigDecimal getPupMetric() {
        return this.pupMetric;
    }

	public void setPupMetric(BigDecimal pupMetric) {
        this.pupMetric = pupMetric;
    }

	@Column(name = "PUP_WORKFORCE", precision = 6)
	public BigDecimal getPupWorkforce() {
		return this.pupWorkforce;
	}

	public void setPupWorkforce(BigDecimal pupWorkforce) {
		this.pupWorkforce = pupWorkforce;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectWbs")
	public Set<ProjectWbsManhour> getProjectWbsManhours() {
		return this.projectWbsManhours;
	}

	public void setProjectWbsManhours(Set<ProjectWbsManhour> projectWbsManhours) {
		this.projectWbsManhours = projectWbsManhours;
	}

@OneToMany(fetch=FetchType.LAZY, mappedBy="projectWbs")
    public Set<ProjectWbsQuantity> getProjectWbsQuantities() {
        return this.projectWbsQuantities;
    }

	public void setProjectWbsQuantities(Set<ProjectWbsQuantity> projectWbsQuantities) {
        this.projectWbsQuantities = projectWbsQuantities;
    }




}
