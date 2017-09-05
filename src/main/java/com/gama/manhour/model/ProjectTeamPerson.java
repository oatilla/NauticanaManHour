package com.gama.manhour.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_TEAM_PERSON", schema = "ADSAAT")
public class ProjectTeamPerson implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"PROJECT_ID","TEAM_ID",	"WORKER_ID","TEAM_LEAD"};

	private ProjectTeamPersonId id;
	private Worker worker;
	private ProjectTeam projectTeam;
	private char teamLead;
	private Set<ProjectWbsManhour> projectWbsManhours = new HashSet<ProjectWbsManhour>(0);

	public ProjectTeamPerson() {
	}

	public ProjectTeamPerson(ProjectTeamPersonId id, Worker worker, ProjectTeam projectTeam, char teamLead) {
		this.id = id;
		this.worker = worker;
		this.projectTeam = projectTeam;
		this.teamLead = teamLead;
	}

	public ProjectTeamPerson(ProjectTeamPersonId id, Worker worker, ProjectTeam projectTeam, char teamLead,
			Set<ProjectWbsManhour> projectWbsManhours) {
		this.id = id;
		this.worker = worker;
		this.projectTeam = projectTeam;
		this.teamLead = teamLead;
		this.projectWbsManhours = projectWbsManhours;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "workerId", column = @Column(name = "WORKER_ID", nullable = false, precision = 8, scale = 0)) })
	public ProjectTeamPersonId getId() {
		return this.id;
	}

	public void setId(ProjectTeamPersonId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKER_ID", nullable = false, insertable = false, updatable = false)
	public Worker getWorker() {
		return this.worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false, insertable = false, updatable = false) })
	public ProjectTeam getProjectTeam() {
		return this.projectTeam;
	}

	public void setProjectTeam(ProjectTeam projectTeam) {
		this.projectTeam = projectTeam;
	}

	@Column(name = "TEAM_LEAD", nullable = false, length = 1)
	public char getTeamLead() {
		return this.teamLead;
	}

	public void setTeamLead(char teamLead) {
		this.teamLead = teamLead;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectTeamPerson")
	public Set<ProjectWbsManhour> getProjectWbsManhours() {
		return this.projectWbsManhours;
	}

	public void setProjectWbsManhours(Set<ProjectWbsManhour> projectWbsManhours) {
		this.projectWbsManhours = projectWbsManhours;
	}

}
