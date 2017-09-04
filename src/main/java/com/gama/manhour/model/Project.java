package com.gama.manhour.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT", schema = "ADSAAT")
public class Project implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"PROJECT_ID","CAPTION","ORGANIZATION_ID"};
	private int projectId;
	private String caption;
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

	@Column(name = "PROJECT_ID", unique = true, nullable = false, precision = 8, scale = 0)
	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name="CAPTION", nullable=false, length=50)
    public String getCaption() {
        return this.caption;
    }

	public void setCaption(String caption) {
        this.caption = caption;
    }

	@Column(name="ORGANIZATION_ID", precision=8, scale=0)
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

}
