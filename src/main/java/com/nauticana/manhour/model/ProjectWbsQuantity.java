package com.nauticana.manhour.model;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PROJECT_WBS_QUANTITY")
public class ProjectWbsQuantity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "PROJECT_WBS_QUANTITY";
	public static final String[] fieldNames = new String[] { "PROJECT_ID", "CATEGORY_ID", "TEAM_ID", "BEGDA", "ENDDA", "QUANTITY", "STATUS" };
	public static final String rootMapping = "projectWbsQuantity";

	private ProjectWbsQuantityId id;
	private Date endda;
	private ProjectWbs projectWbs;
	private ProjectTeam projectTeam;
	private float quantity;
	private String status;

	public ProjectWbsQuantity() {
	}

	public ProjectWbsQuantity(ProjectWbsQuantityId id, ProjectWbs projectWbs, ProjectTeam projectTeam, float quantity, String status) {
		this.id = id;
		this.projectWbs = projectWbs;
		this.projectTeam = projectTeam;
		this.quantity = quantity;
		this.status = status;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false)) })
	public ProjectWbsQuantityId getId() {
		return this.id;
	}

	public void setId(ProjectWbsQuantityId id) {
		this.id = id;
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
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "ENDDA", nullable = false)
	public Date getEndda() {
		return endda;
	}

	public void setEndda(Date endda) {
		this.endda = endda;
	}

	@Column(name = "QUANTITY", nullable = false, precision = 8)
	public float getQuantity() {
		return this.quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Column(name = "STATUS", nullable = false, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
