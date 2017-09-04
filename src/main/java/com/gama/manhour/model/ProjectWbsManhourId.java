package com.gama.manhour.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectWbsManhourId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int projectId;
	private int categoryId;
	private int teamId;
	private int workerId;
	private Date activityDate;

	public ProjectWbsManhourId() {
	}

	public ProjectWbsManhourId(int projectId, int categoryId, int teamId, int workerId, Date activityDate) {
       this.projectId = projectId;
       this.categoryId = categoryId;
       this.teamId = teamId;
       this.workerId = workerId;
       this.activityDate = activityDate;
    }

	@Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)
	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)
	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	@Column(name = "WORKER_ID", nullable = false, precision = 8, scale = 0)
	public int getWorkerId() {
		return this.workerId;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}

	@Column(name="ACTIVITY_DATE", nullable=false, length=7)
    public Date getActivityDate() {
        return this.activityDate;
    }

	public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ProjectWbsManhourId) ) return false;
		 ProjectWbsManhourId castOther = ( ProjectWbsManhourId ) other; 
         
		 return (this.getProjectId()==castOther.getProjectId())
 && (this.getCategoryId()==castOther.getCategoryId())
 && (this.getTeamId()==castOther.getTeamId())
 && (this.getWorkerId()==castOther.getWorkerId())
 && ( (this.getActivityDate()==castOther.getActivityDate()) || ( this.getActivityDate()!=null && castOther.getActivityDate()!=null && this.getActivityDate().equals(castOther.getActivityDate()) ) );
   }

	public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getProjectId();
         result = 37 * result + this.getCategoryId();
         result = 37 * result + this.getTeamId();
         result = 37 * result + this.getWorkerId();
         result = 37 * result + ( getActivityDate() == null ? 0 : this.getActivityDate().hashCode() );
         return result;
   }

}
