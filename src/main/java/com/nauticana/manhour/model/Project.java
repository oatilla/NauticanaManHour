package com.nauticana.manhour.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.jpamodelgen.xml.jaxb.JoinColumn;

@Entity
@Table(name = "PROJECT")
public class Project implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "PROJECT";
	public static final String[] fieldNames = new String[] { "PROJECT_ID", "CAPTION", "COUNTRY", "START_DATE", "DURATION", "ORGANIZATION_ID" };
	private int projectId;
	private String caption;
	private String country;
	private Date startDate;
	private int duration;
	private Integer organizationId;
	
	private Set<ProjectTeam> projectTeams = new HashSet<ProjectTeam>(0);
	
	private Set<ProjectWbs> projectWbses = new HashSet<ProjectWbs>(0);

	public Project() {
	}

	public Project(int projectId, String caption) {
		this.projectId = projectId;
		this.caption = caption;
	}

	public Project(int projectId, String caption, Integer organizationId, Set<ProjectTeam> projectTeams, Set<ProjectWbs> projectWbses) {
		this.projectId = projectId;
		this.caption = caption;
		this.organizationId = organizationId;
		this.projectTeams = projectTeams;
		this.projectWbses = projectWbses;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROJECT_ID_SEQ")
	@SequenceGenerator(name="PROJECT_ID_SEQ", sequenceName="PROJECT_ID_SEQ")
	@Column(name = "PROJECT_ID", unique = true, nullable = false, precision = 8, scale = 0)
	public int getId() {
		return this.projectId;
	}

	public void setId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name = "CAPTION", nullable = false, length = 50)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "COUNTRY", nullable = false, length = 3)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "START_DATE", nullable = false)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "DURATION", nullable = false, precision = 8, scale = 0)
	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Column(name = "ORGANIZATION_ID", precision = 8, scale = 0)
	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<ProjectTeam> getProjectTeams() {
		return this.projectTeams;
	}

	public void setProjectTeams(Set<ProjectTeam> projectTeams) {
		this.projectTeams = projectTeams;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<ProjectWbs> getProjectWbses() {
		return this.projectWbses;
	}

	public void setProjectWbses(Set<ProjectWbs> projectWbses) {
		this.projectWbses = projectWbses;
	}
	

//	    @ManyToMany
//	    @JoinTable(name="CATEGORY", joinColumns={@JoinColumn(name="PROJECT_ID")}, inverseJoinColumns={@JoinColumn(name="CATEGORY_ID")})
//	    @OrderBy(value="orderField")
//	    private List<Category> BList;
//
//	
	

}
