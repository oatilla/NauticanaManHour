package com.nauticana.manhour.model;

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
@Table(name = "PROJECT_WBS_MANHOUR")
public class ProjectWbsManhour implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "PROJECT_WBS_MANHOUR";
	public static final String[] fieldNames = new String[] { "PROJECT_ID", "CATEGORY_ID", "TEAM_ID", "WORKER_ID", "ACTIVITY_DATE", "MANHOUR", "OVERTIME", "LOCAL_MH", "FOREIGN_MH", "TR_MH" };
	private ProjectWbsManhourId id;
	private ProjectTeamPerson projectTeamPerson;
	private ProjectWbs projectWbs;
	private short manhour;
	private short overtime;
	private short localMh;
	private short foreignMh;
	private short trMh;

	public ProjectWbsManhour() {
	}

	public ProjectWbsManhour(ProjectWbsManhourId id, ProjectTeamPerson projectTeamPerson, ProjectWbs projectWbs, byte manhour) {
		this.id = id;
		this.projectTeamPerson = projectTeamPerson;
		this.projectWbs = projectWbs;
		this.manhour = manhour;
	}

	public ProjectWbsManhour(ProjectWbsManhourId id, ProjectTeamPerson projectTeamPerson, ProjectWbs projectWbs,
			short manhour, short overtime, short localMh, short foreignMh, short trMh) {
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

	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "workerId", column = @Column(name = "WORKER_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "activityDate", column = @Column(name = "ACTIVITY_DATE", nullable = false, length = 7)) })
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

	@Column(name = "MANHOUR", nullable = false, precision = 4, scale = 0)
	public short getManhour() {
		return this.manhour;
	}

	public void setManhour(short manhour) {
		this.manhour = manhour;
	}

	@Column(name = "OVERTIME", precision = 4, scale = 0)
	public short getOvertime() {
		return this.overtime;
	}

	public void setOvertime(short overtime) {
		this.overtime = overtime;
	}

	@Column(name = "LOCAL_MH", precision = 4, scale = 0)
	public short getLocalMh() {
		return this.localMh;
	}

	public void setLocalMh(short localMh) {
		this.localMh = localMh;
	}

	@Column(name = "FOREIGN_MH", precision = 4, scale = 0)
	public short getForeignMh() {
		return foreignMh;
	}

	public void setForeignMh(short foreignMh) {
		this.foreignMh = foreignMh;
	}

	@Column(name = "TR_MH", precision = 4, scale = 0)
	public short getTrMh() {
		return this.trMh;
	}

	public void setTrMh(short trMh) {
		this.trMh = trMh;
	}

}
