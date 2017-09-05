package com.gama.manhour.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_WBS_MANHOUR", schema = "ADSAAT")
public class ProjectWbsManhour implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"PROJECT_ID","CATEGORY_ID","TEAM_ID","WORKER_ID","ACTIVITY_DATE","MANHOUR","OVERTIME","LOCAL_MH","FOREIGN_MH","TR_MH"};
	private ProjectWbsManhourId id;
	private ProjectTeamPerson projectTeamPerson;
	private ProjectWbs projectWbs;
	private byte manhour;
	private Byte overtime;
	private Byte localMh;
	private Byte foreignMh;
	private Byte trMh;

	public ProjectWbsManhour() {
	}

	public ProjectWbsManhour(ProjectWbsManhourId id, ProjectTeamPerson projectTeamPerson, ProjectWbs projectWbs,
			byte manhour) {
		this.id = id;
		this.projectTeamPerson = projectTeamPerson;
		this.projectWbs = projectWbs;
		this.manhour = manhour;
	}

	public ProjectWbsManhour(ProjectWbsManhourId id, ProjectTeamPerson projectTeamPerson, ProjectWbs projectWbs, byte manhour, Byte overtime, Byte localMh, Byte foreignMh, Byte trMh) {
       this.id = id;
       this.projectTeamPerson = projectTeamPerson;
       this.projectWbs = projectWbs;
       this.manhour = manhour;
       this.overtime = overtime;
       this.localMh = localMh;
       this.foreignMh = foreignMh;
       this.trMh = trMh;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="projectId", column=@Column(name="PROJECT_ID", nullable=false, precision=8, scale=0) ), 
        @AttributeOverride(name="categoryId", column=@Column(name="CATEGORY_ID", nullable=false, precision=8, scale=0) ), 
        @AttributeOverride(name="teamId", column=@Column(name="TEAM_ID", nullable=false, precision=8, scale=0) ), 
        @AttributeOverride(name="workerId", column=@Column(name="WORKER_ID", nullable=false, precision=8, scale=0) ), 
        @AttributeOverride(name="activityDate", column=@Column(name="ACTIVITY_DATE", nullable=false, length=7) ) } )
    public ProjectWbsManhourId getId() {
        return this.id;
    }

	public void setId(ProjectWbsManhourId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "WORKER_ID", referencedColumnName = "WORKER_ID", nullable = false, insertable = false, updatable = false) })
	public ProjectTeamPerson getProjectTeamPerson() {
		return this.projectTeamPerson;
	}

	public void setProjectTeamPerson(ProjectTeamPerson projectTeamPerson) {
		this.projectTeamPerson = projectTeamPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", nullable = false, insertable = false, updatable = false) })
	public ProjectWbs getProjectWbs() {
		return this.projectWbs;
	}

	public void setProjectWbs(ProjectWbs projectWbs) {
		this.projectWbs = projectWbs;
	}

	@Column(name = "MANHOUR", nullable = false, precision = 2, scale = 0)
	public byte getManhour() {
		return this.manhour;
	}

	public void setManhour(byte manhour) {
		this.manhour = manhour;
	}

	@Column(name="OVERTIME", precision=2, scale=0)
    public Byte getOvertime() {
        return this.overtime;
    }

	public void setOvertime(Byte overtime) {
        this.overtime = overtime;
    }

	@Column(name = "LOCAL_MH", precision = 2, scale = 0)
	public Byte getLocalMh() {
		return this.localMh;
	}

	public void setLocalMh(Byte localMh) {
		this.localMh = localMh;
	}

	@Column(name="FOREIGN_MH", precision=2, scale=0)
    public Byte getForeignMh() {
        return this.foreignMh;
    }

	public void setForeignMh(Byte foreignMh) {
        this.foreignMh = foreignMh;
    }

	@Column(name = "TR_MH", precision = 2, scale = 0)
	public Byte getTrMh() {
		return this.trMh;
	}

	public void setTrMh(Byte trMh) {
		this.trMh = trMh;
	}

}
