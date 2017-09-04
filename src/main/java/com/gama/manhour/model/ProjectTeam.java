package com.gama.manhour.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROJECT_TEAM", schema = "ADSAAT")
public class ProjectTeam implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"PROJECT_ID","TEAM_ID",	"CAPTION","BED_DATE","END_DATE"};

	private ProjectTeamId id;
	private Project project;
	private String caption;
	private Date bedDate;
	private Date endDate;
	private Set<ProjectTeamPerson> projectTeamPersons = new HashSet<ProjectTeamPerson>(0);

	public ProjectTeam() {
	}

	public ProjectTeam(ProjectTeamId id, Project project, String caption) {
        this.id = id;
        this.project = project;
        this.caption = caption;
    }

	public ProjectTeam(ProjectTeamId id, Project project, String caption, Date bedDate, Date endDate, Set<ProjectTeamPerson> projectTeamPersons) {
       this.id = id;
       this.project = project;
       this.caption = caption;
       this.bedDate = bedDate;
       this.endDate = endDate;
       this.projectTeamPersons = projectTeamPersons;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="projectId", column=@Column(name="PROJECT_ID", nullable=false, precision=8, scale=0) ), 
        @AttributeOverride(name="teamId", column=@Column(name="TEAM_ID", nullable=false, precision=8, scale=0) ) } )
    public ProjectTeamId getId() {
        return this.id;
    }

	public void setId(ProjectTeamId id) {
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

	@Column(name="CAPTION", nullable=false, length=50)
    public String getCaption() {
        return this.caption;
    }

	public void setCaption(String caption) {
        this.caption = caption;
    }

	@Temporal(TemporalType.DATE)
	@Column(name = "BED_DATE", length = 7)
	public Date getBedDate() {
		return this.bedDate;
	}

	public void setBedDate(Date bedDate) {
		this.bedDate = bedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectTeam")
	public Set<ProjectTeamPerson> getProjectTeamPersons() {
		return this.projectTeamPersons;
	}

	public void setProjectTeamPersons(Set<ProjectTeamPerson> projectTeamPersons) {
		this.projectTeamPersons = projectTeamPersons;
	}

}
