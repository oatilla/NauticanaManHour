package com.nauticana.manhour.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WORKER")
public class Worker implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "WORKER";
	public static final String[] fieldNames = new String[] { "WORKER_ID", "CAPTION", "PERSON_ID", "SUBCONTRACTOR_ID" };
	private int workerId;
	private Subcontractor subcontractor;
	private String caption;
	private Integer personId;
	private Set<ProjectTeamPerson> projectTeamPersons = new HashSet<ProjectTeamPerson>(0);

	public Worker() {
	}

	public Worker(int workerId, String caption) {
		this.workerId = workerId;
		this.caption = caption;
	}

	public Worker(int workerId, Subcontractor subcontractor, String caption, Integer personId, Set<ProjectTeamPerson> projectTeamPersons) {
		this.workerId = workerId;
		this.subcontractor = subcontractor;
		this.caption = caption;
		this.personId = personId;
		this.projectTeamPersons = projectTeamPersons;
	}

	@Id
	@Column(name = "WORKER_ID", unique = true, nullable = false, precision = 8, scale = 0)
	public int getId() {
		return this.workerId;
	}

	public void setId(int workerId) {
		this.workerId = workerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBCONTRACTOR_ID")
	public Subcontractor getSubcontractor() {
		return this.subcontractor;
	}

	public void setSubcontractor(Subcontractor subcontractor) {
		this.subcontractor = subcontractor;
	}

	@Column(name = "CAPTION", nullable = false, length = 50)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "PERSON_ID", precision = 8, scale = 0)
	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "worker")
	public Set<ProjectTeamPerson> getProjectTeamPersons() {
		return this.projectTeamPersons;
	}

	public void setProjectTeamPersons(Set<ProjectTeamPerson> projectTeamPersons) {
		this.projectTeamPersons = projectTeamPersons;
	}

}
